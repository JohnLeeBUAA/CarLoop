package com.buf.carloop;

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
        vehicle.v_driverlicense = "123";
        vehicle.v_manufacturer = "BMW";
        vehicle.v_model = "M3";
        vehicle.v_plate = "X13JG98";
        vehicle.v_mileage = 123456;
        vehicle.v_capacity = 4;
        return vehicle;
    }

    /*
    search if license already exist
     */
    public static boolean existLicense(String license) {
        return true;
    }

    /*
    add a new vehicle record
     */
    public static boolean addVehicle(int driverid, String driverlicense, String manufacturer, String model, String plate, int mileage, int capacity) {
        return true;
    }

    /*
    update vehicle on v_driverid == driverid
     */
    public static boolean updateVehicle(int driverid, String driverlicense, String manufacturer, String model, String plate, int mileage, int capacity) {
        return true;
    }
}
