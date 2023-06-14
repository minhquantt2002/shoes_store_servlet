<%@ page import="java.util.ArrayList" %>
<%@ page import="com.dev4fun.model.BillDetail" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.dev4fun.model.Bill" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    NumberFormat nf = NumberFormat.getNumberInstance();
    nf.setGroupingUsed(true);
%>
<title>Quản lý đơn hàng</title>
<div id="main">
    <div class="content">
        <div class="box title-decorator--left">
            <div class="wrap-title">
                <div class="item-title">
                    <h3>Đơn hàng</h3>
                </div>
                <div class="timer">
                    <p id="timer"></p>
                </div>
            </div>
        </div>

        <div class="main-container">
            <div class="wrapper-main-container">

                <div class="action-table">
                    <button id="btnAddProduct" class="btn-add" type="button"><a
                            href="<c:url value="/admin/order/add"/>">Thêm đơn hàng</a></button>
                </div>
                <form method="get" action="<c:url value="/admin/order"/>">
                    <div class="table-content">
                        <div class="search-table-content">
                            <div class="wrapper-search">
                                <%--                            <form id="formSearch" method="get" action="<c:url value="/admin/order"/>">--%>
                                <div class="search-option">
                                    <select name="t" id="optionSearchTable">
                                        <% if (request.getParameter("t") != null) {%>
                                        <option value="${valueSearch}" selected>${typeSearch}</option>
                                        <%} else {%>
                                        <option value="" selected disabled hidden>Tìm kiếm theo</option>
                                        <%}%>
                                        <option value="id" style="display: ${id}">ID Đơn hàng</option>
                                        <option value="fn" style="display: ${fn}">Khách hàng</option>
<%--                                        <option value="name" style="display: ${name}">Đơn hàng</option>--%>
                                        <option value="st" style="display: ${st}">Tình trạng</option>
                                    </select>
                                </div>
                                <div class="search-value">
                                    <input type="text" name="v" placeholder="Gõ ở đây..." value="${txt_sbilldetail}"
                                    <%--                                           value="<%=request.getParameter("value") != null ? request.getParameter("value") : ""%>"--%>
                                    >
                                </div>

                                <div class="btn-search">
                                    <button type="submit">Tìm kiếm</button>
                                </div>
                                <%--                            </form>--%>

                            </div>

                        </div>
                        <div class="wrapper-table-content">
                            <table>
                                <thead>
                                <tr>
                                    <th>ID Đơn hàng</th>
                                    <th>Khách hàng</th>
                                    <th>Địa chỉ</th>
                                    <th>Hình Thức</th>
                                    <th>Tổng tiền</th>
                                    <th>Trạng thái</th>
                                    <th class="action">Tính năng</th>
                                </tr>
                                </thead>
                                <%
                                    ArrayList<Bill> listBills = (ArrayList<Bill>) request.getAttribute("listBill");
                                    int sIndex = 0, eIndex = listBills.size();
                                    if (request.getParameter("startIndex") != null) {
                                        sIndex = Integer.parseInt(request.getParameter("startIndex"));
                                    }
                                    if (sIndex + 10 < listBills.size()) eIndex = sIndex + 10;
                                %>
                                <tbody>
                                <% for (int i = sIndex; i < eIndex; i++) {%>
                                <tr>
                                    <td><%=listBills.get(i).getId()%>
                                    </td>
                                    <td><%=listBills.get(i).getFullName()%>
                                    </td>
                                    <td><%=listBills.get(i).getAddress()%>
                                    </td>
                                    <td><%=listBills.get(i).getPayMethod()%>
                                    </td>
                                    <td><%=nf.format(listBills.get(i).getTotalAmount())%><sup>đ</sup></td>
                                    <td><%=listBills.get(i).getStatus()%>
                                    </td>
                                    <td>
                                        <a href="/admin/order/edit?id=<%=listBills.get(i).getId()%>"
                                           class="btn-edit">Sửa</a>
                                    </td>
                                </tr>
                                <%
                                    }
                                %>
                                </tbody>

                            </table>
                        </div>

                        <div class="pagination-table-content">
                            <div class="wrapper-pagination">
                                <div class="wrapper-action-table">
                                    <div class="index">
                                        <% String pageIndex;
                                            int currentPage = 1;

                                            if (request.getParameter("pageIndex") != null) {
                                                pageIndex = request.getParameter("pageIndex");
                                                currentPage = Integer.parseInt(pageIndex);
                                            }

                                            pageIndex = currentPage + "/" + (int) Math.ceil(listBills.size() / 10.0);
                                            int next = (int) Math.ceil(listBills.size() / 10.0);
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
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script>
    const dataLength =
    <%=listBills.size()%>
    const rowPerPage = 10;
    let currentPage = <%=currentPage%>;
</script>
<script src="<c:url value="../../assets/js/pagination.js"/>">
</script>
