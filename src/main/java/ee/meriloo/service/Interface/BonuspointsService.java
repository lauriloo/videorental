package ee.meriloo.service.Interface;

import ee.meriloo.clients.BonusPointable;
import ee.meriloo.items.Movie;

/**
 * Created by Lauri on 12.10.2015.
 */
public interface BonuspointsService {

    public void addBonusPoints(BonusPointable customer, Movie movie);

    public boolean useBonusPoints(BonusPointable customer, Movie movie);

    public boolean isBonusPointsChargeable(BonusPointable customer, Movie movie);


}
