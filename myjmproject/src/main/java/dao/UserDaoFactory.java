package dao;

import utils.PropertyReader;

import java.util.Properties;

public class UserDaoFactory {
    private static UserDao instance;

    public UserDao userDaoFactoryImpl() {

        Properties property = PropertyReader.getProperties("config.properties");
        String prop = property.getProperty("daotype");

        if (prop.equals("UserHibernateDAO")) {
            instance = UserHibernateDAO.getInstance();
        } else if (prop.equals("UserJdbcDAO")) {
            instance = UserJdbcDAO.getInstance();
        } else {
            throw new RuntimeException("Database Type not found");
        }
        return instance;

    }
}
