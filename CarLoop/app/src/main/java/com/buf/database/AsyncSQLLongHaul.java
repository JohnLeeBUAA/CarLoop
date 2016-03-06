package com.buf.database;

import android.os.AsyncTask;

/**
 * Created by xin on 2016/2/27.
 */
public class AsyncSQLLongHaul extends AsyncTask<String, Void, Boolean> {
    public AsyncSQLLongHaul() {}
    @Override
    protected Boolean doInBackground(String... params) {
        // Sql create user operation
        SqlCommond sqlCommond = new SqlCommond();
        boolean value = false;
        try {
            value = sqlCommond.longHaul(params[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
    }
}
