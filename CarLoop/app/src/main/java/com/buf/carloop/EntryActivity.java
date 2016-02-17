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
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        // store user_id if user signed in before
        int user_id = sharedPref.getInt("user_id", -1);
        // store user_identity for the switch function 0 - passenger only; 1 - driver or passenger
        int user_identity = sharedPref.getInt("user_identity", -1);

        if(user_id == -1 || user_identity == -1) {
            Intent intent = new Intent(this, SignIn.class);
            startActivity(intent);
        }
        else {
            GlobalVariables.user_id = user_id;
            GlobalVariables.user_identity = user_identity;
            Intent intent = new Intent(this, CarpoolList.class);
            startActivity(intent);
        }
    }
}
