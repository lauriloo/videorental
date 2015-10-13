package ee.meriloo.demo;

import ee.meriloo.items.Movie;
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
    public ParserService resultParser;
    @Autowired
    SimulatorService simulatorService;
    @Autowired
    InventoryService inventoryService;



    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        Main main = context.getBean(Main.class);
        //main.start();
        main.inventoryTest();
    }



    private void start() {
        System.out.println("hello!");

        List<Movie> movies;
        movies = simulatorService.generateMovieDB();
        System.out.println(resultParser.parseRentResults(movies));
        System.out.println();
        movies = simulatorService.generateMovieDB2();
        System.out.println(resultParser.parseLateReturns(movies));
    }

    private void inventoryTest(){
        ParserService parserService = new SimpleParserService();
        SimulatorService simulatorService = new SimulatorService();

        List<Movie> movies = null;

        movies = inventoryService.listAllMovies();
        System.out.println("Empty movie list: "+parserService.parseListOfMovies(movies));

        movies = simulatorService.generateMovieDB();
        System.out.println("Generated Movie DB1: \n"+parserService.parseListOfMovies(movies));


    }
}