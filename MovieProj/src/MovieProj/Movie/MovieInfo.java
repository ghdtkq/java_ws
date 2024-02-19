package MovieProj.Movie;

public class MovieInfo {
    private String theaterName;
    private String movieName;
    private String showTime;

    public MovieInfo(String theaterName, String movieName, String showTime) {
        this.theaterName = theaterName;
        this.movieName = movieName;
        this.showTime = showTime;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getShowTime() {
        return showTime;
    }
}
