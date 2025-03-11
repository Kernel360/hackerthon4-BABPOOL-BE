package org.example.hana.review.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.hana.review.entity.ReviewComment;
import org.example.hana.review.entity.ReviewPost;
import org.example.hana.review.repository.ReviewCommentRepository;
import org.example.hana.review.repository.ReviewPostRepository;
import org.example.hana.review.service.info.ReviewCommentInfo;
import org.example.hana.review.service.info.ReviewPostInfo;
import org.example.hana.user.TempUserRepository;
import org.example.hana.user.entity.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewPostService {

    private final ReviewPostRepository reviewPostRepository;
    private final TempUserRepository userRepository;

    public ReviewPostInfo create(Long userId, String title, String content, String category, int rating) {
        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);

        ReviewPost reviewPost = ReviewPost.builder()
                .user(user) // TODO user 찾아서 추가
                .title(title)
                .content(content)
                .category(category)
                .rating(rating)
                .build();

        reviewPostRepository.save(reviewPost);

        return ReviewPostInfo.toInfo(reviewPost);
    }

    public List<ReviewPostInfo> findList() {
        Pageable pageable = PageRequest.of(0, 10);
        List<ReviewPost> reviewPosts = reviewPostRepository.findAll(pageable).getContent();

        return reviewPosts.stream()
                .map(ReviewPostInfo::toInfo)
                .toList();
    }

    public ReviewPostInfo find(Long reviewId) {
        ReviewPost reviewPost = reviewPostRepository.findById(reviewId)
                .orElseThrow(() -> new NoSuchElementException("no review post found with id: " + reviewId));

        return ReviewPostInfo.toInfo(reviewPost);
    }

    public ReviewPostInfo update(Long reviewId, String title, String content, String category, int rating) {
        ReviewPost reviewPost = reviewPostRepository.findById(reviewId)
                .orElseThrow(() -> new NoSuchElementException("no review post found with id: " + reviewId));
        System.out.println("check");
        reviewPost.setTitle(title);
        reviewPost.setContent(content);
        reviewPost.setCategory(category);
        reviewPost.setRating(rating);
        reviewPostRepository.save(reviewPost);

        return ReviewPostInfo.toInfo(reviewPost);

    }

    public void delete(Long reviewId) {
        ReviewPost reviewPost = reviewPostRepository.findById(reviewId)
                .orElseThrow(() -> new NoSuchElementException("no review post found with id: " + reviewId));

        reviewPostRepository.delete(reviewPost);
    }
}
