package com.buf.carloop;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zijin on 11/03/16.
 */
public class PassengerCarpool {
    private String passengername;
    private byte[] passengeravatar;
    private int paid;

    public String getPassengername() {
        return passengername;
    }

    public void setPassengername(String passengername) {
        this.passengername = passengername;
    }

    public byte[] getPassengeravatar() {
        return passengeravatar;
    }

    public void setPassengeravatar(byte[] passengeravatar) {
        this.passengeravatar = passengeravatar;
    }

    public int getPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }

    public PassengerCarpool() {}

    public PassengerCarpool(String passengername, byte[] passengeravatar, int paid) {
        this.passengername = passengername;
        this.passengeravatar = passengeravatar;
        this.paid = paid;
    }

    public static List<PassengerCarpool> generatefakelist() {
        List<PassengerCarpool> list = new ArrayList<PassengerCarpool>();
        list.add(new PassengerCarpool("John", null, 0));
        list.add(new PassengerCarpool("Mary", null, 0));
        list.add(new PassengerCarpool("James", null, 0));
        return list;
    }

    /*
    select * from passenger_carpool where pc_carpoolid = carpoolid and pc_status = 2
    also get the avatars of the passengers
     */
    public static List<PassengerCarpool> getPaymentList(int carpoolid) {
        return generatefakelist();
    }

    /*
    update passenger_carpool set pc_paid = 1 where pc_passengername = passengername and pc_carpoolid = carpoolid
     */
    public static int addPayment(String passengername, int carpoolid) {
        return 0;
    }
}
