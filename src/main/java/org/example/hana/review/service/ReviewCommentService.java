package org.example.hana.review.service;

import lombok.RequiredArgsConstructor;
import org.example.hana.global.jwt.TokenProvider;
import org.example.hana.review.entity.ReviewComment;
import org.example.hana.review.entity.ReviewPost;
import org.example.hana.review.repository.ReviewCommentRepository;
import org.example.hana.review.repository.ReviewPostRepository;
import org.example.hana.review.service.info.ReviewCommentInfo;
import org.example.hana.user.TempUserRepository;
import org.example.hana.user.entity.User;
import org.example.hana.user.repository.UserRepository;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ReviewCommentService {

    private final ReviewPostRepository reviewPostRepository;
    private final ReviewCommentRepository reviewCommentRepository;
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;

    public ReviewCommentInfo create(Long postId, Long userId, String content) {
        ReviewPost post = reviewPostRepository.findById(postId)
                .orElseThrow(() -> new NoSuchElementException("no review post found with id: " + postId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("no user found with id: " + userId));

        ReviewComment reviewComment = ReviewComment.builder()
                .reviewPost(post)
                .user(user)
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

    public ReviewCommentInfo update(Long postId, Long commentId, String content, Long userId) {
        reviewPostRepository.findById(postId)
                .orElseThrow(() -> new NoSuchElementException("no review post found with id: " + postId));
        ReviewComment reviewComment = reviewCommentRepository.findById(commentId)
                .orElseThrow(() -> new NoSuchElementException("no comment found with id: " + commentId));
        User user = userRepository.findById(userId)
                        .orElseThrow(() -> new NoSuchElementException("no user found with id: " + userId));

        if (!user.getId().equals(reviewComment.getUser().getId())) {
            throw new RuntimeException("no permission to update comment");
        }

        reviewComment.setContent(content);

        reviewCommentRepository.save(reviewComment);

        return ReviewCommentInfo.toInfo(reviewComment);
    }

    public void delete(Long postId, Long commentId) {
        reviewPostRepository.findById(postId)
                .orElseThrow(() -> new NoSuchElementException("no review post found with id: " + postId));
        ReviewComment reviewComment = reviewCommentRepository.findById(commentId)
                .orElseThrow(() -> new NoSuchElementException("no comment found with id: " + commentId));

        reviewCommentRepository.delete(reviewComment);
    }
}
