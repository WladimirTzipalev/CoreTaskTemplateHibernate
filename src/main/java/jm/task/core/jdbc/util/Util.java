package jm.task.core.jdbc.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class Util {
    private final static String URL = "jdbc:mysql://localhost:3306/mytest";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "OLs54ek85";
    private final static String DIALECT = "org.hibernate.dialect.MySQLDialect";
    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";

    public static Connection getConnect() {
        Connection connection = null;

    try {
        Class.forName(DRIVER);
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        System.out.println("The connection to the database is ready.");
    } catch(SQLException |
    ClassNotFoundException e)

    {
        e.printStackTrace();
        System.out.println("Connection failed. Try again.");
    }
        return connection;
}




    public static SessionFactory getSessionFactory() {
        SessionFactory sessionFactory = null;
        try {
            Configuration configuration = new Configuration();
            configuration.setProperty("hibernate.connection.url", URL);
            configuration.setProperty("hibernate.connection.username", USERNAME);
            configuration.setProperty("hibernate.connection.password", PASSWORD);
            configuration.setProperty("hibernate.dialect", DIALECT);
            configuration.setProperty("hibernate.connection.driver_class", DRIVER);
            configuration.addAnnotatedClass(User.class);

            StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
            registryBuilder.applySettings(configuration.getProperties());

            return configuration.buildSessionFactory(registryBuilder.build());

        } catch (HibernateException e) {
            e.printStackTrace();
            return sessionFactory;
        }
    }
}
