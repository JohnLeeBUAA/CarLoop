package com.buf.database;

import java.sql.Connection;
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
    // 查询多个纪录
    protected Vector selectSomeNote(String sql) {
        Vector<Vector<Object>> vector = new Vector<Vector<Object>>();
        //获得数据库连接
        Connection conn = JDBC.getConnection();
        try {
            //创建连接状态对象
            Statement stmt = conn.createStatement();
            //执行SQL语句获得查询结果
            ResultSet rs = stmt.executeQuery(sql);
            //获得查询数据表的列数
            int columnCount = rs.getMetaData().getColumnCount();
            int row = 1;
            // 遍历结果集
            while (rs.next()) {
                //创建行向量
                Vector<Object> rowV = new Vector<Object>();
                //添加行序号
                rowV.add(new Integer(row++));
                for (int column = 1; column <= columnCount; column ++) {
                    rowV.add(rs.getObject(column));
                }
                //添加行向量到结果集中
                vector.add(rowV);
            }
            //关闭结果集对象
            rs.close();
            //关闭状态连接对象
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //返回结果集向量
        return vector;
    }

    // 查询单个记录
    protected Vector selectOnlyNote(String sql) {
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

    //查询多个值
    protected Vector selectSomeValue(String sql) {
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

    //查询单个值
    protected Object selectOnlyValue(String sql) {
        Object value = null;
        Connection conn = JDBC.getConnection();
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

    // 插入、修改、删除记录
    public boolean longHaul(String sql) {
        boolean isLongHaul = true; // 持久化
        //获取数据库连接
        Connection conn = JDBC.getConnection();
        try {
            //设置为手动提交
            conn.setAutoCommit(false);
            //创建连接状态
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            //提交持久化
            conn.commit();
        } catch (SQLException e) {
            //持久化失败
            isLongHaul = false;
            try {
                //回滚
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();;
            }
            e.printStackTrace();
        }
        return isLongHaul;
    }

    //ResultSet 转换到 List 方法
    public static List<Map<String, Object>> RsToList(ResultSet rs) throws java.sql.SQLException {
        if (rs == null)
            return Collections.EMPTY_LIST;
        ResultSetMetaData md = rs.getMetaData(); //得到结果集(rs)的结构信息，比如字段数、字段名等
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
        SqlCommond d = new SqlCommond();
        d.longHaul("delete from tb_order_item");
        d.longHaul("delete from tb_order_form");
        d.longHaul("delete from tb_user");
    }
}
