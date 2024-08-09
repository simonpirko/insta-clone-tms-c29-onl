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
<h1>Posts</h1>
<c:forEach var="createPost" items="${posts}">
    <div>
        <p>${post.description}</p>
        <img src="${post.filePath}" alt="Картинка поста">
        <p>${post.createdAt}</p>
    </div>
</c:forEach>
</body>
</html>