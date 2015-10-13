package ee.meriloo.services;


import ee.meriloo.items.Movie;
import ee.meriloo.items.MovieType;
import ee.meriloo.services.Interfaces.PriceCalculatorService;
import ee.meriloo.services.enums.PriceType;
import org.springframework.stereotype.Service;

/**
 * Created by Lauri on 10.10.2015.
 */
@Service
public class SimplePriceCalculatorService implements PriceCalculatorService {


    private static final int NEW_RELEASE_BASE_DAYS = 1;
    private static final int REGULAR_BASE_DAYS = 3;
    private static final int OLD_FILM_BASE_DAYS = 5;


    @Override
    public double calculateRentOutPrice(Movie movie, long days){
        MovieType movieType = movie.getMovieType();
        return calculatePrice(movieType, days);
    }

    @Override
    public double calculateOvertimePrice(Movie movie,long overtime){
        MovieType movieType = movie.getMovieType();
        return calculateOvertimePrice(movieType, overtime);
    }



    private double calculateOvertimePrice(MovieType movieType,long overtime){
        double price = 0;

        if (overtime < 1){
            return price;
        }

        if (movieType == MovieType.NEW_RELEASE){
            price = PriceType.PREMIUM_PRICE.getPrice() * overtime;
        }

        else {
            price = PriceType.BASIC_PRICE.getPrice() * overtime;
        }

        return price;

    }



    private double calculatePrice(MovieType movieType,long days){
        double price = 0;

        if (movieType == MovieType.NEW_RELEASE) {
            price = calculatePrice(PriceType.PREMIUM_PRICE, days, NEW_RELEASE_BASE_DAYS);
        }

        else if(movieType == MovieType.REGULAR){
            price = calculatePrice(PriceType.BASIC_PRICE, days, REGULAR_BASE_DAYS);
        }

        else if(movieType == MovieType.OLD_FILM){
            price = calculatePrice(PriceType.BASIC_PRICE, days, OLD_FILM_BASE_DAYS);
        }

        return price;

    }

    private double calculatePrice(PriceType priceType, long days, int baseDays){
        double price = 0;

        if(days > baseDays){
            price = priceType.getPrice()*(days - baseDays + 1);
        }
        else {
            price = priceType.getPrice();
        }

        return price;
    }









}
