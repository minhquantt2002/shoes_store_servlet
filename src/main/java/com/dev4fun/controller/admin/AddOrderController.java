package com.dev4fun.controller.admin;


import com.dev4fun.dao.BillDAO;
import com.dev4fun.dao.BillDetailDAO;
import com.dev4fun.dao.ProductDAO;
import com.dev4fun.model.Account;
import com.dev4fun.model.Bill;
import com.dev4fun.model.BillDetail;
import com.dev4fun.model.Product;
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

@WebServlet(urlPatterns = {"/admin/order/*"})
public class AddOrderController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        if (url.contains("/admin/order/add")) {
            String action = "/admin/order/add";
            req.setAttribute("action", action);
            RequestDispatcher rd = req.getRequestDispatcher("/views/admin/page-add-order.jsp");
            rd.forward(req, resp);
        } else if (url.contains("/admin/order/edit") && req.getParameter("id") != null) {
            String action = "/admin/order/edit";
            req.setAttribute("action", action);
            req.setAttribute("id", req.getParameter("id"));
            Bill bill = new BillDAO().getBillById(Integer.parseInt(req.getParameter("id")));
            req.setAttribute("name", bill.getFullName());
            req.setAttribute("tel", bill.getPhoneNumber());
            req.setAttribute("address", bill.getAddress());
            req.setAttribute("email", bill.getEmail());
            req.setAttribute("code", bill.getPayMethod());
            req.setAttribute("author", bill.getInvoice_creator());
            String status = bill.getStatus();
            if (status.equals("Đang giao hàng")) {
                status = "c";
            } else if (status.equals("Đã nhận hàng")) {
                status = "a";
            } else if (status.equals("Chờ xử lý")) {
                status = "b";
            }
            req.setAttribute(status, "selected");
            req.setAttribute("listBillDetails", bill.getBillDetails());
            RequestDispatcher rd = req.getRequestDispatcher("/views/admin/page-add-order.jsp");
            rd.forward(req, resp);
        } else {
            resp.sendRedirect("/admin/order");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account acc = (Account) SessionUtil.getInstance().getValue(req, "ACCOUNT_ADMIN");
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String url = req.getRequestURI();
        Bill bill = new Bill();
        Product product;
        ProductDAO productDAO = new ProductDAO();
        if (url.contains("/admin/order/edit")) {
            bill.setId(Integer.parseInt(req.getParameter("idBill")));
        }
        bill.setFullName(req.getParameter("name"));
        bill.setPhoneNumber(req.getParameter("tel"));
        bill.setAddress(req.getParameter("address"));
        bill.setEmail(req.getParameter("email"));
        bill.setCreatedAt(currentDateTime.format(dtf));
        bill.setInvoice_creator(acc.getFullName());
        bill.setStatus(req.getParameter("status"));
        bill.setPayMethod(req.getParameter("code"));
        bill.setNote(req.getParameter("note"));
        bill.setUserId(0);

        float totalAmount = 0;
        ArrayList<BillDetail> listBillDetails = new ArrayList<>();

        int indexProd = 0;
        while (req.getParameter("productId" + indexProd) != null) {
            int quantity = Integer.parseInt(req.getParameter("quantityProduct" + indexProd));
            int size = Integer.parseInt(req.getParameter("sizeProduct" + indexProd));
            int productId = Integer.parseInt(req.getParameter("productId" + indexProd));

            if (!checkQuantity(productId, quantity, size)) {
                indexProd++;
                continue;
            }

            product = productDAO.getProductById(productId);
            BillDetail billDetail = new BillDetail();

            if (url.contains("/admin/order/edit")) {
                billDetail.setId(Integer.parseInt(req.getParameter("id" + indexProd)));
                billDetail = new BillDetailDAO().getBillDetailById(Integer.parseInt(req.getParameter("id" + indexProd)));
                if (billDetail.getQuantity() != quantity) {
                    boolean changeQuantity = productDAO.updateProductDetail(productId, size,-billDetail.getQuantity());
                }
            }

            //update quantity
            boolean updateProductDetail = productDAO.updateProductDetail(productId, size, quantity);

            billDetail.setProduct(product);
            billDetail.setSize(size);
            billDetail.setQuantity(quantity);
            billDetail.setAmount(product.getPrice() * quantity);

            totalAmount += product.getPrice() * quantity;

            indexProd++;
            listBillDetails.add(billDetail);

        }
        bill.setTotalAmount(totalAmount);
        bill.setBillDetails(listBillDetails);

        if (url.contains("/admin/order/add")) {
            int result = new BillDAO().createBillByAdmin(bill);
            for (BillDetail temp : listBillDetails) {
                boolean setBillDetail = new BillDetailDAO().createBillDetail(temp, result);
            }
        } else {
            boolean result = new BillDAO().updateBill(bill);
            for (BillDetail temp : listBillDetails) {
                boolean setBillDetail = new BillDetailDAO().updateBillDetail(temp);
            }
        }
        resp.sendRedirect("/admin/order");
    }

    boolean checkQuantity(int id, int quantity, int size) {
        ProductDAO productDAO = new ProductDAO();
        int k = productDAO.getQuantityBySize(id, size);
        return k >= quantity;
    }
}
