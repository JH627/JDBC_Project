package repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcDataRepository implements DataRepository{
    private static final JdbcDataRepository instance = new JdbcDataRepository();
    private Connection conn = null; // 서버와의 연결 여부
    private Statement stmt = null;
    private ResultSet rs = null;

    public static JdbcDataRepository getInstance() {
        return instance;
    }

    private JdbcDataRepository(){
    }

    @Override
    public void addData(String query) {
        try {
            pushData(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> findByTitle(String title) {
        String query = "SELECT * FROM movie WHERE title LIKE '%$%';";
        query = query.replaceFirst("\\$", title);
        try {
            return getData(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> findByTotalnum(String totalnum) {
        String query = "SELECT * FROM movie WHERE totalnum > $;";
        query = query.replaceFirst("\\$", totalnum);
        try {
            return getData(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> findByReleasedate(String predate, String postdate) {
        String query = "SELECT * FROM movie WHERE releasedate between '$' and '$';";
        query = query.replaceFirst("\\$", predate);
        query = query.replaceFirst("\\$", postdate);
        try {
            return getData(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 서버에 연결
    private void connect() {
        String serverUrl = "jdbc:mysql://localhost/mydb?useUnicode=true&serverTimezone=Asia/Seoul";
        String user = "root";
        String password = "1234";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(serverUrl, user, password);
            stmt = conn.createStatement();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // 서버 연결 해제
    private void disconnect() throws SQLException {
        conn.close();
    }

    private void pushData(String query) throws SQLException {
        System.out.println("ddfsa");
        connect();
        if (isConnected()) {
            try {
                stmt.executeUpdate(query);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            disconnect();
        }
    }

    // 서버 응답 내용을 리스트로
    private List<String> getData(String query) throws SQLException {
        connect();
        List<String> resultData = new ArrayList<>();
        if (isConnected()) {
            try{
                rs = stmt.executeQuery(query);
                addAttribute(rs, resultData);
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
            disconnect();
        }
        return resultData;
    }

    // ResultSet을 list로 변환
    private void addAttribute(ResultSet rs, List<String> resultData) throws SQLException {
        while(rs.next()) {
            resultData.add(rs.getString(1) + "|" + rs.getString(2) + "|" +
                    rs.getString(3) + "|" + rs.getString(4) + "|" +
                    rs.getString(5) + "|" + rs.getString(6) + "|" +
                    rs.getString(7) + "|" + rs.getString(8) + "|" +
                    rs.getString(9));
        }
    }
        
    // 서버에 연결되었는지 확인
    private boolean isConnected() {
        if (getConn() == null) {
            System.out.println("connect error");
            return false;
        }
        return true;
    }

    private Connection getConn() {
        return conn;
    }
}