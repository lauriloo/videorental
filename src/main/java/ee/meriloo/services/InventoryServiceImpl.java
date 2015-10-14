package ee.meriloo.services;


import ee.meriloo.items.Inventory;
import ee.meriloo.items.Movie;
import ee.meriloo.items.MovieType;
import ee.meriloo.services.Interfaces.InventoryService;
import ee.meriloo.services.enums.RentableState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by Lauri on 09.10.2015.
 */
@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private Inventory inventory;


    @Override
    public Set<Movie> addMovie(Movie movie) {
        Set<Movie> movies = inventory.getMovies();
        // If Inventory already has this movie, do nothing.
        if (movies.contains(movie)) {
            for (Movie inventoryMovie : movies) {
                if (inventoryMovie.equals(movie))
                    return movies;
            }
        }
        // If Inventory doesn't have this movie, add it.
        movies.add(movie);
        return movies;
    }

    @Override
    public Set<Movie> removeMovie(Movie movie) {
        Set<Movie> movies = inventory.getMovies();
        movies.remove(movie);
        return movies;
    }

    @Override
    public Movie getMovieFromInventory(Movie movie) {
        Set<Movie> movies = inventory.getMovies();
        if (movies.contains(movie)) {
            for (Movie inventoryMovie : movies) {
                if (inventoryMovie.equals(movie)) {
                    return inventoryMovie;
                }
            }
        }
        return null;
    }

    @Override
    public Movie getMovieFromInventory(String title) {
        Movie movie = new Movie(title, MovieType.UNKNOWN);
        return getMovieFromInventory(movie);
    }

    @Override
    public Movie changeType(Movie movie, MovieType newType) {
        movie.setMovieType(newType);
        return movie;
    }

    @Override
    public Set<Movie> changeMovieTypeInInventory(Movie movie, MovieType newType) {
        this.getMovieFromInventory(movie).setMovieType(newType);
        return inventory.getMovies();
    }

    @Override
    public Set<Movie> getAllMovies() {
        Set<Movie> movies = inventory.getMovies();
        return movies;
    }

    @Override
    public Set<Movie> getAllMoviesInStore() {
        Set<Movie> movies = inventory.getMovies();
        Set<Movie> moviesInStore = new HashSet<Movie>();
        for(Movie movie : movies){
            if(movie.getRentableState() == RentableState.IN_STORE){
                moviesInStore.add(movie);
            }
        }
        return moviesInStore;
    }


    @Override
    public Set<Movie> resetInventory() {
        Set<Movie> newInventory = new HashSet<Movie>();
        inventory.setMovies(newInventory);
        return newInventory;
    }

    @Override
    public Set<Movie> setInventory(Set<Movie> newIventory) {
        inventory.setMovies(newIventory);
        return newIventory;
    }
}
