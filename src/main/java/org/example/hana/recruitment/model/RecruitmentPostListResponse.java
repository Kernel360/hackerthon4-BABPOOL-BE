package org.example.hana.recruitment.model;

import lombok.Data;
import org.example.hana.recruitment.entity.RecruitmentPost;

@Data
public class RecruitmentPostListResponse {
    private Long postId;
    private String title;
    private Integer maxParticipants;

    public RecruitmentPostListResponse(RecruitmentPost post) {
        this.postId = post.getId();
        this.title = post.getTitle();
        this.maxParticipants = post.getMaxParticipants();
    }


}
