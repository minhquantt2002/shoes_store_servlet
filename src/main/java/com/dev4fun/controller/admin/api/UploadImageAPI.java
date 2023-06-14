package com.dev4fun.controller.admin.api;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = {"/admin/api/upload"})
@MultipartConfig
public class UploadImageAPI extends HttpServlet {
    Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "",
            "api_key", "",
            "api_secret", "",
            "secure", true));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("{\"info\": \"" + "oke" + "\"}");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Part filePart = req.getPart("file");
            String urlImage = "https://cdn5.vectorstock.com/i/1000x1000/27/89/user-account-flat-icon-vector-14992789.jpg";
//            if (!filePart.getSubmittedFileName().equals("") && cloudinary != null) {
//                Map uploadResult = cloudinary.uploader().upload(filePart.getInputStream().readAllBytes(), ObjectUtils.asMap("folder", "my_images"));
//                urlImage = cloudinary.url().generate((String) uploadResult.get("public_id"));
//            }
            resp.setContentType("application/json");
            req.setCharacterEncoding("UTF-8");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("{\"link\": \"" + urlImage + "\"}");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
