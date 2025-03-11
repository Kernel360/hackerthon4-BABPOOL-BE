package org.example.hana.user.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.hana.user.entity.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
@NoArgsConstructor
public class UserRequestDto {
    private String nickname;
    private String username;
    private String password;

    public User toUser(PasswordEncoder passwordEncoder ){
        return User.builder()
                .nickname(nickname)
                .username(username)
                .password(passwordEncoder.encode(password))
                .build();
    }

    // 입력된 이메일과 비밀번호를 인증 객체로 변환
    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(username, password);
    }

}
