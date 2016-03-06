package com.buf.database;

import android.os.AsyncTask;

/**
 * Created by xin on 2016/2/27.
 */
public class AsyncSQLLongHaul extends AsyncTask<String, Void, Object> {
    public AsyncSQLLongHaul() {}
    @Override
    protected Object doInBackground(String... params) {
        // Sql create user operation
        SqlCommond sqlCommond = new SqlCommond();
        Object value = false;
        try {
            value = sqlCommond.longHaul(params[0]);
        } catch (Exception e) {
            e.printStackTrace();
            value = e.getMessage();
        }
        return value;
    }
}
