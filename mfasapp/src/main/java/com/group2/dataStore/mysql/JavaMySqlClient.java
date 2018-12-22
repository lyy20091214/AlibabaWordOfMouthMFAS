package com.group2.dataStore.mysql;

import breeze.linalg.where;
import com.group2.util.AuraConfig;
import redis.clients.jedis.JedisPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by bigdata on 11/18/18.
 */
public class JavaMySqlClient {
    private static ConnectionPool connPool = null;

    public static ConnectionPool get() throws Exception {
        if(connPool == null) {
            // 创建数据库连接库对象
            // 	create database training default character set utf8 collate utf8_bin;
            // grant all on jira.* to 'training'@'%' identified by 'training';

            connPool = new ConnectionPool(
                    AuraConfig.getRoot().getString("mysql.driverName"),
                    AuraConfig.getRoot().getString("mysql.url"),
                    AuraConfig.getRoot().getString("mysql.root"),
                    AuraConfig.getRoot().getString("mysql.password"));
            // 新建数据库连接库
            connPool.createPool();
        }
        return connPool;
    }
    public static void hincrBy(String tableName,String userid,long count) throws SQLException {
        Connection conn = connPool.getConnection(); // 从连接库中获取一个可用的连接
        Statement stmt = conn.createStatement();
            String sql = "INSERT INTO tableName(userid,click) VALUES (?,?) ON DUPLICATE KEY UPDATE click =click+1";
            System.out.println("insertSql="+sql);
            stmt.execute(sql);
        connPool.returnConnection(conn); // 连接使用完后释放连接到连接池
    }

    public void insert(String insertSql) throws SQLException {
        Connection conn = connPool.getConnection(); // 从连接库中获取一个可用的连接
        Statement stmt = conn.createStatement();
        stmt.execute(insertSql);
        stmt.close();
        connPool.returnConnection(conn); // 连接使用完后释放连接到连接池
    }

    public int update(String updateSql) throws SQLException {
        Connection conn = connPool.getConnection(); // 从连接库中获取一个可用的连接
        Statement stmt = conn.createStatement();
        int uresult=stmt.executeUpdate(updateSql);
        stmt.close();
        connPool.returnConnection(conn); // 连接使用完后释放连接到连接池
        return uresult;
    }
}
