import java.sql.*;

public class MySQL_JDBC {
    public Connection conn = null; // 서버와의 연결 여부
    private Statement stmt = null; //
    private ResultSet rs = null;

    MySQL_JDBC() {
        connect_Sever(); // 처음 생성할 때 서버에 연결
    }

    public boolean getData(String QuerySQL) {  // SELECT 쿼리를 위한 함수, 결과값까지 출력함
        return execute_Data(QuerySQL);         // 보내기 이전에 전처리를 할 수도 있으므로 동일한 함수를 만들었음
    }

    public boolean setData(String UpdateSQL) {  // 그 외의 문법을 위한 함수
        return execute_Update(UpdateSQL);
    }

    private void connect_Sever() { // 서버에 연결
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/mydb?useUnicode=true&serverTimezone=Asia/Seoul",
                    "root", "1234");
            stmt = conn.createStatement();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean execute_Data(String QuerySQL){ // 서버에 전송 후 결과 출력, 실패시 false 반환
        if (conn == null) { // 서버에 연결되었는지 확인
            System.out.println("connect error");
            return false;
        }
        try{
            rs = stmt.executeQuery(QuerySQL);
            System.out.println("\n검색 결과 :");
            while(rs.next()) { // 받아온 양식을 출력
                System.out.println(rs.getString(1) + "|" + rs.getString(2) + "|" +
                        rs.getString(3) + "|" + rs.getString(4) + "|" +
                        rs.getString(5) + "|" + rs.getString(6) + "|" +
                        rs.getString(7) + "|" + rs.getString(8) + "|" +
                        rs.getString(9));
            }
            System.out.printf("\n");
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean execute_Update(String UpdateSQL) { // UPDATE 등의 쿼리 전송, 전송 실패시 fasle를 반환
        if (conn == null) { // 서버에 연결되었는지 확인
            System.out.println("connect error");
            return false;
        }
        try {
            stmt.executeUpdate(UpdateSQL);
        }
        catch (SQLException sqle){
            System.out.println("Could not insert tuple. " + sqle);
            return false;
        }
        return true;
    }
}