package dao;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class UserDaoFactory {
    private static UserDao instance;
    Properties properties = new Properties();



    public UserDao UserDaoFactoryImpl() {
        try {
            properties.load(new FileReader(getClass().getClassLoader().getResource("config.properties").getFile()));//Sprosit naschet NullPointer
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
        String prop = properties.getProperty("daotype");

        if (prop.equals("UserHibernateDAO")) {
            instance = UserHibernateDAO.getInstance();
        } else if (prop.equals("UserJdbcDAO")) {
            instance = UserJdbcDAO.getInstance();
        }
        return instance;
    }
}
