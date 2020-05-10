package dao;

import model.User;
import utils.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDao{
    private Connection connection;
    private static UserJdbcDAO instance;

    private UserJdbcDAO() {
       connection = DBHelper.getConnection();
    }

    public static UserJdbcDAO getInstance() {
        if (instance == null) {
            instance = new UserJdbcDAO();
        }
        return instance;
    }


    public void addUser(User user) throws SQLException {
        String name = user.getName();
        String username = user.getUsername();
        String password = user.getPassword();
        String role = user.getRole();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into users(name,username,password,role) " +
                "values('" + name + "','" + username + "','" + password +"','"+ role + "') ");

        preparedStatement.execute();
        preparedStatement.close();
    }

    public Long getUserIdByUsername(String username) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select id from users where username='"+username+"'");
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();
        Long id = null;
        while (resultSet.next()){
           id = Long.parseLong(resultSet.getString(1));
        }
        preparedStatement.close();
        resultSet.close();
        return id;
    }

    public String getRoleByUsername(String username) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select role from users where username='"+username+"'");
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();
        String role = "";
        while (resultSet.next()){
            role = resultSet.getString(1);
        }
        preparedStatement.close();
        resultSet.close();

        return role;
    }

    public User getUserById(Long id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select * from users where id='"+id+"'");
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();
        String name = "",username = "",password ="", role = "";
        while (resultSet.next()){
            name = resultSet.getString(2);
            username = resultSet.getString(3);
            password = resultSet.getString(4);
            role = resultSet.getString(5);
        }
        User user = new User(name,username,password,role);
        preparedStatement.close();
        resultSet.close();
        return user;
    }

    public boolean validateUser(User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users" +
                " where username = '"+user.getUsername()+"' and password ='"+user.getPassword()+"'");
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();
        String username = "", password = "";
        while (resultSet.next()){
            username = resultSet.getString(3);
            password = resultSet.getString(4);
        }
        preparedStatement.close();
        resultSet.close();
        return user.getUsername().equals(username) && user.getPassword().equals(password);
    }

    public boolean validateUserByUsername(User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users where username = '"+user.getUsername()+"'");
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();
        String username = "";
        while (resultSet.next()){
            username = resultSet.getString(3);
        }
        preparedStatement.close();
        resultSet.close();
        return user.getUsername().equals(username);
    }

    public boolean deleteUser(Long id)  {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE  FROM users where  id='"+id+"'");
            preparedStatement.execute();
            preparedStatement.close();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUser(User user, Long id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("update users set name='"+user.getName()+"'" +
                ", username='"+user.getUsername()+"', password = '"+user.getPassword()+"' where id='"+id+"'");
        preparedStatement.execute();
        preparedStatement.close();
            return true;
    }


    public List<User> getAllUsers() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users");
        preparedStatement.execute();
        ResultSet result = preparedStatement.getResultSet();
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
        preparedStatement.close();
        result.close();
        return list;
    }


}
