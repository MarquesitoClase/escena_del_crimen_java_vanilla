package org.example.view;

import org.example.controller.MovieController;
import org.example.model.Movie;
import org.example.repository.MovieRepositoryImp;

import java.util.*;

public class MovieView {
    private MovieController movieController;
    MovieRepositoryImp movieRepository = new MovieRepositoryImp();
    public MovieView(MovieController movieController){
        this.movieController = movieController;
    }

    public void createMovieView(){
        Movie movie = generateMovie();
        movieController.createMovieController(movie);
    }
    public Movie generateMovie(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Escriba el título de la película: ");
        String title = scanner.nextLine();

        System.out.println("Escriba el género de la película: ");
        String genre = scanner.nextLine();

        System.out.println("Escriba el año de la película: ");
        int anio = Integer.parseInt(scanner.nextLine());

        System.out.println("Escriba la sinopsis de la película: ");
        String synopsis = scanner.nextLine();

        java.lang.String cont = "s";
        String[] actors = new String[0];
        while(cont.equals("s")){
            System.out.println("escribame el nombre de uno de sus actores/actrices.");
            scanner.nextLine();
            System.out.println("Hay más actores?  s->si\notra cosa-> no.");
            cont = scanner.nextLine();
        }

        System.out.println("Escriba su nota en FilmAffinity(#.##)");
        double score = Double.parseDouble(scanner.nextLine());

        System.out.println("Escriba la URL de la imagen: ");
        String imageUrl = scanner.nextLine();

        System.out.println("Escriba el director de la película: ");
        String director = scanner.nextLine();

        System.out.println("Escriba la valoración de la película: ");
        double rating = Double.parseDouble(scanner.nextLine());

        Movie movie = new Movie(title, anio, director,actors, 8.0, synopsis, imageUrl,  rating);

        movieRepository.getAllMovies();

        return movie;
    }
}
