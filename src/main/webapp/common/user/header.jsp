<%@ page import="java.util.ArrayList" %>
<%@ page import="com.dev4fun.model.Cart" %>
<%@ page import="com.dev4fun.utils.CartUtil" %>
<%@ page import="com.dev4fun.utils.SessionUtil" %>
<%@ page import="com.dev4fun.model.Account" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<link href="<c:url value="/assets/style/user/layout-out.css"/>" rel="stylesheet" type="text/css" media="all"/>
<header class="header ">
    <%
        ArrayList<Cart> listCarts = CartUtil.getCart(request);
        Account accountUser = (Account) SessionUtil.getInstance().getValue(request, "ACCOUNT_USER");
        NumberFormat nf = NumberFormat.getInstance();
    %>
    <div class="container-header">
        <div class="container-header--flex">
            <div class="wrap-logo">
                <a href="<c:url value="/home"/>"><img src="<c:url value="/assets/img/demo.png"/>" alt=""></a>
            </div>
            <div class="wrap-link">
                <ul class="main-link">
                    <li><a href="<c:url value="/home"/>">Trang chủ</a></li>
                    <li><a href="<c:url value="/about/about-us"/>">Giới thiệu</a></li>
                    <li><a href="<c:url value="/products"/>">Sản phẩm</a></li>
                    <li><a href="<c:url value="/about/store"/>">Cửa hàng</a></li>
                </ul>
            </div>
            <div class="wrap-search">
                <form action="<c:url value="/products"/>" method="get">
                    <input type="text" name="search" placeholder="Tìm kiếm bằng tên sản phẩm" value="${valueSearch}">
                </form>
            </div>
            <div class="wrap-action">
                <div class="header-cart">
                    <div class="header-cart-wrap">
                        <div class="header__cart-icon" onclick="handleClickCart();">
                            <i class="fa-solid fa-cart-shopping">
                                <%if (listCarts.size() > 0) {%>
                                <span class="header__cart-notice"><%=listCarts.size()%></span>
                                <%}%>
                            </i>
                        </div>
                        <div class="header__cart-list open-cart">
                            <%
                                if (listCarts.size() == 0) {
                            %>
                            <div class="no-cart">
                                <img src="<c:url value="/assets/img/no_cart.png"/>" alt="" class="header__cart-no-cart-img">
                                <p class="header__cart-list-no-cart-msg">
                                    Chưa có sản phẩm
                                </p>
                            </div>
                            <%} else {%>
                            <div class="list-cart-item">
                                <h4 class="header__cart-heading">Sản phẩm đã thêm</h4>
                                <ul class="header__cart-list-item">
                                    <%for (Cart cart : listCarts) {%>
                                    <li class="header__cart-item">
                                        <div class="header__cart-item-img">
                                            <a href="/products/<%=cart.getProduct().getName().replaceAll(" ", "-") + "-" + cart.getProduct().getId()%>">
                                                <div style="background-image: url(<%=cart.getProduct().getImageLink()%>)" class="product-img"></div>
                                            </a>
                                        </div>
                                        <div class="header__cart-item-info">
                                            <div class="header__cart-item-info-name">
                                                <p>
                                                    <%=cart.getProduct().getName()%>
                                                </p>
                                                <p>
                                                    Size: <%=cart.getSize()%>
                                                </p>
                                            </div>
                                            <div style="text-align: left">
                                                <p class="header__cart-item-info-price"><%=nf.format(cart.getProduct().getPrice())%><sup>đ</sup></p>
                                                <p class="header__cart-item-info-price">X <%=cart.getQuantity()%>
                                                </p>
                                            </div>
                                        </div>
                                    </li>
                                    <hr class="item_hr"/>
                                    <%}%>
                                </ul>
                            </div>
                            <%}%>
                            <button class="list-cart-item-button">
                                <a href="<c:url value="/cart"/>">Xem giỏ hàng</a>
                            </button>
                        </div>
                    </div>
                </div>

                <div class="account">
                    <%if (accountUser == null) {%>
                    <a href="<c:url value="/login"/>">
                        <i class="fa-solid fa-user"></i>
                    </a>
                    <div class="account-item dropdown-account-link " style="display: none"></div>
                    <%} else {%>
                    <div class="wrap-account">
                        <a class="account-item">
                            <img src="<%=accountUser.getImageLink()%>"
                                 alt="logo">
                        </a>
                        <ul class="dropdown-account-link">
                            <li><a href="<c:url value="/user/profile"/>">Tài khoản của tôi</a></li>
                            <li><a href="<c:url value="/user/orders"/>">Đơn mua</a></li>
                            <li>
                                <button id="btnLogout">Đăng xuất</button>
                            </li>
                        </ul>
                    </div>
                    <%}%>
                </div>
            </div>
        </div>
    </div>
</header>

<script>
    const btnLogout = document.getElementById("btnLogout")
    btnLogout.addEventListener("click", async () => {
        await fetch(
            '/api/logout',
            {
                method: "POST"
            }
        ).then(data => data.json())
            .then(msg => {
                if (msg['msg'] === 'true') {
                    alert("Đăng xuất thành công! Click Ok để tiếp tục.")
                }
                // location.reload()
                window.location.href = '/login'
            })
    })
</script>

<script src="<c:url value="/assets/js/user.layout.js"/>" type="text/javascript"></script>
