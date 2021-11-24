package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() throws SQLException {
    }

    private Connection connection = Util.getConnection();


    public void createUsersTable() throws SQLException {
        System.out.println("Creating savepoint...");
        Savepoint savepointOne = connection.setSavepoint("SavepointOne");

        try {
            Statement state = connection.createStatement();
            connection.setAutoCommit(false);
            state.execute("CREATE TABLE IF NOT EXISTS users" +
                    "(id BIGINT(19) NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(255) NOT NULL, lastname VARCHAR(255) NOT NULL, " +
                    "age TINYINT UNSIGNED NOT NULL) DEFAULT CHARSET=utf8");
            connection.commit();

        } catch (SQLException e) {
            System.out.println("Unable to create user table: " + e);
            connection.rollback(savepointOne);
        }
    }


    public void dropUsersTable() {

        try {
            Statement statement = connection.createStatement();
            connection.setAutoCommit(false);
            String SQL = "DROP TABLE IF EXISTS users";
            statement.executeUpdate(SQL);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        try {
            Statement statement = connection.createStatement();
            connection.setAutoCommit(false);
            String saveUser = "INSERT INTO users (name, lastName, age) VALUES (" + "'" + name + "', '" + lastName + "'," + age + ")";
            statement.executeUpdate(saveUser);
            System.out.println("New user with name " + name + " has been added to the database");
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {

        try {
            Statement statement = connection.createStatement();
            connection.setAutoCommit(false);
            String removeUser = "DELETE FROM users WHERE ID = id";
            statement.executeUpdate(removeUser);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<User> getAllUsers() {

        List<User> userList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            connection.setAutoCommit(false);
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                userList.add(user);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() throws SQLException {

        try {
            Statement statement = connection.createStatement();
            connection.setAutoCommit(false);
            String SQL = "DELETE FROM users";
            statement.executeUpdate(SQL);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}





