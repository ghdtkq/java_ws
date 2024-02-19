package MovieProj.Admin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ResList {
    public static void showResList() {
        final String Res_file = "movies.txt";

        try(
                FileReader fileReader = new FileReader(Res_file);
                BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                String line;
                boolean reservated = false;
                String[] resList = new String[10];
                int chk = 0;
                // 파일의 끝까지 한 줄씩 읽어서 콘솔에 출력

                while ((line = bufferedReader.readLine()) != null) {

                    String[] resInfo = line.split(",");
                    String userId = resInfo[0].trim();
                    String whereIs = resInfo[1].trim();
                    String movieTitle = resInfo[2].trim();
                    String runDate = resInfo[3].trim();
                    String runTime = resInfo[4].trim();
                    String seatNum = resInfo[6].trim();

                    resList[chk] = chk+1+"번예약 | "+userId+"님의 예약 "+ "점포명 : "+whereIs+" 영화제목 : "+movieTitle+" 상영날짜 : "+runDate+" 상영 시간 : " + runTime+" 좌석번호 : "+seatNum;
                    System.out.println(resList[chk]);
                    reservated = true;
                    chk++;
                }
            if(reservated == false) {
                System.out.println("예매 내역이 없습니다");
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
