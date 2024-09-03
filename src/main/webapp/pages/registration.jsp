<%@ page import="java.util.Properties" %><%--
  Created by IntelliJ IDEA.
  User: Evgeny
  Date: 14.08.2024
  Time: 19:00
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    Properties properties = (Properties) request.getAttribute("properties");
    String name = properties.getProperty("registration.name");
    String surname = properties.getProperty("registration.surname");
    String email = properties.getProperty("registration.email");
    String photo = properties.getProperty("registration.photo");
    String username = properties.getProperty("registration.username");
    String password = properties.getProperty("registration.password");
    String button = properties.getProperty("registration.button");
    String button2 = properties.getProperty("registration.button2");
    String description = properties.getProperty("registration.description");
%>
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
                <form class="col-6 align-self-center" action="/registration" method="post"
                      enctype="multipart/form-data">
                    <div class="row mb-3">
                        <div class="col">
                            <label for="name" class="form-label"><%=name%>
                            </label>
                            <input name="name" type="text" class="form-control" id="name" required pattern="(^[A-Za-z]{3,16})">
                        </div>
                    </div>
                    <div class="mb-3">
                        <div class="col">
                            <label for="surname" class="form-label"><%=surname%>
                            </label>
                            <input name="surname" type="text" class="form-control" id="surname" required
                                   pattern="(^[A-Za-z]{3,16})">
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label"><%=email%>
                        </label>
                        <input name="email" type="email" class="form-control" id="email" required
                               pattern="^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$">
                    </div>
                    <div class="mb-3">
                        <label class="form-label" for="photo"><%=photo%>
                        </label>
                        <input type="file" class="form-control" id="photo" name="filePathPhoto">
                    </div>
                    <div class="mb-3">
                        <label for="username" class="form-label"><%=username%>
                        </label>
                        <input name="username" type="text" class="form-control" id="username" required pattern="\w*">
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label"><%=password%>
                        </label>
                        <input name="password" type="password" class="form-control" id="password" required>
                    </div>
                    <div class="d-grid gap-2 col-6 mx-auto">
                        <button class="btn btn-dark" type="submit"><%=button%>
                        </button>
                    </div>

                    <p class="fs-6"></p>

                    <p class="text-body-secondary">
                        <%=description%> <a href="/login"><%=button2%>
                    </a>
                    </p>
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