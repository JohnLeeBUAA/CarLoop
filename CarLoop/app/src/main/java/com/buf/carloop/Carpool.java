package com.buf.carloop;

import android.os.Parcel;
import android.os.Parcelable;

import com.buf.database.AsyncSQLLongHaul;
import com.buf.database.AsyncSelectOnlyNote;
import com.buf.database.AsyncSelectOnlyValue;
import com.buf.database.AsyncSelectSomeNote;
import com.buf.database.AsyncSelectSomeValue;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import static com.buf.carloop.User.selectSQLBlob;

/**
 * Created by zijin on 17/02/16.
 */
public class Carpool implements Parcelable{
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
        this.desti_loc = desti_loc;
        this.desti_lat = desti_lat;
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

    // Parcelling part
    public Carpool(Parcel in){
        this.carpoolid = in.readInt();
        this.drivername = in.readString();
        this.depart_loc = in.readString();
        this.depart_lat = in.readDouble();
        this.depart_lng = in.readDouble();
        this.desti_loc = in.readString();
        this.desti_lat = in.readDouble();
        this.desti_lng = in.readDouble();
        this.date = in.readString();
        this.time = in.readString();
        this.date_range = in.readString();
        this.time_range = in.readString();
        this.maxpassenger = in.readInt();
        this.price = in.readInt();
        this.passengerconfirmed = in.readInt();
        this.passengeraboard = in.readInt();
        this.status = in.readInt();
        this.driveravatar = new byte[in.readInt()];
        in.readByteArray(this.driveravatar);
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.carpoolid);
        dest.writeString(this.drivername);
        dest.writeString(this.depart_loc);
        dest.writeDouble(this.depart_lat);
        dest.writeDouble(this.depart_lng);
        dest.writeString(this.desti_loc);
        dest.writeDouble(this.desti_lat);
        dest.writeDouble(this.desti_lng);
        dest.writeString(this.date);
        dest.writeString(this.time);
        dest.writeString(this.date_range);
        dest.writeString(this.time_range);
        dest.writeInt(this.maxpassenger);
        dest.writeInt(this.price);
        dest.writeInt(this.passengerconfirmed);
        dest.writeInt(this.passengeraboard);
        dest.writeInt(this.status);
        dest.writeInt(this.driveravatar.length);
        dest.writeByteArray(this.driveravatar);
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Carpool createFromParcel(Parcel in) {
            return new Carpool(in);
        }

