package dao;

import model.User;
import utils.DBHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDao {
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
        PreparedStatement preparedStatement = connection.prepareStatement("insert into users(name,username,password,role) " +
                "values(?,?,?,?) ");

        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getUsername());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.setString(4, user.getRole());
        preparedStatement.execute();
        preparedStatement.close();
    }

    public Long getUserIdByUsername(String username) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select id from users where username = ?");
        preparedStatement.setString(1, username);
        preparedStatement.execute();

        ResultSet resultSet = preparedStatement.getResultSet();
        resultSet.next();
        Long id = Long.parseLong(resultSet.getString(1));

        preparedStatement.close();
        resultSet.close();
        return id;
    }

    public String getRoleByUsername(String username) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select role from users where username= ?");
        preparedStatement.setString(1, username);
        preparedStatement.execute();

        ResultSet resultSet = preparedStatement.getResultSet();
        resultSet.next();
        String role = resultSet.getString(1);

        preparedStatement.close();
        resultSet.close();

        return role;
    }

    public User getUserById(Long id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select * from users where id = ?");
        preparedStatement.setLong(1, id);
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();
        String name = "";
        String username = "";
        String password = "";
        String role = "";
        while (resultSet.next()) {
            name = resultSet.getString(2);
            username = resultSet.getString(3);
            password = resultSet.getString(4);
            role = resultSet.getString(5);
        }
        User user = new User(name, username, password, role);
        preparedStatement.close();
        resultSet.close();
        return user;
    }

    public boolean validateUser(User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select * from users where username = ? and password = ?");
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();
        String username = "";
        String password = "";
        while (resultSet.next()) {
            username = resultSet.getString(3);
            password = resultSet.getString(4);
        }
        preparedStatement.close();
        resultSet.close();
        return user.getUsername().equals(username) && user.getPassword().equals(password);
    }

    public boolean validateUserByUsername(User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select * from users where username = ?");
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();
        resultSet.next();

        String username = resultSet.getString(3);

        preparedStatement.close();
        resultSet.close();
        return user.getUsername().equals(username);
    }

    public boolean deleteUser(Long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from users where  id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUser(User user, Long id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("update users set name = ?, username = ?, password = ? where id = ? ");
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getUsername());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.setLong(4, user.getId());
        preparedStatement.execute();
        preparedStatement.close();
        return true;
    }


    public List<User> getAllUsers() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select * from users");
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

    public void createTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("create table if not exists users (id bigint auto_increment, name varchar(256),username varchar(256), password varchar(256), role varchar(256), primary key (id))");
        stmt.close();
    }

    public void dropTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("drop table if exists users");
        stmt.close();
    }


}
