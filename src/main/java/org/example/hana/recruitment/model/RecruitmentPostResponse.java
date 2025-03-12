package org.example.hana.recruitment.model;

import lombok.Data;
import org.example.hana.recruitment.entity.RecruitmentPost;

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

    private Long userId;


    public RecruitmentPostResponse(RecruitmentPost post) {
        this.postId = post.getId();
        this.leaderNickname = post.getUser().getNickname();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.location = post.getLocation();
        this.restaurantLink = post.getRestaurantLink();
        this.category = post.getCategory();
        this.maxParticipants = post.getMaxParticipants();
        this.currentParticipants = post.getApplications().size();
        this.userId = post.getUser().getId();
    }
}
