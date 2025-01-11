package com.tap.daoImp;

import com.tap.dao.UserDao;
import com.tap.dao.RestaurantDao;
import com.tap.dao.OrderDao;
import com.tap.model.User;
import com.tap.model.Restaurant;
import com.tap.model.Order;

import java.util.Date;

public class TestSetup {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoI();
        RestaurantDao restaurantDao = new RestaurantDaoI();
        OrderDao orderDao = new OrderDaoI();

        // Clear existing data to avoid conflicts
        userDao.deleteUser(1);
        orderDao.deleteOrder(1);
        restaurantDao.deleteRestaurant(101);

        // Add a user
        User user = new User(1, "John Doe", "password123", "johndoe@example.com", "1234567890", "123 Main St.", "customer", new Date(), new Date());
        userDao.addUser(user);

        // Add a restaurant
        Restaurant restaurant = new Restaurant(101, "Pizza Place", "/images/restaurant.jpg", 4.5, new Date(), "Italian", "123 Pizza St.", "Yes", 1);
        restaurantDao.addRestaurant(restaurant);

        // Add an order
        Order order = new Order(1, 101, 1, 500.00, "cash", "confirm", new Date());
        orderDao.addOrder(order);

        System.out.println("Test setup completed.");
    }
}
