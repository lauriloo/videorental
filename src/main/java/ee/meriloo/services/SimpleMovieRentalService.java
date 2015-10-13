package ee.meriloo.services;

import ee.meriloo.clients.Customer;
import ee.meriloo.services.Interfaces.TimeService;
import ee.meriloo.services.enums.RentableState;
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
    public Transaction rentAMovie(Movie movie, Customer customer, long days) {

        if(movie.getRentableState().isRentable()){

            movie.setRentBeginTime(new Date());
            movie.setRentEndTime(null);
            movie.setRentOutTimeInDays(days);
            movie.setItemHolder(customer);
            bonusPointsService.addBonusPoints(customer, movie);
            movie.setRentableState(RentableState.RENTED_OUT);

            return new Transaction(customer, movie, TransactionType.RENTAMOVIE, true);
        }

        return new Transaction(customer, movie, TransactionType.RENTAMOVIE, false);
    }

    @Override
    public Transaction rentAMovieAndPayWithBounusPoints(Movie movie, Customer customer, long days) {
        if(movie.getRentableState().isRentable() && bonusPointsService.isBonusPointsChargeable(customer, movie)){

            movie.setRentBeginTime(new Date());
            movie.setRentEndTime(null);
            movie.setRentOutTimeInDays(days);
            movie.setItemHolder(customer);
            bonusPointsService.useBonusPoints(customer, movie);
            movie.setRentableState(RentableState.RENTED_OUT);

            return new Transaction(customer, movie, TransactionType.RENTAMOVIEANDPAYWITHBONUSPOINTS, true);
        }

        return new Transaction(customer, movie, TransactionType.RENTAMOVIEANDPAYWITHBONUSPOINTS, false);
    }

    @Override
    public Transaction returnAMovie(Movie movie) {
        return returnAMovie(movie, false);
    }

    @Override
    public Transaction returnAMovie(Movie movie, boolean overtimePaid) {
        if(timeService.calculateOvertimeInDays(movie)==0 || overtimePaid == true){
            resetMovie(movie);
            return new Transaction(null, movie, TransactionType.RETURNED, true);
        } else {
            return new Transaction(movie.getItemHolder(), movie, TransactionType.RETURNOVERTIMENOTPAID, false);
        }
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
