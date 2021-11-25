package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.sql.SQLException;
import java.util.List;


import jm.task.core.jdbc.dao.UserDaoHibernateImpl;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {


        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Oleg", "Tsvetkov", (byte) 36);
        userService.saveUser("Sergei", "Islamov", (byte) 33);
        userService.saveUser("Elena", "Popova", (byte) 30);
        userService.saveUser("Roman", "Druzhinin", (byte) 38);

        //userService.getAllUsers();
        //System.out.println(userService.getAllUsers());

        List<User> allUsers = userService.getAllUsers();
        for (Object u: allUsers.toArray()) {
            System.out.println(u);
        }

        userService.removeUserById(3);
        userService.cleanUsersTable();
        userService.dropUsersTable();
        UserDaoHibernateImpl.getSessionFactory().close();
    }
}