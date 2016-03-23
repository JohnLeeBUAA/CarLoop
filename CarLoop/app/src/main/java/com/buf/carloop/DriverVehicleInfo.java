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
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class DriverVehicleInfo extends Footer {

    private String type;
    private Vehicle vehicle;
    private EditText paypal;
    private EditText license;
    private EditText manufacturer;
    private EditText model;
    private EditText plate;
    private EditText mileage;
    private EditText capacity;

    private Button btn;
    private ProgressBar bar;
    private String paypal_val;
    private String license_val;
    private String manufacturer_val;
    private String model_val;
    private String plate_val;
    private String mileage_val;
    private String capacity_val;
    private int mileage_int;
    private int capacity_int;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup vg = (ViewGroup) findViewById(R.id.content);
        ViewGroup.inflate(this, R.layout.activity_driver_vehicle_info, vg);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        type = getIntent().getStringExtra("type");

        paypal = (EditText) findViewById(R.id.paypal_addvehicleinfo);
        license = (EditText) findViewById(R.id.license_addvehicleinfo);
        manufacturer = (EditText) findViewById(R.id.manufacture_addvehicleinfo);
        model = (EditText) findViewById(R.id.model_addvehicleinfo);
        plate = (EditText) findViewById(R.id.plate_addvehicleinfo);
        mileage = (EditText) findViewById(R.id.mileage_addvehicleinfo);
        capacity = (EditText) findViewById(R.id.capacity_addvehicleinfo);
        btn = (Button) findViewById(R.id.button_addvehicleinfo);
        bar = (ProgressBar) findViewById(R.id.bar_addvehicleinfo);

        bar.setVisibility(View.GONE);

        if(type.equals("Add")) {
            this.setTitle("Add Driver And Vehicle Info");
            btn.setText("Add");
        }
        else if(type.equals("Edit")){
            this.setTitle("Edit Driver And Vehicle Info");
            btn.setText("Update");
            vehicle = Vehicle.getVehicle(GlobalVariables.user_name);
            if(vehicle != null) {
                license.setText(vehicle.getV_driverlicense());
                manufacturer.setText(vehicle.getV_manufacturer());
                model.setText(vehicle.getV_model());
                plate.setText(vehicle.getV_plate());
                mileage.setText(Integer.toString(vehicle.getV_mileage()));
                capacity.setText(Integer.toString(vehicle.getV_capacity()));
            }
        }
    }

    public void addVehicle(View view) {
        btn.setVisibility(View.GONE);
        bar.setVisibility(View.VISIBLE);
        if(validate()) {
            if(type.equals("Add")) {
                int status = Vehicle.addVehicle(GlobalVariables.user_name, paypal_val, license_val, manufacturer_val,
                        model_val, plate_val, mileage_int, capacity_int);
                if (status == 0) {
                    User.setDriver(GlobalVariables.user_name);
                    SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("user_identity", GlobalVariables.user_identity);
                    editor.commit();
                    Toast.makeText(this, "Add driver and vehicle info success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, CarpoolNew.class);
                    intent.putExtra("type", "Create");
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                else if (status == 3) {
                    Toast.makeText(this, "License: " + license_val + " already exist", Toast.LENGTH_SHORT).show();
                    btn.setVisibility(View.VISIBLE);
                    bar.setVisibility(View.GONE);
                }
                else if (status == 5) {
                    Toast.makeText(this, "Plate: " + plate_val + " already exist", Toast.LENGTH_SHORT).show();
                    btn.setVisibility(View.VISIBLE);
                    bar.setVisibility(View.GONE);
                }
                else if(status == 6) {
                    Toast.makeText(this, "Paypal: " + paypal_val + " already exist", Toast.LENGTH_SHORT).show();
                    btn.setVisibility(View.VISIBLE);
                    bar.setVisibility(View.GONE);
                }
                else {
                    Toast.makeText(this, "Network error", Toast.LENGTH_SHORT).show();
                    btn.setVisibility(View.VISIBLE);
                    bar.setVisibility(View.GONE);
                }
            }
            else if(type.equals("Edit")) {
                int status = Vehicle.updateVehicle(GlobalVariables.user_name, paypal_val, license_val, manufacturer_val,
                        model_val, plate_val, mileage_int, capacity_int);
                if(status == 0) {
                    Toast.makeText(this, "Driver and vehicle info updated", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else if(status == 3) {
                    Toast.makeText(this, "License: " + license_val + " already exist", Toast.LENGTH_SHORT).show();
                    btn.setVisibility(View.VISIBLE);
                    bar.setVisibility(View.GONE);
                }
                else if (status == 5) {
                    Toast.makeText(this, "Plate: " + plate_val + " already exist", Toast.LENGTH_SHORT).show();
                    btn.setVisibility(View.VISIBLE);
                    bar.setVisibility(View.GONE);
                }
                else if(status == 6) {
                    Toast.makeText(this, "Paypal: " + paypal_val + " already exist", Toast.LENGTH_SHORT).show();
                    btn.setVisibility(View.VISIBLE);
                    bar.setVisibility(View.GONE);
                }
                else {
                    Toast.makeText(this, "Network error", Toast.LENGTH_SHORT).show();
                    btn.setVisibility(View.VISIBLE);
                    bar.setVisibility(View.GONE);
                }
            }
        }
        else {
            btn.setVisibility(View.VISIBLE);
            bar.setVisibility(View.GONE);
        }
    }

    private boolean validate() {
        paypal_val = paypal.getText().toString();
        license_val = license.getText().toString();
        manufacturer_val = manufacturer.getText().toString();
        model_val = model.getText().toString();
        plate_val = plate.getText().toString();
        mileage_val = mileage.getText().toString();
        capacity_val = capacity.getText().toString();
        if(paypal_val.equals("") || license_val.equals("") || manufacturer_val.equals("") || model_val.equals("") ||
                plate_val.equals("") || mileage_val.equals("") || capacity_val.equals("")) {
            Toast.makeText(this, "Input cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            mileage_int = Integer.parseInt(mileage_val);
            capacity_int = Integer.parseInt(capacity_val);
            if(mileage_int < 0 || capacity_int < 0) {
                Toast.makeText(this, "Mileage and capacity value must bigger than zero", Toast.LENGTH_SHORT).show();
                return false;
            }
            else {
                return true;
            }
        }
    }

}
