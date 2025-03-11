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
    public ResponseEntity<ReviewCommentResponseDto> create(
            @PathVariable("postId") Long postId,
            @RequestBody ReviewCommentRequestDto requestDto
    ) {
        ReviewCommentInfo info = reviewCommentService.create(postId, requestDto.getUserId(), requestDto.getContent());
        ReviewCommentResponseDto responseDto = ReviewCommentResponseDto.toDto(info);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/api/review/{postId}/comment")
    public ResponseEntity<List<ReviewCommentResponseDto>> get(
            @PathVariable("postId") Long postId
    ) {
        List<ReviewCommentInfo> infos = reviewCommentService.findByPostId(postId);
        List<ReviewCommentResponseDto> dtos = ReviewCommentResponseDto.toDtos(infos);

        return ResponseEntity.ok(dtos);
    }

    @PatchMapping("/api/review/{postId}/comment/{commentId}")
    public ResponseEntity<ReviewCommentResponseDto> update(
            @PathVariable("postId") Long postId,
            @PathVariable("commentId") Long commentId,
            @RequestBody ReviewCommentRequestDto requestDto
    ) {
        ReviewCommentInfo info = reviewCommentService.update(postId, commentId, requestDto.getContent());
        ReviewCommentResponseDto responseDto = ReviewCommentResponseDto.toDto(info);

        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/api/review/{postId}/comment/{commentId}")
    public ResponseEntity<Void> delete(
            @PathVariable("postId") Long postId,
            @PathVariable("commentId") Long commentId
    ) {
        reviewCommentService.delete(postId, commentId);

        return ResponseEntity.ok().build();
    }
}
