package org.example.hana.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    private String username;

    private String password;



    public void updateProfile(String username, String nickname) {
        this.username = username;
        this.nickname = nickname;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

    @Builder
    public User (String nickname, String username, String password){
        this.nickname = nickname;
        this.username = username;
        this.password = password;
    }
}
