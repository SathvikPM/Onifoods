package com.tap.daoImp;

import com.tap.dao.OrderHistoryDao;
import com.tap.dao.OrderDao;
import com.tap.dao.UserDao;
import com.tap.dao.RestaurantDao;
import com.tap.dao.MenuDao;
import com.tap.model.OrderHistory;
import com.tap.model.Order;
import com.tap.model.User;
import com.tap.model.Restaurant;
import com.tap.model.Menu;

import java.util.Date;
import java.util.List;

public class OrderHistoryDaoTest {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoI();
        RestaurantDao restaurantDao = new RestaurantDaoI();
        MenuDao menuDao = new MenuDaoI();
        OrderDao orderDao = new OrderDaoI();
        OrderHistoryDao orderHistoryDao = new OrderHistoryDaoI();

        // Clear existing data to avoid conflicts
        userDao.deleteUser(1);
        restaurantDao.deleteRestaurant(101);
        menuDao.deleteMenu(1);
        orderDao.deleteOrder(1);

        // Add a user
        User user = new User(1, "John Doe", "password123", "johndoe@example.com", "1234567890", "123 Main St.", "customer", new Date(), new Date());
        userDao.addUser(user);

        // Add a restaurant
        Restaurant restaurant = new Restaurant(101, "Pizza Place", "/images/restaurant.jpg", 4.5, new Date(), "Italian", "123 Pizza St.", "Yes", 1);
        restaurantDao.addRestaurant(restaurant);

        // Add a menu item
        Menu menu = new Menu(1, "Pizza", 299.99, "Delicious cheese pizza", "/images/pizza.jpg", "Yes", 101, 4.5);
        menuDao.addMenu(menu);

        // Add an order
        Order order = new Order(1, 101, 1, 500.00, "cash", "confirm", new Date());
        orderDao.addOrder(order);

        // Add an order history
        OrderHistory orderHistory = new OrderHistory(1, 1, 1);
        orderHistoryDao.addOrderHistory(orderHistory);
        System.out.println("Order History added: " + orderHistoryDao.getOrderHistory(1));

        // Retrieve the order history by ID
        OrderHistory retrievedOrderHistory = orderHistoryDao.getOrderHistory(1);
        if (retrievedOrderHistory != null) {
            System.out.println("Retrieved Order History: " + retrievedOrderHistory);
        } else {
            System.out.println("Order History not found!");
        }

        // Update the order history
        if (retrievedOrderHistory != null) {
            // Ensure the new orderId exists in the Order table
            retrievedOrderHistory.setOrderId(1); // setting it back to an existing orderId
            orderHistoryDao.updateOrderHistory(retrievedOrderHistory);
            System.out.println("Order History updated: " + orderHistoryDao.getOrderHistory(1));
        }

        // Retrieve all order histories
        List<OrderHistory> allOrderHistories = orderHistoryDao.getAllOrderHistories();
        System.out.println("All Order Histories:");
        for (OrderHistory oh : allOrderHistories) {
            System.out.println(oh);
        }

        // Retrieve order histories by user ID
        List<OrderHistory> userOrderHistories = orderHistoryDao.getOrderHistoriesByUserId(1);
        System.out.println("Order Histories for User ID 1:");
        for (OrderHistory oh : userOrderHistories) {
            System.out.println(oh);
        }

        // Retrieve order histories by order ID
        List<OrderHistory> orderOrderHistories = orderHistoryDao.getOrderHistoriesByOrderId(1);
        System.out.println("Order Histories for Order ID 1:");
        for (OrderHistory oh : orderOrderHistories) {
            System.out.println(oh);
        }

        // Delete the order history
        orderHistoryDao.deleteOrderHistory(1);
        System.out.println("Order History deleted successfully!");

        // Verify deletion
        OrderHistory deletedOrderHistory = orderHistoryDao.getOrderHistory(1);
        if (deletedOrderHistory == null) {
            System.out.println("Order History successfully deleted.");
        } else {
            System.out.println("Failed to delete the Order History!");
        }

        // Clean up test data
        menuDao.deleteMenu(1);
        restaurantDao.deleteRestaurant(101);
        orderDao.deleteOrder(1);
        userDao.deleteUser(1);
        System.out.println("All test data cleaned up.");
    }
}
