package ee.meriloo.services;

import ee.meriloo.clients.Customer;
import ee.meriloo.items.Movie;
import ee.meriloo.items.MovieType;
import ee.meriloo.enums.RentableState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Lauri on 11.10.2015.
 */
@Service
public class SimulatorService {


    @Autowired
    SimpleTimeService timeService;

    public Set<Movie> generateMovieDB1(){
        Set<Movie> movieDB = new HashSet<Movie>();

        movieDB.add(generateMovie("Matrix", MovieType.NEW_RELEASE, 1));
        movieDB.add(generateMovie("Spider man", MovieType.REGULAR, 5));
        movieDB.add(generateMovie("Spider man 2", MovieType.REGULAR, 2));
        movieDB.add(generateMovie("Out of africa", MovieType.OLD_FILM, 7));

        return movieDB;
    }


    //OverTimeDB
    public Set<Movie> generateMovieDB2(){
        Set<Movie> movieDB = new HashSet<Movie>();

        Movie movie1 = generateMovie("Matrix", MovieType.NEW_RELEASE, 1);
        Movie movie2 = generateMovie("Spider man", MovieType.REGULAR, 5);
        Movie movie3 = generateMovie("Out of africa", MovieType.OLD_FILM, 7);

        overtimeGenerator(movie1, 2);
        overtimeGenerator(movie2, 1);
        overtimeGenerator(movie3, -3);

        movieDB.add(movie1);
        movieDB.add(movie2);
        movieDB.add(movie3);

        return movieDB;
    }

    public Set<Movie> generateMovieDB3(){
        Set<Movie> movieDB = new HashSet<Movie>();

        movieDB.add(generateMovie("Matrix", MovieType.NEW_RELEASE, new Customer("John"), RentableState.RENTED_OUT));
        movieDB.add(generateMovie("Spider man", MovieType.REGULAR));
        movieDB.add(generateMovie("Spider man 2", MovieType.REGULAR));
        movieDB.add(generateMovie("Out of africa", MovieType.OLD_FILM));

        return movieDB;
    }

    public Set<Movie> generateMovieDB4(){
        Set<Movie> movieDB = new HashSet<Movie>();

        movieDB.add(generateMovie("Matrix 11", MovieType.NEW_RELEASE));
        movieDB.add(generateMovie("Spider man", MovieType.REGULAR));
        movieDB.add(generateMovie("Spider man 2", MovieType.REGULAR));
        movieDB.add(generateMovie("Out of africa", MovieType.OLD_FILM));

        return movieDB;
    }



    private Movie generateMovie(String title, MovieType type, int daysFromNow){

        Movie movie = new Movie(title, type);

        Date date = new Date();
        movie.setRentBeginTime(date);
        movie.setRentOutTimeInDays(daysFromNow);

        return movie;
    }

    private Movie generateMovie(String title, MovieType type){
        Movie movie = new Movie(title, type);
        return movie;
    }

    private Movie generateMovie(String title, MovieType type, Customer customer){
        Movie movie = new Movie(title, type);
        movie.setItemHolder(customer);
        return movie;
    }

    private Movie generateMovie(String title, MovieType type, Customer customer, RentableState state){
        Movie movie = new Movie(title, type);
        movie.setItemHolder(customer);
        movie.setRentableState(state);
        return movie;
    }

    public Movie overtimeGenerator(Movie movie,int overtimeInDays){
        overtimeInDays = overtimeInDays + (int) movie.getRentOutTimeInDays();
        Date begin = new Date();
        Date end = timeService.daysAndHoursAdder(begin, overtimeInDays, -1);
        movie.setRentBeginTime(begin);
        movie.setRentEndTime(end);
        return movie;
    }


}
