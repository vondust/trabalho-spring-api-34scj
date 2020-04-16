package br.com.fiap.application;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.adapter.datastore.entity.AlunoEntity;
import br.com.fiap.adapter.datastore.repository.AlunoRepository;
import br.com.fiap.core.mapper.AlunoMapper;
import br.com.fiap.core.mapper.ModelMapper;
import br.com.fiap.core.model.Aluno;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository repository;

	private static final ModelMapper<Aluno, AlunoEntity> MAPPER = new AlunoMapper();

	public Aluno cria(Aluno model) {
		if (Objects.nonNull(model.getCartao()) && Objects.isNull(model.getCartao().getId()))
			model.getCartao().setValorUtilizado(BigDecimal.ZERO);

		return MAPPER.toDto(repository.save(MAPPER.toEntity(model)));
	}

	public Aluno atualiza(Long id, Aluno model) {
		buscaPorId(id);
		return MAPPER.toDto(repository.save(MAPPER.toEntity(model)));
	}

	public Aluno buscaPorId(Long id) {
		return MAPPER.toDto(repository.findById(id).orElseThrow(() -> new EntityNotFoundException(id.toString())));
	}

	public void remove(Long id) {
		repository.deleteById(id);
	}

	public List<Aluno> busca() {
		return MAPPER.toDto(repository.findAll());
	}
}
