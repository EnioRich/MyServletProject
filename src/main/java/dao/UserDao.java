package dao;

import model.User;

import java.util.List;

public interface UserDao {
    User getUserByName(String name);
    boolean checkUser(User newUser);
    void addUser(User user);
    void deleteUser(User user);
    void updateUsername(User user, String newUsername);
    void updatePassword(User user, String newPassword);
    List<User> getAllUsers();
    User getUserById(int id);
}
