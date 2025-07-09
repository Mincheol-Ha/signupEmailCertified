package com.example.signupemailcertified.mapper;

import com.example.signupemailcertified.entity.UserEntity;
import com.example.signupemailcertified.web.dto.LoginResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LoginMapper {

    // Entity → ResponseDto 매핑
    // (jwtToken, message 등은 직접 세팅 필요)
    @Mapping(target = "jwtToken", ignore = true)
    @Mapping(target = "message", ignore = true)
    LoginResponseDto toDto(UserEntity entity);

}
