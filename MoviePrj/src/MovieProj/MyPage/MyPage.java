package MovieProj.MyPage;

import MovieProj.Admin.AdminMenu;
import MovieProj.Login.Login;

import java.util.*;
import java.io.*;
import java.nio.*;
import java.nio.file.*;

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
                	//닉네임 변경
                    changeNickname(sc); 
                    loginMenu();
                    break;
                case 2:
                	//회원탈퇴
                	String loggedInNickname = Login.getLoggedInNickname();
                	withdrawMembership(loggedInNickname);
                	loggedIn = false;
                    loginMenu();
                    break;
                default:
                    System.out.println("잘못된 선택입니다.");
            }
        }
    }

    //닉네임 변경
    private static void changeNickname(Scanner sc) {
        System.out.print("현재 닉네임을 입력해주세요: ");
        String currentNickname = sc.nextLine();

        if (!isNicknameExists(currentNickname)) {
            System.out.println("닉네임이 존재하지 않습니다.");
            return;
        }

        System.out.print("새로운 닉네임을 입력해주세요: ");
        String newNickname = sc.nextLine();

        
        if (updateNickname(currentNickname, newNickname)) {
            System.out.println("닉네임이 변경되었습니다: " + newNickname);
        } else {
            System.out.println("닉네임 변경에 실패하였습니다.");
        }
    }

    //닉네임 일치 여부
    private static boolean isNicknameExists(String nickname) {
        try (BufferedReader br = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] userInfo = line.split(",");
                String existingNickname = userInfo[4].trim();
                if (existingNickname.equals(nickname)) {
                    return true; // 닉네임 일치
                }
            }
        } catch (IOException e) {
            System.out.println("닉네임이 일치하지 않습니다.");
        }
        return false; // 닉네임 불일치
    }


    private static boolean updateNickname(String currentNickname, String newNickname) {
        try {
            StringBuilder updateContent = new StringBuilder();

            File usersFile = new File(USERS_FILE);
            FileReader fr = new FileReader(usersFile);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                String[] userInfo = line.split(",");
                String existingNickname = userInfo[4].trim();

                // 해당 라인이 현재 사용자 닉네임과 일치하는지 확인
                if (existingNickname.equals(currentNickname)) {
                    // 새로운 닉네임으로 변경
                    userInfo[4] = newNickname;
                    line = String.join(",", userInfo);
                }

                // 변경 내용에 라인 추가
                updateContent.append(line).append('\n');
            }

            br.close();
            fr.close();

            // 파일에 변경 내용 쓰기
            FileWriter fw = new FileWriter(usersFile);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(updateContent.toString());
            bw.close();
            fw.close();

            return true; // 닉네임 변경 성공
        } catch (IOException e) {
            System.out.println("닉네임 변경 실패..");
            return false; // 변경 실패
        }
    }
    
    //회원탈퇴
    private static void withdrawMembership(String nickname) {
        try {
            File usersFile = new File(USERS_FILE);
            FileReader fr = new FileReader(usersFile);
            BufferedReader br = new BufferedReader(fr);

            StringBuilder updateContent = new StringBuilder();
            String line;
            boolean foundUser = false;

            
            while ((line = br.readLine()) != null) {
                // 라인을 사용자 정보로 분할
                String[] userInfo = line.split(",");
                String existingNickname = userInfo[4].trim();

                // 라인이 로그인된 사용자와 일치하는지 확인
                if (existingNickname.equals(nickname)) {
                    foundUser = true; // 사용자 찾기
                    continue; // 라인 스킵
                }

                updateContent.append(line).append('\n');
            }

            br.close();
            fr.close();

            if (!foundUser) {
                System.out.println("회원 조회가 불가능합니다.");
                return;
            }

            FileWriter fw = new FileWriter(usersFile);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(updateContent.toString());
            bw.close();
            fw.close();

            System.out.println(nickname + " 님의 회원탈퇴가 정상적으로 처리되었습니다.");
        } catch (IOException e) {
            System.out.println("오류발생! 다시 시도해주세요.");
        }
    }
    }