package com.dev4fun.controller.admin.api;

import com.dev4fun.dao.AccountDAO;
import com.dev4fun.dao.CategoryDAO;
import com.dev4fun.model.Account;
import com.dev4fun.model.Category;
import com.dev4fun.utils.BCrypt;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api/account/add"})
public class NewAccountAPI extends HttpServlet {
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

        Account account = new Gson().fromJson(req.getReader(), Account.class);
        account.setPassword(BCrypt.hashpw(account.getPassword(), BCrypt.gensalt()));
        account.setImageLink("https://res.cloudinary.com/dzimy62tk/image/upload/v1686676248/352803545_986271059215241_2936290390407327784_n.jpg_ssmw3x.jpg");
        AccountDAO accountDAO = new AccountDAO();

        boolean result = accountDAO.createAccount(account);
        resp.getWriter().write("{\"msg\":\"" + result + "\"}");
    }
}
