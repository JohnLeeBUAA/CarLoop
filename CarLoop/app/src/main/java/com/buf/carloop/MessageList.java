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
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MessageList extends Footer {

    private TextView tip;
    private ListView listview;
    private List<Carpool> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup vg = (ViewGroup) findViewById(R.id.content);
        ViewGroup.inflate(this, R.layout.activity_message_list, vg);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tip = (TextView) findViewById(R.id.tip_messagelist);
        listview = (ListView) findViewById(R.id.list_messagelist);

        if(GlobalVariables.user_identity == 1) {
            list = Carpool.getCreatedList(GlobalVariables.user_name);
        }
        else {
            list = new ArrayList<>();
            List<Carpool> interestedlist = Carpool.getInterestedList(GlobalVariables.user_name);
            List<Carpool> confirmedlist = Carpool.getConfirmedList(GlobalVariables.user_name);
            if(interestedlist != null && interestedlist.size() != 0) {
                list.addAll(interestedlist);
            }
            if(confirmedlist != null && confirmedlist.size() != 0) {
                list.addAll(confirmedlist);
            }
        }
        if(list == null || list.size() == 0) {
            tip.setText("Message List Is Empty");
        }
        else {
            tip.setVisibility(View.GONE);
            populateListView();
            registerClickCallback();
        }
    }

    private void populateListView() {
        ArrayAdapter<Carpool> adapter = new MyListAdapter();
        listview.setAdapter(adapter);
    }

    private void registerClickCallback() {
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked,
                                    int position, long id) {
                Intent intent = new Intent(MessageList.this, Message.class);
                intent.putExtra("carpoolid", list.get(position).getCarpoolid());
                intent.putExtra("drivername", list.get(position).getDrivername());
                startActivity(intent);
            }
        });
    }

    private class MyListAdapter extends ArrayAdapter<Carpool> {
        public MyListAdapter() {
            super(MessageList.this, R.layout.item_message_list, list);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.item_message_list, parent, false);
            }

            Carpool carpool = list.get(position);

            ImageView driveravatar = (ImageView)itemView.findViewById(R.id.item_avatar_messagelist);
            byte[] avatarimage = list.get(position).getDriveravatar();
            if (avatarimage != null) {
                Bitmap bm = BitmapFactory.decodeByteArray(avatarimage, 0, avatarimage.length);
                driveravatar.setImageBitmap(bm);
            }
            else {
                driveravatar.setImageResource(R.drawable.default_avatar);
            }

            TextView drivername = (TextView) itemView.findViewById(R.id.item_drivername_messagelist);
            drivername.setText(carpool.getDrivername());

            TextView depart_loc = (TextView) itemView.findViewById(R.id.item_depart_loc_messagelist);
            depart_loc.setText(carpool.getDepart_loc());

            TextView desti_loc = (TextView) itemView.findViewById(R.id.item_desti_loc_messagelist);
            desti_loc.setText(carpool.getDesti_loc());

            return itemView;
        }
    }

}
