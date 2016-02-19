package com.buf.carloop;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

public class DriverVehicleInfo extends Footer {

    private String type;
    private Vehicle vehicle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup vg = (ViewGroup) findViewById(R.id.content);
        ViewGroup.inflate(this, R.layout.activity_driver_vehicle_info, vg);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        type = getIntent().getStringExtra("type");

        if(type.equals("Add")) {
            this.setTitle("Add Driver And Vehicle Info");
        }
        else {
            this.setTitle("Edit Driver And Vehicle Info");
        }
    }

}
