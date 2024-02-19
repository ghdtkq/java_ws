package MovieProj.Admin;

import MovieProj.Reservation.UserMenu;

import java.util.Scanner;

import static MovieProj.Login.Login.loginMenu;

public class AdminMenu {
    public static void showAdminMenu(Scanner sc) {
        boolean loggedIn = true;

        while (loggedIn) {
            System.out.println("=====  1.예매 내역 전체 보기  2.로그아웃  =====");
            int choice = UserMenu.inputChoice(sc);

            switch (choice) {
                case 1:
                    ResList.showResList();
                    break;
                case 2:
                    System.out.println("로그아웃되었습니다.");
                    loginMenu();
                    break;
                default:
                    System.out.println("잘못된 선택입니다. 다시 선택해주세요.");
            }
        }
    }
}
