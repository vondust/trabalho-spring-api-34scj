package br.com.fiap.core.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import br.com.fiap.adapter.datastore.entity.CartaoEntity;
import br.com.fiap.core.model.Cartao;

public class CartaoMapper implements ModelMapper<Cartao, CartaoEntity> {

	public Cartao toDto(CartaoEntity entity) {
		if (Objects.isNull(entity))
			return null;

		return Cartao.builder()
				.id(entity.getId())
				.numero(entity.getNumero())
				.vencimento(entity.getVencimento())
				.valorLimite(entity.getValorLimite())
				.valorUtilizado(entity.getValorUtilizado())
				.build();
	}

	public CartaoEntity toEntity(Cartao dto) {
		if (Objects.isNull(dto))
			return null;

		return CartaoEntity.builder()
				.id(dto.getId())
				.numero(dto.getNumero())
				.vencimento(dto.getVencimento())
				.valorLimite(dto.getValorLimite())
				.valorUtilizado(dto.getValorUtilizado())
				.build();
	}

	public List<CartaoEntity> toEntity(List<Cartao> dtos) {
		return dtos.stream().map(this::toEntity).collect(Collectors.toList());
	}

	public List<Cartao> toDto(List<CartaoEntity> entities) {
		return entities.stream().map(this::toDto).collect(Collectors.toList());
	}
}
