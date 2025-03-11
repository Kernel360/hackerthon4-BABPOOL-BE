package org.example.hana.recruitment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.example.hana.recruitment.entity.RecruitmentPost;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class RecruitmentPostResponse {
    private Long postId;

    private String leaderNickname;

    private String title;

    private String content;
    private String location;

    private String restaurantLink;

    private String category;

    private Integer maxParticipants;

    private Integer currentParticipants;

    private LocalDate meetingDate;
    private LocalTime meetingTime;

    @JsonProperty("isAuthor")
    private boolean isAuthor; // 추가된 필드


    public RecruitmentPostResponse(RecruitmentPost post, Long currentUserId) {
        this.postId = post.getId();
        this.leaderNickname = post.getUser().getNickname();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.location = post.getLocation();
        this.restaurantLink = post.getRestaurantLink();
        this.category = post.getCategory();
        this.maxParticipants = post.getMaxParticipants();
        this.currentParticipants = post.getApplications().size();
        this.meetingDate = post.getMeetingDate();
        this.meetingTime = post.getMeetingTime();
        this.isAuthor = post.getUser().getId().equals(currentUserId); // isAuthor 설정
    }
}
