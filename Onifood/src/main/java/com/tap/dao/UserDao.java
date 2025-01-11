package com.tap.dao;

import java.util.List;
import com.tap.model.User;

public interface UserDao {
    void addUser(User user);
    User getUser(int userId);
    void updateUser(User user);
    void deleteUser(int userId);
    List<User> getAllUsers();
    User validateUser(String username, String password);
}
