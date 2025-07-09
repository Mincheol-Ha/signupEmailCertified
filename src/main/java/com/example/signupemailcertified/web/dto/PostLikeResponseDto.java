package com.example.signupemailcertified.web.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostLikeResponseDto {
    private Long postId;
    private String email;
    private boolean liked;
    private String message;
}
