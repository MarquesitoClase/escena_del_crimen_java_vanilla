package org.example;

import org.example.config.DBManager;
import org.example.controller.MovieController;
import org.example.model.Movie;
import org.example.repository.MovieRepositoryImp;
import org.example.view.MovieView;

import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        MovieRepositoryImp movieRepository = new MovieRepositoryImp();
        String[] actores = {"Elijah Wood","Ian McKellen","Viggo Mortensen","Sean Astin","Orlando Bloom","John Rhys-Davies","Sean Bean","Dominic Monaghan","Billy Boyd","Cate Blanchett","Liv Tyler","Hugo Weaving","Christopher Lee","Ian Holm"};
        MovieController movieController = new MovieController((movieRepository));
        MovieView movieView = new MovieView(movieController);
        ArrayList<Movie> movies = new ArrayList<>();
        movies = movieRepository.getAllMovies();
        for (Movie movie:movies){
            System.out.println(movie);
        }
    }
}