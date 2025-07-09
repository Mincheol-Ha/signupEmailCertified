package com.example.signupemailcertified.mapper;

import com.example.signupemailcertified.entity.CommentEntity;
import com.example.signupemailcertified.web.dto.CommentRequestDto;
import com.example.signupemailcertified.web.dto.CommentResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentEntity toEntity(CommentRequestDto requestDto);
    CommentResponseDto toDto(CommentEntity commentEntity);
}
