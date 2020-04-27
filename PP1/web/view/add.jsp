<%--
  Created by IntelliJ IDEA.
  User: ale-k
  Date: 05.04.2020
  Time: 4:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String header = "Пожалуйста, придумайте и введите логин и пароль, а также ваш возраст";
    request.setCharacterEncoding("UTF-8");
%>
<html>
<head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title>Добавление пользователя</title>
</head>
<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-left-align">
    <h1>ВХранилище</h1>
    <h5>Добавление пользователя</h5>
</div>
<div class="w3-row w3-container w3-margin-bottom w3-padding">
    <div class="w3-col m2">
        <div class="w3-container"></div>
    </div>
    <div class="w3-col m8">
        <div class="w3-card-4">
            <div class="w3-container w3-green w3-left-align">
                <h4><%= header%>
                </h4>
            </div>
            <form method="get" class="w3-selection w3-white w3-padding">
                <label>Логин:
                    <input type="text" name="name" class="w3-input" style="width: 30%"><br/>
                </label>

                <label>Пароль:
                    <input type="password" name="pass" class="w3-input" style="width: 30%"><br/>
                </label>

                <label>Возраст:
                    <input type="text" name="age" class="w3-input" style="width: 30%"><br/>
                </label>
                <button type="submit" class="w3-button w3-green w3-margin-bottom">Сохранить</button>
            </form>
            <div class="w3-container w3-right-align">
                <form action="${request.contextPath}/" method="get">
                    <input type="hidden" name="Id" value=${user.id}>
                    <button type="submit" class="w3-button w3-blue-grey w3-text-white">Вернуться на главную</button>
                </form>
            </div>
        </div>
    </div>
    <div class="w3-col m2">
        <div class="w3-container"></div>
    </div>
</div>
</body>
</html>
