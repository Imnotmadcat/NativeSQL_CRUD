package main.service;

import main.dao.UserDAO;
import main.dao.UserDAOImpl;
import main.model.User;

import java.util.List;

public class UserServiceImpl implements UserService{
    private final UserDAO userDAO = new UserDAOImpl();
    @Override
    public void createTable() {
        userDAO.createTable();
    }

    @Override
    public void dropTable() {
        userDAO.dropTable();
    }

    @Override
    public void cleanTable() {
        userDAO.cleanTable();
    }

    @Override
    public void addUser(User user) {
        userDAO.addUser(user);
    }
    @Override
    public void addUser(String name,String surname,byte age) {
        userDAO.addUser(name,surname,age);
    }

    @Override
    public void removeUserById(long id) {
        userDAO.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public User getUserById(long id) {
        return userDAO.getUserById(id);
    }

    @Override
    public void updateUserById(long id, User user) {
        userDAO.updateUserById(id, user);
    }
}
