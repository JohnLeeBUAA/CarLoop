package com.buf.database;

import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by xin on 2016/2/22.
 */
public class testAsyncTask extends AsyncTask<String, Void, Object> {

    private Context context;

    public testAsyncTask(Context context) {
        this.context = context;
    }
    public testAsyncTask(){}

    public Object doInBackground(String... username) {
        String sqlSelect = "select u_password from user where u_name = '" + username[0] + "';";
        SqlCommond sqlCommond = new SqlCommond();
        Object value = sqlCommond.selectOnlyValue(sqlSelect);
        System.out.println(username[0]);
        System.out.println(value);
        return value;
    }
}
