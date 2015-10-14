package ee.meriloo.items;

import org.omg.CORBA.UNKNOWN;

/**
 * Created by Lauri on 09.10.2015.
 */
public enum MovieType {
    NEW_RELEASE("New release"),
    REGULAR("Regular rental"),
    OLD_FILM("Old film"),
    UNKNOWN("N/A");

    String type;

    private MovieType(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
