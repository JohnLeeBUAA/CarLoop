package com.buf.carloop;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Payment extends Footer {

    private int carpoolid;
    private ListView listview;
    private List<PassengerCarpool> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup vg = (ViewGroup) findViewById(R.id.content);
        ViewGroup.inflate(this, R.layout.activity_payment, vg);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.setTitle("Payment Info");

        carpoolid = getIntent().getIntExtra("carpoolid", -1);
        listview = (ListView) findViewById(R.id.list_payment);
        list = PassengerCarpool.getPaymentList(carpoolid);

        if(list == null) {
            Toast.makeText(this, "Network error", Toast.LENGTH_SHORT).show();
        }
        else if(list != null && list.size() > 0) {
            populateListView();
        }
    }

    private void populateListView() {
        ArrayAdapter<PassengerCarpool> adapter = new MyListAdapter();
        listview.setAdapter(adapter);
    }

    private class MyListAdapter extends ArrayAdapter<PassengerCarpool> {
        public MyListAdapter() {
            super(Payment.this, R.layout.item_payment, list);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.item_payment, parent, false);
            }

            PassengerCarpool pc = list.get(position);

            ImageView passengeravatar = (ImageView)itemView.findViewById(R.id.item_passengeravatar);
            byte[] avatarimage = pc.getPassengeravatar();
            if (avatarimage != null) {
                Bitmap bm = BitmapFactory.decodeByteArray(avatarimage, 0, avatarimage.length);
                passengeravatar.setImageBitmap(bm);
            }
            else {
                passengeravatar.setImageResource(R.drawable.default_avatar);
            }

            TextView passengername = (TextView) itemView.findViewById(R.id.item_passengername);
            passengername.setText(pc.getPassengername());

            TextView tip = (TextView) itemView.findViewById(R.id.item_tip);
            if(pc.getPaid() == 1) {
                tip.setText("Paid");
            }
            else {
                tip.setText("Not paid");
            }
            return itemView;
        }
    }

    public void paymentBack(View view) {
        finish();
    }

}
