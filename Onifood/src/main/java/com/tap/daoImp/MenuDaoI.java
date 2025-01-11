package com.tap.daoImp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.tap.dao.MenuDao;
import com.tap.model.Menu;

public class MenuDaoI implements MenuDao {
    final static String INSERT_QUERY = "INSERT INTO menu (menuId, itemName, price, description, imagePath, isAvailable, restaurantId, ratings) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    final static String SELECT_QUERY = "SELECT * FROM menu WHERE menuId=?";
    final static String UPDATE_QUERY = "UPDATE menu SET itemName=?, price=?, description=?, imagePath=?, isAvailable=?, restaurantId=?, ratings=? WHERE menuId=?";
    final static String DELETE_QUERY = "DELETE FROM menu WHERE menuId=?";
    final static String SELECT_ALL_QUERY = "SELECT * FROM menu";
    final static String SELECT_BY_RESTAURANT_ID_QUERY = "SELECT * FROM menu WHERE restaurantId=?";

    private Connection connection;

    public MenuDaoI() {
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
    public void addMenu(Menu menu) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setInt(1, menu.getMenuId());
            preparedStatement.setString(2, menu.getItemName());
            preparedStatement.setDouble(3, menu.getPrice());
            preparedStatement.setString(4, menu.getDescription());
            preparedStatement.setString(5, menu.getImagePath());
            preparedStatement.setString(6, menu.isAvailable());
            preparedStatement.setInt(7, menu.getRestaurantId());
            preparedStatement.setDouble(8, menu.getRatings());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Menu getMenu(int menuId) {
        Menu menu = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_QUERY)) {
            statement.setInt(1, menuId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                menu = new Menu(
                        resultSet.getInt("menuId"),
                        resultSet.getString("itemName"),
                        resultSet.getDouble("price"),
                        resultSet.getString("description"),
                        resultSet.getString("imagePath"),
                        resultSet.getString("isAvailable"),
                        resultSet.getInt("restaurantId"),
                        resultSet.getDouble("ratings")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menu;
    }

    @Override
    public void updateMenu(Menu menu) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, menu.getItemName());
            statement.setDouble(2, menu.getPrice());
            statement.setString(3, menu.getDescription());
            statement.setString(4, menu.getImagePath());
            statement.setString(5, menu.isAvailable());
            statement.setInt(6, menu.getRestaurantId());
            statement.setDouble(7, menu.getRatings());
            statement.setInt(8, menu.getMenuId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMenu(int menuId) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, menuId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Menu> getAllMenus() {
        List<Menu> menus = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY)) {
            while (resultSet.next()) {
                menus.add(new Menu(
                        resultSet.getInt("menuId"),
                        resultSet.getString("itemName"),
                        resultSet.getDouble("price"),
                        resultSet.getString("description"),
                        resultSet.getString("imagePath"),
                        resultSet.getString("isAvailable"),
                        resultSet.getInt("restaurantId"),
                        resultSet.getDouble("ratings")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menus;
    }

    @Override
    public List<Menu> getMenusByRestaurantId(int restaurantId) {
        List<Menu> menus = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_RESTAURANT_ID_QUERY)) {
            statement.setInt(1, restaurantId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                menus.add(new Menu(
                        resultSet.getInt("menuId"),
                        resultSet.getString("itemName"),
                        resultSet.getDouble("price"),
                        resultSet.getString("description"),
                        resultSet.getString("imagePath"),
                        resultSet.getString("isAvailable"),
                        resultSet.getInt("restaurantId"),
                        resultSet.getDouble("ratings")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menus;
    }
}
