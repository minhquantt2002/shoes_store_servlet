package com.dev4fun.controller.admin.api;

import com.dev4fun.dao.ProductDAO;
import com.dev4fun.model.Product;
import com.dev4fun.model.ProductDetail;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(urlPatterns = {"/api/product/add"})
public class NewProductAPI extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("{\"msg\":\"ok\"}");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");

        Product product = new Gson().fromJson(req.getReader(), Product.class);
        product.setCreatedAt(currentDateTime.format(dtf));
        ProductDAO productDao = new ProductDAO();
        boolean res = productDao.createProduct(product);
        resp.getWriter().write("{\"msg\":\"" + res + "\"}");
    }
}
