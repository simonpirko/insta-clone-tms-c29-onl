<%--
  Created by IntelliJ IDEA.
  User: Nikita
  Date: 06.08.2024
  Time: 11:15
  To change this template use File | Settings | File Templates.
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
<div class="container text-center">
    <form action="/login" method="post">
        <div class="row align-items-center justify-content-center">
            <div class="col-3">
                <label for="username_or_email">username</label>
                <input type="text" class="form-control" id="username_or_email" placeholder="username or email"
                       value="${requestScope.username}" autocomplete="off" name="username_or_email" required>
            </div>
            <div class="col-3">
                <label for="password">password</label>
                <input type="password" class="form-control" id="password" placeholder="password" autocomplete="off"
                       name="password" required>
            </div>
        </div>
        <div class="row align-items-center justify-content-center mt-2">
            <div class="col-6">
                <button type="submit" class="btn btn-primary w-100">Login</button>
            </div>
        </div>
        <jstl:if test="${not empty errorMessage}">
            <p class="text-danger">${errorMessage}</p>
        </jstl:if>
    </form>
</div>
</body>
</html>
