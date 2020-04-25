package com.solobodo.mapper;

import java.util.List;
import java.util.stream.Collectors;

public interface SuperMapper<E, D> {

    D toDto(E entity);

    E toEntity(D dto);

    default List<D> mapListEntityToDto(List<E> entity) {
        return entity.stream().map(this::toDto).collect(Collectors.toList());
    }

    default List<E> mapListDtoToListEntity(List<D> dto) {
        return dto.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
