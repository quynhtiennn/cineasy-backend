package com.quynhtien.cineasy.repository;

import com.quynhtien.cineasy.entity.LoggedOutToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggedOutTokenRepository extends JpaRepository<LoggedOutToken, String> {
}
