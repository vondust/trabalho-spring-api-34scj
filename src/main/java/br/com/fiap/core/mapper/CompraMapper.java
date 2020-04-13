package br.com.fiap.core.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import br.com.fiap.adapter.datastore.entity.CartaoEntity;
import br.com.fiap.adapter.datastore.entity.CompraEntity;
import br.com.fiap.core.model.Compra;

public class CompraMapper implements ModelMapper<Compra, CompraEntity> {

	public CompraEntity toEntity(Compra dto) {
		if (Objects.isNull(dto))
			return null;

		return CompraEntity.builder()
				.id(dto.getId())
				.valorDebito(dto.getValorDebito())
				.cartaoUtilizado(CartaoEntity.builder().id(dto.getIdCartaoUtilizado()).build())
				.build();
	}

	public Compra toDto(CompraEntity entity) {
		return Compra.builder()
				.id(entity.getId())
				.valorDebito(entity.getValorDebito())
				.idCartaoUtilizado(Objects.isNull(entity.getCartaoUtilizado()) ? null
						: entity.getCartaoUtilizado().getId())
				.build();
	}

	public List<CompraEntity> toEntity(List<Compra> dtos) {
		return dtos.stream().map(this::toEntity).collect(Collectors.toList());
	}

	public List<Compra> toDto(List<CompraEntity> entities) {
		return entities.stream().map(this::toDto).collect(Collectors.toList());
	}
}
