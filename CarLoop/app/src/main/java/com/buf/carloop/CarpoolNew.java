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
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.DatePicker;
import android.view.View.OnClickListener;
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
    private String arrival_loc_val;
    private double arrival_lat_val;
    private double arrival_lng_val;
    private Button set_depart_btn;
    private Button set_arrival_btn;

    private Calendar depart_date;


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
        else {
            this.setTitle("Search Carpool");
            button.setText("Search");
            labelarea.setVisibility(View.GONE);
            textarea.setVisibility(View.GONE);
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
                depart_date.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
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

//        if(type.equals("Create")) {
//            Toast.makeText(this, "Carpool created", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(this, CarpoolList.class);
//            intent.putExtra("type", "Created");
//            startActivity(intent);
//        }
//        else if(type.equals("Demand")) {
//            Toast.makeText(this, "Carpool demanded", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(this, CarpoolList.class);
//            intent.putExtra("type", "Demanded");
//            startActivity(intent);
//        }
//        else {
//            Toast.makeText(this, "Searching carpools", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(this, CarpoolList.class);
//            intent.putExtra("type", "Search");
//            startActivity(intent);
//        }
    }

}
