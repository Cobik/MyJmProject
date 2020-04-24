package dao;

import model.User;
import utils.DBHelper;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface UserDao {
    Connection connection = DBHelper.getConnection();

    void addUser(User user) throws SQLException;

    Long getUserIdByUsername(String username) throws SQLException;

    User getUserById(Long id) throws SQLException;

    boolean validateUser(User user) throws SQLException;

    boolean deleteUser(Long id) throws SQLException;

    boolean updateUser(User user, Long id) throws SQLException;

    List<User> getAllUsers() throws SQLException;

    boolean validateUserByUsername(User user) throws SQLException;

    String getRoleByUsername(String username) throws SQLException;

    default void createTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("create table if not exists users (id bigint auto_increment, name varchar(256),username varchar(256), password varchar(256), role varchar(256), primary key (id))");
        stmt.close();
    }

    default void dropTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DROP TABLE IF EXISTS users");
        stmt.close();
    }
}
