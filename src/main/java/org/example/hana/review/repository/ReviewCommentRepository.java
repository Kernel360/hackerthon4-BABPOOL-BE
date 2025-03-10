package org.example.hana.review.repository;

import org.example.hana.review.entity.ReviewComment;
import org.example.hana.review.entity.ReviewPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewCommentRepository extends JpaRepository<ReviewComment, Long> {

    List<ReviewComment> findAllByReviewPost(ReviewPost reviewPost);
}
