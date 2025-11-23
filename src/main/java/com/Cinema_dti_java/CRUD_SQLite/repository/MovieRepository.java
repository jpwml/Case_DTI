package com.Cinema_dti_java.CRUD_SQLite.repository;

import com.Cinema_dti_java.CRUD_SQLite.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.time.LocalDate;


@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    boolean existsByNameMovie(String nameMovie);
    List<Movie> findByNameMovie(String nameMovie);
    List<Movie> findByDirector(String director);
    List<Movie> findByLaunchYear(LocalDate launchYear);
    Movie findByIdMovie(Long idMovie);

}
