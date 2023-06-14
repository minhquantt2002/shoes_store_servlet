<%--@elvariable id="ACCOUNT_USER" type="com.dev4fun.model.Account"--%>
<%@ page import="com.dev4fun.model.Account" %>
<%@ page import="com.dev4fun.utils.SessionUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div id="main">
    <div class="content content-profile">
        <div class="profile-section">
            <div class="wrapper-avatar">
                <img src="${ACCOUNT_USER.imageLink}" alt="Logo" class="logo">
                <p>${ACCOUNT_USER.fullName}</p>
            </div>

            <form class="form-product" action="<c:url value="/user/profile"/>" method="post">
                <div class="form-line">
                    <label for="fullName">Họ và tên:</label>
                    <input type="text" id="fullName" name="fullName" value="${ACCOUNT_USER.fullName}" required>
                </div>

                <div class="form-group-line">
                    <div class="form-line">
                        <label for="username">Tên đăng nhập:</label>
                        <input type="text" id="username" name="username" value="${ACCOUNT_USER.username}" required>
                    </div>

                    <div class="form-line">
                        <label for="password">Mật khẩu:</label>
                        <input type="password" id="password" name="password" value="${ACCOUNT_USER.password}" required>
                    </div>
                </div>

                <div class="form-group-line">
                    <div class="form-line">
                        <label for="email">Email:</label>
                        <input type="email" id="email" name="email" value="${ACCOUNT_USER.email}" required>
                    </div>

                    <div class="form-line">
                        <label for="tel">Số điện thoại:</label>
                        <input type="tel" id="tel" name="tel" value="${ACCOUNT_USER.phoneNumber}" required>
                    </div>
                </div>

                <div class="form-group-line">
                    <div class="form-line">
                        <label for="dob">Ngày sinh:</label>
                        <input type="text" id="dob" name="dob" value="${ACCOUNT_USER.dob}" required>
                    </div>
                    <div class="form-line">
                        <label for="gender">Giới tính:</label>
                        <%
                            String gender = ((Account) SessionUtil.getInstance().getValue(request, "ACCOUNT_USER")).getGender();
                            if (gender.equals("Nam")) {
                                gender = "c";
                            } else if (gender.equals("Nữ")) {
                                gender = "a";
                            } else if (gender.equals("Khác")) {
                                gender = "b";
                            }
                            request.setAttribute(gender, "selected");
                        %>
                        <select name="gender" id="gender" required>
                            <option value="${ACCOUNT_USER.gender}" selected>-- Chọn giới tính --</option>
                            <option value="Nam" ${c} >Nam</option>
                            <option value="Nữ" ${a} >Nữ</option>
                            <option value="Khác" ${b} >Khác</option>
                        </select>
                    </div>
                </div>
                <div class="form-group-line">
                    <div class="form-line">
                        <label for="address">Địa chỉ:</label>
                        <input type="text" id="address" name="address" value="${ACCOUNT_USER.address}" required>
                    </div>
                </div>
                <div class="form-line form-line-btn">
                    <button class="btn-add" style="background-color: #333333;color: white;" type="submit">Lưu lại</button>
                </div>
            </form>
        </div>

    </div>
</div>