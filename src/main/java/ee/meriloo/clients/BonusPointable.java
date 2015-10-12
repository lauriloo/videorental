package ee.meriloo.clients;

/**
 * Created by Lauri on 12.10.2015.
 */
public interface BonusPointable {

    public void addBonusPoints(long bonusPoints);

    public boolean useBonusPoints(long bonusPoints);

    public long getBonusPoints();
}
