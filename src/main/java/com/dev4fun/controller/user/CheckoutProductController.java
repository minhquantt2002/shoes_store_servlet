package com.dev4fun.controller.user;

import com.dev4fun.dao.BillDAO;
import com.dev4fun.dao.ProductDAO;
import com.dev4fun.model.*;
import com.dev4fun.utils.CartUtil;
import com.dev4fun.utils.SessionUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/checkout"})
public class CheckoutProductController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("productId") == null && CartUtil.getCart(req).size() == 0) {
            resp.sendRedirect("/404");
            return;
        }
        if (req.getParameter("productId") != null) {
            int id = Integer.parseInt(req.getParameter("productId"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            int size = Integer.parseInt(req.getParameter("size"));
            Product product = new ProductDAO().getProductForBuyNow(id, size);

            req.setAttribute("product", product);
            req.setAttribute("quantity", quantity);
            req.setAttribute("size", size);
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/user/page-checkout.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        ProductDAO productDAO = new ProductDAO();
        float totalAmount = 0;
        if (req.getParameter("productId") != null) {
            int id = Integer.parseInt(req.getParameter("productId"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            int size = Integer.parseInt(req.getParameter("size"));
            Product product = productDAO.getProductForBuyNow(id, size);
            totalAmount += product.getPrice() * quantity;
            Bill bill = new Bill();
            bill.setStatus("Chờ xử lý");
            if (req.getParameter("userId") != null) {
                bill.setUserId(Integer.parseInt(req.getParameter("userId")));
            }
            bill.setFullName(req.getParameter("fullName"));
            bill.setEmail(req.getParameter("email"));
            bill.setAddress(req.getParameter("address"));
            bill.setPhoneNumber(req.getParameter("tel"));
            bill.setTotalAmount(totalAmount);
            bill.setPayMethod(req.getParameter("payment_method"));
            bill.setNote("");
            bill.setCreatedAt(LocalDateTime.now().format(dtf));

            ArrayList<BillDetail> listBillDetails = new ArrayList<>();

            BillDetail billDetail = new BillDetail();
            billDetail.setProduct(product);
            billDetail.setQuantity(quantity);
            billDetail.setSize(size);
            billDetail.setAmount(quantity);
            boolean updateProductDetail = productDAO.updateProductDetail(id, size, quantity);
            listBillDetails.add(billDetail);
            bill.setBillDetails(listBillDetails);
            new BillDAO().createBill(bill);
            SessionUtil.getInstance().putValue(req, "ORDER_SUCCESS", bill.getPayMethod());

        } else {
            for (Cart cart : CartUtil.getCart(req)) {
                totalAmount += cart.getQuantity() * cart.getProduct().getPrice();
            }
            Bill bill = new Bill();
            bill.setStatus("Chờ xử lý");
            if (req.getParameter("userId") != null) {
                bill.setUserId(Integer.parseInt(req.getParameter("userId")));
            }
            bill.setFullName(req.getParameter("fullName"));
            bill.setEmail(req.getParameter("email"));
            bill.setAddress(req.getParameter("address"));
            bill.setPhoneNumber(req.getParameter("tel"));
            bill.setTotalAmount(totalAmount);
            bill.setPayMethod(req.getParameter("payment_method"));
            bill.setNote("");
            bill.setCreatedAt(LocalDateTime.now().format(dtf));
            ArrayList<BillDetail> listBillDetails = new ArrayList<>();
            for (Cart cart : CartUtil.getCart(req)) {
                BillDetail billDetail = new BillDetail();
                billDetail.setProduct(cart.getProduct());
                billDetail.setQuantity(cart.getQuantity());
                billDetail.setSize(cart.getSize());
                billDetail.setAmount(cart.getProduct().getPrice() * cart.getQuantity());
                boolean updateProductDetail = productDAO.updateProductDetail(cart.getProduct().getId(), cart.getSize(), cart.getQuantity());
                listBillDetails.add(billDetail);
            }
            bill.setBillDetails(listBillDetails);

            new BillDAO().createBill(bill);
            SessionUtil.getInstance().removeValue(req, "listCarts");
            SessionUtil.getInstance().putValue(req, "ORDER_SUCCESS", bill.getPayMethod());
        }

        resp.sendRedirect("/checkout/order-success");
    }
}
