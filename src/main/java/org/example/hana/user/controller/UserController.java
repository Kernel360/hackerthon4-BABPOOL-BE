package org.example.hana.user.controller;


import lombok.RequiredArgsConstructor;
import org.example.hana.user.dto.TokenDto;
import org.example.hana.user.dto.UserRequestDto;
import org.example.hana.user.dto.UserResponseDto;
import org.example.hana.user.repository.UserRepository;
import org.example.hana.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;
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

    @PatchMapping("/logout")
    public ResponseEntity<String> logout(@AuthenticationPrincipal UserDetails userDetails,
                                         Principal principal) {
        Long userId = Long.parseLong(principal.getName());
        userService.logout(userId);
        return ResponseEntity.ok("로그아웃 성공");
    }

    @PatchMapping("/me")
    public ResponseEntity<UserResponseDto> updateUser(
            @RequestBody UserRequestDto userRequestDto,
            Principal principal // 현재 로그인한 사용자 정보 가져오기
    ) {
        Long userId = Long.parseLong(principal.getName()); // JWT에서 사용자 ID 가져오기
        return ResponseEntity.ok(userService.updateUser(userId, userRequestDto));
    }

    @PatchMapping("/password")
    public ResponseEntity<String> updatePassword(
            @AuthenticationPrincipal UserDetails userDetails,  // 인증된 유저 정보 가져오기
            @RequestBody Map<String, String> request,
            Principal principal)
        {

        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }
        Long userId = Long.parseLong(principal.getName()); // JWT에서 사용자 ID 가져오기
        String newPassword = request.get("password");
        userService.updatePassword(userId, newPassword);

        return ResponseEntity.ok("비밀번호 변경 완료");
    }




}
