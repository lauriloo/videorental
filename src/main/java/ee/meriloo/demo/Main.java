package ee.meriloo.demo;

import ee.meriloo.clients.Customer;
import ee.meriloo.items.Movie;
import ee.meriloo.items.MovieType;
import ee.meriloo.services.Interfaces.*;
import ee.meriloo.services.InventoryServiceImpl;
import ee.meriloo.services.SimpleParserService;
import ee.meriloo.transaction.RentalSession;
import ee.meriloo.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * Created by Lauri on 13.10.2015.
 */
@Component
public class Main {


    @Autowired
    BonuspointsService bonuspointsService;
    @Autowired
    InventoryService inventoryService;
    @Autowired
    MovieRentalService movieRentalService;
    @Autowired
    ParserService parserService;
    @Autowired
    PriceCalculatorService priceCalculatorService;
    @Autowired
    TimeService timeService;
    @Autowired
    SimulatorService simulatorService;



    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        Main main = context.getBean(Main.class);
        //main.start();
        //main.inventoryTest();



        //main.addAMoviesToInventory();
        //main.removeAMovieFromInventory();
        //main.changeType();
        //main.listAllFilms();
        //main.listAllFilmsInStore();
        //main.rentOutAndcalculatePriceOfRental();
        main.returningFilmsLate();
    }



    /*private void start() {
        System.out.println("hello!");

        Set<Movie> movies;
        movies = simulatorService.generateMovieDB();
        System.out.println(parserService.parseRentResults(movies));
        System.out.println();
        movies = simulatorService.generateMovieDB2();
        System.out.println(parserService.parseLateReturns(movies));
    }*/

    /*private void inventoryTest(){
        System.out.println();
        System.out.println("-------------------------------------------------------------");
        System.out.println("Inventory Test...");
        System.out.println("--------------------------------------------------------------");
        System.out.println();

        Set<Movie> movies = null;
        movies = inventoryService.getAllMovies();
        System.out.println("Empty movie list: "+parserService.parseListOfMovies(movies));

        movies = simulatorService.generateMovieDB();
        System.out.println("Generated Movie DB1: \n" + parserService.parseListOfMovies(movies));


    }*/

    private void addAMoviesToInventory(){
        System.out.println();
        System.out.println("-------------------------------------------------------------");
        System.out.println("Add a film");
        System.out.println("--------------------------------------------------------------");
        System.out.println();
        inventoryService.resetInventory();

        Movie movie = new Movie("Matrix 11", MovieType.NEW_RELEASE);
        System.out.println("* Created new movie: \"" + movie.getTitle() + "\"");

        Set<Movie> movies = inventoryService.getAllMovies();
        System.out.println("* Total number of movies before adding a movie: " + movies.size());

        inventoryService.addMovie(movie);
        System.out.println("* Added movie to inventory");
        System.out.println("* Total number of movies after adding a movie: " + movies.size());
    }

    private void removeAMovieFromInventory(){
        System.out.println();
        System.out.println("-------------------------------------------------------------");
        System.out.println("Remove a film");
        System.out.println("--------------------------------------------------------------");
        System.out.println();
        inventoryService.resetInventory();

        Movie movie = new Movie("Matrix 11", MovieType.NEW_RELEASE);
        Set<Movie> movies = inventoryService.getAllMovies();
        inventoryService.addMovie(movie);
        System.out.println("* Total number of movies before removing a movie: " + movies.size());

        inventoryService.removeMovie(movie);
        System.out.println("* Removed movie from inventory");
        System.out.println("* Total number of movies after removing a movie: " + movies.size());
    }

    private void changeType(){
        System.out.println();
        System.out.println("-------------------------------------------------------------");
        System.out.println("Change the type of a film");
        System.out.println("--------------------------------------------------------------");
        System.out.println();
        inventoryService.resetInventory();

        Set<Movie> movies = inventoryService.getAllMovies();
        Movie inventoryMovie = new Movie("Matrix 11", MovieType.NEW_RELEASE);
        inventoryService.addMovie(inventoryMovie);
        System.out.println("* Created a movie and added it to inventory. Title: \"" + inventoryMovie.getTitle() + "\" " +
                ", type: " + inventoryMovie.getMovieType().getType());
        System.out.println("* Total number of movies in store: " + movies.size());

        inventoryService.changeMovieTypeInInventory(inventoryMovie, MovieType.OLD_FILM);
        System.out.println("* Changed movie type in Inventory to OldFilm. Title: \"" + inventoryMovie.getTitle() + "\" " +
                ", type: " + inventoryMovie.getMovieType().getType());

    }

    private void listAllFilms(){
        System.out.println();
        System.out.println("-------------------------------------------------------------");
        System.out.println("List all films");
        System.out.println("--------------------------------------------------------------");
        System.out.println();
        inventoryService.resetInventory();

        Set<Movie> movieDB = simulatorService.generateMovieDB3();
        inventoryService.setInventory(movieDB);
        System.out.println("* Generated new inventory (DB3)");

        Set<Movie> inventoryDB = inventoryService.getAllMovies();
        System.out.println("* List all movies: \n\n" + parserService.parseListOfMovies(inventoryDB));

    }

    private void listAllFilmsInStore(){
        System.out.println();
        System.out.println("-------------------------------------------------------------");
        System.out.println("List all films in store (e.g. not rented at the moment)");
        System.out.println("--------------------------------------------------------------");
        System.out.println();
        inventoryService.resetInventory();

        Set<Movie> movieDB = simulatorService.generateMovieDB3();
        inventoryService.setInventory(movieDB);
        System.out.println("* Generated new inventory (DB3)");

        Set<Movie> inStoreMovies = inventoryService.getAllMoviesInStore();
        System.out.println("* List all films in store: \n\n" + parserService.parseListOfMovies(inStoreMovies));
    }

    private void rentOutAndcalculatePriceOfRental(){
        System.out.println();
        System.out.println("-------------------------------------------------------------");
        System.out.println("Rent out 4 movies and calculate the price");
        System.out.println("--------------------------------------------------------------");
        System.out.println();
        inventoryService.resetInventory();

        Set<Movie> movieDB = simulatorService.generateMovieDB4();
        inventoryService.setInventory(movieDB);
        System.out.println("* Generated new inventory (DB4)");

        Set<Movie> inStoreMovies = inventoryService.getAllMoviesInStore();
        System.out.println("* List all films in store: \n\n" + parserService.parseListOfMovies(inStoreMovies));

        Customer customer = new Customer("Mary");
        Movie movie1 = new Movie("Matrix 11", MovieType.UNKNOWN);
        Movie movie2 = new Movie("Spider man", MovieType.UNKNOWN);
        Movie movie3 = new Movie("Spider man 2", MovieType.UNKNOWN);
        Movie movie4 = new Movie("Out of africa", MovieType.UNKNOWN);
        RentalSession rentalSession = movieRentalService.beginRentalSession();
        movieRentalService.rentAMovie(inventoryService.getMovieFromInventory(movie1), customer, 1, rentalSession);
        movieRentalService.rentAMovie(inventoryService.getMovieFromInventory(movie2), customer, 5, rentalSession);
        movieRentalService.rentAMovie(inventoryService.getMovieFromInventory(movie3), customer, 2, rentalSession);
        movieRentalService.rentAMovie(inventoryService.getMovieFromInventory(movie4), customer, 7, rentalSession);
        movieRentalService.finishRentalSession(rentalSession);

        String output = parserService.parseRentalSession(rentalSession);
        System.out.println(output);

    }

    private void returningFilmsLate(){
        System.out.println();
        System.out.println("-------------------------------------------------------------");
        System.out.println("Returning Films Late");
        System.out.println("--------------------------------------------------------------");
        System.out.println();
        inventoryService.resetInventory();

        Set<Movie> movieDB = simulatorService.generateMovieDB4();
        inventoryService.setInventory(movieDB);
        System.out.println("* Generated new inventory (DB4)");

        Set<Movie> inStoreMovies = inventoryService.getAllMoviesInStore();
        System.out.println("* List all films in store: \n\n" + parserService.parseListOfMovies(inStoreMovies));

        Customer customer = new Customer("Mary");
        Movie movie1 = new Movie("Matrix 11", MovieType.UNKNOWN);
        Movie movie2 = new Movie("Spider man", MovieType.UNKNOWN);
        //Movie movie3 = new Movie("Spider man 2", MovieType.UNKNOWN);
        //Movie movie4 = new Movie("Out of africa", MovieType.UNKNOWN);
        RentalSession rentalSession = movieRentalService.beginRentalSession();
        movie1 = inventoryService.getMovieFromInventory(movie1);
        movie2 = inventoryService.getMovieFromInventory(movie2);
        movieRentalService.rentAMovie(movie1, customer, 1, rentalSession);
        movieRentalService.rentAMovie(movie2, customer, 5, rentalSession);
        //movieRentalService.rentAMovie(inventoryService.getMovieFromInventory(movie3), customer, 2, rentalSession);
        //movieRentalService.rentAMovie(inventoryService.getMovieFromInventory(movie4), customer, 7, rentalSession);
        movieRentalService.finishRentalSession(rentalSession);

        rentalSession = movieRentalService.beginRentalSession();
        simulatorService.overtimeGenerator(movie1, 2);
        simulatorService.overtimeGenerator(movie2, 1);
        movieRentalService.returnAMovie(movie1, rentalSession);
        movieRentalService.returnAMovie(movie2, rentalSession);
        movieRentalService.finishRentalSession(rentalSession);

        String output = parserService.parseRentalSession(rentalSession);
        System.out.println(output);
    }

    private void rentingWithBonusPoints(){
        System.out.println();
        System.out.println("-------------------------------------------------------------");
        System.out.println("Renting With Bonus Points");
        System.out.println("--------------------------------------------------------------");
        System.out.println();
        inventoryService.resetInventory();

        
    }

    private void keepTrackOfBonusPoints(){
        System.out.println();
        System.out.println("-------------------------------------------------------------");
        System.out.println("Keep Track Of Bonus Points");
        System.out.println("--------------------------------------------------------------");
        System.out.println();
        inventoryService.resetInventory();

    }

}