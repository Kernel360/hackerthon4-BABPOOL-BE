package org.example.hana.recruitment.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.hana.BaseEntity;
import org.example.hana.user.entity.User;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "recruitment_comments")
public class RecruitmentComment extends BaseEntity {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private RecruitmentPost recruitmentPost;

    @Getter
    @ManyToOne
    private User user;

    @Setter
    @Getter
    private String content;
}
