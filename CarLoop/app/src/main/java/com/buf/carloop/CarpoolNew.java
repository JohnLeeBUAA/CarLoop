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
import android.widget.Toast;

public class CarpoolNew extends Footer {

    private String type;
    private Button button;
    private LinearLayout labelarea;
    private LinearLayout textarea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup vg = (ViewGroup) findViewById(R.id.content);
        ViewGroup.inflate(this, R.layout.activity_carpool_new, vg);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
    }

    public void addCarpool(View view) {
        if(type.equals("Create")) {
            Toast.makeText(this, "Carpool created", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, CarpoolList.class);
            intent.putExtra("type", "Created");
            startActivity(intent);
        }
        else if(type.equals("Demand")) {
            Toast.makeText(this, "Carpool demanded", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, CarpoolList.class);
            intent.putExtra("type", "Demanded");
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Searching carpools", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, CarpoolList.class);
            intent.putExtra("type", "Search");
            startActivity(intent);
        }
    }

}
