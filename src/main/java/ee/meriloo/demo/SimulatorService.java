package ee.meriloo.demo;

import ee.meriloo.items.Movie;
import ee.meriloo.items.MovieType;
import ee.meriloo.services.SimpleTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Lauri on 11.10.2015.
 */
@Component
public class SimulatorService {


    @Autowired
    SimpleTimeService timeService;

    public List<Movie> generateMovieDB(){
        List<Movie> movieDB = new LinkedList<Movie>();

        movieDB.add(generateMovie("Matrix", MovieType.NEW_RELEASE, 1));
        movieDB.add(generateMovie("Spider man", MovieType.REGULAR, 5));
        movieDB.add(generateMovie("Spider man 2", MovieType.REGULAR, 2));
        movieDB.add(generateMovie("Put of Africa", MovieType.OLD_FILM, 7));

        return movieDB;
    }


    //OverTimeDB
    public List<Movie> generateMovieDB2(){
        List<Movie> movieDB = new LinkedList<Movie>();

        Movie movie1 = generateMovie("Matrix", MovieType.NEW_RELEASE, 1);
        Movie movie2 = generateMovie("Spider man", MovieType.REGULAR, 5);
        Movie movie3 = generateMovie("Put of Africa", MovieType.OLD_FILM, 7);

        overtimeGenerator(movie1, 2);
        overtimeGenerator(movie2, 1);
        overtimeGenerator(movie3, -3);

        movieDB.add(movie1);
        movieDB.add(movie2);
        movieDB.add(movie3);

        return movieDB;
    }



    private Movie generateMovie(String title, MovieType type, int daysFromNow){

        Movie movie = new Movie(title, type);

        Date date = new Date();
        //date = movieRentalService.daysAndHoursAdder(date, -daysFromNow, 10);
        movie.setRentBeginTime(date);
        //movie.setRentEndTime(date);
        movie.setRentOutTimeInDays(daysFromNow);

        return movie;
    }

    private Movie overtimeGenerator(Movie movie,int overtimeInDays){
        overtimeInDays = overtimeInDays + (int) movie.getRentOutTimeInDays();
        Date begin = new Date();
        Date end = timeService.daysAndHoursAdder(begin, overtimeInDays, -1);
        //movie.setRentOutTimeInDays(0);
        movie.setRentBeginTime(begin);
        movie.setRentEndTime(end);
        return movie;
    }


}
