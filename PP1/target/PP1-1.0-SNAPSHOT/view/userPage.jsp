<%@ page import="model.User" %>
<%@ page import="service.Active" %>
<%@ page import="service.Service" %><%--
  Created by IntelliJ IDEA.
  User: ale-k
  Date: 05.04.2020
  Time: 2:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Long result = request.getParameter("Id") == null ? null : Long.parseLong(request.getParameter("Id"));
    System.out.println(result);
    User user;
    if (result != null) user = Service.getInstance().getById(result);
    else user = Active.getInstance().getActive();

    Long id = null;
    String name = null;
    Long age = null;
    if (user != null) {
        id = user.getId();
        name = user.getName();
        age = user.getAge();
    }
    //SUCCESS GREEN
    //String color = "red";
    //Long check = (Long) request.getAttribute("check");
    //if (check != null) color = "green";
    String tableHeader = "Данные пользователя " + name;
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
    <h5>Страница пользователя <%= name%>
    </h5>
</div>


<div class="w3-display-container w3-center w3-margin-bottom w3-padding">
    <div class="w3-container w3-center w3-display-topmiddle w3-margin-bottom w3-padding" style="width:75%;">
        <%--<%
            if (request.getAttribute("Result") != null) {
                out.println("<div class=\"w3-panel w3-" + color + " w3-display-container w3-card-4 w3-round\">\n" +
                        "   <span onclick=\"this.parentElement.style.display='none'\"\n" +
                        "   class=\"w3-button w3-margin-right w3-display-right w3-round-large w3-hover-" + color + " w3-border w3-border-" + color + " w3-hover-border-white\">✕</span>\n" +
                        "   <h5>" + request.getAttribute("Result") + "</h5>\n" +
                        "</div>");
            }
        %>--%>
        <div class="w3-card-4 w3-white" style="width:100%">
            <div class="w3-container w3-left-align w3-display-container w3-green">
                <%
                    if (Active.getInstance().getActive().getRole().equals("admin")) {
                        out.println("<button onclick=\"location.href='/'\" class=\"w3-button w3-margin-right w3-display-right w3-round-large w3-hover-green w3-border w3-border-green w3-hover-border-white\">✕</button>");
                    }
                %>
                <h4><%= tableHeader%></h4>
            </div>

            <table class="w3-table w3-centered w3-bordered w3-hoverable">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Логин</th>
                    <th>Возраст</th>
                </tr>
                </thead>
                <tbody>
                <tr class="w3-hover-pale-green">
                    <td style="width: 30%"><%= id%>
                    </td>
                    <td style="width: 30%"><%= name%>
                    </td>
                    <td style="width: 30%"><%= age%>
                    </td>
                </tr>
                </tbody>
            </table>
            <br/>
            <%
                if (Active.getInstance().getActive().getRole().equals("admin")) {
                    out.println("<button onclick=\"location.href='/'\" class=\"w3-button w3-padding-large w3-green w3-margin-bottom w3-text-white\" style=\"display:inline\">Вернуться на главную</button>\n");
                }
            %>
            <%
                if (Active.getInstance().getActive().getRole().equals("user")) {
                    out.println("<form action=\"" + request.getContextPath() + "/login\" method=\"post\" style=\"display:inline\">\n" +
                            "                <input type=\"hidden\" name=\"name\" value=\"log\">\n" +
                            "                <input type=\"hidden\" name=\"pass\" value=\"off\">\n" +
                            "                <button type=\"submit\" class=\"w3-button w3-padding-large w3-green w3-margin-bottom w3-text-white\">Выйти</button>\n" +
                            "            </form>");
                }
            %>

        </div>
    </div>
</div>
</body>
</html>
