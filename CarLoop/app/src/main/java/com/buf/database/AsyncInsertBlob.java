package com.buf.database;

import android.os.AsyncTask;

import java.util.Objects;

/**
 * Created by xin on 2016/2/27.
 */
public class AsyncInsertBlob extends AsyncTask<Object, Void, Boolean> {
    public AsyncInsertBlob() {}
    @Override
    protected Boolean doInBackground(Object... params) {
        // Sql create user operation
        SqlCommond sqlCommond = new SqlCommond();
        boolean value = sqlCommond.insertBlob((String) params[0], (byte[]) params[1]);
        return value;
    }
}