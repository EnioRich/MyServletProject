package dao;

import connect.DataBaseConnector;
import model.Good;
import model.MyQuery;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public final class GoodDao {

  private static final Logger LOGGER = Logger.getLogger(GoodDao.class);
  private Connection connection = DataBaseConnector.connect();

  public void addGood(final Good good) {
    try (PreparedStatement preparedStatement = connection
            .prepareStatement(MyQuery.addGood())) {
      preparedStatement.setString(1, good.getName());
      preparedStatement.setString(2, good.getDescription());
      preparedStatement.setDouble(3, good.getPrice());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      LOGGER.error("Couldn't add good to Database", e);
    }
  }

  public Good getGoodById(final int id) {
    Good good = new Good();
    try {
      PreparedStatement preparedStatement = connection
              .prepareStatement(MyQuery.getGoodById());
      preparedStatement.setInt(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        int goodId = resultSet.getInt(1);
        String name = resultSet.getString(2);
        String description = resultSet.getString(3);
        double price = resultSet.getDouble(4);
        good = new Good(goodId, name, description, price);
        return good;
      }
    } catch (SQLException e) {
      LOGGER.error("Couldn't get good by id", e);
    }
    return good;
  }

  public List<Good> getAllGoods() {
    List<Good> goodsList = new ArrayList<>();
    try {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(MyQuery.selectGood());
      while (resultSet.next()) {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        double price = resultSet.getDouble("price");
        Good good = new Good(id, name, description, price);
        goodsList.add(good);
      }
    } catch (SQLException e) {
      LOGGER.error("Couldn't get all goods", e);
    }
    return goodsList;
  }

  public boolean checkGood(final Good good) {
    boolean isNewGood = true;
    try {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(MyQuery.selectGood());
      while (resultSet.next()) {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        double price = resultSet.getDouble("price");
        Good goodFromDb = new Good(id, name, description, price);
        if (goodFromDb.getName().equals(good.getName())) {
          isNewGood = false;
        }
      }
    } catch (SQLException e) {
      LOGGER.error("Couldn't check user from Database", e);
    }
    return isNewGood;
  }

  public void deleteGood(final Good good) {
    try (PreparedStatement preparedStatement = connection
            .prepareStatement(MyQuery.deleteGood())) {
      preparedStatement.setInt(1, good.getId());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      LOGGER.error("Couldn't deleteUser user", e);
    }
  }
}