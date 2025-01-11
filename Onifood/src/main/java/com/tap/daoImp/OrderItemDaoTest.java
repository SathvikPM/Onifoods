package com.tap.daoImp;

import com.tap.dao.UserDao;
import com.tap.dao.MenuDao;
import com.tap.dao.OrderItemDao;
import com.tap.dao.RestaurantDao;
import com.tap.model.User;
import com.tap.model.Menu;
import com.tap.model.OrderItem;
import com.tap.model.Restaurant;

import java.util.Date;
import java.util.List;

public class OrderItemDaoTest {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoI();
        RestaurantDao restaurantDao = new RestaurantDaoI();
        MenuDao menuDao = new MenuDaoI();
        OrderItemDao orderItemDao = new OrderItemDaoI();

        // Clear existing data to avoid conflicts
        userDao.deleteUser(1);
        restaurantDao.deleteRestaurant(101);
        menuDao.deleteMenu(1);

        // Add a user
        User user = new User(1, "John Doe", "password123", "johndoe@example.com", "1234567890", "123 Main St.", "customer", new Date(), new Date());
        userDao.addUser(user);

        // Add a restaurant
        Restaurant restaurant = new Restaurant(101, "Pizza Place", "/images/restaurant.jpg", 4.5, new Date(), "Italian", "123 Pizza St.", "Yes", 1);
        restaurantDao.addRestaurant(restaurant);

        // Add a menu item
        Menu menu = new Menu(1, "Pizza", 299.99, "Delicious cheese pizza", "/images/pizza.jpg", "Yes", 101, 4.5);
        menuDao.addMenu(menu);

        // Add an order item
        OrderItem orderItem = new OrderItem(1, 1, 1, "Pizza", 4.5, 2, 599.98);
        orderItemDao.addOrderItem(orderItem);
        System.out.println("Order Item added: " + orderItemDao.getOrderItem(1));

        // Retrieve the order item by ID
        OrderItem retrievedOrderItem = orderItemDao.getOrderItem(1);
        if (retrievedOrderItem != null) {
            System.out.println("Retrieved Order Item: " + retrievedOrderItem);
        } else {
            System.out.println("Order Item not found!");
        }

        // Update the order item
        if (retrievedOrderItem != null) {
            retrievedOrderItem.setQuantity(3);
            orderItemDao.updateOrderItem(retrievedOrderItem);
            System.out.println("Order Item updated: " + orderItemDao.getOrderItem(1));
        }

        // Retrieve all order items
        List<OrderItem> allOrderItems = orderItemDao.getAllOrderItems();
        System.out.println("All Order Items:");
        for (OrderItem oi : allOrderItems) {
            System.out.println(oi);
        }

        // Retrieve order items by user ID
        List<OrderItem> userOrderItems = orderItemDao.getOrderItemsByUserId(1);
        System.out.println("Order Items for User ID 1:");
        for (OrderItem oi : userOrderItems) {
            System.out.println(oi);
        }

        // Retrieve order items by menu ID
        List<OrderItem> menuOrderItems = orderItemDao.getOrderItemsByMenuId(1);
        System.out.println("Order Items for Menu ID 1:");
        for (OrderItem oi : menuOrderItems) {
            System.out.println(oi);
        }

        // Delete the order item
        orderItemDao.deleteOrderItem(1);
        System.out.println("Order Item deleted successfully!");

        // Verify deletion
        OrderItem deletedOrderItem = orderItemDao.getOrderItem(1);
        if (deletedOrderItem == null) {
            System.out.println("Order Item successfully deleted.");
        } else {
            System.out.println("Failed to delete the Order Item!");
        }

        // Clean up test data
        menuDao.deleteMenu(1);
        restaurantDao.deleteRestaurant(101);
        userDao.deleteUser(1);
        System.out.println("All test data cleaned up.");
    }
}
