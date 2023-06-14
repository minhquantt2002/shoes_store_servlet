<%@ page import="java.util.ArrayList" %>
<%@ page import="com.dev4fun.model.BillDetail" %>
<%@ page import="com.dev4fun.model.Bill" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String title = request.getAttribute("id") != null ? "Thay đổi thông tin" : "Thêm";
%>
<title><%=title%> đơn hàng</title>
<div id="main">
    <div class="content">
        <div class="box title-decorator--left">
            <div class="wrap-title">
                <div class="item-title">
                    <h3>Đơn hàng / <%=title%> đơn hàng</h3>
                </div>
                <div class="timer">
                    <p id="timer"></p>
                </div>
            </div>
        </div>

        <div class="main-container">
            <div class="wrapper-main-container">

                <div class="form-content">
                    <div class="wrap-title">
                        <div class="item-title">
                            <h3>Thêm đơn hàng mới</h3>
                        </div>
                    </div>
                    <%
                        String url = String.valueOf(request.getAttribute("action"));
                    %>
                    <form class="form-product" action="<%=url%>" method="post">
                        <% if (url.equals("/admin/order/edit")) {%>
                        <input type="hidden" name="idBill" value="<%=request.getAttribute("id")%>">
                        <% }%>
                        <div class="form-group-line">
                            <div class="form-line">
                                <label for="name">Tên khách hàng:</label>
                                <input type="text" id="name" name="name" value="${name}">
                            </div>

                            <div class="form-line">
                                <label for="tel">Số điện thoại:</label>
                                <input type="tel" id="tel" name="tel" value="${tel}">
                            </div>

                            <div class="form-line">
                                <label for="address">Địa chỉ:</label>
                                <input type="text" id="address" name="address" value="${address}">
                            </div>

                            <div class="form-line">
                                <label for="email">Email:</label>
                                <input type="email" id="email" name="email" value="${email}">
                            </div>
                        </div>
                        <div class="form-group-line">
                            <div class="form-line">
                                <label for="code">Hình thức thanh toán:</label>
                                <input type="text" id="code" name="code" value="${code}">
                            </div>

                            <div class="form-line">
                                <label for="status">Trạng thái:</label>
                                <select name="status" id="status">

                                    <option value="" selected disabled hidden>-- Chọn trạng thái --</option>
                                    <option value="Đã nhận hàng" ${a}>Đã nhận hàng</option>
                                    <option value="Chờ xử lý" ${b}>Chờ xử lý</option>
                                    <option value="Đang giao hàng" ${c}>Đang giao hàng</option>
                                </select>
                            </div>

                        </div>

                        <div class="form-line">
                            <div class="title-list">
                                <%--@declare id="imageinput"--%>
                                <label for="imageInput">Thêm sản phẩm cho đơn hàng:</label>
                                <button type="button" class="btn-add-div" onclick="addProductInOrder();">Thêm sản phẩm
                                </button>
                            </div>
                            <div class="list-product">
                                <%
                                    ArrayList<BillDetail> billDetails = (ArrayList<BillDetail>) request.getAttribute("listBillDetails");
                                    if (billDetails != null) {
                                        for (int i = 0; i < billDetails.size(); i++) {
                                %>
                                <div class="form-group-line">
                                    <div class="form-line">
                                        <label for="status">Mã sản phẩm:</label>
                                        <input type="text" name="productId<%=i%>"
                                               value="<%=billDetails.get(i).getProduct().getId()%>">
                                    </div>
                                    <input type="hidden" name="id<%=i%>" value="<%=billDetails.get(i).getId()%>">
                                    <div class="form-line">
                                        <label for="sizeProduct<%=i%>">Kích thước:</label>
                                        <input type="number" id="sizeProduct<%=i%>" name="sizeProduct<%=i%>"
                                               value="<%=billDetails.get(i).getSize()%>">
                                    </div>
                                    <div class="form-line">
                                        <label for="quantityProduct<%=i%>">Số lượng:</label>
                                        <input type="number" id="quantityProduct<%=i%>" name="quantityProduct<%=i%>"
                                               value="<%=billDetails.get(i).getQuantity()%>">
                                    </div>
                                </div>
                                <%

                                        }
                                    }
                                %>
                            </div>
                        </div>

                        <div class="form-line">
                            <label for="note">Ghi chú:</label>
                            <textarea id="note" name="note" rows="4"></textarea>
                        </div>
                        <div class="form-line form-line-btn">
                            <button class="btn-add" type="submit">Lưu lại</button>
                            <button class="btn-cancel" type="reset">Xóa hết</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>