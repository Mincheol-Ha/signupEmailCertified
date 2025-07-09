package com.example.signupemailcertified.web.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequestDto {

    String email;
    String password;

}
