package org.example.hana.review.repository;

import org.example.hana.review.entity.ReviewPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewPostRepository extends JpaRepository<ReviewPost, Long> {
}
