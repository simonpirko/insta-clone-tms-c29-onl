<%@ page import="java.util.Properties" %><%--
  Created by IntelliJ IDEA.
  User: taras
  Date: 03.09.2024
  Time: 12:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Properties properties = (Properties) request.getAttribute("properties");
    String language = properties.getProperty("locale.language");
    String languageEn = properties.getProperty("locale.languageEn");
    String languageRu = properties.getProperty("locale.languageRu");
    String button = properties.getProperty("locale.button");
%>
<html>
<head>
    <title>Locale</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
<jsp:include page="_header.jsp"/>

<div class="container">
    <div class="row justify-content-center">
        <form class="mt-5 w-25" action="/localization" method="post">
            <select class="form-select form-select-lg mb-3" name="lang" aria-label="Large select example">
                <option selected><%=language%></option>
                <option value="en"><%=languageEn%></option>
                <option value="ru"><%=languageRu%></option>
            </select>
            <div class="d-grid gap-2 col-6 mx-auto">
                <button class="btn btn-dark" type="submit"><%=button%></button>
            </div>
        </form>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
</body>
</html>
