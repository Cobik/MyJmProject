package dao;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    void addUser(User user) throws SQLException;

    Long getUserIdByUsername(String username) throws SQLException;

    User getUserById(Long id) throws SQLException;

    boolean validateUser(User user) throws SQLException;

    boolean deleteUser(Long id) throws SQLException;

    boolean updateUser(User user, Long id) throws SQLException;

    List<User> getAllUsers() throws SQLException;

    boolean validateUserByUsername(User user) throws SQLException;

    String getRoleByUsername(String username) throws SQLException;
}
