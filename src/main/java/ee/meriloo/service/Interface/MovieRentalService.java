package ee.meriloo.service.Interface;

import ee.meriloo.clients.Customer;
import ee.meriloo.transaction.Transaction;
import ee.meriloo.items.Movie;

/**
 * Created by Lauri on 12.10.2015.
 */
public interface MovieRentalService {

    public Transaction rentAMovie(Movie movie, Customer customer, long days);

    public Transaction rentAMovieAndPayWithBounusPoints(Movie movie, Customer customer, long days);

    public Transaction returnAMovie(Movie movie);

    public Transaction returnAMovie(Movie movie, boolean overtimePaid);

}
