package com.example.signupemailcertified.repository;

import com.example.signupemailcertified.entity.PendingUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PendingUserRepository extends JpaRepository<PendingUserEntity,Long> {
    boolean existsByEmail(String email);

    Optional<PendingUserEntity> findByVerificationToken(String token);
}
