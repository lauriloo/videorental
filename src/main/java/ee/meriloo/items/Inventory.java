package ee.meriloo.items;

import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Lauri on 13.10.2015.
 */
@Component
public class Inventory {

    private List<Movie> movies = new LinkedList<Movie>();




    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }


}
