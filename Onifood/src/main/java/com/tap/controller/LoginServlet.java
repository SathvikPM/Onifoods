package com.tap.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.tap.daoImp.RestaurantDaoI;
import com.tap.daoImp.UserDaoI;
import com.tap.model.Restaurant;
import com.tap.model.User;

public class LoginServlet extends HttpServlet {
    private UserDaoI userDao;
    private RestaurantDaoI restaurantDao;

    public void init() {
        userDao = new UserDaoI(); // Create UserDao implementation object
        restaurantDao = new RestaurantDaoI(); // Create RestaurantDao implementation object
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username"); // Get username from login.jsp
        String password = request.getParameter("password"); // Get password from login.jsp

        User user = userDao.validateUser(username, password); // Validate username and password

        if (user != null) { // If user is valid
            List<Restaurant> restaurants = restaurantDao.getAllRestaurants(); // Get all restaurants from database

            HttpSession session = request.getSession();
            session.setAttribute("user", user); // Store User object in session

            request.setAttribute("username", username); // Set username attribute
            request.setAttribute("restaurants", restaurants); // Set restaurants attribute

            RequestDispatcher dispatcher = request.getRequestDispatcher("Welcome.jsp"); // Navigate to Welcome.jsp
            dispatcher.forward(request, response); // Forward request and response
        } else { // If user is invalid
            request.setAttribute("error", "Invalid username or password. Please try again.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp"); // Redirect to Login.jsp
            dispatcher.forward(request, response);
        }
    }
}
