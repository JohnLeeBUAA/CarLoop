package com.buf.carloop;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.lingyun.myapplication.backend.userEntityApi.UserEntityApi;
import com.example.lingyun.myapplication.backend.userEntityApi.model.UserEntity;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;

/**
 * Created by Lingyun on 2/21/2016.
 */
public class GetUserEntityAsyncTask extends AsyncTask<String, Void, UserEntity> {
    private Context context;
    private static final String LOG_TAG = EndpointsAsyncTask.class.getSimpleName();

    GetUserEntityAsyncTask(Context context) {
            this.context = context;
            }
    GetUserEntityAsyncTask(){}

    protected UserEntity doInBackground(String... username) {
        UserEntityApi userEntityApiService = APIService.userEntityApiService;
        UserEntity user = null;
        try {
            user = userEntityApiService.get(username[0]).execute();
        }
        catch (Exception e) {
            if(((GoogleJsonResponseException) e).getStatusCode()==404){
                //not found
                return null;
            }
            else {
                Log.e(LOG_TAG, "Error " + e.getMessage(), e);
                return null;
            }
        }
        return user;
    }
}