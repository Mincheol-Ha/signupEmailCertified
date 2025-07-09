package com.example.signupemailcertified.service;

import com.example.signupemailcertified.entity.PostEntity;
import com.example.signupemailcertified.entity.PostLikeEntity;
import com.example.signupemailcertified.mapper.PostMapper;
import com.example.signupemailcertified.repository.PostLikeRepository;
import com.example.signupemailcertified.repository.PostRepository;
import com.example.signupemailcertified.web.dto.PostLikeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostMapper postMapper;
    private final PostRepository postRepository;

    @Transactional
    public PostLikeResponseDto toggleLike(Long postId, String email) {
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글 없음"));

        Optional<PostLikeEntity> existingLike = postLikeRepository.findByPostAndEmail(post, email);

        if (existingLike.isPresent()) {

            postLikeRepository.delete(existingLike.get());
            return PostLikeResponseDto.builder()
                    .postId(postId)
                    .email(email)
                    .liked(false)
                    .message("좋아요 취소!")
                    .build();
        } else {
            PostLikeEntity newLike = PostLikeEntity.builder()
                    .post(post)
                    .email(email)
                    .build();
            postLikeRepository.save(newLike);
            return PostLikeResponseDto.builder()
                    .postId(postId)
                    .email(email)
                    .liked(true)
                    .message("좋아요 등록!")
                    .build();
        }
    }
}
