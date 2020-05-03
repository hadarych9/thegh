package DAO;

import model.User;

import java.util.Collection;

public interface UserDAO {

    boolean doesUserNotExist(String name);

    long addUser(User user);

    User getById(Long id);

    User getByName(String name);

    int countRoles(String role);

    int deleteUser(Long id);

    int updateUser(User user);

    Collection getAllUsers();

    void createTable();

    void dropTable();
}
