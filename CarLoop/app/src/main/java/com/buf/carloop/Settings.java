package com.buf.carloop;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends Footer {

    private TextView identity_label;
    private LinearLayout switchl;
    private Button manage_vehicle;
    private RadioButton driver;
    private RadioButton passenger;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup vg = (ViewGroup) findViewById(R.id.content);
        ViewGroup.inflate(this, R.layout.activity_settings, vg);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        identity_label = (TextView) findViewById(R.id.identity_label_settings);
        switchl = (LinearLayout) findViewById(R.id.switch_settings);
        manage_vehicle = (Button) findViewById(R.id.manage_vehicle_settings);
        driver = (RadioButton) findViewById(R.id.driver_settings);
        passenger = (RadioButton) findViewById(R.id.passenger_settings);

        if(GlobalVariables.user_identity == 1) {
            identity_label.setText("You are using this app as: Driver");
        }
        else {
            identity_label.setText("You are using this app as: Passenger");
        }

        if(GlobalVariables.user_identity == 0) {
            switchl.setVisibility(View.GONE);
            manage_vehicle.setText("Become A Driver");
        }
        else {
            manage_vehicle.setText("Manage Driver And Vehicle Info");
            if(GlobalVariables.user_identity == 1) {
                driver.setChecked(true);
                passenger.setChecked(false);
            }
            else {
                driver.setChecked(false);
                passenger.setChecked(true);
            }
        }
    }

    public void switchIdentity(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.driver_settings:
                if (checked)
                    GlobalVariables.user_identity = 1;
                    Toast.makeText(this, "Identity changed to: Driver", Toast.LENGTH_SHORT).show();
                    break;
            case R.id.passenger_settings:
                if (checked)
                    Toast.makeText(this, "Identity changed to: Passenger", Toast.LENGTH_SHORT).show();
                    GlobalVariables.user_identity = 2;
                    break;
        }
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("user_identity", GlobalVariables.user_identity);
        editor.commit();
    }

    public void manageVehicle(View view) {
        if(GlobalVariables.user_identity == 0) {
            Intent intent = new Intent(this, DriverVehicleInfo.class);
            intent.putExtra("type", "Add");
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(this, DriverVehicleInfo.class);
            intent.putExtra("type", "Edit");
            startActivity(intent);
        }
    }

    public void manageProfile(View view) {
        Intent intent = new Intent(this, ManageProfile.class);
        startActivity(intent);
    }

    public void changePassword(View view) {
        Intent intent = new Intent(this, ChangePassword.class);
        startActivity(intent);
    }

    public void logOut(View view) {
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("user_id", -1);
        editor.putInt("user_identity", -1);
        editor.commit();
        Intent intent = new Intent(this, SignIn.class);
        startActivity(intent);
    }

    public void test(View view) {
        if(GlobalVariables.user_identity == 0) {
            GlobalVariables.user_identity = 1;
        }
        else if(GlobalVariables.user_identity == 1) {
            GlobalVariables.user_identity = 2;
        }
        else if(GlobalVariables.user_identity == 2) {
            GlobalVariables.user_identity = 0;
        }
    }

}
