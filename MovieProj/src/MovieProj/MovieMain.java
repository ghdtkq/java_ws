package MovieProj;
import MovieProj.Login.Register;
import MovieProj.Reservation.MovieReservation;

import static MovieProj.Login.Login.loginMenu;

public class MovieMain {
    public static void main(String[] args) {
        Register.createUserFileIfNotExists();
        MovieReservation.createMovieFileIfNotExists();
        loginMenu();
    }
}


