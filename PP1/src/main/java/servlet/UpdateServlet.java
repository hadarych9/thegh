package servlet;

import model.User;
import service.UserJDBCService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "update", urlPatterns = "/update")
public class UpdateServlet extends HttpServlet {

    private Long id;
    private UserJDBCService service = UserJDBCService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        id = Long.parseLong(req.getParameter("Id"));
        User user = service.getById(id);
        req.setAttribute("id", id);
        req.setAttribute("name", user.getName());
        req.setAttribute("age", user.getAge());
        req.getRequestDispatcher("view/update.jsp").forward(req, resp);
        resp.sendRedirect("update.jsp");
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
        Long check = 1L;
        try {
            if (!req.getParameter("age").equals("") & req.getParameter("age") != null) {
                age = Long.parseLong(req.getParameter("age"));
            }
        } catch (NumberFormatException e){
            age = user.getAge();
            result = "Пожалуйста, введите возраст в виде цифры";
            check = null;
        }
        if(name.equals("") & password.equals("") & age == null) {
            if(result.equals(success)) result = "Изменения не внесены";
            check = null;
        } else if(!name.equals(name.replaceAll("[^\\da-zA-Zа-яёА-ЯЁ]", ""))){
            name = user.getName();
            if(result.equals(success)) result = "Для ввода логина необходимо использовать только буквы или цифры";
            check = null;
        }
        if (!service.doesClientNotExist(name)) {
                name = user.getName();
                if(result.equals(success)) result = "Такой пользователь уже существует";
                check = null;
            } else if(name.equals("")) {
                name = user.getName();
            }
        if(password.equals("")) password = user.getPassword();
        if(age == null) age = user.getAge();
        service.updateUser(new User(id, name, password, age));
        req.setAttribute("Result", result);
        req.setAttribute("check", check);
        req.getRequestDispatcher("/").forward(req, resp);
    }
}
