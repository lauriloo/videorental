package ee.meriloo.items;

/**
 * Created by Lauri on 09.10.2015.
 */
public class Movie extends RentableItem {

    private String title;
    private MovieType movieType;

    public Movie(String title, MovieType movieType){
        super();
        this.title = title;
        this.movieType = movieType;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MovieType getMovieType() {
        return movieType;
    }

    public void setMovieType(MovieType movieType) {
        this.movieType = movieType;
    }
}
