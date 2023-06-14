<%@ page import="java.util.ArrayList" %>
<%@ page import="com.dev4fun.model.Config" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../common/taglib.jsp" %>
<style>
    .wrapper-editor .editor-item {
        margin-bottom: 20px;
        margin-top: 20px;
        padding: 12px;
        background-color: white;
        border-radius: 5px;
        border: 1px solid rgba(0, 0, 0, 0.1);
    }

    .editor-item h3 {
        margin: 0 0 8px 8px;
    }

    .editor-item #fr-logo {
        display: none !important;
    }

    .editor-item > button {
        margin-top: 8px;
        margin-left: 8px;
        padding-left: 20px;
        padding-right: 20px;
        background-color: rgb(155, 255, 162);
    }

</style>
<%
    ArrayList<Config> listConfigs = (ArrayList<Config>) request.getAttribute("listConfigs");
%>
<title>Cấu hình trang</title>
<div id="main">
    <div class="content">
        <div class="box title-decorator--left">
            <div class="wrap-title">
                <div class="item-title">
                    <h3>Cấu hình trang</h3>
                </div>
                <div class="timer">
                    <p id="timer"></p>
                </div>
            </div>
        </div>

        <div class="wrapper-editor">
            <div class="editor-item">
                <h3>Cấu hình trang điều khoản, chính sách</h3>
                <textarea class="editor" id="policy"></textarea>
                <button id="submitPolicy">Lưu lại</button>
            </div>

            <div class="editor-item">
                <h3>Cấu hình trang về chúng tôi</h3>
                <textarea class="editor" id="aboutUs"></textarea>
                <button id="submitAboutUs">Lưu lại</button>
            </div>

            <div class="editor-item">
                <h3>Cấu hình trang hệ thống cửa hàng</h3>
                <textarea class="editor" id="store"></textarea>
                <button id="submitStore">Lưu lại</button>
            </div>
        </div>
    </div>
</div>


<script src="https://cdnjs.cloudflare.com/ajax/libs/froala-editor/3.2.6/js/froala_editor.pkgd.min.js"></script>
<script>
    const policy = new FroalaEditor('#policy', {
        imageUploadURL: 'http://localhost:8080/admin/api/upload',
        imageEditButtons: ['imageReplace', 'imageAlign', 'imageRemove', '|', 'imageLink', 'linkOpen', 'linkEdit', 'linkRemove', '-', 'imageDisplay', 'imageStyle', 'imageAlt', 'imageSize'],

    }, () => {
        policy.html.set(`<%=listConfigs.get(1).getContent()%>`)
    })

    const aboutUs = new FroalaEditor('#aboutUs', {
        imageUploadURL: 'http://localhost:8080/admin/api/upload',
        imageEditButtons: ['imageReplace', 'imageAlign', 'imageRemove', '|', 'imageLink', 'linkOpen', 'linkEdit', 'linkRemove', '-', 'imageDisplay', 'imageStyle', 'imageAlt', 'imageSize']
    }, () => {
        aboutUs.html.set(`<%=listConfigs.get(0).getContent()%>`)
    })

    const store = new FroalaEditor('#store', {
        imageUploadURL: 'http://localhost:8080/admin/api/upload',
        imageEditButtons: ['imageReplace', 'imageAlign', 'imageRemove', '|', 'imageLink', 'linkOpen', 'linkEdit', 'linkRemove', '-', 'imageDisplay', 'imageStyle', 'imageAlt', 'imageSize']
    }, () => {
        store.html.set(`<%=listConfigs.get(2).getContent()%>`)
    })

    const submitPolicy = document.getElementById('submitPolicy')
    submitPolicy.addEventListener('click', async () => {
        const body = JSON.stringify({content: policy.html.get()})
        await fetch(
            '/admin/api/config/policy',
            {
                method: 'POST',
                body: body
            }
        ).then(data => {
            alert('Sửa trang điều khoản và thỏa thuận thành công! Click Ok để tiếp tục.')
            location.reload()
        })
    })

    const submitAboutUs = document.getElementById('submitAboutUs')
    submitAboutUs.addEventListener('click', async () => {
        const body = JSON.stringify({content: aboutUs.html.get()})
        await fetch(
            '/admin/api/config/about-us',
            {
                method: 'POST',
                body: body
            }
        ).then(data => {
            alert('Sửa trang giới thiệu thành công! Click Ok để tiếp tục.')
            location.reload()
        })
    })

    const submitStore = document.getElementById('submitStore')
    submitStore.addEventListener('click', async () => {
        const body = JSON.stringify({content: store.html.get()})
        await fetch(
            '/admin/api/config/store',
            {
                method: 'POST',
                body: body
            }
        ).then(data => {
            alert('Sửa trang hệ thống cửa hàng thành công! Click Ok để tiếp tục.')
            location.reload()
        })
    })
</script>