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

<div class="container">
    <div class="container text-center">
        <div class="row align-items-start">
            <div class="col">

            </div>
            <div class="col-4 mt-5 ">
                <form action="createPost" method="post" enctype="multipart/form-data">
                    <div class="mb-3">
                        <label for="file">Загрудить файл:</label>
                        <input type="file" id="file" name="file" class="form-control" required>

                    </div>
                    <div class="mb-3">
                        <label for="description">Описание:</label>
                        <input type="text" id="description" class="form-control" name="description" required>
                    </div>


                    <button type="submit" class="btn btn-primary">Создать пост</button>


                </form>

            </div>
            <div class="col">



            </div>
        </div>
    </div>
</div>
</body>
</html>