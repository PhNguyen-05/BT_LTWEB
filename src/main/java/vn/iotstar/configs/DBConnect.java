package vn.iotstar.configs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnect {
    private final String serverName = "localhost"; // hoặc IP
    private final String dbName = "LTWEB";
    private final String portNumber = "1433";
    private final String instance = ""; // nếu có instance: "SQLEXPRESS"
    private final String userID = "sa";
    private final String password = "123456";

    public Connection getConnection() throws SQLException {
        String url;
        if (instance == null || instance.trim().isEmpty()) {
            url = "jdbc:sqlserver://" + serverName + ":" + portNumber
                + ";databaseName=" + dbName
                + ";encrypt=true;trustServerCertificate=true";
        } else {
            url = "jdbc:sqlserver://" + serverName + "\\" + instance + ":" + portNumber
                + ";databaseName=" + dbName
                + ";encrypt=false;trustServerCertificate=true";
        }

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(url, userID, password);
        } catch (ClassNotFoundException e) {
            throw new SQLException("JDBC Driver not found: " + e.getMessage(), e);
        }
    }

    // Thêm phương thức đóng kết nối
    public void closeConnection(Connection conn, PreparedStatement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DBConnect db = new DBConnect();
        String sqlInsert = "INSERT INTO users (id, username, fullname, email, sdt, password) VALUES (?, ?, ?, ?, ?, ?)";
        String selectAll = "SELECT * FROM users";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = db.getConnection();

            // Insert test data
            stmt = conn.prepareStatement(sqlInsert);
            stmt.setInt(1, 1); // id
            stmt.setString(2, "trung123"); // username
            stmt.setString(3, "Trung Nguyen"); // fullname
            stmt.setString(4, "trung@example.com"); // email
            stmt.setString(5, "0901234567"); // sdt
            stmt.setString(6, "password123"); // password
            stmt.executeUpdate();

            // Select all
            stmt = conn.prepareStatement(selectAll);
            rs = stmt.executeQuery();

            // Show data
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " " + rs.getString("username") + " " 
                    + rs.getString("fullname") + " " + rs.getString("email") 
                    + " " + rs.getString("sdt") + " " + rs.getString("password"));
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            db.closeConnection(conn, stmt, rs);
        }
    }
}