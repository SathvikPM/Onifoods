package com.tap.model;

public class OrderItem {
    private int orderItemId;
    private int userId;
    private int menuId;
    private String itemName;
    private double ratings;
    private int quantity;
    private double price;

    // Default constructor
    public OrderItem() {}

    // Constructor with all fields
    public OrderItem(int orderItemId, int userId, int menuId, String itemName, double ratings, int quantity, double price) {
        this.orderItemId = orderItemId;
        this.userId = userId;
        this.menuId = menuId;
        this.itemName = itemName;
        this.ratings = ratings;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and Setters for each field

    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getRatings() {
        return ratings;
    }

    public void setRatings(double ratings) {
        this.ratings = ratings;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "OrderItem{" +
                "orderItemId=" + orderItemId +
                ", userId=" + userId +
                ", menuId=" + menuId +
                ", itemName='" + itemName + '\'' +
                ", ratings=" + ratings +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
