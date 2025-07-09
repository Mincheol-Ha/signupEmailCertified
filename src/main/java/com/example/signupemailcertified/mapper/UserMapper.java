package com.example.signupemailcertified.mapper;

import com.example.signupemailcertified.entity.UserEntity;
import com.example.signupemailcertified.web.dto.SignupRequestDto;
import com.example.signupemailcertified.web.dto.SignupResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    // 회원가입 요청 DTO -> Entity 변환
    UserEntity toEntity(SignupRequestDto dto);

    // 엔티티 -> 회원가입 응답 DTO 변환
    SignupResponseDto toSignupResponseDto(UserEntity user);


}
