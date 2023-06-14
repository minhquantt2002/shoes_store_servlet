package com.dev4fun.controller.admin.api;

import com.dev4fun.utils.SessionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api/admin/logout"})
public class LogoutAPI extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionUtil.getInstance().removeValue(req, "ACCOUNT_ADMIN");
        resp.getWriter().write("{\"msg\":\"" + true + "\"}");
    }
}
