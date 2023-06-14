package com.dev4fun.controller.admin;


import com.dev4fun.dao.AccountDAO;
import com.dev4fun.model.Account;
import com.dev4fun.utils.SessionUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/admin/account"})
public class AccountController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AccountDAO accountDAO = new AccountDAO();
        ArrayList<Account> listAccounts = accountDAO.getAllAccount();

        if (req.getParameter("t") != null && req.getParameter("v") != null) {
            String temp = req.getParameter("t");
            String value = req.getParameter("v");
            String none = "none";
            ArrayList<Account> accounts;
            accounts = accountDAO.getAccountByElement(temp, value);
            String typeSearch = temp.equals("username") ? "Tên người dùng" :
                    temp.equals("role") ? "Loại tài khoản" :
                            temp.equals("email") ? "Email" : "Số điện thoại";
            req.setAttribute("valueSearch", temp);
            req.setAttribute("typeSearch", typeSearch);
            req.setAttribute(temp, none);
            req.setAttribute("listAccounts", accounts);
            req.setAttribute("txt_sAccount", value);
        } else {
            req.setAttribute("valueSearch", "");
            req.setAttribute("typeSearch", "Tìm kiếm theo");
            req.setAttribute("listAccounts", listAccounts);
            System.out.println(listAccounts.size());
        }


        RequestDispatcher rd = req.getRequestDispatcher("/views/admin/page-account.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("act") != null && req.getParameter("act").equals("delete")) {
            int id = ((Account) SessionUtil.getInstance().getValue(req, "ACCOUNT_ADMIN")).getId();
            if (!req.getParameter("accountId").equals(String.valueOf(id))) {
                AccountDAO accountDAO = new AccountDAO();
                boolean result = accountDAO.deleteAccount(Integer.parseInt(req.getParameter("accountId")));
            }
            resp.sendRedirect("/admin/account");
        }
    }
}
