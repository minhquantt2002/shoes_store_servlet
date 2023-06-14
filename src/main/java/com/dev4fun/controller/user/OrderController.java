package com.dev4fun.controller.user;

import com.dev4fun.dao.AccountDAO;
import com.dev4fun.dao.BillDAO;
import com.dev4fun.model.Account;
import com.dev4fun.model.Bill;
import com.dev4fun.utils.SessionUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/user/orders"})
public class OrderController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BillDAO billDAO = new BillDAO();
        Account account = (Account) SessionUtil.getInstance().getValue(req, "ACCOUNT_USER");
        ArrayList<Bill> listBill = billDAO.getBillsByAccountId(account.getId());
        req.setAttribute("listBills", listBill);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/user/page-order.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
