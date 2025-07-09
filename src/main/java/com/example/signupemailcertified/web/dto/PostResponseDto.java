package com.example.signupemailcertified.web.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder(toBuilder = true)
public class PostResponseDto {
    private Long id;
    private String title;
    private String email;
    private LocalDateTime creatAt;
}
