package ee.meriloo.services;

import ee.meriloo.items.Movie;
import ee.meriloo.services.Interfaces.PriceCalculatorService;
import ee.meriloo.services.Interfaces.ParserService;
import ee.meriloo.services.Interfaces.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        int totalPrice = 0;
        for (Movie movie : movies){
            output.append(parseRentalResult(movie));
            output.append("\n");
            totalPrice = totalPrice + (int)priceCalculatorService.calculateRentOutPrice(movie, movie.getRentOutTimeInDays());
        }
        output.append("Total price : " + totalPrice + " EUR");
        return output.toString();
    }

    @Override
    public String parseLateReturns(Set<Movie> movies){
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
}
