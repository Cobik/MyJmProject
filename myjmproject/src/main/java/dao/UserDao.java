package dao;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public void addUser(User user) throws SQLException {
        Statement statement = connection.createStatement();
        String name = user.getName();
        String username = user.getUsername();
        String password = user.getPassword();

        statement.execute("insert into users(name,username,password) values('" + name + "','" + username + "','" + password + "') ");
        statement.close();

    }

    public Long getUserIdByUsername(String username) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("select id from users where username='"+username+"'");
        ResultSet resultSet = statement.getResultSet();
        Long id = null;
        while (resultSet.next()){
           id = Long.parseLong(resultSet.getString(1));
        }
        statement.close();
        resultSet.close();
        return id;
    }

    public User getUserById(Long id) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("select * from users where id='"+id+"'");
        ResultSet resultSet = statement.getResultSet();
        String name = "",username = "",password ="";
        while (resultSet.next()){
            name = resultSet.getString(2);
            username = resultSet.getString(3);
            password = resultSet.getString(4);
        }
        User user = new User(name,username,password);
        statement.close();
        resultSet.close();
        return user;
    }

    public boolean validateUser(User user) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("SELECT * FROM users where username = '"+user.getUsername()+"'and password ='"+user.getPassword()+"'");
        ResultSet resultSet = statement.getResultSet();
        String username = "", password = "";
        while (resultSet.next()){
            username = resultSet.getString(3);
            password = resultSet.getString(4);
        }
        statement.close();
        resultSet.close();
        if (user.getUsername().equals(username) && user.getPassword().equals(password)){
            return true;
        } else return user.getUsername().equals(username);
    }

    public boolean deleteUser(Long id)  {
        try {
            Statement statement = connection.createStatement();
            statement.execute("DELETE  FROM users where  id='"+id+"'");
            statement.close();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUser(User user, Long id) throws SQLException {
        Statement statement = connection.createStatement();
            statement.execute("update users set name='"+user.getName()+"', username='"+user.getUsername()+"', password = '"+
                    user.getPassword()+"' where id='"+id+"'");
            return true;
    }


    public List<User> getAllUsers() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("SELECT * FROM users");
        ResultSet result = statement.getResultSet();
        List<User> list = new ArrayList<>();
        while (result.next()) {
            list.add(
                    new User(
                            result.getLong(1),
                            result.getString(2),
                            result.getString(3),
                            result.getString(4)
                    ));
        }
        statement.close();
        result.close();
        return list;
    }

    public void createTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("create table if not exists users (id bigint auto_increment, name varchar(256),username varchar(256), password varchar(256), primary key (id))");
        stmt.close();
    }

    public void dropTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DROP TABLE IF EXISTS users");
        stmt.close();
    }
}
