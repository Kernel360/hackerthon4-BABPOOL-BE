package org.example.hana.recruitment.controller;

import lombok.RequiredArgsConstructor;
import org.example.hana.global.common.CommonResponse;
import org.example.hana.recruitment.model.RecruitmentCommentRequest;
import org.example.hana.recruitment.model.RecruitmentCommentResponse;
import org.example.hana.recruitment.model.RecruitmentPostListResponse;
import org.example.hana.recruitment.model.RecruitmentPostRequest;
import org.example.hana.recruitment.service.RecruitmentCommentService;
import org.example.hana.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recruitment-posts")
@RequiredArgsConstructor
public class RecruitmentCommentController {

    private final RecruitmentCommentService recruitmentCommentService;

    @GetMapping("/{postId}/comments")
    public ResponseEntity<CommonResponse> findRecruitmentCommentList(@PathVariable Long postId,@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size);

        Page<RecruitmentCommentResponse> commentList = recruitmentCommentService.getCommentsByPostId(postId, pageable);
        CommonResponse response = new CommonResponse("공고 댓글리스트 조회 성공", 201, commentList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    // 댓글 작성
    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommonResponse> createRecruitmentComment(@PathVariable Long postId, @RequestBody RecruitmentCommentRequest request) {
        User user = User.builder()
                .id(1L)
                .nickname("ggg")
                .username("djdj")
                .password("sdkjlf")
                .build();
        recruitmentCommentService.createRecruitmentComment(postId, request, user);
        CommonResponse response = new CommonResponse("공고 댓글 생성 성공", 201, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 댓글 수정
    @PatchMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<CommonResponse> updateRecruitmentComment(@PathVariable Long postId,@PathVariable Long commentId, @RequestBody RecruitmentPostRequest request) {
        User user = User.builder()
                .id(1L)
                .nickname("ggg")
                .username("djdj")
                .password("sdkjlf")
                .build();

        recruitmentCommentService.updateRecruitmentComment(postId,commentId, request, user);
        CommonResponse response = new CommonResponse("공고 댓글 생성 성공", 201, null);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }


    //댓글 삭제
    @DeleteMapping("{postId}/comments/{commentId}")
    public ResponseEntity<CommonResponse> deleteRecruitmentComment(@PathVariable Long commentId) {
        User user = User.builder()
                .id(1L)
                .nickname("ggg")
                .username("djdj")
                .password("sdkjlf")
                .build();

        recruitmentCommentService.deleteRecruitmentComment(commentId, user);

        CommonResponse response = new CommonResponse("공고 댓글 삭제 성공", 204, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
