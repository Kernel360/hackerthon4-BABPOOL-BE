package org.example.hana.recruitment.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.hana.BaseEntity;
import org.example.hana.user.entity.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
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

    private Integer maxParticipants;
}
