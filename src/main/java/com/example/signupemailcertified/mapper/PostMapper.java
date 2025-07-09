package com.example.signupemailcertified.mapper;

import com.example.signupemailcertified.entity.PostEntity;
import com.example.signupemailcertified.web.dto.PostRequestDto;
import com.example.signupemailcertified.web.dto.PostResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostEntity toEntity(PostRequestDto postRequestDto);
    PostResponseDto toDto(PostEntity postEntity);
}
