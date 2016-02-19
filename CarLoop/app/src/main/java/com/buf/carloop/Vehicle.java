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
