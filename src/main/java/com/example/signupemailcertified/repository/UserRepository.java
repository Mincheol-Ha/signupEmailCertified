package com.example.signupemailcertified.repository;

import com.example.signupemailcertified.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);
    Optional<UserEntity> findByEmailAndPassword(String email, String password);
}
