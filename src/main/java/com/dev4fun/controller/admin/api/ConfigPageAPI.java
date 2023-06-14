package com.dev4fun.controller.admin.api;

import com.dev4fun.dao.ConfigDAO;
import com.dev4fun.model.Config;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin/api/config/*"})
public class ConfigPageAPI extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("{\"info\": \"" + "oke" + "\"}");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String url = req.getRequestURI();
        Config config = (new Gson()).fromJson(req.getReader(), Config.class);
        ConfigDAO configDAO = new ConfigDAO();
        boolean result = false;
        if (url.contains("/api/config/policy")) {
            result = configDAO.updateConfigById(config, "policy-rule");
        } else if (url.contains("/api/config/store")) {
            result = configDAO.updateConfigById(config, "store");
        } else if (url.contains("/api/config/about-us")) {
            result = configDAO.updateConfigById(config, "about-us");
        }

        resp.getWriter().write("{\"result\": \"" + result + "\"}");
    }
}
