package org.example.hana.recruitment.repository;

import org.example.hana.recruitment.entity.RecruitmentComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RecruitmentCommentRepository extends JpaRepository<RecruitmentComment,Long> {
    Page<RecruitmentComment> findByRecruitmentPostId(Long postId, Pageable pageable);
}
