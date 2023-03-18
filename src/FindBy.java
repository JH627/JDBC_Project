import java.util.Scanner;

public class FindBy {
    Scanner sc = new Scanner(System.in);
    MySQL_JDBC mySQL;
    private int mode = 0; // mode 0 : Title / 1 : Totalnum / 2 : Releasedate
    private final String[] basicQuery = {"SELECT * FROM movie WHERE title LIKE '%$%';",
            "SELECT * FROM movie WHERE totalnum > $;",
            "SELECT * FROM movie WHERE releasedate between '$' and '$';"}; // 기본 쿼리 문법
    private final String[] modeName = {"Title", "Totalnum", "Releasedate"};
    private String resultQuery; // 서버에 최종적으로 보낼 쿼리

    FindBy(MySQL_JDBC SQL) {
        this.mySQL = SQL; // 서버 설정
    }

    public void findByKeyword(String name) {
        findModeByInput(name);
        System.out.printf("%s(으)로 검색을 시도합니다.\n", modeName[mode]);
        System.out.printf("검색할 키워드를 입력하세요: ");
        makeQuery();  // 쿼리 제작
        System.out.println("서버에 전송된 내용 : " + resultQuery);
        mySQL.getData(resultQuery); // 제작된 쿼리를 서버에 전송
    }

    private void findModeByInput(String Name) { // main에서 보낸 keyword로 mode 설정
        for (int i = 0; i < modeName.length; i++) {
            if (modeName[i].equals(Name)) {
                this.mode = i;
                break;
            }
        }
    }
    private void makeQuery() { // input를 기반으로 쿼리 제작
        resultQuery = basicQuery[mode].replaceFirst("\\$", sc.next()); // '$' 자리에 키워드가 들어감
        while (resultQuery.contains("$")) { // 인자가 두개 이상인경우에도 가능하도록 함
            resultQuery = resultQuery.replaceFirst("\\$", sc.next());
        }
    }
}