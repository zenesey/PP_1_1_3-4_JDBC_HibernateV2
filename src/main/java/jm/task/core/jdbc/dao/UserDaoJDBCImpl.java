package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Util util = new Util();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = util.getConnection()) {
            String TABLE = "CREATE TABLE IF NOT EXISTS users "
                    + "(Id INT AUTO_INCREMENT PRIMARY KEY, "
                    + " name VARCHAR(45) NOT NULL, "
                    + " lastName VARCHAR(45) NOT NULL, "
                    + " age INTEGER NOT NULL)";
            statement.executeUpdate(TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void dropUsersTable() {
        try (Statement statement = util.getConnection()) {
            String DROP = "DROP TABLE IF EXISTS users";
            statement.executeUpdate(DROP);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String INSERT = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?);";

        try (Statement statement = util.getConnection()) {
            PreparedStatement preparedStatement = statement.getConnection().prepareStatement(
                    INSERT);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        String REMOVE = "DELETE FROM users WHERE id =" + id;

        try (Statement statement = util.getConnection()) {

            statement.executeUpdate(REMOVE);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {

        String ALL = "SELECT * FROM users";

        List<User> users = new ArrayList<>();

        try (Statement statement = util.getConnection()) {
            ResultSet resultSet = statement.executeQuery(ALL);

            while (resultSet.next()) {

                User user = new User();
                user.setId(resultSet.getLong("Id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        String CLEAN = "TRUNCATE TABLE users";
        try (Statement statement = util.getConnection()) {
            statement.executeUpdate(CLEAN);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
