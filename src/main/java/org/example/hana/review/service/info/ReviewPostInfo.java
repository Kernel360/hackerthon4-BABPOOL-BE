package org.example.hana.review.service.info;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.hana.review.entity.ReviewPost;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewPostInfo {
    private Long id;
    private Long userId;
    private String nickname;
    private String title;
    private String content;
    private String category;
    private Integer rating;

    public static ReviewPostInfo toInfo(ReviewPost reviewPost) {
        return ReviewPostInfo.builder()
                .id(reviewPost.getId())
                .userId(reviewPost.getUser().getId())
                .nickname(reviewPost.getUser().getNickname())
                .title(reviewPost.getTitle())
                .content(reviewPost.getContent())
                .category(reviewPost.getCategory())
                .rating(reviewPost.getRating())
                .build();
    }
}
