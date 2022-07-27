package main.service;

import main.model.User;

import java.util.List;

public interface UserService {
    public void createTable();

    public void dropTable();

    public void cleanTable();

    public void addUser(User user);

    public void addUser(String name, String surname, byte age);

    public void removeUserById(long id);

    public List<User> getAllUsers();

    public User getUserById(long id);

    public void updateUserById(long id, User user);
}
