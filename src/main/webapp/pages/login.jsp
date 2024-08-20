<%--
  Created by IntelliJ IDEA.
  User: Evgeny
  Date: 14.08.2024
  Time: 19:00
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<jsp:include page="_bootstrap.jsp"/>
<jsp:include page="_header.jsp"/>

<div class="container">
    <div class="row min-vh-100 align-items-center">
        <div class="col-2">
        </div>
        <div class="col-8">
            <div class="row justify-content-center mb-5">
                <img class="col-2"
                     src="https://cdn.pixabay.com/photo/2016/08/01/21/00/icon-1562136_1280.png"
                     alt="">
            </div>
            <div class="row justify-content-center">
                <form class="col-6 align-self-center" action="/login" method="post">
                    <div class="mb-3">
                        <label for="username_or_email" class="form-label"><b>Username or Email -></b></label>
                        <input value="${requestScope.username}" type="text" class="form-control" id="username_or_email"
                               placeholder="enter username or email here" name="username_or_email" autocomplete="off"
                               required>
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label"><b>Password -></b></label>
                        <input type="password" class="form-control" id="password" placeholder="enter password here"
                               autocomplete="off"
                               name="password" required>
                    </div>


                    <button type="submit" class="btn btn-success w-100 mb-2 mt-3">Login</button>
                    <button style="text-align: center" type="button" onclick="window.location='registration'" class="btn btn-outline-success btn-sm w-100">
                        Registration
                    </button>
                </form>
            </div>
            <div style="text-align: center">
                <jstl:if test="${not empty errorMessage}">
                    <label class="form-label"><h1><b>${errorMessage}</b></h1></label>
                </jstl:if>
            </div>
        </div>
        <div class="col-2"></div>
    </div>
</div>
</body>
</html>