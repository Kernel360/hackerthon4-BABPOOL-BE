package org.example.hana.user.dto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TokenDto {
    private Long userId;
    private String grantType;      // ex) "Bearer"
    private String accessToken;    // JWT 액세스 토큰
    private String refreshToken;   // JWT 리프레시 토큰
    private Long accessTokenExpiresIn;

    @Builder
    public TokenDto(Long userId, String grantType, String accessToken, String refreshToken, Long accessTokenExpiresIn) {
        this.userId = userId;
        this.grantType = grantType;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.accessTokenExpiresIn = accessTokenExpiresIn;
    }
}