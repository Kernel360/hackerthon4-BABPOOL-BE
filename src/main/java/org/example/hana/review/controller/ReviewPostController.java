package org.example.hana.review.controller;

import lombok.RequiredArgsConstructor;
import org.example.hana.review.controller.dto.ReviewPostRequestDto;
import org.example.hana.review.controller.dto.ReviewPostResponseDto;
import org.example.hana.review.service.ReviewPostService;
import org.example.hana.review.service.info.ReviewPostInfo;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewPostController {

    private final ReviewPostService reviewPostService;

    @PostMapping(value = "/api/review", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ReviewPostResponseDto> create(
            @RequestPart("dto") ReviewPostRequestDto dto,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) {

        ReviewPostInfo postInfo = reviewPostService.create(
                dto.getUserId(),
                dto.getTitle(),
                dto.getContent(),
                dto.getCategory(),
                dto.getRating(),
                file
        );

        ReviewPostResponseDto responseDto = ReviewPostResponseDto.toDto(postInfo);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/api/review")
    public ResponseEntity<List<ReviewPostResponseDto>> findList() {
        List<ReviewPostInfo> infos = reviewPostService.findList();
        List<ReviewPostResponseDto> dtos = infos.stream()
                .map(ReviewPostResponseDto::toDto)
                .toList();

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/api/review/{postId}")
    public ResponseEntity<ReviewPostResponseDto> find(
            @PathVariable("postId") Long postId
    ) {
        ReviewPostInfo info = reviewPostService.find(postId);
        ReviewPostResponseDto dto = ReviewPostResponseDto.toDto(info);

        return ResponseEntity.ok(dto);
    }

    @PatchMapping("/api/review/{postId}")
    public ResponseEntity<ReviewPostResponseDto> update(
            @PathVariable("postId") Long postId,
            @RequestBody ReviewPostRequestDto dto
    ) {
        ReviewPostInfo info = reviewPostService.update(
                postId,
                dto.getUserId(),
                dto.getTitle(),
                dto.getContent(),
                dto.getCategory(),
                dto.getRating()
        );
        ReviewPostResponseDto responseDto = ReviewPostResponseDto.toDto(info);

        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/api/review/{postId}")
    public ResponseEntity<Void> delete(
            @PathVariable("postId") Long postId
    ) {
        reviewPostService.delete(postId);

        return ResponseEntity.ok().build();
    }
}
