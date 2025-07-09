package com.example.signupemailcertified.web.controller;

import com.example.signupemailcertified.jwt.JwtTokenProvider;
import com.example.signupemailcertified.service.CommentService;
import com.example.signupemailcertified.web.dto.CommentRequestDto;
import com.example.signupemailcertified.web.dto.CommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/comments")
    public CommentResponseDto creatComment(
            @RequestHeader("Authorization") String token,
            @RequestBody CommentRequestDto commentRequestDto) {
        String email = extractEmailFromToken(token);
        return commentService.create(email, commentRequestDto);
    }

    @GetMapping("/comments/{postId}")
    public List<CommentResponseDto> getCommentsByPostId(@PathVariable Long postId) {
        return commentService.getCommentsByPostId(postId);
    }

    private String extractEmailFromToken(String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        return jwtTokenProvider.getEmailFromToken(token);
    }

    @PutMapping("/comments/{id}")
    public CommentResponseDto updateComment(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id,
            @RequestBody CommentRequestDto commentRequestDto) {
        String email = extractEmailFromToken(token);
        return commentService.updateComment(id, email, commentRequestDto);
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<String> deleteComment(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id) {
        String email = extractEmailFromToken(token);
        commentService.deleteComment(id, email);
        return ResponseEntity.ok("댓글이 삭제되었습니다.");

    }
}
