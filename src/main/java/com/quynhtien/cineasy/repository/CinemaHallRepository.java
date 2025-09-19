package com.quynhtien.cineasy.repository;

import com.quynhtien.cineasy.entity.CinemaHall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CinemaHallRepository extends JpaRepository<CinemaHall, Long> {
}
