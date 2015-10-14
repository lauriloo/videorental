package ee.meriloo.services.Interfaces;

import ee.meriloo.items.Movie;
import ee.meriloo.items.MovieType;
import java.util.Set;

/**
 * Created by Lauri on 12.10.2015.
 */
public interface InventoryService {

    public Set<Movie> addMovie(Movie movie);

    public Set<Movie> removeMovie(Movie movie);

    public Movie getMovieFromInventory(Movie movie);

    public Movie changeType(Movie movie, MovieType newType);

    public Set<Movie> changeMovieTypeInInventory(Movie movie, MovieType newType);

    public Set<Movie> getAllMovies();

    public Set<Movie> getAllMoviesInStore();

    public Set<Movie> resetInventory();

    public Set<Movie> setInventory(Set<Movie> newInventory);


}
