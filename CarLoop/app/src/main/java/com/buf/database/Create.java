package com.buf.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by xin on 2016/2/21.
 */
public class Create {
    public static int createTableUser(String name)
    {
        int rs;
        //set up connection to the database
        Connection conn = JDBC.getConnection();
        try {
            Statement stmt = conn.createStatement();
            String sqlCreate = "create table " + name +
                    " (u_id INT NOT NULL AUTO_INCREMENT, u_name VARCHAR(32), u_password CHAR(32)," +
                    " u_email VARCHAR(32), u_identity ENUM('0', '1'), u_avatar BLOB," +
                    " u_rate DOUBLE, u_gender ENUM('male', 'female'), u_phone VARCHAR(32), u_description TEXT," +
                    " KEY (u_id), UNIQUE (u_email), UNIQUE (u_name), PRIMARY KEY (u_name));";
            System.out.println(sqlCreate);
            rs = stmt.executeUpdate(sqlCreate);
            stmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
            rs = -1;
        }
        return rs;
    }

    public static int createTableVehichle(String name) {
        int rs;
        //set up connection to the database
        Connection conn = JDBC.getConnection();
        try {
            Statement stmt = conn.createStatement();
            String sqlCreate = "create table " + name +
                    " (v_id INT NOT NULL AUTO_INCREMENT, v_drivername VARCHAR(32), v_driverlicense VARCHAR(32)," +
                    " v_manufacturer VARCHAR(32), v_model VARCHAR(32), v_plate VARCHAR(32)," +
                    " v_mileage INT, v_capacity INT, " +
                    " UNIQUE (v_driverlicense), UNIQUE (v_plate), FOREIGN KEY (v_drivername) REFERENCES user(u_name), PRIMARY KEY (v_id));";
            System.out.println(sqlCreate);
            rs = stmt.executeUpdate(sqlCreate);
            stmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
            rs = -1;
        }
        return rs;
    }

    public static int createTalbeCarpool_created(String name) {
        int rs;
        //set up connection to the database
        Connection conn = JDBC.getConnection();
        try {
            Statement stmt = conn.createStatement();
            String sqlCreate = "create table " + name +
                    " (v_id INT NOT NULL AUTO_INCREMENT, v_drivername VARCHAR(32), v_driverlicense VARCHAR(32)," +
                    " v_manufacturer VARCHAR(32), v_model VARCHAR(32), v_plate VARCHAR(32)," +
                    " v_mileage INT, v_capacity INT, " +
                    " UNIQUE (v_driverlicense), FOREIGN KEY (v_drivername) REFERENCES user(u_name), PRIMARY KEY (v_id));";
            System.out.println(sqlCreate);
            rs = stmt.executeUpdate(sqlCreate);
            stmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
            rs = -1;
        }
        return rs;
    }

    public static int createTableCarpoo_demanded(String name) {
        return 0;
    }

    public static int createTableDriver_interested(String name) {
        return 0;
    }

    public static int createTablePassenger_interested(String name) {
        return 0;
    }

    public static int createTablePassenger_confirmed(String name) {
        return 0;
    }

    public static int createTableReview(String name) {
        return 0;
    }
    public static void main(String[] args) {

        Create.createTableVehichle("vehicle");
    }
}
