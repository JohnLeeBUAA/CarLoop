package com.buf.database;

import android.util.Log;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;
import java.util.Vector;
import java.util.List;
import java.util.Map;

/**
 * Created by xin on 2016/2/20.
 */
public class SqlCommond {
    // Query for several records
    public Vector selectSomeNote(String sql) {
        Vector<Vector<Object>> vector = new Vector<Vector<Object>>();
        //get the connection of database
        Connection conn = JDBC.getConnection();
        try {
            //create the object of the connection 创建连接状态对象
            Statement stmt = conn.createStatement();
            //execute the sql to get the result of the query执行SQL语句获得查询结果
            ResultSet rs = stmt.executeQuery(sql);
            //get the column number of the sql result获得查询数据表的列数
            int columnCount = rs.getMetaData().getColumnCount();
            int row = 1;
            // add all the result sets遍历结果集
            while (rs.next()) {
                //create the row vector 创建行向量
                Vector<Object> rowV = new Vector<Object>();
                //add the column number添加行序号
                rowV.add(new Integer(row++));
                for (int column = 1; column <= columnCount; column ++) {
                    rowV.add(rs.getObject(column));
                }
                //add the column vector to the result set添加行向量到结果集中
                vector.add(rowV);
            }
            //close the result object 关闭结果集对象
            rs.close();
            //close the sql connection 关闭状态连接对象
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //return the result vector 返回结果集向量
        return vector;
    }

    // query for only one record 查询单个记录
    public Vector selectOnlyNote(String sql) {
        Vector<Object> vector = null;
        Connection conn = JDBC.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int columnCount = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                vector = new Vector<Object>();
                for (int column = 1; column <= columnCount; column ++) {
                    vector.add(rs.getObject(column));
                }
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vector;
    }

    //query for several value 查询多个值
    public Vector selectSomeValue(String sql) {
        Vector<Object> vector = new Vector<Object>();
        Connection conn = JDBC.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                vector.add(rs.getObject(1));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vector;
    }

    //query for only one value 查询单个值
    public Object selectOnlyValue(String sql) {
        Object value = null;
        Connection conn = JDBC.getConnection();
        if (conn == null) System.out.println("why connection is null?");
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                value = rs.getObject(1);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return value;
    }

    // insert, modify, delete the record 插入、修改、删除记录
    public int longHaul(String sql) {
        boolean isLongHaul = true; // long lasting 持久化
        //get the connection of the database 获取数据库连接
        Connection conn = JDBC.getConnection();
        try {
            //set it as hand commitment设置为手动提交
            conn.setAutoCommit(false);
            //create the connection to mysql创建连接状态
            Statement stmt = conn.createStatement();
            int status = stmt.executeUpdate(sql);
            stmt.close();
            //commit the long lasting result 提交持久化
            conn.commit();
            if (status == 0) return 4; // password not match
            else return 0;
        } catch (SQLException e) {
            //the long lasting fails持久化失败
            try {
                //rollback 回滚
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            Log.v("**error**", "*************************\n" + e.getMessage());
            if (e.getMessage().contains("u_name") || e.getMessage().contains("PRIMARY")) {
                return 1;
            }
            else if (e.getMessage().contains("u_email")) {
                return 2;
            }
            else if (e.getMessage().contains("v_driverlicense")) {
                return 3;
            }
            else if (e.getMessage().contains("v_plate")) {
                return 5;
            }
            else {
                return -1;
            }
        }
    }

    // get the image blob from database
    public byte[] selectBlob(String sql) {
        byte[] value = new byte[]{(byte) 0xe0};;
        //get the connection of the database
        Connection conn = JDBC.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                value = rs.getBytes("u_avatar");
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return value;
    }

    //insert the image blob into database
    public boolean insertBlob(String sql, byte[] blobData) {
        boolean isLongHaul = true; // long lasting 持久化
        //get the connection of the database 获取数据库连接
        Connection conn = JDBC.getConnection();
        try {
            //set it as hand commitment设置为手动提交
            conn.setAutoCommit(false);
            //create the connection to mysql创建连接状态
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setBinaryStream(1, new ByteArrayInputStream(blobData), blobData.length);
            stmt.executeUpdate();
            stmt.close();
            //commit the long lasting result 提交持久化
            conn.commit();
        } catch (SQLException e) {
            //the long lasting fails持久化失败
            isLongHaul = false;
            try {
                //rollback 回滚
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return isLongHaul;
    }
    //change ResultSet to List ResultSet 转换到 List 方法
    public static List<Map<String, Object>> RsToList(ResultSet rs) throws java.sql.SQLException {
        if (rs == null)
            return Collections.EMPTY_LIST;
        // get the structure of the result set
        ResultSetMetaData md = rs.getMetaData(); //得到结果集(rs)的结构信息，比如字段数、字段名等
        // get the column number of the result set
        int columnCount = md.getColumnCount(); //返回此 ResultSet 对象中的列数
        List list = new ArrayList();
        Map rowData = new HashMap();
        while (rs.next()) {
            rowData = new HashMap(columnCount);
            for (int i = 1; i <= columnCount; i++) {
                rowData.put(md.getColumnName(i), rs.getObject(i));
            }
            list.add(rowData);
            //System.out.println("list : " + list.toString());
        }
        return list;
    }

    public static void main(String[] args) {
        String username = "test1";
        String password = "1234";
        String email = "liu1@gmail.com";

        String sqlComm = "update user set" +
                " u_gender='" + "male" + "', " +
                " u_phone='" + "1234" + "', " +
                " u_description='" + "ewe" + "' " +
                " where u_name='" + "1" + "';";
        /*
        String sqlComm = "insert into user (u_name, u_password, u_email, u_identity) values ('" + username + "', '"
                + password + "', '" + email + "', " + 0 + ");";

*/
        SqlCommond sqlCommond = new SqlCommond();
       int value = sqlCommond.longHaul(sqlComm);
        System.out.println(value);

    }
}
