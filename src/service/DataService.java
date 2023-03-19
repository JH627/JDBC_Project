package service;

import repository.DataRepository;
import repository.JdbcDataRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class DataService {

    private static final DataService instance = new DataService();
    DataRepository repository = JdbcDataRepository.getInstance();
    Scanner sc = new Scanner(System.in);
    Scanner fc;

    public static DataService getInstance() {
        return instance;
    }

    private DataService(){
    }

    public void addData() {
        addRelation();
        addDataFromFile();
    }

    public void findByTitle() {
        System.out.print("검색할 Title을 입력하세요 : ");
        List<String> result = repository.findByTitle(sc.next());
        printResult(result);
    }

    public void findByTotalnum() {
        System.out.print("검색할 Totalnum을 입력하세요 : ");
        List<String> result = repository.findByTotalnum(sc.next());
        printResult(result);
    }

    public void findByReleasedate() {
        System.out.print("검색할 Releasedate을 입력하세요 : ");
        List<String> result = repository.findByReleasedate(sc.next(), sc.next());
        printResult(result);
    }

    private void addRelation() {
        String relation = "create table movie (    " +
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
        repository.addData(relation);
    }

    private void addDataFromFile() {
        String filePath = "./movie_data.txt";
        File file = new File(filePath);
        try {
            fc = new Scanner(file, "euc-kr");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while(fc.hasNextLine()) { // 파일 내용을 문법에 맞게 제작 후 서버에 전송
            repository.addData(makeQuery(fc.nextLine())); //
        }
        fc.close();
    }

    private String makeQuery(String origin) { // 쿼리 제작
        String basicInsertQuery = "INSERT INTO movie VALUES ";
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
        return result;
    }

    // 받아온 양식을 출력
    private void printResult(List<String> result) {
        System.out.printf("\n검색된 개수: %d", result.size());
        System.out.println("\n검색 결과 : ");
        for (String s : result) {
            System.out.println(s);
        }
        System.out.println();
    }
}