<%--
  Created by IntelliJ IDEA.
  User: iamal
  Date: 01.08.2024
  Time: 22:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Registration</title>
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
                <form class="col-6 align-self-center" action="/calc" method="post">
                    <div class="mb-3">
                        <label for="exampleInputEmail1" class="form-label"><b>Username -></b></label>
                        <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp"
                               placeholder="enter username here" name="num1">
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputPassword1" class="form-label"><b>Password -></b></label>
                        <input type="text" class="form-control" id="exampleInputPassword1"
                               placeholder="enter password here" name="num2">
                    </div>
                    <label for="exampleInputPassword1" class="form-label"><b>Email -></b></label>
                    <input type="text" class="form-control" id="exampleInputPassword2"
                           placeholder="enter Email here" name="num2">
                    <button type="submit" class="btn btn-success w-100 mb-2 mt-3">Registration</button>
                    <button style="text-align: center" type="button" class="btn btn-outline-success btn-sm w-100">
                        Login
                    </button>
                </form>
            </div>
            <div style="text-align: center">
                <label for="exampleInputPassword1" class="form-label"><h1><b>${result}</b></h1></label>
            </div>
        </div>
        <div class="col-2"></div>
    </div>
</div>

<%--<div class="container text-center">
    <form action="/registration" method="post">
        <div class="row align-items-center justify-content-center">
            <div class="col-2">
                <label for="username">Username</label>
                <input type="text" class="form-control" id="username" placeholder="username" autocomplete="off"
                       name="username" required>
            </div>
            <div class="col-2">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" placeholder="password" autocomplete="off"
                       name="password" required>
            </div>
            <div class="col-2">
                <label for="email">Email</label>
                <input type="email" class="form-control" id="email" placeholder="email" name="email" required>
            </div>
        </div>
        <div class="row align-items-center justify-content-center mt-2">
            <div class="col-6">
                <button type="submit" class="btn btn-primary w-100">Registration</button>
            </div>
        </div>
        <jstl:if test="${not empty errorMessage}">
            <p class="text-danger">${errorMessage}</p>
        </jstl:if>
    </form>
</div>--%>
</body>
</html>
