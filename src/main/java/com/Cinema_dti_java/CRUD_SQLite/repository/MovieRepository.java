package com.Cinema_dti_java.CRUD_SQLite.repository;

import com.Cinema_dti_java.CRUD_SQLite.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.time.LocalDate;


@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    boolean existsByNameMovie(String nameMovie);
    List<Movie> findByNameMovieContainingIgnoreCase(String nameMovie);
    List<Movie> findByDirectorContainingIgnoreCase(String director);
    List<Movie> findByLaunchYearContainingIgnoreCase(LocalDate launchYear);
    Movie findByIdMovie(Long idMovie);

}
