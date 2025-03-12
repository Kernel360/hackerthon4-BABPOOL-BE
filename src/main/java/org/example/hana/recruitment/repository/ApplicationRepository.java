package org.example.hana.recruitment.repository;

import org.example.hana.recruitment.entity.Application;
import org.example.hana.recruitment.entity.RecruitmentPost;
import org.example.hana.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Optional<Application> findByUserIdAndRecruitmentPostId(Long id, Long postId);

    boolean existsByUserAndRecruitmentPost(User currentUser, RecruitmentPost recruitmentPost);
}
