package com.buf.carloop;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.lingyun.myapplication.backend.userEntityApi.UserEntityApi;
import com.example.lingyun.myapplication.backend.userEntityApi.model.UserEntity;

/**
 * Created by Lingyun on 2/21/2016.
 */
public class SignUpAsyncTask extends AsyncTask<UserEntity, Void, Boolean> {
    private Context context;
    private static final String LOG_TAG = SignUpAsyncTask.class.getSimpleName();

    SignUpAsyncTask(Context context) {this.context = context;}
    SignUpAsyncTask() {}

    @Override
    protected Boolean doInBackground(UserEntity... newUser) {
        UserEntityApi userEntityApiService = APIService.userEntityApiService;
        //try to insert to database
        try {
            userEntityApiService.insert(newUser[0]).execute();
        } catch (Exception e) {
            Log.e(LOG_TAG, "Error ", e);
            return false;
        }
        return true;
    }
}