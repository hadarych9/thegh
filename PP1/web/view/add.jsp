<%@ page import="servlet.LoginServlet" %>
<%@ page import="service.Active" %><%--
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
    Long check = (Long) request.getAttribute("check");
    String color = "red";
    if(check != null) color = "green";

    String role = null;
    if(Active.getInstance().getActive() != null) role = Active.getInstance().getActive().getRole();
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
<div class="w3-container w3-display-container w3-margin-bottom w3-padding">
    <div class="w3-container w3-display-topmiddle w3-margin-bottom w3-padding" style="width:75%;">
        <%
            if (request.getAttribute("Result") != null) {
                out.println("<div class=\"w3-panel w3-" + color + " w3-display-container w3-card-4 w3-round\">\n" +
                        "   <span onclick=\"this.parentElement.style.display='none'\"\n" +
                        "   class=\"w3-button w3-margin-right w3-display-right w3-round-large w3-hover-" + color + " w3-border w3-border-" + color + " w3-hover-border-white\">✕</span>\n" +
                        "   <h5>" + request.getAttribute("Result") + "</h5>\n" +
                        "</div>");
            }
        %>
        <div class="w3-card-4 w3-white">
            <div class="w3-container w3-display-container w3-green">
                <button onclick="location.href='/'" class="w3-button w3-margin-right w3-display-right w3-round-large w3-hover-green w3-border w3-border-green w3-hover-border-white">✕</button>
                <h4><%= header%></h4>
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

                <%
                    if(role != null && role.equals("admin")){
                        out.println("Полномочия:\n" +
                                "                <p>\n" +
                                "                    <input class=\"w3-radio\" type=\"radio\" name=\"role\" value=\"user\" checked>\n" +
                                "                    <label>Пользователь</label></p>\n" +
                                "                <p>\n" +
                                "                    <input class=\"w3-radio\" type=\"radio\" name=\"role\" value=\"admin\" checked>\n" +
                                "                    <label>Администратор</label></p>");
                    }
                %>
                <button type="submit" class="w3-button w3-green w3-margin-bottom">Сохранить</button>
            </form>
        </div>
        <%
            if(Active.getInstance().getActive() != null){
                out.println("<div class=\"w3-container w3-right-align\">\n" +
                        "                <button onclick=\"location.href='/'\" class=\"w3-button w3-blue-grey w3-text-white\">Вернуться на главную</button>\n" +
                        "        </div>");
            }
        %>
    </div>
</div>
</body>
</html>
