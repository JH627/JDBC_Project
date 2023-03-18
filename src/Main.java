public class Main {
    public static void main(String[] args) {
        int selectedMenu = 0;
        MySQL_JDBC mySQL = new MySQL_JDBC();
        Menu mainMenu = new Menu();
        AddData add_Data = new AddData(mySQL);
        FindBy find= new FindBy(mySQL);

        while (true) {
            selectedMenu = mainMenu.choiceMenu();
            if (selectedMenu == 0) {
                break;
            }
            switch (selectedMenu) {
                case 1:
                    add_Data.addFromFile();
                    break;
                case 2:
                    find.findByKeyword("Title");
                    break;
                case 3:
                    find.findByKeyword("Totalnum");
                    break;
                case 4:
                    find.findByKeyword("Releasedate");
                    break;
                default:
                    break;
            }
        }
        System.out.println("프로그램을 정상적으로 종료합니다.");
    }
}