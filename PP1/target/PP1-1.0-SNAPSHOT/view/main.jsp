<%@ page import="model.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: ale-k
  Date: 05.04.2020
  Time: 2:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String tableHeader = "База данных пользователей";
    List<User> users = (List<User>) request.getAttribute("userData");
    //SUCCESS GREEN
    String color = "red";
    Long check = (Long) request.getAttribute("check");
    if (check != null) color = "green";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title>Главная</title>
</head>
<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-left-align">
    <h1>ВХранилище</h1>
    <h5>Главная страница</h5>
</div>


<div class="w3-row w3-container w3-center w3-margin-bottom w3-padding">
    <div class="w3-col m2">
        <div class="w3-container"></div>
    </div>
    <div class="w3-col m8">
        <%
            if (request.getAttribute("Result") != null) {
                out.println("<div class=\"w3-panel w3-" + color + " w3-display-container w3-card-4 w3-round\">\n" +
                        "   <span onclick=\"this.parentElement.style.display='none'\"\n" +
                        "   class=\"w3-button w3-margin-right w3-display-right w3-round-large w3-hover-" + color + " w3-border w3-border-" + color + " w3-hover-border-white\">✕</span>\n" +
                        "   <h5>" + request.getAttribute("Result") + "</h5>\n" +
                        "</div>");
            }
        %>
        <div class="w3-card-4 w3-white" style="width:100%">
            <div class="w3-container w3-green w3-left-align w3-text-white">
                <h4><%= tableHeader%>
                </h4>
            </div>

            <table class="w3-table w3-centered w3-bordered w3-hoverable">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Логин</th>
                    <th>Возраст</th>
                    <th>Редактирование</th>
                    <th>Удаление</th>
                </tr>
                </thead>
                <tbody>
                <jsp:useBean id="userData" scope="request" type="java.util.List"/>
                <c:forEach items="${userData}" var="user">
                    <tr class="w3-hover-pale-green">
                        <td> ${user.id} </td>
                        <td> ${user.name} </td>
                        <td> ${user.age} </td>
                        <td>
                            <form action="${request.contextPath}/update" method="post">
                                <input type="hidden" name="Id" value=${user.id}>
                                <button type="submit" class="w3-button w3-green w3-text-white">Изменить</button>
                            </form>
                        </td>
                        <td>
                            <form action="${request.contextPath}/delete" method="get">
                                <input type="hidden" name="Id" value=${user.id}>
                                <button type="submit" class="w3-button w3-green w3-text-white">Удалить</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <br/>
            <form action="${request.contextPath}/add" method="post">
                <button type="submit" class="w3-button w3-padding-large w3-green w3-margin-bottom w3-text-white">
                    Добавить пользователя
                </button>
            </form>
        </div>
        <div class="w3-container w3-right-align w3-light-grey">
            <form action="${request.contextPath}/" method="post">
                <input type="hidden" name="Id" value=${user.id}>
                <button type="submit" class="w3-button w3-blue-grey w3-text-white">Очистить таблицу</button>
            </form>
        </div>
    </div>
    <div class="w3-col m2"></div>
</div>
<div class="w3-col m2">
    <div class="w3-container"></div>
</div>
</div>
</body>
</html>
