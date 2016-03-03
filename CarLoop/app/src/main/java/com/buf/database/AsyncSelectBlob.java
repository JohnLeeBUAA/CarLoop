package com.buf.database;

import android.os.AsyncTask;

/**
 * Created by xin on 2016/2/27.
 */
public class AsyncSelectBlob extends AsyncTask<String, Void, byte[]> {
    public AsyncSelectBlob() {}
    @Override
    protected byte[] doInBackground(String... params) {
        // Sql create user operation
        SqlCommond sqlCommond = new SqlCommond();
        byte[] value = sqlCommond.selectBlob(params[0]);
        return value;
    }
}
