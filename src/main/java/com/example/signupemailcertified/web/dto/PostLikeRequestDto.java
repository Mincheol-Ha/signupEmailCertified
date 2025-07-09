package com.example.signupemailcertified.web.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostLikeRequestDto {
    private Long postId;
    private String email;
}
