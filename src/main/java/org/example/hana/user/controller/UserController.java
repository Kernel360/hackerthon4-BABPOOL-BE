package org.example.hana.user.controller;


import lombok.RequiredArgsConstructor;
import org.example.hana.user.dto.TokenDto;
import org.example.hana.user.dto.UserRequestDto;
import org.example.hana.user.dto.UserResponseDto;
import org.example.hana.user.entity.User;
import org.example.hana.user.repository.UserRepository;
import org.example.hana.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/users/me")  // 특정 사용자 정보 조회
    public ResponseEntity<UserResponseDto> findById(
            @RequestParam Long id
    ) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다. id=" + id));

        return ResponseEntity.ok(UserResponseDto.of(user));
    }

    @PostMapping("/users/signup")
    public ResponseEntity<UserResponseDto> signup(
            @RequestBody UserRequestDto userRequestDto
    ) {
        return ResponseEntity.ok(userService.signup(userRequestDto));
    }

    @PostMapping("/users/login")
    public ResponseEntity<TokenDto> login(@RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(userService.login(userRequestDto));
    }

    @PatchMapping("/users/logout")
    public ResponseEntity<String> logout(@AuthenticationPrincipal UserDetails userDetails,
                                         Principal principal) {
        Long userId = Long.parseLong(principal.getName());
        userService.logout(userId);
        return ResponseEntity.ok("로그아웃 성공");
    }

    @PatchMapping("/users/me")
    public ResponseEntity<UserResponseDto> updateUser(
            @RequestBody UserRequestDto userRequestDto,
            Principal principal // 현재 로그인한 사용자 정보 가져오기
    ) {
        Long userId = Long.parseLong(principal.getName()); // JWT에서 사용자 ID 가져오기
        return ResponseEntity.ok(userService.updateUser(userId, userRequestDto));
    }

    @PatchMapping("/users/password")
    public ResponseEntity<Map<String, String>> updatePassword(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody Map<String, String> request,
            Principal principal) {

        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("message", "로그인이 필요합니다."));
        }

        Long userId = Long.parseLong(principal.getName());
        String currentPassword = request.get("currentPassword");
        String newPassword = request.get("password");

        try {
            userService.updatePassword(userId, currentPassword, newPassword);
            return ResponseEntity.ok(Collections.singletonMap("message", "비밀번호 변경 완료"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("message", e.getMessage()));
        }
    }




}
