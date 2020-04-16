package service;

import dao.UserDao;
import exception.DBException;
import model.User;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserService {


    private static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("mydbtest?").          //db name
                    append("user=root&").          //login
                    append("password=root");       //password

            System.out.println("URL: " + url + "\n");

            return DriverManager.getConnection(url.toString());
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }


    private static UserDao getUserDao(){
        return new UserDao(getMysqlConnection());
    }

    public Long getUserIdByUsername(String username)  {
        UserDao dao = getUserDao();
        try {
            return dao.getUserIdByUsername(username);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<User> getAllUsers() throws SQLException {
        UserDao dao = getUserDao();
        List<User> users = dao.getAllUsers();
        return users;
    }

    public boolean addUser(User user) throws SQLException {
        UserDao dao = getUserDao();

        if (!dao.validateUser(user)){
            dao.addUser(user);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteUser(Long id)throws SQLException{
        UserDao dao = getUserDao();
        return dao.deleteUser(id);
    }

    public boolean signInUser(User user) throws SQLException {
        UserDao dao = getUserDao();
        return dao.validateUser(user);
    }

    public  boolean validateUser(User user) throws SQLException {
        UserDao dao = getUserDao();
        return dao.validateUser(user);
    }

    public boolean updateUser(User user,Long id) throws SQLException {
        UserDao dao = getUserDao();
        return dao.updateUser(user,id);
    }

    public User getUserById(Long id){
        UserDao dao = getUserDao();
        try {
            return dao.getUserById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void cleanUp() throws DBException {
        UserDao dao = getUserDao();
        try {
            dao.dropTable();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public void createTable() throws DBException {
        UserDao dao = getUserDao();
        try {
            dao.createTable();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
}
