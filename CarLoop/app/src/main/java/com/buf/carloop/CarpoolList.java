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

public class CarpoolList extends Footer {

    private String type;
    private Button button;
    private LinearLayout buttonlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup vg = (ViewGroup) findViewById(R.id.content);
        ViewGroup.inflate(this, R.layout.activity_carpool_list, vg);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        button = (Button) findViewById(R.id.addlist_carpool_list);
        buttonlist = (LinearLayout) findViewById(R.id.buttonlist_carpool_list);
        
        type = getIntent().getStringExtra("type");
        if(type.equals("Search")) {
            this.setTitle("Search Result");
            buttonlist.setVisibility(View.GONE);
        }
        else this.setTitle(type + " List");

        if(GlobalVariables.user_identity == 1) {
            button.setText("Created");
        }
        else {
            button.setText("Demanded");
        }

    }

    public void jumpAddList(View view) {
        if(GlobalVariables.user_identity == 1) {
            Intent intent = new Intent(this, CarpoolList.class);
            intent.putExtra("type", "Created");
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(this, CarpoolList.class);
            intent.putExtra("type", "Demanded");
            startActivity(intent);
        }
    }

    public void jumpConfirmedList(View view) {
        Intent intent = new Intent(this, CarpoolList.class);
        intent.putExtra("type", "Confirmed");
        startActivity(intent);
    }

    public void jumpInterestedList(View view) {
        Intent intent = new Intent(this, CarpoolList.class);
        intent.putExtra("type", "Interested");
        startActivity(intent);
    }

    public void checkCarpool(View view) {
        Intent intent = new Intent(this, CarpoolSingle.class);
        startActivity(intent);
    }

}
