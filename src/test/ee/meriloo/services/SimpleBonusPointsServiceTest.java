package ee.meriloo.services;

import ee.meriloo.clients.Customer;
import ee.meriloo.items.Movie;
import ee.meriloo.items.MovieType;
import ee.meriloo.services.Interfaces.BonuspointsService;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Lauri on 15.10.2015.
 */
public class SimpleBonusPointsServiceTest {

    BonuspointsService bonusPointsService = new SimpleBonusPointsService();
    Movie movie = null;
    Customer customer = null;
    MovieType movieType = null;

    @Test
    public void testAddBonusPoints() throws Exception {

        movie = new Movie("Matrix", MovieType.NEW_RELEASE);
        customer = new Customer("Albert");
        bonusPointsService.addBonusPoints(customer,movie);
        assertEquals(2,customer.getBonusPoints());

        customer = new Customer("Albert");
        movie.setMovieType(MovieType.REGULAR);
        bonusPointsService.addBonusPoints(customer,movie);
        assertEquals(1,customer.getBonusPoints());

        customer = new Customer("Albert");
        movie.setMovieType(MovieType.OLD_FILM);
        bonusPointsService.addBonusPoints(customer,movie);
        assertEquals(1,customer.getBonusPoints());

        customer = new Customer("Albert");
        movie.setMovieType(MovieType.UNKNOWN);
        bonusPointsService.addBonusPoints(customer,movie);
        assertEquals(1,customer.getBonusPoints());

        movie.setMovieType(MovieType.NEW_RELEASE);
        bonusPointsService.addBonusPoints(customer,movie);
        assertEquals(3,customer.getBonusPoints());

        customer = new Customer("Albert");
        movie = null;
        bonusPointsService.addBonusPoints(customer,movie);
        assertEquals(0,customer.getBonusPoints());

        customer = null;
        movie = new Movie("Matrix", MovieType.NEW_RELEASE);;
        bonusPointsService.addBonusPoints(customer,movie);

        customer = null;
        movie = null;
        bonusPointsService.addBonusPoints(customer,movie);

        movie = new Movie("Matrix", null);
        customer = new Customer("Albert");
        bonusPointsService.addBonusPoints(customer,movie);
        assertEquals(1,customer.getBonusPoints());

    }

    @Test
    public void testUseBonusPoints() throws Exception {

        movie = null;
        customer = null;
        assertEquals(false, bonusPointsService.useBonusPoints(customer,movie));

        movie = new Movie("Matrix", MovieType.NEW_RELEASE);
        customer = null;
        assertEquals(false, bonusPointsService.useBonusPoints(customer,movie));

        movie = new Movie("Matrix", MovieType.NEW_RELEASE);
        customer = new Customer("Albert");
        customer.addBonusPoints(25);
        assertEquals(true, bonusPointsService.useBonusPoints(customer,movie));
        assertEquals(0, customer.getBonusPoints());

        movie = new Movie("Matrix", MovieType.NEW_RELEASE);
        customer = new Customer("Albert");
        customer.addBonusPoints(24);
        assertEquals(false, bonusPointsService.useBonusPoints(customer,movie));
        assertEquals(24, customer.getBonusPoints());

        movie = new Movie("Matrix", MovieType.NEW_RELEASE);
        customer = new Customer("Albert");
        customer.addBonusPoints(26);
        assertEquals(true, bonusPointsService.useBonusPoints(customer,movie));
        assertEquals(1, customer.getBonusPoints());

        movie = new Movie("Matrix", MovieType.NEW_RELEASE);
        customer = new Customer("Albert");
        assertEquals(false, bonusPointsService.useBonusPoints(customer,movie));
        assertEquals(0, customer.getBonusPoints());

        movie = new Movie("Matrix", MovieType.NEW_RELEASE);
        customer = new Customer("Albert");
        customer.addBonusPoints(-1);
        assertEquals(false, bonusPointsService.useBonusPoints(customer,movie));
        assertEquals(-1, customer.getBonusPoints());

        movie = new Movie("Matrix", MovieType.REGULAR);
        customer = new Customer("Albert");
        customer.addBonusPoints(25);
        assertEquals(false, bonusPointsService.useBonusPoints(customer,movie));
        assertEquals(25, customer.getBonusPoints());

        movie = new Movie("Matrix", MovieType.OLD_FILM);
        customer = new Customer("Albert");
        customer.addBonusPoints(25);
        assertEquals(false, bonusPointsService.useBonusPoints(customer,movie));
        assertEquals(25, customer.getBonusPoints());

        movie = new Movie("Matrix", MovieType.UNKNOWN);
        customer = new Customer("Albert");
        customer.addBonusPoints(25);
        assertEquals(false, bonusPointsService.useBonusPoints(customer,movie));
        assertEquals(25, customer.getBonusPoints());



    }

    @Test
    public void testIsBonusPointsChargeable() throws Exception {

        movie = null;
        customer = null;
        assertEquals(false, bonusPointsService.isBonusPointsChargeable(customer, movie));

        movie = new Movie("Matrix", MovieType.NEW_RELEASE);
        customer = new Customer("Albert");
        customer.addBonusPoints(25);
        assertEquals(true, bonusPointsService.isBonusPointsChargeable(customer,movie));

        movie = new Movie("Matrix", MovieType.NEW_RELEASE);
        customer = new Customer("Albert");
        customer.addBonusPoints(24);
        assertEquals(false, bonusPointsService.isBonusPointsChargeable(customer,movie));

        movie = new Movie("Matrix", MovieType.REGULAR);
        customer = new Customer("Albert");
        customer.addBonusPoints(25);
        assertEquals(false, bonusPointsService.isBonusPointsChargeable(customer,movie));

        movie = new Movie("Matrix", MovieType.OLD_FILM);
        customer = new Customer("Albert");
        customer.addBonusPoints(25);
        assertEquals(false, bonusPointsService.isBonusPointsChargeable(customer,movie));

        movie = new Movie("Matrix", MovieType.UNKNOWN);
        customer = new Customer("Albert");
        customer.addBonusPoints(25);
        assertEquals(false, bonusPointsService.isBonusPointsChargeable(customer,movie));

    }
}