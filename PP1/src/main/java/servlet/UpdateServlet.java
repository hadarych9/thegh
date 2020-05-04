package servlet;

import model.User;
import service.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "update", urlPatterns = "/admin/update")
public class UpdateServlet extends HttpServlet {

    private Long id;
    private Service service = Service.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        id = Long.parseLong(req.getParameter("Id"));
        User user = service.getById(id);
        if(id == null | user == null){
            req.setAttribute("Result", "Такого пользователя не существует");
            req.getRequestDispatcher("/admin").forward(req, resp);
            return;
        }
        req.setAttribute("id", id);
        req.setAttribute("name", user.getName());
        req.setAttribute("password", user.getPassword());
        req.setAttribute("age", user.getAge());
        req.setAttribute("role", user.getRole());
        req.getRequestDispatcher("/view/admin/update.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String success = "Изменения внесены!";
        String result = success;
        req.setCharacterEncoding("UTF-8");
        User user = service.getById(id);
        req.setAttribute("Id", id);
        String name = req.getParameter("name");
        String password = req.getParameter("pass");
        Long age = null;
        String role = req.getParameter("role");
        Long check = 1L;
        try {
            if (req.getParameter("age") != null && !req.getParameter("age").equals("")) {
                age = Long.parseLong(req.getParameter("age"));
            }
        } catch (NumberFormatException e){
            age = user.getAge();
            result = "Пожалуйста, введите возраст в виде цифры";
            check = null;
        }
        if((name.equals("") | name.equals(user.getName()))
                & (password.equals("") | password.equals(user.getPassword()))
                & (age == null | age == user.getAge()) & (role.equals("") | role.equals(user.getRole()))) {
            if(result.equals(success)) result = "Изменения не внесены";
            check = null;
        } else if(!name.equals(name.replaceAll("[^\\da-zA-Zа-яёА-ЯЁ]", ""))){
            name = user.getName();
            if(result.equals(success)) result = "Для ввода логина необходимо использовать только буквы или цифры";
            check = null;
        }
        if (!service.doesUserNotExist(name) & !name.equals(user.getName())) {
                name = user.getName();
                if(result.equals(success)) result = "Такой пользователь уже существует";
                check = null;
            } else if(name.equals("")) {
                name = user.getName();
            }
        if(password.equals("")) password = user.getPassword();
        if(age == null) age = user.getAge();
        if(role.equals("")) role = user.getRole();
        service.updateUser(new User(id, name, password, age, role));
        req.setAttribute("Result", result);
        req.setAttribute("check", check);
        req.getRequestDispatcher("/admin").forward(req, resp);
    }
}
