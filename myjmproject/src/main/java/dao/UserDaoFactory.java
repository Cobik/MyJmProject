package dao;

import utils.DBHelper;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class UserDaoFactory {
    private static UserDao instance;
    Properties properties = new Properties();

    public UserDao UserDaoFactory() {
        try {
            properties.load(new FileReader("/Users/javidanhajizada/Documents/MyJmProject/myjmproject/src/main/resources/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String prop = properties.getProperty("daotype");

        if (prop.equals("UserHibernateDAO")) {
            instance = new UserHibernateDAO(DBHelper.getSessionFactory().openSession());
        } else if (prop.equals("UserJdbcDAO")) {
            instance = new UserJdbcDAO(DBHelper.getConnection());
        }
        return instance;
    }
}
