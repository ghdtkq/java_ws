package MovieProj.Reservation;

import static MovieProj.Reservation.UserMenu.showUserMenu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ResCancle {

	public static void resCancle(Scanner sc, String id){
		ResCheck.resCheck(id);//예약목록 출력
		System.out.println("몇 번 예약을 취소하시겠습니까?(숫자만 입력, 취소는 0) :");
		int cancleNum = sc.nextInt();
		switch(cancleNum) {
		case 0:
			MovieMenu.showMovieMenu(sc, id);
		default:
			resCancler(sc, cancleNum, id);
		}
		
	}

	private static void resCancler(Scanner sc, int cancleNum, String id) {
		final String Res_file = "movies.txt";
		int allchk = -1;	//전체 목록 체커
		String[] allList = new String[100];//전체 예약 목록
		int chk = 0;	//현재 로그인중인 아이디의 목록 체커
		String[] resList = new String[10];	//현재 로그인중인 아이디의 리스트
		String line;
		
		String dummy = "";					//더미

		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(Res_file));
			
			//resList[chk], 로그인중인 아이디의 예약 목록을 불러오는 구문
			while ((line = br.readLine()) != null) {		//line에 버퍼리더br이 읽어온 내용이 저장되고, 이것이 null이 될 때까지(빈 줄에 도달할 때 까지)반복
				 String[] resInfo = line.split(",");	//resInfo[0]에 id, [1]에 지점, [2]에 영화제목...
				 String userId = resInfo[0].trim();		//userId에 resInfo[0].trim, 즉 id가 그대로 저장됨
				 if(userId.equals(id)) {	//resCancler에서 받아온 id와 userId가 같은 라인만 골라서
					 resList[chk] = line;	//각 line이 resList[0] resList[1] resList[2]... 계속 저장됨
					 chk++;
				 }
			}
			
			if(cancleNum>chk){	//만약 입력받은 숫자가 resList의 칼럼수보다 많다면
				System.out.println("다시 입력해주세요!");
				ResCancle.resCancle(sc, id);	//다시 처음으로
			}
			else {
			//resList[cancleNum-1] -> 유저가 선택한 라인
			//allList[allchk], 유저가 선택한 resList[cancleNum-1]과 동일한 내용을 갖는 라인이 allList에선 몇 번째(allchk) 라인인지를 구하는 구문
				br = new BufferedReader(new FileReader(Res_file));
				while ((line = br.readLine()) != null) {		//line에 버퍼리더br이 읽어온 내용 저장해가면서, 그것이 null이 될 때 까지
					allchk++;									//allchk는 -1에서 시작, 0번부터 라인을 저장하며 확인한다
					allList[allchk] = line;
					if(allList[allchk].equals(resList[cancleNum-1])) {	//모든 예약 리스트에서 allchk번째 줄이 유저가 선택한 라인과 내용이 일치할 경우
						break;											//allchk를 남긴 채 while문을 빠져나온다
					}
	
				}
				br = new BufferedReader(new FileReader(Res_file));
				for(int i = 0; i<allchk ; i++) {
					line = br.readLine();
					dummy += (line+"\r\n");
				}
			//삭제되는 데이터
				String delData = br.readLine();
				
				
				while ((line=br.readLine()) != null) {
					dummy += (line+"\r\n");
				}
				
			
				FileWriter fw = new FileWriter(Res_file);
				fw.write(dummy);
				fw.close();
				System.out.println("예약이 취소되었습니다!!");
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
