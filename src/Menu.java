import java.util.Scanner;

public class Menu {
    Scanner sc = new Scanner(System.in);
    private String[] list = {"(0) 종료", "(1) 릴레이션 생성 및 데이터 추가", "(2) 제목을 이용한 검색", "(3) 관객수를 이용한 검색", "(4) 개봉일을 이용한 검색"};
    public int number = 0;

    public int choiceMenu() {
        printMenuList();              // 메뉴 양식을 출력
        number = sc.nextInt();        // 이용자가 정수를 입력한다고 가정하였음
        if (!isRightNumber()) {       // 번호가 범위 내에 있는지 확인
            number = choiceMenu();    // 없다면 다시 선택
        }
        return number;                // 선택한 번호를 반환 
    }

    private void printMenuList() { // 메뉴를 출력하는 함수
        System.out.println("========================================");
        for (int i = 0; i < list.length; i++) {
            System.out.printf("%s\n", list[i]);
        }
        System.out.println("========================================");
        System.out.printf("%s", "원하는 번호를 입력 하시오 : ");
    }

    private boolean isRightNumber() {
        if (number <= -1 || number >= list.length) {   // 입력한 번호가 범위내의 올바른 번호인지 확인하고
            System.out.println("\n== 올바르지 않은 번호 입니다. 다시 입력하세요 ==");
            return false;                    // 올바르지 않다면 false를 반환
        }
        return true;
    }
}