<%@ page import="com.dev4fun.model.Config" %>
<%@ page import="com.dev4fun.dao.ConfigDAO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link href="<c:url value="/assets/style/user/layout-out.css"/>" rel="stylesheet" type="text/css" media="all"/>
<style>

    .fill-text{
        text-align: justify;
    }
</style>
<footer>

    <%
        ArrayList<Config> configs = new ConfigDAO().getAllConfig();
        Config aboutUs = configs.get(0);
        String limitConfig = aboutUs.getContent().length()>300? aboutUs.getContent().substring(0,300): aboutUs.getContent();
    %>
    <div class="footer-container">
        <div class="wrap-info-footer">
            <div>
                <a style="color: white" href="<c:url value="/about/about-us"/>"><h2>Giới thiệu</h2></a>
                <div class="fill-text"><%=limitConfig%> ...</div>
            </div>
            <div>
                <h2>Liên hệ</h2>
                <p>Email: d4f@gmail.com</p>
                <p>Hotline: 0888123456</p>

            </div>
            <div>
                <h2>Địa chỉ cửa hàng</h2>
                <p class="fill-text">+ 245 Cao Lỗ - Đông Anh - Hà Nội</p>
                <p class="fill-text">+ 304 Ngọc Lâm - Long Biên - Hà Nội</p>
                <p class="fill-text">+ 111B1 Phạm Ngọc Thạch - Đống Đa - Hà Nội</p>
                <p class="fill-text">+ 165 Chùa Bộc - Đống Đa - Hà Nội</p>
            </div>
            <div>
                <h2>Fanpage</h2>
                <p><a href="https://www.facebook.com/profile.php" style="color: #00d9ff"><i class="fa-brands fa-facebook" ></i> Facebook</a></p>
                <p><a href="https://twitter.com" style="color: #00d9ff"><i class="fa-brands fa-twitter" ></i> D4F-Official</a></p>
                <p><a href="https://www.youtube.com" style="color: #00d9ff"><i class="fa-brands fa-youtube" ></i> D4F-Official</a></p>
                <p><a href="https://github.com/hquang782/shoesshop_jsp.git" style="color: #00d9ff"><i class="fa-brands fa-github" ></i> D4F-Official</a></p>
            </div>
        </div>
    </div>
    <div class="wrap-copyright">
        <p>Copyright 2023 © Team D4F</p>
    </div>
</footer>