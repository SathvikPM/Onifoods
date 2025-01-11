package com.tap.daoImp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.tap.dao.OrderHistoryDao;
import com.tap.model.OrderHistory;

public class OrderHistoryDaoI implements OrderHistoryDao {
    // Removed orderHistoryId from the INSERT_QUERY as it should be auto-incremented by the database
    final static String INSERT_QUERY = "INSERT INTO order_history (orderId, userId) VALUES (?, ?)";
    final static String SELECT_QUERY = "SELECT * FROM order_history WHERE orderHistoryId=?";
    final static String UPDATE_QUERY = "UPDATE order_history SET orderId=?, userId=? WHERE orderHistoryId=?";
    final static String DELETE_QUERY = "DELETE FROM order_history WHERE orderHistoryId=?";
    final static String SELECT_ALL_QUERY = "SELECT * FROM order_history";
    final static String SELECT_BY_USER_ID_QUERY = "SELECT * FROM order_history WHERE userId=?";
    final static String SELECT_BY_ORDER_ID_QUERY = "SELECT * FROM order_history WHERE orderId=?";

    private Connection connection;

    public OrderHistoryDaoI() {
        String url = "jdbc:mysql://localhost:3306/tapfoods";
        String username = "root";
        String password = "root";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addOrderHistory(OrderHistory orderHistory) {
        // Updated to match INSERT_QUERY without orderHistoryId
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setInt(1, orderHistory.getOrderId());
            preparedStatement.setInt(2, orderHistory.getUserId());

            System.out.println("Executing query: " + preparedStatement.toString());
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new order history was inserted successfully!");
            } else {
                System.out.println("No rows were inserted.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public OrderHistory getOrderHistory(int orderHistoryId) {
        OrderHistory orderHistory = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_QUERY)) {
            statement.setInt(1, orderHistoryId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                orderHistory = new OrderHistory(
                        resultSet.getInt("orderHistoryId"),
                        resultSet.getInt("orderId"),
                        resultSet.getInt("userId")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderHistory;
    }

    @Override
    public void updateOrderHistory(OrderHistory orderHistory) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setInt(1, orderHistory.getOrderId());
            statement.setInt(2, orderHistory.getUserId());
            statement.setInt(3, orderHistory.getOrderHistoryId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrderHistory(int orderHistoryId) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, orderHistoryId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OrderHistory> getAllOrderHistories() {
        List<OrderHistory> orderHistories = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY)) {
            while (resultSet.next()) {
                orderHistories.add(new OrderHistory(
                        resultSet.getInt("orderHistoryId"),
                        resultSet.getInt("orderId"),
                        resultSet.getInt("userId")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderHistories;
    }

    @Override
    public List<OrderHistory> getOrderHistoriesByUserId(int userId) {
        List<OrderHistory> orderHistories = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_USER_ID_QUERY)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orderHistories.add(new OrderHistory(
                        resultSet.getInt("orderHistoryId"),
                        resultSet.getInt("orderId"),
                        resultSet.getInt("userId")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderHistories;
    }

    @Override
    public List<OrderHistory> getOrderHistoriesByOrderId(int orderId) {
        List<OrderHistory> orderHistories = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_ORDER_ID_QUERY)) {
            statement.setInt(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orderHistories.add(new OrderHistory(
                        resultSet.getInt("orderHistoryId"),
                        resultSet.getInt("orderId"),
                        resultSet.getInt("userId")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderHistories;
    }
}
