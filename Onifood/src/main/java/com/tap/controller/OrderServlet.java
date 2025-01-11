package com.tap.controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tap.dao.OrderDao;
import com.tap.dao.OrderHistoryDao;
import com.tap.dao.OrderItemDao;
import com.tap.daoImp.OrderDaoI;
import com.tap.daoImp.OrderHistoryDaoI;
import com.tap.daoImp.OrderItemDaoI;
import com.tap.model.Cart;
import com.tap.model.CartItem;
import com.tap.model.Order;
import com.tap.model.OrderHistory;
import com.tap.model.OrderItem;
import com.tap.model.User;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {

    private OrderDao orderDao;
    private OrderItemDao orderItemDao;
    private OrderHistoryDao orderHistoryDao;

    @Override
    public void init() throws ServletException {
        orderDao = new OrderDaoI();
        orderItemDao = new OrderItemDaoI();
        orderHistoryDao = new OrderHistoryDaoI();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");
           //to take all the order details and put in the order table
        if (cart != null && user != null && !cart.getItems().isEmpty()) {
            String paymentMode = req.getParameter("paymentMode");

            Order order = new Order();
            order.setUserId(user.getUserId());
            order.setRestaurantId((int) session.getAttribute("restaurantId"));
            order.setOrderDate(new Timestamp(System.currentTimeMillis()));
            order.setModeOfPayment(paymentMode);
            order.setOrderStatus("confirm");

            double totalAmount = 0;
            for (CartItem item : cart.getItems().values()) {
                totalAmount += item.getPrice() * item.getQuantity();
            }
            order.setTotalAmount(totalAmount);

            orderDao.addOrder(order);  //take all the order details and put it in the order table
     

            // Check if orderId is set
            if (order.getOrderId() > 0) {
                // Insert order items
                for (CartItem item : cart.getItems().values()) {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrderItemId(order.getOrderId());
                    orderItem.setUserId(user.getUserId());
                    orderItem.setMenuId(item.getMenuId());
                    orderItem.setItemName(item.getItemName());
                    orderItem.setQuantity(item.getQuantity());
                    orderItem.setPrice(item.getPrice());

                    orderItemDao.addOrderItem(orderItem);
                   
                }

                     // Insert order history
                OrderHistory orderHistory = new OrderHistory();
                orderHistory.setOrderId(order.getOrderId());
                orderHistory.setUserId(user.getUserId());
                orderHistoryDao.addOrderHistory(orderHistory);
                

                session.removeAttribute("cart");
                session.setAttribute("order", order);
                resp.sendRedirect("orderSuccess.jsp");//it will send to orderSucees page
            } else {
                System.out.println("Failed to retrieve orderId after inserting the order.");
                resp.sendRedirect("error.jsp");
            }
        } else {
            resp.sendRedirect("cart.jsp");
        }
    }
}
