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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Notifications extends Footer {
    private TextView tip;
    private ListView listview;
    private List<PassengerCarpool> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup vg = (ViewGroup) findViewById(R.id.content);
        ViewGroup.inflate(this, R.layout.activity_notifications, vg);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tip = (TextView) findViewById(R.id.tip_notifications);
        listview = (ListView) findViewById(R.id.list_notifications);

        if(GlobalVariables.user_identity == 1) {
            list = PassengerCarpool.getDriversNotifications(GlobalVariables.user_name);
        }
        else {
            list = PassengerCarpool.getPassengersNotifications(GlobalVariables.user_name);
        }

        if(list == null) {
            tip.setText("Network error");
            Toast.makeText(this, "Network error", Toast.LENGTH_SHORT).show();
        }
        else if(list.size() == 0) {
            tip.setText("No Notification");
        }
        else {
            tip.setVisibility(View.GONE);
            populateListView();
            registerClickCallback();
        }
    }

    private void populateListView() {
        ArrayAdapter<PassengerCarpool> adapter = new MyListAdapter();
        listview.setAdapter(adapter);
    }

    private void registerClickCallback() {
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked,
                                    int position, long id) {

                Carpool tc = Carpool.getCarpool(list.get(position).getCarpoolid());
                if (tc == null) {
                    Toast.makeText(Notifications.this, "Carpool does not exist", Toast.LENGTH_SHORT).show();
                } else if (tc.getCarpoolid() == -1) {
                    Toast.makeText(Notifications.this, "Network error", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Notifications.this, CarpoolSingle.class);
                    intent.putExtra("carpool", tc);
                    if (GlobalVariables.user_identity == 1) {
                        if (list.get(position).getStatus() == 1) {
                            intent.putExtra("type", "Trip");
                        } else {
                            intent.putExtra("type", "Created");
                        }
                    } else {
                        intent.putExtra("type", "Search");
                    }
                    startActivity(intent);
                }
            }
        });
    }

    private class MyListAdapter extends ArrayAdapter<PassengerCarpool> {
        public MyListAdapter() {
            super(Notifications.this, R.layout.item_payment, list);
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
            if(GlobalVariables.user_identity == 1) {
                tip.setText("confirmed your carpool");
            }
            else {
                tip.setText("answered your demand");
            }

            return itemView;
        }
    }

    public void jumpBack(View view) {
        finish();
    }

}
