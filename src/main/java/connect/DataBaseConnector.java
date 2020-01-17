package connect;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnector {

  private static final Logger LOGGER =
          Logger.getLogger(DataBaseConnector.class);
  private static String DB_URL = "jdbc:mysql://localhost:3306/hw";
  private static String NAME = "root";
  private static String PASSWORD = "12345";
  private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

  public final Connection connectToDb() {
    try {
      Class.forName(JDBC_DRIVER);
    } catch (ClassNotFoundException e) {
      LOGGER.error("Couldn't read JDBC Driver", e);
    }
    Connection connection = null;
    try {
      connection = DriverManager.getConnection(DB_URL, NAME, PASSWORD);
    } catch (SQLException e) {
      LOGGER.error("Couldn't connect to database", e);
    }
    return connection;
  }

  public static Connection connect() {
    DataBaseConnector dataBaseConnector = new DataBaseConnector();
    return dataBaseConnector.connectToDb();
  }
}