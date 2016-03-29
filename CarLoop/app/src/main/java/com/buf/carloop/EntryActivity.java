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
            if(GlobalVariables.user_identity == 1) {
                Intent intent = new Intent(this, CarpoolNew.class);
                intent.putExtra("type", "Create");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
            else {
                Intent intent = new Intent(this, CarpoolNew.class);
                intent.putExtra("type", "Search");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }
    }
}
