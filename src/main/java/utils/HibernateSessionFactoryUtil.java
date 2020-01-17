package utils;

import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import org.apache.log4j.Logger;

public final class HibernateSessionFactoryUtil {
  private static final Logger LOGGER = Logger
          .getLogger(HibernateSessionFactoryUtil.class);
  private static SessionFactory sessionFactory;
  private static final String USERNAME = "root";
  private static final String PASSWORD = "12345";
  private static final String HIBERNATE_DIALECT =
          "org.hibernate.dialect.H2Dialect";
  private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
  private static final String CONNECTION_URL =
          "jdbc:mysql://localhost:3306/hw?useUnicode=true"
                  + "&useJDBCCompliantTimezoneShift=true"
                  + "&useLegacyDatetimeCode=false&serverTimezone=UTC";
  private static final String UNABLE_CONNECT_TO_DB_MESSAGE =
          "Couldn't connect to database";

  private HibernateSessionFactoryUtil() {
  }

  public static SessionFactory getSessionFactory() {
    if (sessionFactory == null) {
      try {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.setProperty("hibernate.connection.username", USERNAME);
        configuration.setProperty("hibernate.connection.password", PASSWORD);
        configuration.setProperty("hibernate.connection.url", CONNECTION_URL);
        configuration.setProperty("hibernate.dialect", HIBERNATE_DIALECT);
        StandardServiceRegistryBuilder builder =
                new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties());
        Class.forName(JDBC_DRIVER);
        sessionFactory = configuration.buildSessionFactory(builder.build());
      } catch (Exception e) {
        LOGGER.error(UNABLE_CONNECT_TO_DB_MESSAGE, e);
      }
    }
    return sessionFactory;
  }
}
