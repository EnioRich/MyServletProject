package dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public final class HibernateImplementationDao implements UserDao {

  @Override
  public User getUserByName(final String name) {
    User user = null;
    try {
      Session session = HibernateSessionFactoryUtil
              .getSessionFactory()
              .openSession();
      Query query = session.createQuery(
              "SELECT user FROM User user "
                      + "WHERE user.username = :username", User.class);
      query.setParameter("username", name);
      user = (User) query.getSingleResult();
    } catch (NullPointerException e) {
      e.printStackTrace();
    }
    return user;
  }

  @Override
  public boolean checkUser(final User newUser) {
    boolean isNewUser = true;
    List<User> users = getAllUsers();
    for (User user : users) {
      if (user.getUsername().equals(newUser.getUsername())) {
        isNewUser = false;
      }
    }
    return isNewUser;
  }

  @Override
  public void addUser(final User user) {
    Session session = HibernateSessionFactoryUtil
            .getSessionFactory().openSession();
    session.beginTransaction();
    session.save(user);
    session.getTransaction().commit();
    session.close();
  }

  @Override
  public void deleteUser(final User user) {
    Session session = HibernateSessionFactoryUtil
            .getSessionFactory().openSession();
    session.beginTransaction();
    session.delete(user);
    session.getTransaction().commit();
    session.close();
  }

  @Override
  public void updateUsername(final User user, final String newUsername) {
    Session session = HibernateSessionFactoryUtil
            .getSessionFactory().openSession();
    session.beginTransaction();
    user.setUsername(newUsername);
    session.update(user);
    session.getTransaction().commit();
    session.close();
  }

  @Override
  public void updatePassword(final User user, final String newPassword) {
    Session session = HibernateSessionFactoryUtil
            .getSessionFactory().openSession();
    session.beginTransaction();
    user.setPassword(newPassword);
    session.update(user);
    session.getTransaction().commit();
    session.close();
  }

  public List<User> getAllUsers() {
    return (List<User>) HibernateSessionFactoryUtil
            .getSessionFactory()
            .openSession()
            .createQuery("From User")
            .list();
  }

  @Override
  public User getUserById(final int id) {
    return HibernateSessionFactoryUtil
            .getSessionFactory().openSession().get(User.class, id);
  }
}