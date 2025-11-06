package com.quynhtien.cineasy.repository;

import com.quynhtien.cineasy.entity.Movie;
import com.quynhtien.cineasy.enums.MovieStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByStatus(MovieStatusEnum status);
}
