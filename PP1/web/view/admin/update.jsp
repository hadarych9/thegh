<%@ page import="service.Active" %><%--
  Created by IntelliJ IDEA.
  User: ale-k
  Date: 25.04.2020
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setCharacterEncoding("UTF-8");
    String header = "Пожалуйста, введите новые значения в те поля, которые желаете изменить";
    Long id = (Long) request.getAttribute("id");
    String name = (String) request.getAttribute("name");
    String pass = (String) request.getAttribute("password");
    Long age = (Long) request.getAttribute("age");
    String role = Active.getInstance().getActive().getRole();
%>
<html>
<head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title>Изменение данных</title>
</head>
<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-left-align">
    <h1>ВХранилище</h1>
    <h5>Редактирование пользователя <%= name%>
    </h5>
</div>
<div class="w3-container w3-display-container w3-margin-bottom w3-padding">
    <div class="w3-container w3-display-topmiddle w3-margin-bottom w3-padding" style="width:75%;">
        <div class="w3-card-4 w3-white">
            <div class="w3-container w3-display-container w3-green">
                <button onclick="location.href='/'" class="w3-button w3-margin-right w3-display-right w3-round-large w3-hover-green w3-border w3-border-green w3-hover-border-white">✕</button>
                <h4><%= header%></h4>
            </div>
            <form method="get" class="w3-selection w3-white w3-padding">
                <label>Логин:
                    <input name="name" value="<%= name%>" class="w3-input"
                           style="width: 30%"><br/>
                </label>

                <label>Пароль:
                    <input type="password" name="pass" value="<%= pass%>" class="w3-input"
                           style="width: 30%"><br/>
                </label>

                <label>Возраст:
                    <input name="age" value="<%= age%>" class="w3-input"
                           style="width: 30%"><br/>
                </label>

                <%
                    if(role.equals("admin")){
                        out.println("Полномочия:\n" +
                                "                <p>\n" +
                                "                    <input class=\"w3-radio\" type=\"radio\" name=\"role\" value=\"admin\" checked>\n" +
                                "                    <label>Администратор</label></p>\n" +
                                "                <p>\n" +
                                "                    <input class=\"w3-radio\" type=\"radio\" name=\"role\" value=\"user\" checked>\n" +
                                "                    <label>Пользователь</label></p>");
                    }
                %>

                <button type="submit" class="w3-button w3-green w3-margin-bottom">Сохранить</button>
            </form>
        </div>
        <div class="w3-container w3-light-grey w3-right-align">
            <form action="${pageContext.request.contextPath}/" method="get">
                <button type="submit" class="w3-button w3-blue-grey w3-text-white">Вернуться на главную</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
