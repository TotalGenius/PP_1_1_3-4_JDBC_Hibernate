package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Oleg","Olegov", (byte) 23);
        userService.saveUser("Dmitriy","Sinicyn", (byte) 33);
        userService.saveUser("Alexander","Kim", (byte) 28);
        userService.saveUser("Alexey","Gromov", (byte) 45);
        System.out.println("------------------");
        userService.getAllUsers().forEach(el -> System.out.println(el));
        userService.cleanUsersTable();
        userService.dropUsersTable();


    }
}
