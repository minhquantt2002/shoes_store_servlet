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

@WebServlet(urlPatterns = {"/user/profile"})
public class ProfileController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/user/page-profile.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = (Account) SessionUtil.getInstance().getValue(req, "ACCOUNT_USER");
        account.setFullName(req.getParameter("fullName"));
        account.setUsername(req.getParameter("username"));
        account.setPassword(req.getParameter("password"));
        if (!req.getParameter("password").equals(account.getPassword())) {
            account.setPassword(BCrypt.hashpw(req.getParameter("password"), BCrypt.gensalt()));
        }
        account.setEmail(req.getParameter("email"));
        account.setPhoneNumber(req.getParameter("tel"));
        account.setDob(req.getParameter("dob"));
        account.setGender(req.getParameter("gender"));
        account.setAddress(req.getParameter("address"));
        AccountDAO accountDAO = new AccountDAO();
        boolean result = accountDAO.updateAccount(account);
        if (result) {
            SessionUtil.getInstance().putValue(req, "ACCOUNT_USER", accountDAO.getAccountById(account.getId()));
        }
        resp.sendRedirect("/user/profile");
    }
}
