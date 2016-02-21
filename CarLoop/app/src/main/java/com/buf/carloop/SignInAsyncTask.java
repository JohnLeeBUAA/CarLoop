package com.buf.carloop;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.lingyun.myapplication.backend.userEntityApi.UserEntityApi;
import com.example.lingyun.myapplication.backend.userEntityApi.model.UserEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lingyun on 2/21/2016.
 */
public class SignInAsyncTask extends AsyncTask<Void, Void, List<UserEntity>> {
    private Context context;
    private static final String LOG_TAG = EndpointsAsyncTask.class.getSimpleName();

        SignInAsyncTask(Context context) {
            this.context = context;
            }

    @Override
    protected List<UserEntity> doInBackground(Void... paras) {
        UserEntityApi userEntityApiService = APIService.UserEntityApiService;
        List<UserEntity> list = new ArrayList<>();
        try {
            list = userEntityApiService.list().execute().getItems();
        } catch (Exception e) {
            Log.e(SignIn.class.getSimpleName(), "Error ", e);
        }
        return list;
    }

    @Override
    protected void onPostExecute(List<UserEntity> result) {
            for (UserEntity user : result) {
            Toast.makeText(context, user.getUName() + " : " + user.getUPassword(), Toast.LENGTH_LONG).show();
            }
    }
}