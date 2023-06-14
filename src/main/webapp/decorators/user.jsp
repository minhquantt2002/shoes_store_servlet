<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <title><dec:title default="Trang chủ"/></title>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <link rel="icon" href="https://icon-library.com/images/web-dev-icon/web-dev-icon-27.jpg"/>
    <script src="https://kit.fontawesome.com/5c6f358cdb.js" crossorigin="anonymous"></script>
</head>
<body>
<!-- Header-->
<%@include file="/common/user/header.jsp" %>

<!-- Main-->
<dec:body/>

<div style="position: fixed;bottom: 32px;right: 20px;cursor: pointer;">
    <a href="https://www.facebook.com/messages/t/100009485747337" style="  position: absolute; bottom: 30px; right: 0px;">
        <img onmouseover="showInfoBox(this)" onmouseout="hideInfoBox()" alt=""
             src="https://upload.wikimedia.org/wikipedia/commons/thumb/6/6c/Facebook_Messenger_logo_2018.svg/2048px-Facebook_Messenger_logo_2018.svg.png"
             width="56px" height="56px">
    </a>
    <div id="infoBox" class="info-box">
        <h4>Liên hệ với chúng tôi qua Facebook</h4>
    </div>
</div>

<!-- Footer-->
<%@include file="/common/user/footer.jsp" %>

</body>
</html>
