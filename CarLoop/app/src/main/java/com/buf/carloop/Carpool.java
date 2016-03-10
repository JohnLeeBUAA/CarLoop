package com.buf.carloop;

import com.buf.database.AsyncSQLLongHaul;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by zijin on 17/02/16.
 */
public class Carpool {
    private int carpoolid;
    private String drivername;
    private String depart_loc;
    private double depart_lat;
    private double depart_lng;
    private String desti_loc;
    private double desti_lat;
    private double desti_lng;
    private String date;
    private String time;
    private String date_range;
    private String time_range;
    private int maxpassenger;
    private int price;
    private int passengerconfirmed;
    private int passengeraboard;
    private int status;
    private byte[] driveravatar;

    public Carpool() {

    }

    public Carpool(int carpoolid, String drivername,
                   String depart_loc, double depart_lat, double depart_lng,
                   String desti_loc, double desti_lat, double desti_lng,
                   String date, String time, String date_range, String time_range,
                   int maxpassenger, int price, int passengerconfirmed, int passengeraboard, int status,
                   byte[] driveravatar) {
        this.carpoolid = carpoolid;
        this.drivername = drivername;
        this.depart_loc = depart_loc;
        this.depart_lat = depart_lat;
        this.depart_lng = depart_lng;
        this.desti_lat = desti_lat;
        this.desti_loc = desti_loc;
        this.desti_lng = desti_lng;
        this.date = date;
        this.time = time;
        this.date_range = date_range;
        this.time_range = time_range;
        this.maxpassenger = maxpassenger;
        this.price = price;
        this.passengerconfirmed = passengerconfirmed;
        this.passengeraboard = passengeraboard;
        this.status = status;
        this.driveravatar = driveravatar;
    }

    public int getCarpoolid() {
        return carpoolid;
    }

    public void setCarpoolid(int carpoolid) {
        this.carpoolid = carpoolid;
    }

    public String getDrivername() {
        return drivername;
    }

    public void setDrivername(String drivername) {
        this.drivername = drivername;
    }

    public String getDepart_loc() {
        return depart_loc;
    }

    public void setDepart_loc(String depart_loc) {
        this.depart_loc = depart_loc;
    }

    public double getDepart_lat() {
        return depart_lat;
    }

    public void setDepart_lat(double depart_lat) {
        this.depart_lat = depart_lat;
    }

    public double getDepart_lng() {
        return depart_lng;
    }

    public void setDepart_lng(double depart_lng) {
        this.depart_lng = depart_lng;
    }

    public String getDesti_loc() {
        return desti_loc;
    }

    public void setDesti_loc(String desti_loc) {
        this.desti_loc = desti_loc;
    }

    public double getDesti_lat() {
        return desti_lat;
    }

    public void setDesti_lat(double desti_lat) {
        this.desti_lat = desti_lat;
    }

    public double getDesti_lng() {
        return desti_lng;
    }

