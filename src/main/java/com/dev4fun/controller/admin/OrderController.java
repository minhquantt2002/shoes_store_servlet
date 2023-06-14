package com.dev4fun.controller.admin;


import com.dev4fun.dao.BillDAO;
import com.dev4fun.dao.BillDetailDAO;
import com.dev4fun.model.Bill;
import com.dev4fun.model.BillDetail;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/admin/order"})
public class OrderController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BillDAO billDAO = new BillDAO();
        BillDetailDAO billDetailDAO = new BillDetailDAO();
        ArrayList<Bill> billArrayList = billDAO.getAllBill();

        if (req.getParameter("t") != null && req.getParameter("v") != null) {
            String temp = req.getParameter("t");
            String value = req.getParameter("v");
            String none = "none";
            ArrayList<Bill> listBillDetail = new ArrayList<>();
            switch (temp) {
                case "id":
                    //id don hang ID
                    listBillDetail.add(billDAO.getBillById(Integer.parseInt(value)));
                    break;
                case "fn":
                    //ten khach hang full_name
                    listBillDetail.add(billDAO.getBillByUserName(value));
                    break;
//                case "name":
                    //ten don hang
//                    listBillDetail = billDAO.get(value);
//                    break;
                case "st":
                    //status
                    listBillDetail = billDAO.getBillByStatus(value);
                    break;
                default:
                    break;
            }
            String typeSearch = temp.equals("id") ? "ID Đơn hàng" :
                    temp.equals("fn") ? "Khách hàng" :
                            temp.equals("name") ? "Đơn hàng" : "Tình trạng";
            req.setAttribute("valueSearch", temp);
            req.setAttribute(temp, none);
            req.setAttribute("typeSearch", typeSearch);
            req.setAttribute("listBill", listBillDetail);
            req.setAttribute("txt_sbilldetail", value);
        } else {
            req.setAttribute("valueSearch", "");
            req.setAttribute("typeSearch", "Tìm kiếm theo");
            req.setAttribute("listBill", billArrayList);
        }
        RequestDispatcher rd = req.getRequestDispatcher("/views/admin/page-order.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
