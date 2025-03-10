package org.example.hana.user.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.hana.user.dto.UserRequestDto;
import org.example.hana.user.dto.UserResponseDto;
import org.example.hana.user.entity.User;
import org.example.hana.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponseDto signup(UserRequestDto userRequestDto){
        if(userRepository.existsByUsername(userRequestDto.getUsername())){
            throw new RuntimeException("이미 가입되어있는 유저입니다.");
        }
        User user = userRequestDto.toUser(passwordEncoder);
        return UserResponseDto.of(userRepository.save(user));
    }






}
