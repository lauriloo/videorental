package ee.meriloo.items;

/**
 * Created by Lauri on 09.10.2015.
 */
public class Movie extends RentableItem {

    private String title;
    private MovieType movieType;
    private int releaseYear;

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

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Override
    public boolean equals(Object o){
        if(o == null)                return false;
        if(!(o instanceof Movie)) return false;

        Movie other = (Movie) o;
        if(! this.title.equals(other.title)) return false;
        if(this.releaseYear != other.releaseYear)      return false;

        return true;
    }

    @Override
    public int hashCode(){
        return (int) title.hashCode()
                 * releaseYear
                    ;
    }
}
