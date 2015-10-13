package ee.meriloo.services.Interfaces;

import ee.meriloo.items.Movie;

/**
 * Created by Lauri on 12.10.2015.
 */
public interface PriceCalculatorService {

    public double calculateRentOutPrice(Movie movie, long days);

    public double calculateOvertimePrice(Movie movie,long overtime);

}
