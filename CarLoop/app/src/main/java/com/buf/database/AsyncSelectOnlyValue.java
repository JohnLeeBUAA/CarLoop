package com.buf.database;

import android.os.AsyncTask;

/**
 * Created by xin on 2016/2/27.
 */
public class AsyncSelectOnlyValue extends AsyncTask<String, Void, Object> {
    public AsyncSelectOnlyValue() {}

    @Override
    protected Object doInBackground(String... params) {
        SqlCommond sqlCommond = new SqlCommond();
        Object value = sqlCommond.selectOnlyValue(params[0]);
        System.out.println(value);
        return value;
    }
}
