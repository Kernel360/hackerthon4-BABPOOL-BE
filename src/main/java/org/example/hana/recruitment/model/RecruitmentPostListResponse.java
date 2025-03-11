package org.example.hana.recruitment.model;

import lombok.Data;
import org.example.hana.recruitment.entity.RecruitmentPost;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class RecruitmentPostListResponse {
    private Long postId;
    private String title;
    private String location;
    private String restaurantLink;
    private LocalDate meetingDate;
    private LocalTime meetingTime;
    private Integer maxParticipants;
    private Integer currentParticipants;


    public RecruitmentPostListResponse(RecruitmentPost post) {
        this.postId = post.getId();
        this.title = post.getTitle();
        this.maxParticipants = post.getMaxParticipants();
        this.location = post.getLocation();
        this.restaurantLink = post.getRestaurantLink();
        this.meetingDate = post.getMeetingDate();
        this.meetingTime = post.getMeetingTime();
        this.currentParticipants = post.getApplications().size();
    }


}
