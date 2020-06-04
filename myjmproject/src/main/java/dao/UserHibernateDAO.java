package dao;


import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.DBHelper;

import java.util.List;


public class UserHibernateDAO implements UserDao {
    private SessionFactory sessionFactory = DBHelper.getSessionFactory();
    private static UserHibernateDAO instance;

    private UserHibernateDAO() {

    }

    public static UserHibernateDAO getInstance() {
        if (instance == null) {
            instance = new UserHibernateDAO();
        }
        return instance;
    }


    @Override
    public void addUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(user);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            session.close();
            transaction.rollback();
            e.printStackTrace();
        }

    }

    @Override
    public Long getUserIdByUsername(String username) {
        Session session = sessionFactory.openSession();
        try {
            Query query = session.createQuery("select id from User where username =:username");
            query.setString("username" , username);
            long id = (long) query.uniqueResult();
            session.close();
            return id;
        } catch (RuntimeException e) {
            session.close();
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUserById(Long id) {
        Session session = sessionFactory.openSession();
        try {
            Query query = session.createQuery("from User where  id =:id");
            query.setString("id" , String.valueOf(id));

            User user1 = (User) query.uniqueResult();
            session.close();
            return user1;

        } catch (RuntimeException e) {
            session.close();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean validateUser(User user) {
        Session session = sessionFactory.openSession();
        try {
            Query query = session.createQuery("from User where username =:username and password =:password");
            query.setString("username" , user.getUsername());
            query.setString("password" , user.getPassword());

            User user1 = (User) query.uniqueResult();
            session.close();
            return user1 != null;

        } catch (RuntimeException e) {
            session.close();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteUser(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery("delete User where id = :id");
            query.setString("id" , String.valueOf(id));
            query.executeUpdate();
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            session.close();
            return false;
        }

    }

    @Override
    public boolean updateUser(User user, Long id) {
        Session session = sessionFactory.openSession();
        try {

            Query query = session.createQuery("UPDATE User  SET name =:name, username =:username, password=:password" +
                    " where id =:id");
            query.setString("name" , user.getName());
            query.setString("username" , user.getUsername());
            query.setString("password" , user.getPassword());
            query.setString("id" , String.valueOf(id));
            query.executeUpdate();

            session.close();
            return true;

        } catch (Exception e) {
            session.close();
            e.printStackTrace();

            return false;
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        try {
            List<User> userList = session.createQuery("From User").list();
            session.close();
            return userList;
        } catch (RuntimeException e) {
            session.close();
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean validateUserByUsername(User user) {
        Session session = sessionFactory.openSession();
        try {
            Query query = session.createQuery("from User where username =:username");
            query.setString("username" , user.getUsername());

            User user1 = (User) query.uniqueResult();
            session.close();
            return user1 != null;

        } catch (RuntimeException e) {
            session.close();
            throw new RuntimeException(e);
        }


    }

    @Override
    public String getRoleByUsername(String username) {
        Session session = sessionFactory.openSession();
        try {
            Query query = session.createQuery("select role from User where username =:username");
            query.setString("username" , username);
            String role = query.uniqueResult().toString();
            session.close();
            return role;
        } catch (RuntimeException e) {
            session.close();
            throw new RuntimeException(e);
        }
    }
}