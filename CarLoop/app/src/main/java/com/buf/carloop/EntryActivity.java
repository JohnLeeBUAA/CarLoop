package com.buf.carloop;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class EntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        // read stored record
        SharedPreferences sharedPref = getSharedPreferences("CarLoopPref", Context.MODE_PRIVATE);
        String user_name = sharedPref.getString("user_name", "");
        int user_identity = sharedPref.getInt("user_identity", -1);

        if(user_name.equals("") || user_identity == -1) {
            Intent intent = new Intent(this, SignIn.class);
            startActivity(intent);
        }
        else {
            GlobalVariables.user_name = user_name;
            GlobalVariables.user_identity = user_identity;
            Intent intent = new Intent(this, CarpoolList.class);
            startActivity(intent);
        }
    }
}
