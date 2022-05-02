package application;

import java.sql.*;

public class DataBase {
    private static DataBase singleton = null;
    private Connection conn;

    private DataBase() {
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        conn = null;
        try {
            conn = DriverManager.getConnection(url, "mihai", "mihai");
        } catch (
                SQLException e) {
            System.out.println("Database connection not working: " + e);
        }
    }

    public static DataBase getInstance() {
        if(singleton == null) {
            singleton = new DataBase();
        }
        return singleton;
    }

    public ResultSet query(String sql) throws SQLException {
        try{
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("QUERY ERROR: " + e);
            return null;
        }
    }
}
