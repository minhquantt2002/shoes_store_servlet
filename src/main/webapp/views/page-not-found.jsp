<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<style>


    .container {
        width: 80%;
        max-width: 500px;
        margin: 0 auto;
        text-align: center;
        padding-top: 100px;
    }

    .container h1 {
        font-size: 160px;
        margin: 0;
        font-weight: 600;
    }

    .container .btn-back {
        display: inline-block;
        background-color: #333;
        color: #fff;
        padding: 10px 20px;
        margin-top: 20px;
        text-decoration: none;
        border-radius: 4px;
    }

    .btn-back:hover {
        background-color: #555;
    }
</style>
<title>Không tìm thấy trang</title>
<div class="container">
    <h1>404</h1>
    <h2>Không tìm thấy trang</h2>
    <p>Trang bạn đang tìm kiếm có thể đã bị xóa, chuyển đi, thay đổi link hoặc chưa bao giờ tồn tại.</p>
    <a href="<c:url value="/home"/>" class="btn-back">Trở về trang chủ</a>
</div>