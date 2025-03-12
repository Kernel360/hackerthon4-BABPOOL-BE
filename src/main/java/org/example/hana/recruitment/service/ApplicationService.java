package org.example.hana.recruitment.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.hana.global.exception.CustomException;
import org.example.hana.global.exception.ErrorCode;
import org.example.hana.recruitment.repository.ApplicationRepository;
import org.example.hana.recruitment.repository.RecruitmentPostRepository;
import org.example.hana.recruitment.entity.Application;
import org.example.hana.recruitment.entity.RecruitmentPost;
import org.example.hana.user.entity.User;
import org.example.hana.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final RecruitmentPostRepository recruitmentPostRepository;
    private final UserRepository userRepository;


    @Transactional
    public void createApplication(Long postId, Long userId) {
        User currentUser = userRepository.findById(userId).orElseThrow(
                ()->new CustomException(ErrorCode.NOT_FOUND)
        );

        RecruitmentPost recruitmentPost = recruitmentPostRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.APPLICATION_NOT_FOUND));

        if (applicationRepository.existsByUserAndRecruitmentPost(currentUser, recruitmentPost)) {
            throw new CustomException(ErrorCode.DUPLICATE_APPLICATION);
        }

        Application application = Application.builder()
                .user(currentUser)
                .recruitmentPost(recruitmentPost)
                .build();

        applicationRepository.save(application);
    }

    @Transactional
    public void deleteApplication(Long postId, Long userId) {
        Optional<Application> optionalApplication = applicationRepository.findByUserIdAndRecruitmentPostId(userId, postId);
        if(optionalApplication.isPresent()){
            applicationRepository.delete(optionalApplication.get());
        } else {
            throw new CustomException(ErrorCode.APPLICATION_NOT_FOUND);
        }
    }

    public boolean isUserParticipating(Long userId, Long meetingId) {
        if (userId == null) { // userId가 null인 경우 false 반환
            return false;
        }
        // 유저와 모임 존재 여부 확인 (필요에 따라 예외 처리)
        User user = userRepository.findById(userId).orElseThrow(
                ()-> new CustomException(ErrorCode.NOT_FOUND)
        );
        RecruitmentPost post = recruitmentPostRepository.findById(meetingId).orElseThrow(()->new CustomException(ErrorCode.RECRUITMENT_POST_NOT_FOUND));

        return applicationRepository.existsByUserAndRecruitmentPost(user, post);
    }
}
