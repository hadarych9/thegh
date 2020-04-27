<%--
  Created by IntelliJ IDEA.
  User: ale-k
  Date: 25.04.2020
  Time: 23:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String success = "Изменение прошло успешно!";
    String result = (String) request.getAttribute("updResult");
    String header = (result != null) ? header = result : success;
    String disabled = "";
    if(header.equals(success)) disabled = "disabled";
    Long id = (Long) request.getAttribute("Id");
%>
<html>
<head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title>Изменение данных пользователя</title>
</head>
<body>
<h4><%= header%></h4>
<form action="${request.contextPath}/update" method="get">
    <input type="hidden" name="Id" value=<%= id%>>
    <button type="submit" <%= disabled%>>Ввести заново</button>
</form>
<button type="button" onclick="location.href='/'">Вернуться на главную</button>
</body>
</html>
