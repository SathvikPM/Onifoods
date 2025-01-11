package com.tap.model;

public class Menu {
    private int menuId;
    private String itemName;
    private double price;
    private String description;
    private String imagePath;
    private String isAvailable;
    private int restaurantId; // Foreign key
    private double ratings;

    // Default constructor
    public Menu() {}

    // Constructor with all fields
    public Menu(int menuId, String itemName, double price, String description, 
                String imagePath, String isAvailable, int restaurantId, double ratings) {
        this.menuId = menuId;
        this.itemName = itemName;
        this.price = price;
        this.description = description;
        this.imagePath = imagePath;
        this.isAvailable = isAvailable;
        this.restaurantId = restaurantId;
        this.ratings = ratings;
    }

    // Getters and Setters for each field

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String isAvailable() {
        return isAvailable;
    }

    public void setAvailable(String isAvailable) {
        this.isAvailable = isAvailable;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public double getRatings() {
        return ratings;
    }

    public void setRatings(double ratings) {
        this.ratings = ratings;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "Menu{" +
                "menuId=" + menuId +
                ", itemName='" + itemName + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", isAvailable=" + isAvailable +
                ", restaurantId=" + restaurantId +
                ", ratings=" + ratings +
                '}';
    }
}
