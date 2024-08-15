<%--
  Created by IntelliJ IDEA.
  User: iamal
  Date: 01.08.2024
  Time: 22:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="_bootstrap.jsp"/>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Navbar</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="/">Home</a>
                </li>
                <core:if test="${sessionScope.account == null}">
                    <li class="nav-item">
                        <a class="nav-link" href="/login">Authorization</a>
                    </li>
                </core:if>
                <core:if test="${sessionScope.account != null}">
                    <li class="nav-item">
                        <a class="nav-link" href="/logout">Logout</a>
                    </li>
                </core:if>
                <li class="nav-item">
                    <core:if test="${sessionScope.account == null}">
                        <a class="nav-link disabled" aria-disabled="true">Hello, Guest</a>
                    </core:if>
                    <core:if test="${sessionScope.account != null}">
                        <a class="nav-link disabled" aria-disabled="true">Hello, ${sessionScope.account.username}</a>
                    </core:if>
                </li>
            </ul>
        </div>
    </div>
</nav>
