package org.example.hana.recruitment.controller;


import lombok.RequiredArgsConstructor;
import org.example.hana.global.common.CommonResponse;
import org.example.hana.recruitment.model.RecruitmentPostListResponse;
import org.example.hana.recruitment.model.RecruitmentPostRequest;
import org.example.hana.recruitment.model.RecruitmentPostResponse;
import org.example.hana.recruitment.service.ApplicationService;
import org.example.hana.recruitment.service.RecruitmentPostService;
import org.example.hana.user.repository.UserRepository;
import org.example.hana.user.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recruitment-posts")
@RequiredArgsConstructor
public class RecruitmentPostController {

    private final RecruitmentPostService recruitmentPostService;
    private final UserRepository userRepository;
    private final ApplicationService applicationService;


    @GetMapping("")
    public ResponseEntity<CommonResponse<List<RecruitmentPostListResponse>>> findRecruitmentPostList(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
        List<RecruitmentPostListResponse> postListResponseList = recruitmentPostService.findRecruitmentPostList(page, size);
        CommonResponse response = new CommonResponse("공고 리스트 조회 성공", 201, postListResponseList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<CommonResponse<RecruitmentPostResponse>> findRecruitmentPost(@PathVariable Long postId) {
        RecruitmentPostResponse postResponse = recruitmentPostService.findRecruitmentPost(postId);
        CommonResponse response = new CommonResponse("공고 상세 조회 성공", 201, postResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<CommonResponse> createRecruitmentPost(@RequestBody RecruitmentPostRequest request) {
        User user = User.builder()
                .id(1L)
                .nickname("ggg")
                .username("djdj")
                .password("sdkjlf")
                .build();
        recruitmentPostService.createRecruitmentPost(request,user);
        CommonResponse response = new CommonResponse("공고 생성 성공", 201, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<CommonResponse> updateRecruitmentPost(@PathVariable Long postId, @RequestBody RecruitmentPostRequest request) {
        User user = User.builder()
                .id(1L)
                .nickname("ggg")
                .username("djdj")
                .password("sdkjlf")
                .build();
        recruitmentPostService.updateRecruitmentPost(postId, request, user);

        CommonResponse response = new CommonResponse("공고 수정 성공", 201, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @DeleteMapping("/{postId}")
    public ResponseEntity<CommonResponse> deleteRecruitmentPost(@PathVariable Long postId) {
        User user = User.builder()
                .id(1L)
                .nickname("ggg")
                .username("djdj")
                .password("sdkjlf")
                .build();

        recruitmentPostService.deleteRecruitment(postId, user);

        CommonResponse response = new CommonResponse("공고 삭제 성공", 204, null);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }


    @PostMapping("/{postId}/participants")
    public ResponseEntity<CommonResponse> createApplication(@PathVariable Long postId){
        User user = User.builder()
                .id(2L)
                .nickname("ggg")
                .username("djdj")
                .password("sdkjlf")
                .build();
        applicationService.createApplication(postId,user);

        CommonResponse response = new CommonResponse("가입 신청 성공", 201, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{postId}/participants")
    public ResponseEntity<CommonResponse> deleteApplication(@PathVariable Long postId){
        User user = User.builder()
                .id(2L)
                .nickname("ggg")
                .username("djdj")
                .password("sdkjlf")
                .build();
        applicationService.deleteApplication(postId,user);
        CommonResponse response = new CommonResponse("가입 신청 취소 성공", 204, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
