package jm.task.core.jdbc.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    private static Util util;
    public static Util getUtil() {
        if (util == null) {
            util = new Util();
        }
        return util;
    }
    public Util () {
    }
    private static final String URL = "jdbc:mysql://localhost:3306/kataex1";
    private static final String UNAME = "root";
    private static final String UPASSWORD = "root";




    public Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, UNAME, UPASSWORD);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Statement getStatement() {
        try {
            return getConnection().createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
