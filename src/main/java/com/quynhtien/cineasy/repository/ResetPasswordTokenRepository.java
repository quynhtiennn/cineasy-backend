package com.quynhtien.cineasy.repository;

import com.quynhtien.cineasy.entity.ResetPasswordToken;
import com.quynhtien.cineasy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface ResetPasswordTokenRepository extends JpaRepository<ResetPasswordToken, UUID> {
    ResetPasswordToken findByUser(User user);

}
