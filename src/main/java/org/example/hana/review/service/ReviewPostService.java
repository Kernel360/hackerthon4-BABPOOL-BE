package org.example.hana.review.service;

import lombok.RequiredArgsConstructor;
import org.example.hana.review.entity.ReviewPost;
import org.example.hana.review.repository.ReviewPostRepository;
import org.example.hana.review.service.info.ReviewPostInfo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ReviewPostService {

    private final ReviewPostRepository reviewPostRepository;

    public void create(Long userId, String title, String content, String category, int rating) {
        ReviewPost reviewPost = ReviewPost.builder()
                .user(null) // TODO user 찾아서 추가
                .title(title)
                .content(content)
                .category(category)
                .rating(rating)
                .build();

        reviewPostRepository.save(reviewPost);
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

    public void update(Long reviewId, String title, String content, String category) {
        ReviewPost reviewPost = reviewPostRepository.findById(reviewId)
                .orElseThrow(() -> new NoSuchElementException("no review post found with id: " + reviewId));

        reviewPost.setTitle(title);
        reviewPost.setContent(content);
        reviewPost.setCategory(category);
        reviewPostRepository.save(reviewPost);
    }

    public void delete(Long reviewId) {
        ReviewPost reviewPost = reviewPostRepository.findById(reviewId)
                .orElseThrow(() -> new NoSuchElementException("no review post found with id: " + reviewId));

        reviewPostRepository.delete(reviewPost);
    }
}
