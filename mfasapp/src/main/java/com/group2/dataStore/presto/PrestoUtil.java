package com.group2.dataStore.presto;

import com.group2.util.AuraConfig;

import java.sql.*;

/**
 * Created by lvyou on 2018/12/19.
 */
public class PrestoUtil {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName(AuraConfig.getRoot().getString("presto.driverName"));
        Connection connection = DriverManager.getConnection(AuraConfig.getRoot().getString("presto.url"),AuraConfig.getRoot().getString("presto.user"),AuraConfig.getRoot().getString("presto.password"));  ;
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("show tables");
        while (rs.next()) {
            System.out.println(rs.getString(1));
        }
        rs.close();
        connection.close();
    }
}
