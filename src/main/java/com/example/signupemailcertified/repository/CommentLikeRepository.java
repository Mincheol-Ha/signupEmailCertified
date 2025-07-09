package com.example.signupemailcertified.repository;

import com.example.signupemailcertified.entity.CommentEntity;
import com.example.signupemailcertified.entity.CommentLikeEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CommentLikeRepository extends CrudRepository<CommentLikeEntity, Long> {
    Optional<CommentLikeEntity> findByCommentAndEmail(CommentEntity comment, String email);
}
