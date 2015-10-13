package ee.meriloo.services;


import ee.meriloo.items.Movie;
import ee.meriloo.items.MovieType;
import ee.meriloo.services.Interfaces.InventoryService;
import ee.meriloo.services.enums.RentableState;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Lauri on 09.10.2015.
 */
public class InventoryServiceImpl implements InventoryService {

    private List<Movie> movies = new LinkedList<Movie>();

    @Override
    public List<Movie> addMovie(Movie movie) {
        movies.add(movie);
        return movies;
    }

    @Override
    public List<Movie> removeMovie(Movie movie) {
        movies.remove(movie);
        return movies;
    }

    @Override
    public Movie changeType(Movie movie, MovieType newType) {
        movie.setMovieType(newType);
        return movie;
    }

    @Override
    public List<Movie> listAllMovies() {
        return movies;
    }

    @Override
    public List<Movie> listAllMoviesInStore() {
        List<Movie> moviesInStore = new LinkedList<Movie>();
        for(Movie movie : this.movies){
            if(movie.getRentableState() == RentableState.IN_STORE){
                moviesInStore.add(movie);
            }
        }
        return moviesInStore;
    }


}
