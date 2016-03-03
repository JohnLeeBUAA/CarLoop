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
import android.widget.Toast;

public class DriverVehicleInfo extends Footer {

    private String type;
    private Vehicle vehicle;
    private EditText license;
    private EditText manufacturer;
    private EditText model;
    private EditText plate;
    private EditText mileage;
    private EditText capacity;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup vg = (ViewGroup) findViewById(R.id.content);
        ViewGroup.inflate(this, R.layout.activity_driver_vehicle_info, vg);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        type = getIntent().getStringExtra("type");

        license = (EditText) findViewById(R.id.license_addvehicleinfo);
        manufacturer = (EditText) findViewById(R.id.manufacture_addvehicleinfo);
        model = (EditText) findViewById(R.id.model_addvehicleinfo);
        plate = (EditText) findViewById(R.id.plate_addvehicleinfo);
        mileage = (EditText) findViewById(R.id.mileage_addvehicleinfo);
        capacity = (EditText) findViewById(R.id.capacity_addvehicleinfo);
        button = (Button) findViewById(R.id.button_addvehicleinfo);

        if(type.equals("Add")) {
            this.setTitle("Add Driver And Vehicle Info");
            button.setText("Add");
        }
        else if(type.equals("Edit")){
            this.setTitle("Edit Driver And Vehicle Info");
            button.setText("Update");
            vehicle = Vehicle.getVehicle(GlobalVariables.user_id);
            license.setText(vehicle.getV_driverlicense());
            manufacturer.setText(vehicle.getV_manufacturer());
            model.setText(vehicle.getV_model());
            plate.setText(vehicle.getV_plate());
            mileage.setText(Integer.toString(vehicle.getV_mileage()));
            capacity.setText(Integer.toString(vehicle.getV_capacity()));
        }
    }

    public void addVehicle(View view) {
        if(validate()) {
            if(type.equals("Add")) {
                if(Vehicle.addVehicle(GlobalVariables.user_id, license.getText().toString(), manufacturer.getText().toString(),
                        model.getText().toString(), plate.getText().toString(), Integer.parseInt(mileage.getText().toString()), Integer.parseInt(capacity.getText().toString()))) {
                    User.setDriver(GlobalVariables.user_id);
                    SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("user_identity", GlobalVariables.user_identity);
                    editor.commit();
                    Toast.makeText(this, "Add driver and vehicle info success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, CarpoolNew.class);
                    intent.putExtra("type", "Create");
                    startActivity(intent);
                }
            }
            else if(type.equals("Edit")) {
                if(Vehicle.updateVehicle(GlobalVariables.user_id, license.getText().toString(), manufacturer.getText().toString(),
                        model.getText().toString(), plate.getText().toString(), Integer.parseInt(mileage.getText().toString()), Integer.parseInt(capacity.getText().toString()))) {
                    Toast.makeText(this, "Driver and vehicle info updated", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private boolean validate() {
        String license_val = license.getText().toString();
        String manufacturer_val = manufacturer.getText().toString();
        String model_val = model.getText().toString();
        String plate_val = plate.getText().toString();
        String mileage_val = mileage.getText().toString();
        String capacity_val = capacity.getText().toString();
        if(license_val.equals("") || manufacturer_val.equals("") || model_val.equals("") ||
                plate_val.equals("") || mileage_val.equals("") || capacity_val.equals("")) {
            Toast.makeText(this, "Input cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            int mileage_int = Integer.parseInt(mileage_val);
            int capacity_int = Integer.parseInt(capacity_val);
            if(mileage_int < 0 || capacity_int < 0) {
                Toast.makeText(this, "Mileage and capacity value must bigger than zero", Toast.LENGTH_SHORT).show();
                return false;
            }
            else if((type.equals("Add") && Vehicle.existLicense(license_val)) ||
                    (type.equals("Edit") && (!license_val.equals(vehicle.getV_driverlicense())) && Vehicle.existLicense(license_val))) {
                Toast.makeText(this, "License: " + license_val + " already exist", Toast.LENGTH_SHORT).show();
                return false;
            }
            else {
                return true;
            }
        }
    }

}
