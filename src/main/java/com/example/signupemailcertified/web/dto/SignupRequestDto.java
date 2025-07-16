package com.example.signupemailcertified.web.dto;

import com.example.signupemailcertified.valid.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignupRequestDto {

    @NotBlank(message = "이름을 입력하세요.")
    private String userName;

    @NotBlank(message = "이메일을 입력하세요.")
    @Email(message = "유효한 이메일 주소를 입력하세요.")
    private String email;

    @ValidPassword
    private String password;
}
