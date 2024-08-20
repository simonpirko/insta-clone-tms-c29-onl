<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: yakubchyk
  Date: 08.08.24
  Time: 18:20
  To change this template use File | Settings | File Templates.
--%>

<!DOCTYPE html>
<html>
<head>
    <title>Лента постов</title>
</head>

<body>
<jsp:include page="_header.jsp"/>
<jsp:include page="_bootstrap.jsp"/>


<div class="container text-center">
    <div class="row">
        <div class="col">
            Column
        </div>
        <div class="col-6">


            <c:forEach var="post" items="${posts}">
                <div class="card-body">
                    <h5 class="card-title">Пост</h5>

                    <img width="1080" src="data:image/png;base64,${post.filePath}" class="card-img-top"
                         alt="Картинка поста">

                    <p>${post.description}</p>

                    <p>${post.createdAt}</p>
                </div>

            </c:forEach>
        </div>
        <div class="col">
            Column
        </div>
    </div>
</div>


</body>
</html>
