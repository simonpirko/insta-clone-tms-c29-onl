<%--
  Created by IntelliJ IDEA.
  User: iamal
  Date: 01.08.2024
  Time: 22:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<jsp:include page="_bootstrap.jsp"/>
<jsp:include page="_header.jsp"/>
<div class="container text-center">
    <form action="/registration" method="post">
        <div class="row align-items-center justify-content-center">
            <div class="col-2">
                <label for="username">username</label>
                <input type="text" class="form-control" id="username" value="" autocomplete="off" name="username">
            </div>
            <div class="col-2">
                <label for="password">password</label>
                <input type="password" class="form-control" id="password" placeholder="password" autocomplete="off"
                       name="password">
            </div>
            <div class="col-2">
                <label for="email">email</label>
                <input type="email" class="form-control" id="email" name="email">
            </div>
        </div>
        <div class="row align-items-center justify-content-center mt-2">
            <div class="col-6">
                <button type="submit" class="btn btn-primary w-100">registration</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>
