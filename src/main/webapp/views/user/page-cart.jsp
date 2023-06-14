<%@page import="com.dev4fun.dao.ProductDAO" %>
<%@page import="java.util.ArrayList" %>
<%@page import="com.dev4fun.utils.CartUtil" %>
<%@page import="com.dev4fun.model.Cart" %>
<%@ page import="java.text.NumberFormat" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<link rel="stylesheet" href="<c:url value="/assets/style/user/cart-style.css"/>"/>
<title>Giỏ hàng</title>
<div class="main">
    <%
        NumberFormat nf = NumberFormat.getNumberInstance();
        ArrayList<Cart> cart = CartUtil.getCart(request);
        ProductDAO productDao = new ProductDAO();
        float total = 0;
    %>
    <div class="navigate">
        <a href="<c:url value="/home"/>">Trang chủ</a>
        <span>/</span>
        <a href="<c:url value="/cart"/>">Giỏ hàng</a>
    </div>
    <div class="content">
        <div class="left-side">
            <div class="mainCart-detail">
                <div class="heading-cart">
                    <h1>Giỏ hàng của bạn</h1>
                </div>
                <p class="title-number-cart">Bạn đang có <strong><%=cart.size()%> sản phẩm</strong>
                    trong giỏ hàng</p>
                <div class="table-cart">
                    <%for (Cart c : cart) {%>
                    <div class="wrapper-item-cart item-cart">
                        <div class="wrapper-image">
                            <div class="item-img">
                                <img src="<%=c.getProduct().getImageLink()%>">
                            </div>
                            <div class="item-remove">
                                <a id="btnDelete" onclick="clickDelete(`<%=c.getSize()%>-<%=c.getProduct().getId()%>`)">Xóa</a>
                                <form id="deleteForm<%=c.getSize()%>-<%=c.getProduct().getId()%>" action="<c:url value="/cart"/>" method="post">
                                    <input type="hidden" name="act" value="remove">
                                    <input type="hidden" name="size" value="<%=c.getSize()%>">
                                    <input type="hidden" name="productId" value="<%=c.getProduct().getId()%>">
                                </form>
                            </div>
                        </div>

                        <div class="wrapper-info">
                            <div class="item-info">
                                <div class="title">
                                    <a href="/products/<%=c.getProduct().getName().replaceAll(" ", "-") + "-" + c.getProduct().getId()%>">
                                        <%=c.getProduct().getName()%>
                                    </a>
                                </div>
                                <div class="size">
                                    <span>Size: <%=c.getSize()%></span>

                                </div>
                            </div>
                            <div class="price">
                                <span><%=nf.format(c.getProduct().getPrice())%><sup>đ</sup></span>
                            </div>
                        </div>

                        <div class="wrapper-total">
                            <div class="item-total-price">
                                <span><%=nf.format(c.getProduct().getPrice() * c.getQuantity())%><sup>đ</sup></span>
                            </div>
                            <div class="item-qty">
                                <div class="quantity">
                                    <div class="quantity-set">
                                        <div class="quantity-reduce">
                                            <div onclick="Sub(<%=c.getProduct().getId()%> + '-' + <%=c.getSize()%>)">-
                                            </div>
                                        </div>
                                        <div class="quatity-value">
                                            <input id="quantityValue1-<%=c.getProduct().getId()%>-<%=c.getSize()%>" type="text" value="<%=c.getQuantity()%>" name="quantity"
                                                   max="<%=productDao.getQuantityBySize(c.getProduct().getId(), c.getSize())%>">
                                            <form id="updateForm<%=c.getProduct().getId()%>-<%=c.getSize()%>" action="<c:url value="/cart"/>" method="post">
                                                <input type="hidden" name="act" value="update">
                                                <input type="hidden" name="size" value="<%=c.getSize()%>">
                                                <input id="quantityValue2-<%=c.getProduct().getId()%>-<%=c.getSize()%>" type="hidden" value="<%=c.getQuantity()%>" name="quantity"
                                                       max="<%=productDao.getQuantityBySize(c.getProduct().getId(), c.getSize())%>">
                                            </form>
                                        </div>
                                        <div class="quantity-increase">
                                            <div onclick="Add(<%=c.getProduct().getId()%> + '-' + <%=c.getSize()%>)">+
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                    <%
                            total += c.getProduct().getPrice() * c.getQuantity();
                        }
                    %>
                </div>
            </div>
        </div>

        <div class="right-side">
            <div class="mainCart-sidebar wrap-order-summary">
                <div class="order-summary-block">
                    <h2 class="summary-title">Thông tin đơn hàng</h2>
                    <div class="summary-total">
                        <p>Tổng tiền: <span class="total-final"><%=nf.format(total)%><sup>đ</sup></span></p>
                    </div>
                    <div class="summary-action">
<%--                        <div class="summary-alert alert alert-danger">--%>
<%--                            FreeShip toàn quốc với đơn hàng từ 500.000<sup>đ</sup>--%>
<%--                        </div>--%>
                        <div class="summary-button">
                            <a <%=CartUtil.getCart(request).size() > 0 ? "href=\"/checkout\"" : ""%> id="btnCart-checkout" class="checkout-btn btnred">THANH TOÁN</a>
                        </div>
                        <br>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    function Add(id) {
        const input = Number(document.getElementById("quantityValue1-" + id).value);
        document.getElementById("quantityValue1-" + id).value = input + 1;
        document.getElementById("quantityValue2-" + id).value = input + 1;
        document.getElementById("updateForm" + id).submit()
    }

    function Sub(id) {
        const input = Number(document.getElementById("quantityValue1-" + id).value);
        if (input > 1) {
            document.getElementById("quantityValue1-" + id).value = input - 1;
            document.getElementById("quantityValue2-" + id).value = input - 1;
            document.getElementById("updateForm" + id).submit()
        }
    }

    function clickDelete(id) {
        document.getElementById("deleteForm" + id).submit()

    }
</script>