package moviePrj;

import java.util.*;

public class MovieReservationSystem {
	
	public ArrayList<User> users;
	public ArrayList<Movie> movies;
	public User currentUser;
	public Scanner scanner;

    public MovieReservationSystem() {
        this.users = new ArrayList<>();
        this.movies = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean exit = false;

        while (!exit) {
            System.out.println("메뉴를 선택하세요.");
            System.out.println("1. 회원가입");
            System.out.println("2. 로그인");
            System.out.println("3. 종료");
            int choice = scanner.nextInt();
            System.out.println("");

            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    loginUser();
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("잘못 선택하셨습니다.");
            }
        }
        scanner.close();
    }

    private void registerUser() {
        System.out.print("아이디: ");
        String username = scanner.next();
        System.out.print("비밀번호: ");
        String password = scanner.next();

        users.add(new User(username, password));
        System.out.println("회원가입 성공!");
        System.out.println("");
    }

    private void loginUser() {
        System.out.print("아이디: ");
        String username = scanner.next();
        System.out.print("비밀번호: ");
        String password = scanner.next();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                currentUser = user;
                currentUser.setLoggedIn(true);
                System.out.println("로그인 성공!");
                System.out.println("");
                movieReservation();
                return;
            }
        }

        System.out.println("잘못 입력하셨습니다.");
    }

    private void movieReservation() {
        while (true) {
            System.out.println("영화 목록:");
            for (int i = 0; i < movies.size(); i++) {
                Movie movie = movies.get(i);
                System.out.println((i + 1) + ". " + movie.getTitle() + "  남은 좌석: " + movie.getAvailableSeats());
            }
            System.out.println("0. 로그아웃");
            System.out.print("영화를 선택해주세요: ");
            int choice = scanner.nextInt();
            System.out.println("");
            
            if (choice == 0) {
                currentUser.setLoggedIn(false);
                currentUser = null;
                break;
            }

            if (choice > 0 && choice <= movies.size()) {
                Movie selectedMovie = movies.get(choice - 1);
                System.out.println("선택한 영화: " + selectedMovie.getTitle());
                if (selectedMovie.getAvailableSeats() > 0) {
                    System.out.print("영화 시청 인원을 선택해주세요: ");
                    int numSeatsToReserve = scanner.nextInt();

                    if (numSeatsToReserve > 0 && numSeatsToReserve <= selectedMovie.getAvailableSeats()) {
                        selectedMovie.decreaseAvailableSeats(numSeatsToReserve);
                        System.out.println(selectedMovie.getTitle()+"영화 예약을 성공하였습니다!");
                    } else {
                        System.out.println("잘못 선택하셨습니다.");
                    }
                } else {
                    System.out.println("남은 좌석이 없습니다.");
                }
            }
            System.out.println("");
        }
    }
    
    public void addMovie(String title, int availableSeats) {
        movies.add(new Movie(title, availableSeats));
    }
}