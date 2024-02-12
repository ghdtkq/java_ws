package moviePrj;

public class Movie {
	
	public String title;
	public int availableSeats;

    public Movie(String title, int availableSeats) {
        this.title = title;
        this.availableSeats = availableSeats;
    }

    public String getTitle() {
        return title;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void decreaseAvailableSeats(int numSeats) {
        availableSeats -= numSeats;
    }
}