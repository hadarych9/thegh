package DAO;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Collection;

public class UserHibernateDAO implements UserDAO {

    private SessionFactory sessionFactory;

    public UserHibernateDAO(SessionFactory factory){
        sessionFactory = factory;
    }

    @Override
    public boolean doesUserNotExist(String name) {
        Session session = sessionFactory.openSession();
        Query<User> query = session.createQuery("FROM User WHERE name = :name");
        query.setParameter("name", name);
        boolean result = query.list().isEmpty();
        session.close();
        return result;
    }

    @Override
    public long addUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.beginTransaction();
        Long result = (Long) session.save(user);
        tr.commit();
        session.close();
        return result;
    }

    @Override
    public User getById(Long id) {
        Session session = sessionFactory.openSession();
        User user = session.get(User.class, id);
        session.close();
        return user;
    }

    @Override
    public int deleteUser(Long id) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.beginTransaction();
        User user = getById(id);
        int x;
        if(user != null){
            session.delete(user);
            x = 1;
        } else x = 0;
        tr.commit();
        session.close();
        return x;
    }

    @Override
    public int updateUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.beginTransaction();
        Query<User> query = session.createQuery("UPDATE User SET name = :name , password = :password , age = :age WHERE id = :id");
        query.setParameter("id", user.getId());
        query.setParameter("name", user.getName());
        query.setParameter("password", user.getPassword());
        query.setParameter("age", user.getAge());
        query.executeUpdate();
        tr.commit();
        session.close();
        return 0;
    }

    @Override
    public Collection getAllUsers() {
        Session session = sessionFactory.openSession();
        Collection collection = session.createQuery("FROM User").list();
        session.close();
        return collection;
    }

    @Override
    public void createTable() {

    }

    @Override
    public void dropTable() {
        Session session = sessionFactory.openSession();
        Transaction tr = session.beginTransaction();
        session.createQuery("DELETE FROM User").executeUpdate();
        tr.commit();
        session.close();
    }
}
