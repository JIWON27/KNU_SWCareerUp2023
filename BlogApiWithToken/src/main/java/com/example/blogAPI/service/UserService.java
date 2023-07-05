package com.example.blogAPI.service;

import com.example.blogAPI.domain.user.User;
import com.example.blogAPI.repository.UserRepository;
import com.example.blogAPI.web.dto.AddUserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
  // 컨트롤러에서 처리할 비지니스 로직
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public Long save(AddUserRequestDto addUserRequestDto){
    return userRepository.save(
        User.builder()
            .email(addUserRequestDto.getEmail())
            .password(bCryptPasswordEncoder.encode(addUserRequestDto.getPassword())) //비번 암호화해야하니까 인코더 넣어줌
            .build()
    ).getId();
  }

  // 단건 조회
  public User findById(Long userId){
    return userRepository.findById(userId)
        .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
  }

}
