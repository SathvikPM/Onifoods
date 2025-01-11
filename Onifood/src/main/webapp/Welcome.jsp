<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.tap.model.Restaurant" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome to Onifood</title>
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
		    justify-content: center; /* Centers content horizontally */
		    align-items: center; /* Centers content vertically */
		    padding: 20px;
		    background-color: rgba(0, 0, 0, 0.5);
		}
		
		.navbar td {
		    color: white;
		    font-weight: bold;
		    cursor: pointer;
		    text-align: center; /* Ensures the text inside the td is centered */
		}
       
        }
        .left {
            margin-right: auto;
        }
        .right {
            display: flex;
            gap: 20px;
        }
        .app-name {
            margin-top: 50px;
            font-size: 48px;
            font-weight: bold;
            color: red;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.7);
        }
        .tagline {
            margin-top: 10px;
            font-size: 24px;
            font-weight: normal;
            background: -webkit-linear-gradient(45deg, #FFDD44, #FF8C00);
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
            text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.5);
        }
        .restaurants {
            margin-top: 20px;
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 20px;
        }
        .restaurant-card {
            background-color: rgba(255, 255, 255, 0.8);
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            overflow: hidden;
            width: 300px;
            text-align: center;
        }
        .restaurant-card img {
            width: 100%;
            height: 200px;
            object-fit: cover;
        }
        .restaurant-card h3 {
            margin: 10px 0 5px;
            font-size: 20px;
            color: #333;
        }
        .restaurant-card p {
            margin: 5px 0;
            font-size: 16px;
            color: #666;
        }
        .restaurant-card a {
            display: block;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            margin: 10px 0;
        }
        .restaurant-card a:hover {
            background-color: #45a049;
        }
        td {
            padding: 10px;
        }
        .navbar td:hover {
            color: #FFD700;
        }
        .footer {
            width: 100%;
            background-color: #FFD700; /* Yellow background */
            color: black; /* Black text color */
            padding: 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        .footer p {
            margin: 5px 0;
        }
        .footer-links {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 20px;
            margin-bottom: 20px;
        }
        .footer-links div {
            text-align: center;
        }
        .social-icons {
            display: flex;
            justify-content: center;
            gap: 10px;
        }
        .social-icons img {
            width: 24px;
            height: 24px;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <div class="navbar">
    <table class="center-navbar">
        <tr>
            <td><h3>Hii, <%= request.getAttribute("username") %>! Welcome to Onifoods</h3></td>
        </tr>
    </table>
	</div>
    
    <div class="app-name">ONIFOODS</div>
    <p class="tagline">Discover the best foods and drinks in Bengaluru</p>
    <div class="restaurants">
                   <%-- To get all the restaurant to display in the browser and it will navagate to menu servlet --%>
          <%
             List<Restaurant> list = (List<Restaurant>) request.getAttribute("restaurants");

            if (list != null && !list.isEmpty()) {
                for (Restaurant r : list) {
        %>
          <a href="MenuServlet?restaurantId=<%= r.getRestaurantId() %>">
           <div class="restaurant-card">
		    <img src="<%= r.getImagePath() %>" alt="<%= r.getName() %>">
		    <h3><%= r.getName() %></h3>
		    <p>Cuisine: <%= r.getCuisineType() %></p>
		    <p>Rating: <%= r.getRatings() %> ★</p>
		    <p>Address: <%= r.getAddress() %></p>
		    <p>Active: <%= r.getIsActive() %></p>
		    
          </div>
          </a>
           
        <%
                }
            } else {
        %>
        <p>No restaurants available.</p>
        <%
            }
        %>
    </div>
    <div class="footer">
    <div class="footer-links">
        <div>
            <img src="https://i.pinimg.com/originals/b1/fc/bb/b1fcbbfd4fb8116c714ef352bb39bbaf.jpg" alt="Onifood Logo" style="width: 100px; height: auto;">
        </div>
        <div>
            <p>India</p>
            <p>English</p>
        </div>
        <div>
            <p>About Onifood</p>
            <p>Who We Are</p>
            <p>Blog</p>
            <p>Work With Us</p>
            <p>Investor Relations</p>
            <p>Report Fraud</p>
            <p>Press Kit</p>
            <p>Contact Us</p>
        </div>
        <div>
            <p>Oniverse</p>
            <p>Onifood</p>
            <p>Blinkit</p>
            <p>District</p>
            <p>Feeding India</p>
            <p>Hyperpure</p>
            <p>Onifood Live</p>
            <p>Oniland</p>
            <p>Weather Union</p>
        </div>
        <div>
            <p>For Restaurants</p>
            <p>Partner With Us</p>
            <p>Apps For You</p>
            <p>Learn More</p>
        </div>
        <div>
            <p>Privacy</p>
            <p>Security</p>
            <p>Terms</p>
        </div>
    </div>
    <div class="social-icons">
        <img src="https://img.freepik.com/premium-vector/art-illustration_929495-41.jpg?semt=ais_hybrid" alt="Facebook">
        <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/6/6f/Logo_of_Twitter.svg/2491px-Logo_of_Twitter.svg.png" alt="Twitter">
    </div>
    <p>By continuing past this page, you agree to our Terms of Service, Cookie Policy, Privacy Policy and Content Policies. All trademarks are properties of their respective owners. 2008-2024 © Onifood™ Ltd. All rights reserved.</p>
</div>
    
</body>
</html>
