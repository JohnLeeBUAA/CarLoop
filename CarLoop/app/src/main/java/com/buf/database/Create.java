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
                    " u_email VARCHAR(32), u_identity ENUM('0', '1'), u_avatar MEDIUMBLOB," +
                    " u_rate DOUBLE DEFAULT 0.0, u_gender ENUM('male', 'female'), u_phone VARCHAR(32), u_description TEXT," +
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
                    " (v_id INT NOT NULL AUTO_INCREMENT, v_drivername VARCHAR(32), v_driverpaypal VARCHAR(32), v_driverlicense VARCHAR(32)," +
                    " v_manufacturer VARCHAR(32), v_model VARCHAR(32), v_plate VARCHAR(32)," +
                    " v_mileage INT DEFAULT 0, v_capacity INT DEFAULT 0, " +
                    " UNIQUE (v_driverlicense), UNIQUE(v_driverpaypal), UNIQUE (v_plate), FOREIGN KEY (v_drivername) REFERENCES user(u_name) on delete cascade, PRIMARY KEY (v_id));";
            System.out.println(sqlCreate);
            rs = stmt.executeUpdate(sqlCreate);
            stmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
            rs = -1;
        }
        return rs;
    }

    public static int createTableCarpool_created() {
        int rs;
        //set up connection to the database
        Connection conn = JDBC.getConnection();
        try {
            Statement stmt = conn.createStatement();
            String sqlCreate = "create table " + "carpool_created" +
                    " (cc_id INT NOT NULL AUTO_INCREMENT, cc_drivername VARCHAR(32), cc_depart_lat DOUBLE DEFAULT 0.0," +
                    " cc_depart_lng DOUBLE DEFAULT 0.0, cc_depart_loc VARCHAR(32), cc_desti_lat DOUBLE DEFAULT 0.0," +
                    " cc_desti_lng DOUBLE DEFAULT 0.0, cc_desti_loc VARCHAR(32), cc_date DATE, cc_date_range DATE, cc_time TIME, cc_time_range TIME," +
                    " cc_maxpassenger INT DEFAULT 0, cc_price INT DEFAULT 0, cc_passengerconfirmed INT DEFAULT 0, cc_passengeraboard INT DEFAULT 0, cc_status INT DEFAULT 0," +
                    " cc_demander VARCHAR(32)," +
                    " FOREIGN KEY (cc_drivername) REFERENCES user(u_name) on delete cascade, PRIMARY KEY (cc_id));";
            System.out.println(sqlCreate);
            rs = stmt.executeUpdate(sqlCreate);
            stmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
            rs = -1;
        }
        return rs;
    }

    public static int createTablePassenger_carpool() {
        int rs;
        //set up connection to the database
        Connection conn = JDBC.getConnection();
        try {
            Statement stmt = conn.createStatement();
            String sqlCreate = "create table " + "passenger_carpool" +
                    " (pc_id INT NOT NULL AUTO_INCREMENT, pc_passengername VARCHAR(32), pc_carpoolid INT," +
                    " pc_status INT DEFAULT 0, pc_message INT DEFAULT 0, pc_paid INT DEFAULT 0," +
                    " pc_aboard INT DEFAULT 0, pc_datetime DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                    " FOREIGN KEY (pc_passengername) REFERENCES user(u_name) on delete cascade," +
                    " FOREIGN KEY (pc_carpoolid) REFERENCES carpool_created(cc_id) on delete cascade, PRIMARY KEY (pc_id));";
            System.out.println(sqlCreate);
            rs = stmt.executeUpdate(sqlCreate);
            stmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
            rs = -1;
        }
        return rs;
    }

    public static int createTableReview() {
        int rs;
        //set up connection to the database
        Connection conn = JDBC.getConnection();
        try {
            Statement stmt = conn.createStatement();
            String sqlCreate = "create table " + "review" +
                    " (r_id INT NOT NULL AUTO_INCREMENT, r_passengername VARCHAR(32), r_drivername VARCHAR(32)," +
                    " r_rate Double DEFAULT 0.0, r_review TEXT," +
                    " FOREIGN KEY (r_passengername) REFERENCES user(u_name) on delete set null," +
                    " FOREIGN KEY (r_drivername) REFERENCES user(u_name) on delete cascade, PRIMARY KEY (r_id));";
            System.out.println(sqlCreate);
            rs = stmt.executeUpdate(sqlCreate);
            stmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
            rs = -1;
        }
        return rs;
    }

    public static int createTableMessage() {
        int rs;
        //set up connection to the database
        Connection conn = JDBC.getConnection();
        try {
            Statement stmt = conn.createStatement();
            String sqlCreate = "create table " + "message" +
                    " (m_id INT NOT NULL AUTO_INCREMENT, m_username VARCHAR(32), m_carpoolid INT," +
                    " m_content TEXT, m_datetime DATETIME DEFAULT CURRENT_TIMESTAMP," +
                    " FOREIGN KEY (m_username) REFERENCES user(u_name) on delete set null," +
                    " FOREIGN KEY (m_carpoolid) REFERENCES carpool_created(cc_id) on delete cascade, PRIMARY KEY (m_id));";
            System.out.println(sqlCreate);
            rs = stmt.executeUpdate(sqlCreate);
            stmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
            rs = -1;
        }
        return rs;
    }
    public static int createTableVoice() {
        int rs;
        //set up connection to the database
        Connection conn = JDBC.getConnection();
        try {
            Statement stmt = conn.createStatement();
            String sqlCreate = "create table " + "voice" +
                    " (v_id INT NOT NULL AUTO_INCREMENT, v_name VARCHAR(32), v_gender ENUM('male', 'female')," +
                    " v_voice MEDIUMBLOB, v_avatar MEDIUMBLOB," +
                    " UNIQUE(v_name), PRIMARY KEY (v_id));";
            System.out.println(sqlCreate);
            rs = stmt.executeUpdate(sqlCreate);
            stmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
            rs = -1;
        }
        return rs;
    }

    public static int createTableCarpool_demanded() {
        int rs;
        //set up connection to the database
        Connection conn = JDBC.getConnection();
        try {
            Statement stmt = conn.createStatement();
            String sqlCreate = "create table " + "carpool_demanded" +
                    " (cd_id INT NOT NULL AUTO_INCREMENT, cd_demander VARCHAR(32), cd_depart_lat DOUBLE DEFAULT 0.0," +
                    " cd_depart_lng DOUBLE DEFAULT 0.0, cd_depart_loc VARCHAR(32), cd_desti_lat DOUBLE DEFAULT 0.0," +
                    " cd_desti_lng DOUBLE DEFAULT 0.0, cd_desti_loc VARCHAR(32), cd_date DATE, cd_date_range DATE, cd_time TIME, cd_time_range TIME," +
                    " FOREIGN KEY (cd_demander) REFERENCES user(u_name) on delete cascade, PRIMARY KEY (cd_id));";
            System.out.println(sqlCreate);
            rs = stmt.executeUpdate(sqlCreate);
            stmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
            rs = -1;
        }
        return rs;
    }
    public static void main(String[] args) {
        createTableMessage();
        createTablePassenger_carpool();
    }
}
