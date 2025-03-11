package org.example.hana.recruitment.model;

import lombok.Data;
import org.example.hana.recruitment.entity.RecruitmentComment;

@Data
public class RecruitmentCommentResponse {
    private Long commentId;
    private String nickname;
    private String content;

    public RecruitmentCommentResponse(RecruitmentComment comment) {
        this.commentId = comment.getId();
        this.nickname = comment.getUser().getNickname();
        this.content = comment.getContent();
    }
}
