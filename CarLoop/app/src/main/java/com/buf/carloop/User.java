package com.buf.carloop;

import android.os.AsyncTask;

import com.buf.database.SqlCommond;
import com.buf.database.testAsyncTask;

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
        /*
        String sqlSelect = "select u_password from user where u_name = '" + username + "';";
        SqlCommond sqlCommond = new SqlCommond();
        Object value = sqlCommond.selectOnlyValue(sqlSelect);
        */
        signInSQL task = new signInSQL();
        task.execute(username);
        try {
            Object value = task.get(5000, TimeUnit.MILLISECONDS);
            if (value == null) {
                return 0;
            } else if (!value.equals(password)) {
                return 1;
            } else {
                GlobalVariables.user_id = 1;
                GlobalVariables.user_identity = 2;
                return 2;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*
        if(!username.equals("li")) {
            return 0;
        }
        else if(!password.equals("123")) {
            return 1;
        }
        else {
            GlobalVariables.user_id = 1;
            GlobalVariables.user_identity = 2;
            return 2;
        }
        */
        return 0;
    }
    private static class signInSQL extends AsyncTask<String, Void, Object>  {
        public signInSQL(){}
        @Override
        public Object doInBackground(String... username) {
            String sqlSelect = "select u_password from user where u_name = '" + username[0] + "';";
            SqlCommond sqlCommond = new SqlCommond();
            Object value = sqlCommond.selectOnlyValue(sqlSelect);
            System.out.println(username[0]);
            System.out.println(value);
            return value;
        }
    }
    /*
    search username only
     */
    public static boolean existUsername(String username) {
        existUsernameSQL task = new existUsernameSQL();
        task.execute(username);
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

    private static class existUsernameSQL extends AsyncTask<String, Void, Object> {
        public existUsernameSQL() {}

        protected Object doInBackground(String... params) {
            String sqlSelect = "select u_name from user where u_name = '" + params[0] + "';";
            SqlCommond sqlCommond = new SqlCommond();
            Object value = sqlCommond.selectOnlyValue(sqlSelect);
            return value;
        }

    }
    /*
    search email only
     */
    public static boolean existEmail(String email) {
        existEmailSQL task = new existEmailSQL();
        task.execute(email);
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

    private static class existEmailSQL extends AsyncTask<String, Void, Object> {
        public existEmailSQL() {}

        protected Object doInBackground(String... params) {
            String sqlSelect = "select u_email from user where u_email = '" + params[0] + "';";
            SqlCommond sqlCommond = new SqlCommond();
            Object value = sqlCommond.selectOnlyValue(sqlSelect);
            System.out.println("existEmailSQL:" + value);
            return value;
        }

    }
    /*
    select u_email from user where u_name = username
    no record, return 0
    u_email != email, return 1
    valid return 2
     */
    public static int matchEmail(String username, String email) {
        return 2;
    }

    /*
    create new user
    MD5 encrypt password before store to DB
    u_rate = 0
    after insert, MUST SET GlobalVariables.user_id = last inserted id, GlobalVariables.user_identity = 0
     */
    public static boolean signUp(String username, String password, String email) {
        GlobalVariables.user_id = 1;
        GlobalVariables.user_identity = 0;

        // Sql create user operation
        signUpSQL task = new signUpSQL();
        task.execute(username, password, email);
        try {
            boolean value = task.get(5000, TimeUnit.MILLISECONDS);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static class signUpSQL extends AsyncTask <String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            // Sql create user operation
            String sqlCreate = "insert into user (u_name, u_password, u_email) values ('" + params[0] + "', '"
                    + params[1] + "', '" + params[2] + "');";
            SqlCommond sqlCommond = new SqlCommond();
            boolean value = sqlCommond.longHaul(sqlCreate);
            return value;
        }
    }
    /*
    update user on u_id == user_id
     */
    public static boolean updateUser(int user_id, byte[] avatar, String gender, String phone, String description) {
        return true;
    }

    /*
    check if password is correct with user_id
    MD5 encrypt password before compare with u_password in DB
    return true if match, false if not
     */
    public static boolean checkPassword(int user_id, String password) {
        return true;
    }

    /*
    update password with u_id == user_id
    MD5 encrypt new_password before update in DB
     */
    public static boolean updatePassword(int user_id, String new_password) {
        return true;
    }

    /*
    update password with u_name == username
    MD5 encrypt temp_password before update in DB
     */
    public static boolean retrievePassword(String username, String temp_password) {
        return true;
    }

    /*
    search user with u_id == user_id
     */
    public static User getUser(int user_id) {
        User user = new User();
//        user.setU_avatar(null);
//        user.setU_gender("male");
//        user.setU_description("Some description about me");
//        user.setU_phone("123-456-7890");
        user.setU_name("Billie Joe");
        user.setU_email("something@somewhere.com");

        return user;
    }

    /*
    update u_identity = 1 on u_id == user_id
    MUST SET GlobalVariables.user_identity = 1;
     */
    public static boolean setDriver(int user_id) {
        GlobalVariables.user_identity = 1;
        return true;
    }

    public static boolean updateUser(int user_id, String avatar, String gender, String phone, String description) {
        updateUserSQL task = new updateUserSQL();
        task.execute(Integer.toString(user_id), avatar, gender, phone, description);
        try {
            boolean value = task.get(5000, TimeUnit.MILLISECONDS);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static class updateUserSQL extends AsyncTask<String, Void, Boolean> {
        public updateUserSQL(){}
        @Override
        public Boolean doInBackground(String... params) {
            String sqlSelect = "update user set  u_avatar= '" + params[1] + "'," +
                    "u_gender='" + params[2] + "', " +
                    "u_phone='" + params[3] + "', " +
                    "u_description='" + params[4] + "', " +
                    "where u_id=" + params[0] + ";";
            SqlCommond sqlCommond = new SqlCommond();
            Boolean value = sqlCommond.longHaul(sqlSelect);
            System.out.println(params[0]);
            System.out.println(value);
            return value;
        }
    }
}
