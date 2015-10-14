package ee.meriloo.demo;

import ee.meriloo.items.Movie;
import ee.meriloo.items.MovieType;
import ee.meriloo.services.Interfaces.InventoryService;
import ee.meriloo.services.Interfaces.ParserService;
import ee.meriloo.services.InventoryServiceImpl;
import ee.meriloo.services.SimpleParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Lauri on 13.10.2015.
 */
@Component
public class Main {

    @Autowired
    public ParserService parserService;
    @Autowired
    SimulatorService simulatorService;
    @Autowired
    InventoryService inventoryService;



    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        Main main = context.getBean(Main.class);
        //main.start();
        //main.inventoryTest();
        main.addAMoviesToInventory();
        main.removeAMovieFromInventory();
    }



    private void start() {
        System.out.println("hello!");

        List<Movie> movies;
        movies = simulatorService.generateMovieDB();
        System.out.println(parserService.parseRentResults(movies));
        System.out.println();
        movies = simulatorService.generateMovieDB2();
        System.out.println(parserService.parseLateReturns(movies));
    }

    private void inventoryTest(){

        List<Movie> movies = null;
        movies = inventoryService.getAllMovies();
        System.out.println("Empty movie list: "+parserService.parseListOfMovies(movies));

        movies = simulatorService.generateMovieDB();
        System.out.println("Generated Movie DB1: \n"+parserService.parseListOfMovies(movies));


    }

    private void addAMoviesToInventory(){
        Movie movie = new Movie("Matrix 11", MovieType.NEW_RELEASE);
        List<Movie> movies = inventoryService.getAllMovies();
        System.out.println("Total number of movies: " + movies.size());
        System.out.println("Adding a movie \"" + movie.getTitle() + "\"");
        inventoryService.addMovie(movie);
        System.out.println("Total number of movies: " + movies.size());

    }

    private void removeAMovieFromInventory(){
        Movie movie = new Movie("Matrix 11", MovieType.NEW_RELEASE);
        List<Movie> movies = inventoryService.getAllMovies();
        inventoryService.addMovie(movie);
        System.out.println("Total number of movies: " + movies.size());
        System.out.println("Removing a movie \"" + movie.getTitle() + "\"");
        inventoryService.removeMovie(movie);
        System.out.println("Total number of movies: " + movies.size());
    }

}