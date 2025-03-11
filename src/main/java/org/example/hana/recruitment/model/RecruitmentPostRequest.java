package org.example.hana.recruitment.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class RecruitmentPostRequest {
    private String title;
    private String content;
    private String restaurantLink;
    private String category;
    private Integer maxParticipants;
    private LocalDate meetingDate;
    private LocalTime meetingTime;

}
