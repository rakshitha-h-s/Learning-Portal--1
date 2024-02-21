package com.effigo.learning.portal.dto.mapper;

import java.util.List;

public interface EntityMapper<D, E> {
	D toDto(E e);

	E toEntity(D d);

	List<D> toDto(List<E> elist);

	List<E> toEntity(List<D> dlist);
}
