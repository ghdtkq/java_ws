package MovieProj.MyPage;

import MovieProj.Admin.AdminMenu;
import MovieProj.Login.Login;

import java.util.*;
import java.io.*;
import java.nio.*;

import static MovieProj.Login.Login.loginMenu;

public class MyPage {
    private static final String USERS_FILE = "users.txt";

    public static void showMyPageMenu(Scanner sc) {
        boolean loggedIn = true;

        while (loggedIn) {
            System.out.println("=====  1.내 정보 변경  2.회원 탈퇴	  =====");
            System.out.print("메뉴를 선택해주세요: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    changeNickname(sc); //닉네임 변경
                    break;

                case 2:
                    deleteAccount(sc); // 회원탈퇴
                    loggedIn = false;
                    break;

                default:
                    System.out.println("잘못된 선택입니다.");
            }
        }
    }

    //닉네임 변경 메서드
    private static void changeNickname(Scanner sc) {
        System.out.print("현재 닉네임을 입력해주세요: ");
        String currentNickname = sc.nextLine();

        if (!isNicknameExists(currentNickname)) {
            System.out.println("닉네임이 존재하지 않습니다.");
            return;
        }

        System.out.print("새로운 닉네임을 입력해주세요: ");
        String newNickname = sc.nextLine();

        //updateNickname 메서드 호출
        if (updateNickname(currentNickname, newNickname)) {
            System.out.println("닉네임이 변경되었습니다: " + newNickname);
        } else {
            System.out.println("닉네임 변경에 실패하였습니다.");
        }
    }

    //닉네임이 맞는지 확인하는 메서드
    private static boolean isNicknameExists(String nickname) {
        boolean existNickname = false;
        try (BufferedReader br = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] userInfo = line.split(",");
                String existingNickname = userInfo[4].trim();
                if (existingNickname.equals(nickname.trim())) {
                    existNickname = true;
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("에러.");
            e.printStackTrace();
        }
        return existNickname;
    }


    private static boolean updateNickname(String currentNickname, String newNickname) {
        try {

            StringBuilder updateContent = new StringBuilder();

            File usersFile = new File(USERS_FILE);
            FileReader fr = new FileReader(usersFile);
            BufferedReader br = new BufferedReader(fr);

            String line;
            boolean nicknameUpdated = false;
            while ((line = br.readLine()) != null) {

                String[] userInfo = line.split(",");
                String existingNickname = userInfo[4].trim();

                if (existingNickname.equals(currentNickname)) {
                    userInfo[4] = newNickname;
                    line = String.join(",", userInfo);
                    nicknameUpdated = true;
                }

                updateContent.append(line).append('\n');
            }

            br.close();
            fr.close();

            if (nicknameUpdated) {
                FileWriter fw = new FileWriter(usersFile);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(updateContent.toString());
                bw.close();
                fw.close();
                return true;
            } else {
                System.out.println("닉네임 변경에 실패하였습니다.");
                return false;
            }
        } catch (IOException e) {
            System.out.println("닉네임 변경에 실패하였습니다.");
            e.printStackTrace();
            return false;
        }
    }

    public static void deleteAccount(Scanner sc) {
        System.out.print("삭제하실 회원님의 닉네임을 입력해주세요: ");
        String currentNickname = sc.nextLine();

        String sourceFilePath = "users.txt";

        try (
                BufferedReader reader = new BufferedReader(new FileReader(sourceFilePath));
                BufferedWriter writer = new BufferedWriter(new FileWriter(sourceFilePath))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line by comma
                String[] userInfo = line.split(",");
                // Check if the nickname is in the line
                if (userInfo[4].trim().equals(currentNickname)) {
                    // Modify the line to "1"
                    line = "1";
                }
                // Write the modified line to the temporary file
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }
}