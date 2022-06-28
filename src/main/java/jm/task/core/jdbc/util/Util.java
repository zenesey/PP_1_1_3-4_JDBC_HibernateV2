package jm.task.core.jdbc.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    // реализуйте настройку соеденения с
    public Util () {

    }
    private static final String URL = "jdbc:mysql://localhost:3306/kataex1";
    private static final String UNAME = "root";
    private static final String UPASSWORD = "721408Dd";


    public Statement getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, UNAME, UPASSWORD);
            return connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
