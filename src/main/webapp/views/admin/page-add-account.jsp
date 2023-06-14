<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.dev4fun.model.Role" %>
<%@ page import="com.dev4fun.model.Account" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    Account account = (Account) request.getAttribute("account");
    String title = account != null ? "Thay đổi thông tin" : "Thêm";
%>
<title><%=title%> tài khoản</title>
<div id="main">
    <div class="content">
        <div class="box title-decorator--left">
            <div class="wrap-title">
                <div class="item-title">
                    <h3>Tài khoản / <%=title%> tài khoản</h3>
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
                            <h3><%=title%> tài khoản</h3>
                        </div>
                    </div>
                    <div>
                        <button class="btn-add" onclick="handleOpenModal(this);" value="modalAddCategoryAcc">Thêm loại
                            tài khoản
                        </button>
                    </div>

                    <div id="modalAddCategoryAcc" class="modal-category">
                        <div class="modal-content">
                            <div>
                                <div class="modal-line">
                                    <h3>Thêm loại tài khoản</h3>
                                </div>
                                <form method="post" action="/admin/account/add?act=role">
                                    <div class="modal-line form-line">
                                        <label for="newCategory">Tên loại tài khoản:</label>
                                        <input type="text" name="newCategory" id="newCategory">
                                    </div>
                                    <div class="modal-line form-line">
                                        <label>Loại tài khoản hiện có:</label>
                                        <ul>
                                            <%for (Role role : (ArrayList<Role>) request.getAttribute("listRole")) {%>
                                            <li><%=role.getName()%>
                                            </li>
                                            <%}%>
                                        </ul>
                                    </div>
                                    <div class="modal-line form-line">
                                        <button class="btn-add">Lưu lại</button>
                                        <button class="btn-cancel" onclick="handleCloseModal(this);"
                                                value="modalAddCategoryAcc">Hủy bỏ
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <%
                        String url = String.valueOf(request.getAttribute("action"));
                    %>
                    <form class="form-add" action="<%=url%>" method="post" enctype='multipart/form-data'>
                        <%
                            if (url.equals("/admin/account/edit")) {%>
                        <input type="hidden" name="idAccount" value="<%=request.getAttribute("id")%>">
                        <%}%>
                        <div class="form-group-line">
                            <div class="form-line">
                                <label for="fullName">Họ và tên:</label>
                                <input type="text" id="fullName" name="fullName" value="${fullName}" required>
                            </div>

                            <div class="form-line">
                                <label for="dob">Ngày sinh:</label>
                                <input type="date" id="dob" name="dob" value="${dob}">
                            </div>

                            <div class="form-line">
                                <label for="gender">Giới tính:</label>
                                <select name="gender" id="gender">
                                    <option value="" selected disabled hidden>-- Chọn giới tính --</option>
                                    <option value="Nam" ${Nam}>Nam</option>
                                    <option value="Nữ" ${Nữ}>Nữ</option>
                                    <option value="Khác" ${Khác}>Khác</option>
                                </select>
                            </div>

                        </div>

                        <div class="form-group-line">
                            <div class="form-line">
                                <label for="tel">Số điện thoại:</label>
                                <input type="tel" id="tel" name="tel" value="${tel}">
                            </div>

                            <div class="form-line">
                                <label for="address">Địa chỉ:</label>
                                <input type="text" id="address" name="address" value="${address}">
                            </div>

                            <div class="form-line">
                                <label for="email">Địa chỉ email:</label>
                                <input type="email" id="email" name="email" required value="${email}">
                            </div>

                        </div>

                        <div class="form-group-line">
                            <div class="form-line">
                                <label for="username">Tên tài khoản:</label>
                                <input type="text" id="username" name="username" required value="${username}">
                            </div>

                            <div class="form-line">
                                <label for="password">Mật khẩu:</label>
                                <input type="password" id="password" name="password" required value="${password}">
                            </div>

                            <div class="form-line">
                                <label for="role">Loại tài khoản:</label>
                                <select name="role" id="role" required>
                                    <option value="" selected disabled hidden>-- Chọn loại tài khoản --</option>
                                    <%
                                        String display = "";
                                        for (Role role : (ArrayList<Role>) request.getAttribute("listRole")) {
                                            if (request.getAttribute(role.getName()) != null) {
                                                display = (String) request.getAttribute(role.getName());
                                            } else display = "";
                                    %>
                                    <option value="<%=role.getName()%>" <%=display%> ><%=role.getName()%>
                                    </option>
                                    <%}%>
                                </select>
                            </div>
                        </div>

                        <div class="form-line">
                            <div class="title-list">
                                <label for="imageInput">Chọn ảnh đại diện:</label>
                            </div>
                            <div class="list-image">
                                <div class="item-image">
                                    <label for="imageInput">
                                        <p>Chọn ảnh</p>
                                        <%
                                            String imgDisplay;
                                            String imgLink;
                                            if (account != null) {
                                                imgDisplay = "";
                                                imgLink = account.getImageLink();
                                            } else {
                                                imgLink = "";
                                                imgDisplay = "none";
                                            }
                                        %>
                                        <img style="display: <%=imgDisplay%>>;" alt="" src="<%=imgLink%>">
                                    </label>
                                    <input class="image-input" id="imageInput" type="file" name="imageInput"
                                           onchange="handlePutNewImg(this);" enctype="multipart/form-data">
                                    <input type="hidden" id="textImageInput" name="textImageInput" value="<%=imgLink%>">
                                </div>
                            </div>
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

