package org.example.model;

import java.util.Arrays;

public class Movie {
    private int id;
    private String title;
    private int year;
    private String director;
    private String[] actors;
    private double filmAffinityScore;
    private String filmDescription;
    private String imgUrl;
    private double price;
//(String title, int year, String director,String[] actors, double filmAffinityScore, String filmDescription, String imgUrl, double price)
public Movie(String title, int year, String director, String[] actors, double filmAffinityScore, String filmDescription, String imgUrl, double price) {
    this.title = title;
    this.year = year;
    this.director = director;
    this.actors = actors;
    this.filmAffinityScore = filmAffinityScore;
    this.filmDescription = filmDescription;
    this.imgUrl = imgUrl;
    this.price = price;
}

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String[] getActors() {
        return actors;
    }

    public void setActors(String[] actors) {
        this.actors = actors;
    }

    public double getFilmAffinityScore() {
        return filmAffinityScore;
    }

    public void setFilmAffinityScore(double filmAffinityScore) {
        this.filmAffinityScore = filmAffinityScore;
    }

    public String getFilmDescription() {
        return filmDescription;
    }

    public void setFilmDescription(String filmDescription) {
        this.filmDescription = filmDescription;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", year=" + year +
                ", director='" + director + '\'' +
                ", actors=" + Arrays.toString(actors) +
                ", filmAffinityScore=" + filmAffinityScore +
                ", filmDescription='" + filmDescription + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", price=" + price +
                '}';
    }
}
