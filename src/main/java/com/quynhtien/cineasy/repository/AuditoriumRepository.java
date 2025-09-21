package com.quynhtien.cineasy.repository;

import com.quynhtien.cineasy.entity.Auditorium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuditoriumRepository extends JpaRepository<Auditorium, Long> {
}
