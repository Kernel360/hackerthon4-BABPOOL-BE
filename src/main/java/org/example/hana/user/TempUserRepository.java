package org.example.hana.user;

import org.example.hana.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TempUserRepository extends JpaRepository<User, Long> {
}
