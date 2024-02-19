package MovieProj.Reservation;

import java.io.*;
import java.util.Scanner;

public class MovieReservation {
    // 예약된 좌석 정보를 저장하는 파일 경로
    private static final String MOVIES_FILE_PATH = "movies.txt";

    // 영화 예매를 선택했을 때 나올 선택지 구현
    public static void showMovieReservationMenu(Scanner sc, String id) {
        //id 변수를 Login -> UserMenu -> MovieMenu 에서 지금 파일로 매개변수를 통해 계속 전달.

        String theater = "";
        String movieName = "";
        String date = "";
        String showTime = "";
        int people;
        String seats;

        // 1. 영화관 선택
        while (true) {
            System.out.println("원하시는 영화관을 선택하세요 1. CGV홍대점  2. CGV강남점  3. CGV구로점 ");
            System.out.print("입력란: ");
            int num1 = sc.nextInt();
            if (num1 == 1) {
                theater = "CGV홍대점";
                break;
            } else if (num1 == 2) {
                theater = "CGV강남점";
                break;
            } else if (num1 == 3) {
                theater = "CGV구로점";
                break;
            } else {
                System.out.println("잘못된 번호입니다 다시 선택해주세요.");
                System.out.println();
            }
        }

        // 2. 영화 선택
        while (true) {
            System.out.println();
            System.out.println("관람하고 싶으신 영화를 선택하세요 1. 웡카 2.귀멸의칼날: 인연의 기적 3. 시민 덕희");
            System.out.print("입력란: ");
            int num2 = sc.nextInt();
            if (num2 == 1) {
                movieName = "웡카";
                break;
            } else if (num2 == 2) {
                movieName = "귀멸의칼날: 인연의 기적";
                break;
            } else if (num2 == 3) {
                movieName = "시민 덕희";
                break;
            } else {
                System.out.println("잘못된 번호입니다 다시 선택해주세요.");
                System.out.println();
            }
        }

        // 3. 날짜 선택
        while (true) {
            System.out.println();
            System.out.println("관람하고 싶은 날짜를 선택하세요 1. 02/20  2. 02/21  3. 02/22");
            System.out.print("입력란: ");
            int num3 = sc.nextInt();
            if (num3 == 1) {
                date = "02/20";
                break;
            } else if (num3 == 2) {
                date = "02/21";
                break;
            } else if (num3 == 3) {
                date = "02/22";
                break;
            } else {
                System.out.println("잘못된 번호입니다 다시 선택해주세요.");
                System.out.println();
            }
        }

        // 4. 상영시간 선택
        while (true) {
            System.out.println();
            System.out.println("원하시는 상영시간을 선택하세요 1. 09:20  2. 12:10  3. 17:15");
            System.out.print("입력란: ");
            int num4 = sc.nextInt();
            if (num4 == 1) {
                showTime = "09:20";
                break;
            } else if (num4 == 2) {
                showTime = "12:10";
                break;
            } else if (num4 == 3) {
                showTime = "17:15";
                break;
            } else {
                System.out.println("잘못된 번호입니다 다시 선택해주세요.");
                System.out.println();
            }
        }

        // 5. 좌석수 선택
        while (true) {
            System.out.println();
            System.out.println("영화관람 인원수를 입력하세요 (최대 10인 제한)");
            System.out.print("입력란: ");
            people = sc.nextInt();
            if (people <= 10 && people > 0) {
                break;
            } else {
                System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            }
        }

        // 6. 좌석 선택
        // 행과 열의 크기 정의
        final int ROWS = 5;
        final int COLS = 9;

        // 영화관 좌석 배열 생성
        char[][] seatsarr = new char[ROWS][COLS];

        // 좌석에 알파벳과 숫자 할당
        char rowChar = 'A';
        for (int i = 0; i < ROWS; i++) {
            char colNum = '1';
            for (int j = 0; j < COLS; j++) {
                seatsarr[i][j] = '□'; // 공석 표시 할당
                colNum++;
            }
            rowChar++;
        }

        // 7. 이미 예약된 좌석 로드 및 좌석 배치
        try {
            BufferedReader reader = new BufferedReader(new FileReader(MOVIES_FILE_PATH));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] seatInfo = line.split(", ");
                if (seatInfo.length >= 7) {
                    String reservedTheater = seatInfo[1];
                    String reservedMovieName = seatInfo[2];
                    String reservedDate = seatInfo[3];
                    String reservedShowTime = seatInfo[4];

                    if (theater.equals(reservedTheater) && movieName.equals(reservedMovieName) &&
                            date.equals(reservedDate) && showTime.equals(reservedShowTime)) {
                        String[] reservedSeats = seatInfo[6].split(" ");
                        for (String seat : reservedSeats) {
                            char row = seat.charAt(0); // 이미 예매된 자리의 알파벳과 숫자 분리작업
                            char col = seat.charAt(1);

                            int rowIndex = row - 'A'; //인덱스는 첫 숫자가 0이므로 'A'와 '1'을 빼줌
                            int colIndex = col - '1';

                            if (rowIndex >= 0 && rowIndex < ROWS && colIndex >= 0 && colIndex < COLS) {
                                seatsarr[rowIndex][colIndex] = '■'; // 해당 좌석의 칸을 칠함
                            }
                        }
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("파일을 읽는 도중 오류가 발생했습니다: " + e.getMessage());
        }



        //8. 좌석배치도 출력
        System.out.println();
        System.out.println("");
        System.out.println("");
        System.out.println("           +————————————+");
        System.out.println("               screen");
        System.out.println("           +————————————+");
        System.out.println("");

        char seatChar2 = 'A';
        for (int i = 0; i < 5; i++) {
            System.out.print("    ");
            for (int j = 0; j < 9; j++) {
                if (j == 1) {
                    System.out.print(seatsarr[i][j] + "   ");
                } else if (j == 6) {
                    System.out.print(seatsarr[i][j] + "   ");
                } else if (j == 0) {
                    System.out.print(seatChar2 + "   " + seatsarr[i][j] + " ");
                    seatChar2++;
                } else {
                    System.out.print(seatsarr[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println("");
        System.out.println("        1 2   3 4 5 6 7   8 9");
        System.out.println("");

        // 9. 좌석 번호 입력 및 텍스트 파일에 저장
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(MOVIES_FILE_PATH, true));
            writer.write(id + ", " + theater + ", " + movieName + ", " + date + ", " + showTime + ", " + people + ", ");

            boolean[][] selectedSeats = new boolean[seatsarr.length][seatsarr[0].length];

            for (int p = 0; p < people; ) {
                int pp = p + 1;
                System.out.print("입력란" + pp + ": ");
                seats = sc.next();

                char row = seats.charAt(0); // 입력값의 행과 열 분리작업
                char col = seats.charAt(1);

                int rowIndex = row - 'A'; // 사용자가 'A'를 입력했다면 'A' - 'A'는 0, 'B'를 입력했다면 1 (아스키코드)
                int colIndex = col - '1'; //'1'을 입력했다면 '1' - '1'은 0이 되고, '2'를 입력했다면 1이 됨 이 계산값이 인덱스가 된다.

                if (rowIndex < 0 || rowIndex >= seatsarr.length || colIndex < 0 || colIndex >= seatsarr[0].length) {
                    System.out.println("올바르지 않은 좌석입니다 다시 입력해 주세요.");
                } else if (selectedSeats[rowIndex][colIndex]) {
                    System.out.println("이미 선택한 좌석입니다 다시 입력해 주세요.");
                } else if (seatsarr[rowIndex][colIndex] == '■') {
                    System.out.println("이미 예약된 좌석입니다. 다른 좌석을 선택해주세요.");
                } else {
                    selectedSeats[rowIndex][colIndex] = true;
                    seatsarr[rowIndex][colIndex] = '■'; // 입력한 좌석의 칸을 칠함
                    writer.write(seats + " ");
                    p++;
                }
            }
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            System.out.println("파일에 정보를 저장하는 중 오류가 발생했습니다: " + e.getMessage());
        }

        System.out.println();
        System.out.println("");
        System.out.println("           +————————————+");
        System.out.println("               screen");
        System.out.println("           +————————————+");
        System.out.println("");

        seatChar2 = 'A';
        for (int i = 0; i < 5; i++) {
            System.out.print("    ");
            for (int j = 0; j < 9; j++) {
                if (j == 1) {
                    System.out.print(seatsarr[i][j] + "   ");
                } else if (j == 6) {
                    System.out.print(seatsarr[i][j] + "   ");
                } else if (j == 0) {
                    System.out.print(seatChar2 + "   " + seatsarr[i][j] + " ");
                    seatChar2++;
                } else {
                    System.out.print(seatsarr[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println("");
        System.out.println("        1 2   3 4 5 6 7   8 9");
        System.out.println("");
        System.out.println("예매가 완료되었습니다.");
        System.out.println();
    }

    public static void createMovieFileIfNotExists() {
        File file = new File(MOVIES_FILE_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("movies.txt 파일을 생성하는 중 오류가 발생했습니다.");
                e.printStackTrace();
            }
        }
    }

}
