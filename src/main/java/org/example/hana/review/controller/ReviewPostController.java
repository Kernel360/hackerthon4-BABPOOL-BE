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
    public ResponseEntity<Void> create(
            @RequestBody ReviewPostRequestDto dto
    ) {

        System.out.println(dto.toString());
        reviewPostService.create(
                dto.getUserId(),
                dto.getTitle(),
                dto.getContent(),
                dto.getCategory()
        );

        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/review")
    public ResponseEntity<?> findList() {
        List<ReviewPostInfo> infos = reviewPostService.findList();
        List<ReviewPostResponseDto> dtos = infos.stream()
                .map(ReviewPostResponseDto::toDto)
                .toList();

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/api/review/{postId}")
    public ResponseEntity<?> find(
            @PathVariable("postId") Long postId
    ) {
        ReviewPostInfo info = reviewPostService.find(postId);
        ReviewPostResponseDto dto = ReviewPostResponseDto.toDto(info);

        return ResponseEntity.ok(dto);
    }

    @PatchMapping("/api/review/{postId}")
    public ResponseEntity<Void> update(
            @PathVariable("postId") Long postId,
            @RequestBody ReviewPostRequestDto dto
    ) {
        reviewPostService.update(
                postId,
                dto.getTitle(),
                dto.getContent(),
                dto.getCategory()
        );

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/review/{postId}")
    public ResponseEntity<Void> delete(
            @PathVariable("postId") Long postId
    ) {
        reviewPostService.delete(postId);

        return ResponseEntity.ok().build();
    }
}
