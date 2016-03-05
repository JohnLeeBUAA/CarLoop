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
import android.widget.ProgressBar;
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
    private int carpoolid;

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
    private TextView tip;
    private TextView tip2;
    private TextView tip3;
    private TextView tip4;
    private Button btn;
    private Button btn2;
    private Button btn3;
    private Button btn4;

    private LinearLayout maxpassengerarea;
    private LinearLayout pricearea;
    private EditText maxpassenger;
    private EditText price;
    private String maxpassenger_val;
    private String price_val;

    private Button add_btn;
    private ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup vg = (ViewGroup) findViewById(R.id.content);
        ViewGroup.inflate(this, R.layout.activity_carpool_new, vg);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        set_depart_btn = (Button) findViewById(R.id.setdepart_carpoolnew);
        set_desti_btn = (Button) findViewById(R.id.setdesti_carpoolnew);

        tip = (TextView) findViewById(R.id.tip);
        tip2 = (TextView) findViewById(R.id.tip2);
        tip3 = (TextView) findViewById(R.id.tip3);
        tip4 = (TextView) findViewById(R.id.tip4);
        btn = (Button) findViewById(R.id.btn);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);

        maxpassenger = (EditText) findViewById(R.id.maxpassenger_carpoolnew);
        price = (EditText) findViewById(R.id.price_carpoolnew);
        maxpassengerarea = (LinearLayout) findViewById(R.id.maxpassegerarea_carpoolnew);
        pricearea = (LinearLayout) findViewById(R.id.pricearea_carpoolnew);

        add_btn = (Button) findViewById(R.id.add_carpoolnew);
        bar = (ProgressBar) findViewById(R.id.progressBar_carpoolnew);
        bar.setVisibility(View.GONE);

        type = getIntent().getStringExtra("type");

        if(type.equals("Create")) {
            this.setTitle("Create Carpool");
            add_btn.setText("Create");
        }
        else if(type.equals("Search")) {
            this.setTitle("Search Carpool");
            add_btn.setText("Search");
            maxpassengerarea.setVisibility(View.GONE);
            pricearea.setVisibility(View.GONE);
        }
        else if(type.equals("Edit")) {
            this.setTitle("Edit Carpool");
            add_btn.setText("Update");

            carpoolid = getIntent().getIntExtra("carpoolid", 0);
            Carpool carpool = Carpool.getCarpool(carpoolid);

            depart_loc_val = carpool.getDepart_loc();
            depart_lat_val = carpool.getDepart_lat();
            depart_lng_val = carpool.getDepart_lng();
            desti_loc_val = carpool.getDesti_loc();
            desti_lat_val = carpool.getDesti_lat();
            desti_lng_val = carpool.getDesti_lng();

            set_depart_btn.setText(depart_loc_val);
            set_desti_btn.setText(desti_loc_val);

            tip.setText(carpool.getDate());
            tip2.setText(carpool.getDate_range());
            tip3.setText(carpool.getTime());
            tip4.setText(carpool.getTime_range());

            maxpassenger.setText(Integer.toString(carpool.getMaxpassenger()));
            price.setText(Integer.toString(carpool.getPrice()));
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
        add_btn.setVisibility(View.GONE);
        bar.setVisibility(View.VISIBLE);
        try {
            if(validate()) {
                if(type.equals("Create")) {
                    if(Carpool.createCarpool(
                            GlobalVariables.user_name,
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
                            Integer.parseInt(price_val),
                            0,
                            0
                    )) {
                        Toast.makeText(this, "Carpool created", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this, CarpoolList.class);
                        intent.putExtra("type", "Created");
                        startActivity(intent);
                    }
                }
                else if(type.equals("Edit")) {
                    if(Carpool.updateCarpool(
                            carpoolid,
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
                        Toast.makeText(this, "Carpool updated", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this, CarpoolList.class);
                        intent.putExtra("type", "Created");
                        startActivity(intent);
                    }
                }
                else if(type.equals("Search")) {
                    Toast.makeText(this, "Searching carpools", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, CarpoolList.class);
                    intent.putExtra("depart_lat_val", depart_lat_val);
                    intent.putExtra("depart_lng_val", depart_lng_val);
                    intent.putExtra("desti_lat_val", desti_lat_val);
                    intent.putExtra("desti_lng_val", desti_lng_val);
                    intent.putExtra("date", date);
                    intent.putExtra("time", time);
                    intent.putExtra("date_range", date_range);
                    intent.putExtra("time_range", time_range);
                    intent.putExtra("type", "Search Result");
                    startActivity(intent);
                }
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            add_btn.setVisibility(View.VISIBLE);
            bar.setVisibility(View.GONE);
        }
    }

    /*
    interface for set departure location
    NOTE: need to update the text on the button "set_depart_btn" for display purpose
     */
    public void setDepart(View view) {
        depart_loc_val = "Waterloo";
        depart_lat_val = 11.11D;
        depart_lng_val = 22.22D;
        set_depart_btn.setText(depart_loc_val);
    }

    /*
    interface for set destination
    NOTE: need to update the text on the button "set_desti_btn" for display purpose
     */
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

        if(date_range.equals("")) date_range = date;
        if(time_range.equals("")) time_range = time;

        maxpassenger_val = maxpassenger.getText().toString();
        price_val = price.getText().toString();

        if(depart_loc_val == null || desti_loc_val == null || date.equals("") || time.equals("")) {
            Toast.makeText(this, "Input cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if ((type.equals("Create") || type.equals("Edit")) && (maxpassenger_val.equals("") || price_val.equals(""))){
            Toast.makeText(this, "Input cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            return true;
        }
    }

}
