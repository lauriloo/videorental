package ee.meriloo.services.Interfaces;

import ee.meriloo.items.Movie;

/**
 * Created by Lauri on 12.10.2015.
 */
public interface TimeService {

    public long calculateOvertimeInDays(Movie movie);

    public long calculateRentTimeInDays(Movie movie);


}
