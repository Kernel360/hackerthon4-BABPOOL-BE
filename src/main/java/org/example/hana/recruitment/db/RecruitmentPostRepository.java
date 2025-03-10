package org.example.hana.recruitment.db;

import org.example.hana.recruitment.entity.RecruitmentPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitmentPostRepository extends JpaRepository<RecruitmentPost, Long> {
}
