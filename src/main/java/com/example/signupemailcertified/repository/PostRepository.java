package com.example.signupemailcertified.repository;

import com.example.signupemailcertified.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

    List<PostEntity> findAllByEmail(String email);
}
