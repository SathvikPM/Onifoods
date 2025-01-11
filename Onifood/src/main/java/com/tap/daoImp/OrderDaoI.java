package com.tap.daoImp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.tap.dao.OrderDao;
import com.tap.model.Order;

public class OrderDaoI implements OrderDao {
    final static String INSERT_QUERY = "INSERT INTO `order` (restaurantId, userId, totalAmount, modeOfPayment, orderStatus, orderDate) VALUES (?, ?, ?, ?, ?, ?)";
    final static String SELECT_QUERY = "SELECT * FROM `order` WHERE orderId=?";
    final static String UPDATE_QUERY = "UPDATE `order` SET restaurantId=?, userId=?, totalAmount=?, modeOfPayment=?, orderStatus=?, orderDate=? WHERE orderId=?";
    final static String DELETE_QUERY = "DELETE FROM `order` WHERE orderId=?";
    final static String SELECT_ALL_QUERY = "SELECT * FROM `order`";
    final static String SELECT_BY_USER_ID_QUERY = "SELECT * FROM `order` WHERE userId=?";
    final static String SELECT_BY_RESTAURANT_ID_QUERY = "SELECT * FROM `order` WHERE restaurantId=?";

    private Connection connection;

    public OrderDaoI() {
        String url = "jdbc:mysql://localhost:3306/tapfoods";
        String username = "root";
        String password = "root";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            if (connection != null) {
                System.out.println("Connected to the database successfully!");
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addOrder(Order order) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, order.getRestaurantId());
            preparedStatement.setInt(2, order.getUserId());
            preparedStatement.setDouble(3, order.getTotalAmount());
            preparedStatement.setString(4, order.getModeOfPayment());
            preparedStatement.setString(5, order.getOrderStatus());
            preparedStatement.setTimestamp(6, new Timestamp(order.getOrderDate().getTime()));
  
            System.out.println("Executing query: " + preparedStatement.toString()); // Logging
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        order.setOrderId(generatedKeys.getInt(1)); // Set the generated orderId
                        
                    }
                }
              
            } else {
              
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Order getOrder(int orderId) {
        Order order = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_QUERY)) {
            statement.setInt(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                order = new Order(
                        resultSet.getInt("orderId"),
                        resultSet.getInt("restaurantId"),
                        resultSet.getInt("userId"),
                        resultSet.getDouble("totalAmount"),
                        resultSet.getString("modeOfPayment"),
                        resultSet.getString("orderStatus"),
                        resultSet.getTimestamp("orderDate")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public void updateOrder(Order order) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setInt(1, order.getRestaurantId());
            statement.setInt(2, order.getUserId());
            statement.setDouble(3, order.getTotalAmount());
            statement.setString(4, order.getModeOfPayment());
            statement.setString(5, order.getOrderStatus());
            statement.setTimestamp(6, new Timestamp(order.getOrderDate().getTime()));
            statement.setInt(7, order.getOrderId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrder(int orderId) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, orderId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY)) {
            while (resultSet.next()) {
                orders.add(new Order(
                        resultSet.getInt("orderId"),
                        resultSet.getInt("restaurantId"),
                        resultSet.getInt("userId"),
                        resultSet.getDouble("totalAmount"),
                        resultSet.getString("modeOfPayment"),
                        resultSet.getString("orderStatus"),
                        resultSet.getTimestamp("orderDate")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_USER_ID_QUERY)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orders.add(new Order(
                        resultSet.getInt("orderId"),
                        resultSet.getInt("restaurantId"),
                        resultSet.getInt("userId"),
                        resultSet.getDouble("totalAmount"),
                        resultSet.getString("modeOfPayment"),
                        resultSet.getString("orderStatus"),
                        resultSet.getTimestamp("orderDate")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public List<Order> getOrdersByRestaurantId(int restaurantId) {
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_RESTAURANT_ID_QUERY)) {
            statement.setInt(1, restaurantId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orders.add(new Order(
                        resultSet.getInt("orderId"),
                        resultSet.getInt("restaurantId"),
                        resultSet.getInt("userId"),
                        resultSet.getDouble("totalAmount"),
                        resultSet.getString("modeOfPayment"),
                        resultSet.getString("orderStatus"),
                        resultSet.getTimestamp("orderDate")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
}
