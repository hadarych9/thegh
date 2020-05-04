package servlet;

import service.Active;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "user", urlPatterns = "/user")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        req.getRequestDispatcher("view/userPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String name = req.getParameter("name");
        String password = req.getParameter("pass");
        if(name.equals("log") & password.equals("off")) {
            req.setAttribute("Result", "Вы вышли из системы");
            req.setAttribute("check", 1L);
            Active.getInstance().setActive(null);
        }
        req.getRequestDispatcher("view/login.jsp").forward(req, resp);
    }
}
