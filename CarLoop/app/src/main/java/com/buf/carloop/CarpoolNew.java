package com.buf.carloop;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.util.Calendar;

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

    private Carpool carpool;

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
        else if(type.equals("CreateOnDemand")) {
            this.setTitle("Create Carpool On Demand");
            add_btn.setText("Create");

            carpool = (Carpool) getIntent().getParcelableExtra("carpool");

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
        }
        else if(type.equals("Demand")) {
            this.setTitle("Demand Carpool");
            add_btn.setText("Demand");
            maxpassengerarea.setVisibility(View.GONE);
            pricearea.setVisibility(View.GONE);
        }
        else if(type.equals("Search")) {
            this.setTitle("Search Carpool");
            add_btn.setText("Search");
            maxpassengerarea.setVisibility(View.GONE);
            pricearea.setVisibility(View.GONE);
        }
        else if(type.equals("SearchDemand")) {
            this.setTitle("Search Demanded Carpool");
            add_btn.setText("Search");
            maxpassengerarea.setVisibility(View.GONE);
            pricearea.setVisibility(View.GONE);
        }
        else if(type.equals("Edit")) {
            this.setTitle("Edit Carpool");
            add_btn.setText("Update");

            carpool = (Carpool) getIntent().getParcelableExtra("carpool");
            carpoolid = carpool.getCarpoolid();

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

        else if(type.equals("EditDemand")) {
            this.setTitle("Edit Demanded Carpool");
            add_btn.setText("Update");

            carpool = (Carpool) getIntent().getParcelableExtra("carpool");
            carpoolid = carpool.getCarpoolid();

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

            maxpassengerarea.setVisibility(View.GONE);
            pricearea.setVisibility(View.GONE);
        }

        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                new DatePickerDialog(CarpoolNew.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker dp, int year, int month, int day) {
                                tip.setText(String.format("%04d-%02d-%02d", year, month + 1, day));
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
                            public void onDateSet(DatePicker dp, int year, int month, int day) {
                                tip2.setText(String.format("%04d-%02d-%02d", year, month + 1, day));
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
                                tip3.setText(String.format("%02d:%02d:%02d", hour, min, 0));
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
                                tip4.setText(String.format("%02d:%02d:%02d", hour, min, 0));
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
        if(validate()) {
            if(type.equals("Create")) {
                int status = Carpool.createCarpool(
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
                        0,
                        0
                );
                if(status == 0) {
                    Toast.makeText(this, "Carpool created", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, CarpoolList.class);
                    intent.putExtra("type", "Created");
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(this, "Network error", Toast.LENGTH_SHORT).show();
                    add_btn.setVisibility(View.VISIBLE);
                    bar.setVisibility(View.GONE);
                }
            }
            else if(type.equals("CreateOnDemand")) {
                int status = Carpool.createCarpoolOnDemand(
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
                        carpool.getDrivername()
                );
                if(status == 0) {
                    Toast.makeText(this, "Carpool created", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    Toast.makeText(this, "Network error", Toast.LENGTH_SHORT).show();
                    add_btn.setVisibility(View.VISIBLE);
                    bar.setVisibility(View.GONE);
                }
            }
            else if(type.equals("Demand")) {
                int status = Carpool.demandCarpool(
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
                        time_range
                );
                if(status == 0) {
                    Toast.makeText(this, "Carpool demanded", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, CarpoolList.class);
                    intent.putExtra("type", "Demanded");
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(this, "Network error", Toast.LENGTH_SHORT).show();
                    add_btn.setVisibility(View.VISIBLE);
                    bar.setVisibility(View.GONE);
                }
            }
            else if(type.equals("Edit")) {
                carpool.setDepart_loc(depart_loc_val);
                carpool.setDepart_lat(depart_lat_val);
                carpool.setDepart_lng(depart_lng_val);
                carpool.setDesti_loc(desti_loc_val);
                carpool.setDesti_lat(desti_lat_val);
                carpool.setDesti_lng(desti_lng_val);
                carpool.setDate(date);
                carpool.setTime(time);
                carpool.setDate_range(date_range);
                carpool.setTime_range(time_range);
                carpool.setMaxpassenger(Integer.parseInt(maxpassenger_val));
                carpool.setPrice(Integer.parseInt(price_val));

                int status = Carpool.updateCarpool(carpool);
                if(status == 0) {
                    Toast.makeText(this, "Carpool updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, CarpoolSingle.class);
                    intent.putExtra("carpool", carpool);
                    intent.putExtra("type", "Created");
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(this, "Network error", Toast.LENGTH_SHORT).show();
                    add_btn.setVisibility(View.VISIBLE);
                    bar.setVisibility(View.GONE);
                }
            }
            else if(type.equals("EditDemand")) {
                carpool.setDepart_loc(depart_loc_val);
                carpool.setDepart_lat(depart_lat_val);
                carpool.setDepart_lng(depart_lng_val);
                carpool.setDesti_loc(desti_loc_val);
                carpool.setDesti_lat(desti_lat_val);
                carpool.setDesti_lng(desti_lng_val);
                carpool.setDate(date);
                carpool.setTime(time);
                carpool.setDate_range(date_range);
                carpool.setTime_range(time_range);

                int status = Carpool.updateDemandedCarpool(carpool);
                if(status == 0) {
                    Toast.makeText(this, "Carpool updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, CarpoolSingle.class);
                    intent.putExtra("carpool", carpool);
                    intent.putExtra("type", "Demanded");
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(this, "Network error", Toast.LENGTH_SHORT).show();
                    add_btn.setVisibility(View.VISIBLE);
                    bar.setVisibility(View.GONE);
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
                intent.putExtra("type", "Search");
                startActivity(intent);
                finish();
            }
            else if(type.equals("SearchDemanded")) {
                Toast.makeText(this, "Searching demanded carpools", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, CarpoolList.class);
                intent.putExtra("depart_lat_val", depart_lat_val);
                intent.putExtra("depart_lng_val", depart_lng_val);
                intent.putExtra("desti_lat_val", desti_lat_val);
                intent.putExtra("desti_lng_val", desti_lng_val);
                intent.putExtra("date", date);
                intent.putExtra("time", time);
                intent.putExtra("date_range", date_range);
                intent.putExtra("time_range", time_range);
                intent.putExtra("type", "SearchDemanded");
                startActivity(intent);
                finish();
            }
        }
        else {
            add_btn.setVisibility(View.VISIBLE);
            bar.setVisibility(View.GONE);
        }
    }

    private static final int DEPART_PICKER_ID = 1;
    private static final int DEST_PICKER_ID = 2;

    /*
    interface for set departure location
    NOTE: need to update the text on the button "set_depart_btn" for display purpose
     */
    public void setDepart(View view) {
        try {
            PlacePicker.IntentBuilder intentBuilder =
                    new PlacePicker.IntentBuilder();
            Intent intent = intentBuilder.build(CarpoolNew.this);
            // Start the intent by requesting a result,
            // identified by a request code.
            startActivityForResult(intent, DEPART_PICKER_ID);
        } catch (GooglePlayServicesRepairableException e) {
            // ...
        } catch (GooglePlayServicesNotAvailableException e) {
            // ...
        }
    }

    /*
    interface for set destination
    NOTE: need to update the text on the button "set_desti_btn" for display purpose
     */
    public void setDesti(View view) {
        try {
            PlacePicker.IntentBuilder intentBuilder =
                    new PlacePicker.IntentBuilder();
            Intent intent = intentBuilder.build(CarpoolNew.this);
            // Start the intent by requesting a result,
            // identified by a request code.
            startActivityForResult(intent, DEST_PICKER_ID);
        } catch (GooglePlayServicesRepairableException e) {
            // ...
        } catch (GooglePlayServicesNotAvailableException e) {
            // ...
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == DEPART_PICKER_ID||requestCode == DEST_PICKER_ID) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(this,data);
                place.getName();

                if(requestCode == DEPART_PICKER_ID){
                    depart_loc_val = place.getName().toString();
                    depart_lat_val = place.getLatLng().latitude;
                    depart_lng_val = place.getLatLng().longitude;
                    set_depart_btn.setText(depart_loc_val);
                }
                else{
                    desti_loc_val = place.getName().toString();
                    desti_lat_val = place.getLatLng().latitude;
                    desti_lng_val = place.getLatLng().longitude;
                    set_desti_btn.setText(desti_loc_val);
                }
            }
        }
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
