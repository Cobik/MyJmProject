package dao;

import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import utils.DBHelper;

import java.sql.SQLException;
import java.util.List;


public class UserHibernateDAO implements UserDao {
    private Session session;
    private static UserHibernateDAO instance;

    private UserHibernateDAO(){
        session = DBHelper.getSessionFactory().openSession();
    }

    public static UserHibernateDAO getInstance() {
        if (instance == null) {
            instance = new UserHibernateDAO();
        }
        return instance;
    }


    @Override
    public void addUser(User user)  {
        Transaction transaction = session.beginTransaction();
        try {
            session.save(user);
            session.close();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }

    }

    @Override
    public Long getUserIdByUsername(String username) {
        Query query = session.createQuery("select id from User where username =:username");
        query.setString("username",username);
        Long id = Long.valueOf(query.executeUpdate());
        return id;
    }

    @Override
    public User getUserById(Long id)  {
      User user = (User) session.createCriteria(User.class)
              .add(Restrictions.eq("id",id))
              .setMaxResults(1).uniqueResult();
      session.close();
      return user;
    }

    @Override
    public boolean validateUser(User user)  {
        User user1 = (User) session.createCriteria(User.class)
                .add(Restrictions.eq("username",user.getUsername()))
                .add(Restrictions.eq("password",user.getPassword()))
                .setMaxResults(1).uniqueResult();
        session.close();
        return user1 != null;
    }

    @Override
    public boolean deleteUser(Long id)  {
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery("delete User where id = :id");
            query.setString("id", String.valueOf(id));
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean updateUser(User user,Long id) {
        Transaction transaction = session.beginTransaction();
       try {
           Query query = session.createQuery("UPDATE User  SET name =:name, username =:username, password=:password" +
                   " where id =:id");
           query.setString("name",user.getName());
           query.setString("username",user.getUsername());
           query.setString("password",user.getPassword());
           query.setString("id", String.valueOf(id));
           query.executeUpdate();
           transaction.commit();
           return true;
       } catch (Exception e){
           transaction.rollback();
           e.printStackTrace();
           return false;
       }
    }

    @Override
    public List<User> getAllUsers()  {
        List<User> users = session.createQuery("FROM User ").list();
        session.close();
        return users;
    }

    @Override
    public boolean validateUserByUsername(User user) throws SQLException {
        User user1 = (User) session.createCriteria(User.class)
                .add(Restrictions.eq("username",user.getUsername()))
                .setMaxResults(1).uniqueResult();
        session.close();
        return user1 != null;
    }

    @Override
    public String getRoleByUsername(String username) throws SQLException {
       User user = (User) session.createCriteria(User.class)
               .add(Restrictions.eq("username",username))
               .setMaxResults(1).uniqueResult();
       session.close();
       return user.getRole();
    }

}
