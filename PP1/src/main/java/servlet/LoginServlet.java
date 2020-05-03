package servlet;

import model.User;
import service.Active;
import service.Service;
import service.UserDaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "login", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("view/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Service service = Service.getInstance();
        req.setCharacterEncoding("UTF-8");

        if(req.getParameter("forgot") != null && req.getParameter("forgot").equals("true")){
            req.setAttribute("Result", "Весьма жаль");
            req.getRequestDispatcher("view/login.jsp").forward(req, resp);
            return;
        }

        String result;
        Long check = null;
        String name = req.getParameter("name");
        String password = req.getParameter("pass");
        User user = service.getByName(name);

        if(name.equals("") | password.equals("")) {
            result = "Пожалуйста, заполните все поля";
        } else if(!name.equals(name.replaceAll("[^\\da-zA-Zа-яёА-ЯЁ]", ""))){
            result = "Для ввода логина необходимо использовать только буквы или цифры";
        } else if(name.equals("log") & password.equals("off")){
            result = "Вы вышли из системы";
            Active.getInstance().setActive(null);
        } else if(user == null && service.doesUserNotExist(name)) {
            result = "Такого пользователя не существует";
        } else if(!password.equals(user.getPassword())){
            result = "Пароль введен неверно";
        } else {
            result = "Вы успешно авторизовались";
            check = 1L;
            Active.getInstance().setActive(user);
        }

        req.setAttribute("check", check);
        req.setAttribute("Result", result);

        if(check != null && Active.getInstance().getActive() != null) {
            if(Active.getInstance().getActive().getRole().equals("admin")){
                req.getRequestDispatcher("/admin").forward(req, resp);
            } else {
                //req.getRequestDispatcher("/user").forward(req, resp);
                resp.sendRedirect("/user");
            }
            return;
        }
        /*if(check != null && Active.getInstance().getActive() != null){
            req.getRequestDispatcher("/").forward(req, resp);
            return;
        }*/
        req.getRequestDispatcher("view/login.jsp").forward(req, resp);
    }
}
