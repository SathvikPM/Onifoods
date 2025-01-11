<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Login</title>
    <link rel="icon" type="image/png" href="https://i.pinimg.com/originals/b1/fc/bb/b1fcbbfd4fb8116c714ef352bb39bbaf.jpg"> <!-- Replace with your favicon URL -->
    <style>
        body {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            font-family: Arial, sans-serif;
            background-image: url('https://img.freepik.com/premium-psd/free-food-service-arrangement-with-background-mock-up_23-2148421299.jpg?semt=ais_hybrid'); /* Replace with your image URL */
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
        }
        .heading {
            margin-bottom: 20px;
            color: white;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
        }
       form {
		    display: flex;
		    flex-direction: column;
		    align-items: flex-start;
		    padding: 40px; /* Increased padding */
		    border-radius: 8px;
		    background-color: rgba(255, 255, 255, 0.9); /* White background with less transparency */
		    box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2); /* Stronger shadow */
		}
        label {
            margin-bottom: 5px;
            font-weight: bold;
        }
        input[type="text"], input[type="password"] {
            margin-bottom: 15px;
            padding: 10px;
            width: 100%;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        input[type="submit"] {
            padding: 10px 15px;
            background-color: #4CAF50; /* Green background */
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049; /* Darker green on hover */
        }
        .new-to-onifood {
            margin-top: 20px;
            color: white;
            text-align: center;
        }
        .sign-button {
            margin-top: 10px;
            padding: 10px 15px;
            background-color: #007BFF; /* Blue background */
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: none;
            display: inline-block;
        }
        .sign-button:hover {
            background-color: #0056b3; /* Darker blue on hover */
        }
    </style>
</head>
<body>
    <h1 class="heading">Login</h1>
    <!-- it will navigate to login page -->
    <form action="login" method="post">
        <label for="userName">Username</label>
        <input type="text" id="userName" name="username" required>
        <label for="password">Password</label>
        <input type="password" id="password" name="password" required>
        <input type="submit" value="Submit">
    </form>
    
    <div class="new-to-onifood">
        <h2><p style="color:red">New to Onifood?</p></h2>
        <!-- it will navigate to signup page if u r new user -->
        <a href="Signup.jsp" class="sign-button">Sign Up</a>
    </div>
    <% 
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
        <p style="color:red;"><%= error %></p>
    <% 
        }
    %>
</body>
</html>
