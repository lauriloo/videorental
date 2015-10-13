package ee.meriloo.demo;

import ee.meriloo.items.Movie;
import ee.meriloo.services.Interfaces.ParserService;
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



    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        Main p = context.getBean(Main.class);
        p.start(args);
    }



    private void start(String[] args) {
        System.out.println("hello!");

        List<Movie> movies;
        movies = simulatorService.generateMovieDB();
        System.out.println(resultParser.parseRentResults(movies));
        System.out.println();
        movies = simulatorService.generateMovieDB2();
        System.out.println(resultParser.parseLateReturns(movies));
    }
}