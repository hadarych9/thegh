package DAO;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDAO{
    private Connection connection;

    public UserJdbcDAO(Connection connection){
        this.connection = connection;
    }

    public interface TResultHandler<T> {
        T handle(ResultSet resultSet) throws SQLException;
    }

    public static class TExecutor {
        public static <T> T execQuery(Connection con, String query, TResultHandler<T> handler) throws SQLException {
            Statement stmt = con.createStatement();
            stmt.execute(query);
            ResultSet resultSet = stmt.getResultSet();
            T value = handler.handle(resultSet);
            resultSet.close();
            stmt.close();
            return value;
        }
    }

    public static int execUpdate(Connection con, String query) throws SQLException {
        Statement stmt = con.createStatement();
        stmt.execute(query);
        int updated = stmt.getUpdateCount();
        stmt.close();
        return updated;
    }

    @Override
    public long addUser(User user){
        long x = 0;
        try{
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO users (name,password,age,role) VALUES (?, ?, ?, ?)");
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getPassword());
            pstmt.setLong(3, user.getAge());
            pstmt.setString(4, user.getRole());
            x = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return x;
    }

    @Override
    public boolean doesUserNotExist(String name){
        return getByName(name) == null;
    }

    @Override
    public int deleteUser(Long id) {
        int x = 0;
        try{
            x = execUpdate(connection, "DELETE FROM users WHERE id='" + id + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return x;
    }

    @Override
    public User getById(Long id) {
        String name = null;
        String pass = null;
        Long age = null;
        String role = null;
        try{
            name = TExecutor.execQuery(connection, "SELECT * FROM users WHERE id=" + id,
                    resultSet -> {
                        resultSet.next();
                        return resultSet.getString("name");
                    });
            pass = TExecutor.execQuery(connection, "SELECT * FROM users WHERE id=" + id,
                    resultSet -> {
                        resultSet.next();
                        return resultSet.getString("password");
                    });
            age = TExecutor.execQuery(connection, "SELECT * FROM users WHERE id=" + id,
                    resultSet -> {
                        resultSet.next();
                        return resultSet.getLong("age");
                    });
            role = TExecutor.execQuery(connection, "SELECT * FROM users WHERE id=" + id,
                    resultSet -> {
                        resultSet.next();
                        return resultSet.getString("role");
                    });
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return new User(id, name, pass, age, role);
    }

    @Override
    public User getByName(String name) {
        User user = null;
        try(PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM users WHERE name=?")){
            pstmt.setString(1, name);
            pstmt.executeQuery();
            ResultSet resultSet = pstmt.getResultSet();
            if(resultSet.next()) user = new User(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getString("password"), resultSet.getLong("age"), resultSet.getString("role"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public int countRoles(String role){
        int result = 0;
        try(PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM users WHERE role=?")){
            pstmt.setString(1, role);
            pstmt.executeQuery();
            ResultSet resultSet = pstmt.getResultSet();
            while (resultSet.next()){
                result++;
            }
        } catch (SQLException e) {e.printStackTrace();}
        return result;
    }

    @Override
    public int updateUser(User user){
        Long id = user.getId();
        int x = 0;
        try {
            PreparedStatement pstmt = connection.prepareStatement("UPDATE users SET name=? , password=? , age=? , role=? WHERE id=?");
            connection.setAutoCommit(false);
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getPassword());
            pstmt.setLong(3, user.getAge());
            pstmt.setString(4, user.getRole());
            pstmt.setLong(5, id);
            x = pstmt.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
            try{
                connection.rollback();
            } catch (SQLException e1){
                e1.printStackTrace();
            }
        }
        return x;
    }

    @Override
    public List<User> getAllUsers(){
        List<User> uList = new ArrayList<>();
        try(Statement stmt = connection.createStatement(); ResultSet resultSet = stmt.executeQuery("SELECT * FROM users")) {
            while (resultSet.next()){
                uList.add(new User(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("password"),
                        resultSet.getLong("age"),
                        resultSet.getString("role")));
            }
        } catch (SQLException e) {e.printStackTrace();}
        return uList;
    }

    public void createTable() {
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS users (id bigint auto_increment, name VARCHAR(256), password VARCHAR(256), age BIGINT, role VARCHAR(256), primary key(id)) DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci");
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTable() {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DROP TABLE IF EXISTS users");
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
