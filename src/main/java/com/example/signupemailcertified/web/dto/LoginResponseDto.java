package com.example.signupemailcertified.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class LoginResponseDto {

    private String email;
    private String userName;
    private String jwtToken;
    private String message;

}
