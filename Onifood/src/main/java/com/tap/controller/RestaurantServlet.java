package com.tap.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tap.daoImp.RestaurantDaoI;
import com.tap.model.Restaurant;

public class RestaurantServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("RestaurantServlet invoked"); // Debug statement

        RestaurantDaoI r = new RestaurantDaoI();//create a restaurant implementation object
        List<Restaurant> list = r.getAllRestaurants();//it will fetch all the restaurants from database and store in the list

      

        req.setAttribute("restaurants", list); // Set restaurant data as request attribute

        
        RequestDispatcher dispatcher = req.getRequestDispatcher("/restaurant.jsp");//it will navigate to jsp file
        dispatcher.forward(req, resp);//forwords the req and resp
        
    }
}
