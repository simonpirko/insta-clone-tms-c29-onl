<%--
  Created by IntelliJ IDEA.
  User: dimon
  Date: 13.08.2024
  Time: 13:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="_bootstrap.jsp"/>

<html>
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        .icon {
            width: 25px; /* Размер иконки */
            height: 25px;
            margin-right: 8px; /* Отступ между иконкой и текстом */
            vertical-align: middle; /* Выравнивание по центру */
        }
        .navbar-vertical {
            height: 100vh;
            position: fixed;
            top: 0;
            left: 0;
            width: 300px;
            display: flex;
            flex-direction: column;
            justify-content: flex-start;
            align-items: flex-start;
            background-color: #262626;
        }

        .navbar-brand {
            font-size: 1.5rem;
            padding-left: 1rem;
            padding-bottom: 1rem;
        }

        .nav-link {
            width: 100%;
            padding: 0.75rem 1rem;
            text-align: left;
            color: black;
        }

        .nav-link:hover {
            background-color: lightgray; /* Серый фон при наведении */
            border-radius: 20px;
        }

        .profile-icon {
            width: 25px; /* Размер иконки профиля */
            height: 25px;
            border-radius: 50%; /* Округлые углы для круглого изображения */
            object-fit: cover; /* Обрезка изображения по форме контейнера */
            margin-right: 10px; /* Отступ между иконкой и текстом */
        }

        .footer-link {
            margin-top: auto;
            width: 100%;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-light bg-light navbar-vertical">
    <a class="navbar-brand" href="https://www.instagram.com" target="_blank">Instagram</a>
    <ul class="nav flex-column w-100">
        <li class="nav-item">
            <a class="nav-link" href="#">
                <img class="icon"  src="https://cdn-icons-png.flaticon.com/512/5948/5948524.png " width="25" height="25" alt=""> Main
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">
                <svg class="icon" viewBox="0 0 48 48" xmlns="http://www.w3.org/2000/svg">
                    <path d="M 21 3 C 11.621094 3 4 10.621094 4 20 C 4 29.378906 11.621094 37 21 37 C 24.710938 37 28.140625 35.804688 30.9375 33.78125 L 44.09375 46.90625 L 46.90625 44.09375 L 33.90625 31.0625 C 36.460938 28.085938 38 24.222656 38 20 C 38 10.621094 30.378906 3 21 3 Z M 21 5 C 29.296875 5 36 11.703125 36 20 C 36 28.296875 29.296875 35 21 35 C 12.703125 35 6 28.296875 6 20 C 6 11.703125 12.703125 5 21 5 Z"></path>
                </svg> Search
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">
                <img class="icon"  src="https://img.icons8.com/external-others-iconmarket/64/external-compas-navigation-others-iconmarket-2.png" alt="external-compas-navigation-others-iconmarket-2"/> Interesting
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">
                <svg class="icon" viewBox="0 0 48 48" xmlns="http://www.w3.org/2000/svg">
                    <path d="M33,13H15c-4.963,0-9,4.037-9,9v13c0,4.963,4.037,9,9,9h18c4.963,0,9-4.037,9-9V22C42,17.037,37.963,13,33,13z"></path><path fill="#fff" d="M33,41H15c-4.418,0-8-3.582-8-8V15c0-4.418,3.582-8,8-8h18c4.418,0,8,3.582,8,8v18	C41,37.418,37.418,41,33,41z"></path><path d="M33,42H15c-4.963,0-9-4.037-9-9V15c0-4.963,4.037-9,9-9h18c4.963,0,9,4.037,9,9v18C42,37.963,37.963,42,33,42z M15,8	c-3.859,0-7,3.141-7,7v18c0,3.859,3.141,7,7,7h18c3.859,0,7-3.141,7-7V15c0-3.859-3.141-7-7-7H15z"></path><path d="M20,36.119c-0.516,0-1.03-0.136-1.5-0.406c-0.939-0.543-1.5-1.514-1.5-2.599v-9.229c0-1.085,0.561-2.057,1.501-2.599	c0.938-0.541,2.06-0.541,2.998,0.001l7.998,4.613c0.939,0.543,1.5,1.515,1.5,2.6c0,1.084-0.561,2.056-1.5,2.599l-7.998,4.613	C21.029,35.983,20.515,36.119,20,36.119z M20.004,22.883c-0.23,0-0.413,0.084-0.504,0.137c-0.15,0.087-0.5,0.345-0.5,0.866v9.229	c0,0.521,0.35,0.779,0.5,0.866s0.548,0.263,0.999,0l7.998-4.613c0.452-0.261,0.5-0.693,0.5-0.866c0-0.174-0.048-0.606-0.5-0.867	l-7.998-4.613C20.322,22.918,20.153,22.883,20.004,22.883z"></path><rect width="34" height="2" x="7" y="15"></rect><rect width="2" height="10.372" x="17.216" y="6.439" transform="rotate(-32.471 18.216 11.625)"></rect><rect width="2" height="10.519" x="29.176" y="6.303" transform="rotate(-32.471 30.174 11.562)"></rect>
                </svg> Reels
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">
                <svg class="icon" viewBox="0 0 50 50" xmlns="http://www.w3.org/2000/svg">
                <path d="M 25 6 C 13.526286 6 4 13.7136 4 23.4375 C 4 29.610949 8.0052576 35.336806 14.298828 38.4375 A 1.0001 1.0001 0 0 0 14.298828 38.439453 C 14.321498 38.450593 14.316721 38.431551 14.306641 38.462891 C 14.026044 39.334531 13.083785 41.111046 12.175781 42.433594 A 1.0001 1.0001 0 0 0 13.294922 43.955078 C 15.7364 43.202868 18.266937 41.809598 19.787109 40.328125 C 19.787109 40.328125 19.789062 40.328125 19.789062 40.328125 C 20.69854 40.522027 21.624358 40.657425 22.554688 40.748047 C 24.946035 42.758366 28.247811 43.990234 31.841797 43.990234 C 33.013255 43.990234 34.188265 43.852989 35.337891 43.585938 C 36.384344 44.315325 37.521711 44.58987 38.572266 44.947266 A 1.0001 1.0001 0 0 0 39.742188 43.466797 C 39.384102 42.897562 39.169632 42.658155 38.972656 42.199219 C 42.649396 40.192133 44.947266 36.757832 44.947266 32.994141 C 44.947266 31.954794 44.763855 30.958201 44.447266 30.011719 C 45.441687 27.986624 46 25.763454 46 23.4375 C 46 13.7136 36.473714 6 25 6 z M 25 8 C 35.582286 8 44 15.0334 44 23.4375 C 44 24.888208 43.73741 26.284543 43.273438 27.617188 C 41.009817 24.24087 36.692775 22 31.841797 22 C 24.718957 22 18.736328 26.826273 18.736328 32.994141 C 18.736328 34.980576 19.36404 36.824024 20.443359 38.416016 C 20.361743 38.399548 20.278581 38.388465 20.197266 38.371094 A 1.0001 1.0001 0 0 0 20.191406 38.369141 C 20.064341 38.342981 19.927545 38.326172 19.78125 38.326172 C 19.264833 38.326172 18.765808 38.535031 18.398438 38.892578 A 1.0001 1.0001 0 0 0 18.396484 38.892578 C 17.701332 39.571005 16.505944 40.294461 15.214844 40.947266 C 15.543019 40.304953 16.038305 39.611017 16.210938 39.074219 C 16.512716 38.134058 16.070064 37.081797 15.181641 36.644531 C 9.4592628 33.825224 6 28.744051 6 23.4375 C 6 15.0334 14.417714 8 25 8 z M 31.841797 24 C 38.068957 24 42.947266 28.140009 42.947266 32.994141 C 42.947266 35.969675 41.145727 38.76769 37.958984 40.482422 A 1.0001 1.0001 0 0 0 37.957031 40.484375 C 37.326022 40.824847 37.074008 41.499264 37.072266 42.189453 C 36.896198 42.102293 36.592201 42.04088 36.447266 41.9375 A 1.0001 1.0001 0 0 0 36.445312 41.935547 C 36.12793 41.710906 35.739477 41.583984 35.345703 41.583984 C 35.199513 41.583984 35.052625 41.601246 34.910156 41.634766 C 33.901002 41.871228 32.868502 41.990234 31.841797 41.990234 C 25.614637 41.990234 20.736328 37.848273 20.736328 32.994141 C 20.736421 28.140011 25.614637 24 31.841797 24 z M 26.703125 31.056641 A 1.976 1.976 0 1 0 26.703125 35.007812 A 1.976 1.976 0 1 0 26.703125 31.056641 z M 32.236328 31.056641 A 1.976 1.976 0 1 0 32.236328 35.007812 A 1.976 1.976 0 1 0 32.236328 31.056641 z M 37.769531 31.056641 A 1.976 1.976 0 1 0 37.769531 35.007812 A 1.976 1.976 0 1 0 37.769531 31.056641 z"></path>
            </svg> Messages
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">
                <img class="icon" src="https://img.icons8.com/material-outlined/24/like--v1.png" alt="like--v1"/> Notifications
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">
                <img class="icon" width="25" height="25" src="https://img.icons8.com/ios/50/add--v1.png" alt="add--v1"/> Make
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">
                <!-- Иконка профиля загружается из базы данных -->
                <img class="profile-icon" src="${profileImageUrl}" alt="Profile Icon" />
                Profile
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">
                <svg class="icon" viewBox="0 0 50 50" xmlns="http://www.w3.org/2000/svg">
                    <path d="M 22.205078 2 A 1.0001 1.0001 0 0 0 21.21875 2.8378906 L 20.246094 8.7929688 C 19.076509 9.1331971 17.961243 9.5922728 16.910156 10.164062 L 11.996094 6.6542969 A 1.0001 1.0001 0 0 0 10.708984 6.7597656 L 6.8183594 10.646484 A 1.0001 1.0001 0 0 0 6.7070312 11.927734 L 10.164062 16.873047 C 9.583454 17.930271 9.1142098 19.051824 8.765625 20.232422 L 2.8359375 21.21875 A 1.0001 1.0001 0 0 0 2.0019531 22.205078 L 2.0019531 27.705078 A 1.0001 1.0001 0 0 0 2.8261719 28.691406 L 8.7597656 29.742188 C 9.1064607 30.920739 9.5727226 32.043065 10.154297 33.101562 L 6.6542969 37.998047 A 1.0001 1.0001 0 0 0 6.7597656 39.285156 L 10.648438 43.175781 A 1.0001 1.0001 0 0 0 11.927734 43.289062 L 16.882812 39.820312 C 17.936999 40.39548 19.054994 40.857928 20.228516 41.201172 L 21.21875 47.164062 A 1.0001 1.0001 0 0 0 22.205078 48 L 27.705078 48 A 1.0001 1.0001 0 0 0 28.691406 47.173828 L 29.751953 41.1875 C 30.920633 40.838997 32.033372 40.369697 33.082031 39.791016 L 38.070312 43.291016 A 1.0001 1.0001 0 0 0 39.351562 43.179688 L 43.240234 39.287109 A 1.0001 1.0001 0 0 0 43.34375 37.996094 L 39.787109 33.058594 C 40.355783 32.014958 40.813915 30.908875 41.154297 29.748047 L 47.171875 28.693359 A 1.0001 1.0001 0 0 0 47.998047 27.707031 L 47.998047 22.207031 A 1.0001 1.0001 0 0 0 47.160156 21.220703 L 41.152344 20.238281 C 40.80968 19.078827 40.350281 17.974723 39.78125 16.931641 L 43.289062 11.933594 A 1.0001 1.0001 0 0 0 43.177734 10.652344 L 39.287109 6.7636719 A 1.0001 1.0001 0 0 0 37.996094 6.6601562 L 33.072266 10.201172 C 32.023186 9.6248101 30.909713 9.1579916 29.738281 8.8125 L 28.691406 2.828125 A 1.0001 1.0001 0 0 0 27.705078 2 L 22.205078 2 z M 23.056641 4 L 26.865234 4 L 27.861328 9.6855469 A 1.0001 1.0001 0 0 0 28.603516 10.484375 C 30.066026 10.848832 31.439607 11.426549 32.693359 12.185547 A 1.0001 1.0001 0 0 0 33.794922 12.142578 L 38.474609 8.7792969 L 41.167969 11.472656 L 37.835938 16.220703 A 1.0001 1.0001 0 0 0 37.796875 17.310547 C 38.548366 18.561471 39.118333 19.926379 39.482422 21.380859 A 1.0001 1.0001 0 0 0 40.291016 22.125 L 45.998047 23.058594 L 45.998047 26.867188 L 40.279297 27.871094 A 1.0001 1.0001 0 0 0 39.482422 28.617188 C 39.122545 30.069817 38.552234 31.434687 37.800781 32.685547 A 1.0001 1.0001 0 0 0 37.845703 33.785156 L 41.224609 38.474609 L 38.53125 41.169922 L 33.791016 37.84375 A 1.0001 1.0001 0 0 0 32.697266 37.808594 C 31.44975 38.567585 30.074755 39.148028 28.617188 39.517578 A 1.0001 1.0001 0 0 0 27.876953 40.3125 L 26.867188 46 L 23.052734 46 L 22.111328 40.337891 A 1.0001 1.0001 0 0 0 21.365234 39.53125 C 19.90185 39.170557 18.522094 38.59371 17.259766 37.835938 A 1.0001 1.0001 0 0 0 16.171875 37.875 L 11.46875 41.169922 L 8.7734375 38.470703 L 12.097656 33.824219 A 1.0001 1.0001 0 0 0 12.138672 32.724609 C 11.372652 31.458855 10.793319 30.079213 10.427734 28.609375 A 1.0001 1.0001 0 0 0 9.6328125 27.867188 L 4.0019531 26.867188 L 4.0019531 23.052734 L 9.6289062 22.117188 A 1.0001 1.0001 0 0 0 10.435547 21.373047 C 10.804273 19.898143 11.383325 18.518729 12.146484 17.255859 A 1.0001 1.0001 0 0 0 12.111328 16.164062 L 8.8261719 11.46875 L 11.523438 8.7734375 L 16.185547 12.105469 A 1.0001 1.0001 0 0 0 17.28125 12.148438 C 18.536908 11.394293 19.919867 10.822081 21.384766 10.462891 A 1.0001 1.0001 0 0 0 22.132812 9.6523438 L 23.056641 4 z M 25 17 C 20.593567 17 17 20.593567 17 25 C 17 29.406433 20.593567 33 25 33 C 29.406433 33 33 29.406433 33 25 C 33 20.593567 29.406433 17 25 17 z M 25 19 C 28.325553 19 31 21.674447 31 25 C 31 28.325553 28.325553 31 25 31 C 21.674447 31 19 28.325553 19 25 C 19 21.674447 21.674447 19 25 19 z"></path>
                </svg> Settings
            </a>
        </li>
    </ul>
    <div class="footer-link">
        <a class="nav-link" href="/logout">
            <img class="icon" src="https://img.icons8.com/ios/50/exit--v1.png" alt="exit--v1"/>
            Logout</a>
    </div>
</nav>

</body>
</html>
