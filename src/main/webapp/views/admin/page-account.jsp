<%@ page import="com.dev4fun.model.Account" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.dev4fun.utils.SessionUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<title>Quản lý tài khoản</title>
<div id="main">
    <div class="content">
        <div class="box title-decorator--left">
            <div class="wrap-title">
                <div class="item-title">
                    <h3>Tài khoản</h3>
                </div>
                <div class="timer">
                    <p id="timer"></p>
                </div>
            </div>
        </div>

        <div class="main-container">
            <div class="wrapper-main-container">

                <div class="action-table">
                    <button class="btn-add"><a href="<c:url value="/admin/account/add"/>">Thêm tài khoản</a></button>
                </div>
                <form method="get" action="<c:url value="/admin/account"/>">
                    <div class="table-content">
                        <div class="search-table-content">
                            <div class="wrapper-search">
                                <%--                            <form method="get" action="<c:url value="/admin/account"/>">--%>
                                <div class="search-option">
                                    <select name="t" id="optionSearchTable">
                                        <% if (request.getParameter("t") != null) {%>
                                        <option value="${valueSearch}" selected>${typeSearch}</option>
                                        <%} else {%>
                                        <option value="" selected disabled hidden>Tìm kiếm theo</option>
                                        <%}%>
                                        <option value="username" style="display: ${username}">Tên người dùng</option>
                                        <option value="email" style="display: ${email}">Email</option>
                                        <option value="phone_number" style="display: ${phone_number}">Số điện thoại
                                        </option>
                                        <option value="role" style="display: ${role}">Loại tài khoản</option>
                                    </select>
                                </div>
                                <div class="search-value">
                                    <input type="text" placeholder="Typing here..." name="v" value="${txt_sAccount}">
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
                                    <th>Tên người dùng</th>
                                    <th>Họ và tên</th>
                                    <th>Email</th>
                                    <th>Số điện thoại</th>
                                    <th>Ngày sinh</th>
                                    <th>Loại</th>
                                    <%
                                        Account acc = (Account) SessionUtil.getInstance().getValue(request, "ACCOUNT_ADMIN");
                                        if (acc.getRole().equals("ADMIN")) {%>
                                    <th class="action">Action</th>
                                    <% } %>

                                </tr>
                                </thead>

                                <%
                                    ArrayList<Account> listAccounts = (ArrayList<Account>) request.getAttribute("listAccounts");
                                    int sIndex = 0, eIndex = listAccounts.size();
                                    if (request.getParameter("startIndex") != null) {
                                        System.out.println("index-null");
                                        sIndex = Integer.parseInt(request.getParameter("startIndex"));
                                        System.out.println(sIndex);
                                    }
                                    if (sIndex + 5 < listAccounts.size()) eIndex = sIndex + 5;
                                %>
                                <tbody class="list-accounts">
                                <% for (int i = sIndex; i < eIndex; i++) {%>

                                <tr>
                                    <td><%=listAccounts.get(i).getUsername()%>
                                    </td>
                                    <td><%=listAccounts.get(i).getFullName()%>
                                    </td>
                                    <td><%=listAccounts.get(i).getEmail()%>
                                    </td>
                                    <td><%=listAccounts.get(i).getPhoneNumber()%>
                                    </td>
                                    <td><%=listAccounts.get(i).getDob()%>
                                    </td>
                                    <td><%=listAccounts.get(i).getRole()%>
                                    </td>

                                    <%
                                        if (acc.getRole().equals("ADMIN")) {%>
                                    <td>
                                        <a href="/admin/account/edit?id=<%=listAccounts.get(i).getId()%>"
                                           class="btn-edit">Sửa</a>
                                        <form method="post" action="/admin/account?act=delete">
                                            <input type="hidden" name="accountId"
                                                   value="<%=listAccounts.get(i).getId()%>">
                                            <button class="btn-delete" type="submit">Xóa</button>
                                        </form>
                                    </td>
                                    <% } %>

                                </tr>
                                <%}%>

                                </tbody>
                            </table>
                        </div>

                        <div class="pagination-table-content">
                            <div class="wrapper-pagination">
                                <%--                            <div class="wrapper-qty-row">--%>
                                <%--                                <div>--%>
                                <%--                                    <p>Rows per table</p>--%>
                                <%--                                </div>--%>
                                <%--                                <div>--%>
                                <%--                                    <select name="qty_row" id="qtyRow">--%>
                                <%--                                        <option value="10">10</option>--%>
                                <%--                                        <option value="25">25</option>--%>
                                <%--                                        <option value="50">50</option>--%>
                                <%--                                        <option value="100">100</option>--%>
                                <%--                                    </select>--%>
                                <%--                                </div>--%>
                                <%--                            </div>--%>
                                <div class="wrapper-action-table">
                                    <div class="index">
                                        <% String pageIndex;
                                            int currentPage = 1;
                                            if (request.getParameter("pageIndex") != null) {
                                                pageIndex = request.getParameter("pageIndex");
                                                currentPage = Integer.parseInt(pageIndex);
                                            }

                                            pageIndex = currentPage + "/" + (int) Math.ceil(listAccounts.size() / 5.0);
                                            int next = (int) Math.ceil(listAccounts.size() / 5.0);
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
    <%=listAccounts.size()%>
    const rowPerPage = 5
    let currentPage = <%=currentPage%>;
</script>
<script type="text/javascript" src="<c:url value="../../assets/js/pagination.js"/>"></script>