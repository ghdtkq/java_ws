package MovieProj.Login;

import java.io.*;
import java.util.Scanner;

public class Register {
    private static final String USERS_FILE = "users.txt";

    public static void register(Scanner sc) {
        System.out.print("이름: ");
        String name = sc.next();
        int age = inputAge(sc);
        sc.nextLine();

        String nickName;
        while (true) {
            System.out.print("닉네임: ");
            nickName = sc.nextLine();
            if (isNickNameExists(nickName)) {
                System.out.println("이미 사용중인 닉네임입니다. 다른 닉네임을 입력해주세요.");
            } else {
                break;
            }
        }

        String id;
        while (true) {
            System.out.print("아이디: ");
            id = sc.nextLine();
            if (isIdExists(id)) {
                System.out.println("이미 사용중인 아이디입니다. 다른 아이디를 입력해주세요.");
            } else {
                break;
            }
        }

        System.out.print("비밀번호: ");
        String password = sc.nextLine();



        try (BufferedWriter bw = new BufferedWriter(new FileWriter(USERS_FILE, true))) {
            bw.write(name + "," + age + "," + id + "," + password+","+nickName);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("사용자 정보를 파일에 추가하는 중 오류가 발생했습니다.");
            e.printStackTrace();
        }

        System.out.println("회원가입이 완료되었습니다.");
    }

    private static boolean isIdExists(String id) {
        boolean existId = false;
        try (BufferedReader br = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] userInfo = line.split(",");
                String existingId = userInfo[2].trim();
                if (existingId.equals(id.trim())) {
                    existId = true;
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("사용자 정보 파일을 읽는 중 오류가 발생했습니다.");
            e.printStackTrace();
        }
        return existId;
    }

    private static boolean isNickNameExists(String id) {
        boolean existNickName = false;
        try (BufferedReader br = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] userInfo = line.split(",");
                String existingNickName = userInfo[4].trim();
                if (existingNickName.equals(id.trim())) {
                    existNickName = true;
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("사용자 정보 파일을 읽는 중 오류가 발생했습니다.");
            e.printStackTrace();
        }
        return existNickName;
    }

    public static int inputAge(Scanner sc) {
        int age;
        while (true) {
            try {
                System.out.print("나이: ");
                age = Integer.parseInt(sc.next());
                break;
            } catch (NumberFormatException e) {
                System.out.println("정수만 입력 가능합니다.");
            }
        }
        return age;
    }




}
