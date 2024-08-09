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
<h1>Посты</h1>



<c:forEach var="createPost" items="${posts}">
    <div>
        <p>${createPost.description}</p>
        <img src="${createPost.filePath}" alt="Картинка поста">
        <p>${createPost.createdAt}</p>
    </div>
</c:forEach>


</body>
</html>