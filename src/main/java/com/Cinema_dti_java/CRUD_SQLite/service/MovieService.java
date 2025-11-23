package com.Cinema_dti_java.CRUD_SQLite.service;

import com.Cinema_dti_java.CRUD_SQLite.entity.Movie;
import com.Cinema_dti_java.CRUD_SQLite.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MovieService {

    boolean continuar = true;

    @Autowired
    private MovieRepository movieRepository;

    public Movie criarFilme(Movie movie) {


     while (continuar) {
         if (movie.getNameMovie() == null) {
             System.out.println("O nome não pode ser nulo");
         }
         continuar = false;
     }
        if (movieRepository.existsByNameMovie(movie.getNameMovie())) {
            throw new RuntimeException("Já existe um filme com esse nome!");
        }

        return movieRepository.save(movie);
    }


    public List<Movie> BuscarTodosFilmes() {
        return movieRepository.findAll();
    }

    public Movie BuscarPorId(Long idMovie) {return movieRepository.findByIdMovie(idMovie);}

    public List<Movie> BuscarPorNome(String nome) {
        return movieRepository.findByNameMovieContainingIgnoreCase(nome);

    }

    public List<Movie> BuscarPorDiretor(String director) {
        return movieRepository.findByDirectorContainingIgnoreCase(director);
    }

    public List<Movie> BuscarPorAnoLancamento(LocalDate launchYear) {
        return movieRepository.findByLaunchYearContainingIgnoreCase(launchYear);
    }

    public Movie atualizarFilme(Long idMovie, Movie movieAtualizado) {

        Movie movieExistente = movieRepository.findByIdMovie(idMovie);

        movieExistente.setNameMovie(movieAtualizado.getNameMovie());
        movieExistente.setLaunchYear(movieAtualizado.getLaunchYear());
        movieExistente.setDirector(movieAtualizado.getDirector());
        movieExistente.setDuration(movieAtualizado.getDuration());

        return movieRepository.save(movieExistente);
    }



    public void DeletarMovie(Long idMovie){

        Movie MovieDeletar = movieRepository.findByIdMovie(idMovie);

        movieRepository.delete(MovieDeletar);
    }

    public long contarMovies() {
        return movieRepository.count();
    }


}


