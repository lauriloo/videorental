package ee.meriloo.service;

import ee.meriloo.clients.BonusPointable;
import ee.meriloo.items.Movie;
import ee.meriloo.items.MovieType;
import ee.meriloo.service.Interface.BonuspointsService;

/**
 * Created by Lauri on 11.10.2015.
 */
public class SimpleBonusPointsService implements BonuspointsService {

    private static final int DEFAULT_BONUS_POINTS = 1;
    private static final int NEW_RELEASE_BONUS_POINTS = 2;
    public static final int CHARGEABLE_BONUS_POINTS = 25;



    @Override
    public void addBonusPoints(BonusPointable customer, Movie movie) {
        long bonusPoints = calculateBonusPoints(movie.getMovieType());
        customer.addBonusPoints(bonusPoints);
    }

    @Override
    public boolean useBonusPoints(BonusPointable customer, Movie movie) {
        if(isBonusPointsChargeable(customer, movie)){
            customer.useBonusPoints(CHARGEABLE_BONUS_POINTS);
            return true;
        }
        return false;
    }

    @Override
    public boolean isBonusPointsChargeable(BonusPointable customer, Movie movie) {
        if(movie.getMovieType() == MovieType.NEW_RELEASE && customer.getBonusPoints() >= CHARGEABLE_BONUS_POINTS){
            return true;
        }
        return false;
    }


    private int calculateBonusPoints(MovieType movieType){
        int bonusPoints = DEFAULT_BONUS_POINTS;

        if (movieType == MovieType.NEW_RELEASE){
            bonusPoints = NEW_RELEASE_BONUS_POINTS;
        }

        return bonusPoints;

    }


}
