package com.example.signupemailcertified.mapper;

import com.example.signupemailcertified.entity.PendingUserEntity;
import com.example.signupemailcertified.web.dto.SignupRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PendingUserMapper {
    PendingUserEntity toEntity(SignupRequestDto dto);
}
