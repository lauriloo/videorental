package ee.meriloo.services.Interfaces;

import ee.meriloo.clients.Customer;
import ee.meriloo.transaction.RentalSession;
import ee.meriloo.transaction.Transaction;
import ee.meriloo.items.Movie;

/**
 * Created by Lauri on 12.10.2015.
 */
public interface MovieRentalService {

    public RentalSession rentAMovie(Movie movie, Customer customer, long days, RentalSession rentalSession);

    public RentalSession rentAMovieAndPayWithBounusPoints(Movie movie, Customer customer, long days, RentalSession rentalSession);

    public RentalSession returnAMovie(Movie movie, RentalSession rentalSession);

    public RentalSession returnAMovie(Movie movie, boolean overtimePaid, RentalSession rentalSession);

    public RentalSession beginRentalSession();

    public RentalSession finishRentalSession(RentalSession rentalSession);

}
