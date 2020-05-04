<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="servlet.LoginServlet" %>
<%@ page import="service.Active" %><%--
  Created by IntelliJ IDEA.
  User: ale-k
  Date: 05.04.2020
  Time: 2:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String tableHeader = "База данных пользователей";
    User admin = Active.getInstance().getActive();
    //SUCCESS GREEN
    String color = "red";
    Long check = (Long) request.getAttribute("check");
    if (check != null) color = "green";
    String adminName = null;
    if(admin != null) adminName = admin.getName();
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
    <h5>Страница администратора <%= adminName%></h5>
</div>


<div class="w3-container w3-display-container w3-center w3-margin-bottom w3-padding">
    <div class="w3-container w3-center w3-display-topmiddle w3-margin-bottom w3-padding" style="width:75%;">
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
                    <th>Уровень допуска</th>
                    <th>Просмотр</th>
                    <th>Редактирование</th>
                    <th>Удаление</th>
                </tr>
                </thead>
                <tbody>
                <jsp:useBean id="userData" scope="request" type="java.util.List"/>
                <c:forEach items="${userData}" var="user">
                    <tr class="w3-hover-pale-green">
                        <td style="width: 10%"> ${user.id} </td>
                        <td style="width: 15%"> ${user.name} </td>
                        <td style="width: 15%"> ${user.age} </td>
                        <td style="width: 15%"> ${user.role}</td>
                        <td style="width: 15%">
                            <form action="${pageContext.request.contextPath}/user" method="get">
                                <input type="hidden" name="Id" value=${user.id}>
                                <button type="submit" class="w3-button w3-green w3-text-white">⌕</button>
                            </form>
                        </td>
                        <td style="width: 15%">
                            <form action="${pageContext.request.contextPath}/admin/update" method="post">
                                <input type="hidden" name="Id" value=${user.id}>
                                <button type="submit" class="w3-button w3-green w3-text-white">Изменить</button>
                            </form>
                        </td>
                        <td style="width: 15%">
                            <form action="${pageContext.request.contextPath}/admin/delete" method="get">
                                <input type="hidden" name="Id" value=${user.id}>
                                <button type="submit" class="w3-button w3-green w3-text-white">Удалить</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <br/>
            <form action="${pageContext.request.contextPath}/add" method="post">
                <button type="submit" class="w3-button w3-padding-large w3-green w3-margin-bottom w3-text-white">Добавить пользователя</button>
            </form>
        </div>
        <div class="w3-container w3-right-align w3-light-grey">
            <form action="${pageContext.request.contextPath}/admin" method="post" style="display:inline">
                <input type="hidden" name="drop" value="true">
                <button type="submit" class="w3-button w3-blue-grey w3-text-white">Очистить таблицу</button>
            </form>
            <form action="${pageContext.request.contextPath}/user" method="post" style="display:inline">
                <input type="hidden" name="name" value="log">
                <input type="hidden" name="pass" value="off">
                <button type="submit" class="w3-button w3-blue-grey w3-text-white">Выйти</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
