package main.test;

import main.dao.UserDAO;
import main.dao.UserDAOImpl;
import main.model.User;
import main.service.UserService;
import main.service.UserServiceImpl;
import main.util.JDBCUtil;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<User> userList = new ArrayList<>();
        UserService userService = new UserServiceImpl();
        userService.dropTable();
        userService.createTable();
        userService.addUser("Oleg1", "Savchenko1", (byte) 21);
        userService.addUser("Oleg2", "Savchenko2", (byte) 22);
        userService.addUser("Oleg3", "Savchenko3", (byte) 23);
        userService.addUser("Oleg4", "Savchenko4", (byte) 24);
        userService.addUser("Oleg5", "Savchenko5", (byte) 25);
        userService.addUser("Oleg6", "Savchenko6", (byte) 26);
        userList = userService.getAllUsers();
        System.out.println(userList);
        userService.removeUserById(1);
        userService.removeUserById(2);
        userList = userService.getAllUsers();
        System.out.println(userList);
        userService.addUser("Oleg7", "Savchenko7", (byte) 27);
        userService.addUser("Oleg8", "Savchenko8", (byte) 28);
        userService.updateUserById(5,new User("Oleg200","Savchenko200",(byte) 99));
        userService.cleanTable();
        JDBCUtil.closeConnection();
    }
}
