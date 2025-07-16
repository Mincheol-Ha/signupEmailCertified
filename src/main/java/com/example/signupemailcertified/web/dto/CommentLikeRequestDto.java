package com.example.signupemailcertified.web.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentLikeRequestDto {
    private Long CommentId;
    private Long postId;
    private String email;
}
