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
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(this, CarpoolNew.class);
            intent.putExtra("type", "Demand");
            startActivity(intent);
        }
    }

    public void search(View view) {
        Intent intent = new Intent(this, CarpoolNew.class);
        intent.putExtra("type", "Search");
        startActivity(intent);
    }

    public void list(View view) {
        Intent intent = new Intent(this, CarpoolList.class);
        intent.putExtra("type", "Confirmed");
        startActivity(intent);
    }

    public void message(View view) {
        Intent intent = new Intent(this, Message.class);
        startActivity(intent);
    }

    public void settings(View view) {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
}
