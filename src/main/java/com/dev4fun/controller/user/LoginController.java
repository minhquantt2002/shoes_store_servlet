package com.dev4fun.controller.user;


import com.dev4fun.dao.AccountDAO;
import com.dev4fun.model.Account;
import com.dev4fun.utils.BCrypt;
import com.dev4fun.utils.SessionUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account acc = (Account) SessionUtil.getInstance().getValue(req, "ACCOUNT_USER");
        String errorLog = (String) SessionUtil.getInstance().getValue(req, "userLoginFail");
        String preRequest = req.getHeader("referer");
        if (preRequest != null && preRequest.contains("/login") && errorLog != null) {
            SessionUtil.getInstance().removeValue(req, "userLoginFail");
        }
        if (acc != null) {
            resp.sendRedirect("/user/profile");
        } else {
            if (errorLog != null) {
                req.setAttribute("errorLog", errorLog);
            }
            RequestDispatcher rd = req.getRequestDispatcher("/views/authn/user-login.jsp");
            rd.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username.equals("") || password.equals("")) {
            resp.sendRedirect("/login");
        } else {
            try {
                Account account = new AccountDAO().getAccountByUsername(username);
                if (account != null) {
                    if (BCrypt.checkpw(password, account.getPassword())) {
                        if (account.getRole().equals("USER")) {
                            SessionUtil.getInstance().putValue(req, "ACCOUNT_USER", account);
                            resp.sendRedirect("/user/profile");
                        } else {
                            SessionUtil.getInstance().putValue(req, "userLoginFail", "Tài khoản không có quyền truy cập. Vui lòng thử lại.");
                            resp.sendRedirect("/login");
                        }
                    } else {
                        SessionUtil.getInstance().putValue(req, "userLoginFail", "Tài khoản hoặc mật khẩu không chính xác. Vui lòng thử lại.");
                        resp.sendRedirect("/login");
                    }
                } else {
                    SessionUtil.getInstance().putValue(req, "userLoginFail", "Tài khoản không tồn tại. Vui lòng thử lại.");
                    resp.sendRedirect("/login");
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
