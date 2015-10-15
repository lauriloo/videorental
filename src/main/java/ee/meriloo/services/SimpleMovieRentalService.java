package ee.meriloo.services;

import ee.meriloo.clients.Customer;
import ee.meriloo.services.Interfaces.TimeService;
import ee.meriloo.enums.RentableState;
import ee.meriloo.transaction.RentalSession;
import ee.meriloo.transaction.Transaction;
import ee.meriloo.items.Movie;
import ee.meriloo.services.Interfaces.BonuspointsService;
import ee.meriloo.services.Interfaces.MovieRentalService;
import ee.meriloo.transaction.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Lauri on 12.10.2015.
 */
@Service
public class SimpleMovieRentalService implements MovieRentalService {


    @Autowired
    private BonuspointsService bonusPointsService;

    @Autowired
    private TimeService timeService;

    @Override
    public RentalSession rentAMovie(Movie movie, Customer customer, long days, RentalSession rentalSession) {

        if(movie != null && movie.getRentableState().isRentable()){

            movie.setRentBeginTime(new Date());
            movie.setRentEndTime(null);
            movie.setRentOutTimeInDays(days);
            movie.setItemHolder(customer);
            bonusPointsService.addBonusPoints(customer, movie);
            movie.setRentableState(RentableState.RENTED_OUT);

            return rentalSession.addTransaction(new Transaction(customer, movie, TransactionType.RENTAMOVIE, true));
        }

        return rentalSession.addTransaction(new Transaction(customer, movie, TransactionType.RENTAMOVIE_MOVIE_NA, false));
    }

    @Override
    public RentalSession rentAMovieAndPayWithBounusPoints(Movie movie, Customer customer, long days, RentalSession rentalSession) {
        if(movie.getRentableState().isRentable() && bonusPointsService.isBonusPointsChargeable(customer, movie)){

            movie.setRentBeginTime(new Date());
            movie.setRentEndTime(null);
            movie.setRentOutTimeInDays(days);
            movie.setItemHolder(customer);
            bonusPointsService.useBonusPoints(customer, movie);
            movie.setRentableState(RentableState.RENTED_OUT);

            return rentalSession.addTransaction(new Transaction(customer, movie, TransactionType.RENTAMOVIEANDPAYWITHBONUSPOINTS, true));
        }

        return rentalSession.addTransaction(new Transaction(customer, movie, TransactionType.RENTAMOVIEANDPAYWITHBONUSPOINTS_FAIL, false));
    }

    @Override
    public RentalSession returnAMovie(Movie movie, RentalSession rentalSession) {

        return returnAMovie(movie, false, rentalSession);
    }

    @Override
    public RentalSession returnAMovie(Movie movie, boolean overtimePaid, RentalSession rentalSession) {
        if(timeService.calculateOvertimeInDays(movie)==0 || overtimePaid == true){
            resetMovie(movie);
            return rentalSession.addTransaction(new Transaction(null, movie, TransactionType.RETURNED, true));
        } else {
            return rentalSession.addTransaction(new Transaction(movie.getItemHolder(), movie, TransactionType.RETURNOVERTIMENOTPAID, false));
        }
    }

    @Override
    public RentalSession beginRentalSession() {
        RentalSession rentalSession = new RentalSession();
        return rentalSession;
    }

    @Override
    public RentalSession finishRentalSession(RentalSession rentalSession) {
        rentalSession.finish();
        return rentalSession;
    }

    private Movie resetMovie(Movie movie){
        movie.setRentBeginTime(null);
        movie.setRentEndTime(null);
        movie.setRentOutTimeInDays(0);
        movie.setItemHolder(null);
        movie.setRentableState(RentableState.IN_STORE);

        return movie;
    }
}
