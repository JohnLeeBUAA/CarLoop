package com.buf.database;

import android.os.AsyncTask;

/**
 * Created by xin on 2016/2/27.
 */
public class AsyncSQLLongHaul extends AsyncTask<String, Void, Integer> {
    public AsyncSQLLongHaul() {}
    @Override
    protected Integer doInBackground(String... params) {
        // Sql create user operation
        SqlCommond sqlCommond = new SqlCommond();
        Integer value  = sqlCommond.longHaul(params[0]);
        return value;
    }
}
