package MovieProj.MyPage;

import MovieProj.Admin.AdminMenu;
import MovieProj.Login.Login;

import java.util.*;
import java.io.*;
import java.nio.*;

import static MovieProj.Login.Login.loginMenu;

public class MyPage {
    private static final String USERS_FILE = "users.txt";

    public static void showMyPageMenu(Scanner sc, String loggedInUserName) {
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
				deleteAccount(sc, loggedInUserName); // 회원탈퇴
                    loggedIn = false;
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

    //닉네임이 맞는지 확인
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

    //회원 탈퇴
    private static void deleteAccount(Scanner sc, String loggedInUserName) {
    	isNicknameExists(loggedInUserName);
    	
        System.out.println("Are you sure you want to cancel your membership? (yes/no)");
        String answer = sc.nextLine();

        if (answer.equalsIgnoreCase("yes")) {
            // Delete the user's information from the file
            try {
                File inputFile = new File(USERS_FILE);
                File tempFile = new File("temp.txt");

                Scanner fileScanner = new Scanner(inputFile);
                FileWriter writer = new FileWriter(tempFile);

                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    String[] parts = line.split(",");
                    // Assuming each line contains user information in the format: username,nickname,email
                    // Check if the username matches the currently logged-in user
                    // and do not write it to the temp file
                    if (!parts[0].equals(loggedInUserName)) {
                        writer.write(line + System.lineSeparator());
                    }
                }

                fileScanner.close();
                writer.close();

                // Delete the original file
                try {
                    if (!inputFile.delete()) {
                        System.out.println("Error: Failed to delete the original file. Possible reasons:");
                        System.out.println("- The file is in use by another process.");
                        System.out.println("- Insufficient permissions to delete the file.");
                        System.out.println("- The file does not exist.");
                        return;
                    }
                } catch (SecurityException e) {
                    System.out.println("Error: Security exception occurred. Insufficient permissions to delete the file.");
                    return;
                } catch (Exception e) {
                    System.out.println("Error: Failed to delete the original file due to an unexpected error: " + e.getMessage());
                    return;
                }

                // Rename the temp file to the original filename
                if (!tempFile.renameTo(inputFile)) {
                    System.out.println("Error: Failed to rename the temp file.");
                }

                System.out.println("Membership canceled successfully.");
            } catch (FileNotFoundException e) {
                System.out.println("Error: Users file not found.");
            } catch (IOException e) {
                System.out.println("Error: IO exception occurred.");
            }
        } else {
            System.out.println("Membership cancellation aborted.");
        }
    }

}