package ee.meriloo.demo;


import ee.meriloo.items.Movie;
import ee.meriloo.service.ResultParserService;

import java.util.List;

/**
 * Created by Lauri on 09.10.2015.
 */
public class Hello {



    public static void main(String[] args){

        ResultParserService resultParser = new ResultParserService();
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
