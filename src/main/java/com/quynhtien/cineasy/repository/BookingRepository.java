package com.quynhtien.cineasy.repository;

import com.quynhtien.cineasy.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {
}
