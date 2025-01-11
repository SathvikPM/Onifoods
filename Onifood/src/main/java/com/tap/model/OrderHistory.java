package com.tap.model;

public class OrderHistory {
    private int orderHistoryId;
    private int orderId;
    private int userId;

    // Default constructor
    public OrderHistory() {}

    // Constructor with all fields
    public OrderHistory(int orderHistoryId, int orderId, int userId) {
        this.orderHistoryId = orderHistoryId;
        this.orderId = orderId;
        this.userId = userId;
    }

    // Getters and Setters for each field

    public int getOrderHistoryId() {
        return orderHistoryId;
    }

    public void setOrderHistoryId(int orderHistoryId) {
        this.orderHistoryId = orderHistoryId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "OrderHistory{" +
                "orderHistoryId=" + orderHistoryId +
                ", orderId=" + orderId +
                ", userId=" + userId +
                '}';
    }
}
