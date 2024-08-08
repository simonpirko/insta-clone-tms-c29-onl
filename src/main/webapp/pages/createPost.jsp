<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--
  Created by IntelliJ IDEA.
  User: yakubchyk
  Date: 08.08.24
  Time: 18:21
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <title>Create Post</title>
</head>
<body>
<jsp:include page="_header.jsp"/>
<jsp:include page="_bootstrap.jsp"/>

<form action="post" method="post" enctype="multipart/form-data">
    <label for="description">Description:</label>
    <input type="text" id="description" name="description" required>
    <label for="file">Upload File:</label>
    <input type="file" id="file" name="file" required>
    <button type="submit">Create Post</button>
</form>
</body>
</html>