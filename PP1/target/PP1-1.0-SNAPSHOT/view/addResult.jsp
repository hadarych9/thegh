<%--
  Created by IntelliJ IDEA.
  User: ale-k
  Date: 25.04.2020
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String success = "Добавление прошло успешно!";
    String result = (String) request.getAttribute("addResult");
    String header = (result != null) ? header = result : success;
    String disabled = "";
    if(header.equals(success)) disabled = "disabled";
%>
<html>
<head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title>Добавление пользователя</title>
</head>
<body>
    <h4><%= header%></h4>
    <button type="button" onclick="location.href='add'" <%= disabled%>>Ввести заново</button>
    <button type="button" onclick="location.href='/'">Вернуться на главную</button>
</body>
</html>
