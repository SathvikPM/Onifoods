package com.tap.controller;

import com.tap.daoImp.UserDaoI;
import com.tap.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

public class SignUpServlet extends HttpServlet {
    private UserDaoI userDao;

    public void init() {
        userDao = new UserDaoI();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	//to get all the details of the user
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String password = request.getParameter("password");
        String address = request.getParameter("address");

        
        //to create the object of the user and to set the user details of the user
        User user = new User();
        user.setUserName(username);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(password);
        user.setAddress(address);
        user.setRole("customer");
        user.setCreateDate(new Timestamp(new Date().getTime()));
        user.setLastLoginDate(new Timestamp(new Date().getTime()));

        try {
            userDao.addUser(user);//set all the user details in the user table of the database
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("Login.jsp"); // to navagate to the login.jsp
    }

}
