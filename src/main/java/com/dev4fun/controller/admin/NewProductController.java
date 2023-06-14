package com.dev4fun.controller.admin;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.dev4fun.dao.CategoryDAO;
import com.dev4fun.dao.DAO;
import com.dev4fun.dao.ProductDAO;
import com.dev4fun.model.Category;
import com.dev4fun.model.Product;
import com.dev4fun.model.ProductDetail;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;

@WebServlet(urlPatterns = {"/admin/product/*"})
@MultipartConfig
public class NewProductController extends HttpServlet {
    Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", "", "api_key", "", "api_secret", "", "secure", true));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CategoryDAO categoryDAO = new CategoryDAO();
        ArrayList<Category> listCategories = categoryDAO.getListCategories();
        req.setAttribute("listCategories", listCategories);

        String url = req.getRequestURI();
        if (url.contains("/admin/product/add")) {
            RequestDispatcher rd = req.getRequestDispatcher("/views/admin/page-new-product.jsp");
            rd.forward(req, resp);
        } else if (url.contains("/admin/product/edit") && req.getParameter("id") != null) {
            ProductDAO productDAO = new ProductDAO();
            Product product = productDAO.getProductById(Integer.parseInt(req.getParameter("id")));
            req.setAttribute("product", product);
            RequestDispatcher rd = req.getRequestDispatcher("/views/admin/page-new-product.jsp");
            rd.forward(req, resp);
        } else {
            resp.sendRedirect("/admin/product");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("act") != null && req.getParameter("act").equals("category")) {
            CategoryDAO categoryDAO = new CategoryDAO();
            Category category = new Category();
            category.setName(req.getParameter("newCategory"));
            boolean result = categoryDAO.createCategory(category);
            resp.sendRedirect("/admin/product");
            return;
        }

        ProductDAO productDAO = new ProductDAO();
        Product product = new Product();
        product.setName(req.getParameter("name"));
        product.setPrice(Float.parseFloat(req.getParameter("price").replaceAll(",", "")));
        product.setCost(Float.parseFloat(req.getParameter("cost").replaceAll(",", "")));
        product.setStatus(req.getParameter("status"));
        product.setCategoryId(Integer.parseInt(req.getParameter("category")));
        product.setDescription(req.getParameter("description"));

        int numSize = 0;
        ArrayList<ProductDetail> listProductDetails = new ArrayList<>();
        while (req.getParameter("size" + numSize) != null) {
            ProductDetail productDetail = new ProductDetail();
            productDetail.setQuantity(Integer.parseInt(req.getParameter("quantity" + numSize)));
            productDetail.setSize(req.getParameter("size" + numSize));
            if (req.getParameter("productDetailId" + numSize) != null) {
                productDetail.setId(Integer.parseInt(req.getParameter("productDetailId" + numSize)));
            }
            numSize++;
            listProductDetails.add(productDetail);
        }
        product.setProductDetails(listProductDetails);

        int numImage = 0;
        StringBuilder listImages = new StringBuilder();
        while (req.getPart("imageInput" + numImage) != null) {
            String urlImage = "";
            String urlImgParam = req.getParameter("textImageInput" + numImage);
//            if (urlImgParam != null && !urlImgParam.equals("")) {
//                urlImage = urlImgParam;
//            } else {
//                Part filePart = req.getPart("imageInput" + numImage);
//                if (!filePart.getSubmittedFileName().equals("") && cloudinary != null) {
//                    Map uploadResult = cloudinary.uploader().upload(filePart.getInputStream().readAllBytes(), ObjectUtils.asMap("folder", "my_images"));
//                    urlImage = cloudinary.url().generate((String) uploadResult.get("public_id"));
//                }
//            }
            if (numImage == 0) {
                product.setImageLink(urlImage);
            } else {
                listImages.append(urlImage).append(",");
            }
            System.out.println("A picture imported: " + urlImage);
            numImage++;
        }
        product.setImageList(listImages.toString());

        String url = req.getRequestURI();
        if (url.contains("/admin/product/add")) {
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
            product.setCreatedAt(currentDateTime.format(dtf));
            boolean result = productDAO.createProduct(product);
        } else if (url.contains("/admin/product/edit")) {
            product.setId(Integer.parseInt(req.getParameter("id")));
            boolean result = productDAO.updateProductById(product);
        }
        resp.sendRedirect("/admin/product");
    }
}
