<%@ page import="java.util.Properties" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    Properties properties = (Properties) request.getAttribute("properties");
    String brand = properties.getProperty("header.brand");
    String home = properties.getProperty("header.home");
    String profile = properties.getProperty("header.profile");
    String createPost = properties.getProperty("header.createPost");
    String language = properties.getProperty("header.language");
    String register = properties.getProperty("header.register");
    String login = properties.getProperty("header.login");
    String logout = properties.getProperty("header.logout");
%>

<html>
<head>
    <title>Header</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-lg bg-dark justify-content-center sticky-top" data-bs-theme="dark">
    <div class="container justify-content-center">
        <div class="container-fluid d-flex align-items-center">
            <a class="navbar-brand fst-italic fw-bolder fs-4" href="/"><%=brand%>
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">

                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <c:if test="${sessionScope.account != null}">
                        <a class="nav-link" aria-current="page" href="/"><%=home%>
                        </a>
                    </c:if>
                </ul>

                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <c:if test="${sessionScope.account != null}">
                        <a class="nav-link" aria-current="page" href="/account/profile?username=${sessionScope.account.getUsername()}"><%=profile%>
                        </a>
                    </c:if>
                </ul>

                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <c:if test="${sessionScope.account != null}">
                        <a class="nav-link" aria-current="page" href="/create-post"><%=createPost%>
                        </a>
                    </c:if>
                </ul>

                <ul class="nav justify-content-end">
                    <c:if test="${sessionScope.account == null}">
                        <li class="nav-item">
                            <a class="nav-link" aria-current="page" href="/localization"><%=language%>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" aria-current="page" href="/register"><%=register%>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" aria-current="page" href="/login"><%=login%>
                            </a>
                        </li>
                    </c:if>

                    <c:if test="${sessionScope.account != null}">
                        <li class="nav-item">
                            <a class="nav-link" aria-current="page" href="/logout"><%=logout%>
                            </a>
                        </li>
                    </c:if>

                </ul>
            </div>
        </div>
    </div>
</nav>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
        crossorigin="anonymous"></script>
</body>
</html>