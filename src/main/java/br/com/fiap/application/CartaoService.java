package br.com.fiap.application;

import java.util.List;
import java.util.Objects;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.adapter.datastore.entity.CartaoEntity;
import br.com.fiap.adapter.datastore.repository.CartaoRepository;
import br.com.fiap.core.mapper.CartaoMapper;
import br.com.fiap.core.mapper.ModelMapper;
import br.com.fiap.core.model.Aluno;
import br.com.fiap.core.model.Cartao;

@Service
public class CartaoService {

	@Autowired
	private CartaoRepository repository;

	@Autowired
	private AlunoService serviceAluno;

	private static final ModelMapper<Cartao, CartaoEntity> MAPPER = new CartaoMapper();

	public Cartao cria(Long idAluno, Cartao model) {
		Aluno aluno = serviceAluno.buscaPorId(idAluno);
		aluno.setCartao(model);
		return serviceAluno.cria(aluno).getCartao();
	}

	public Cartao atualiza(Long idAluno, Long id, Cartao model) {
		Aluno aluno = serviceAluno.buscaPorId(idAluno);
		if (Objects.isNull(aluno.getCartao()) || aluno.getCartao().getId() != id)
			new ResponseStatusException(HttpStatus.CONFLICT);

		return MAPPER.toDto(repository.save(MAPPER.toEntity(model)));
	}

	public Cartao buscaPorId(Long id) {
		return MAPPER.toDto(repository.findById(id).orElseThrow(() -> new EntityNotFoundException(id.toString())));
	}

	public void remove(Long id) {
		repository.remove(id);
	}

	public List<Cartao> busca() {
		return MAPPER.toDto(repository.findAll());
	}
}
