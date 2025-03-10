package org.example.hana.review.controller;

import lombok.RequiredArgsConstructor;
import org.example.hana.review.controller.dto.ReviewCommentRequestDto;
import org.example.hana.review.controller.dto.ReviewCommentResponseDto;
import org.example.hana.review.service.ReviewCommentService;
import org.example.hana.review.service.info.ReviewCommentInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewCommentController {

    private final ReviewCommentService reviewCommentService;

    @PostMapping("/api/review/{postId}/comment")
    public ResponseEntity<?> create(
            @PathVariable("postId") Long postId,
            @RequestBody ReviewCommentRequestDto requestDto
    ) {
        ReviewCommentInfo info = reviewCommentService.create(postId, requestDto.getUserId(), requestDto.getContent());

        return ResponseEntity.ok(ReviewCommentResponseDto.toDto(info));
    }

    @GetMapping("/api/review/{postId}/comment")
    public ResponseEntity<?> get(
            @PathVariable("postId") Long postId
    ) {
        List<ReviewCommentInfo> infos = reviewCommentService.findByPostId(postId);
        List<ReviewCommentResponseDto> dtos = ReviewCommentResponseDto.toDtos(infos);

        return ResponseEntity.ok(dtos);
    }

    @PatchMapping("/api/review/{postId}/comment/{commentId}")
    public ResponseEntity<?> update(
            @PathVariable("postId") Long postId,
            @PathVariable("commentId") Long commentId,
            @RequestBody ReviewCommentRequestDto requestDto
    ) {
        reviewCommentService.update(postId, commentId, requestDto.getContent());

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/review/{postId}/comment/{commentId}")
    public ResponseEntity<?> delete(
            @PathVariable("postId") Long postId,
            @PathVariable("commentId") Long commentId
    ) {
        reviewCommentService.delete(postId, commentId);

        return ResponseEntity.ok().build();
    }
}
