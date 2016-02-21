package com.buf.database;

/**
 * Created by xin on 2016/2/20.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class JDBC {
    private static final String DRIVERCLASS = "com.mysql.jdbc.Driver";

    private static final String URL = "jdbc:mysql://localhost:3306/javabase";
    private static final String USERNAME = "test";
    private static final String PASSWORD = "test1234";


    private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

    // 加载驱动 使用静态方法
    static {
        try {
            Class.forName(DRIVERCLASS).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //创建数据库连接的方法
    public static Connection getConnection(){
        //从线程中获得数据库连接
        Connection  conn = threadLocal.get();

        if (conn == null) { // 没有可用的数据库连接
            try {
                //通过url, username, password 获取

                //创建新的数据库连接
                conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                threadLocal.set(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    //关闭数据库连接
    public static boolean closeConnection() {
        boolean isClosed = true;
        //从线程中获取数据库连接
        Connection conn = threadLocal.get();
        threadLocal.set(null);
        //数据库连接有效
        if (conn != null) {
            try {
                //关闭数据库连接
                conn.close();
            } catch (SQLException e) {
                isClosed = false;
                e.printStackTrace();
            }
        }
        return isClosed;
    }


}
