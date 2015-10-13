package ee.meriloo.services.Interfaces;

import ee.meriloo.items.Movie;

import java.util.List;

/**
 * Created by Lauri on 13.10.2015.
 */
public interface ParserService {

    public String parseRentalResult(Movie movie);

    public String parseLateReturn(Movie movie);

    public String parseRentResults(List<Movie> movies);

    public String parseLateReturns(List<Movie> movies);

    public String parseListOfMovies(List<Movie> movies);

    public String parseMovie(Movie movie);
}
