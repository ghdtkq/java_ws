package MovieProj.Login;

import MovieProj.Admin.AdminMenu;
import MovieProj.Reservation.UserMenu;
import MovieProj.MyPage.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Login {
    private static final String USERS_FILE = "users.txt";
    private static String loggedInNickname;
    private static String loggedInPassword;

    public static boolean login(Scanner sc) {
        System.out.print("아이디: ");
        String id = sc.next();
        System.out.print("비밀번호: ");
        String password = sc.next();

        try (BufferedReader br = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] userInfo = line.split(",");
                String existingId = userInfo[2].trim();
                String existingPassword = userInfo[3].trim();
                if (existingId.equals(id) && existingPassword.equals(password)) {
                    if (existingId.equals("admin")) {
                        System.out.println("관리자로 로그인");
                        AdminMenu.showAdminMenu(sc);
                    }
                    loggedInNickname = userInfo[4];
                    loggedInPassword = existingPassword;
                    System.out.println(userInfo[4] + "님 환영합니다.");
                    UserMenu.showUserMenu(sc, id);
                    return true; // 로그인 성공
                }
            }
        } catch (IOException e) {
            System.out.println("사용자 정보 파일을 읽는 중 오류가 발생했습니다.");
            e.printStackTrace();
        }

        System.out.println("아이디 또는 비밀번호가 일치하지 않습니다. 다시 시도해주세요.");
        return false; // 로그인 실패
    }

    public static void loginMenu() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("=====  1.로그인  2.회원가입  3.종료  =====");
            int choice = UserMenu.inputChoice(sc);

            switch (choice) {
                case 1:
                    login(sc);
                    break;
                case 2:
                    Register.register(sc);
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("잘못된 선택입니다. 다시 선택해주세요.");
            }
        }
    }

    public static String getLoggedInNickname() {
        return loggedInNickname;
    }

    public static void setLoggedInNickname(String nickname) {
        loggedInNickname = nickname;
    }

    public static String getLoggedInPassword() {
        return loggedInPassword;
    }

    public static void setLoggedInPassword(String newPassword) {
        loggedInPassword = newPassword;
    }
}