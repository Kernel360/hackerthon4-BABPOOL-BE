package org.example.hana.review.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.hana.BaseEntity;
import org.example.hana.user.entity.User;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "review_posts")
@Getter
public class ReviewPost extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String title;

    @Lob
    private String content;

    private String category;

    private Integer rating;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
