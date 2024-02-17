package MovieProj.Reservation;
import java.io.*;

public class ResCheck {
	
	public static void resCheck(String id){
	final String Res_file = "res.txt";
		
		try
		(
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
				 String runTime = resInfo[3].trim();
				 String seatNum = resInfo[4].trim();
				 
				 if(userId.equals(id)) {
					 resList[chk] = chk+1+"번예약 | " + "점포명 : "+whereIs+" 영화제목 : "+movieTitle+" 상영시간 : "+runTime+" 좌석번호 : "+seatNum;
					 System.out.println(resList[chk]);
					 reservated = true;
					 chk++;
				 }
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