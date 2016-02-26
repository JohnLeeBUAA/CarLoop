package com.buf.carloop;

import android.os.AsyncTask;

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

    /*
    search vehicle with driverid
     */
    public static Vehicle getVehicle(int driverid) {
        Vehicle vehicle = new Vehicle();
        /*vehicle.v_driverlicense = "123";
        vehicle.v_manufacturer = "BMW";
        vehicle.v_model = "M3";
        vehicle.v_plate = "X13JG98";
        vehicle.v_mileage = 123456;
        vehicle.v_capacity = 4;
        */
        getVehicleSQL task = new getVehicleSQL();
        task.execute(driverid);
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

    private static class getVehicleSQL extends AsyncTask<Integer, Void, Vector> {
        public getVehicleSQL(){}

        @Override
        protected Vector doInBackground(Integer... params) {
            String sqlSelect = "select * from vehicle where v_driverid=" + params[0] + ";";
            SqlCommond sqlCommond = new SqlCommond();
            Vector<Object> vector = sqlCommond.selectOnlyNote(sqlSelect);
            return vector;
        }

    }
    /*
    add a new vehicle record
     */
    public static boolean addVehicle(int driverid, String driverlicense, String manufacturer, String model, String plate, int mileage, int capacity) {
        addVehicleSQL task = new addVehicleSQL();
        task.execute(Integer.toString(driverid), driverlicense, manufacturer, model, plate, Integer.toString(mileage), Integer.toString(capacity));
        try {
            boolean value = task.get(5000, TimeUnit.MILLISECONDS);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static class addVehicleSQL extends AsyncTask<String, Void, Boolean> {
        public addVehicleSQL(){}
        @Override
        public Boolean doInBackground(String... params) {
            String sqlSelect = "insert into vehicle (v_driverid, v_driverlicense, v_manufacturer, v_model, v_plate, v_mileage, v_capacity) values ('" +
                    Integer.parseInt(params[0]) + "', '" + params[1] + "', '" + params[2] + "', '" + params[3] + "', '" + params[4] +
                    "', " + Integer.parseInt(params[5]) + Integer.parseInt(params[6]) + ");";
            SqlCommond sqlCommond = new SqlCommond();
            Boolean value = sqlCommond.longHaul(sqlSelect);
            System.out.println(params[0]);
            System.out.println(value);
            return value;
        }
    }
    /*
    update vehicle on v_driverid == driverid
     */
    public static boolean updateVehicle(int driverid, String driverlicense, String manufacturer, String model, String plate, int mileage, int capacity) {
        updateVehicleSQL task = new updateVehicleSQL();
        task.execute(Integer.toString(driverid), driverlicense, manufacturer, model, plate, Integer.toString(mileage), Integer.toString(capacity));
        try {
            boolean value = task.get(5000, TimeUnit.MILLISECONDS);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static class updateVehicleSQL extends AsyncTask<String, Void, Boolean> {
        public updateVehicleSQL(){}
        @Override
        public Boolean doInBackground(String... params) {
            String sqlSelect = "update vehicle set  v_driverlicense= '" + params[1] + "'," +
                    "v_manufacturer='" + params[2] + "', " +
                    "v_model='" + params[3] + "', " +
                    "v_plate='" + params[4] + "', " +
                    "v_mileage=" + Integer.parseInt(params[5])+ ", " +
                    "v_capacity=" + Integer.parseInt(params[6]) + " " +
                    "where v_driverid=" + params[0] + ";";
            SqlCommond sqlCommond = new SqlCommond();
            Boolean value = sqlCommond.longHaul(sqlSelect);
            System.out.println(params[0]);
            System.out.println(value);
            return value;
        }
    }
}
