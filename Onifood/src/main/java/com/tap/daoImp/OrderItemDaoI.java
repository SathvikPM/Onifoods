package com.tap.daoImp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.tap.dao.OrderItemDao;
import com.tap.model.OrderItem;

public class OrderItemDaoI implements OrderItemDao {
    // Removed orderItemId from the INSERT_QUERY as it should be auto-incremented by the database
    final static String INSERT_QUERY = "INSERT INTO order_item (userId, menuId, itemName, ratings, quantity, price) VALUES (?, ?, ?, ?, ?, ?)";
    final static String SELECT_QUERY = "SELECT * FROM order_item WHERE orderItemId=?";
    final static String UPDATE_QUERY = "UPDATE order_item SET userId=?, menuId=?, itemName=?, ratings=?, quantity=?, price=? WHERE orderItemId=?";
    final static String DELETE_QUERY = "DELETE FROM order_item WHERE orderItemId=?";
    final static String SELECT_ALL_QUERY = "SELECT * FROM order_item";
    final static String SELECT_BY_USER_ID_QUERY = "SELECT * FROM order_item WHERE userId=?";
    final static String SELECT_BY_MENU_ID_QUERY = "SELECT * FROM order_item WHERE menuId=?";

    private Connection connection;

    public OrderItemDaoI() {
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
    public void addOrderItem(OrderItem orderItem) {
        // Updated to match INSERT_QUERY without orderItemId
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setInt(1, orderItem.getUserId());
            preparedStatement.setInt(2, orderItem.getMenuId());
            preparedStatement.setString(3, orderItem.getItemName());
            preparedStatement.setDouble(4, orderItem.getRatings());
            preparedStatement.setInt(5, orderItem.getQuantity());
            preparedStatement.setDouble(6, orderItem.getPrice());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public OrderItem getOrderItem(int orderItemId) {
        OrderItem orderItem = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_QUERY)) {
            statement.setInt(1, orderItemId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                orderItem = new OrderItem(
                        resultSet.getInt("orderItemId"),
                        resultSet.getInt("userId"),
                        resultSet.getInt("menuId"),
                        resultSet.getString("itemName"),
                        resultSet.getDouble("ratings"),
                        resultSet.getInt("quantity"),
                        resultSet.getDouble("price")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItem;
    }

    @Override
    public void updateOrderItem(OrderItem orderItem) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setInt(1, orderItem.getUserId());
            statement.setInt(2, orderItem.getMenuId());
            statement.setString(3, orderItem.getItemName());
            statement.setDouble(4, orderItem.getRatings());
            statement.setInt(5, orderItem.getQuantity());
            statement.setDouble(6, orderItem.getPrice());
            statement.setInt(7, orderItem.getOrderItemId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrderItem(int orderItemId) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, orderItemId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OrderItem> getAllOrderItems() {
        List<OrderItem> orderItems = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY)) {
            while (resultSet.next()) {
                orderItems.add(new OrderItem(
                        resultSet.getInt("orderItemId"),
                        resultSet.getInt("userId"),
                        resultSet.getInt("menuId"),
                        resultSet.getString("itemName"),
                        resultSet.getDouble("ratings"),
                        resultSet.getInt("quantity"),
                        resultSet.getDouble("price")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItems;
    }

    @Override
    public List<OrderItem> getOrderItemsByUserId(int userId) {
        List<OrderItem> orderItems = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_USER_ID_QUERY)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orderItems.add(new OrderItem(
                        resultSet.getInt("orderItemId"),
                        resultSet.getInt("userId"),
                        resultSet.getInt("menuId"),
                        resultSet.getString("itemName"),
                        resultSet.getDouble("ratings"),
                        resultSet.getInt("quantity"),
                        resultSet.getDouble("price")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItems;
    }

    @Override
    public List<OrderItem> getOrderItemsByMenuId(int menuId) {
        List<OrderItem> orderItems = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_MENU_ID_QUERY)) {
            statement.setInt(1, menuId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orderItems.add(new OrderItem(
                        resultSet.getInt("orderItemId"),
                        resultSet.getInt("userId"),
                        resultSet.getInt("menuId"),
                        resultSet.getString("itemName"),
                        resultSet.getDouble("ratings"),
                        resultSet.getInt("quantity"),
                        resultSet.getDouble("price")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItems;
    }
}
