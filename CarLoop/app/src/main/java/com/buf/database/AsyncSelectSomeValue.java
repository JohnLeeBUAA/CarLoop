package com.buf.database;

import android.os.AsyncTask;

import java.util.Vector;

/**
 * Created by xin on 2016/2/27.
 */
public class AsyncSelectSomeValue extends AsyncTask<String, Void, Vector> {
    public AsyncSelectSomeValue(){}

    @Override
    protected Vector doInBackground(String... params) {
        SqlCommond sqlCommond = new SqlCommond();
        Vector<Object> vector = sqlCommond.selectSomeValue(params[0]);
        if (vector == null) System.out.println("vector is null");
        return vector;
    }
}
