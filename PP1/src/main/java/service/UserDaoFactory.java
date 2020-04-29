package service;

import DAO.UserDAO;
import DAO.UserHibernateDAO;
import DAO.UserJdbcDAO;
import util.DBHelper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UserDaoFactory {
    private static String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static String props = rootPath + "dao.properties";

    public static UserDAO getDao(){
        UserDAO dao = null;
        String daotype = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(props);
            Properties prop = new Properties();
            prop.load(fileInputStream);
            daotype = prop.getProperty("daotype");
        } catch (IOException e) {
            System.out.println("Файл конфигурации " + props + " не обнаружен");
            e.printStackTrace();
        }
        if(daotype.equals("JDBC")){
            dao = new UserJdbcDAO(DBHelper.getInstance().getConnection());
        }
        else if(daotype.equals("Hibernate")){
            dao = new UserHibernateDAO(DBHelper.getInstance().getSessionFactory());
        }
        return dao;
    }
}
