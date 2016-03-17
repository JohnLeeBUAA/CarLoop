package com.buf.carloop;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CarpoolList extends Footer {

    private String type;
    private LinearLayout buttonlist;
    private TextView tip;
    private ListView listview;
    private List<Carpool> list;
    private Button btn_interested;
    private Button btn_confirmed;

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
        btn_interested = (Button) findViewById(R.id.btn_interested_carpoollist);
        btn_confirmed = (Button) findViewById(R.id.btn_confirmed_carpoollist);
        
        type = getIntent().getStringExtra("type");
        if(type.equals("Search")) {
            this.setTitle("Search Result");
            buttonlist.setVisibility(View.GONE);

            list = Carpool.getSearchList(
                    GlobalVariables.user_name,
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
                populateListView();
                registerClickCallback();
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
                populateListView();
                registerClickCallback();
            }
        }
        else if(type.equals("Interested")) {
            this.setTitle("Interested List");
            btn_interested.setBackgroundResource(R.drawable.green_button);
            list = Carpool.getInterestedList(GlobalVariables.user_name);
            if(list == null || list.size() == 0) {
                tip.setText("No Interested Carpool");
            }
            else {
                tip.setVisibility(View.GONE);
                populateListView();
                registerClickCallback();
            }
        }
        else if(type.equals("Confirmed")) {
            this.setTitle("Confirmed List");
            btn_confirmed.setBackgroundResource(R.drawable.green_button);
            list = Carpool.getConfirmedList(GlobalVariables.user_name);
            if(list == null || list.size() == 0) {
                tip.setText("No Confirmed Carpool");
            }
            else {
                tip.setVisibility(View.GONE);
                populateListView();
                registerClickCallback();
            }
        }
        else if(type.equals("Message")) {
            this.setTitle("Message List");
            buttonlist.setVisibility(View.GONE);
            if(GlobalVariables.user_identity == 1) {
                list = Carpool.getCreatedList(GlobalVariables.user_name);
            }
            else {
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
                int clickedCarpoolid = list.get(position).getCarpoolid();
                if (type.equals("Message")) {
                    Intent intent = new Intent(CarpoolList.this, Message.class);
                    intent.putExtra("carpoolid", clickedCarpoolid);
                    intent.putExtra("drivername", list.get(position).getDrivername());
                    startActivity(intent);
                } else {
                    if (type.equals("Search")) {
                        TextView status = (TextView) viewClicked.findViewById(R.id.item_status);
                        status.setText("This carpool is checked");
                    }
                    Intent intent = new Intent(CarpoolList.this, CarpoolSingle.class);
                    intent.putExtra("carpool", list.get(position));
                    if (list.get(position).getStatus() == 1 && type.equals("Created")) {
                        /*
                        carpool trip for drivers
                         */
                        intent.putExtra("type", "Trip");
                    } else {
                        /*
                        Created, Interested, Confirmed, Search
                         */
                        intent.putExtra("type", type);
                    }
                    startActivity(intent);
                }
            }
        });
    }

    private class MyListAdapter extends ArrayAdapter<Carpool> {
        public MyListAdapter() {
            super(CarpoolList.this, R.layout.item_carpool, list);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.item_carpool, parent, false);
            }

            Carpool carpool = list.get(position);

            ImageView driveravatar = (ImageView)itemView.findViewById(R.id.item_driveravatar);
            byte[] avatarimage = list.get(position).getDriveravatar();
            if (avatarimage != null) {
                Bitmap bm = BitmapFactory.decodeByteArray(avatarimage, 0, avatarimage.length);
                driveravatar.setImageBitmap(bm);
            }
            else {
                driveravatar.setImageResource(R.drawable.default_avatar);
            }

            TextView drivername = (TextView) itemView.findViewById(R.id.item_drivername);
            drivername.setText(carpool.getDrivername());

            TextView depart_loc = (TextView) itemView.findViewById(R.id.item_depart_loc);
            depart_loc.setText(carpool.getDepart_loc());

            TextView desti_loc = (TextView) itemView.findViewById(R.id.item_desti_loc);
            desti_loc.setText(carpool.getDesti_loc());

            TextView date = (TextView) itemView.findViewById(R.id.item_date);
            if(carpool.getDate_range().equals(carpool.getDate())) {
                date.setText(carpool.getDate());
            }
            else {
                date.setText(carpool.getDate() + " - " + carpool.getDate_range());
            }

            TextView time = (TextView) itemView.findViewById(R.id.item_time);
            if(carpool.getTime_range().equals(carpool.getTime())) {
                time.setText(carpool.getTime());
            }
            else {
                time.setText(carpool.getTime() + " - " + carpool.getTime_range());
            }

            TextView price = (TextView) itemView.findViewById(R.id.item_price);
            price.setText("$" + Integer.toString(carpool.getPrice()));

            TextView maxpassenger = (TextView) itemView.findViewById(R.id.item_maxpassenger);
            maxpassenger.setText(Integer.toString(carpool.getMaxpassenger()));

            TextView passengerconfirmed = (TextView) itemView.findViewById(R.id.item_passengerconfirmed);
            passengerconfirmed.setText(Integer.toString(carpool.getPassengerconfirmed()));

            return itemView;
        }
    }

    public void jumpInterestedList(View view) {
        this.setTitle("Interested List");
        btn_interested.setBackgroundResource(R.drawable.green_button);
        btn_confirmed.setBackgroundResource(R.drawable.single_border);
        list = Carpool.getInterestedList(GlobalVariables.user_name);
        if(list == null || list.size() == 0) {
            tip.setText("No Interested Carpool");
        }
        else {
            tip.setVisibility(View.GONE);
            populateListView();
            registerClickCallback();
        }
        /*Intent intent = new Intent(this, CarpoolList.class);
        intent.putExtra("type", "Interested");
        startActivity(intent);*/
    }

    public void jumpConfirmedList(View view) {
        this.setTitle("Confirmed List");
        btn_confirmed.setBackgroundResource(R.drawable.green_button);
        btn_interested.setBackgroundResource(R.drawable.single_border);
        list = Carpool.getConfirmedList(GlobalVariables.user_name);
        if(list == null || list.size() == 0) {
            tip.setText("No Confirmed Carpool");
        }
        else {
            tip.setVisibility(View.GONE);
            populateListView();
            registerClickCallback();
        }
        /*Intent intent = new Intent(this, CarpoolList.class);
        intent.putExtra("type", "Confirmed");
        startActivity(intent);*/
    }

}
