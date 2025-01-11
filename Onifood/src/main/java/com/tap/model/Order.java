package com.tap.model;

import java.util.Date;

public class Order {
    private int orderId;
    private int restaurantId;
    private int userId;
    private double totalAmount;
    private String modeOfPayment;
    private String orderStatus;
    private Date orderDate;

    // Default constructor
    public Order() {}

    // Constructor with all fields
    public Order(int orderId, int restaurantId, int userId, double totalAmount, String modeOfPayment, String orderStatus, Date orderDate) {
        this.orderId = orderId;
        this.restaurantId = restaurantId;
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.modeOfPayment = modeOfPayment;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
    }

    // Getters and Setters for each field

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getModeOfPayment() {
        return modeOfPayment;
    }

    public void setModeOfPayment(String modeOfPayment) {
        this.modeOfPayment = modeOfPayment;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", restaurantId=" + restaurantId +
                ", userId=" + userId +
                ", totalAmount=" + totalAmount +
                ", modeOfPayment='" + modeOfPayment + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", orderDate=" + orderDate +
                '}';
    }
}
