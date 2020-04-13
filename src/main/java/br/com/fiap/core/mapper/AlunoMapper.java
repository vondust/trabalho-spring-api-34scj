package br.com.fiap.core.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import br.com.fiap.adapter.datastore.entity.AlunoEntity;
import br.com.fiap.adapter.datastore.entity.CartaoEntity;
import br.com.fiap.core.model.Aluno;
import br.com.fiap.core.model.Cartao;

public class AlunoMapper implements ModelMapper<Aluno, AlunoEntity> {

	private static final ModelMapper<Cartao, CartaoEntity> MAPPER_CARTAO = new CartaoMapper();

	public Aluno toDto(AlunoEntity entity) {
		return Aluno.builder()
				.id(entity.getId())
				.nome(entity.getNome())
				.matricula(entity.getMatricula())
				.turma(entity.getTurma())
				.cartao(MAPPER_CARTAO.toDto(entity.getCartao()))
				.build();
	}

	public AlunoEntity toEntity(Aluno dto) {
		if (Objects.isNull(dto))
			return null;

		return AlunoEntity.builder()
				.id(dto.getId())
				.nome(dto.getNome())
				.matricula(dto.getMatricula())
				.turma(dto.getTurma())
				.cartao(MAPPER_CARTAO.toEntity(dto.getCartao()))
				.build();
	}

	public List<AlunoEntity> toEntity(List<Aluno> dtos) {
		return dtos.stream().map(this::toEntity).collect(Collectors.toList());
	}

	public List<Aluno> toDto(List<AlunoEntity> entities) {
		return entities.stream().map(this::toDto).collect(Collectors.toList());
	}
}
