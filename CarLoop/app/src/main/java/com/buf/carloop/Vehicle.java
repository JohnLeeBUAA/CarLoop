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
    private String v_drivername;
    private String v_driverpaypal;
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

    public String getV_drivername() {
        return v_drivername;
    }

    public void setV_drivername(String v_drivername) {
        this.v_drivername = v_drivername;
    }

    public String getV_driverlicense() {
        return v_driverlicense;
    }

    public void setV_driverlicense(String v_driverlicense) {
        this.v_driverlicense = v_driverlicense;
    }

    public String getV_driverpaypal() {
        return v_driverpaypal;
    }

    public void setV_driverpaypal(String v_driverpaypal) {
        this.v_driverpaypal = v_driverpaypal;
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
    search vehicle with drivername
     */
    public static Vehicle getVehicle(String drivername) {
        Vehicle vehicle = new Vehicle();
        String sqlComm = "select * from vehicle where v_drivername='" + drivername + "';";
        AsyncSelectOnlyNote task = new AsyncSelectOnlyNote();
        task.execute(sqlComm);
        try {
            Vector<Object> vector = task.get(5000, TimeUnit.MILLISECONDS);
            vehicle.setV_id((int) vector.elementAt(0));
            vehicle.setV_drivername((String) vector.elementAt(1));
            vehicle.setV_driverpaypal((String) vector.elementAt(2));
            vehicle.setV_driverlicense((String) vector.elementAt(3));
            vehicle.setV_manufacturer((String) vector.elementAt(4));
            vehicle.setV_model((String) vector.elementAt(5));
            vehicle.setV_plate((String) vector.elementAt(6));
            vehicle.setV_mileage((int) vector.elementAt(7));
            vehicle.setV_capacity((int) vector.elementAt(7));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehicle;
    }

    /*
    search if license already exist
     */
    public static int existLicense(String license) {
        String sqlComm = "select v_driverlicense from user where v_driverlicense = '" + license + "';";
        AsyncSelectOnlyValue task = new AsyncSelectOnlyValue();
        task.execute(sqlComm);
        try {
            Object value = task.get(10000, TimeUnit.MILLISECONDS);
            if(value == null) {
                return 1;
            }
            else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /*
    add a new vehicle record
     */
    public static int addVehicle(String drivername, String driverpaypal, String driverlicense, String manufacturer, String model, String plate, int mileage, int capacity) {
        String sqlComm = "insert into vehicle (v_drivername, v_driverpaypal, v_driverlicense, v_manufacturer, v_model, v_plate, v_mileage, v_capacity) values ('" +
                drivername+ "', '" + driverpaypal + "', '" + driverlicense + "', '" + manufacturer + "', '" + model + "', '" + plate +
                "', " + mileage + ", " + capacity + ");";
        AsyncSQLLongHaul task = new AsyncSQLLongHaul();
        task.execute(sqlComm);
        try {
            return task.get(10000, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /*
    update vehicle on v_drivername == drivername
     */
    public static int updateVehicle(String drivername, String driverpaypal, String driverlicense, String manufacturer, String model, String plate, int mileage, int capacity) {
        String sqlComm = "update vehicle set  " +
                "v_driverpaypal='" + driverpaypal + "', " +
                "v_driverlicense= '" + driverlicense + "'," +
                "v_manufacturer='" + manufacturer + "', " +
                "v_model='" + model + "', " +
                "v_plate='" + plate + "', " +
                "v_mileage=" + mileage + ", " +
                "v_capacity=" + capacity + " " +
                "where v_drivername='" + drivername + "';";
        AsyncSQLLongHaul task = new AsyncSQLLongHaul();
        task.execute(sqlComm);
        try {
            return task.get(10000, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
