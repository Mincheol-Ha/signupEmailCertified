package com.example.signupemailcertified.web.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentRequestDto {
    private String content;
    private Long postId;

}
