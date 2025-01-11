package com.tap.daoImp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.tap.dao.UserDao;
import com.tap.model.User;

public class UserDaoI implements UserDao {
    final static String INSERT_QUERY = "INSERT INTO `user` (`userId`, `userName`, `password`, `email`, `phoneNumber`, `address`, `role`, `createDate`, `lastLoginDate`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    final static String SELECT_QUERY = "SELECT * FROM `user` WHERE `userId`=?";
    final static String UPDATE_QUERY = "UPDATE `user` SET `userName`=?, `password`=?, `email`=?, `phoneNumber`=?, `address`=?, `role`=?, `createDate`=?, `lastLoginDate`=? WHERE `userId`=?";
    final static String DELETE_QUERY = "DELETE FROM `user` WHERE `userId`=?";
    final static String SELECT_ALL_QUERY = "SELECT * FROM `user`";

    private Connection connection;

    public UserDaoI() {
        String url = "jdbc:mysql://localhost:3306/tapfoods";
        String un = "root";
        String pwd = "root";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, un, pwd);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addUser(User user) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setInt(1, user.getUserId());
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPhoneNumber());
            preparedStatement.setString(6, user.getAddress());
            preparedStatement.setString(7, user.getRole());
            preparedStatement.setTimestamp(8, new Timestamp(user.getCreateDate().getTime()));
            preparedStatement.setTimestamp(9, new Timestamp(user.getLastLoginDate().getTime()));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUser(int userId) {
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_QUERY)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User(
                    resultSet.getInt("userId"),
                    resultSet.getString("userName"),
                    resultSet.getString("password"),
                    resultSet.getString("email"),
                    resultSet.getString("phoneNumber"),
                    resultSet.getString("address"),
                    resultSet.getString("role"),
                    resultSet.getTimestamp("createDate"),
                    resultSet.getTimestamp("lastLoginDate")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void updateUser(User user) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPhoneNumber());
            statement.setString(5, user.getAddress());
            statement.setString(6, user.getRole());
            statement.setTimestamp(7, new Timestamp(user.getCreateDate().getTime()));
            statement.setTimestamp(8, new Timestamp(user.getLastLoginDate().getTime()));
            statement.setInt(9, user.getUserId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int userId) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY)) {
            while (resultSet.next()) {
                users.add(new User(
                    resultSet.getInt("userId"),
                    resultSet.getString("userName"),
                    resultSet.getString("password"),
                    resultSet.getString("email"),
                    resultSet.getString("phoneNumber"),
                    resultSet.getString("address"),
                    resultSet.getString("role"),
                    resultSet.getTimestamp("createDate"),
                    resultSet.getTimestamp("lastLoginDate")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
   
        public User validateUser(String username, String password) {
            String query = "SELECT * FROM user WHERE userName = ? AND password = ?";
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tapfoods", "root", "root");
                 PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, username);
                statement.setString(2, password);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    User user = new User();
                    user.setUserId(resultSet.getInt("userId"));
                    user.setUserName(resultSet.getString("userName"));
                    user.setPassword(resultSet.getString("password"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPhoneNumber(resultSet.getString("phoneNumber"));
                    user.setAddress(resultSet.getString("address"));
                    user.setRole(resultSet.getString("role"));
                    user.setCreateDate(resultSet.getTimestamp("createDate"));
                    user.setLastLoginDate(resultSet.getTimestamp("lastLoginDate"));
                    return user;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
    

}
