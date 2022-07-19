package com.example.first_study_plugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.UUID;

import org.bukkit.Bukkit;

public class sqlite {
  public static Connection connection() {
    String dbname = "development.db"; // 利用するデータベースファイル
    Connection conn = null;
    try {
      Class.forName("org.sqlite.JDBC");
      conn = DriverManager.getConnection("jdbc:sqlite:" + dbname);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return conn;
  }

  public static boolean exist(String name) throws SQLException {
    boolean is_exist = false;

    Connection connection = connection();
    PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM account WHERE name = ?");
    pstmt.setString(1, name);
    ResultSet rs = pstmt.executeQuery();
    if (rs.next()) {
      is_exist = true;
      Bukkit.getLogger().info("既にアカウントが存在します");
    } else {
      Bukkit.getLogger().info("アカウントが存在しません");
    }
    return is_exist;
  }

  public static boolean createAccount(UUID uuid, String name) {
    try (
        Connection connection = connection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO account (uuid, name) VALUES(?,?)");) {
      statement.setString(1, uuid.toString());
      statement.setString(2, name);
      if (statement.executeUpdate() != 0) {
        Bukkit.getLogger().info("アカウントを作成しました");

        return true;
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return false;
  }

  public static Integer getId(UUID uuid, String name) {
    try (Connection connection = connection()){
      try ( PreparedStatement statement = connection.prepareStatement(
        "SELECT * FROM account WHERE uuid = ?"
      )) {
        statement.setString(1, uuid.toString());
        // get id
        try (ResultSet resultSet = statement.executeQuery()){
          if (resultSet.next()) {
            return resultSet.getInt("id");
          }
        }
        // insert id
        try (PreparedStatement insert = connection.prepareStatement(
            "INSERT INTO account (uuid, name) VALUES(?,?)"
        )) {
          insert.setString(1, uuid.toString());
          insert.setString(2, name);
          insert.executeUpdate();
        }
        // re get id
        try (ResultSet resultSet = statement.executeQuery()) {
          if (resultSet.next()) {
            return resultSet.getInt("id");
          }
        }
      } catch (SQLException e) {
        connection.rollback();
        throw e;
      } 
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    throw new RuntimeException("The ID could not be issued.");
  }

  public static Integer getSeichiCount(Integer id) {
    try (Connection connection = connection()){
      try (PreparedStatement statement = connection.prepareStatement(
          "select * from seichi where id = ?"
      )) {
        // get count
        statement.setInt(1, id);
        try (ResultSet resultSet = statement.executeQuery()) {
          if (resultSet.next()) {
              return resultSet.getInt("count");
          }
        }
        // insert data
        try (PreparedStatement insert = connection.prepareStatement(
            "INSERT INTO seichi (id, count) VALUES (?,?)"
        )) {
          insert.setInt(1, id);
          insert.setInt(2, 0);
          insert.executeUpdate();
        }
        // re get id
        try (ResultSet resultSet = statement.executeQuery()) {
          if (resultSet.next()) {
            return resultSet.getInt("count");
          }
        }
      } catch (SQLException e) {
        connection.rollback();
        throw e;
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    throw new RuntimeException("Unexpected Error");
  }

  public static boolean setSeichiCount(int id, int count){
    try (Connection connection = connection();
         PreparedStatement statement = connection.prepareStatement(
          "UPDATE seichi SET count=? WHERE id=?"
      )) {
        statement.setInt(1, count);
        statement.setInt(2, id);
        return (statement.executeUpdate() != 0);
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
  }

  public static void test() throws SQLException {
    String dbname = "development.db"; // 利用するデータベースファイル
    Connection conn = null;
    Statement stmt = null;
    try {
      Class.forName("org.sqlite.JDBC");
      conn = DriverManager.getConnection("jdbc:sqlite:" + dbname);
      Bukkit.getLogger().info("接続成功");

      stmt = conn.createStatement();
      ResultSet rs = stmt
          .executeQuery("SELECT * FROM accounts");
      while (rs.next()) {
        int uid = rs.getInt("uid");
        String name = rs.getString("name");
        int quantity = rs.getInt("quantity");
        System.out.println(name);
        Bukkit.getLogger().info(uid + "\t" + name + "\t" + quantity);
      }
      rs.close();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (conn != null) {
          conn.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
