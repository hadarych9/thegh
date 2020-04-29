package service;

import DAO.UserDAO;
import DAO.UserHibernateDAO;
import DAO.UserJdbcDAO;
import model.User;
import util.DBHelper;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.*;

public class Service {

    private static String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static String props = rootPath + "dao.properties";

    private static UserDAO dao;

    private static Service INSTANCE;

    public static Service getInstance(){
        try {
            FileInputStream fileInputStream = new FileInputStream(props);
            Properties prop = new Properties();
            prop.load(fileInputStream);
            String daotype = prop.getProperty("daotype");
            if(daotype.equals("JDBC")) dao = new UserJdbcDAO(DBHelper.getConnection());
            else if(daotype.equals("Hibernate")) dao = new UserHibernateDAO(DBHelper.getSessionFactory());
        } catch (IOException e) {
            System.out.println("Файл конфигурации " + props + " не обнаружен");
            e.printStackTrace();
        }

        if(INSTANCE == null){
            synchronized (Service.class){
                if(INSTANCE == null) INSTANCE = new Service();
            }
        }
        return INSTANCE;
    }

    public Service(){

    }

    public void addUser(User user){
        dao.addUser(user);
    }

    public boolean doesUserNotExist(String name){
        return dao.doesUserNotExist(name);
    }

    public List<User> getAllUsers() {
        return (List) dao.getAllUsers();
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
}
