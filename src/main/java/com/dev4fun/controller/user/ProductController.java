package com.dev4fun.controller.user;

import com.dev4fun.dao.ProductDAO;
import com.dev4fun.model.Product;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/products"})
public class ProductController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Product> listProducts;
        if (request.getParameter("search") != null) {
            String temp = request.getParameter("search");
            listProducts = new ProductDAO().getProductByElement(temp);
            if (listProducts.size() == 0) {
                listProducts = new ProductDAO().getProductByCategoryName(temp);
            }
            request.setAttribute("valueSearch", temp);
        }
        else if(request.getParameter("category")!=null){
            listProducts = new ProductDAO().getProductByCategoryName(request.getParameter("category"));
            request.setAttribute("valueCategory",request.getParameter("category"));
        }
        else {
            listProducts = new ProductDAO().getAllProduct();
        }
        request.setAttribute("listProducts", listProducts);
        RequestDispatcher rd = request.getRequestDispatcher("/views/user/page-product.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
