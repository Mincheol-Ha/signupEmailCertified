package com.example.signupemailcertified.web.controller;

import com.example.signupemailcertified.jwt.JwtTokenProvider;
import com.example.signupemailcertified.service.CommentLikeService;
import com.example.signupemailcertified.service.PostLikeService;
import com.example.signupemailcertified.web.dto.CommentLikeResponseDto;
import com.example.signupemailcertified.web.dto.PostLikeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LikeController {

    private final PostLikeService postLikeService;
    private final CommentLikeService commentLikeService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/posts/{postId}/like")
    public PostLikeResponseDto togglePostLike(
            @PathVariable Long postId,
            @RequestHeader("Authorization") String token) {
        String email = extractEmailFromToken(token);
        return postLikeService.toggleLike(postId, email);

    }

    @PostMapping("/comments/{commentId}/like")
    public CommentLikeResponseDto toggleCommentLike(
            @PathVariable Long commentId,
            @RequestHeader("Authorization") String token) {
        String email = extractEmailFromToken(token);
        return commentLikeService.toggleLike(commentId, email);

    }

    private String extractEmailFromToken(String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        return jwtTokenProvider.getEmailFromToken(token);
    }
}

