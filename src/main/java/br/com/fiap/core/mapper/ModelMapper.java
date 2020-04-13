package br.com.fiap.core.mapper;

import java.util.List;

public interface ModelMapper<D, E> {

	E toEntity(D dto);

	D toDto(E entity);

	List<E> toEntity(List<D> dtos);

	List<D> toDto(List<E> entities);
}
