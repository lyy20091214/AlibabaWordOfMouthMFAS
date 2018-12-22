package com.group2.dataStore.hive;

import com.group2.util.AuraConfig;

import java.sql.*;

/**
 * Created by qianxi.zhang on 6/7/17.
 */
public class HiveExample {


  public void process() throws SQLException {
    try {
      Class.forName(AuraConfig.getRoot().getString("hive.driverName"));
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      System.exit(1);
    }

    Connection con = DriverManager.getConnection(
            AuraConfig.getRoot().getString("hive.url"),
            AuraConfig.getRoot().getString("hive.user"),
            AuraConfig.getRoot().getString("hive.password"));

    Statement stmt = con.createStatement();
    long startTime = System.currentTimeMillis();
    String sql = "show tables";
    ResultSet res = stmt.executeQuery(sql);
    int count = 0;
    while (res.next()) {
      count++;
      System.out.println(res.getString(1));
    }
    long stopTime = System.currentTimeMillis();
    System.out.println("time: " + (stopTime - startTime) + ", count : " + count);
  }

  public static void main(String[] args) throws SQLException {
    HiveExample example = new HiveExample();
    example.process();
  }
}
