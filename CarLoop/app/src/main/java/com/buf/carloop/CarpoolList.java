package com.buf.carloop;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class CarpoolList extends Footer {

    private String type;
    private LinearLayout buttonlist;
    private TextView tip;
    private ListView listview;
    private List<Carpool> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup vg = (ViewGroup) findViewById(R.id.content);
        ViewGroup.inflate(this, R.layout.activity_carpool_list, vg);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        buttonlist = (LinearLayout) findViewById(R.id.buttonlist_carpool_list);
        listview = (ListView) findViewById(R.id.list_carpoollist);
        tip = (TextView) findViewById(R.id.tip_carpoollist);
        
        type = getIntent().getStringExtra("type");
        if(type.equals("Search")) {
            this.setTitle("Search Result");
            buttonlist.setVisibility(View.GONE);

            list = Carpool.getSearchList(
                    getIntent().getDoubleExtra("depart_lat_val", 0D),
                    getIntent().getDoubleExtra("depart_lng_val", 0D),
                    getIntent().getDoubleExtra("desti_lat_val", 0D),
                    getIntent().getDoubleExtra("desti_lng_val", 0D),
                    getIntent().getStringExtra("date"),
                    getIntent().getStringExtra("time"),
                    getIntent().getStringExtra("date_range"),
                    getIntent().getStringExtra("time_range"));
            if(list == null || list.size() == 0) {
                tip.setText("No Matched Carpool");
            }
            else {
                tip.setVisibility(View.GONE);
            }
        }
        else if(type.equals("Created")) {
            this.setTitle("Created List");
            buttonlist.setVisibility(View.GONE);

            list = Carpool.getCreatedList(GlobalVariables.user_name);
            if(list == null || list.size() == 0) {
                tip.setText("No Created Carpool");
            }
            else {
                tip.setVisibility(View.GONE);
            }
        }
        else if(type.equals("Interested")) {
            this.setTitle("Interested List");

            list = Carpool.getInterestedList(GlobalVariables.user_name);
            if(list == null || list.size() == 0) {
                tip.setText("No Interested Carpool");
            }
            else {
                tip.setVisibility(View.GONE);
            }
        }
        else if(type.equals("Confirmed")) {
            this.setTitle("Confirmed List");

            list = Carpool.getConfirmedList(GlobalVariables.user_name);
            if(list == null || list.size() == 0) {
                tip.setText("No Confirmed Carpool");
            }
            else {
                tip.setVisibility(View.GONE);
            }
        }
        else if(type.equals("Message")) {
            this.setTitle("Message List");
            buttonlist.setVisibility(View.GONE);

            list = Carpool.getMessageList(GlobalVariables.user_name);
            if(list == null || list.size() == 0) {
                tip.setText("Message List Is Empty");
            }
            else {
                tip.setVisibility(View.GONE);
            }
        }
    }

    public void jumpInterestedList(View view) {
        Intent intent = new Intent(this, CarpoolList.class);
        intent.putExtra("type", "Interested");
        startActivity(intent);
    }

    public void jumpConfirmedList(View view) {
        Intent intent = new Intent(this, CarpoolList.class);
        intent.putExtra("type", "Confirmed");
        startActivity(intent);
    }

}
