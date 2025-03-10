package org.example.hana.review.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewPostRequestDto {

    private Long userId;
    private String title;
    private String content;
    private String category;
    private Integer rating;
}
