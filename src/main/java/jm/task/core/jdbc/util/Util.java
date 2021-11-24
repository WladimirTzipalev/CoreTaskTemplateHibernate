package jm.task.core.jdbc.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private final static String URL = "jdbc:mysql://localhost:3306/mytest";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "OLs54ek85";
    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";


    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(DRIVER);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}