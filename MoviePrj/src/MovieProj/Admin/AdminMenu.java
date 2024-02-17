package MovieProj.Admin;

import java.util.Scanner;

import static MovieProj.Login.Login.loginMenu;

public class AdminMenu {
    public static void showAdminMenu(Scanner sc) {
        boolean loggedIn = true;

        while (loggedIn) {
            System.out.println("=====  1.영화 리스트  2.영화 추가  3.영화 삭제  4.로그아웃  =====");
            System.out.print("선택: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    // 영화 리스트 보여주기
                    break;
                case 2:
                    // 영화 추가 기능
                    break;
                case 3:
                    // 영화 삭제 기능
                    break;
                case 4:
                    System.out.println("로그아웃되었습니다.");
                    loginMenu();
                    break;
                default:
                    System.out.println("잘못된 선택입니다. 다시 선택해주세요.");
            }
        }
    }
}
