package com.buf.database;

import android.os.AsyncTask;

/**
 * Created by xin on 2016/2/27.
 */
public class AsyncInsertBlob extends AsyncTask<String, Void, Boolean> {
    public AsyncInsertBlob() {}
    @Override
    protected Boolean doInBackground(String... params) {
        // Sql create user operation
        SqlCommond sqlCommond = new SqlCommond();
        boolean value = sqlCommond.insertBlob(params[0], params[1].getBytes());
        return value;
    }
}