package com.example.signupemailcertified.service;

import com.example.signupemailcertified.entity.PendingUserEntity;
import com.example.signupemailcertified.entity.UserEntity;
import com.example.signupemailcertified.jwt.JwtTokenProvider;
import com.example.signupemailcertified.mapper.LoginMapper;
import com.example.signupemailcertified.mapper.PendingUserMapper;
import com.example.signupemailcertified.mapper.UserMapper;
import com.example.signupemailcertified.repository.PendingUserRepository;
import com.example.signupemailcertified.repository.UserRepository;
import com.example.signupemailcertified.web.dto.LoginRequestDto;
import com.example.signupemailcertified.web.dto.LoginResponseDto;
import com.example.signupemailcertified.web.dto.SignupRequestDto;
import com.example.signupemailcertified.web.dto.SignupResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PendingUserRepository pendingUserRepository;
    private final MailService mailService;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserMapper userMapper;
    private final PendingUserMapper pendingUserMapper;
    private final LoginMapper loginMapper;

    // 1. 회원가입(임시 저장 + 메일 발송)
    public SignupResponseDto requestEmailVerification(SignupRequestDto dto) {
        if (pendingUserRepository.existsByEmail(dto.getEmail()) ||
                userRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }

        String token = java.util.UUID.randomUUID().toString();
        PendingUserEntity pendingUser = pendingUserMapper.toEntity(dto);
        pendingUser.setVerificationToken(token);
        pendingUser.setTokenCreatedAt(java.time.LocalDateTime.now());
        pendingUserRepository.save(pendingUser);

        mailService.sendVerificationEmail(dto.getEmail(), token);

        return SignupResponseDto.builder()
                .userName(dto.getUserName())
                .email(dto.getEmail())
                .message("이메일 인증 후 가입이 완료됩니다.")
                .build();
    }

    // 2. 이메일 인증 처리 (실제 회원 등록)
    public SignupResponseDto verifyEmail(String token) {
        PendingUserEntity pendingUser = pendingUserRepository.findByVerificationToken(token)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 인증입니다."));

        // DTO로 변환(수동 빌더지만 타입 안전!)
        SignupRequestDto signupRequestDto = SignupRequestDto.builder()
                .userName(pendingUser.getUserName())
                .email(pendingUser.getEmail())
                .password(pendingUser.getPassword())
                .build();

        UserEntity user = userMapper.toEntity(signupRequestDto);
        userRepository.save(user);

        pendingUserRepository.delete(pendingUser);

        return SignupResponseDto.builder()
                .userName(user.getUserName())
                .email(user.getEmail())
                .message("회원가입 인증에 성공했습니다.")
                .build();
    }

    // 회원가입 (즉시 등록)
    public SignupResponseDto signup(SignupRequestDto signupRequestDto) {
        if (userRepository.existsByEmail(signupRequestDto.getEmail())) {
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        }
        UserEntity userEntity = userMapper.toEntity(signupRequestDto);
        UserEntity savedUser = userRepository.save(userEntity);

        return userMapper.toSignupResponseDto(savedUser)
                .toBuilder()
                .message("회원가입이 완료되었습니다.")
                .build();
    }

    // 로그인
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        UserEntity user = userRepository.findByEmailAndPassword(
                        loginRequestDto.getEmail(),
                        loginRequestDto.getPassword())
                .orElseThrow(() -> new IllegalArgumentException("아이디 / 비밀번호 다름"));

        String token = jwtTokenProvider.createToken(user.getEmail());

        // MapStruct 매핑 + builder로 추가 정보 합치기
        return loginMapper.toDto(user)
                .toBuilder()
                .jwtToken(token)
                .message("로그인 되었습니다.")
                .build();
    }

}

