package com.example.signupemailcertified.repository;

import com.example.signupemailcertified.entity.PostEntity;
import com.example.signupemailcertified.entity.PostLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLikeEntity, Long> {
    Optional<PostLikeEntity> findByPostAndEmail(PostEntity post, String email);
}
