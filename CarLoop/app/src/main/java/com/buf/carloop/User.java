package com.buf.carloop;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;

import com.buf.database.AsyncInsertBlob;
import com.buf.database.AsyncSQLLongHaul;
import com.buf.database.AsyncSelectBlob;
import com.buf.database.AsyncSelectOnlyNote;
import com.buf.database.AsyncSelectOnlyNote;
import com.buf.database.AsyncSelectOnlyValue;
import com.buf.database.SqlCommond;
import com.buf.database.testAsyncTask;

import java.nio.charset.Charset;
import java.util.Objects;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 * Created by zijin on 16/02/16.
 */
public class User {

    private int u_id;
    private String u_name;
    private String u_password; //MD5 encrypted
    private String u_email;
    private int u_identity;
    private byte[] u_avatar;
    private double u_rate;
    private String u_gender;
    private String u_phone;
    private String u_description;

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getU_password() {
        return u_password;
    }

    public void setU_password(String u_password) {
        this.u_password = u_password;
    }

    public String getU_email() {
        return u_email;
    }

    public void setU_email(String u_email) {
        this.u_email = u_email;
    }

    public int getU_identity() {
        return u_identity;
    }

    public void setU_identity(int u_identity) {
        this.u_identity = u_identity;
    }

    public byte[] getU_avatar() {
        return u_avatar;
    }

    public void setU_avatar(byte[] u_avatar) {
        this.u_avatar = u_avatar;
    }

    public double getU_rate() {
        return u_rate;
    }

    public void setU_rate(double u_rate) {
        this.u_rate = u_rate;
    }

    public String getU_gender() {
        return u_gender;
    }

    public void setU_gender(String u_gender) {
        this.u_gender = u_gender;
    }

    public String getU_phone() {
        return u_phone;
    }

    public void setU_phone(String u_phone) {
        this.u_phone = u_phone;
    }

    public String getU_description() {
        return u_description;
    }

    public void setU_description(String u_description) {
        this.u_description = u_description;
    }

