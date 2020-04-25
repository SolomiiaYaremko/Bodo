package com.solobodo.mapper;

import com.solobodo.dto.CommentDto;
import com.solobodo.dto.OfferDto;
import com.solobodo.entity.Comment;
import com.solobodo.entity.Offer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CommentMapper extends SuperMapper<Comment, CommentDto> {

    Comment toEntity(OfferDto dto);

    CommentDto toDto(Offer entity);

    default List<CommentDto> mapListEntityToDto(List<Comment> fields) {
        return fields.stream().map(this::toDto).collect(Collectors.toList());
    }

    default List<Comment> mapListDtoToListEntity(List<CommentDto> fieldDtos) {
        return fieldDtos.stream().map(this::toEntity).collect(Collectors.toList());
    }
}

