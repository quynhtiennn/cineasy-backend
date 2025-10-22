package com.quynhtien.cineasy.repository;

import com.quynhtien.cineasy.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ShowTimeRepository extends JpaRepository<Showtime, Long> {
}