    /*
    select u_id, u_name, u_password, u_identity from user where u_name = username
    if username does not exist, return 0
    if username exist but password does not match, return 1  you need to MD5 encrypt password before compare the password in DB
    valid return 2, MUST SET GlobalVariables.user_id = u_id, GlobalVariables.user_identity = u_identity
     */
    public static int signIn(String username, String password) {
        String sqlComm = "select * from user where u_name = '" + username + "';";
        AsyncSelectOnlyNote task = new AsyncSelectOnlyNote();
        task.execute(sqlComm);
        try {
            Vector<Object> value = task.get(10000, TimeUnit.MILLISECONDS);
            if (value == null) {
                return 1;
            } else if (!value.elementAt(2).equals(password)) {
                return 2;
            } else {
                GlobalVariables.user_identity = Integer.parseInt((String)value.elementAt(4));
                GlobalVariables.user_name = username;
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /*
    search username only
     */
    public static int existUsername(String username) {
        String sqlComm = "select u_name from user where u_name = '" + username + "';";
        AsyncSelectOnlyValue task = new AsyncSelectOnlyValue();
        task.execute(sqlComm);
        try {
            Object value = task.get(10000, TimeUnit.MILLISECONDS);
            if(value == null) {
                return 0; // username does not exit
            }
            else {
                return 1; // username exits
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }

    /*
    search email only
     */
    public static int existEmail(String email) {
        String sqlComm = "select u_email from user where u_email = '" + email + "';";
        AsyncSelectOnlyValue task = new AsyncSelectOnlyValue();
        task.execute(sqlComm);
        try {
            Object value = task.get(10000, TimeUnit.MILLISECONDS);
            if(value == null) {
                return 0;  // it is legal
            }
            else {
                return 1;   // email exists
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /*
    select u_email from user where u_name = username
    no record, return 0
    u_email != email, return 1
    valid return 2
     */
    public static int matchEmail(String username, String email) {
        String sqlComm = "select u_email from user where u_name = '" + username + "';";

        AsyncSelectOnlyValue task = new AsyncSelectOnlyValue();
        task.execute(sqlComm);
        try {
            Object value = task.get(10000, TimeUnit.MILLISECONDS);
            if (value == null) {
                return 2; // no record
            } else if (!value.equals(email)) {
                return 1;  // email does not match
            } else {
                return 0;  // match the email
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /*
    create new user
    MD5 encrypt password before store to DB
    u_rate = 0
    after insert, MUST SET GlobalVariables.user_name = username, GlobalVariables.user_identity = 0
     */
    public static int signUp(String username, String password, String email) {
        String sqlComm = "insert into user (u_name, u_password, u_email, u_identity) values ('" + username + "', '"
                + password + "', '" + email + "', '0');";
        // Sql create user operation
        AsyncSQLLongHaul task = new AsyncSQLLongHaul();
        task.execute(sqlComm);
        try {
            GlobalVariables.user_identity = 0;
            GlobalVariables.user_name = username;
            return task.get(10000, TimeUnit.MILLISECONDS);

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /*
    update user on u_name == user_name
     */
    public static int updateUser(String user_name, byte[] avatar, String gender, String phone, String description) {
        String sqlComm = "update user set " +
                "u_gender='" + gender + "', " +
                "u_phone='" + phone + "', " +
                "u_description='" + description + "' " +
                "where u_name='" + user_name + "';";
        int value = 0;
        AsyncSQLLongHaul task = new AsyncSQLLongHaul();
        task.execute(sqlComm);
        try {
            value = task.get(10000, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        insertSQLBlob(user_name, avatar);
        return value;
    }



    private static byte[] selectSQLBlob(String user_name) {
        String sqlComm = "select u_avatar from user where u_name = '" + user_name + "';";
        AsyncSelectBlob task = new AsyncSelectBlob();
        task.execute(sqlComm);
        try {
            byte[] value = task.get(10000, TimeUnit.MILLISECONDS);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static boolean insertSQLBlob(String user_name, byte[] avatar) {
        String sqlComm = "update user set u_avatar= ? where u_name= '" + user_name + "';";
        AsyncInsertBlob task = new AsyncInsertBlob();

        task.execute(sqlComm, avatar);
        try {
            boolean value = task.get(100000, TimeUnit.MILLISECONDS);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /*
    check if password is correct with user_name
    MD5 encrypt password before compare with u_password in DB
    return true if match, false if not
     */
    public static int checkPassword(String user_name, String password) {
        String sqlComm = "select u_password from user where u_name = '" + user_name + "';";
        AsyncSelectOnlyValue task = new AsyncSelectOnlyValue();
        task.execute(sqlComm);
        try {
            Object value = task.get(10000, TimeUnit.MILLISECONDS);
            if (value == null) {
                return 2;   // no record
            } else if (value.equals(password)) {
                return 0;   // match
            }
            else {
                return 1;  // not match
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /*
    update password with u_name == user_name
    MD5 encrypt new_password before update in DB
     */
    public static int updatePassword(String user_name, String new_password, String old_password) {
        String sqlComm = "update user set  u_password= '" + new_password + "' " +
                "where u_name='" + user_name + "' and u_password='" + old_password + "';";
        Log.v("****", sqlComm);
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
    update password with u_name == username
    MD5 encrypt temp_password before update in DB
     */
    public static int retrievePassword(String username, String email, String temp_password) {

        String sqlComm = "update user set  u_password= '" + temp_password + "' " +
                "where u_name='" + username + "' and u_email='" + email + "';";
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
    search user with u_name == user_name
     */
    public static User getUser(String user_name) {
        User user = new User();
        String sqlComm = "select u_name, u_email, u_gender, u_phone, u_description from user where u_name = '" + user_name + "';";

        AsyncSelectOnlyNote task = new AsyncSelectOnlyNote();
        task.execute(sqlComm);
        try {
            Vector value = task.get(10000, TimeUnit.MILLISECONDS);
            if (value == null) {
                return null;
            }
            else {
                user.setU_name((String) value.elementAt(0));
                user.setU_email((String) value.elementAt(1));
                user.setU_avatar(null);
                user.setU_gender((String) value.elementAt(2));
                user.setU_phone((String) value.elementAt(3));
                user.setU_description((String) value.elementAt(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        user.setU_avatar(selectSQLBlob(user_name));
        user.setU_avatar(null);
        return user;
    }


    /*
    update u_identity = 1 on u_name == user_name
    MUST SET GlobalVariables.user_identity = 1;
     */
    public static int setDriver(String user_name) {
        GlobalVariables.user_identity = 1;
        String sqlComm = "update user set  u_identity='1'" +
                " where u_name='" + user_name + "';";
        AsyncSQLLongHaul task = new AsyncSQLLongHaul();
        task.execute(sqlComm);
        try {
            return task.get(10000, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int updateUser(String user_name, String avatar, String gender, String phone, String description) {
        String sqlComm = "update user set  u_avatar= '" + avatar + "'," +
                "u_gender='" + gender + "', " +
                "u_phone='" + phone + "', " +
                "u_description='" + description + "' " +
                "where u_name='" + user_name + "';";
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
