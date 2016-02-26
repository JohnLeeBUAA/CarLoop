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
    private String u_avatar;
    private double u_rate;
    private String u_gender;
    private String u_phone;
    private String u_description;

    /*
    select u_id, u_name, u_password, u_identity from user where u_name = username
    if username does not exist, return 0
    if username exist but password does not match, return 1
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
    create new user
    MD5 encrypt password
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
