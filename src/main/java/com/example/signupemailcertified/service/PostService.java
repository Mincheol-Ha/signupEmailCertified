package com.example.signupemailcertified.service;

import com.example.signupemailcertified.entity.PostEntity;
import com.example.signupemailcertified.mapper.PostMapper;
import com.example.signupemailcertified.repository.PostRepository;
import com.example.signupemailcertified.repository.UserRepository;
import com.example.signupemailcertified.web.dto.PostRequestDto;
import com.example.signupemailcertified.web.dto.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final UserRepository userRepository;

    // 게시글 작성
    public PostResponseDto createPost(String email, PostRequestDto postRequestDto) {
        System.out.println("createPost()의 email 파라미터: " + email); // 추가

        if (!userRepository.existsByEmail(email)) {
            System.out.println("DB에 없는 email: " + email); // 추가
            throw new IllegalArgumentException("회원이 아닙니다.");
        }

        // 작성자 이메일 유효성 체크
        if (!userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("회원이 아닙니다.");
        }

        PostEntity post = postMapper.toEntity(postRequestDto);
        post.setCreatAt(LocalDateTime.now());
        post.setEmail(email);
        PostEntity saved = postRepository.save(post);
        return postMapper.toDto(saved);
    }

    // 게시글 전제 조회
    public List<PostResponseDto> getAllPosts() {
        return postRepository.findAll().stream()
                .map(postMapper::toDto)
                .collect(Collectors.toList());
    }

    // email 게시글 전체 조회
    public List<PostResponseDto> getPostsByEmail(String email) {
        return postRepository.findAllByEmail(email).stream()
                .map(postMapper::toDto)
                .collect(Collectors.toList());
    }

    // 게시글 수정(작성자 본인만 가능)
    public PostResponseDto updatePost(Long id, String email, PostRequestDto postRequestDto) {
        PostEntity post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글 없음"));
        if (!post.getEmail().equals(email)) {
            throw new IllegalArgumentException("작성자만 수정 가능함.");
        }
        post.setTitle(postRequestDto.getTitle());
        post.setContent(postRequestDto.getContent());
        PostEntity updated = postRepository.save(post);
        return postMapper.toDto(updated);
    }

    public void deletePost(Long id, String email) {
        PostEntity post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 없습니다."));

        if (!post.getEmail().equals(email)) {
            throw new IllegalArgumentException("작성자만 삭제할 수 있습니다.");
        }

        postRepository.delete(post);

    }
}
