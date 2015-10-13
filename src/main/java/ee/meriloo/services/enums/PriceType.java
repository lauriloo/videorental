package ee.meriloo.services.enums;

/**
 * Created by Lauri on 10.10.2015.
 */
public enum PriceType {

    BASIC_PRICE(3),
    PREMIUM_PRICE(4);

    private double price;

    PriceType(double price){
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
