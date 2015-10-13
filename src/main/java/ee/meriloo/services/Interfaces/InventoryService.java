package ee.meriloo.services.Interfaces;

import ee.meriloo.items.Movie;
import ee.meriloo.items.MovieType;
import java.util.List;

/**
 * Created by Lauri on 12.10.2015.
 */
public interface InventoryService {

    public List<Movie> addMovie(Movie movie);

    public List<Movie> removeMovie(Movie movie);

    public Movie changeType(Movie movie, MovieType newType);

    public List<Movie> listAllMovies();

    public List<Movie> listAllMoviesInStore();
}
