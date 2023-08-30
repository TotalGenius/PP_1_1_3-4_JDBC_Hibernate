package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.exceptions.DBConnectionException;
import jm.task.core.jdbc.exceptions.SQLOperationException;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute("""
                CREATE TABLE IF NOT EXISTS Users(
                id BIGINT PRIMARY KEY AUTO_INCREMENT,
                name VARCHAR(50),
                lastname VARCHAR(50),
                age TINYINT)
                """);
        } catch (SQLException e) {
            throw new SQLOperationException(e);
        }

    }

    public void dropUsersTable() {

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS Users");
        } catch (SQLException e) {
            throw new SQLOperationException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        try (Connection connection = Util.getConnection();
             PreparedStatement statement = connection.prepareStatement("""
                INSERT INTO Users(name, lastname, age)
                VALUES (?,?,?)
                """)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            System.out.printf("User с именем - %s добавлен в базу данных\n", name);
        } catch (SQLException e) {
            throw new SQLOperationException(e);
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection();
             PreparedStatement statement = connection.prepareStatement("""
                DELETE FROM Users
                WHERE id=?
                """)) {
            statement.setLong(1, id);
        } catch (SQLException e) {
            throw new SQLOperationException(e);
        }
    }

    public List<User> getAllUsers() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement();
             ResultSet set = statement.executeQuery("SELECT * FROM Users")) {
            List<User> users = new ArrayList<>();
            while (set.next()) {
                User user = new User(set.getString("name"),
                        set.getString("lastname"),
                        set.getByte("age"));
                user.setId(set.getLong("id"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new SQLOperationException(e);
        }
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute("DELETE FROM Users");
        } catch (SQLException e) {
            throw new SQLOperationException(e);
        }

    }
}
