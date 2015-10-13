package ee.meriloo.services;

import ee.meriloo.items.Movie;
import ee.meriloo.services.Interfaces.TimeService;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Lauri on 11.10.2015.
 */
public class SimpleTimeService implements TimeService {




    @Override
    public long calculateOvertimeInDays(Movie movie) {
        long overtime = 0;
        long paidRentOutTime = movie.getRentOutTimeInDays();
        long actualRentOutTime = calculateRentTimeInDays(movie);

        if(paidRentOutTime < actualRentOutTime){
            overtime = actualRentOutTime - paidRentOutTime;
        }
        return overtime;
    }



    public long calculateRentTimeInDays(Movie movie){
        return calculateDays(movie.getRentBeginTime(), movie.getRentEndTime());

    }

    public Date daysAndHoursAdder(Date date, int days, int sec){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        cal.add(Calendar.SECOND, sec);
        return cal.getTime();
    }


    /*private long calculateDays(Date rentOutTime) {
        long diffInDays = ( ((new Date()).getTime() - rentOutTime.getTime())
                / (1000 * 60 * 60 * 24));
        return ++diffInDays;
    }*/

    private long calculateDays(Date beginingTime, Date endTime) {
        long diffInDays = ( (endTime.getTime() - beginingTime.getTime())
                / (1000 * 60 * 60 * 24));
        return ++diffInDays;
    }
}
