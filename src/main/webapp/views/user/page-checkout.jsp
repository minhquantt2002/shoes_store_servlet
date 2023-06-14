<%@page import="com.dev4fun.utils.SessionUtil" %>
<%@page import="com.dev4fun.model.Account" %>
<%@ page import="com.dev4fun.utils.CartUtil" %>
<%@ page import="com.dev4fun.model.Cart" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.dev4fun.model.Product" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<link rel="stylesheet" href="<c:url value="/assets/style/user/checkout-style.css"/>"/>
<%
    Account acc = (Account) SessionUtil.getInstance().getValue(request, "ACCOUNT_USER");
%>
<title>Thanh toán</title>
<div class="main">
    <div class="content">
        <div class="left-side">
            <div class="deliver-top">
                <a href="<c:url value="/home"/>" class="logo">
                    <img src="<c:url value="/assets/img/demo_den.png"/>" alt="Logo">
                </a>
                <h2>Thông tin giao hàng</h2>
            </div>
            <div class="deliver-main">
                <%if (acc == null) {%>
                <p>Bạn đã có tài khoản?
                    <a href="">Đăng nhập</a>
                </p>
                <%}%>
                <form class="form-add" action="<c:url value="/checkout"/>" method="post">
                    <%if (acc != null) {%>
                    <input type="hidden" name="userId" value="<%=acc.getId()%>">
                    <%}%>
                    <% if (request.getAttribute("product") != null) {
                        Product product = (Product) request.getAttribute("product");
                    %>
                    <input type="hidden" name="productId" value="<%=product.getId()%>">
                    <input type="hidden" name="size" value="<%=product.getProductDetails().get(0).getSize()%>">
                    <input type="hidden" name="quantity" value="<%=request.getAttribute("quantity")%>">

                    <%}%>
                    <div class="form-group-line">
                        <div class="form-line">
                            <label for="fullName">Họ và tên:</label>
                            <input type="text" id="fullName" name="fullName"
                                   value="<%=acc == null ? "" : acc.getFullName()%>" required>
                        </div>
                    </div>

                    <div class="form-group-line">
                        <div class="form-line">
                            <label for="email">Địa chỉ email:</label>
                            <input type="email" id="email" name="email" value="<%=acc == null ? "" : acc.getEmail()%>"
                                   required>
                        </div>
                        <div class="form-line">
                            <label for="tel">Số điện thoại:</label>
                            <input type="tel" id="tel" name="tel" value="<%=acc == null ? "" : acc.getPhoneNumber()%>"
                                   required>
                        </div>
                    </div>

                    <div class="form-group-line">
                        <div class="form-line">
                            <label for="address">Địa chỉ:</label>
                            <input type="text" id="address" name="address"
                                   value="<%=acc == null ? "" : acc.getAddress()%>" required>
                        </div>
                    </div>

                    <div class="box-pay-method">
                        <h2>Phương thức thanh toán</h2>
                        <div>
                            <div class="radio-wrapper">
                                <div class="radio-label">
                                    <div class="radio-input">
                                        <input type="radio" id="QR" name="payment_method" value="QR" checked="">
                                    </div>
                                    <div class="radio-content-input">
                                        <img class="main-img"
                                             src="https://hstatic.net/0/0/global/design/seller/image/payment/vnpay_new.svg?v=4"
                                             alt="">
                                        <div>
                                            <label for="QR">Thẻ ATM/Visa/Master/JCB/QR Pay qua cổng VNPAY</label>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="radio-wrapper">
                                <div class="radio-label">
                                    <div class="radio-input">
                                        <input type="radio" id="COD" name="payment_method" value="COD">
                                    </div>
                                    <div class="radio-content-input">
                                        <img class="main-img"
                                             src="https://hstatic.net/0/0/global/design/seller/image/payment/cod.svg?v=4"
                                             alt="">
                                        <div>
                                            <label for="COD">Thanh toán khi giao hàng (COD)</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="form-line form-line-btn">
                        <button class="btn-confirm" type="submit">Hoàn tất đơn hàng</button>
                        <a class="" href="<c:url value="/cart"/>">Giỏ hàng</a>
                    </div>
                </form>
            </div>
        </div>


        <div class="right-side">
            <div>
                <table class="product-table">
                    <thead>
                    <tr>
                        <th scope="col"></th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        NumberFormat nf = NumberFormat.getNumberInstance();
                        float totalAmount = 0;
                        if (request.getAttribute("product") != null) {
                            int quantity = (int) request.getAttribute("quantity");
                            Product product = (Product) request.getAttribute("product");
                            totalAmount = quantity * product.getPrice();
                    %>
                    <tr class="product">
                        <td class="product-image">
                            <img src="<%=product.getImageLink()%>"
                                 alt="<%=product.getName()%>">
                        </td>
                        <td class="product-description">
                            <p class="description"><%=product.getName()%>
                            </p>
                            <p><%=product.getProductDetails().get(0).getSize()%>
                            </p>
                            <p>X <%=quantity%>
                            </p>
                        </td>
                        <td class="product-price td--right">
                            <p><%=nf.format(quantity * product.getPrice())%><sup>đ</sup>
                            </p>
                        </td>
                    </tr>
                    <% } else {

                        for (Cart cart : CartUtil.getCart(request)) {
                            totalAmount += cart.getQuantity() * cart.getProduct().getPrice();
                    %>
                    <tr class="product">
                        <td class="product-image">
                            <img src="<%=cart.getProduct().getImageLink()%>"
                                 alt="<%=cart.getProduct().getName()%>">
                        </td>
                        <td class="product-description">
                            <p class="description"><%=cart.getProduct().getName()%>
                            </p>
                            <p><%=cart.getSize()%>
                            </p>
                            <p>X <%=cart.getQuantity()%>
                            </p>
                        </td>
                        <td class="product-price td--right">
                            <p><%=nf.format(cart.getQuantity() * cart.getProduct().getPrice())%><sup>đ</sup>
                            </p>
                        </td>
                    </tr>
                    <%
                            }
                        }
                    %>
                    </tbody>
                </table>
            </div>
            <div>
                <table class="total-line-table">
                    <thead>
                    <tr>
                        <th scope="col"></th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr class="total-line">
                        <td>Tạm tính</td>
                        <td class="td--right">
                            <p class="td--right"><%=nf.format(totalAmount)%><sup>đ</sup></p>
                        </td>
                    </tr>
                    <tr class="total-line">
                        <td>Phí vận chuyển</td>
                        <td class="td--right">
                            <p>0đ</p>
                        </td>
                    </tr>
                    </tbody>
                    <tfoot class="total-line-table-footer">
                    <tr class="total-line">
                        <td>
                            <p class="label-total">Tổng cộng</p>
                        </td>
                        <td class="td--right">
                            <p class="total-amount"><%=nf.format(totalAmount)%><sup>đ</sup></p>
                        </td>
                    </tr>
                    </tfoot>
                </table>
            </div>
        </div>
    </div>
</div>
