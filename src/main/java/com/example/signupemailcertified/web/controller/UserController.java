package com.example.signupemailcertified.web.controller;

import com.example.signupemailcertified.service.UserService;
import com.example.signupemailcertified.web.dto.LoginRequestDto;
import com.example.signupemailcertified.web.dto.LoginResponseDto;
import com.example.signupemailcertified.web.dto.SignupRequestDto;
import com.example.signupemailcertified.web.dto.SignupResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    public final UserService userService;

    // 1. 회원가입 (이메일 인증 메일 발송)
    @PostMapping("/signup")
    public SignupResponseDto signup(@RequestBody @Validated SignupRequestDto signupRequestDto) {
        return userService.requestEmailVerification(signupRequestDto);
    }

    // 2. 이메일 인증 링크 클릭 (최종 회원등록)
    @GetMapping("/verify-email")
    public SignupResponseDto verifyEmail(@RequestParam String token) {
        return userService.verifyEmail(token);
    }

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto) {
        return  userService.login(loginRequestDto);
    }

}

