<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Success</title>
    <style>
        body {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            margin: 0;
            font-family: Arial, sans-serif;
            background-image: url('https://www.shutterstock.com/image-vector/fast-delivery-package-by-scooter-260nw-1609947508.jpg');
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            background-attachment: fixed;
        }
        .container {
            background-color: rgba(255, 255, 255, 0.8);
            border-radius: 8px;
            padding: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        h1 {
            margin: 0;
            font-size: 36px;
            color: green;
            text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.5);
        }
        p {
            margin: 10px 0;
            font-size: 18px;
            color: #333;
        }
        a {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            transition: background-color 0.3s ease;
        }
        a:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Order Placed Successfully!</h1>
        <p>Thank you for your order.</p>
        <%
            Integer restaurantId = (Integer) session.getAttribute("restaurantId");
        %>
        <a href="MenuServlet?restaurantId=<%= restaurantId %>">Browse more Menu</a>
    </div>
</body>
</html>
