package MovieProj.Reservation;

import MovieProj.Login.Login;
import MovieProj.MyPage.MyPage;

import java.util.Scanner;

public class UserMenu {
    public static void showUserMenu(Scanner sc, String id) {
        boolean loggedIn = true;

        while (loggedIn) {
            System.out.println("=====  1.영화 메뉴  2.마이페이지  3.로그아웃  =====");
            int choice = inputChoice(sc);

            switch (choice) {
                case 1:
                    MovieMenu.showMovieMenu(sc, id);
                    break;
                case 2:
                    MyPage.showMyPageMenu(sc, id);
                    break;
                case 3:
                    System.out.println("로그아웃되었습니다.");
                    loggedIn = false;
                    Login.loginMenu();
                    break;
                default:
                    System.out.println("잘못된 선택입니다. 다시 선택해주세요.");
            }
        }
    }

    public static int inputChoice(Scanner sc) {
        int choice;
        while (true) {
            try {
                System.out.print("선택: ");
                choice = Integer.parseInt(sc.next());
                break;
            } catch (NumberFormatException e) {
                System.out.println("다시 선택해주세요.");
            }
        }
        return choice;
    }
}
