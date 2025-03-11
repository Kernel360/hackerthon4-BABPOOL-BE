package org.example.hana.user.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "users")
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    @Column(unique = true, nullable = false)
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Setter
    @Column(length = 500)
    private String refreshToken;


    public void updateProfile(String username, String nickname) {
        this.username = username;
        this.nickname = nickname;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

    @Builder
    public User(String username, String password, String nickname,Authority authority, String refreshToken) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.authority = authority;
        this.refreshToken = refreshToken;
    }

}
