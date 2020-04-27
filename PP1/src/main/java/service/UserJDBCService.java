package service;

import DAO.UserJdbcDAO;
import model.User;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

public class UserJDBCService {

    UserJdbcDAO dao = getJdbcDAO();

    private static UserJDBCService INSTANCE;

    public static UserJDBCService getInstance(){
        if(INSTANCE == null){
            synchronized (UserJDBCService.class){
                if(INSTANCE == null) INSTANCE = new UserJDBCService();
            }
        }
        return INSTANCE;
    }

    public void addUser(User user){
        dao.addUser(user);
    }

    public boolean doesClientNotExist(String name){
        return dao.doesClientNotExist(name) == null;
    }

    public List<User> getAllUsers() {
        return dao.getAllUsers();
    }

    public int updateUser(User user){
        return dao.updateUser(user);
    }

    public User getById(Long id){
        return dao.getById(id);
    }

    public int deleteUser(Long id){
        return dao.deleteUser(id);
    }

    public void createTable(){
        dao.createTable();
    }

    public void dropTable(){
        dao.dropTable();
    }

    private static Connection getMySQLConnection(){
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").
                    append("localhost:").
                    append("3306/").
                    append("db_users?").
                    append("user=hadarych&").
                    append("password=password9").
                    append("&serverTimezone=UTC");

            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException e){
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    private static UserJdbcDAO getJdbcDAO(){
        return new UserJdbcDAO(getMySQLConnection());
    }
}
