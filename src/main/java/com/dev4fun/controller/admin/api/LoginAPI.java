package com.dev4fun.controller.admin.api;

import com.dev4fun.dao.AccountDAO;
import com.dev4fun.model.Account;
import com.dev4fun.utils.BCrypt;
import com.dev4fun.utils.SessionUtil;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

@WebServlet(urlPatterns = {"/api/admin/login"})
public class LoginAPI extends HttpServlet {
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
        if (account.getUsername().equals("") || account.getPassword().equals("")) {
            resp.getWriter().write("{\"msg\":\"" + false + "\"}");
            SessionUtil.getInstance().putValue(req, "userLoginFail", "Vui lòng nhập đầy đủ username và password.");
        } else {
            try {
                Account accountToCheck = new AccountDAO().getAccountByUsername(account.getUsername());
                if (accountToCheck != null) {
                    if (BCrypt.checkpw(account.getPassword(), accountToCheck.getPassword())) {
                        if (accountToCheck.getRole().equals("ADMIN") || accountToCheck.getRole().equals("STAFF")) {
                            SessionUtil.getInstance().putValue(req, "ACCOUNT_ADMIN", accountToCheck);
                            resp.getWriter().write("{\"msg\":\"" + true + "\"}");
                        } else {
                            SessionUtil.getInstance().putValue(req, "adminLoginFail", "Tài khoản không có quyền truy cập. Vui lòng thử lại.");
                            resp.getWriter().write("{\"msg\":\"" + false + "\"}");
                        }
                    } else {
                        SessionUtil.getInstance().putValue(req, "adminLoginFail", "Tài khoản hoặc mật khẩu không chính xác. Vui lòng thử lại.");
                        resp.getWriter().write("{\"msg\":\"" + false + "\"}");
                    }
                } else {
                    SessionUtil.getInstance().putValue(req, "adminLoginFail", "Tài khoản không tồn tại. Vui lòng thử lại.");
                    resp.getWriter().write("{\"msg\":\"" + false + "\"}");
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
