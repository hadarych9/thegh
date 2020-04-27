<%--
  Created by IntelliJ IDEA.
  User: ale-k
  Date: 25.04.2020
  Time: 7:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String header = "Результат удаления";
    String tableHeader = (String) request.getAttribute("result");
%>

<html>
<head>
    <title>Удаление пользователей</title>
</head>
<body>
<h3><%= header%></h3>
<h4><%= tableHeader%></h4>
<br />
<button type="button" onclick="location.href ='/'">Вернуться на главную</button>
</body>
</html>