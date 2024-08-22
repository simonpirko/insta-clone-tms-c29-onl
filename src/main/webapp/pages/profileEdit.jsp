<%--
  Created by IntelliJ IDEA.
  User: Tanya
  Date: 19.08.2024
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ProfileEdit</title>
</head>
<body>
<jsp:include page="_bootstrap.jsp"/>
<jsp:include page="_instaHeader.jsp"/>

<div class="container">
    <div class="row min-vh-100 align-items-center">
        <div class="col-2">
        </div>
        <div class="col-10">
            <div class="row justify-content-center">
                <div class="row align-items-start">
                    <div class="col-2">
                    </div>
                    <div class="col-2">
                        <img src="data:image/png;base64,${avatar}" class="rounded-circle" alt="100" width="100">
                    </div>
                    <div class="col-2 fs-5" style="height: 50px;">
                        ${userName}
                    </div>
                    <div class="col-4 fs-5" style="height: 50px;">
                    </div>
                </div>

                <form class="col-8 align-self-center" action="/profileEdit" method="post"
                      enctype="multipart/form-data">
                    <div class="row align-items-start">
                        <%--                       <form action="/upload" method="post" enctype="multipart/form-data">--%>
                        <%--                           <input type="file" name="avatar">--%>
                        <%--                           <button>Submit</button>--%>
                        <%--                       </form>--%>
                        <div class="col-2">
                        </div>
                        <div class="col-8">
                            <div class="mb-3">
                                <%--                                <label for="formFile" class="form-label"></label>--%>
                                <input class="form-control" type="file" name="fileAvatar">
                                <%--                                <form action="/upload" method="post" enctype="multipart/form-data">--%>
                                <%--                                    <input type="file" name="avatar">--%>
                                <%--                                    <button>Submit</button>--%>
                                <%--                                </form>--%>


                            </div>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="name" class="form-label"><b>Name -></b></label>
                        <input value="${requestScope.name}" type="text" class="form-control" id="name"
                               placeholder="enter name here" autocomplete="off"
                               name="name" required>

                        <%--                        <core:if test="${name == ''}">--%>
                        <%--                            <input type="text" class="form-control" id="name"--%>
<%--                                   placeholder="enter name here" autocomplete="off"--%>
<%--                                   name="name" required>--%>
<%--                        </core:if>--%>
<%--                        <core:if test="${name != ''}">--%>
<%--                            <input type="text" src="${name}" class="form-control" id="name"--%>
<%--                                   name="name" required>--%>
<%--                        </core:if>--%>

                    </div>
                    <div class="mb-3">
                        <label for="website" class="form-label"><b>Website -></b></label>
                        <input value="${requestScope.website}" type="text" class="form-control" id="website"
                               placeholder="enter website here" autocomplete="off"
                               name="website" required>
                    </div>
                    <div class="mb-3">
                        <label for="about" class="form-label"><b>About -></b></label>
                        <input value="${requestScope.about}" type="text" class="form-control" id="about"
                               placeholder="enter about here" autocomplete="off" name="about" height="400" required>
                    </div>
                    <%--                    <div class="h-100 d-inline-block" style="width: 120px; background-color: rgba(0,0,255,.1)">Высота 100%</div>--%>
                    <div class="dropdown">
                        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1"
                                data-bs-toggle="dropdown" aria-expanded="false">
                            Gender
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                            <li><a class="dropdown-item" href="#">Мужской</a></li>
                            <li><a class="dropdown-item" href="#">Женский</a></li>
                            <li><a class="dropdown-item" href="#">Свой вариант</a></li>
                        </ul>
                    </div>
                    <div class="mb-3">
                        <label for="gender" class="form-label"><b>Gender -></b></label>
                        <input value="${requestScope.gender}" type="text" class="form-control" id="gender"
                               placeholder="enter gender here" autocomplete="off"
                               name="gender" required>
                    </div>
                    <button type="submit" class="btn btn-success w-100 mb-2 mt-3">Save profile</button>
                    <button type="button" onclick="window.location='cancelProfileEdit'" class="btn btn-secondary w-100 mb-2 mt-3" >Cancel</button>
                </form>
                <%--                <form action="/upload" method="post" enctype="multipart/form-data">--%>
                <%--                    <input type="file" name="avatar">--%>
                <%--                    <button>Submit</button>--%>
                <%--                </form>--%>


                <%--                <form class="col-6 align-self-center" action="/profileEdit" method="post">--%>
                <%--                <button style="text-align: center" type="button" onclick="window.location='profileEdit'" class="btn btn-outline-success btn-sm w-100">--%>
                <%--                    Login--%>
                <%--                </button>--%>


            </div>
        </div>
    </div>
</div>
</body>
</html>
