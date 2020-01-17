package dao;

import connect.DataBaseConnector;
import model.MyQuery;
import model.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public final class UserDaoJd implements UserDao {
  private static final Logger LOGGER = Logger.getLogger(UserDaoJd.class);
  private Connection connection = DataBaseConnector.connect();

  public void initialization() {
    try (Statement statement = connection.createStatement()) {
      statement.execute(MyQuery.createTableUsers());
      statement.execute(MyQuery.createTableGoods());
    } catch (SQLException e) {
      LOGGER.warn("Couldn't create table", e);
    }
  }

  public User getUserByName(String name) {
    User newUser = new User();
    try {
      PreparedStatement preparedStatement = connection
              .prepareStatement(MyQuery.getByUserName());
      preparedStatement.setString(1, name);
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        newUser = getUserByResultSet(resultSet, newUser);
      }
    } catch (SQLException e) {
      LOGGER.error("Couldn't get user by name", e);
    }
    return newUser;
  }

  public boolean checkUser(final User newUser) {
    boolean isNewUser = true;
    try {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(MyQuery.selectUser());
      while (resultSet.next()) {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("username");
        String email = resultSet.getString("email");
        String pass = resultSet.getString("password");
        String role = resultSet.getString("role");
        String salt = resultSet.getString("salt");
        User userFromDb = new User(id, name, email, pass, role, salt);
        if (userFromDb.getUsername().equals(newUser.getUsername())) {
          isNewUser = false;
        }
      }
    } catch (SQLException e) {
      LOGGER.error("Couldn't check user from Database", e);
    }
    return isNewUser;
  }

  public void addUser(User user) {
    try (PreparedStatement preparedStatement = connection.prepareStatement(
            MyQuery.addUser())) {
      preparedStatement.setString(1, user.getUsername());
      preparedStatement.setString(2, user.getEmail());
      preparedStatement.setString(3, user.getPassword());
      preparedStatement.setString(4, user.getRole());
      preparedStatement.setString(5, user.getSalt());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      LOGGER.error("Couldn't add user to Database", e);
    }
  }

  public void deleteUser(User user) {
    try (PreparedStatement preparedStatement = connection
            .prepareStatement(MyQuery.deleteUser())) {
      preparedStatement.setString(1, user.getUsername());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      LOGGER.error("Couldn't deleteUser user", e);
    }
  }

  public void updateUsername(User user, String newUsername) {
    try (PreparedStatement preparedStatement = connection
            .prepareStatement(MyQuery.updateUsername())) {
      preparedStatement.setString(1, newUsername);
      preparedStatement.setString(2, user.getUsername());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      LOGGER.error("Couldn't update username", e);
    }
  }

  public void updatePassword(User user, String newPassword) {
    try (PreparedStatement preparedStatement = connection
            .prepareStatement(MyQuery.updatePassword())) {
      preparedStatement.setString(1, newPassword);
      preparedStatement.setString(2, user.getUsername());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      LOGGER.error("Couldn't update password", e);
    }
  }

  public List<User> getAllUsers() {
    List<User> userList = new ArrayList<>();
    try {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(MyQuery.selectUser());
      while (resultSet.next()) {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("username");
        String email = resultSet.getString("email");
        String pass = resultSet.getString("password");
        String role = resultSet.getString("role");
        String salt = resultSet.getString("salt");
        User user = new User(id, name, email, pass, role, salt);
        userList.add(user);
      }
    } catch (SQLException e) {
      LOGGER.error("Couldn't get all users", e);
    }
    return userList;
  }

  public User getUserById(int id) {
    User newUser = new User();
    try {
      PreparedStatement preparedStatement = connection
              .prepareStatement(MyQuery.getByUserId());
      preparedStatement.setInt(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        newUser = getUserByResultSet(resultSet, newUser);
      }
    } catch (SQLException e) {
      LOGGER.error("Couldn't get user by name", e);
    }
    return newUser;
  }

  private static User getUserByResultSet(
          ResultSet resultSet,
          User newUser) throws SQLException {
    int userId = resultSet.getInt(1);
    String userName = resultSet.getString(2);
    String userEmail = resultSet.getString(3);
    String userPassword = resultSet.getString(4);
    String userRole = resultSet.getString(5);
    String userSalt = resultSet.getString(6);
    newUser = new User(userId, userName, userEmail,
            userPassword, userRole, userSalt);
    return newUser;
  }
}