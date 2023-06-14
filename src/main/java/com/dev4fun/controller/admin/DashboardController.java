package com.dev4fun.controller.admin;

import com.dev4fun.dao.AccountDAO;
import com.dev4fun.dao.BillDAO;
import com.dev4fun.dao.ProductDAO;
import com.dev4fun.model.Account;
import com.dev4fun.model.Bill;
import com.dev4fun.model.Chart;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/admin"})
public class DashboardController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BillDAO billDAO = new BillDAO();
        AccountDAO accountDAO = new AccountDAO();
        ProductDAO productDAO = new ProductDAO();
        int totalBills = billDAO.getTotalBills();
        int totalClientAccounts = accountDAO.getTotalClientAccounts();
        int totalProducts = productDAO.getTotalProducts();
        int totalProductExpired = productDAO.getTotalProductExpired();
        ArrayList<Chart> incomeForChart = billDAO.getIncomeForChart();

        req.setAttribute("incomeForChart", incomeForChart);
        req.setAttribute("totalClientAccounts", totalClientAccounts);
        req.setAttribute("ttBill", totalBills);
        req.setAttribute("ttProd", totalProducts);
        req.setAttribute("ttExpired", totalProductExpired);

        ArrayList<Bill> listNewBill = billDAO.getAllBill();
        req.setAttribute("newBill", listNewBill);

        ArrayList<Account> listNewAccount = accountDAO.getAllAccount();
        req.setAttribute("newAcc", listNewAccount);

        RequestDispatcher rd = req.getRequestDispatcher("/views/admin/home.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
