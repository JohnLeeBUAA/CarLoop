package com.buf.carloop;

/**
 * Created by zijin on 16/02/16.
 */
public class User {

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

    public static boolean existUsername(String username) {
        if(username.equals("li")) {
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean existEmail(String email) {
        if(email.equals("a@a.com")) {
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean signUp(String username, String password, String email) {
        GlobalVariables.user_id = 1;
        GlobalVariables.user_identity = 0;
        return true;
    }
}
