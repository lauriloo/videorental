package ee.meriloo.clients;

/**
 * Created by Lauri on 10.10.2015.
 */
public class Customer extends Person implements BonusPointable {

    private long bonusPoints;

    public Customer(String name){
        super(name);
        this.bonusPoints = 0;
    }

    @Override
    public long getBonusPoints() {
        return bonusPoints;
    }

    public void setBonusPoints(int bonusPoints) {
        this.bonusPoints = bonusPoints;
    }

    @Override
    public void addBonusPoints(long bonusPoints) {
        this.bonusPoints = this.bonusPoints + bonusPoints;
    }

    @Override
    public boolean useBonusPoints(long bonusPoints) {
        if (this.bonusPoints >= bonusPoints){
            this.bonusPoints = this.bonusPoints - bonusPoints;
            System.out.println("Cusomer: "+getName()+", bonus points used:  "+bonusPoints+", bonus points left: "+this.bonusPoints);
            return true;
        }
        System.out.println("Failed to use bonus points !!!");
        System.out.println("Cusomer: "+getName()+", bonus points charged:  "+bonusPoints+", bonus points: "+this.bonusPoints);
        return false;
    }


}
