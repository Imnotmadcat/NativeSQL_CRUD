package main.dao;

import main.model.User;
import main.util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private final Connection connection = JDBCUtil.getConnection();
    private String sql;

    @Override
    public void createTable() {
        sql = "create table if not exists people " +
                "( id bigint primary key auto_increment, " +
                " surname varchar(100) not null ," +
                " user_name varchar(100) not null ," +
                " age smallint not null )";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Table has Created");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void dropTable() {
        sql = "drop table if exists people";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Table has dropped");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void cleanTable() {
        sql = "truncate table people";
        // sql = "delete from people";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Table has truncated");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void addUser(User user) {
        sql = "insert into people(surname, user_name, age) values (?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getSurname());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setByte(3, user.getAge());
            preparedStatement.executeUpdate();
            System.out.println("Added user with name " + user.getName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addUser(String name, String surname, byte age) {
        sql = "insert into people(surname, user_name, age) VALUES (?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("Added user with name " + name);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeUserById(long id) {
        sql = "delete from  people where id =(?) ";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Removed user with id = " + id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        ResultSet resultSet;
        List<User> userList = new ArrayList<>();
        sql = "select * from people";

        try (Statement statement = connection.createStatement()) {
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setName(resultSet.getString("user_name"));
                user.setSurname(resultSet.getString("surname"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);

            }
            System.out.println("All users was add to list");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return userList;
    }

    @Override
    public User getUserById(long id) {
        ResultSet resultSet;
        sql = "select surname,user_name,age from people where id = (?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();

            User user = new User();
            user.setName(resultSet.getString("user_name"));
            user.setSurname(resultSet.getString("surname"));
            user.setAge(resultSet.getByte("age"));
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateUserById(long id, User user) {
        sql = "update people set user_name = (?), surname =(?), age = (?) where id =(?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getSurname());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setByte(3, user.getAge());
            preparedStatement.setLong(4, id);
            preparedStatement.executeUpdate();
            System.out.println("User with id = " + id +" changed his name to = " + user.getName() );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
