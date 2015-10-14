package ee.meriloo.services.Interfaces;

import ee.meriloo.items.Movie;

import java.util.List;
import java.util.Set;

/**
 * Created by Lauri on 13.10.2015.
 */
public interface ParserService {

    public String parseRentalResult(Movie movie);

    public String parseLateReturn(Movie movie);

    public String parseRentResults(Set<Movie> movies);

    public String parseLateReturns(Set<Movie> movies);

    public String parseListOfMovies(Set<Movie> movies);

    public String parseMovie(Movie movie);
}
