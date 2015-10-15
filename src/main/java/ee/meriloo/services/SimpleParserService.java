package ee.meriloo.services;

import ee.meriloo.items.Movie;
import ee.meriloo.services.Interfaces.PriceCalculatorService;
import ee.meriloo.services.Interfaces.ParserService;
import ee.meriloo.services.Interfaces.TimeService;
import ee.meriloo.transaction.RentalSession;
import ee.meriloo.transaction.Transaction;
import ee.meriloo.transaction.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Lauri on 11.10.2015.
 */
@Service
public class SimpleParserService implements ParserService {

    @Autowired
    private PriceCalculatorService priceCalculatorService;

    @Autowired
    private TimeService timeService;



    @Override
    public String parseRentalSession(RentalSession rentalSession) {

        List<Transaction> transactions = rentalSession.getTransactions();
        Set<Movie> moviesRented = new HashSet<Movie>();
        Set<Movie> moviesLateReturned = new HashSet<Movie>();
        Set<Movie> moviesPaidWithBonusPoints = new HashSet<Movie>();
        Set<Movie> everythingElse = new HashSet<Movie>();
        for (Transaction transaction : transactions){
            if(transaction.getTransactionType() == TransactionType.RENTAMOVIE){
                moviesRented.add((Movie)transaction.getRentableItem());
                continue;
            }
            if(transaction.getTransactionType() == TransactionType.RETURNOVERTIMENOTPAID){
                moviesLateReturned.add((Movie)transaction.getRentableItem());
                continue;
            }
            if(transaction.getTransactionType() == TransactionType.RENTAMOVIEANDPAYWITHBONUSPOINTS){
                moviesPaidWithBonusPoints.add((Movie)transaction.getRentableItem());
                continue;
            }
            else {
                everythingElse.add((Movie)transaction.getRentableItem());
            }
        }

        StringBuilder output = new StringBuilder();
        output.append(parseRentResults(moviesRented));
        output.append(parsePaidWithBonusPoints(moviesPaidWithBonusPoints));
        output.append(parseLateReturns(moviesLateReturned));

        return output.toString();
    }

    @Override
    public String parseRentalResult(Movie movie){

        String output = movie.getTitle() + "(" + movie.getMovieType().getType() + ") " +
                movie.getRentOutTimeInDays() + " days " +
                (int)priceCalculatorService.calculateRentOutPrice(movie, movie.getRentOutTimeInDays()) + " EUR";
        return output;
    }

    @Override
    public String parseLateReturn(Movie movie){

        String output = movie.getTitle() + "(" + movie.getMovieType().getType() + ") " +
                timeService.calculateOvertimeInDays(movie) + " extra days " + (int)priceCalculatorService.calculateOvertimePrice(movie, timeService.calculateOvertimeInDays(movie)) + " EUR";

        return output;
    }

    @Override
    public String parseRentResults(Set<Movie> movies){
       StringBuilder output = new StringBuilder();

        if(movies.size() > 0){
            output.append(
                    "-------------------------------------------------------------\n" +
                            "Rent Results\n" +
                            "--------------------------------------------------------------\n\n");

        }

        int totalPrice = 0;
        for (Movie movie : movies){
            output.append(parseRentalResult(movie));
            output.append("\n");
            totalPrice = totalPrice + (int)priceCalculatorService.calculateRentOutPrice(movie, movie.getRentOutTimeInDays());
        }

        if(movies.size() > 0){
            output.append("Total price : " + totalPrice + " EUR");
        }

        return output.toString();
    }

    @Override
    public String parseLateReturns(Set<Movie> movies){
        StringBuilder output = new StringBuilder();

        if(movies.size() > 0){
            output.append(
                    "-------------------------------------------------------------\n" +
                            "Movies Returned Late\n" +
                            "--------------------------------------------------------------\n\n");

        }

        int totalLatePrice = 0;
        for (Movie movie : movies){
            if(timeService.calculateOvertimeInDays(movie)== 0) continue;
            output.append(parseLateReturn(movie));
            output.append("\n");
            totalLatePrice = totalLatePrice + (int) priceCalculatorService.calculateOvertimePrice(movie, timeService.calculateOvertimeInDays(movie));
        }

        if(movies.size() > 0){
            output.append("Total late charge: " + totalLatePrice + " EUR");
        }

        return output.toString();
    }

    @Override
    public String parseListOfMovies(Set<Movie> movies) {
        StringBuilder output = new StringBuilder();
        for (Movie movie : movies){
            output.append(parseMovie(movie));
            output.append("\n");
        }
        return output.toString();
    }

    @Override
    public String parseMovie(Movie movie){
        String output = "Title: "+ movie.getTitle() + " \n"+
                "type: "+ movie.getMovieType().getType() + "\n"+
                "state: "+ movie.getRentableState().getState() + "\n"+
                "startTime: "+ movie.getRentBeginTime() + "\n"+
                "endTime: "+ movie.getRentEndTime() + "\n"+
                "rentoutTime: "+ movie.getRentOutTimeInDays() + "\n"+
                "customer: "+ movie.getItemHolder()+ "\n";
        return output;
    }

    @Override
    public String parsePaidWithBonusPoints(Set<Movie> movies) {
        StringBuilder output = new StringBuilder();

        for (Movie movie : movies){
            output.append(parsePaidWithBonusPoints(movie));
            output.append("\n");
        }
        return output.toString();
    }

    private String parsePaidWithBonusPoints(Movie movie) {
        String output =
                "-------------------------------------------------------------\n" +
                        "Paid with Bonuspoints\n" +
                        "--------------------------------------------------------------\n\n" +
                       movie.getTitle() + "(" + movie.getMovieType().getType() + ") " + movie.getRentOutTimeInDays() +
                        "days (Paid with " + SimpleBonusPointsService.CHARGEABLE_BONUS_POINTS + ")\n" +
                        "Total price : 0 EUR";
        return output;
    }
}
