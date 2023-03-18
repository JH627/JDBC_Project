import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AddData {
    private MySQL_JDBC mySQL;
    private final String filePath = "./movie_data.txt";
    private final String basicInsertQuery = "INSERT INTO movie VALUES ";
    private File file;
    private Scanner sc;
    private boolean data_Added = false;

    private final String relation = "create table movie (    " +
            "id           char(3),    " +
            "title        varchar (100),    " +
            "company      varchar (50),    " +
            "releasedate  date,    " +
            "country      varchar (10),    " +
            "totalscreen  int,    " +
            "profit       numeric (15,2),    " +
            "totalnum     int,    " +
            "grade        varchar (50),    " +
            "primary key (id));";

    public AddData (MySQL_JDBC mySQL) {
        this.mySQL = mySQL;
        file = new File(filePath); // 파일 경로 설정
    }

    public void addFromFile() {
        if (data_Added) {   // 데이터를 이미 추가했다면
            System.out.println("이미 데이터를 추가했습니다.");
            return;         // 함수 종료
        }
        try {
            mySQL.setData(relation);    // relation 추가
            System.out.println("relation 추가 성공");
            sc = new Scanner(file, "euc-kr"); // 파일 열기
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while(sc.hasNextLine()) { // 파일 내용을 문법에 맞게 제작 후 서버에 전송
            mySQL.setData(makeQuery(sc.nextLine())); //
        }

        System.out.println("데이터 추가 성공");
        data_Added = true;
        sc.close();
    }

    private String makeQuery(String origin) { // 쿼리 제작
        String result = basicInsertQuery + origin;
        result = result.replaceFirst("\\|", "(\"");
        for(int i = 0; i < 4; i++) {
            result = result.replaceFirst("\\|","\", \"");
        }
        result = result.replaceFirst("\\|","\", ");
        result = result.replaceFirst("\\|",", ");
        result = result.replaceFirst("\\|",", ");
        result = result.replaceFirst("\\|",", \"");
        result += "\");";
        System.out.println(result);
        return result;
    }
}