package ee.meriloo.items;

import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by Lauri on 13.10.2015.
 */
@Component
public class Inventory {

    private Set<Movie> movies = new HashSet<Movie>();


    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }
}
