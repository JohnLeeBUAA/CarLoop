package com.buf.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by xin on 2016/2/21.
 */
public class Create {
    public static int createTableUser()
    {
        int rs;
        //set up connection to the database
        Connection conn = JDBC.getConnection();
        try {
            Statement stmt = conn.createStatement();
            String sqlCreate = "create table " + "user" +
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

    public static int createTableVehicle() {
        int rs;
        //set up connection to the database
        Connection conn = JDBC.getConnection();
        try {
            Statement stmt = conn.createStatement();
            String sqlCreate = "create table " + "vehicle" +
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

    public static int createTalbeCarpool_created() {
        int rs;
        //set up connection to the database
        Connection conn = JDBC.getConnection();
        try {
            Statement stmt = conn.createStatement();
            String sqlCreate = "create table " + "carpool_created" +
                    " (cc_id INT NOT NULL AUTO_INCREMENT, cc_drivername VARCHAR(32), cc_depart_lat DOUBLE," +
                    " cc_depart_lng DOUBLE, cc_depart_loc VARCHAR(32), cc_desti_lat DOUBLE," +
                    " cc_desti_lng DOUBLE, cc_desti_loc VARCHAR(32), cc_data DATE, cc_time TIME, cc_time_range TIME," +
                    " cc_maxpassenger INT, cc_price INT, cc_passengerconfirmed INT, cc_passengeraboard INT, cc_status INT" +
                    " FOREIGN KEY (cc_drivername) REFERENCES user(u_name), PRIMARY KEY (cc_id));";
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

        Create.createTableVehicle();
    }
}
