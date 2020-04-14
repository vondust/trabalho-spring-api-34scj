package br.com.fiap.core.util;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.adapter.datastore.entity.AlunoEntity;
import br.com.fiap.adapter.datastore.repository.AlunoRepository;
import br.com.fiap.adapter.datastore.repository.CartaoRepository;
import br.com.fiap.adapter.datastore.repository.CompraRepository;

@Component
public class CargaInicial {

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private CartaoRepository cartaoRepository;

	@Autowired
	private CompraRepository compraRepository;

	public void carga(Integer batchSize) {
		List<AlunoEntity> carga = CargaDefault.carregaAlunos(batchSize);
		List<AlunoEntity> alunos = alunoRepository.saveAll(carga);
		compraRepository.saveAll(CargaDefault.carregaCompras(alunos));
		cartaoRepository.saveAll(alunos.stream().map(AlunoEntity::getCartao).collect(Collectors.toList()));
	}
}
