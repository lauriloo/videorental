package ee.meriloo.demo;

import ee.meriloo.items.Movie;
import ee.meriloo.services.Interfaces.InventoryService;
import ee.meriloo.services.Interfaces.ParserService;
import ee.meriloo.services.InventoryServiceImpl;
import ee.meriloo.services.SimpleParserService;

import java.util.List;

/**
 * Created by Lauri on 12.10.2015.
 */
public class InventoryFunctionsTests {





    public static void main(String[] args){
        InventoryService inventoryService = new InventoryServiceImpl();
        ParserService parserService = new SimpleParserService();
        SimulatorService simulatorService = new SimulatorService();

        List<Movie> movies = null;

        movies = inventoryService.listAllMovies();
        System.out.println("Empty movie list: "+parserService.parseListOfMovies(movies));

        movies = simulatorService.generateMovieDB();
        System.out.println("Generated Movie DB1: \n"+parserService.parseListOfMovies(movies));


    }

    /*private void addAFilm(List<Movies> ){
        System.out.println("ADD A FILM: \n");

        Movie movie = new Movie("")

    }*/
}
