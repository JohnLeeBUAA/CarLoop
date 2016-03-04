package com.buf.carloop;

import android.os.AsyncTask;

import com.buf.database.AsyncSQLLongHaul;
import com.buf.database.AsyncSelectOnlyNote;
import com.buf.database.AsyncSelectOnlyValue;
import com.buf.database.SqlCommond;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 * Created by zijin on 18/02/16.
 */
public class Vehicle {
    private int v_id;
    private int v_driverid;
    private String v_driverlicense;
    private String v_manufacturer;
    private String v_model;
    private String v_plate;
    private int v_mileage;
    private int v_capacity;

    public int getV_id() {
        String sqlComm = "";
        AsyncSelectOnlyValue task = new AsyncSelectOnlyValue();
        task.execute(sqlComm);
        try {
            int value = (int) task.get(5000, TimeUnit.MILLISECONDS);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return v_id;
    }

    public void setV_id(int v_id) {
        this.v_id = v_id;
    }

    public int getV_driverid() {
        return v_driverid;
    }

    public void setV_driverid(int v_driverid) {
        this.v_driverid = v_driverid;
    }

    public String getV_driverlicense() {
        return v_driverlicense;
    }

    public void setV_driverlicense(String v_driverlicense) {
        this.v_driverlicense = v_driverlicense;
    }

    public String getV_manufacturer() {
        return v_manufacturer;
    }

    public void setV_manufacturer(String v_manufacturer) {
        this.v_manufacturer = v_manufacturer;
    }

    public String getV_model() {
        return v_model;
    }

    public void setV_model(String v_model) {
        this.v_model = v_model;
    }

    public String getV_plate() {
        return v_plate;
    }

    public void setV_plate(String v_plate) {
        this.v_plate = v_plate;
    }

    public int getV_mileage() {
        return v_mileage;
    }

    public void setV_mileage(int v_mileage) {
        this.v_mileage = v_mileage;
    }

    public int getV_capacity() {
        return v_capacity;
    }

    public void setV_capacity(int v_capacity) {
        this.v_capacity = v_capacity;
    }

    /*
    search vehicle with driverid
     */
    public static Vehicle getVehicle(int driverid) {
        Vehicle vehicle = new Vehicle();
        String sqlComm = "select * from vehicle where v_driverid=" + driverid + ";";
        AsyncSelectOnlyNote task = new AsyncSelectOnlyNote();
        task.execute(sqlComm);
        try {
            Vector<Object> vector = task.get(5000, TimeUnit.MILLISECONDS);
            vehicle.v_driverlicense = (String) vector.elementAt(2);
            vehicle.v_manufacturer = (String) vector.elementAt(3);
            vehicle.v_model = (String) vector.elementAt(4);
            vehicle.v_plate = (String) vector.elementAt(5);
            vehicle.v_mileage = (int) vector.elementAt(6);
            vehicle.v_capacity = (int) vector.elementAt(7);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehicle;
    }

    /*
    search if license already exist
     */
    public static boolean existLicense(String license) {
        String sqlComm = "select v_driverlicense from user where v_driverlicense = '" + license + "';";
        AsyncSelectOnlyValue task = new AsyncSelectOnlyValue();
        task.execute(sqlComm);
        try {
            Object value = task.get(5000, TimeUnit.MILLISECONDS);
            if(value == null) {
                return false;
            }
            else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    /*
    add a new vehicle record
     */
    public static boolean addVehicle(int driverid, String driverlicense, String manufacturer, String model, String plate, int mileage, int capacity) {
        String sqlComm = "insert into vehicle (v_driverid, v_driverlicense, v_manufacturer, v_model, v_plate, v_mileage, v_capacity) values (" +
                driverid+ ", '" +driverlicense + "', '" + manufacturer + "', '" + model + "', '" + plate +
                "', " +mileage + ", " + capacity + ");";
        AsyncSQLLongHaul task = new AsyncSQLLongHaul();
        task.execute(sqlComm);
        try {
            boolean value = task.get(5000, TimeUnit.MILLISECONDS);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
    update vehicle on v_driverid == driverid
     */
    public static boolean updateVehicle(int driverid, String driverlicense, String manufacturer, String model, String plate, int mileage, int capacity) {
        String sqlComm = "update vehicle set  v_driverlicense= '" + driverlicense + "'," +
                "v_manufacturer='" + manufacturer + "', " +
                "v_model='" + model + "', " +
                "v_plate='" + plate + "', " +
                "v_mileage=" + mileage + ", " +
                "v_capacity=" + capacity + " " +
                "where v_driverid=" + driverid + ";";
        AsyncSQLLongHaul task = new AsyncSQLLongHaul();
        task.execute(sqlComm);
        try {
            boolean value = task.get(5000, TimeUnit.MILLISECONDS);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
