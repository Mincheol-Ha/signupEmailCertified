package com.example.signupemailcertified.web.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentLikRequestDto {
    private Long CommentId;
    private Long postId;
    private String email;
}
