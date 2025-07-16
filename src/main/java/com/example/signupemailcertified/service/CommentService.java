package com.example.signupemailcertified.service;

import com.example.signupemailcertified.entity.CommentEntity;
import com.example.signupemailcertified.entity.PostEntity;
import com.example.signupemailcertified.mapper.CommentMapper;
import com.example.signupemailcertified.repository.CommentRepository;
import com.example.signupemailcertified.repository.PostRepository;
import com.example.signupemailcertified.web.dto.CommentRequestDto;
import com.example.signupemailcertified.web.dto.CommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final PostRepository postRepository;

    // 댓글 작성
    @Transactional
    public CommentResponseDto create(String email, CommentRequestDto requestDto) {
        PostEntity post = postRepository.findById(requestDto.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("게시글 없음"));
        CommentEntity comment = CommentEntity.builder()
                .content(requestDto.getContent())
                .email(email)
                .post(post)
                .createdAt(LocalDateTime.now())
                .build();
        CommentEntity saved = commentRepository.save(comment);
        return commentMapper.toDto(saved);
    }

    @Transactional
    public List<CommentResponseDto> getCommentsByPostId(Long postId) {
        List<CommentEntity> comments = commentRepository.findByPostId(postId);
        return comments.stream()
                .map(commentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentResponseDto updateComment(Long commentId, String email, CommentRequestDto requestDto) {
        CommentEntity comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글이 없습니다."));

        // 작성자만 수정 가능
        if (!comment.getEmail().equals(email)) {
            throw new IllegalArgumentException("본인 댓글만 수정 가능합니다.");
        }
        comment.setContent(requestDto.getContent());
        CommentEntity updated = commentRepository.save(comment);
        return commentMapper.toDto(updated);
    }

    @Transactional
    public void deleteComment(Long commentId, String email) {
        CommentEntity comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글이 없습니다."));

        if(!comment.getEmail().equals(email))
            throw new IllegalArgumentException("본인 댓글 만 삭제할 수 있습니다.");

        commentRepository.delete(comment);
    }

}
