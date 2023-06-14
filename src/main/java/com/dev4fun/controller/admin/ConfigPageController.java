package com.dev4fun.controller.admin;

import com.dev4fun.dao.ConfigDAO;
import com.dev4fun.model.Config;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/admin/config"})
public class ConfigPageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ConfigDAO configDAO = new ConfigDAO();
        ArrayList<Config> listConfigs = configDAO.getAllConfig();
        req.setAttribute("listConfigs", listConfigs);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/admin/page-config.jsp");
        requestDispatcher.forward(req, resp);
    }
}
