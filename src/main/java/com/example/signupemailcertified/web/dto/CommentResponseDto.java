package com.example.signupemailcertified.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentResponseDto {
    private Long id;
    private String postId;
    private String content;
    private String email;
    private LocalDateTime createdAt;

}
