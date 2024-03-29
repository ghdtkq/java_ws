package MovieProj.MyPage;

import MovieProj.Login.Login;

import java.util.*;
import java.io.*;

import static MovieProj.Login.Login.loginMenu;
import static MovieProj.Reservation.UserMenu.showUserMenu;

public class MyPage {
    private static final String USERS_FILE = "users.txt";

    public static void showMyPageMenu(Scanner sc, String id) {
        boolean loggedIn = true;

        while (loggedIn) {
            System.out.println("===== 1. 내 정보 변경 2. 회원탈퇴 3. 뒤로가기 =====");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    changeInformation(sc, id);
                    break;
                case 2:
                    withdrawMembership(sc);
                    loggedIn = false;
                    break;
                case 3:
                    showUserMenu(sc, id);
                    break;
                default:
                    System.out.println("잘못된 선택입니다.");
            }
        }
    }

    private static void changeInformation(Scanner sc, String id) {
        System.out.println("===== 1. 닉네임 변경 2. 비밀번호 변경 3. 뒤로가기=====");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                changeNickname(sc);
                break;
            case 2:
                changePassword(sc);
                break;
            case 3:
            	showMyPageMenu(sc, id);
            default:
                System.out.println("잘못된 선택입니다.");
        }
    }

    private static void changeNickname(Scanner sc) {
        String currentLoggedInNickname = Login.getLoggedInNickname();

        System.out.print("새 닉네임을 입력해주세요: ");
        String newNickname = sc.nextLine();

        if (isNicknameAvailable(newNickname)) { // 입력된 닉네임이 사용 가능한지 확인
            if (updateNickname(currentLoggedInNickname, newNickname)) { // 닉네임 업데이트
                System.out.println(newNickname + " 닉네임으로 변경하였습니다.");
                setNewNickname(newNickname); // 로그인 정보에 새로운 닉네임 설정
            } else {
                System.out.println("닉네임 변경에 실패하였습니다.");
            }
        } else {
            System.out.println("이미 존재하는 닉네임입니다. 다른 닉네임을 입력해주세요.");
        }
    }

    // 새로운 닉네임이 이미 존재하는지 확인하는 메소드
    private static boolean isNicknameAvailable(String newNickname) {
        try (BufferedReader br = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] userInfo = line.split(",");
                String existingNickname = userInfo[4].trim();
                if (existingNickname.equals(newNickname)) {
                    return false; // 이미 존재하는 닉네임이면 false 반환
                }
            }
        } catch (IOException e) {
            System.out.println("닉네임 확인에 오류가 발생하였습니다.");
        }
        return true; // 존재하지 않는 닉네임이면 true 반환
    }

    private static void changePassword(Scanner sc) {
        System.out.print("비밀번호를 입력해주세요: ");
        String currentPassword = sc.nextLine();

        if (!isPasswordCorrect(currentPassword)) {
            System.out.println("잘못 입력하였습니다.");
            return;
        }

        System.out.print("변경할 비밀번호를 입력해주세요: ");
        String newPassword = sc.nextLine();

        if (updatePassword(currentPassword, newPassword)) {
            System.out.println("비밀번호가 변경되었습니다.");
            setNewPassword(newPassword);
        } else {
            System.out.println("비밀번호 변경에 실패하셨습니다.");
        }
    }

    private static boolean isPasswordCorrect(String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] userInfo = line.split(",");
                String existingNickname = userInfo[4].trim();
                String existingPassword = userInfo[3].trim();

                if (existingNickname.equals(Login.getLoggedInNickname()) && existingPassword.equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("비밀번호 검증에 오류가 발생하였습니다.");
        }
        return false;
    }

    private static boolean updatePassword(String currentPassword, String newPassword) {
        try {
            StringBuilder updateContent = new StringBuilder();

            FileReader fr = new FileReader(USERS_FILE);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                String[] userInfo = line.split(",");
                String existingNickname = userInfo[4].trim();

                if (existingNickname.equals(Login.getLoggedInNickname())) {
                    userInfo[3] = newPassword;
                    line = String.join(",", userInfo);
                }

                updateContent.append(line).append('\n');
            }

            br.close();
            fr.close();

            FileWriter fw = new FileWriter(USERS_FILE);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(updateContent.toString());
            bw.close();
            fw.close();

            return true;
        } catch (IOException e) {
            System.out.println("비밀번호 변경에 실패하였습니다.");
            return false;
        }
    }

    private static void setNewPassword(String newPassword) {
        Login.setLoggedInPassword(newPassword);
    }

    private static void setNewNickname(String newNickname) {
        Login.setLoggedInNickname(newNickname);
    }

    private static boolean updateNickname(String currentNickname, String newNickname) {
        try {
            StringBuilder updateContent = new StringBuilder();

            FileReader fr = new FileReader(USERS_FILE);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                String[] userInfo = line.split(",");
                String existingNickname = userInfo[4].trim();

                if (existingNickname.equals(currentNickname)) {
                    userInfo[4] = newNickname;
                    line = String.join(",", userInfo);
                }

                updateContent.append(line).append('\n');
            }

            br.close();
            fr.close();

            FileWriter fw = new FileWriter(USERS_FILE);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(updateContent.toString());
            bw.close();
            fw.close();

            return true;
        } catch (IOException e) {
            System.out.println("닉네임 변경에 실패하였습니다.");
            return false;
        }
    }

    private static void withdrawMembership(Scanner sc) {
        
            System.out.print("비밀번호 확인: ");
            String inputPassword = sc.nextLine();
            String currentPassword = Login.getLoggedInPassword();

            if (inputPassword.equals(currentPassword)) {
                System.out.print("정말 회원탈퇴 하시겠습니까? (예/아니요): ");
                String confirmation = sc.nextLine();
                if (confirmation.equalsIgnoreCase("예")) {
                    String currentNickname = Login.getLoggedInNickname();
                    withdrawMembership(currentNickname);
                    loginMenu();
                } else {
                    System.out.println("회원탈퇴가 취소되었습니다.");
                }
            } else {
                System.out.println("비밀번호를 잘못 입력하셨습니다. 다시 시도해주세요.");
            }
        }

    private static void withdrawMembership(String currentNickname) {
        try {
            StringBuilder updateContent = new StringBuilder();

            FileReader fr = new FileReader(USERS_FILE);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                String[] userInfo = line.split(",");
                String existingNickname = userInfo[4].trim();

                if (!existingNickname.equals(currentNickname)) {
                    updateContent.append(line).append('\n');
                }
            }

            br.close();
            fr.close();

            FileWriter fw = new FileWriter(USERS_FILE);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(updateContent.toString());
            bw.close();
            fw.close();

            System.out.println("정상적으로 회원탈퇴가 완료되었습니다.");
            System.out.println("그동안 이용해주셔서 감사합니다.");
        } catch (IOException e) {
            System.out.println("회원탈퇴 오류입니다. 다시 시도해주세요.");
        }
    }
}