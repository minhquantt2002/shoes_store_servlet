package com.dev4fun.controller.admin.api;

import com.dev4fun.dao.CategoryDAO;
import com.dev4fun.model.Category;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api/category/add"})
public class NewCategoryAPI extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write("{\"msg\":\"ok\"}");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        Category category = new Gson().fromJson(req.getReader(), Category.class);
        CategoryDAO categoryDAO = new CategoryDAO();
        boolean res = categoryDAO.createCategory(category);
        resp.getWriter().write("{\"msg\":\"" + res + "\"}");
    }
}
