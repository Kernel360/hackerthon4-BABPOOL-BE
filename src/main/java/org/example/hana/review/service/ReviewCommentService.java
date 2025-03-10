package org.example.hana.review.service;

import lombok.RequiredArgsConstructor;
import org.example.hana.review.entity.ReviewComment;
import org.example.hana.review.entity.ReviewPost;
import org.example.hana.review.repository.ReviewCommentRepository;
import org.example.hana.review.repository.ReviewPostRepository;
import org.example.hana.review.service.info.ReviewCommentInfo;
import org.example.hana.user.TempUserRepository;
import org.example.hana.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ReviewCommentService {

    private final ReviewPostRepository reviewPostRepository;
    private final ReviewCommentRepository reviewCommentRepository;
    private final TempUserRepository tempUserRepository;

    public ReviewCommentInfo create(Long postId, Long userId, String content) {
        ReviewPost post = reviewPostRepository.findById(postId)
                .orElseThrow(() -> new NoSuchElementException("no review post found with id: " + postId));
        User user = tempUserRepository.findById(userId).get();

        ReviewComment reviewComment = ReviewComment.builder()
                .reviewPost(post)
                .user(user) // TODO
                .content(content)
                .build();

        reviewCommentRepository.save(reviewComment);

        return ReviewCommentInfo.toInfo(reviewComment);
    }

    public List<ReviewCommentInfo> findByPostId(Long postId) {
        ReviewPost reviewPost = reviewPostRepository.findById(postId)
                .orElseThrow(() -> new NoSuchElementException("no review post found with id: " + postId));

        List<ReviewComment> comments = reviewCommentRepository.findAllByReviewPost(reviewPost);

        return comments.stream()
                .map(ReviewCommentInfo::toInfo)
                .toList();
    }

    public void update(Long postId, Long commentId, String content) {
        reviewPostRepository.findById(postId)
                .orElseThrow(() -> new NoSuchElementException("no review post found with id: " + postId));
        ReviewComment reviewComment = reviewCommentRepository.findById(commentId)
                .orElseThrow(() -> new NoSuchElementException("no comment found with id: " + commentId));

        reviewComment.setContent(content);

        reviewCommentRepository.save(reviewComment);
    }

    public void delete(Long postId, Long commentId) {
        reviewPostRepository.findById(postId)
                .orElseThrow(() -> new NoSuchElementException("no review post found with id: " + postId));
        ReviewComment reviewComment = reviewCommentRepository.findById(commentId)
                .orElseThrow(() -> new NoSuchElementException("no comment found with id: " + commentId));

        reviewCommentRepository.delete(reviewComment);
    }
}
