<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.dev4fun.model.Product" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.text.NumberFormat" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<link href="<c:url value="/assets/style/user/product-style.css"/>" rel="stylesheet" type="text/css"/>
<%
    String title = "";
    if (request.getParameter("category") != null) {
        title = "thuộc loại " + request.getParameter("category");
    } else if (request.getParameter("search") != null) {
        title = "có từ khóa '" + request.getParameter("search") +  "'";
    }
%>
<title>Tất cả sản phẩm <%=title%></title>
<div class="wraper">
    <form action="<c:url value="/products"/>" method="get">
        <div class="bgwhite-heading">
            <h1 class="title">Tất cả sản phẩm <%=title%></h1>
        </div>
        <input type="hidden" name="search" value="${valueSearch}">
        <input type="hidden" name="category" value="${valueCategory}">
        <%
            ArrayList<Product> listProducts = (ArrayList<Product>) request.getAttribute("listProducts");
            NumberFormat nf = NumberFormat.getNumberInstance();
            int sIndex = 0, eIndex = listProducts.size();
            if (request.getParameter("startIndex") != null) {
                sIndex = Integer.parseInt(request.getParameter("startIndex"));
            }
            if (sIndex + 20 < listProducts.size()) eIndex = sIndex + 20;
        %>
        <div class="container-product">
            <div class="home-product">
                <div class="grid">
                    <% for (int i = sIndex; i < eIndex; i++) {%>
                    <div class="grid__column-2-4">
                        <a href="/products/<%=listProducts.get(i).getName().replaceAll(" ", "-") + "-" + listProducts.get(i).getId()%>">
                            <div class="home-product-item">
                                <div class="home-product-item__img home-product-add-cart"
                                     style="background-image: url(<%=listProducts.get(i).getImageLink()%>)"></div>
                                <div class="home-product-item__properties">
                                    <h5 class="home-product-item__name"><%=listProducts.get(i).getName()%>
                                    </h5>
                                    <div class="home-product-item__infor">
                                        <div class="home-product-item__price"><%=nf.format(listProducts.get(i).getPrice())%>
                                            <sup>đ</sup>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </div>
                    <%}%>
                </div>
                <div class="wrapper-action-table" style="display: flex; justify-content: right; align-items: center;">
                    <div class="index">
                        <%
                            String pageIndex;
                            int currentPage = 1;
                            if (request.getParameter("pageIndex") != null) {
                                pageIndex = request.getParameter("pageIndex");
                                currentPage = Integer.parseInt(pageIndex);
                            }
                            pageIndex = currentPage + "/" + (int) Math.ceil(listProducts.size() / 20.0);
                            int next = (int) Math.ceil(listProducts.size() / 20.0);
                        %>
                        <p id="currentPage"><%=pageIndex%>
                        </p>
                    </div>
                    <input style="display: none" id="st" name="startIndex">
                    <input style="display: none" id="page" name="pageIndex">
                    <div class="previous">
                        <button <%=currentPage - 1 != 0 ? "onclick=\"previousPages()\"" : "type=\"button\""%>>&#8592;</button>
                    </div>
                    <div class="next">
                        <button <%=currentPage != next ? "onclick=\"nextPages()\"": "type=\"button\""%>>&#8594;</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>

<script>
    const dataLength =
    <%=listProducts.size()%>
    const rowPerPage = 20
    let currentPage = <%=currentPage%>;
</script>
<script type="text/javascript" src="<c:url value="../../assets/js/pagination.js"/>"></script>

