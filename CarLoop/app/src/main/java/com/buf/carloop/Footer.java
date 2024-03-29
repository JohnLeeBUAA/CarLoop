package com.buf.carloop;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class Footer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_footer);
    }

    public void add(View view) {
        if(GlobalVariables.user_identity == 1) {
            Intent intent = new Intent(this, CarpoolNew.class);
            intent.putExtra("type", "Create");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(this, CarpoolNew.class);
            intent.putExtra("type", "Demand");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    public void search(View view) {
        if(GlobalVariables.user_identity == 1) {
            //driver search demanded carpools
            Intent intent = new Intent(this, CarpoolNew.class);
            intent.putExtra("type", "SearchDemanded");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else {
            //passenger search created carpools
            Intent intent = new Intent(this, CarpoolNew.class);
            intent.putExtra("type", "Search");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    public void list(View view) {
        Intent intent = new Intent(this, CarpoolList.class);
        if(GlobalVariables.user_identity == 1) {
            //display created list for driver
            intent.putExtra("type", "Created");
        }
        else {
            //display confirmed list for passenger
            intent.putExtra("type", "Confirmed");
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void message(View view) {
        Intent intent = new Intent(this, MessageList.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void settings(View view) {
        Intent intent = new Intent(this, Settings.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
