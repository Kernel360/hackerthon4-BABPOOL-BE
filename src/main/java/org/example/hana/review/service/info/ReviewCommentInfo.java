package org.example.hana.review.service.info;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.hana.review.entity.ReviewComment;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewCommentInfo {

    private Long id;
    private Long postId;
    private Long userId;
    private String nickname;
    private String content;

    public static ReviewCommentInfo toInfo(ReviewComment reviewComment) {
        return ReviewCommentInfo.builder()
                .id(reviewComment.getId())
                .postId(reviewComment.getReviewPost().getId())
                .userId(reviewComment.getUser().getId())
                .nickname(reviewComment.getUser().getNickname())
                .content(reviewComment.getContent())
                .build();
    }
}
