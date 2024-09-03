<%@ page import="java.util.Properties" %><%--
  Created by IntelliJ IDEA.
  User: taras
  Date: 29.08.2024
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    Properties properties = (Properties) request.getAttribute("properties");
    String edit = properties.getProperty("profile.edit");
    String followers = properties.getProperty("profile.Followers");
    String following = properties.getProperty("profile.Following");
    String Delete = properties.getProperty("profile.Delete");
%>
<html>
<head>
    <title>Profile</title>
    <style>
        .avatar-border {
            border-style: solid;
            border-color: coral;
        }
    </style>
</head>
<body>
<jsp:include page="_bootstrap.jsp"/>
<jsp:include page="_header.jsp"/>

<div class="container-fluid bg-3 text-center border-bottom">
    <br>
    <div class="row">
        <div class="col-md d-flex align-items-center justify-content-center">
            <c:choose>
                <c:when test="${viewedUser.getStories().size() > 0}">
                    <a class="avatar-border" href="/account?username=${viewedAccount.getUsername()}">
                        <img src="data:image/jpg;base64,${viewedUser.getFilePathPhoto()}" width="150" height="150" alt="fsd"/>
                    </a>
                </c:when>
                <c:otherwise>
                    <img src="data:image/jpg;base64,${viewedUser.getFilePathPhoto()}" width="150" height="150" alt="fsd"/>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="col-md text-md-start">
            <h3>${viewedAccount.getName()} ${viewedAccount.getSurname()}</h3>
            <h4>@${viewedAccount.getUsername()}</h4>

            <c:if test="${viewedAccount.getUsername() == account.getUsername()}">
                <a href="/edit-profile" class="btn btn-sm btn-danger" role="button" aria-pressed="true"><%=edit%>
                </a>
            </c:if>
        </div>
        <div class="col-md">
            <div class="container">
                <div class="row">
                    <a class="col" href="#">${followersCount} <%=followers%>
                    </a>
                    <a class="col" href="#">${followingCount} <%=following%>
                    </a>
                </div>
                <c:if test="${viewedAccount.getUsername() != account.getUsername()}">
                    <br>
                    <div class="row">
                        <c:if test="${isAlreadyFollowed == true}">
                            <form action="/unfollow" method="post">
                                <input type="hidden" value="${viewedAccount.getUsername()}" name="child">

                                <input type="submit" value="Unfollow">
                            </form>
                        </c:if>

                        <c:if test="${isAlreadyFollowed == false}">
                            <form action="/follow" method="post">
                                <input type="hidden" value="${viewedAccount.getUsername()}" name="child">

                                <input type="submit" value="Follow">
                            </form>
                        </c:if>

                    </div>
                </c:if>
            </div>

        </div>
    </div>
    <br>
</div>

<div class="border-bottom">
    <div class="container-fluid text-center">
        <div class="row">
            <c:forEach items="${accountPosts}" var="item">
                <div class="col-sm-4">
                    <div class="card" style="width: 100%">
                        <a href="#">
                            <img class="card-img-top" src="data:image/jpg;base64,${item.getFilePathPhoto()}"
                                 style="width:100%; height: auto;  aspect-ratio: 16/9" alt="Image"/>
                        </a>

                        <div class="card-body">
                            <p class="card-text">${item.getDescription()}</p>
                        </div>

                        <br>

                        <c:if test="${viewedAccount.getUsername() == account.getUsername()}">
                            <div class="d-flex justify-content-end">
                                <form action="/account/remove_post" method="post">
                                    <input type="hidden" name="postId" value="${item.getId()}">
                                    <button class="btn btn-primary btn-sm" type="submit">
                                        <%=Delete%>
                                    </button>
                                </form>
                            </div>
                        </c:if>

                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

</body>
</html>
