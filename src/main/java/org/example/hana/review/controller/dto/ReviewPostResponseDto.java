package org.example.hana.review.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.hana.review.service.info.ReviewPostInfo;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewPostResponseDto {
    private Long id;
    private Long userId;
    private String nickname;
    private String title;
    private String content;
    private String category;
    private Integer rating;
    private String createdAt;
    private String updatedAt;

    public static ReviewPostResponseDto toDto(ReviewPostInfo reviewPostInfo) {
        return ReviewPostResponseDto.builder()
                .id(reviewPostInfo.getId())
                .userId(reviewPostInfo.getUserId())
                .nickname(reviewPostInfo.getNickname())
                .title(reviewPostInfo.getTitle())
                .content(reviewPostInfo.getContent())
                .category(reviewPostInfo.getCategory())
                .rating(reviewPostInfo.getRating())
                .createdAt(reviewPostInfo.getCreatedAt())
                .updatedAt(reviewPostInfo.getUpdatedAt())
                .build();
    }
}
