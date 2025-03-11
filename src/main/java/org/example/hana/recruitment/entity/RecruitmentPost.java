package org.example.hana.recruitment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.example.hana.global.BaseEntity;
import org.example.hana.user.entity.User;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "recruitment_posts")
public class RecruitmentPost extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String title;

    @Lob
    private String content;

    private String restaurantLink;

    private String category;
}
