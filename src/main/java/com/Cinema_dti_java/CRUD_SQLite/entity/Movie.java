package com.Cinema_dti_java.CRUD_SQLite.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity  // ← DESCOMENTE ISSO!
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMovie;

    private String nameMovie;

    private String director;

    private LocalDate launchYear;

    private int duration;

    // Construtor vazio necessário para o JPA
    public Movie() {
    }

    public Movie(String nameMovie, String director, LocalDate launchYear, int duration) {
        this.nameMovie = nameMovie;
        this.director = director;
        this.launchYear = launchYear;
        this.duration = duration;
    }

    // ... resto dos getters e setters
    public Long getIdMovie() {
        return idMovie;
    }

    public LocalDate getLaunchYear() {
        return launchYear;
    }

    public void setLaunchYear(LocalDate launchYear) {
        this.launchYear = launchYear;
    }

    public void setIdMovie(Long idMovie) {
        this.idMovie = idMovie;
    }

    public String getNameMovie() {
        return nameMovie;
    }

    public void setNameMovie(String nameMovie) {
        this.nameMovie = nameMovie;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "idMovie=" + idMovie +
                ", nameMovie='" + nameMovie + '\'' +
                ", director='" + director + '\'' +
                ", launchYear=" + launchYear +
                ", duration=" + duration +
                '}';
    }
}//codigo corrigido dar ctrl+z