package com.tap.model;

import java.sql.Date;

public class Restaurant {
    private int restaurantId;
    private String name;
    private String imagePath;
    private double ratings;
    private java.util.Date eta;
    private String cuisineType;
    private String address;
    private String isActive;
    private int restaurantOwnerId;

    // Default constructor
    public Restaurant() {}

    // Constructor with all fields
    public Restaurant(int restaurantId, String name, String imagePath, double ratings, java.util.Date date, String cuisineType, String address, String isActive, int restaurantOwnerId) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.imagePath = imagePath;
        this.ratings = ratings;
        this.eta = date;
        this.cuisineType = cuisineType;
        this.address = address;
        this.isActive = isActive;
        this.restaurantOwnerId = restaurantOwnerId;
    }

    // Getters and Setters for each field

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public double getRatings() {
        return ratings;
    }

    public void setRatings(double ratings) {
        this.ratings = ratings;
    }

    public java.util.Date getEta() {
        return eta;
    }

    public void setEta(java.util.Date eta) {
        this.eta = eta;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public int getRestaurantOwnerId() {
        return restaurantOwnerId;
    }

    public void setRestaurantOwnerId(int restaurantOwnerId) {
        this.restaurantOwnerId = restaurantOwnerId;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "Restaurant{" +
                "restaurantId=" + restaurantId +
                ", name='" + name + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", ratings=" + ratings +
                ", eta=" + eta +
                ", cuisineType='" + cuisineType + '\'' +
                ", address='" + address + '\'' +
                ", isActive=" + isActive +
                ", restaurantOwnerId=" + restaurantOwnerId +
                '}';
    }
}
