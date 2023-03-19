package controller;

import service.DataService;
import service.Menu;

public class MainController {
    private static final MainController instance = new MainController();
    DataService service = DataService.getInstance();
    Menu menu = Menu.getInstance();

    private MainController(){
    }

    public static MainController getInstance() {
        return instance;
    }

    public void run() {
        int number;
        while (true) {
            menu.show();
            number = menu.choice();
            if (number == 0) {
                System.out.println("프로그램을 정상적으로 종료합니다.");
                break;
            }
            switch (number) {
                case 1 -> service.addData();
                case 2 -> service.findByTitle();
                case 3 -> service.findByTotalnum();
                case 4 -> service.findByReleasedate();
            }
        }
    }

}
