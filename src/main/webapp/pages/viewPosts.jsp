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
    <form>
        <div class="row align-items-center justify-content-center">

            <div class="card" style="width: 18rem;">

                <c:forEach var="post" items="${posts}">
                    <div class="card-body">
                        <h5 class="card-title">Пост</h5>
                        <p>${post.description}</p>
                        <img src="${post.filePath}" class="card-img-top" alt="Картинка поста">
                        <p>${post.createdAt}</p>
                    </div>
                    <div >

                        <a href="#" class="btn btn-primary">Лайк</a>
                    </div>
                </c:forEach>

            </div>

        </div>
    </form>
</div>

</body>
</html>