<%@page import="com.dev4fun.dao.ProductDAO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.dev4fun.model.Product" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.dev4fun.model.ProductDetail" %>
<%@ page import="com.dev4fun.dao.CommentDAO" %>
<%@ page import="com.dev4fun.model.Comment" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<style>

    .product-comment {
        margin-bottom: 20px;
        margin-top: 60px;
        background-color: white;
        padding: 30px;
    }


    .product-comment input[type="submit"] {
        padding: 8px 16px;
        background-color: #337ab7;
        color: white;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        font-size: 18px;
        margin-left: 15px;
    }

    .product-comment input[type="text"] {
        width: 800px;
        padding: 12px;
        font-size: 18px;
    }

    .comment-list {
        list-style-type: none;
        padding: 0;
        margin-top: 25px;
    }

    .comment-item {
        margin-bottom: 10px;
        padding: 10px;
        background-color: #f9f9f9;
        border: 1px solid #ddd;
        border-radius: 5px;
    }

    .comment-user {
        font-weight: bold;
        display: flex;
    }

    .comment-user p {
        margin-left: 12px;
        font-size: 18px;
    }

    .comment-content {
        margin-top: 5px;
    }
</style>

<link href="<c:url value="/assets/style/user/product-detail.css"/>" rel="stylesheet" type="text/css"/>
<%
    Product product = (Product) request.getAttribute("product");
    ArrayList<String> listImages = new ArrayList<>(List.of(product.getImageList().split(",")));
    NumberFormat nf = NumberFormat.getNumberInstance();
%>
<title><%=product.getName()%></title>
<div class="main">
    <div class="product">
        <div class="product-top">
            <a href="<c:url value="/home"/>">Trang chủ</a>
            <span>/</span>
            <a href="/products?category=<%=product.getCategory().getName()%>"><%=product.getCategory().getName()%>
            </a>
            <span>/</span>
            <a href="/products/<%=product.getName().replaceAll(" ", "-") + "-" + product.getId()%>"><%=product.getName()%>
            </a>
        </div>
        <div class="product-content">
            <div class="product-content-left">
                <div class="product-content-left-big-img">
                    <img src="<%=product.getImageLink()%>" alt="">
                </div>
                <div class="product-content-left-small-img">
                    <%for (String image : listImages) {%>
                    <img src="<%=image%>" alt="">
                    <%}%>
                </div>
            </div>
            <div class="product-content-right">
                <div class="product-content-right-product-name">
                    <h1><%=product.getName()%>
                    </h1>
                    <div class="product-content-right-product-name-danhgia row">
                        <span>Mã sản phẩm: <%="SP" + product.getId()%></span>
                    </div>
                </div>
                <div class="product-content-right-product-price">
                    <p><%=nf.format(product.getPrice())%><sup>đ</sup></p>
                </div>
                <div class="product-content-right-product-size">
                    <p style="font-weight: bold; font-size: 18px;">Size:</p>
                    <div class="size">
                        <%
                            for (ProductDetail pd : product.getProductDetails()) {
                                if (pd.getQuantity() == 0) continue;
                        %>
                        <span id="choose-size" onclick="getSize(this)"
                              aria-valuenow="<%=pd.getSize()%>"><%=pd.getSize()%></span>
                        <%}%>
                    </div>
                </div>
                <div class="quantity">
                    <p>Số lượng:</p>
                    <div class="quantity-set">
                        <div class="quantity-reduce">
                            <div onclick="sub()">-</div>
                        </div>
                        <div class="quatity-value">
                            <input id="quantityValue" type="text" value="1">
                        </div>
                        <div class="quantity-increase">
                            <div onclick="add()">+</div>
                        </div>
                    </div>
                </div>
                <div class="product-content-right-product-button">
                    <div class="product-content-right-product-button-cart">
                        <form id="addToCart" action="<c:url value="/cart"/>" method="post">
                            <button type="submit" onclick="getValue()">
                                THÊM VÀO GIỎ HÀNG
                            </button>
                            <input type="hidden" name="productId" value="<%=product.getId()%>">
                            <input type="hidden" name="act" value="add">
                            <input id="qtt" type="hidden" name="quantity" value="-1">
                            <input id="size" type="hidden" name="size" value="-1">
                        </form>
                    </div>
                    <div class="product-content-right-product-button-mua">
                        <form id="buyNow" action="<c:url value="/checkout"/>" method="get">
                            <button type="submit" onclick="getValue()">
                                MUA NGAY
                            </button>
                            <input type="hidden" name="productId" value="<%=product.getId()%>">
                            <input id="qtt1" type="hidden" name="quantity" value="-1">
                            <input id="size1" type="hidden" name="size" value="-1">
                        </form>
                    </div>
                </div>
                <div class="product-content-right-bottom">
                    <div class="product-content-right-bottom-content-big">
                        <div class="product-content-right-bottom-content-title">
                            <div class="product-content-right-bottom-content-title-item mota">
                                <p>Mô tả sản phẩm</p>
                            </div>
                        </div>
                        <div class="product-content-right-bottom-content">
                            <%=product.getDescription()%>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <div class="product-comment">
            <form method="post" action="<c:url value="/products/detail"/>"
                  style="    display: flex; justify-content: center;">
                <input type="text" name="commentText" placeholder="Nhập bình luận" required><br>
                <input type="hidden" name="productId" value="<%= product.getId() %>">
                <input type="submit" value="Bình luận">
            </form>
            <ul class="comment-list ">
                <% List<Comment> comments = new CommentDAO().getCommentByProductId(product.getId());
                    for (Comment comment : comments) { %>
                <li class="comment-item">
                    <div class="comment-user">
                        <img src="<%=comment.getUser().getImageLink()%>" alt="" width="24">
                        <p><%=comment.getUser().getFullName()%>
                        </p>
                    </div>
                    <div class="comment-content"><%= comment.getContent()%>
                    </div>
                </li>
                <% } %>
            </ul>
        </div>


    </div>
</div>

<script>
    const bigImg = document.querySelector(".product-content-left-big-img img")
    const smallImg = document.querySelectorAll(".product-content-left-small-img img")
    smallImg.forEach(function (imgItem) {
        imgItem.addEventListener("click", function () {
            bigImg.src = imgItem.src
        })
    })
    const sizeSelected1 = document.getElementById("size1")
    const qttSelected1 = document.getElementById("qtt1")
    const sizeSelected = document.getElementById("size")
    const qttSelected = document.getElementById("qtt")
    const inputQtt = document.getElementById('quantityValue')
    let previousElement = null;

    function getSize(e) {
        sizeSelected.value = e.ariaValueNow
        sizeSelected1.value = e.ariaValueNow
        if (previousElement !== null) {
            previousElement.style.fontWeight = "normal";
        }
        e.style.fontWeight = "bold";
        e.style.color = "black";
        previousElement = e;
    }

    const addToCart = document.getElementById("addToCart")
    const buyNow = document.getElementById("buyNow")

    function getValue() {
        if (sizeSelected.value === '-1' && sizeSelected1.value === '-1') {
            alert('Hãy chọn size cho sản phẩm!')
            addToCart.onsubmit = (e) => {
                e.preventDefault()
            }
            buyNow.onsubmit = (e) => {
                e.preventDefault()
            }
        } else {
            addToCart.onsubmit = () => { }
            buyNow.onsubmit = () => { }
        }
        qttSelected.value = inputQtt.value
        qttSelected1.value= inputQtt.value
    }


    function add() {
        inputQtt.value = parseInt(inputQtt.value) + 1
    }

    function sub() {
        inputQtt.value = parseInt(inputQtt.value) - 1
    }
</script>