        public Carpool[] newArray(int size) {
            return new Carpool[size];
        }
    };

    /*
        search in created carpool with given id
        return a Carpool instance
         */
    public static Carpool getCarpool(int carpoolid) {
        Carpool carpool = new Carpool();
        String sqlComm = "select * from carpool_created where cc_id = " + carpoolid + ";";
        AsyncSelectOnlyNote task = new AsyncSelectOnlyNote();
        task.execute(sqlComm);
        try {
            Vector value = task.get(10000, TimeUnit.MILLISECONDS);
            if (value == null) {
                return null;
            }
            else {
                carpool.setCarpoolid((int) value.elementAt(0));
                carpool.setDrivername((String) value.elementAt(1));
                carpool.setDepart_lat((Double) value.elementAt(2));
                carpool.setDepart_lng((Double) value.elementAt(3));
                carpool.setDepart_loc((String) value.elementAt(4));
                carpool.setDesti_lat((Double) value.elementAt(5));
                carpool.setDesti_lng((Double) value.elementAt(6));
                carpool.setDesti_loc((String) value.elementAt(7));
                carpool.setDate((String) value.elementAt(8));
                carpool.setDate_range((String) value.elementAt(9));
                carpool.setTime((String) value.elementAt(10));
                carpool.setTime_range((String) value.elementAt(11));
                carpool.setMaxpassenger((int) value.elementAt(12));
                carpool.setPrice((int) value.elementAt(13));
                carpool.setPassengerconfirmed((int) value.elementAt(14));
                carpool.setPassengeraboard((int) value.elementAt(15));
                carpool.setStatus((int) value.elementAt(16));
                carpool.setDriveravatar(selectSQLBlob(carpool.getDrivername()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
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
        byte[] avatar = selectSQLBlob(user_name);
        String sqlComm = "select * from carpool_created where cc_drivername = '" + user_name + "';";
        List<Carpool> list = new ArrayList<Carpool>();
        Carpool carpool = null;
        AsyncSelectSomeNote task = new AsyncSelectSomeNote();
        task.execute(sqlComm);
        try {
            Vector value_original = task.get(10000, TimeUnit.MILLISECONDS);
            Vector value;
            if (value_original.size() > 0) {
                for (int i = 0; i < value_original.size(); i++) {
                    value = (Vector) value_original.elementAt(i);
                    carpool = new Carpool();
                    carpool.setCarpoolid((int) value.elementAt(0));
                    carpool.setDrivername((String) value.elementAt(1));
                    carpool.setDepart_lat((Double) value.elementAt(2));
                    carpool.setDepart_lng((Double) value.elementAt(3));
                    carpool.setDepart_loc((String) value.elementAt(4));
                    carpool.setDesti_lat((Double) value.elementAt(5));
                    carpool.setDesti_lng((Double) value.elementAt(6));
                    carpool.setDesti_loc((String) value.elementAt(7));
                    carpool.setDate(((Date) value.elementAt(8)).toString());
                    carpool.setDate_range(((Date) value.elementAt(9)).toString());
                    carpool.setTime(((Time) value.elementAt(10)).toString());
                    carpool.setTime_range(((Time) value.elementAt(11)).toString());
                    carpool.setMaxpassenger((int) value.elementAt(12));
                    carpool.setPrice((int) value.elementAt(13));
                    carpool.setPassengerconfirmed((int) value.elementAt(14));
                    carpool.setPassengeraboard((int) value.elementAt(15));
                    carpool.setStatus((int) value.elementAt(16));
                    carpool.setDriveravatar(avatar);
                    list.add(carpool);
                }
            }
            else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /*
    search * in passenger_carpool where pc_passengername = user_name and pc_status = 1
    join user to get avatar
    on pc_datetime descending order
     */
    public static List<Carpool> getInterestedList(String user_name) {
        String sqlComm = "select t1.* from carpool_created as t1 inner join passenger_carpool as t2 " +
                "on t1.cc_id = t2.pc_carpoolid and t2.pc_status = 1 and t2.pc_passengername= '" + user_name + "' order by t2.pc_datetime desc;";
        List<Carpool> list = new ArrayList<Carpool>();
        Carpool carpool = null;
        AsyncSelectSomeNote task = new AsyncSelectSomeNote();
        task.execute(sqlComm);
        try {
            Vector value_original = task.get(10000, TimeUnit.MILLISECONDS);
            Vector value;
            if (value_original.size() > 0) {
                for (int i = 0; i < value_original.size(); i++) {
                    value = (Vector) value_original.elementAt(i);
                    carpool = new Carpool();
                    carpool.setCarpoolid((int) value.elementAt(0));
                    carpool.setDrivername((String) value.elementAt(1));
                    carpool.setDepart_lat((Double) value.elementAt(2));
                    carpool.setDepart_lng((Double) value.elementAt(3));
                    carpool.setDepart_loc((String) value.elementAt(4));
                    carpool.setDesti_lat((Double) value.elementAt(5));
                    carpool.setDesti_lng((Double) value.elementAt(6));
                    carpool.setDesti_loc((String) value.elementAt(7));
                    carpool.setDate(((Date) value.elementAt(8)).toString());
                    carpool.setDate_range(((Date) value.elementAt(9)).toString());
                    carpool.setTime(((Time) value.elementAt(10)).toString());
                    carpool.setTime_range(((Time) value.elementAt(11)).toString());
                    carpool.setMaxpassenger((int) value.elementAt(12));
                    carpool.setPrice((int) value.elementAt(13));
                    carpool.setPassengerconfirmed((int) value.elementAt(14));
                    carpool.setPassengeraboard((int) value.elementAt(15));
                    carpool.setStatus((int) value.elementAt(16));
                    carpool.setDriveravatar(selectSQLBlob(carpool.getDrivername()));
                    list.add(carpool);
                    System.out.println(value.elementAt(1));
                }
            }
            else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /*
    search * in passenger_carpool where pc_passengername = user_name and pc_status = 2
    join user to get avatar
    on pc_datetime descending order
    */
    public static List<Carpool> getConfirmedList(String user_name) {
        String sqlComm = "select t1.* from carpool_created as t1 inner join passenger_carpool as t2 " +
                "on t1.cc_id = t2.pc_carpoolid and t2.pc_status = 2 and t2.pc_passengername= '" + user_name + "' order by t2.pc_datetime desc;";
        List<Carpool> list = new ArrayList<Carpool>();
        Carpool carpool = null;
        AsyncSelectSomeNote task = new AsyncSelectSomeNote();
        task.execute(sqlComm);
        try {
            Vector value_original = task.get(10000, TimeUnit.MILLISECONDS);
            Vector value;
            int i;
            if (value_original.size() > 0) {
                for (i = 0; i < value_original.size(); i++) {
                    value = (Vector) value_original.elementAt(i);
                    carpool = new Carpool();
                    carpool.setCarpoolid((int) value.elementAt(0));
                    carpool.setDrivername((String) value.elementAt(1));
                    carpool.setDepart_lat((Double) value.elementAt(2));
                    carpool.setDepart_lng((Double) value.elementAt(3));
                    carpool.setDepart_loc((String) value.elementAt(4));
                    carpool.setDesti_lat((Double) value.elementAt(5));
                    carpool.setDesti_lng((Double) value.elementAt(6));
                    carpool.setDesti_loc((String) value.elementAt(7));
                    carpool.setDate(((Date) value.elementAt(8)).toString());
                    carpool.setDate_range(((Date) value.elementAt(9)).toString());
                    carpool.setTime(((Time) value.elementAt(10)).toString());
                    carpool.setTime_range(((Time) value.elementAt(11)).toString());
                    carpool.setMaxpassenger((int) value.elementAt(12));
                    carpool.setPrice((int) value.elementAt(13));
                    carpool.setPassengerconfirmed((int) value.elementAt(14));
                    carpool.setPassengeraboard((int) value.elementAt(15));
                    carpool.setStatus((int) value.elementAt(16));
                    carpool.setDriveravatar(selectSQLBlob(carpool.getDrivername()));
                    list.add(carpool);
                    System.out.println(value.elementAt(1));
                }
            }
            else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /*
    search * in passenger_carpool where pc_passengername = user_name and pc_message = 1
    join user to get avatar
    on pc_datetime descending order
    */
    public static List<Carpool> getMessageList(String user_name) {
        String sqlComm = "select t1.* from carpool_created as t1 inner join passenger_carpool as t2 " +
                "on t1.cc_id = t2.pc_carpoolid and t2.pc_message = 1 and t2.pc_passengername= '" + user_name + "' order by t2.pc_datetime desc;";
        List<Carpool> list = new ArrayList<Carpool>();
        Carpool carpool = null;
        AsyncSelectSomeNote task = new AsyncSelectSomeNote();
        task.execute(sqlComm);
        try {
            Vector value_original = task.get(10000, TimeUnit.MILLISECONDS);
            Vector value;
            int i;
            if (value_original.size() > 0) {
                for (i = 0; i < value_original.size(); i++) {
                    value = (Vector) value_original.elementAt(i);
                    carpool = new Carpool();
                    carpool.setCarpoolid((int) value.elementAt(0));
                    carpool.setDrivername((String) value.elementAt(1));
                    carpool.setDepart_lat((Double) value.elementAt(2));
                    carpool.setDepart_lng((Double) value.elementAt(3));
                    carpool.setDepart_loc((String) value.elementAt(4));
                    carpool.setDesti_lat((Double) value.elementAt(5));
                    carpool.setDesti_lng((Double) value.elementAt(6));
                    carpool.setDesti_loc((String) value.elementAt(7));
                    carpool.setDate(((Date) value.elementAt(8)).toString());
                    carpool.setDate_range(((Date) value.elementAt(9)).toString());
                    carpool.setTime(((Time) value.elementAt(10)).toString());
                    carpool.setTime_range(((Time) value.elementAt(11)).toString());
                    carpool.setMaxpassenger((int) value.elementAt(12));
                    carpool.setPrice((int) value.elementAt(13));
                    carpool.setPassengerconfirmed((int) value.elementAt(14));
                    carpool.setPassengeraboard((int) value.elementAt(15));
                    carpool.setStatus((int) value.elementAt(16));
                    carpool.setDriveravatar(selectSQLBlob(carpool.getDrivername()));
                    list.add(carpool);
                    System.out.println(value.elementAt(1));
                }
            }
            else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

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
        String sqlComm = "select * from carpool_created " +
                "where (cc_date <= '"+ date + "' and cc_date_range <= '"  + date + "' or cc_date <= '"+ date_range + "' and cc_date_range >= '" + date_range + "') and " +
                "(cc_time <= '"+ time + "' and cc_time_range <= '"  + time + "' or cc_time <= '"+ time_range + "' and cc_time_range >= '" + time_range + "') and " +
                "cc_status = 0 and cc_passengerconfirmed < cc_maxpassenger " +
                "and cc_id not in (select pc_carpoolid from passenger_carpool where pc_passengername = '" + user_name + "');";
        List<Carpool> list = new ArrayList<Carpool>();
        Carpool carpool = null;
        AsyncSelectSomeNote task = new AsyncSelectSomeNote();
        task.execute(sqlComm);
        try {
            Vector value_original = task.get(10000, TimeUnit.MILLISECONDS);
            Vector value;
            int i;
            if (value_original.size() > 0) {
                for (i = 0; i < value_original.size(); i++) {
                    value = (Vector) value_original.elementAt(i);
                    carpool = new Carpool();
                    carpool.setCarpoolid((int) value.elementAt(0));
                    carpool.setDrivername((String) value.elementAt(1));
                    carpool.setDepart_lat((Double) value.elementAt(2));
                    carpool.setDepart_lng((Double) value.elementAt(3));
                    carpool.setDepart_loc((String) value.elementAt(4));
                    carpool.setDesti_lat((Double) value.elementAt(5));
                    carpool.setDesti_lng((Double) value.elementAt(6));
                    carpool.setDesti_loc((String) value.elementAt(7));
                    carpool.setDate(((Date) value.elementAt(8)).toString());
                    carpool.setDate_range(((Date) value.elementAt(9)).toString());
                    carpool.setTime(((Time) value.elementAt(10)).toString());
                    carpool.setTime_range(((Time) value.elementAt(11)).toString());
                    carpool.setMaxpassenger((int) value.elementAt(12));
                    carpool.setPrice((int) value.elementAt(13));
                    carpool.setPassengerconfirmed((int) value.elementAt(14));
                    carpool.setPassengeraboard((int) value.elementAt(15));
                    carpool.setStatus((int) value.elementAt(16));
                    carpool.setDriveravatar(selectSQLBlob(carpool.getDrivername()));
                    list.add(carpool);
                    System.out.println(value.elementAt(1));
                }
            }
            else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /*
    update passenger_carpool set pc_message = 1 where pc_passengername = user_name and pc_carpoolid = carpoolid
     */
    public static int updateMessage(String user_name, int carpoolid) {
        String sqlComm = "update passenger_carpool set pc_message=1 where pc_passengername= '" + user_name + "' and pc_carpoolid=" + carpoolid + ";";
        AsyncSQLLongHaul task = new AsyncSQLLongHaul();

        task.execute(sqlComm);
        try {
            int value = task.get(100000, TimeUnit.MILLISECONDS);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /*
    delete carpool in carpool_created table
    note records in other table that has FK on carpoolid and is set delete on cascade
     */
    public static int deleteCarpool(int carpoolid) {
        String sqlComm = "delete from carpool_created where cc_id=" + carpoolid + ";";
        AsyncSQLLongHaul task = new AsyncSQLLongHaul();

        task.execute(sqlComm);
        try {
            int value = task.get(100000, TimeUnit.MILLISECONDS);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /*
    in created_carpool with cc_id = carpoolid
    if cc_passengeraboard == cc_passengerconfirmed   return true;
    else   return false;
     */
    public static int startTripCheck(int carpoolid) {
        String sqlComm = "select cc_passengeraboard, cc_passengerconfirmed from carpool_created where cc_id=" + carpoolid + ";";
        AsyncSelectOnlyNote task = new AsyncSelectOnlyNote();

        task.execute(sqlComm);
        try {
            Vector value = task.get(100000, TimeUnit.MILLISECONDS);
            if (value != null) {
                if ((int)value.elementAt(0) == (int)value.elementAt(1)) {
                    return 0;
                }
                else return 1;
            } else {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /*
    update carpool_created set cc_status = 1 where cc_id = carpoolid
     */
    public static int startTrip(int carpoolid) {
        String sqlComm = "update carpool_created set cc_status=1 where cc_id=" + carpoolid + ";";
        AsyncSQLLongHaul task = new AsyncSQLLongHaul();

        task.execute(sqlComm);
        try {
            int value = task.get(100000, TimeUnit.MILLISECONDS);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /*
    insert into passenger_carpool
    pc_passengername = user_name, pc_carpoolid = carpoolid, pc_status = 0
    need to set pc_datetime as well
     */
    public static int declineSearch(String user_name, int carpoolid) {
        String sqlComm =  "insert into passenger_carpool (pc_passengername, pc_carpoolid, pc_status) values ('" + user_name + "', "
                + carpoolid + ", 0);";
        AsyncSQLLongHaul task = new AsyncSQLLongHaul();

        task.execute(sqlComm);
        try {
            int value = task.get(100000, TimeUnit.MILLISECONDS);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /*
    insert into passenger_carpool
    pc_passengername = user_name, pc_carpoolid = carpoolid, pc_status = 1
    need to set pc_datetime as well
     */
    public static int interestedSearch(String user_name, int carpoolid) {
        String sqlComm =  "insert into passenger_carpool (pc_passengername, pc_carpoolid, pc_status) values ('" + user_name + "', "
                + carpoolid + ", 1);";
        AsyncSQLLongHaul task = new AsyncSQLLongHaul();

        task.execute(sqlComm);
        try {
            int value = task.get(100000, TimeUnit.MILLISECONDS);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /*
    insert into passenger_carpool
    pc_passengername = user_name, pc_carpoolid = carpoolid, pc_status = 2
    need to set pc_datetime as well
    !!!IMPORTANT: ALSO update created_carpool set cc_passengerconfirmed = cc_passengerconfirmed + 1 where cc_id = carpoolid
     */
    public static int confirmSearch(String user_name, int carpoolid) {
        String sqlComm =  "insert into passenger_carpool (pc_passengername, pc_carpoolid, pc_status) values ('" + user_name + "', "
                + carpoolid + ", 0);";
        AsyncSQLLongHaul task = new AsyncSQLLongHaul();

        task.execute(sqlComm);
        try {
            int value = task.get(100000, TimeUnit.MILLISECONDS);

            confirmPassenger(carpoolid);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    private static int confirmPassenger(int carpoolid) {
        String sqlComm = "update carpool_created set cc_passengerconfirmed = cc_passengerconfirmed + 1 where cc_id=" + carpoolid + ";";
        AsyncSQLLongHaul task = new AsyncSQLLongHaul();

        task.execute(sqlComm);
        try {
            int value = task.get(100000, TimeUnit.MILLISECONDS);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    /*
    delete record in passenger_carpool where pc_passengername = user_name and pc_carpoolid = carpoolid and pc_status = 1
     */
    public static int deleteInterested(String user_name, int carpoolid) {
        String sqlComm = "delete from passenger_carpool where pc_passengername = '"+ user_name + "' and pc_carpoolid =" + carpoolid + " and pc_status = 1;";
        AsyncSQLLongHaul task = new AsyncSQLLongHaul();

        task.execute(sqlComm);
        try {
            int value = task.get(100000, TimeUnit.MILLISECONDS);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /*
    update passenger_carpool set pc_aboard = 1 where pc_passengername = user_name and pc_carpoolid = carpoolid
    !!!IMPORTANT: ALSO update created_carpool set cc_passengeraboard = cc_passengeraboard + 1 where cc_id = carpoolid
     */
    public static int confirmInterested(String user_name, int carpoolid) {
        String sqlComm = "update passenger_carpool set pc_aboard = 1 where pc_passengername = '" + user_name + "' and pc_carpoolid=" + carpoolid + ";";
        AsyncSQLLongHaul task = new AsyncSQLLongHaul();

        task.execute(sqlComm);
        try {
            int value = task.get(100000, TimeUnit.MILLISECONDS);
            aboardPassenger(carpoolid);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    private static int aboardPassenger(int carpoolid) {
        String sqlComm = "update carpool_created set cc_passengeraboard = cc_passengeraboard + 1 where cc_id=" + carpoolid + ";";
        AsyncSQLLongHaul task = new AsyncSQLLongHaul();

        task.execute(sqlComm);
        try {
            int value = task.get(100000, TimeUnit.MILLISECONDS);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    /*
    update passenger_carpool set pc_status = 2 where pc_passengername = user_name and pc_carpoolid = carpoolid
    !!!IMPORTANT: ALSO update created_carpool set cc_passengerconfirmed = cc_passengerconfirmed + 1 where cc_id = carpoolid
     */
    public static int aboardConfirmed(String user_name, int carpoolid) {
        String sqlComm = "update passenger_carpool set pc_status = 2 where pc_passengername = '" + user_name + "' and pc_carpoolid=" + carpoolid + ";";
        AsyncSQLLongHaul task = new AsyncSQLLongHaul();

        task.execute(sqlComm);
        try {
            int value = task.get(100000, TimeUnit.MILLISECONDS);
            confirmPassenger(carpoolid);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /*
    in passenger_carpool with pc_passengername = user_name and pc_carpoolid = carpoolid
    if pc_paid = 1  return true;
    else   return false;
     */
    public static int reviewCheck(String user_name, int carpoolid) {
        String sqlComm = "select pc_paid from passenger_carpool where pc_passengername = '" + user_name + "' and pc_carpoolid = "  + carpoolid + ";";
        AsyncSelectOnlyValue task = new AsyncSelectOnlyValue();
        task.execute(sqlComm);
        try {
            Object value = task.get(100000, TimeUnit.MILLISECONDS);
            if (value != null) {
                if (value.equals(1)) {
                    return 0;
                }
                else return 1;
            } else {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static void main(String[] args) {
    }
}
