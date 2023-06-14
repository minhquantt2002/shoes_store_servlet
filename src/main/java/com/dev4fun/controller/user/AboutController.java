package com.dev4fun.controller.user;

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

@WebServlet(urlPatterns = {"/about/*"})
public class AboutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ConfigDAO configDAO = new ConfigDAO();
        ArrayList<Config> listConfigs = configDAO.getAllConfig();
        req.setAttribute("listConfigs", listConfigs);

        String[] urlSplit = req.getRequestURI().split("/");
        if (urlSplit.length == 3) {
            String configId = urlSplit[2];
            Config config = configDAO.getConfigById(configId);
            req.setAttribute("config", config);

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/user/page-about.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
}
