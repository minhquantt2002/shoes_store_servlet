<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<title>Đăng nhập vào hệ thống quản trị</title>
<div>
    <div class="header">
        <a href="<c:url value="/admin"/>" class="logo">
            <img src="<c:url value="/assets/img/demo_den.png"/>" alt="Brand">
        </a>
    </div>
    <div class="content">
        <div class="container-login">
            <div class="wrapper">
                <div class="form">
                    <div class="form-title">
                        <div class="wrapper-form-title">
                            <h3>Đăng nhập</h3>
                        </div>
                    </div>
                    <p id="error-message" style="display: block; color: red;">${errorLog}</p>
                    <div>
                        <div class="form-body-line">
                            <div class="wrapper-form-line">
                                <label for="username">Tên đăng nhập (*)</label>
                                <div>
                                    <input type="text" placeholder="Tên đăng nhập" name="username" id="username" required>
                                </div>
                            </div>
                        </div>

                        <div class="form-body-line">
                            <div class="wrapper-form-line">
                                <label for="password">Mật khẩu (*)</label>
                                <div>
                                    <input type="password" placeholder="Mật khẩu" name="password" id="password" required>
                                </div>
                            </div>
                        </div>
                        <div class="form-body-line">
                            <div class="wrapper-form-line">
                                <div>
                                    <input id="onLogin" type="submit" value="Đăng Nhập">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    const btnLogin = document.getElementById("onLogin")
    const inputUsername = document.getElementById("username")
    const inputPassword = document.getElementById("password")
    btnLogin.addEventListener("click", async () => {
        const body = JSON.stringify({
            username: inputUsername.value,
            password: inputPassword.value
        })

        await fetch(
            '/api/admin/login',
            {
                method: "POST",
                headers: {
                    'Content-type': 'Application/json'
                },
                body: body
            }
        ).then(data => data.json())
            .then(msg => {
                if (msg['msg'] === 'true') {
                    alert("Đăng nhập thành công! Click Ok để tiếp tục.")
                    window.location.href = '/admin'
                }
            })
    })
</script>