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
}
