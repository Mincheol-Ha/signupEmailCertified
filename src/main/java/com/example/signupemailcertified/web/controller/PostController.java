package com.example.signupemailcertified.web.controller;

import com.example.signupemailcertified.jwt.JwtTokenProvider;
import com.example.signupemailcertified.service.PostService;
import com.example.signupemailcertified.web.dto.PostRequestDto;
import com.example.signupemailcertified.web.dto.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping
    public PostResponseDto write(@RequestHeader("Authorization") String token,
                                 @RequestBody PostRequestDto requestDto) {
        String email = extractEmailFromToken(token);
        return postService.createPost(email, requestDto);
    }


    @GetMapping
    public List<PostResponseDto> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/findByEmail")
    public List<PostResponseDto> getPostsByEmail(@RequestParam String email) {
        return postService.getPostsByEmail(email);
    }

    @PutMapping("/{id}")
    public PostResponseDto updatePost(
            @PathVariable Long id,
            @RequestHeader("Authorization") String token,
            @RequestBody PostRequestDto postRequestDto) {

        String email = extractEmailFromToken(token);
        return postService.updatePost(id, email, postRequestDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePost(
            @PathVariable Long id,
            @RequestHeader("Authorization") String token) {
        String email = extractEmailFromToken(token);
        postService.deletePost(id, email);
        return ResponseEntity.ok("게시글 삭제됨");
    }

    private String extractEmailFromToken(String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        return jwtTokenProvider.getEmailFromToken(token);
    }
}
