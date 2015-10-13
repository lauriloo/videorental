package ee.meriloo.demo;


import ee.meriloo.items.Movie;
import ee.meriloo.services.SimpleParserService;

import java.util.List;

/**
 * Created by Lauri on 09.10.2015.
 */
public class Hello {



    public static void main(String[] args){

        SimpleParserService resultParser = new SimpleParserService();
        SimulatorService simulatorService = new SimulatorService();

        System.out.println("hello!");

        List<Movie> movies;
        movies = simulatorService.generateMovieDB();
        System.out.println(resultParser.parseRentResults(movies));
        System.out.println();
        movies = simulatorService.generateMovieDB2();
        System.out.println(resultParser.parseLateReturns(movies));


    }
}
