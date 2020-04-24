package service;

import dao.UserDaoFactory;
import exception.DBException;
import model.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private static UserService instance;

    UserDaoFactory daoFactory = new UserDaoFactory();

    private UserService() {

    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public Long getUserIdByUsername(String username) {
        try {
            return daoFactory.UserDaoFactory().getUserIdByUsername(username);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<User> getAllUsers() {
        try {
            return daoFactory.UserDaoFactory().getAllUsers();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean addUser(User user) throws SQLException {

        if (daoFactory.UserDaoFactory().validateUser(user)) {
            return false;
        } else {
            daoFactory.UserDaoFactory().addUser(user);
            return true;
        }
    }

    public String getRoleByUsername(String username) {
        try {
            return daoFactory.UserDaoFactory().getRoleByUsername(username);
        } catch (SQLException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public boolean deleteUser(Long id) throws SQLException {
        return daoFactory.UserDaoFactory().deleteUser(id);
    }

    public boolean signInUser(User user) throws SQLException {
        return daoFactory.UserDaoFactory().validateUser(user);
    }

    public boolean validateUser(User user) throws SQLException {
        return daoFactory.UserDaoFactory().validateUser(user) || daoFactory.UserDaoFactory().validateUserByUsername(user);
    }

    public boolean updateUser(User user, Long id) throws SQLException {
        return daoFactory.UserDaoFactory().updateUser(user, id);
    }

    public User getUserById(Long id) {
        try {
            return daoFactory.UserDaoFactory().getUserById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void cleanUp() throws DBException {
        try {
            daoFactory.UserDaoFactory().dropTable();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public void createTable() throws DBException {
        try {
            daoFactory.UserDaoFactory().createTable();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
}
