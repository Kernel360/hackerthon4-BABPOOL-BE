package org.example.hana.review.controller;

import lombok.RequiredArgsConstructor;
import org.example.hana.review.controller.dto.ReviewPostRequestDto;
import org.example.hana.review.controller.dto.ReviewPostResponseDto;
import org.example.hana.review.service.ReviewPostService;
import org.example.hana.review.service.info.ReviewPostInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewPostController {

    private final ReviewPostService reviewPostService;

    @PostMapping("/api/review")
    public ResponseEntity<Void> create(ReviewPostRequestDto dto) {
        reviewPostService.create(dto.getUserId(), dto.getTitle(), dto.getContent(), dto.getCategory(), dto.getRating());

        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/review")
    public ResponseEntity<?> findList() {
        var infos = reviewPostService.findList();
        List<ReviewPostResponseDto> dtos = infos.stream().map(ReviewPostResponseDto::toDto).toList();

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/api/review/{reviewId}")
    public ResponseEntity<?> find(@PathVariable Long reviewId) {
        ReviewPostInfo info = reviewPostService.find(reviewId);
        ReviewPostResponseDto dto = ReviewPostResponseDto.toDto(info);

        return ResponseEntity.ok(dto);
    }

    @PatchMapping("/api/review/{reviewId}")
    public ResponseEntity<Void> update(@PathVariable Long reviewId, ReviewPostRequestDto dto) {
        reviewPostService.update(reviewId, dto.getTitle(), dto.getContent(), dto.getCategory());

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/review/{reviewId}")
    public ResponseEntity<Void> delete(@PathVariable Long reviewId) {
        reviewPostService.delete(reviewId);

        return ResponseEntity.ok().build();
    }
}
