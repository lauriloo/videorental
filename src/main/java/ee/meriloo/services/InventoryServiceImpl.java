package ee.meriloo.services;


import ee.meriloo.items.Inventory;
import ee.meriloo.items.Movie;
import ee.meriloo.items.MovieType;
import ee.meriloo.services.Interfaces.InventoryService;
import ee.meriloo.services.enums.RentableState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Lauri on 09.10.2015.
 */
@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private Inventory inventory;


    @Override
    public List<Movie> addMovie(Movie movie) {
        List<Movie> movies = inventory.getMovies();
        movies.add(movie);
        return movies;
    }

    @Override
    public List<Movie> removeMovie(Movie movie) {
        List<Movie> movies = inventory.getMovies();
        movies.remove(movie);
        return movies;
    }

    @Override
    public Movie changeType(Movie movie, MovieType newType) {
        movie.setMovieType(newType);
        return movie;
    }

    @Override
    public List<Movie> getAllMovies() {
        List<Movie> movies = inventory.getMovies();
        return movies;
    }

    @Override
    public List<Movie> getAllMoviesInStore() {
        List<Movie> movies = inventory.getMovies();
        List<Movie> moviesInStore = new LinkedList<Movie>();
        for(Movie movie : movies){
            if(movie.getRentableState() == RentableState.IN_STORE){
                moviesInStore.add(movie);
            }
        }
        return moviesInStore;
    }




}
