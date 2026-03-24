package org.example.repository;

import org.example.config.DBManager;
import org.example.model.Movie;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MovieRepositoryImp {

    private Connection connection;

    public void createMovie(Movie movie) {
        String querySQLCreate =
                "INSERT INTO films(title, year, director, actors, filmAffinittyScore, imgUrl, url, ranking) " +
                        "VALUES ('" + movie.getTitle() + "','" + movie.getYear() + "','" + movie.getDirector() + "','" +
                        String.join(", ", movie.getActors()) + "','" + movie.getFilmAffinityScore() +
                        "','" + movie.getImgUrl() + "','','" + 25 + "')";

        try {
            connection = DBManager.innitConnection(); // conectar a la BD
            Statement statement = connection.createStatement();
            statement.executeUpdate(querySQLCreate);
            System.out.println("Pelicula creada en la BBDD");

        } catch (Exception exception) {
            System.out.println(exception.getMessage());

        } finally {
            DBManager.closeConnection();
        }
    }

    public ArrayList<Movie> getAllMovies() {
        ArrayList<Movie> movies = new ArrayList<>();
        String querySQLGet = "SELECT * FROM films";

        try {
            connection = DBManager.innitConnection();
            Statement statement = connection.createStatement();
            System.out.println("Estoy aquí");
            ResultSet rs = statement.executeQuery(querySQLGet);

            while (rs.next()) {
                String actorsString = rs.getString("actors");
                String[] actorsArray = actorsString.split(",\\s*");

                Movie movie = new Movie(
                        rs.getString("title"),
                        rs.getInt("year"),
                        rs.getString("director"),
                        actorsArray,
                        rs.getDouble("filmAffinittyScore"),
                        rs.getString("imgUrl"),
                        rs.getString("url"),
                        rs.getInt("ranking")
                );
                movies.add(movie);
            }

        } catch (Exception exception) {
            System.out.println(exception.getMessage());

        } finally {
            DBManager.closeConnection();
        }

        return movies;
    }
}