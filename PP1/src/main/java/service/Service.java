package service;

import DAO.UserDAO;
import model.User;

import java.util.*;

public class Service {

    private static UserDAO dao;

    private static Service INSTANCE;

    public static Service getInstance(){
        if(INSTANCE == null){
            synchronized (Service.class){
                if(INSTANCE == null) INSTANCE = new Service(UserDaoFactory.getDao());
            }
        }
        return INSTANCE;
    }

    public Service(UserDAO dao){
        Service.dao = dao;
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
