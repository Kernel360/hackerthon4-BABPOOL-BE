package org.example.hana.recruitment.controller;


import lombok.RequiredArgsConstructor;
import org.example.hana.global.common.CommonResponse;
import org.example.hana.recruitment.model.RecruitmentPostListResponse;
import org.example.hana.recruitment.model.RecruitmentPostRequest;
import org.example.hana.recruitment.service.RecruitmentPostService;
import org.example.hana.user.db.UserRepository;
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

    @PostMapping("")
    public ResponseEntity<CommonResponse> createRecruitmentPost(@RequestBody RecruitmentPostRequest request) {
        User user = User.builder()
                .nickname("ggg")
                .username("djdj")
                .password("sdkjlf")
                .build();
        userRepository.save(user);
        recruitmentPostService.createRecruitmentPost(request,user);
        CommonResponse response= new CommonResponse("공고 생성 성공",201,null);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PatchMapping("/{postId}")
    public void updateRecruitmentPost(@PathVariable Long postId, @RequestBody RecruitmentPostRequest request) {
        User user = User.builder()
                .nickname("ggg")
                .username("djdj")
                .password("sdkjlf")
                .build();
        recruitmentPostService.updateRecruitmentPost(postId, request, user);

    }

    @GetMapping("")
    public ResponseEntity<CommonResponse<List<RecruitmentPostListResponse>>> findRecruitmentPostList(@RequestParam(defaultValue = "1")int page, @RequestParam(defaultValue = "10") int size) {
        List<RecruitmentPostListResponse> postListResponseList = recruitmentPostService.findRecruitmentPostList(page, size);
        CommonResponse response= new CommonResponse("공고 생성 성공",201,postListResponseList);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<CommonResponse> deleteRecruitmentPost(@PathVariable Long postId) {
        recruitmentPostService.deleteRecruitment(postId);

        CommonResponse response= new CommonResponse("공고 삭제 성공",204,null);
        return new ResponseEntity<>(response,HttpStatus.OK);

    }


}
