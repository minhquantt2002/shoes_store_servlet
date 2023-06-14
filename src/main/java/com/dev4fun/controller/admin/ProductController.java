package com.dev4fun.controller.admin;

import com.dev4fun.dao.CategoryDAO;
import com.dev4fun.dao.ProductDAO;
import com.dev4fun.model.Category;
import com.dev4fun.model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/admin/product"})
public class ProductController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDAO productDAO = new ProductDAO();
        ArrayList<Product> listProducts = productDAO.getAllProduct();
        if (req.getParameter("t") != null && req.getParameter("v") != null) {
            String temp = req.getParameter("t");
            String value = req.getParameter("v");
            String none = "none";
            ArrayList<Product> searchProduct;
            if (temp.equals("name")) {
                searchProduct = productDAO.getProductByName(value);
            } else if (temp.equals("category")) {
                searchProduct = productDAO.getProductByCategoryName(value);
            } else {
                searchProduct = productDAO.getProductByStatus(value);
            }
            String typeSearch = temp.equals("name") ? "Tên sản phẩm" : temp.equals("category") ? "Danh mục" : "Trạng thái";
            req.setAttribute("valueSearch", temp);
            req.setAttribute(temp, none);
            req.setAttribute("typeSearch", typeSearch);
            req.setAttribute("listProducts", searchProduct);
            req.setAttribute("txt_sproduct", value);
        } else {
            req.setAttribute("valueSearch", "");
            req.setAttribute("typeSearch", "Tìm kiếm theo");
            req.setAttribute("listProducts", listProducts);
        }
        RequestDispatcher rd = req.getRequestDispatcher("/views/admin/page-product.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("act") != null && req.getParameter("act").equals("delete") && req.getParameter("productId") != null) {
            ProductDAO productDAO = new ProductDAO();
            boolean result = productDAO.deleteProduct(Integer.parseInt(req.getParameter("productId")));
            resp.sendRedirect("/admin/product");
            return;
        }
        resp.sendRedirect("/admin/product");
    }
}
