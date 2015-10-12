package ee.meriloo.items;

/**
 * Created by Lauri on 10.10.2015.
 */
public class Item {

    private static long count = 0;
    long id;

    Item(){
        this.id = ++count;
    }

    public long getId() {
        return id;
    }
}
