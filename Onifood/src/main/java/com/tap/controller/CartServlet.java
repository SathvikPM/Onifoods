package com.tap.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tap.dao.MenuDao;
import com.tap.daoImp.MenuDaoI;
import com.tap.model.Cart;
import com.tap.model.Menu;
import com.tap.model.CartItem;

@WebServlet("/Cart")
public class CartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        //if cart is null create cartItem
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
      
        String action = req.getParameter("action");
        int itemId = Integer.parseInt(req.getParameter("itemId"));

        MenuDao menudao = new MenuDaoI();

        try {
            if ("add".equals(action)) {
                Menu menuItem = menudao.getMenu(itemId);//to get a menuIten object
                //to add the item into the cart
                if (menuItem != null) {
                    int quantity = Integer.parseInt(req.getParameter("quantity"));
                    CartItem cartItem = new CartItem(menuItem.getMenuId(), menuItem.getRestaurantId(), menuItem.getItemName(), menuItem.getPrice(), quantity);
                    cart.addItem(cartItem);
                    session.setAttribute("restaurantId", menuItem.getRestaurantId()); // Store restaurantId in session
                }
                //to update the item  cart
            } else if ("update".equals(action)) {
                int quantity = Integer.parseInt(req.getParameter("quantity"));
                cart.updateItem(itemId, quantity);
                //to remove the item of the cart
            } else if ("remove".equals(action)) {
                cart.removeItem(itemId);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        
        session.setAttribute("cart", cart);
        resp.sendRedirect("cart.jsp");//to navigate to the cart.jsp file
    }
}
