package ee.meriloo.items;

/**
 * Created by Lauri on 09.10.2015.
 */
public enum MovieType {
    NEW_RELEASE("New release"),
    REGULAR("Regular rental"),
    OLD_FILM("Old film");

    String type;

    private MovieType(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
