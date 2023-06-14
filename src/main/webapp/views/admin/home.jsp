<%@ page import="com.dev4fun.model.Bill" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.dev4fun.model.Account" %>
<%@ page import="com.dev4fun.model.Chart" %>
<%@ page import="java.text.NumberFormat" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    NumberFormat nf = NumberFormat.getNumberInstance();
    nf.setGroupingUsed(true);
%>
<title>Trang chủ</title>
<div id="main">
    <div class="content">
        <div class="box title-decorator--left">
            <div class="wrap-title">
                <div class="item-title">
                    <h3>Trang chủ</h3>
                </div>
                <div class="timer">
                    <p id="timer"></p>
                </div>
            </div>
        </div>
        <!-- main content -->
        <div class="main-container" style="display: flex;">

            <div class="left-side">
                <div class="box-info">
                    <div class="wrap-info box">
                        <p class="icon-info">
                            <img src="../../assets/img/total-customer.png" width="100%">
                        </p>
                        <div class="info-recap">
                            <h4>Tổng khách hàng</h4>
                            <p><b><%=request.getAttribute("totalClientAccounts")%> khách hàng</b></p>
                            <p class="total-info">Tổng số khách hàng</p>
                        </div>
                    </div>
                </div>
                <div class="box-info">
                    <div class="wrap-info box">
                        <p class="icon-info">
                            <img src="../../assets/img/total-product.png" width="100%">
                        </p>
                        <div class="info-recap">
                            <h4>Tổng sản phẩm</h4>
                            <p><b><%=request.getAttribute("ttProd")%> sản phẩm</b></p>
                            <p class="total-info">Tổng số sản phẩm</p>
                        </div>
                    </div>
                </div>
                <div class="box-info">
                    <div class="wrap-info box">
                        <p class="icon-info">
                            <img src="../../assets/img/total-oder.png" width="100%" alt="">
                        </p>
                        <div class="info-recap">
                            <h4>Tổng đơn hàng</h4>
                            <p><b><%=request.getAttribute("ttBill")%> đơn hàng</b></p>
                            <p class="total-info">Tổng số hóa đơn</p>
                        </div>
                    </div>
                </div>
                <div class="box-info">
                    <div class="wrap-info box">
                        <p class="icon-info">
                            <img src="../../assets/img/none-product.png" width="100%" alt="">
                        </p>
                        <div class="info-recap">
                            <h4>Hết hàng</h4>
                            <p><b><%=request.getAttribute("ttExpired")%> sản phẩm</b></p>
                            <p class="total-info">Tổng số sản phẩm đã hết hàng</p>
                        </div>
                    </div>
                </div>
                <!-- box show new bills -->
                <div class="box-info mini-table">
                    <div class="wrap-info box">
                        <div class="table-content">
                            <div class="box-info-title">
                                <h3>Trạng thái đơn hàng</h3>
                            </div>
                            <table>
                                <thead>
                                <tr>
                                    <th>Mã đơn hàng</th>
                                    <th>Tên khách hàng</th>
                                    <th>Tổng tiền</th>
                                    <th>Trạng thái</th>
                                </tr>
                                </thead>
                                <tbody>
                                <%
                                    int limitBill = 5;
                                    ArrayList<Bill> bills = (ArrayList<Bill>) request.getAttribute("newBill");
                                    if (bills.size() <= 5) limitBill = bills.size();
                                    for (int i = 0; i <= limitBill; i++) { %>
                                <tr>
                                    <td title="<%=bills.get(i).getId()%>"><%=bills.get(i).getId()%>
                                    </td>
                                    <td title="<%=bills.get(i).getFullName()%>"><%=bills.get(i).getFullName()%>
                                    </td>
                                    <td title="<%=bills.get(i).getTotalAmount()%>"><%=nf.format(bills.get(i).getTotalAmount())%><sup>đ</sup>
                                    </td>
                                    <td title="<%=bills.get(i).getStatus()%>"><span><%=bills.get(i).getStatus()%></span>
                                    </td>
                                </tr>
                                <%}%>
                                </tbody>
                            </table>
                        </div>
                        <div class="link-page">
                            <a href="<c:url value="/admin/order"/>">Xem thêm</a>
                        </div>
                    </div>
                </div>
                <!-- Box show new user -->
                <div class="box-info mini-table">
                    <div class="wrap-info box">
                        <div class="table-content">
                            <div class="box-info-title">
                                <h3>Tài khoản khách hàng mới</h3>
                            </div>
                            <table>
                                <thead>
                                <tr>
                                    <th>Tên người dùng</th>
                                    <th>Họ và tên</th>
                                    <th>Email</th>
                                    <th>Số điện thoại</th>
                                </tr>
                                </thead>
                                <tbody>
                                <%
                                    int limitAccount = 5;
                                    ArrayList<Account> accounts = (ArrayList<Account>) request.getAttribute("newAcc");
                                    if(accounts.size()<=5) limitAccount = accounts.size();
                                    for (int i = 0;i<=limitAccount;i++) { %>
                                <tr>
                                    <td title="<%=accounts.get(i).getUsername()%>"><%=accounts.get(i).getUsername()%>
                                    </td>
                                    <td title="<%=accounts.get(i).getFullName()%>"><%=accounts.get(i).getFullName()%>
                                    </td>
                                    <td title="<%=accounts.get(i).getEmail()%>"><%=accounts.get(i).getEmail()%>
                                    </td>
                                    <td title="<%=accounts.get(i).getPhoneNumber()%>"><%=accounts.get(i).getPhoneNumber()%>
                                    </td>
                                </tr>
                                <%}%>
                                </tbody>
                            </table>
                        </div>
                        <div class="link-page">
                            <a href="<c:url value="/admin/account"/>">Xem thêm</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="right-side">

                <!-- chart  -->
                <div class="chart box-info mini-table">
                    <div class="wrap-info box">
                        <div class="box-info-title">
                            <h3>Thống kê doanh thu 6 tháng gần đây</h3>
                        </div>
                        <div class="wrap-chart">

                            <canvas id="myChart"></canvas>

                        </div>
                        <div class="link-page">
                            <a href="<c:url value="/admin/sale-stat"/>">Xem thêm</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    const xValues = [];
    const yValues = [];
    <%
        for(Chart chart: (ArrayList<Chart>)request.getAttribute("incomeForChart")){%>
    xValues.push("<%=chart.getTime()%>");
    yValues.push(<%=(chart.getIncome())%>);
    <%  } %>
    //DATATEST
    // const xValues = ["12/2022","01/2023","02/2023","03/2023","04/2023","05/2023"];
    // const yValues = [1500,2000,1800,2300,2500,1900];

    new Chart("myChart", {
        type: "line",
        data: {
            labels: xValues,
            datasets: [{
                // backgroundColor:"rgb(0,217,255)",
                borderColor: "rgba(0,217,255)",
                data: yValues,
                label: 'Doanh thu'
            }]
        },

    });
</script>