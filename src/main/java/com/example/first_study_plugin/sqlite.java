package com.example.first_study_plugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

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
    PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM accounts WHERE name = ?");
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
