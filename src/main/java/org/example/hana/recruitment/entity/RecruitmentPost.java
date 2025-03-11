package org.example.hana.recruitment.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.hana.BaseEntity;
import org.example.hana.user.entity.User;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String title;

    @Lob
    private String content;

    private String location;

    private String restaurantLink;

    private String category;

    private Integer maxParticipants;

    private LocalDate meetingDate;

    private LocalTime meetingTime;

    @OneToMany(mappedBy = "recruitmentPost", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<RecruitmentComment> comments;

    @OneToMany(mappedBy = "recruitmentPost", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Application> applications;
}
