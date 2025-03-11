package org.example.hana.recruitment.model;

import lombok.Data;

@Data
public class RecruitmentPostRequest {
    private String title;
    private String content;
    private String restaurantLink;
    private String category;
    private Integer maxParticipants;
}
