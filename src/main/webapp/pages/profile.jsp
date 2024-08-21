<%--
  Created by IntelliJ IDEA.
  User: Tanya
  Date: 19.08.2024
  Time: 1:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>
<jsp:include page="_bootstrap.jsp"/>
<jsp:include page="_instaHeader.jsp"/>

<form action="/profile" method="post">
    <button>Submit</button>
</form>

<div class="container overflow-hidden">
    <div class="lines{margin-bottom: 150px;}">
        <div class="row gy-5">
            <div class="col-3">
            </div>
            <div class="col-2">
<%--                <img src="https://masterpiecer-images.s3.yandex.net/7c8387fca0d711eeb34896ac41f50fa8:upscaled"--%>
<%--                     class="rounded-circle" alt="200" width="200">--%>
    <img src="data:image/png;base64,${avatar}" class="rounded-circle" alt="200" width="200">


            </div>
            <div class="col-7">
                <div class="row align-items-start">
                    <div class="col-2 fs-5" style="height: 50px;">
                        ${userName}
                    </div>
                    <div class="col-3" style="height: 50px;">
                        <form action="/profile" method="post">
                            <button type="submit" class="btn btn-light fs-7 text-nowrap btn-sm w-100" role="button">
                                Редактировать профиль
                                <%--                            <a class="nav-link" href="/profileEdit">Редактировать профиль</a>--%>
                            </button>
                        </form>
                    </div>
                    <div class="col-3">
                        <button type="button" class="btn btn-light fs-7 btn-sm w-100">Посмотреть архив</button>
                    </div>
<%--                </div>--%>
                <div class="row align-items-center">
                    <div class="col-2 fs-7" style="height: 50px;">
                        3 публикаций
                    </div>
                    <div class="col-3 fs-7" style="height: 50px;">
                        78 подписчиков
                    </div>
                    <div class="col-3 fs-7" style="height: 50px;">
                        795 подписок
                    </div>
                </div>
                <div class="row align-items-end">
                    <div class="col-4 fs-9" style="height: 50px;">
                        ${name}
                    </div>
                    <div class="col-2 fs-9" style="height: 50px;">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
