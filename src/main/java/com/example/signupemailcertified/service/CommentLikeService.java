package com.example.signupemailcertified.service;

import com.example.signupemailcertified.entity.CommentEntity;
import com.example.signupemailcertified.entity.CommentLikeEntity;
import com.example.signupemailcertified.entity.PostEntity;
import com.example.signupemailcertified.entity.PostLikeEntity;
import com.example.signupemailcertified.mapper.CommentMapper;
import com.example.signupemailcertified.mapper.PostMapper;
import com.example.signupemailcertified.repository.CommentLikeRepository;
import com.example.signupemailcertified.repository.CommentRepository;
import com.example.signupemailcertified.repository.PostLikeRepository;
import com.example.signupemailcertified.repository.PostRepository;
import com.example.signupemailcertified.web.dto.CommentLikeResponseDto;
import com.example.signupemailcertified.web.dto.PostLikeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentLikeService {

    private final CommentLikeRepository commentLikeRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public CommentLikeResponseDto toggleLike(Long commentId, String email) {
        CommentEntity comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 없음"));

        Optional<CommentLikeEntity> existingLike = commentLikeRepository.findByCommentAndEmail(comment, email);

        if (existingLike.isPresent()) {

            commentLikeRepository.delete(existingLike.get());
            return CommentLikeResponseDto.builder()
                    .commentId(commentId)
                    .email(email)
                    .liked(false)
                    .message("좋아요 취소!")
                    .build();
        } else {

            CommentLikeEntity newLike = CommentLikeEntity.builder()
                    .comment(comment)
                    .email(email)
                    .build();
            commentLikeRepository.save(newLike);
            return CommentLikeResponseDto.builder()
                    .commentId(comment.getId())
                    .email(email)
                    .liked(true)
                    .message("좋아요 등록!")
                    .build();
        }
    }
}
