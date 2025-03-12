package org.example.hana.recruitment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.example.hana.recruitment.entity.RecruitmentComment;

@Data
public class RecruitmentCommentResponse {
    private Long commentId;
    private String nickname;
    private String content;
    @JsonProperty("isAuthor")
    private boolean isAuthor;

    public RecruitmentCommentResponse(RecruitmentComment comment, boolean isAuthor) {
        this.commentId = comment.getId();
        this.nickname = comment.getUser().getNickname();
        this.content = comment.getContent();
        this.isAuthor = isAuthor;
    }
}
