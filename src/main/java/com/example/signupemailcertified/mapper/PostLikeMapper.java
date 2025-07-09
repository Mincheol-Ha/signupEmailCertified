package com.example.signupemailcertified.mapper;

import com.example.signupemailcertified.entity.PostLikeEntity;
import com.example.signupemailcertified.web.dto.PostLikeRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface PostLikeMapper {
    PostLikeEntity toEntity(PostLikeRequestDto postLikeRequestDto);

    @Mapping(source = "post.id", target = "postId")
    PostLikeRequestDto toDto(PostLikeEntity postLikeEntity);
}
