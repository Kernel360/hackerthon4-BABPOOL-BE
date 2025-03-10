package org.example.hana.review.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.hana.BaseEntity;
import org.example.hana.user.entity.User;

@NoArgsConstructor
@Entity(name = "review_posts")
@Getter
public class ReviewPost extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Setter
    private String title;

    @Lob
    @Setter
    private String content;

    @Setter
    private String category;

    private Integer rating;

    @Builder
    public ReviewPost(User user, String title, String content, String category) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.category = category;
        this.rating = 0;
    }
}
