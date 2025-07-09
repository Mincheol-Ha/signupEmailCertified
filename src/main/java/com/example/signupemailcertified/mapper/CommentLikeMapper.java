package com.example.signupemailcertified.mapper;

import com.example.signupemailcertified.entity.CommentLikeEntity;
import com.example.signupemailcertified.web.dto.CommentLikeResponseDto;
import com.example.signupemailcertified.web.dto.CommentRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import javax.swing.*;

@Mapper(componentModel = "spring")
public interface CommentLikeMapper {
    CommentLikeEntity toEntity(CommentRequestDto commentRequestDto);

    @Mapping(source = "comment.id", target = "commentId")
    CommentLikeResponseDto toDto(CommentLikeEntity commentLikeEntity);
}
