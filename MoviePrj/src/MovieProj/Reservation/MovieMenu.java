package MovieProj.Reservation;

import java.util.Scanner;

import static MovieProj.Reservation.UserMenu.inputChoice;
import static MovieProj.Reservation.UserMenu.showUserMenu;

public class MovieMenu {
    public static void showMovieMenu(Scanner scanner, String id) {
        boolean loggedIn = true;

        while (loggedIn) {
            System.out.println("=====  1.영화 예매  2.예매 확인  3.예매 취소  4.예매 변경  5.뒤로가기  =====");
            int choice = inputChoice(scanner);

            switch (choice) {
                case 1:
                    MovieReservation.showMovieReservationMenu(scanner, id);
                    break;
                case 2:
                    ResCheck.resCheck(id);
                    break;
                case 3:
                    ResCancle.resCancle(id);
                    break;
                case 4:
                	//ResChange();
                case 5:
                	showUserMenu(scanner, id);
                default:
                    System.out.println("잘못된 선택입니다. 다시 선택해주세요.");
            }
        }
    }
}
