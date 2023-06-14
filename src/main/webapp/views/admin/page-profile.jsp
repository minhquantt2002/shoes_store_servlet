<%@ page import="com.dev4fun.model.Account" %>
<%@ page import="com.dev4fun.utils.SessionUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<title>Thông tin tài khoản</title>
<div id="main">
    <div class="content">
        <div class="profile-section">
            <div class="wrapper-avatar">
                <img src="${ACCOUNT_ADMIN.imageLink}" alt="Logo" class="logo">
                <p>${ACCOUNT_ADMIN.fullName}</p>
            </div>

            <form class="form-product" action="<c:url value="/admin/profile"/>" method="post">
                <div class="form-line">
                    <label for="fullName">Họ và tên:</label>
                    <input type="text" id="fullName" name="fullName" value="${ACCOUNT_ADMIN.fullName}" required>
                </div>

                <div class="form-group-line">
                    <div class="form-line">
                        <label for="username">Tên đăng nhập:</label>
                        <input type="text" id="username" name="username" value="${ACCOUNT_ADMIN.username}" required>
                    </div>

                    <div class="form-line">
                        <label for="password">Mật khẩu:</label>
                        <input type="password" id="password" name="password" value="${ACCOUNT_ADMIN.password}" required>
                    </div>
                </div>

                <div class="form-group-line">
                    <div class="form-line">
                        <label for="email">Email:</label>
                        <input type="email" id="email" name="email" value="${ACCOUNT_ADMIN.email}" required>
                    </div>

                    <div class="form-line">
                        <label for="tel">Số điện thoại:</label>
                        <input type="tel" id="tel" name="tel" value="${ACCOUNT_ADMIN.phoneNumber}" required>
                    </div>
                </div>

                <div class="form-group-line">
                    <div class="form-line">
                        <label for="dob">Ngày sinh:</label>
                        <input type="text" id="dob" name="dob" value="${ACCOUNT_ADMIN.dob}" required>
                    </div>
                    <div class="form-line">
                        <label for="gender">Giới tính:</label>
                        <select name="gender" id="gender" required>
                            <option value="${ACCOUNT_ADMIN.gender}" selected>${ACCOUNT_ADMIN.gender}</option>
                            <%
                                String gender = ((Account) SessionUtil.getInstance().getValue(request, "ACCOUNT_ADMIN")).getGender();
                                String optionGender1, optionGender2;
                                if (gender.equals("Nam")) {
                                    optionGender1 = "Nữ";
                                    optionGender2 = "Khác";
                                } else if (gender.equals("Nữ")) {
                                    optionGender1 = "Nam";
                                    optionGender2 = "Khác";
                                } else {
                                    optionGender1 = "Nam";
                                    optionGender2 = "Nữ";
                                }
                            %>
                            <option value="<%=optionGender1%>"><%=optionGender1%>
                            </option>
                            <option value="<%=optionGender2%>"><%=optionGender2%>
                            </option>
                        </select>
                    </div>

                </div>
                <div class="form-line form-line-btn">
                    <button class="btn-add" type="submit">Lưu lại</button>
                </div>
            </form>
        </div>

    </div>
</div>