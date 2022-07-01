package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

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

    private static SessionFactory sessionFactory;

    // 1  Configuration сюда упаковать propert

    // 2  Properties -- Сюда все настройки через Put
    // driver,url,user,pass,dialect,show-sql,CURRENT_SESSION_CONTEXT_CLASS - какаято срань про поток,
    // HBM2DDL_AUTO- С помощью create-drop схема базы данных будет удалена

    //3 StandardServiceRegistryBuilder


    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Properties properties = new Properties();
                Configuration configuration = new Configuration();
                properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                properties.put(Environment.URL, URL);
                properties.put(Environment.USER, UNAME);
                properties.put(Environment.PASS, UPASSWORD);
                properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
                properties.put(Environment.SHOW_SQL, "true");
                properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                properties.put(Environment.HBM2DDL_AUTO, "create-drop");

                configuration.setProperties(properties);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry sr = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(sr);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }





    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, UNAME, UPASSWORD);
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
