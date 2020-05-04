package servlet;

import model.User;
import service.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "delete", urlPatterns = "/admin/delete")
public class DeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Service service = Service.getInstance();
        Long id = Long.parseLong(req.getParameter("Id"));
        User user = service.getById(id);
        if(user == null){
            req.setAttribute("Result", "Такого пользователя не существует");
            req.getRequestDispatcher("/admin").forward(req, resp);
            return;
        }
        String result = null;
        Long check = null;
        int x = 0;
        if(user.getRole() == null) user.setRole("user");
        if(user.getRole().equals("admin")){
            if(service.countRoles("admin") > 1) x = service.deleteUser(id);
            else result = "Нельзя удалить единственного администратора";
        } else if(user.getRole().equals("user") | user.getRole().equals("") | user.getRole() == null){
            x = service.deleteUser(id);
        }
        if (x > 0){
            result = "Удаление "+user.getName()+" успешно проведено";
            check = 1L;

        } else {
            if(result == null) result = "Удаление неудачно";
        }
        req.setAttribute("Result", result);
        req.setAttribute("check", check);
        req.getRequestDispatcher("/admin").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
