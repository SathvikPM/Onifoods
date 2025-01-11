package com.tap.daoImp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.tap.dao.RestaurantDao;
import com.tap.model.Menu;
import com.tap.model.Restaurant;

public class RestaurantDaoI implements RestaurantDao {
    final static String INSERT_QUERY = "INSERT INTO restaurant (restaurantId, name, imagePath, ratings, eta, cuisineType, address, isActive, restaurantOwnerId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    final static String SELECT_QUERY = "SELECT * FROM restaurant WHERE restaurantId=?";
    final static String UPDATE_QUERY = "UPDATE restaurant SET name=?, imagePath=?, ratings=?, eta=?, cuisineType=?, address=?, isActive=?, restaurantOwnerId=? WHERE restaurantId=?";
    final static String DELETE_QUERY = "DELETE FROM restaurant WHERE restaurantId=?";
    final static String SELECT_ALL_QUERY = "SELECT * FROM restaurant";
    final static String SELECT_BY_ID_QUERY = "SELECT * FROM restaurant WHERE restaurantId = ?"; // New query added

    private Connection connection;

    public RestaurantDaoI() {
        String url = "jdbc:mysql://localhost:3306/tapfoods";
        String un = "root";
        String pwd = "root";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, un, pwd);
            System.out.println("Database connected!"); // Debug statement
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addRestaurant(Restaurant restaurant) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setInt(1, restaurant.getRestaurantId());
            preparedStatement.setString(2, restaurant.getName());
            preparedStatement.setString(3, restaurant.getImagePath());
            preparedStatement.setDouble(4, restaurant.getRatings());
            preparedStatement.setTimestamp(5, new Timestamp(restaurant.getEta().getTime()));
            preparedStatement.setString(6, restaurant.getCuisineType());
            preparedStatement.setString(7, restaurant.getAddress());
            preparedStatement.setString(8, restaurant.getIsActive());
            preparedStatement.setInt(9, restaurant.getRestaurantOwnerId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Restaurant getRestaurant(int restaurantId) {
        Restaurant restaurant = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_QUERY)) {
            statement.setInt(1, restaurantId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                restaurant = new Restaurant(
                    resultSet.getInt("restaurantId"),
                    resultSet.getString("name"),
                    resultSet.getString("imagePath"),
                    resultSet.getDouble("ratings"),
                    resultSet.getTimestamp("eta"),
                    resultSet.getString("cuisineType"),
                    resultSet.getString("address"),
                    resultSet.getString("isActive"),
                    resultSet.getInt("restaurantOwnerId")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurant;
    }

    @Override
    public void updateRestaurant(Restaurant restaurant) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, restaurant.getName());
            statement.setString(2, restaurant.getImagePath());
            statement.setDouble(3, restaurant.getRatings());
            statement.setTimestamp(4, new Timestamp(restaurant.getEta().getTime()));
            statement.setString(5, restaurant.getCuisineType());
            statement.setString(6, restaurant.getAddress());
            statement.setString(7, restaurant.getIsActive());
            statement.setInt(8, restaurant.getRestaurantOwnerId());
            statement.setInt(9, restaurant.getRestaurantId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRestaurant(int restaurantId) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, restaurantId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY)) {
            while (resultSet.next()) {
                restaurants.add(new Restaurant(
                    resultSet.getInt("restaurantId"),
                    resultSet.getString("name"),
                    resultSet.getString("imagePath"),
                    resultSet.getDouble("ratings"),
                    resultSet.getTimestamp("eta"),
                    resultSet.getString("cuisineType"),
                    resultSet.getString("address"),
                    resultSet.getString("isActive"),
                    resultSet.getInt("restaurantOwnerId")
                ));
            }
            System.out.println("Retrieved restaurants: " + restaurants); // Debug statement
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurants;
    }

    // New method added below
    public Restaurant getRestaurantById(int restaurantId) {
        Restaurant restaurant = null;
        try (PreparedStatement ps = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
            ps.setInt(1, restaurantId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                restaurant = new Restaurant();
                restaurant.setRestaurantId(rs.getInt("restaurantId"));
                restaurant.setName(rs.getString("name"));
                restaurant.setCuisineType(rs.getString("cuisineType"));
                restaurant.setRatings(rs.getDouble("ratings"));
                restaurant.setAddress(rs.getString("address"));
                restaurant.setImagePath(rs.getString("imagePath"));
                restaurant.setIsActive(rs.getString("isActive"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurant;
    }
}
