package br.com.fiap.application;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.adapter.datastore.entity.CartaoEntity;
import br.com.fiap.adapter.datastore.entity.CompraEntity;
import br.com.fiap.adapter.datastore.repository.CartaoRepository;
import br.com.fiap.adapter.datastore.repository.CompraRepository;
import br.com.fiap.core.mapper.CartaoMapper;
import br.com.fiap.core.mapper.CompraMapper;
import br.com.fiap.core.mapper.ModelMapper;
import br.com.fiap.core.model.Cartao;
import br.com.fiap.core.model.Compra;

@Service
public class CompraService {

	@Autowired
	private CompraRepository repository;

	@Autowired
	private CartaoRepository repositoryCartao;

	private static final ModelMapper<Compra, CompraEntity> MAPPER = new CompraMapper();
	private static final ModelMapper<Cartao, CartaoEntity> MAPPER_CARTAO = new CartaoMapper();

	public Compra cria(Compra model) {
		Cartao cartao = buscaCartao(model.getIdCartaoUtilizado());
		atualizaValorUtilizadoCartao(cartao, model.getValorDebito());

		return MAPPER.toDto(repository.save(MAPPER.toEntity(model)));
	}

	public Compra atualiza(Long id, Compra model) {
		Compra compra = buscaPorId(id);
		Cartao cartao = buscaCartao(compra.getIdCartaoUtilizado());

		model.setIdCartaoUtilizado(compra.getIdCartaoUtilizado());
		atualizaValorUtilizadoCartao(cartao, model.getValorDebito().subtract(compra.getValorDebito()));

		return MAPPER.toDto(repository.save(MAPPER.toEntity(model)));
	}

	public Compra buscaPorId(Long id) {
		return MAPPER.toDto(repository.findById(id).orElseThrow(() -> new EntityNotFoundException(id.toString())));
	}

	public void remove(Long id) {
		repository.deleteById(id);
	}

	public List<Compra> busca() {
		return MAPPER.toDto(repository.findAll());
	}

	private Cartao buscaCartao(Long idCartao) {
		return MAPPER_CARTAO.toDto(repositoryCartao.findById(idCartao)
				.orElseThrow(() -> new EntityNotFoundException(idCartao.toString())));
	}

	private void atualizaValorUtilizadoCartao(Cartao cartao, BigDecimal valorDebito) {
		cartao.atualizaValorUtilizado(valorDebito);
		repositoryCartao.save(MAPPER_CARTAO.toEntity(cartao));
	}
}
