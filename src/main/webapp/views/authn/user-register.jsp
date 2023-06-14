<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<title>Đăng ký tài khoản</title>
<div>
    <div class="header">
        <a href="<c:url value="/home"/>" class="logo">
            <img src="<c:url value="/assets/img/demo_den.png"/>" alt="">
        </a>
    </div>
    <div class="content">
        <div class="container-register">
            <div class="wrapper">
                <div class="form">
                    <div class="form-title">
                        <div class="wrapper-form-title">
                            <h3>Đăng ký</h3>
                            <a href="<c:url value="/login"/>">Bạn đã có tài khoản?</a>
                        </div>
                    </div>

                    <div class="form-body">
                        <!-- required -->
                        <div class="side-required">
                            <div class="form-body-line">
                                <div class="wrapper-form-line">
                                    <label for="fullName">Họ và tên (*)</label>
                                    <div>
                                        <input type="text" placeholder="Họ và tên" name="fullName" id="fullName" required>
                                    </div>
                                </div>
                            </div>

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
                                    <label for="password2">Nhập lại mật khẩu (*)</label>
                                    <div>
                                        <input type="password" placeholder="Nhập lại mật khẩu" id="password2" required>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- optional -->
                        <div class="side-optional">
                            <div class="form-body-line">
                                <div class="wrapper-form-line">
                                    <label for="email">Email (?)</label>
                                    <div>
                                        <input type="text" placeholder="Email" name="email" id="email">
                                    </div>
                                </div>
                            </div>

                            <div class="form-body-line">
                                <div class="wrapper-form-line">
                                    <label for="tel">Số điện thoại (?)</label>
                                    <div>
                                        <input type="text" placeholder="Số điện thoại" name="tel"
                                               id="tel">
                                    </div>
                                </div>
                            </div>

                            <div class="form-body-line">
                                <div class="wrapper-form-line">
                                    <label for="dob">Ngày sinh (?)</label>
                                    <div>
                                        <input type="date" placeholder="Sinh nhật" name="dob" id="dob">
                                    </div>
                                </div>
                            </div>

                            <div class="form-body-line">
                                <div class="wrapper-form-line">
                                    <label>Giới tính (?)</label>
                                    <div class="gender">
                                        <div>
                                            <input type="radio" name="gender" value="Nam" checked>
                                            <p>Nam</p>
                                        </div>
                                        <div>
                                            <input type="radio" name="gender" value="Nữ">
                                            <p>Nữ</p>
                                        </div>
                                        <div>
                                            <input type="radio" name="gender" value="Khác">
                                            <p>Khác</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="form-body-line">
                        <div class="wrapper-form-line">
                            <label for="address">Địa chỉ (?)</label>
                            <div>
                                <input style="background: #ffffff" type="text" placeholder="Địa chỉ" name="address" id="address">
                            </div>
                        </div>
                    </div>

                    <div class="form-body-line">
                        <div class="wrapper-form-line">
                            <div>
                                <input type="submit" id="onRegister" value="Đăng ký">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    const btnRegister = document.getElementById("onRegister")

    const inputUsername = document.getElementById("username")
    const inputPassword1 = document.getElementById("password")
    const inputPassword2 = document.getElementById("password2")
    const inputFullName = document.getElementById("fullName")
    const inputEmail = document.getElementById("email")
    const inputTel = document.getElementById("tel")
    const inputDob = document.getElementById("dob")
    const inputGender = document.querySelector('input[name=gender]:checked')
    const inputAddress = document.getElementById("address")

    btnRegister.addEventListener("click", async () => {
        const body = JSON.stringify({
            username: inputUsername.value,
            password: inputPassword1.value,
            fullName: inputFullName.value,
            email: inputEmail.value,
            tel: inputTel.value,
            dob: inputDob.value,
            gender: inputGender.value,
            address: inputAddress.value,
        })

        await fetch(
            '/api/user/register',
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
                    alert("Đăng ký thành công! Click Ok để tiếp tục.")
                    window.location.href = '/login'
                }
            })
    })
</script>