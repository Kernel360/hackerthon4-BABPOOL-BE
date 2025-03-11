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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewPostService {

    private final ReviewPostRepository reviewPostRepository;
    private final TempUserRepository userRepository;

    public ReviewPostInfo create(Long userId, String title, String content, String category, int rating, MultipartFile image) {
        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);

        String filePath = null;
        if(image != null && !image.isEmpty()) {
//            filePath = saveFile(image); // TODO 배포후 할것
        }

        ReviewPost reviewPost = ReviewPost.builder()
                .user(user)
                .title(title)
                .content(content)
                .category(category)
                .rating(rating)
                .build();

        reviewPostRepository.save(reviewPost);

        return ReviewPostInfo.toInfo(reviewPost);
    }

    private String saveFile(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFilename = UUID.randomUUID().toString() + fileExtension; // 고유한 파일 이름 생성

        String uploadDir = "uploads/"; // 파일 저장 경로 (상대 경로 또는 절대 경로)
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String filePath = uploadDir + newFilename;
        try {
            file.transferTo(new File(filePath));
            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
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
