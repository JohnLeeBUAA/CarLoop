package com.buf.carloop;

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
    }

    /*
    search username only
     */
    public static boolean existUsername(String username) {
        if(username.equals("li")) {
            return true;
        }
        else {
            return false;
        }
    }

    /*
    search email only
     */
    public static boolean existEmail(String email) {
        if(email.equals("a@a.com")) {
            return true;
        }
        else {
            return false;
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
        return true;
    }

    /*
    update user on u_id == user_id
     */
    public static boolean updateUser(int user_id, String avatar, String gender, String phone, String description) {
        return true;
    }
}
