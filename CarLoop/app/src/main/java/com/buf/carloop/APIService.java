package com.buf.carloop;

import com.example.lingyun.myapplication.backend.testEntityApi.TestEntityApi;
import com.example.lingyun.myapplication.backend.userEntityApi.UserEntityApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

/**
 * Created by Lingyun on 2/20/2016.
 */
public class APIService {
    private static UserEntityApi.Builder builder = new UserEntityApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
//            .setRootUrl("http://10.0.2.2:8080/_ah/api/");
            .setRootUrl("https://carloop-1226.appspot.com/_ah/api/");
    public static final UserEntityApi userEntityApiService = builder.build();
    private static TestEntityApi.Builder builder_TestEntityApi = new TestEntityApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
//            .setRootUrl("http://10.0.2.2:8080/_ah/api/");
            .setRootUrl("https://carloop-1226.appspot.com/_ah/api/");
    public static final TestEntityApi testEntityApiService  = builder_TestEntityApi.build();

}
