package com.solobodo.mapper;

import com.solobodo.dto.OfferDto;
import com.solobodo.entity.Offer;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OfferMapper extends SuperMapper<Offer, OfferDto> {

    Offer toEntity(OfferDto dto);

    OfferDto toDto(Offer entity);

    default List<OfferDto> mapListEntityToDto(List<Offer> fields) {
        return fields.stream().map(this::toDto).collect(Collectors.toList());
    }

    default List<Offer> mapListDtoToListEntity(List<OfferDto> fieldDtos) {
        return fieldDtos.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
