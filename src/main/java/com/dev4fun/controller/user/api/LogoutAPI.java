package com.dev4fun.controller.user.api;

import com.dev4fun.utils.SessionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api/logout"})
public class LogoutAPI extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionUtil.getInstance().removeValue(req, "ACCOUNT_USER");
        resp.getWriter().write("{\"msg\":\"" + true + "\"}");
    }
}
