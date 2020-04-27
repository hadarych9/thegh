package servlet;

import model.User;
import service.UserJDBCService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "delete", urlPatterns = "/delete")
public class DeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserJDBCService service = UserJDBCService.getInstance();
        Long id = Long.parseLong(req.getParameter("Id"));
        User user = service.getById(id);
        String result;
        Long check = null;
        int x = service.deleteUser(id);
        if (x > 0){
            result = "Удаление "+user.getName()+" успешно проведено";
            check = 1L;

        } else {
            result = "Удаление неудачно";
        }
        req.setAttribute("Result", result);
        req.setAttribute("check", check);
        req.getRequestDispatcher("/").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
