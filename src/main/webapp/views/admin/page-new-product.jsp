<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.dev4fun.model.Category" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.dev4fun.model.Product" %>
<%@ page import="com.dev4fun.model.ProductDetail" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    Product product = (Product) request.getAttribute("product");
    String title = product != null ? "Thay đổi thông tin" : "Thêm";
    NumberFormat nf = NumberFormat.getNumberInstance();
%>
<title><%=title%> sản phẩm</title>
<div id="main">
    <div class="content">
        <div class="box title-decorator--left">
            <div class="wrap-title">
                <div class="item-title">
                    <h3>Sản phẩm</h3>
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
                            <h3><%=title%> sản phẩm</h3>
                        </div>
                    </div>

                    <div>
                        <button class="btn-add" onclick="handleOpenModal(this);" value="modalAddCategory">Thêm danh mục mới</button>
                    </div>

                    <div id="modalAddCategory" class="modal-category">
                        <div class="modal-content">
                            <div>
                                <div class="modal-line">
                                    <h3>Thêm danh mục mới</h3>
                                </div>
                                <form action="<c:url value="/admin/product/add?act=category"/>" method="post" accept-charset="UTF-8">
                                    <div class="modal-line form-line">
                                        <label for="newCategory">Tên danh mục:</label>
                                        <input type="text" name="newCategory" id="newCategory">
                                    </div>
                                    <div class="modal-line form-line">
                                        <label>Danh mục sản phẩm hiện đang có</label>
                                        <ul>
                                            <%for (Category category : (ArrayList<Category>) request.getAttribute("listCategories")) {%>
                                            <li><%=category.getName()%>
                                            </li>
                                            <%}%>
                                        </ul>
                                    </div>
                                    <div class="modal-line form-line">
                                        <button class="btn-add" type="submit">Thêm danh mục</button>
                                        <button class="btn-cancel" onclick="handleCloseModal(this);" value="modalAddCategory" type="button">Hủy bỏ</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>

                    <form class="form-product" action="/admin/product/<%=product != null ? "edit" : "add"%>" method="post" enctype='multipart/form-data'>
                        <%if (product != null) {%>
                        <input type="hidden" name="id" value="<%=product.getId()%>">
                        <%}%>
                        <div class="form-group-line">
                            <div class="form-line">
                                <label for="name">Tên sản phẩm:</label>
                                <input type="text" value="<%=product != null ? product.getName() : ""%>" id="name" name="name" required>
                            </div>

                            <div class="form-line">
                                <label for="status">Trạng thái:</label>
                                <select name="status" id="status" required>
                                    <option value="" selected disabled hidden>-- Chọn trạng thái --</option>
                                    <option value="Còn hàng" <%=product != null && product.getStatus().equals("Còn hàng") ? "selected" : ""%>>Còn hàng</option>
                                    <option value="Hết Hàng" <%=product != null && product.getStatus().equals("Hết hàng") ? "selected" : ""%>>Hết hàng</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group-line">
                            <div class="form-line">
                                <label for="category">Danh mục:</label>
                                <select name="category" id="category">
                                    <option value="" selected disabled hidden>-- Chọn danh mục --</option>
                                    <%
                                        for (Category category : (ArrayList<Category>) request.getAttribute("listCategories")) {
                                            if (product != null && category.getId() == product.getCategoryId()) {
                                    %>
                                    <option value="<%=category.getId()%>" selected><%=category.getName()%>
                                    </option>
                                    <%} else {%>
                                    <option value="<%=category.getId()%>"><%=category.getName()%>
                                    </option>
                                    <%
                                            }
                                        }
                                    %>
                                </select>
                            </div>

                            <div class="form-line">
                                <label for="price">Giá bán:</label>
                                <input type="text" id="price" value="<%=product != null ? nf.format(product.getPrice()) : ""%>" name="price" required>
                            </div>

                            <div class="form-line">
                                <label for="cost">Giá gốc:</label>
                                <input type="text" id="cost" value="<%=product != null ? nf.format(product.getCost()) : ""%>" name="cost" required>
                            </div>
                        </div>

                        <div class="form-line">
                            <div class="title-list">
                                <label>Chọn size:</label>
                                <button type="button" class="btn-add-div" onclick="addSizeInProduct();">Thêm size</button>
                            </div>
                            <div class="list-size">
                                <%
                                    if (product != null) {
                                        int i = 0;
                                        for (ProductDetail productDetail : product.getProductDetails()) {
                                %>
                                <div class="item-size">
                                    <input type="hidden" id="productDetailId<%=i%>" value="<%=productDetail.getId()%>" name="productDetailId<%=i%>">
                                    <div>
                                        <label for="size<%=i%>">Size:</label>
                                        <input type="text" id="size<%=i%>" value="<%=productDetail.getSize()%>" name="size<%=i%>">
                                    </div>
                                    <div>
                                        <label for="quantity<%=i%>">Số lượng:</label>
                                        <input type="number" id="quantity<%=i%>" name="quantity<%=i%>" value="<%=productDetail.getQuantity()%>">
                                    </div>
                                </div>
                                <%
                                            i++;
                                        }
                                    }
                                %>
                            </div>
                        </div>

                        <div class="form-line">
                            <div class="title-list">
                                <%--@declare id="imageinput"--%>
                                <label for="imageInput">Chọn ảnh:</label>
                                <button type="button" class="btn-add-div" onclick="addImgInProduct();">Thêm ảnh</button>
                            </div>
                            <div class="list-image">
                                <%
                                    if (product != null) {
                                        int i = 0;
                                        ArrayList<String> imgLinks = new ArrayList<>();
                                        imgLinks.add(product.getImageLink());
                                        imgLinks.addAll(List.of(product.getImageList().split(",")));
                                        for (String img : imgLinks) {
                                %>
                                <div class="item-image">
                                    <label for="imageInput<%=i%>">
                                        <p>Chọn ảnh</p>
                                        <img src="<%=img%>" alt="">
                                    </label>
                                    <input onchange="handleChangeImgInput(<%=i%>)" class="image-input" id="imageInput<%=i%>" type="file" name="imageInput<%=i%>" enctype='multipart/form-data'>
                                    <input type="hidden" id="textImageInput<%=i%>" name="textImageInput<%=i%>" value="<%=img%>">
                                </div>
                                <%
                                            i++;
                                        }
                                    }
                                %>
                            </div>

                        </div>

                        <div class="form-line">
                            <label for="description">Mô tả:</label>
                            <textarea id="description" name="description" style="width: 100%; height: 200px;"><%=product != null ? product.getDescription() : ""%></textarea>
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

<script>
    function handleChangeImgInput(index) {
        const imgInput = document.getElementById('textImageInput' + index)
        if (imgInput !== null) {
            imgInput.value = ''
        }
    }
</script>
