package service;

import java.util.Scanner;

public class Menu {

    private static final Menu instance = new Menu();
    Scanner sc = new Scanner(System.in);
    private final String[] menuList = {"(0) 종료", "(1) 릴레이션 생성 및 데이터 추가", "(2) 제목을 이용한 검색", "(3) 관객수를 이용한 검색", "(4) 개봉일을 이용한 검색"};

    private Menu(){
    }

    public static Menu getInstance() {
        return instance;
    }

    public void show(){
        System.out.println("========================================");
        for (String menu : menuList) {
            System.out.println(menu);
        }
        System.out.println("========================================");
    }

    public int choice() {
        System.out.printf("%s", "원하는 번호를 입력 하시오 : ");
        int number = sc.nextInt();
        while (true) {
            if (isRightNumber(number)) {
                return number;
            }
        }
    }

    private boolean isRightNumber(int number) {
        if (number <= -1 || number >= menuList.length) {   // 입력한 번호가 범위내의 올바른 번호인지 확인하고
            System.out.println("\n== 올바르지 않은 번호 입니다. 다시 입력하세요 ==");
            return false;                    // 올바르지 않다면 false를 반환
        }
        return true;
    }

}
