package org.example.hana.user.dto;

import lombok.Builder;
import lombok.Getter;
import org.example.hana.user.entity.User;

@Getter
@Builder
public class UserResponseDto {
    private Long id;
    private String username;
    private String nickname;


    //user entity -> dto로 변환
    public static UserResponseDto of (User user){
        return UserResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .nickname(user.getNickname())
                .build();
    }
}
