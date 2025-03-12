package org.example.hana.recruitment.controller;


import lombok.RequiredArgsConstructor;
import org.example.hana.global.common.CommonResponse;
import org.example.hana.global.util.UserUtils;
import org.example.hana.recruitment.entity.RecruitmentPost;
import org.example.hana.recruitment.model.RecruitmentPostListResponse;
import org.example.hana.recruitment.model.RecruitmentPostRequest;
import org.example.hana.recruitment.model.RecruitmentPostResponse;
import org.example.hana.recruitment.service.ApplicationService;
import org.example.hana.recruitment.service.RecruitmentPostService;
import org.example.hana.user.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/recruitment-posts")
@RequiredArgsConstructor
public class RecruitmentPostController {

    private final RecruitmentPostService recruitmentPostService;
    private final ApplicationService applicationService;


    @GetMapping("")
    public ResponseEntity<CommonResponse<List<RecruitmentPostListResponse>>> findRecruitmentPostList(@RequestParam(defaultValue = "1") int page,
                                                                                                     @RequestParam(defaultValue = "8") int size) {
        List<RecruitmentPostListResponse> postListResponseList = recruitmentPostService.findRecruitmentPostList(page, size);
        CommonResponse response = new CommonResponse("공고 리스트 조회 성공", 201, postListResponseList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<CommonResponse<RecruitmentPostResponse>> findRecruitmentPost(@PathVariable Long postId
            , Principal principal) {
        Long userId = UserUtils.getUserIdFromPrincipal(principal);
        RecruitmentPostResponse postResponse = recruitmentPostService.findRecruitmentPost(postId, userId);
        CommonResponse response = new CommonResponse("공고 상세 조회 성공", 201, postResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{meetingId}/participants/status")
    public ResponseEntity<CommonResponse<Boolean>> getParticipantStatus(@PathVariable Long meetingId,
                                                                        Principal principal) {
        Long userId = UserUtils.getUserIdFromPrincipal(principal);
        boolean isParticipating = applicationService.isUserParticipating(userId, meetingId);
        Map<String, Boolean> result = new HashMap<>();
        result.put("isParticipating", isParticipating);
        CommonResponse response = new CommonResponse("공고 참가 여부확인", 201, result);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<CommonResponse> createRecruitmentPost(@RequestBody RecruitmentPostRequest request,
                                                                Principal principal) {
        Long userId = UserUtils.getUserIdFromPrincipal(principal);
        RecruitmentPost postEntity = recruitmentPostService.createRecruitmentPost(request, userId);
        CommonResponse response = new CommonResponse("공고 생성 성공", 201, postEntity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<CommonResponse> updateRecruitmentPost(@PathVariable Long postId,
                                                                @RequestBody RecruitmentPostRequest request,
                                                                Principal principal) {
        Long userId = UserUtils.getUserIdFromPrincipal(principal);
        recruitmentPostService.updateRecruitmentPost(postId, request, userId);

        CommonResponse response = new CommonResponse("공고 수정 성공", 201, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @DeleteMapping("/{postId}")
    public ResponseEntity<CommonResponse> deleteRecruitmentPost(@PathVariable Long postId,
                                                                Principal principal) {
        Long userId = UserUtils.getUserIdFromPrincipal(principal);

        recruitmentPostService.deleteRecruitment(postId, userId);

        CommonResponse response = new CommonResponse("공고 삭제 성공", 204, null);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }


    @PostMapping("/{postId}/participants")
    public ResponseEntity<CommonResponse> createApplication(@PathVariable Long postId,
                                                            Principal principal) {
        Long userId = UserUtils.getUserIdFromPrincipal(principal);

        applicationService.createApplication(postId, userId);

        CommonResponse response = new CommonResponse("가입 신청 성공", 201, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{postId}/participants")
    public ResponseEntity<CommonResponse> deleteApplication(@PathVariable Long postId,
                                                            Principal principal) {
        Long userId = UserUtils.getUserIdFromPrincipal(principal);

        applicationService.deleteApplication(postId, userId);
        CommonResponse response = new CommonResponse("가입 신청 취소 성공", 204, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
