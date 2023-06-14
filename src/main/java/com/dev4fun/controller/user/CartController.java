package com.dev4fun.controller.user;

import com.dev4fun.dao.ProductDAO;
import com.dev4fun.model.Cart;
import com.dev4fun.model.Product;
import com.dev4fun.utils.CartUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/user/page-cart.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String act = req.getParameter("act");
        String referer = req.getHeader("referer");
        if (act != null) {
            if (act.equalsIgnoreCase("add")) {
                int id = Integer.parseInt(req.getParameter("productId"));
                int quantity = Integer.parseInt(req.getParameter("quantity"));
                int size = Integer.parseInt(req.getParameter("size"));
                Product product = new ProductDAO().getProductById(id);
                CartUtil.addProductToCart(req, product, size, quantity);
                resp.sendRedirect(referer);
            } else if (act.equalsIgnoreCase("remove")) {
                int id = Integer.parseInt(req.getParameter("productId"));
                int size = Integer.parseInt(req.getParameter("size"));
                CartUtil.removeProductInCart(req, id, size);
                resp.sendRedirect("/cart");
            } else if (act.equalsIgnoreCase("update")) {
                String quantity = req.getParameter("quantity");
                String size = req.getParameter("size");
                CartUtil.updateCart(req, quantity, size);
                resp.sendRedirect("/cart");
            }
        }
    }
}
