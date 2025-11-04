package com.quynhtien.cineasy.repository;

import com.quynhtien.cineasy.entity.EmailVerificationToken;
import com.quynhtien.cineasy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface EmailVerificationTokenRepository extends JpaRepository<EmailVerificationToken, UUID> {
    EmailVerificationToken findByUser(User user);
}
