package dao;

import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        return user.getUsername().equals(username) && user.getPassword().equals(password) || user.getUsername().equals(username);
    }

    public boolean deleteUser(String name, String username)  {
        try {
            Statement statement = connection.createStatement();
            statement.execute("DELETE  FROM users where  name='" + name + "' and username= '" + username + "'");
            ResultSet resultSet = statement.getResultSet();
            return resultSet.next();
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
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
