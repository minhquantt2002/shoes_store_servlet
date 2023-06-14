package com.dev4fun.controller.user;

import com.dev4fun.utils.SessionUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/checkout/order-success"})
public class OrderSuccessController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (SessionUtil.getInstance().getValue(req, "ORDER_SUCCESS") == null) {
            resp.sendRedirect("/404");
            return;
        }
        if (SessionUtil.getInstance().getValue(req, "ORDER_SUCCESS").equals("QR")) {
            req.setAttribute("qrCode", SessionUtil.getInstance().getValue(req, "ORDER_SUCCESS"));
        }
        RequestDispatcher rd = req.getRequestDispatcher("/views/user/page-order-success.jsp");
        rd.forward(req, resp);

        SessionUtil.getInstance().removeValue(req, "ORDER_SUCCESS");
    }
}