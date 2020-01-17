package model;

public final class MyQuery {
  private MyQuery() {
  }

  public static String createTableUsers() {
    return "CREATE TABLE Users ("
            + "id INT NOT NULL AUTO_INCREMENT,"
            + " username VARCHAR (50) NOT NULL,"
            + " email VARCHAR (50) NOT NULL,"
            + " password VARCHAR (512) NOT NULL,"
            + " role VARCHAR (10) NOT NULL, "
            + " salt VARCHAR (10) NOT NULL, "
            + " PRIMARY KEY (id)"
            + ")";
  }

  public static String selectUser() {
    return "SELECT id, username, email, password, role, salt FROM Users";
  }

  public static String addUser() {
    return "INSERT INTO Users ("
            + "username, email, password, role, salt"
            + ") VALUES (?, ?, ?, ?, ?)";
  }

  public static String deleteUser() {
    return "DELETE FROM Users WHERE username = ?";
  }

  public static String updateUsername() {
    return "UPDATE Users SET username=? WHERE username=?";
  }

  public static String updatePassword() {
    return "UPDATE Users SET password=? WHERE username=?";
  }

  public static String getByUserName() {
    return "SELECT * FROM Users WHERE username = ?;";
  }

  public static String getByUserId() {
    return "SELECT * FROM Users WHERE id = ?;";
  }

  public static String createTableGoods() {
    return "CREATE TABLE Goods ("
            + "id INT NOT NULL AUTO_INCREMENT,"
            + " name VARCHAR (500) NOT NULL,"
            + " description VARCHAR (1000),"
            + " price DOUBLE NOT NULL,"
            + " PRIMARY KEY (id)"
            + ")";
  }

  public static String addGood() {
    return "INSERT INTO Goods ("
            + "name, description, price"
            + ") VALUES (?, ?, ?)";
  }

  public static String getGoodById() {
    return "SELECT * FROM Goods WHERE id = ?;";
  }

  public static String selectGood() {
    return "SELECT id, name, description, price FROM Goods";
  }

  public static String deleteGood() {
    return "DELETE FROM Goods WHERE id = ?";
  }
}