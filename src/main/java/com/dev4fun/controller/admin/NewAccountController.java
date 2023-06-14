package com.dev4fun.controller.admin;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.dev4fun.dao.AccountDAO;
import com.dev4fun.dao.RoleDAO;
import com.dev4fun.model.Account;
import com.dev4fun.model.Role;
import com.dev4fun.utils.BCrypt;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

@WebServlet(urlPatterns = {"/admin/account/*"})
@MultipartConfig
public class NewAccountController extends HttpServlet {
    Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "",
            "api_key", "",
            "api_secret", "",
            "secure", true));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RoleDAO roleDAO = new RoleDAO();
        ArrayList<Role> listRole = roleDAO.getAllRole();
        req.setAttribute("listRole", listRole);

        String url = req.getRequestURI();
        if (url.contains("/admin/account/add")) {
            req.setAttribute("action", "/admin/account/add");
            RequestDispatcher rd = req.getRequestDispatcher("/views/admin/page-add-account.jsp");
            rd.forward(req, resp);
        } else if (url.contains("/admin/account/edit") && req.getParameter("id") != null) {
            req.setAttribute("action", "/admin/account/edit");
            req.setAttribute("id", req.getParameter("id"));
            AccountDAO accountDAO = new AccountDAO();
            Account account = accountDAO.getAccountById(Integer.parseInt(req.getParameter("id")));
            req.setAttribute("fullName", account.getFullName());
            req.setAttribute("dob", account.getDob());
            req.setAttribute(account.getGender(), "selected");
            req.setAttribute("account", account);
            req.setAttribute("tel", account.getPhoneNumber());
            req.setAttribute("address", account.getAddress());
            req.setAttribute("email", account.getEmail());
            req.setAttribute("username", account.getUsername());
            req.setAttribute("password", account.getPassword());
            req.setAttribute(account.getRole(), "selected");
            RequestDispatcher rd = req.getRequestDispatcher("/views/admin/page-add-account.jsp");
            rd.forward(req, resp);
        } else {
            resp.sendRedirect("/admin/account");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("act") != null && req.getParameter("act").equals("role") && req.getParameter("newCategory") != null) {
            RoleDAO roleDAO = new RoleDAO();
            Role role = new Role();
            role.setName(req.getParameter("newCategory"));
            boolean result = roleDAO.createRole(role);
            resp.sendRedirect("/admin/account/add");
            return;
        }

        Account account = new Account();
        String url = req.getRequestURI();
        if (url.contains("/admin/account/edit")) {
            account = new AccountDAO().getAccountById(Integer.parseInt(req.getParameter("idAccount")));
            if (!account.getPassword().equals(req.getParameter("password"))) {
                account.setPassword(BCrypt.hashpw(req.getParameter("password"), BCrypt.gensalt()));
            }
        } else account.setPassword(BCrypt.hashpw(req.getParameter("password"), BCrypt.gensalt()));

        account.setUsername(req.getParameter("username"));
        account.setFullName(req.getParameter("fullName"));
        account.setPhoneNumber(req.getParameter("tel"));
        account.setRole(req.getParameter("role"));
        account.setEmail(req.getParameter("email"));
        account.setDob(req.getParameter("dob"));
        account.setGender(req.getParameter("gender"));
        account.setAddress(req.getParameter("address"));

        String urlImgParam = req.getParameter("textImageInput");
        String urlImage = "https://res.cloudinary.com/dzimy62tk/image/upload/v1686676248/352803545_986271059215241_2936290390407327784_n.jpg_ssmw3x.jpg";
//        if (urlImgParam != null && !urlImgParam.equals("")) {
//            urlImage = urlImgParam;
//        } else {
//            Part filePart = req.getPart("imageInput");
//            if (!filePart.getSubmittedFileName().equals("") && cloudinary != null) {
//                Map uploadResult = cloudinary.uploader().upload(filePart.getInputStream().readAllBytes(), ObjectUtils.asMap("folder", "my_images"));
//                urlImage = cloudinary.url().generate((String) uploadResult.get("public_id"));
//            }
//        }
        account.setImageLink(urlImage);
        AccountDAO accountDAO = new AccountDAO();
        if (url.contains("/admin/account/add")) {
            boolean result = accountDAO.createAccount(account);
        } else {
            boolean result = accountDAO.updateAccount(account);
        }
        resp.sendRedirect("/admin/account");
    }
}
