package org.example.hana.recruitment.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.hana.recruitment.db.RecruitmentPostRepository;
import org.example.hana.recruitment.entity.RecruitmentPost;
import org.example.hana.recruitment.model.RecruitmentPostListResponse;
import org.example.hana.recruitment.model.RecruitmentPostRequest;
import org.example.hana.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecruitmentPostService {

    private final RecruitmentPostRepository recruitmentPostRepository;

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
                .build();

        recruitmentPostRepository.save(entity);
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
                .orElseThrow(() -> new IllegalArgumentException("공고가 없습니다"));

        if (!recruitmentPost.getUser().getId().equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "해당 공고를 수정할 권한이 없습니다.");
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
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<RecruitmentPost> postPage = recruitmentPostRepository.findAll(pageable);
        return postPage.getContent().stream()
                .map(RecruitmentPostListResponse::new)
                .collect(Collectors.toList());
    }

    public void deleteRecruitment(Long postId) {
        RecruitmentPost recruitmentPost = recruitmentPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("공고가 없습니다"));

        recruitmentPostRepository.delete(recruitmentPost);
    }
}
