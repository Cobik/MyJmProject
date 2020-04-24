package dao;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDao{
    private Connection connection;

    public UserJdbcDAO(Connection connection) {
        this.connection = connection;
    }

    public void addUser(User user) throws SQLException {
        Statement statement = connection.createStatement();
        String name = user.getName();
        String username = user.getUsername();
        String password = user.getPassword();
        String role = user.getRole();

        statement.execute("insert into users(name,username,password,role) values('" + name + "','" + username + "','" + password +"','"+ role + "') ");
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

    public String getRoleByUsername(String username) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("select role from users where username='"+username+"'");
        ResultSet resultSet = statement.getResultSet();
        String role = "";
        while (resultSet.next()){
            role = resultSet.getString(1);
        }
        statement.close();
        resultSet.close();

        return role;
    }

    public User getUserById(Long id) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("select * from users where id='"+id+"'");
        ResultSet resultSet = statement.getResultSet();
        String name = "",username = "",password ="", role = "";
        while (resultSet.next()){
            name = resultSet.getString(2);
            username = resultSet.getString(3);
            password = resultSet.getString(4);
            role = resultSet.getString(5);
        }
        User user = new User(name,username,password,role);
        statement.close();
        resultSet.close();
        return user;
    }

    public boolean validateUser(User user) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("SELECT * FROM users where username = '"+user.getUsername()+"' and password ='"+user.getPassword()+"'");
        ResultSet resultSet = statement.getResultSet();
        String username = "", password = "";
        while (resultSet.next()){
            username = resultSet.getString(3);
            password = resultSet.getString(4);
        }
        statement.close();
        resultSet.close();
        return user.getUsername().equals(username) && user.getPassword().equals(password);
    }

    public boolean validateUserByUsername(User user) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("SELECT * FROM users where username = '"+user.getUsername()+"'");
        ResultSet resultSet = statement.getResultSet();
        String username = "";
        while (resultSet.next()){
            username = resultSet.getString(3);
        }
        statement.close();
        resultSet.close();
        return user.getUsername().equals(username);
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
                            result.getString(4),
                            result.getString(5)
                    ));
        }
        statement.close();
        result.close();
        return list;
    }


}
