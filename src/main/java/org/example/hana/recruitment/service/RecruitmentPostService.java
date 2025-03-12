package org.example.hana.recruitment.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.hana.global.exception.CustomException;
import org.example.hana.global.exception.ErrorCode;
import org.example.hana.recruitment.db.ApplicationRepository;
import org.example.hana.recruitment.db.RecruitmentPostRepository;
import org.example.hana.recruitment.entity.Application;
import org.example.hana.recruitment.entity.RecruitmentPost;
import org.example.hana.recruitment.model.RecruitmentPostListResponse;
import org.example.hana.recruitment.model.RecruitmentPostRequest;
import org.example.hana.recruitment.model.RecruitmentPostResponse;
import org.example.hana.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecruitmentPostService {

    private final RecruitmentPostRepository recruitmentPostRepository;
    private final ApplicationRepository applicationRepository;

    /**
     * 모임 공고 생성
     *
     * @param request 공고 정보(제목,내용,식당주소,카테고리,인원제한)
     * @param user
     */
    @Transactional
    public void createRecruitmentPost(RecruitmentPostRequest request, User user) {


        RecruitmentPost entity = RecruitmentPost.builder()
                .user(user)
                .title(request.getTitle())
                .content(request.getContent())
                .restaurantLink(request.getRestaurantLink())
                .category(request.getCategory())
                .maxParticipants(request.getMaxParticipants())
                .meetingDate(request.getMeetingDate())
                .meetingTime(request.getMeetingTime())
                .build();
        RecruitmentPost savedEntity = recruitmentPostRepository.save(entity);

        Application application = Application.builder()
                .user(user)
                .recruitmentPost(savedEntity)
                .build();

        applicationRepository.save(application);


    }

    /**
     * 모임 공고 수정
     *
     * @param postId  공고 아이디
     * @param request 공고 정보(제목,내용,식당주소,카테고리,인원제한)
     * @param user
     */
    @Transactional
    public void updateRecruitmentPost(Long postId, RecruitmentPostRequest request, User user) {
        RecruitmentPost recruitmentPost = recruitmentPostRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.RECRUITMENT_POST_NOT_FOUND));

        if (!recruitmentPost.getUser().getId().equals(user.getId())) {
            throw new CustomException(ErrorCode.NO_PERMISSION_TO_MODIFY);
        }

        recruitmentPost.setTitle(request.getTitle());
        recruitmentPost.setContent(request.getContent());
        recruitmentPost.setRestaurantLink(request.getRestaurantLink());
        recruitmentPost.setCategory(request.getCategory());
        recruitmentPost.setMaxParticipants(request.getMaxParticipants());
    }


    /**
     * 공고 리스트 조회
     *
     * @param page 페이지
     * @param size 개수
     * @return
     */
    public List<RecruitmentPostListResponse> findRecruitmentPostList(int page, int size) {
//        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        Pageable pageable = PageRequest.of(page, size);
        Page<RecruitmentPost> postPage = recruitmentPostRepository.findAll(pageable);
        return postPage.getContent().stream()
                .map(RecruitmentPostListResponse::new)
                .collect(Collectors.toList());
    }

    public void deleteRecruitment(Long postId, User user) {
        RecruitmentPost recruitmentPost = recruitmentPostRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.RECRUITMENT_POST_NOT_FOUND));
        if (!recruitmentPost.getUser().getId().equals(user.getId())) {
            throw new CustomException(ErrorCode.NO_PERMISSION_TO_MODIFY);
        }
        recruitmentPostRepository.delete(recruitmentPost);
    }

    public RecruitmentPostResponse findRecruitmentPost(Long postId) {
        RecruitmentPost recruitmentPost = recruitmentPostRepository.findById(postId)
                .orElseThrow(()->new CustomException(ErrorCode.RECRUITMENT_POST_NOT_FOUND));

        return new RecruitmentPostResponse(recruitmentPost);
    }
}
