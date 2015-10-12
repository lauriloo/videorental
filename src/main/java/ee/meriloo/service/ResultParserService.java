package ee.meriloo.service;

import ee.meriloo.items.Movie;
import ee.meriloo.service.Interface.PriceCalculatorService;
import ee.meriloo.service.Interface.TimeService;

import java.util.List;

/**
 * Created by Lauri on 11.10.2015.
 */
public class ResultParserService {

    private PriceCalculatorService priceCalculatorService = new SimplePriceCalculatorService();
    private TimeService timeService = new SimpleTimeService();

    public String parseRentalResult(Movie movie){

        String output = movie.getTitle() + "(" + movie.getMovieType().getType() + ") " +
                movie.getRentOutTimeInDays() + " days " +
                (int)priceCalculatorService.calculateRentOutPrice(movie, movie.getRentOutTimeInDays()) + " EUR";
        return output;
    }

    public String parseLateReturn(Movie movie){

        String output = movie.getTitle() + "(" + movie.getMovieType().getType() + ") " +
                timeService.calculateOvertimeInDays(movie) + " extra days " + (int)priceCalculatorService.calculateOvertimePrice(movie, timeService.calculateOvertimeInDays(movie)) + " EUR";

        return output;
    }

    public String parseRentResults(List<Movie> movies){
       StringBuilder output = new StringBuilder();
        int totalPrice = 0;
        for (Movie movie : movies){
            output.append(parseRentalResult(movie));
            output.append("\n");
            totalPrice = totalPrice + (int)priceCalculatorService.calculateRentOutPrice(movie, movie.getRentOutTimeInDays());
        }
        output.append("Total price : " + totalPrice + " EUR");
        return output.toString();
    }

    public String parseLateReturns(List<Movie> movies){
        StringBuilder output = new StringBuilder();
        int totalLatePrice = 0;
        for (Movie movie : movies){
            if(timeService.calculateOvertimeInDays(movie)== 0) continue;
            output.append(parseLateReturn(movie));
            output.append("\n");
            totalLatePrice = totalLatePrice + (int) priceCalculatorService.calculateOvertimePrice(movie, timeService.calculateOvertimeInDays(movie));
        }
        output.append("Total late charge: " + totalLatePrice + " EUR");
        return output.toString();
    }
}
