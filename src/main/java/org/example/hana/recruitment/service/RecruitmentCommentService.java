package org.example.hana.recruitment.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.hana.global.exception.CustomException;
import org.example.hana.global.exception.ErrorCode;
import org.example.hana.recruitment.repository.RecruitmentCommentRepository;
import org.example.hana.recruitment.repository.RecruitmentPostRepository;
import org.example.hana.recruitment.entity.RecruitmentComment;
import org.example.hana.recruitment.entity.RecruitmentPost;
import org.example.hana.recruitment.model.RecruitmentCommentRequest;
import org.example.hana.recruitment.model.RecruitmentCommentResponse;
import org.example.hana.recruitment.model.RecruitmentPostRequest;
import org.example.hana.user.entity.User;
import org.example.hana.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;


@Service
@RequiredArgsConstructor
public class RecruitmentCommentService {
    private final RecruitmentCommentRepository recruitmentCommentRepository;
    private final RecruitmentPostRepository recruitmentPostRepository;
    private final UserRepository userRepository;





    @Transactional
    public void createRecruitmentComment(Long postId, RecruitmentCommentRequest request, Long userId) {
        User currentUser = userRepository.findById(userId).orElseThrow(
                ()->new CustomException(ErrorCode.NOT_FOUND)
        );

        RecruitmentPost postEntity = recruitmentPostRepository.findById(postId).orElseThrow();
        RecruitmentComment entity = RecruitmentComment.builder()
                .content(request.getContent())
                .user(currentUser)
                .recruitmentPost(postEntity)
                .build();
        recruitmentCommentRepository.save(entity);
    }

    @Transactional
    public void updateRecruitmentComment(Long commentId, RecruitmentPostRequest request, Long userId) {

        RecruitmentComment recruitmentComment = recruitmentCommentRepository.findById(commentId)
                .orElseThrow(()-> new CustomException(ErrorCode.RECRUITMENT_COMMENT_NOT_FOUND));

        if (!recruitmentComment.getUser().getId().equals(userId)) {
            throw new CustomException(ErrorCode.NO_PERMISSION_TO_MODIFY);
        }

        recruitmentComment.setContent(request.getContent());

    }


    public Page<RecruitmentCommentResponse> getCommentsByPostId(Long postId, Pageable pageable, Long userId) {
        Page<RecruitmentComment> comments = recruitmentCommentRepository.findByRecruitmentPostId(postId, pageable);

        return comments.map(comment -> {
            boolean isAuthor = comment.getUser().getId().equals(userId);
            return new RecruitmentCommentResponse(comment, isAuthor);
        });
    }


    @Transactional
    public void deleteRecruitmentComment(Long commentId, Long userId) {

        RecruitmentComment recruitmentComment = recruitmentCommentRepository.findById(commentId)
                .orElseThrow(()->new CustomException(ErrorCode.RECRUITMENT_COMMENT_NOT_FOUND));


        if (!recruitmentComment.getUser().getId().equals(userId)) {
            throw new CustomException(ErrorCode.NO_PERMISSION_TO_MODIFY);
        }

        recruitmentCommentRepository.delete(recruitmentComment);
    }
}