    public void setDesti_lng(double desti_lng) {
        this.desti_lng = desti_lng;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate_range() {
        return date_range;
    }

    public void setDate_range(String date_range) {
        this.date_range = date_range;
    }

    public String getTime_range() {
        return time_range;
    }

    public void setTime_range(String time_range) {
        this.time_range = time_range;
    }

    public int getMaxpassenger() {
        return maxpassenger;
    }

    public void setMaxpassenger(int maxpassenger) {
        this.maxpassenger = maxpassenger;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPassengerconfirmed() {
        return passengerconfirmed;
    }

    public void setPassengerconfirmed(int passengerconfirmed) {
        this.passengerconfirmed = passengerconfirmed;
    }

    public int getPassengeraboard() {
        return passengeraboard;
    }

    public void setPassengeraboard(int passengeraboard) {
        this.passengeraboard = passengeraboard;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public byte[] getDriveravatar() {
        return driveravatar;
    }

    public void setDriveravatar(byte[] driveravatar) {
        this.driveravatar = driveravatar;
    }

    /*
        search in created carpool with given id
        return a Carpool instance
         */
    public static Carpool getCarpool(int carpoolid) {
        return new Carpool();
    }

    /*
    create a new carpool in carpool_created
    NOTE: date and time are string, convert to sql.date and sql.time type if needed
    date format(yyyy/MM/dd), time format(kk:mm) (simpledateformat)
     */
    public static int createCarpool(
            String user_name,
            String depart_loc,
            double depart_lat,
            double depart_lng,
            String desti_loc,
            double desti_lat,
            double desti_lng,
            String date,
            String time,
            String date_range,
            String time_range,
            int maxpassenger,
            int price,
            int passengerconfirmed,
            int passengeraboard,
            int status
    ) {
        String sqlComm = String.format("insert into carpool_created (cc_drivername, cc_depart_lat, cc_depart_lng, cc_depart_loc, " +
                        "cc_desti_lat, cc_desti_lng, cc_desti_loc, cc_date, cc_date_range, cc_time, cc_time_range, cc_maxpassenger, " +
                        "cc_price, cc_passengerconfirmed, cc_passengeraboard, cc_status) values ('%s', %f, %f, '%s', %f, %f, '%s', " +
                        "'%s', '%s', '%s', '%s', %d, %d, %d, %d, %d);", user_name, depart_lat, depart_lng, depart_loc, desti_lat, desti_lng,
                desti_loc, date, date_range, time, time_range, maxpassenger, price, passengerconfirmed, passengeraboard, status);
        // Sql create user operation
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
    update a carpool with given id
    NOTE: date and time are string, convert to sql.date and sql.time type if needed
    date format(yyyy/MM/dd), time format(kk:mm) (simpledateformat)
     */
    public static int updateCarpool(
            int carpoolid,
            String depart_loc,
            double depart_lat,
            double depart_lng,
            String desti_loc,
            double desti_lat,
            double desti_lng,
            String date,
            String time,
            String date_range,
            String time_range,
            int maxpassenger,
            int price
    ) {
        String sqlComm = String.format("update carpool_created set cc_depart_lat=%f, cc_depart_lng=%f, cc_depart_loc='%s', " +
                        "cc_desti_lat=%f, cc_desti_lng=%f, cc_desti_loc='%s', cc_date='%s', cc_date_range='%s', cc_time='%s', " +
                        "cc_time_range='%s', cc_maxpassenger=%d, cc_price=%d where cc_id=%d;",
                        depart_lat, depart_lng, depart_loc, desti_lat, desti_lng, desti_loc, date, date_range, time, time_range, maxpassenger, price, carpoolid);
        // Sql create user operation
        AsyncSQLLongHaul task = new AsyncSQLLongHaul();
        task.execute(sqlComm);
        try {
            return task.get(10000, TimeUnit.MILLISECONDS);

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static List<Carpool> generateFakeList() {
        List<Carpool> list = new ArrayList<Carpool>();
        list.add(new Carpool(1, "John Lee",
                "Waterloo", 11.11D, 22.22D,
                "Toronto", 33.33D, 44.44D,
                "2016/3/2", "12:00", "2016/3/8", "22:00",
                4, 40, 3, 0, 0,
                null));
        list.add(new Carpool(2, "Luke",
                "Beijing", 11.11D, 22.22D,
                "Tianjin", 33.33D, 44.44D,
                "2016/4/3", "8:00", "2016/4/4", "10:00",
                3, 55, 3, 0, 0,
                null));
        list.add(new Carpool(1, "Jin Xin",
                "Huston", 11.11D, 22.22D,
                "Dallas", 33.33D, 44.44D,
                "2016/5/2", "3:00", "2016/5/2", "3:00",
                8, 90, 5, 0, 0,
                null));
        return list;
    }

    public static List<Carpool> generateFakeList2() {
        List<Carpool> list = new ArrayList<Carpool>();
        list.add(new Carpool(1, "John Lee",
                "Waterloo", 11.11D, 22.22D,
                "Toronto", 33.33D, 44.44D,
                "2016/3/2", "12:00", "2016/3/8", "22:00",
                4, 40, 3, 0, 0,
                null));
        return list;
    }

    public static List<Carpool> generateFakeList3() {
        List<Carpool> list = new ArrayList<Carpool>();
        list.add(new Carpool(1, "Jin Xin",
                "Huston", 11.11D, 22.22D,
                "Dallas", 33.33D, 44.44D,
                "2016/5/2", "3:00", "2016/5/2", "3:00",
                8, 90, 5, 0, 0,
                null));
        return list;
    }

    /*
    select * from carpool_created where cc_drivername = user_name
    join user to get avatar
    on pc_datetime descending order
     */
    public static List<Carpool> getCreatedList(String user_name) {
        return generateFakeList();
    }

    /*
    search * in passenger_carpool where pc_passengername = user_name and pc_status = 1
    join user to get avatar
    on pc_datetime descending order
     */
    public static List<Carpool> getInterestedList(String user_name) {
        return generateFakeList2();
    }

    /*
    search * in passenger_carpool where pc_passengername = user_name and pc_status = 2
    join user to get avatar
    on pc_datetime descending order
    */
    public static List<Carpool> getConfirmedList(String user_name) {
        return generateFakeList3();
    }

    /*
    search * in passenger_carpool where pc_passengername = user_name and pc_message = 1
    join user to get avatar
    on pc_datetime descending order
    */
    public static List<Carpool> getMessageList(String user_name) {
        return generateFakeList();
    }

    /*
    search carpools with valid date and time and cc_status == 0
    and carpoolid not in (select pc_carpoolid from passenger_carpool where pc_passengername = user_name)
    and passengerconfirmed < maxpassenger
    on walking distance ascending order
     */
    public static List<Carpool> getSearchList(
            String user_name,
            double depart_lat_val,
            double depart_lng_val,
            double desti_lat_val,
            double desti_lng_val,
            String date,
            String time,
            String date_range,
            String time_range
    ) {
        return generateFakeList();
    }

    /*
    update passenger_carpool set pc_message = 1 where pc_passengername = user_name and pc_carpoolid = carpoolid
     */
    public static int updateMessage(String user_name, int carpoolid) {
        return 0;
    }

    /*
    delete carpool in carpool_created table
    note records in other table that has FK on carpoolid and is set delete on cascade
     */
    public static int deleteCarpool(int carpoolid) {
        return 0;
    }

    /*
    in created_carpool with cc_id = carpoolid
    if cc_passengeraboard == cc_passengerconfirmed   return true;
    else   return false;
     */
    public static boolean startTripCheck(int carpoolid) {
        return true;
    }

    /*
    update created_carpool set cc_status = 1 where cc_id = carpoolid
     */
    public static int startTrip(int carpoolid) {
        return 0;
    }

    /*
    insert into passenger_carpool
    pc_passengername = user_name, pc_carpoolid = carpoolid, pc_status = 0
    need to set pc_datetime as well
     */
    public static int declineSearch(String user_name, int carpoolid) {
        return 0;
    }

    /*
    insert into passenger_carpool
    pc_passengername = user_name, pc_carpoolid = carpoolid, pc_status = 1
    need to set pc_datetime as well
     */
    public static int interestedSearch(String user_name, int carpoolid) {
        return 0;
    }

    /*
    insert into passenger_carpool
    pc_passengername = user_name, pc_carpoolid = carpoolid, pc_status = 2
    need to set pc_datetime as well
    !!!IMPORTANT: ALSO update created_carpool set cc_passengerconfirmed = cc_passengerconfirmed + 1 where cc_id = carpoolid
     */
    public static int confirmSearch(String user_name, int carpoolid) {
        return 0;
    }

    /*
    delete record in passenger_carpool where pc_passengername = user_name and pc_carpoolid = carpoolid and pc_status = 1
     */
    public static int deleteInterested(String user_name, int carpoolid) {
        return 0;
    }

    /*
    update passenger_carpool set pc_aboard = 1 where pc_passengername = user_name and pc_carpoolid = carpoolid
    !!!IMPORTANT: ALSO update created_carpool set cc_passengeraboard = cc_passengeraboard + 1 where cc_id = carpoolid
     */
    public static int confirmInterested(String user_name, int carpoolid) {
        return 0;
    }

    /*
    update passenger_carpool set pc_status = 2 where pc_passengername = user_name and pc_carpoolid = carpoolid
    !!!IMPORTANT: ALSO update created_carpool set cc_passengerconfirmed = cc_passengerconfirmed + 1 where cc_id = carpoolid
     */
    public static int aboardConfirmed(String user_name, int carpoolid) {
        return 0;
    }

    /*
    in passenger_carpool with pc_passengername = user_name and pc_carpoolid = carpoolid
    if pc_paid = 1  return true;
    else   return false;
     */
    public static boolean reviewCheck(String user_name, int carpoolid) {
        return true;
    }
}
