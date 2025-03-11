package org.example.hana.review.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.hana.global.BaseEntity;
import org.example.hana.user.entity.User;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "review_comments")
@Getter
public class ReviewComment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private ReviewPost reviewPost;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Setter
    private String content;
}
