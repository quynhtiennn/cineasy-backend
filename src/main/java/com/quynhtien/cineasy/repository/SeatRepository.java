package com.quynhtien.cineasy.repository;

import com.quynhtien.cineasy.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
}
