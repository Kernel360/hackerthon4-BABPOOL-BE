package org.example.hana.user.controller;


import lombok.RequiredArgsConstructor;
import org.example.hana.user.dto.TokenDto;
import org.example.hana.user.dto.UserRequestDto;
import org.example.hana.user.dto.UserResponseDto;
import org.example.hana.user.repository.UserRepository;
import org.example.hana.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/me")  //user list 확인
    public ResponseEntity<List<UserResponseDto>> findAll(){
        List<UserResponseDto> users = userRepository.findAll().stream()
                .map(UserResponseDto::of)
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signup(
            @RequestBody UserRequestDto userRequestDto
            ) {
        return ResponseEntity.ok(userService.signup(userRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(userService.login(userRequestDto));
    }




}
