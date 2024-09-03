<%@ page import="java.util.Properties" %><%--
  Created by IntelliJ IDEA.
  User: simonpirko
  Date: 30.07.24
  Time: 1:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    Properties properties = (Properties) request.getAttribute("properties");
    String next = properties.getProperty("index.next");
    String previous = properties.getProperty("index.previous");
    String button = properties.getProperty("index.button");
%>
<html>
<head>
    <title>Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">

</head>
<body>
<jsp:include page="_header.jsp"/>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-5 mt-3 justify-content-center">

            <ul>
                <c:forEach items="${page.getItemsForPageList()}" var="item">
                    <div class="card mt-5" style="width: 30rem;">
                        <p style="margin-left: 20px">
                            <a href="/account/profile?username=${item.getAccount().getUsername()}"
                               class="link-body-emphasis link-offset-2 link-underline-opacity-25 link-underline-opacity-75-hover fs-5 fw-bold font-monospace">${item.getAccount().getUsername()}
                            </a>
                        </p>

                        <a href="/account/viewpost?id=${item.getId()}">
                            <img src="data:image/jpg;base64,${item.getFilePathPhoto()}" width="100%"/>
                        </a>

                        <div class="card-body">
                            <p class="card-text">${item.getDescription()}</p>
                        </div>
                    </div>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-4 mt-4 justify-content-center">
            <nav aria-label="...">
                <ul class="pagination">

                    <li class="page-item ${page.hasPreviousPage() ? "" : "disabled"}">
                        <a class="page-link" href="/page?page=${page.getPreviousPage()}" tabindex="-1"><%=previous%></a>
                    </li>

                    <c:forEach begin="${page.getMinRange()}" end="${page.getMaxRange()}" var="i">
                        <c:choose>
                            <c:when test="${page.getPageNumber() eq i}">

                                <li class="page-item active" aria-current="page">
                                    <a class="page-link" href="/page?page=${i}">${i}</a>
                                </li>

                            </c:when>

                            <c:otherwise>
                                <li class="page-item"><a class="page-link" href="/page?page=${i}">${i}</a></li>

                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                    <li class="page-item ${page.hasNextPage() ? "" : "disabled"}">
                        <a class="page-link" href="/page?page=${page.getNextPage()}"><%=next%></a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</div>


<form name="pageForm" id="pageForm" method="POST">
    <input id="postsPerPage" name="postsPerPage" type="hidden">
    <input id="page" name="page" type="hidden" value="${page.getPageNumber()}">
    <div class="dropdown">
        <div class="row justify-content-center">
            <div class="col-6 mt-6 justify-content-center">
                <button class="btn btn-secondary btn-sm dropdown-toggle" type="button" data-bs-toggle="dropdown"
                        aria-expanded="false">
                    <%=button%>
                </button>
                <ul class="dropdown-menu">
                    <li onclick="document.getElementById('postsPerPage').value = '2'; document.getElementById('pageForm').submit()">
                        2
                    </li>
                    <li onclick="document.getElementById('postsPerPage').value = '5'; document.getElementById('pageForm').submit()">
                        5
                    </li>
                    <li onclick="document.getElementById('postsPerPage').value = '10'; document.getElementById('pageForm').submit()">
                        10
                    </li>
                </ul>
            </div>
        </div>
    </div>
</form>


</body>
</html>