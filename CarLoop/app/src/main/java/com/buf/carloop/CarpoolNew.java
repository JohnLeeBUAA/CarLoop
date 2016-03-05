package com.buf.carloop;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.DatePicker;
import android.view.View.OnClickListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.content.DialogInterface.*;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;

public class CarpoolNew extends Footer {

    private String type;
    private Button button;
    private LinearLayout labelarea;
    private LinearLayout textarea;
    private TextView tip;
    private TextView tip2;
    private TextView tip3;
    private TextView tip4;
    private Button btn;
    private Button btn2;
    private Button btn3;
    private Button btn4;

    private String depart_loc_val;
    private double depart_lat_val;
    private double depart_lng_val;
    private String desti_loc_val;
    private double desti_lat_val;
    private double desti_lng_val;
    private Button set_depart_btn;
    private Button set_desti_btn;

    private String date;
    private String time;
    private String date_range;
    private String time_range;

    private EditText maxpassenger;
    private EditText price;
    private String maxpassenger_val;
    private String price_val;

    private int carpoolid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup vg = (ViewGroup) findViewById(R.id.content);
        ViewGroup.inflate(this, R.layout.activity_carpool_new, vg);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        tip = (TextView) findViewById(R.id.tip);
        tip2 = (TextView) findViewById(R.id.tip2);
        tip3 = (TextView) findViewById(R.id.tip3);
        tip4 = (TextView) findViewById(R.id.tip4);
        btn = (Button) findViewById(R.id.btn);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        button = (Button) findViewById(R.id.add_carpool_new);
        labelarea = (LinearLayout) findViewById(R.id.labelarea_carpool_new);
        textarea = (LinearLayout) findViewById(R.id.textarea_carpool_new);

        set_depart_btn = (Button) findViewById(R.id.setdepart_carpoolnew);
        set_desti_btn = (Button) findViewById(R.id.setdesti_carpoolnew);

        maxpassenger = (EditText) findViewById(R.id.maxpassenger_carpoolnew);
        price = (EditText) findViewById(R.id.price_carpoolnew);

        type = getIntent().getStringExtra("type");

        if(type.equals("Create")) {
            this.setTitle("Create Carpool");
            button.setText("Create");
        }
        else if(type.equals("Demand")) {
            this.setTitle("Demand Carpool");
            button.setText("Demand");
            labelarea.setVisibility(View.GONE);
            textarea.setVisibility(View.GONE);
        }
        else if(type.equals("Search")) {
            this.setTitle("Search Carpool");
            button.setText("Search");
            labelarea.setVisibility(View.GONE);
            textarea.setVisibility(View.GONE);
        }
        else {
            this.setTitle("Edit Carpool");
            button.setText("Update");

            //read carpool initialize fields

            if(type.equals("EditDemand")) {
                labelarea.setVisibility(View.GONE);
                textarea.setVisibility(View.GONE);
            }
        }

        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                new DatePickerDialog(CarpoolNew.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker dp, int year, int mounth, int day) {
                                tip.setText(year + "/" + (mounth+1) + "/" + day);
                            }
                        },
                        c.get(Calendar.YEAR),
                        c.get(Calendar.MONTH),
                        c.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        btn2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                new DatePickerDialog(CarpoolNew.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker dp, int year, int mounth, int day) {
                                tip2.setText(year + "/" + (mounth+1) + "/" + day);
                            }
                        },
                        c.get(Calendar.YEAR),
                        c.get(Calendar.MONTH),
                        c.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        btn3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                new TimePickerDialog(CarpoolNew.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int hour, int min) {
                                tip3.setText(hour + ":" + min);
                            }
                        },
                        c.get(Calendar.HOUR_OF_DAY),
                        c.get(Calendar.MINUTE),
                        true).show();
            }
        });

        btn4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                new TimePickerDialog(CarpoolNew.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int hour, int min) {
                                tip4.setText(hour + ":" + min);
                            }
                        },
                        c.get(Calendar.HOUR_OF_DAY),
                        c.get(Calendar.MINUTE),
                        true).show();
            }
        });
    }

    public void addCarpool(View view) {
        if(validate()) {
            if(type.equals("Create")) {
                if(Carpool.createCarpool(
                        GlobalVariables.user_id,
                        depart_loc_val,
                        depart_lat_val,
                        depart_lng_val,
                        desti_loc_val,
                        desti_lat_val,
                        desti_lng_val,
                        date,
                        time,
                        date_range,
                        time_range,
                        Integer.parseInt(maxpassenger_val),
                        Integer.parseInt(price_val)
                )) {
                    Toast.makeText(this, "Carpool created", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, CarpoolList.class);
                    intent.putExtra("type", "Created");
                    startActivity(intent);
                }
            }
            else if(type.equals("Demand")) {
                if(Carpool.demandCarpool(
                        GlobalVariables.user_id,
                        depart_loc_val,
                        depart_lat_val,
                        depart_lng_val,
                        desti_loc_val,
                        desti_lat_val,
                        desti_lng_val,
                        date,
                        time,
                        date_range,
                        time_range
                )) {
                    Toast.makeText(this, "Carpool demanded", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, CarpoolList.class);
                    intent.putExtra("type", "Demanded");
                    startActivity(intent);
                }
            }
            else if(type.equals("Search")) {
                Toast.makeText(this, "Searching carpools", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, CarpoolList.class);
                intent.putExtra("type", "Search");
                intent.putExtra("depart_lat_val", depart_lat_val);
                intent.putExtra("depart_lng_val", depart_lng_val);
                intent.putExtra("desti_lat_val", desti_lat_val);
                intent.putExtra("desti_lng_val", desti_lng_val);
                intent.putExtra("date", date);
                intent.putExtra("time", time);
                intent.putExtra("date_range", date_range);
                intent.putExtra("time_range", time_range);
                startActivity(intent);
            }
        }
    }

    public void setDepart(View view) {
        depart_loc_val = "Waterloo";
        depart_lat_val = 11.11D;
        depart_lng_val = 22.22D;
        set_depart_btn.setText(depart_loc_val);
    }

    public void setDesti(View view) {
        desti_loc_val = "Toronto";
        desti_lat_val = 33.33D;
        desti_lng_val = 44.44D;
        set_desti_btn.setText(desti_loc_val);
    }

    private boolean validate() {
        date = tip.getText().toString();
        time = tip3.getText().toString();
        date_range = tip2.getText().toString();
        time_range = tip4.getText().toString();
        maxpassenger_val = maxpassenger.getText().toString();
        price_val = price.getText().toString();

        if(depart_loc_val == null || desti_loc_val == null || date.equals("") || time.equals("")) {
            Toast.makeText(this, "Input cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if ((type.equals("Create") || type.equals("EditCreate")) && (maxpassenger_val.equals("") || price_val.equals(""))){
            Toast.makeText(this, "Input cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            return true;
        }
    }

}
