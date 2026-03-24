package org.example.controller;

import org.example.model.Movie;
import org.example.repository.MovieRepositoryImp;

public class MovieController {
    MovieRepositoryImp movieRepository;

    public MovieController(MovieRepositoryImp movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void createMovieController(Movie movie){
        movieRepository.createMovie(movie);
    }

    public boolean addMovie(Movie movie){
        if(!movieRepository.getAllMovies().contains(movie)) movieRepository.createMovie(movie);
        return true;
    }
}
