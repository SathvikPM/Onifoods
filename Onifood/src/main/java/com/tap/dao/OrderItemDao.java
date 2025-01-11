package com.tap.dao;

import java.util.List;
import com.tap.model.OrderItem;

public interface OrderItemDao {
    void addOrderItem(OrderItem orderItem);
    OrderItem getOrderItem(int orderItemId);
    void updateOrderItem(OrderItem orderItem);
    void deleteOrderItem(int orderItemId);
    List<OrderItem> getAllOrderItems();
    List<OrderItem> getOrderItemsByUserId(int userId);
    List<OrderItem> getOrderItemsByMenuId(int menuId);
}
