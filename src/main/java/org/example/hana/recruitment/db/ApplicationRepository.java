package org.example.hana.recruitment.db;

import org.example.hana.recruitment.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Optional<Application> findByUserIdAndRecruitmentPostId(Long id, Long postId);
}
