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
                <form class="col-6 align-self-center" action="/registration" method="post">
                    <div class="mb-3">
                        <label for="username" class="form-label"><b>Username -></b></label>
                        <input type="text" class="form-control" id="username"
                               placeholder="enter username here" autocomplete="off"
                               name="username" required>
                    </div>


                    <div class="mb-3">
                        <label for="password" class="form-label"><b>Password -></b></label>
                        <input type="text" class="form-control" id="password"
                               placeholder="enter password here" autocomplete="off" name="password" required>
                    </div>


                    <label for="email" class="form-label"><b>Email -></b></label>
                    <input type="email" class="form-control" id="email"
                           placeholder="enter Email here" name="email" required>


                    <button type="submit" class="btn btn-success w-100 mb-2 mt-3">Registration</button>
                    <button style="text-align: center" type="button" onclick="window.location='login'" class="btn btn-outline-success btn-sm w-100">
                        Login
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

       <%--<jstl:if test="${not empty errorMessage}">
            <p class="text-danger">${errorMessage}</p>
        </jstl:if>--%>
    </form>

</body>
</html>