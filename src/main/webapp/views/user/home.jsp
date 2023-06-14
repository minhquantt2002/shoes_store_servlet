<%@page import="com.dev4fun.model.Product" %>
<%@page import="com.dev4fun.model.Category" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>

<link href="<c:url value="/assets/style/user/home-style.css"/>" rel="stylesheet" type="text/css"/>
<title>Miracle - Giày Việt Nam chất lượng cao</title>
<div class="main">
    <div class="container">
        <%
            ArrayList<Category> listCategoriesWithProducts = (ArrayList<Category>) request.getAttribute("listCategoriesWithProducts");
            NumberFormat nf = NumberFormat.getNumberInstance();
        %>
        <nav>
            <% for (Category category : listCategoriesWithProducts) {%>
            <a href="/products?category=<%=category.getName()%>" class="nav-item">
                <img src="<%=category.getIcon()%>" alt="hình ảnh ">
                <b class="item-text">
                    <%=category.getName()%>
                </b>
            </a>
            <%}%>
        </nav>

        <%for (Category category : listCategoriesWithProducts) {%>
        <div class="list-product">
            <div class="head-product">
                <div class="brand"><%=category.getName()%>
                </div>
                <a href="/products?category=<%=category.getName()%>" class="allProduct">Tất cả sản phẩm</a>
            </div>
            <div class="container-product">
                <div class="home-product">
                    <div class="grid">
                        <% for (Product product : category.getListProducts()) {%>
                        <div class="grid__column-2-4">
                            <a href="/products/<%=product.getName().replaceAll(" ", "-") + "-" + product.getId()%>">
                                <div class="home-product-item">
                                    <div class="home-product-item__img home-product-add-cart"
                                         style="background-image: url(<%=product.getImageLink()%>)"></div>
                                    <div class="home-product-item__properties">
                                        <h5 class="home-product-item__name"><%=product.getName()%>
                                        </h5>
                                        <div class="home-product-item__infor">
                                            <div class="home-product-item__price"><%=nf.format(product.getPrice())%>
                                                <sup>đ</sup>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <%}%>
                    </div>
                </div>
            </div>
        </div>
        <%}%>
    </div>
</div>
