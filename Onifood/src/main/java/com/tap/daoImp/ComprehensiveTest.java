package com.tap.daoImp;

import com.tap.dao.UserDao;
import com.tap.dao.RestaurantDao;
import com.tap.dao.MenuDao;
import com.tap.dao.OrderDao;
import com.tap.model.User;
import com.tap.model.Restaurant;
import com.tap.model.Menu;
import com.tap.model.Order;

import java.util.Date;
import java.util.List;

public class ComprehensiveTest {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoI();
        RestaurantDao restaurantDao = new RestaurantDaoI();
        MenuDao menuDao = new MenuDaoI();
        OrderDao orderDao = new OrderDaoI();

        // Clear existing data to avoid conflicts
        restaurantDao.deleteRestaurant(101);
        userDao.deleteUser(1);

        // Test User DAO
        System.out.println("Testing User DAO...");
        User user = new User(1, "John Doe", "password123", "johndoe@example.com", "1234567890", "123 Main St.", "owner", new Date(), new Date());
        userDao.addUser(user);
        System.out.println("User added: " + userDao.getUser(1));

        // Test Restaurant DAO
        System.out.println("Testing Restaurant DAO...");
        Restaurant restaurant = new Restaurant(101, "Pizza Place", "/images/restaurant.jpg", 4.5, new Date(), "Italian", "123 Pizza St.", "Yes", 1); // Changed isActive to "Yes"
        restaurantDao.addRestaurant(restaurant);
        System.out.println("Restaurant added: " + restaurantDao.getRestaurant(101));

        // Test Menu DAO
        System.out.println("Testing Menu DAO...");
        Menu menu = new Menu(1, "Pizza", 299.99, "Delicious cheese pizza", "/images/pizza.jpg", "Yes", 101, 4.5); // Changed isAvailable to "Yes"
        menuDao.addMenu(menu);
        System.out.println("Menu added: " + menuDao.getMenu(1));

        // Test Order DAO
        System.out.println("Testing Order DAO...");
        Order order = new Order(1, 101, 1, 500.00, "cash", "confirm", new Date());
        orderDao.addOrder(order);
        System.out.println("Order added: " + orderDao.getOrder(1));

        // Retrieve and print all data to verify
        System.out.println("All Users:");
        for (User u : userDao.getAllUsers()) {
            System.out.println(u);
        }

        System.out.println("All Restaurants:");
        for (Restaurant r : restaurantDao.getAllRestaurants()) {
            System.out.println(r);
        }

        System.out.println("All Menus:");
        for (Menu m : menuDao.getAllMenus()) {
            System.out.println(m);
        }

        System.out.println("All Orders:");
        for (Order o : orderDao.getAllOrders()) {
            System.out.println(o);
        }

        // Clean up test data
        menuDao.deleteMenu(1);
        orderDao.deleteOrder(1);
        restaurantDao.deleteRestaurant(101);
        userDao.deleteUser(1);

        System.out.println("All test data cleaned up.");
    }
}
