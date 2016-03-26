package com.buf.carloop;

import com.buf.database.AsyncSQLLongHaul;
import com.buf.database.AsyncSelectOnlyNote;
import com.buf.database.AsyncSelectSomeNote;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import static com.buf.carloop.User.selectSQLBlob;

/**
 * Created by zijin on 11/03/16.
 */
public class PassengerCarpool {
    private String passengername;
    private byte[] passengeravatar;
    private int paid;
    private int pc_id;
    private int carpoolid;
    private int status;
    private int message;
    private int aboard;

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

    public int getPc_id() {
        return pc_id;
    }

    public void setPc_id(int pc_id) {
        this.pc_id = pc_id;
    }

    public int getCarpoolid() {
        return carpoolid;
    }

    public void setCarpoolid(int carpoolid) {
        this.carpoolid = carpoolid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getMessage() {
        return message;
    }

    public void setMessage(int message) {
        this.message = message;
    }

    public int getAboard() {
        return aboard;
    }

    public void setAboard(int aboard) {
        this.aboard = aboard;
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
        String sqlComm = "select * from passenger_carpool where pc_carpoolid = " + carpoolid + " and pc_status = 2 order by pc_datetime desc;";
        List<PassengerCarpool> list = new ArrayList<PassengerCarpool>();
        PassengerCarpool carpool = null;
        AsyncSelectSomeNote task = new AsyncSelectSomeNote();
        task.execute(sqlComm);
        try {
            Vector value_original = task.get(10000, TimeUnit.MILLISECONDS);
            Vector value;
            if (value_original == null) {
                return null;
            } else if (value_original.size() > 0) {
                for (int i = 0; i < value_original.size(); i++) {
                    value = (Vector) value_original.elementAt(i);
                    carpool = new PassengerCarpool();
                    carpool.setPc_id((int) value.elementAt(0));
                    carpool.setPassengername((String) value.elementAt(1));
                    carpool.setCarpoolid((int) value.elementAt(2));
                    carpool.setStatus((int) value.elementAt(3));
                    carpool.setMessage((int) value.elementAt(4));
                    carpool.setPaid((int) value.elementAt(5));
                    carpool.setAboard((int) value.elementAt(6));
                    carpool.setPassengeravatar(selectSQLBlob(carpool.getPassengername()));
                    list.add(carpool);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    /*
    update passenger_carpool set pc_paid = 1 where pc_passengername = passengername and pc_carpoolid = carpoolid
     */
    public static int addPayment(String passengername, int carpoolid) {
        String sqlComm = "update passenger_carpool set pc_paid = 1 where pc_passengername = '" + passengername + "' and pc_carpoolid = "  + carpoolid + ";";
        AsyncSQLLongHaul task = new AsyncSQLLongHaul();

        task.execute(sqlComm);
        try {
            int value = task.get(10000, TimeUnit.MILLISECONDS);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public PassengerCarpool(int paid, int aboard) {
        this.paid = paid;
        this.aboard = aboard;
    }

    /*
    select pc_paid, pc_aboard from passengercarpool where pc_passengername = passengername and pc_carpoolid = carpoolid
     */
    public static PassengerCarpool getInfo(String passengername, int carpoolid) {
        String sqlComm = "select * from passenger_carpool where pc_passengername = '" + passengername + "' and pc_carpoolid = " + carpoolid + ";";
        PassengerCarpool carpool = null;
        AsyncSelectOnlyNote task = new AsyncSelectOnlyNote();
        task.execute(sqlComm);
        try {
            Vector value = task.get(10000, TimeUnit.MILLISECONDS);
            if (value.size() == 0) {
                carpool.setPc_id(-1);
                return carpool;
            } else if (value != null) {
                    carpool = new PassengerCarpool();
                    carpool.setPc_id((int) value.elementAt(0));
                    carpool.setPassengername((String) value.elementAt(1));
                    carpool.setCarpoolid((int) value.elementAt(2));
                    carpool.setStatus((int) value.elementAt(3));
                    carpool.setMessage((int) value.elementAt(4));
                    carpool.setPaid((int) value.elementAt(5));
                    carpool.setAboard((int) value.elementAt(6));
            }
            else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            carpool.setPc_id(-1);
            return carpool;
        }
        return carpool;
    }

    /*
    select * from passenger_carpool where pc_status = 2 and pc_carpoolid in (select cc_id from carpool_created where cc_drivername = user_name)
     */
    public static List<PassengerCarpool> getDriversNotifications(String user_name) {
        String sqlComm = "select * from passenger_carpool where pc_status = 2 and pc_carpoolid in (select cc_id from carpool_created where cc_drivername = '" + user_name + "');";
        List<PassengerCarpool> list = new ArrayList<PassengerCarpool>();
        PassengerCarpool carpool = null;
        AsyncSelectSomeNote task = new AsyncSelectSomeNote();
        task.execute(sqlComm);
        try {
            Vector value_original = task.get(10000, TimeUnit.MILLISECONDS);
            Vector value;
            if (value_original == null) {
                return null;
            } else if (value_original.size() > 0) {
                for (int i = 0; i < value_original.size(); i++) {
                    value = (Vector) value_original.elementAt(i);
                    carpool = new PassengerCarpool();
                    carpool.setPc_id((int) value.elementAt(0));
                    carpool.setPassengername((String) value.elementAt(1));
                    carpool.setCarpoolid((int) value.elementAt(2));
                    carpool.setStatus((int) value.elementAt(3));
                    carpool.setMessage((int) value.elementAt(4));
                    carpool.setPaid((int) value.elementAt(5));
                    carpool.setAboard((int) value.elementAt(6));
                    carpool.setPassengeravatar(selectSQLBlob(carpool.getPassengername()));
                    list.add(carpool);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    /*
    return a list of carpools in carpool_created where cc_demander = user_name

    return in PassengerCarpool type:
    pc.setPassengername(cc_drivername)
    pc.setPassengeravatar(cc_drivername's avatar)
    pc.setCarpoolid(cc_id)
    other fields are non-important

    order: the opposite order of insertion
     */
    public static List<PassengerCarpool> getPassengersNotifications(String user_name) {
        String sqlComm = "select cc_id, cc_drivername from carpool_created where cc_demander = '" + user_name + "';";
        List<PassengerCarpool> list = new ArrayList<PassengerCarpool>();
        PassengerCarpool carpool = null;
        AsyncSelectSomeNote task = new AsyncSelectSomeNote();
        task.execute(sqlComm);
        try {
            Vector value_original = task.get(10000, TimeUnit.MILLISECONDS);
            Vector value;
            if (value_original == null) {
                return null;
            } else if (value_original.size() > 0) {
                for (int i = 0; i < value_original.size(); i++) {
                    value = (Vector) value_original.elementAt(i);
                    carpool = new PassengerCarpool();
                    carpool.setPassengername((String) value.elementAt(1));
                    carpool.setCarpoolid((int) value.elementAt(0));
                    carpool.setPassengeravatar(selectSQLBlob(carpool.getPassengername()));
                    list.add(carpool);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}