package com.example.demospringboot.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demospringboot.models.user;


@Repository
public interface PasswordRepository extends JpaRepository<user, Long> {
    user findByEmail(String email);
    user findByResetToken(String resetToken);
}
