<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="com.tap.model.Cart" %>
<%@ page import="com.tap.model.CartItem" %>
<%@ page import="com.tap.model.Restaurant" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Cart</title>
<link rel="icon" type="image/png" href="https://i.pinimg.com/originals/b1/fc/bb/b1fcbbfd4fb8116c714ef352bb39bbaf.jpg">
<style>
    body {
        display: flex;
        flex-direction: column;
        justify-content: flex-start;
        align-items: center;
        min-height: 100vh;
        margin: 0;
        font-family: Arial, sans-serif;
        background-image: url('https://img.freepik.com/premium-psd/free-food-service-arrangement-with-background-mock-up_23-2148421299.jpg?semt=ais_hybrid');
        background-size: cover;
        background-position: center;
        background-repeat: no-repeat;
        background-attachment: fixed;
    }
    .navbar {
        width: 100%;
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 20px;
        background-color: rgba(0, 0, 0, 0.5);
    }
    .navbar td {
        color: white;
        font-weight: bold;
        cursor: pointer;
    }
    .left {
        margin-right: auto;
    }
    .right {
        display: flex;
        gap: 20px;
    }
    .cart-container {
        background-color: rgba(255, 255, 255, 0.8);
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        width: 80%;
        max-width: 800px;
        margin-top: 50px;
    }
    .cart-item {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 20px;
        padding: 10px;
        background-color: white;
        border-radius: 8px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }
    .item-details {
        flex: 1;
    }
    .item-details h3 {
        margin: 0;
        font-size: 18px;
        color: #333;
    }
    .item-details p {
        margin: 5px 0;
        font-size: 16px;
        color: #666;
    }
    .item-actions {
        display: flex;
        gap: 10px;
        align-items: center;
    }
    .item-actions form {
        display: flex;
        gap: 5px;
        align-items: center;
    }
    .btn {
        padding: 5px 10px;
        border: none;
        cursor: pointer;
        border-radius: 4px;
    }
    .btn-primary {
        background-color: #4CAF50;
        color: white;
    }
    .btn-secondary {
        background-color: #FF8C00;
        color: white;
    }
    .btn-danger {
        background-color: #f44336;
        color: white;
    }
    .btn-success {
        background-color: #4CAF50;
        color: white;
    }
    .empty-cart {
        text-align: center;
    }
    .cart-summary {
        text-align: center;
        margin-top: 20px;
    }
   
</style>
</head>
<body>
    <div class="navbar">
        <table class="left">
            <tr>
                <td>GET THE APP</td>
            </tr>
        </table>
        <table class="right">
            <tr>
                <td><a href="javascript:history.back()" style="color: white; text-decoration: none;">Back to Restaurants</a></td>
            </tr>
        </table>
    </div>
    <div class="cart-container">
        <h1>Your Cart</h1>

        <%
            Cart cart = (Cart) session.getAttribute("cart");
            Integer sessionRestaurantId = (Integer) session.getAttribute("restaurantId");

            int restaurantId = 0;
            if (cart != null && !cart.getItems().isEmpty()) {
                // Get restaurantId from the first item in the cart
                restaurantId = cart.getItems().values().iterator().next().getRestaurantId();
            } else if (sessionRestaurantId != null) {
                restaurantId = sessionRestaurantId;
            }

            if (cart == null || cart.getItems().isEmpty()) {
        %>
        <div class="empty-cart">
          <%-- if the cart is empty it has to go menuservlet and display the menu by clicking the browsemenu --%>
            <p>Your cart is empty.</p>
            <a href="MenuServlet?restaurantId=<%= restaurantId %>" class="btn btn-secondary">Browse Menu</a>
        </div>
        <%
            } else {
        %>
        <div class="cart-items">
          <%-- to get all the menu details and to calucate them  --%>
            <%
                
                double totalAmount = 0;
                for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
                    CartItem item = entry.getValue();
                    totalAmount += item.getPrice() * item.getQuantity();
            %>
            <div class="cart-item">
                <div class="item-details">
                    <h3><%= item.getItemName() %></h3>
                    <p>Price: Rs. <%= item.getPrice() %></p>
                    <p>Total: Rs. <%= item.getPrice() * item.getQuantity() %></p>
                </div>
                <div class="item-actions">
                <!-- if the action is update it has to go to cart servlet and again redirect to cart.jsp -->
                <form action="Cart" method="post" class="update-quantity-form">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="itemId" value="<%= item.getMenuId() %>">
                        <button type="submit" name="quantity" value="<%= item.getQuantity() - 1 %>" class="btn btn-secondary">-</button>
                        <span><%= item.getQuantity() %></span>
                        <button type="submit" name="quantity" value="<%= item.getQuantity() + 1 %>" class="btn btn-secondary">+</button>
                    </form>
                    <!-- if the action is remove it has to go to cart servlet and again redirect to cart.jsp -->
                    <form action="Cart" method="post" class="remove-item-form">
                        <input type="hidden" name="action" value="remove">
                        <input type="hidden" name="itemId" value="<%= item.getMenuId() %>">
                        <button type="submit" class="btn btn-danger">Remove</button>
                    </form>
                </div>
            </div>
            <%
                }
            %>
            <div class="cart-summary">
                <h3>Total Amount: Rs.<%= totalAmount %></h3>
                <a href="MenuServlet?restaurantId=<%= restaurantId %>" class="btn btn-secondary">Add More items</a>
                <a href="checkout.jsp" class="btn btn-success">Proceed to checkout</a>
            </div>
        </div>
        <%
            }
        %>
    </div>
    
    
     
</body>
</html>
    