package org.example.hana.review.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.hana.review.entity.ReviewComment;
import org.example.hana.review.service.info.ReviewCommentInfo;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewCommentResponseDto {

    private Long id;
    private Long postId;
    private Long userId;
    private String nickname;
    private String content;

    public static ReviewCommentResponseDto toDto(ReviewCommentInfo info) {
        return ReviewCommentResponseDto.builder()
                .id(info.getId())
                .postId(info.getPostId())
                .userId(info.getUserId())
                .nickname(info.getNickname())
                .content(info.getContent())
                .build();
    }

    public static List<ReviewCommentResponseDto> toDtos(List<ReviewCommentInfo> reviewCommentInfos) {
        return reviewCommentInfos.stream()
                .map(info -> ReviewCommentResponseDto.builder()
                        .id(info.getId())
                        .postId(info.getPostId())
                        .userId(info.getUserId())
                        .nickname(info.getNickname())
                        .content(info.getContent())
                        .build())
                .toList();
    }
}
