package servlet;

import model.User;
import service.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "main", urlPatterns = "/")
public class UserServlet extends HttpServlet {

    private String cresult;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Service service = Service.getInstance();
        service.createTable();

        String result = (String) req.getAttribute("Result");
        Long check = (Long) req.getAttribute("check");

        if(cresult != null){
            result = cresult;
            check = 1L;
            cresult = null;
        }

        List<User> users = service.getAllUsers();
        for(User user : users){
            Long id = user.getId();
            req.setAttribute("id", id);

            String name = user.getName();
            req.setAttribute("name", name);

            Long age = user.getAge();
            req.setAttribute("age", age);
        }

        req.setAttribute("userData", users);
        req.setAttribute("Result", result);
        req.setAttribute("check", check);

        req.getRequestDispatcher("view/main.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Service service = Service.getInstance();
        service.dropTable();
        cresult = "Очистка базы успешно завершена";
        resp.sendRedirect("/");
    }
}
