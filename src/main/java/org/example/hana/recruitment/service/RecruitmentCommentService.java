package org.example.hana.recruitment.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.hana.global.exception.CustomException;
import org.example.hana.global.exception.ErrorCode;
import org.example.hana.recruitment.db.RecruitmentCommentRepository;
import org.example.hana.recruitment.db.RecruitmentPostRepository;
import org.example.hana.recruitment.entity.RecruitmentComment;
import org.example.hana.recruitment.entity.RecruitmentPost;
import org.example.hana.recruitment.model.RecruitmentCommentRequest;
import org.example.hana.recruitment.model.RecruitmentCommentResponse;
import org.example.hana.recruitment.model.RecruitmentPostRequest;
import org.example.hana.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class RecruitmentCommentService {
    private final RecruitmentCommentRepository recruitmentCommentRepository;
    private final RecruitmentPostRepository recruitmentPostRepository;




    @Transactional
    public void createRecruitmentComment(Long postId, RecruitmentCommentRequest request, User user) {
        RecruitmentPost postEntity = recruitmentPostRepository.findById(postId).orElseThrow();
        RecruitmentComment entity = RecruitmentComment.builder()
                .content(request.getContent())
                .user(user)
                .recruitmentPost(postEntity)
                .build();
        recruitmentCommentRepository.save(entity);
    }

    @Transactional
    public void updateRecruitmentComment(Long postId, Long commentId, RecruitmentPostRequest request, User user) {
        RecruitmentComment recruitmentComment = recruitmentCommentRepository.findById(commentId)
                .orElseThrow(()-> new CustomException(ErrorCode.RECRUITMENT_COMMENT_NOT_FOUND));

        if (!recruitmentComment.getUser().getId().equals(user.getId())) {
            throw new CustomException(ErrorCode.NO_PERMISSION_TO_MODIFY);
        }

        recruitmentComment.setContent(request.getContent());

    }


    public Page<RecruitmentCommentResponse> getCommentsByPostId(Long postId, Pageable pageable) {
        Page<RecruitmentComment> comments = recruitmentCommentRepository.findByRecruitmentPostId(postId, pageable);
        return comments.map(RecruitmentCommentResponse::new);
    }


    @Transactional
    public void deleteRecruitmentComment(Long commentId, User user) {
        RecruitmentComment recruitmentComment = recruitmentCommentRepository.findById(commentId)
                .orElseThrow(()->new CustomException(ErrorCode.RECRUITMENT_COMMENT_NOT_FOUND));


        if (!recruitmentComment.getUser().getId().equals(user.getId())) {
            throw new CustomException(ErrorCode.NO_PERMISSION_TO_MODIFY);
        }

        recruitmentCommentRepository.delete(recruitmentComment);
    }
}
