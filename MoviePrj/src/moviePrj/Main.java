package moviePrj;

import java.util.Scanner;

public class Main {
	
    public static void main(String[] args) {
    	MovieReservationSystem reservationSystem = new MovieReservationSystem();
        
    	reservationSystem.addMovie("아바타", 30);
    	reservationSystem.addMovie("트랜스포머", 20);
    	
    	reservationSystem.start();
    }
}