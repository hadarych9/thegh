package servlet;

import model.User;
import service.Active;
import service.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "add", urlPatterns = "/add")
public class AddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("view/add.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Service service = Service.getInstance();
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String password = req.getParameter("pass");
        String role = (req.getParameter("role") == null || req.getParameter("role").equals("")) ? "user" : req.getParameter("role");
        Long age;
        if(service.doesUserNotExist(name)){
            try{
                if(!req.getParameter("age").equals("") & req.getParameter("age") != null) {
                    age = Long.parseLong(req.getParameter("age"));
                    if(name.equals("") | password.equals("")) {
                        req.setAttribute("Result", "Пожалуйста, заполните все поля");
                    } else if(!name.equals(name.replaceAll("[^\\da-zA-Zа-яёА-ЯЁ]", ""))){
                        req.setAttribute("Result", "Для ввода логина необходимо использовать только буквы или цифры");
                    }
                    else {
                        User user = new User(name, password, age, role);
                        service.addUser(user);
                        if(Active.getInstance().getActive() == null) Active.getInstance().setActive(user);
                        req.setAttribute("Result", "Пользователь " + name + " добавлен(а)!");
                        req.setAttribute("check", 1L);
                    }
                } else req.setAttribute("Result", "Пожалуйста, заполните все поля");
            } catch (NumberFormatException e){
                req.setAttribute("Result", "Пожалуйста, введите возраст в виде цифры");
            }
        } else req.setAttribute("Result", "Такой пользователь уже существует");
        /*if(Active.getInstance().getActive() != null){
            req.getRequestDispatcher("/admin").forward(req, resp);
            return;
        }*/
        if(Active.getInstance().getActive() != null) {
            if (Active.getInstance().getActive().getRole().equals("admin")) {
                req.getRequestDispatcher("/admin").forward(req, resp);
            } else {
                resp.sendRedirect("/user");
            }
            return;
        }
        req.getRequestDispatcher("view/add.jsp").forward(req, resp);
    }
}