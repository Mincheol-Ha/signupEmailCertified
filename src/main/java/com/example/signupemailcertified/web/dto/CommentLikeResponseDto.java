package com.example.signupemailcertified.web.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentLikeResponseDto {
    private Long commentId;
    private Long postId;
    private String email;
    private boolean liked;
    private String message;
}
