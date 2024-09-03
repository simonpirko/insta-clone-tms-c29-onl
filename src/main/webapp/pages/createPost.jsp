<%@ page import="java.util.Properties" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--
  Created by IntelliJ IDEA.
  User: yakubchyk
  Date: 08.08.24
  Time: 18:21
  To change this template use File | Settings | File Templates.
--%>
<%
    Properties properties = (Properties) request.getAttribute("properties");
    String text = properties.getProperty("createPost.text");
    String description = properties.getProperty("createPost.description");
    String button = properties.getProperty("createPost.button");
%>
<html>
<head>
    <title>Create Post</title>
</head>
<body>
<jsp:include page="_header.jsp"/>

<div class="row justify-content-center">
    <div class="col-4">
        <div class="container">
            <form action="/create-post" method="post" enctype="multipart/form-data">
                <p class="fs-6"></p>

                <h4>
                    <div class="text-center mb-3" style="width: calc(100% - 0px);"><%=text%>
                    </div>
                </h4>

                <div class="input-group flex-nowrap mb-3">
                    <input name="photo" type="file" class="form-control" placeholder="link" aria-label="link"
                           aria-describedby="addon-wrapping" required>
                </div>

                <div class="form-floating">
                    <textarea name="description" class="form-control" placeholder="Leave a description here"
                              id="floatingTextarea" maxlength="2200" required></textarea>
                    <label for="floatingTextarea"><%=description%>
                    </label>
                </div>

                <p class="fs-6"></p>

                <div class="d-grid gap-2 col-6 mx-auto">
                    <button class="btn btn-primary" type="submit"><%=button%>
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>