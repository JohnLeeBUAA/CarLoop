package com.buf.database;

/**
 * Created by xin on 2016/2/20.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class JDBC {
    private static final String DRIVERCLASS = "com.mysql.jdbc.Driver";
/*
    private static final String URL = "jdbc:mysql://192.168.22.1/javabase";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "jxkjinxin@4J";
*/

    //private static final String URL = "jdbc:mysql://104.196.60.15/carpool";
    private static final String URL = "jdbc:mysql://rdslo202fbr0p4o8420no.mysql.rds.aliyuncs.com/carpool";
    private static final String USERNAME = "carpool";
    private static final String PASSWORD = "carpool";



    private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

    // load the driver using the static way 加载驱动 使用静态方法
    static {
        try {
            Class.forName(DRIVERCLASS).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //create the method for the connection of database 创建数据库连接的方法
    public static Connection getConnection(){
        //get the database connection from the thread 从线程中获得数据库连接
        Connection  conn = threadLocal.get();

        if (conn == null) { // if there is no connection of the database没有可用的数据库连接
            try {
                //then use url, username and password to get the connection 通过url, username, password 获
                //create the connection of the database 创建新的数据库连接
                conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("Database connected!");
                threadLocal.set(conn);
            } catch (SQLException e) {
                System.out.println("Database connection failure!");
                e.printStackTrace();
            }
        }
        return conn;
    }

    //close the connection of the database关闭数据库连接
    public static boolean closeConnection() {
        boolean isClosed = true;
        //get the connection from the local thread 从线程中获取数据库连接
        Connection conn = threadLocal.get();
        threadLocal.set(null);
        //if the connection works数据库连接有效
        if (conn != null) {
            try {
                //close the connection of the database 关闭数据库连接
                conn.close();
            } catch (SQLException e) {
                isClosed = false;
                e.printStackTrace();
            }
        }
        return isClosed;
    }

}
