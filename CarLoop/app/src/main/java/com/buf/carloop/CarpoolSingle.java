package com.buf.carloop;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CarpoolSingle extends Footer {

    private TextView depart_loc;
    private TextView desti_loc;
    private TextView date;
    private TextView time;
    private TextView price;
    private TextView capacity;
    private TextView confirmed;

    private ImageView driveravatar;
    private TextView drivername;
    private RatingBar driverrate;
    private TextView tip;
    private ListView listview;
    private List<ReviewClass> list;

    private LinearLayout message_layout;
    private LinearLayout created_layout;
    private LinearLayout search_layout;
    private LinearLayout interested_layout;
    private LinearLayout confirmed_layout;
    private LinearLayout trip_layout;

    private Carpool carpool;
    private int carpoolid;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup vg = (ViewGroup) findViewById(R.id.content);
        ViewGroup.inflate(this, R.layout.activity_carpool_single, vg);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        depart_loc = (TextView) findViewById(R.id.depart_loc_carpoolsingle);
        desti_loc = (TextView) findViewById(R.id.desti_loc_carpoolsingle);
        date = (TextView) findViewById(R.id.date_carpoolsingle);
        time = (TextView) findViewById(R.id.time_carpoolsingle);
        price = (TextView) findViewById(R.id.price_carpoolsingle);
        capacity = (TextView) findViewById(R.id.maxpassenger_carpoolsingle);
        confirmed = (TextView) findViewById(R.id.passengerconfirmed_carpoolsingle);

        driveravatar = (ImageView) findViewById(R.id.driveravatar_carpoolsingle);
        drivername = (TextView) findViewById(R.id.drivername_carpoolsingle);
        driverrate = (RatingBar) findViewById(R.id.ratingbar_capoolsingle);
        tip = (TextView) findViewById(R.id.tip_review);
        listview = (ListView) findViewById(R.id.list_review);

        message_layout = (LinearLayout) findViewById(R.id.message_layout);
        message_layout.setVisibility(View.GONE);
        created_layout = (LinearLayout) findViewById(R.id.created_layout);
        created_layout.setVisibility(View.GONE);
        search_layout = (LinearLayout) findViewById(R.id.search_layout);
        search_layout.setVisibility(View.GONE);
        interested_layout = (LinearLayout) findViewById(R.id.interested_layout);
        interested_layout.setVisibility(View.GONE);
        confirmed_layout = (LinearLayout) findViewById(R.id.confirmed_layout);
        confirmed_layout.setVisibility(View.GONE);
        trip_layout = (LinearLayout) findViewById(R.id.trip_layout);
        trip_layout.setVisibility(View.GONE);

        type = getIntent().getStringExtra("type");
        carpool = (Carpool) getIntent().getParcelableExtra("carpool");
        carpoolid = carpool.getCarpoolid();
        depart_loc.setText(carpool.getDepart_loc());
        desti_loc.setText(carpool.getDesti_loc());
        if(carpool.getDate_range().equals(carpool.getDate())) {
            date.setText(carpool.getDate());
        }
        else {
            date.setText(carpool.getDate() + " - " + carpool.getDate_range());
        }
        if(carpool.getTime_range().equals(carpool.getTime())) {
            time.setText(carpool.getTime());
        }
        else {
            time.setText(carpool.getTime() + " - " + carpool.getTime_range());
        }
        price.setText("$" + Integer.toString(carpool.getPrice()));
        capacity.setText(Integer.toString(carpool.getMaxpassenger()));
        confirmed.setText(Integer.toString(carpool.getPassengerconfirmed()));

        byte[] avatarimage = carpool.getDriveravatar();
        if (avatarimage != null) {
            Bitmap bm = BitmapFactory.decodeByteArray(avatarimage, 0, avatarimage.length);
            driveravatar.setImageBitmap(bm);
        }
        drivername.setText(carpool.getDrivername());
        list = ReviewClass.getReviewList(carpool.getDrivername());
        if(list == null || list.size() == 0) {
            tip.setText("No review yet");
        }
        else {
            tip.setVisibility(View.GONE);
            double rate = 0D;
            for(int i = 0; i < list.size(); i++) {
                rate += list.get(i).getRate();
            }
            rate = rate / list.size();
            driverrate.setNumStars(5);
            driverrate.setRating((float)rate);
            populateListView();
        }

        if(type.equals("Search")) {
            this.setTitle("Search Result");
            search_layout.setVisibility(View.VISIBLE);
        }
        else if(type.equals("Created")) {
            this.setTitle("Created Carpool");
            message_layout.setVisibility(View.VISIBLE);
            created_layout.setVisibility(View.VISIBLE);
        }
        else if(type.equals("Interested")) {
            this.setTitle("Interested Carpool");
            message_layout.setVisibility(View.VISIBLE);
            interested_layout.setVisibility(View.VISIBLE);
        }
        else if(type.equals("Confirmed")) {
            this.setTitle("Confirmed Carpool");
            message_layout.setVisibility(View.VISIBLE);
            confirmed_layout.setVisibility(View.VISIBLE);

            PassengerCarpool pc = PassengerCarpool.getInfo(GlobalVariables.user_name, carpoolid);
            Button btn_aboard = (Button) findViewById(R.id.btn_aboardconfirmed);
            Button btn_pay = (Button) findViewById(R.id.btn_payconfirmed);
            Button btn_review = (Button) findViewById(R.id.btn_reviewconfirmed);
            if(pc.getAboard() == 0) {
                //not aboard
                btn_pay.setEnabled(false);
                btn_review.setEnabled(false);
            }
            else {
                //aboard
                btn_aboard.setText("\tAlready aboard\t");
                btn_aboard.setEnabled(false);
                if(pc.getPaid() == 0) {
                    //not paid
                    btn_review.setEnabled(false);
                }
                else {
                    //paid
                    btn_pay.setText("\tAlready paid\t");
                    btn_pay.setEnabled(false);
                }
            }
        }
        else if(type.equals("Trip")) {
            this.setTitle("Ongoing Carpool Trip");
            message_layout.setVisibility(View.VISIBLE);
            trip_layout.setVisibility(View.VISIBLE);
        }
    }

    private void populateListView() {
        ArrayAdapter<ReviewClass> adapter = new MyListAdapter();
        listview.setAdapter(adapter);
    }

    private class MyListAdapter extends ArrayAdapter<ReviewClass> {
        public MyListAdapter() {
            super(CarpoolSingle.this, R.layout.item_review, list);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.item_review, parent, false);
            }

            ReviewClass rc = list.get(position);

            ImageView avatar = (ImageView)itemView.findViewById(R.id.item_revieweravatar);
            byte[] avatarimage = rc.getReviewavatar();
            if (avatarimage != null) {
                Bitmap bm = BitmapFactory.decodeByteArray(avatarimage, 0, avatarimage.length);
                avatar.setImageBitmap(bm);
            }
            else {
                avatar.setImageResource(R.drawable.default_avatar);
            }

            TextView name = (TextView) itemView.findViewById(R.id.item_reviewername);
            name.setText(rc.getReviewername());

            TextView content = (TextView) itemView.findViewById(R.id.item_reviewcontent);
            content.setText(rc.getReview());

            RatingBar rb = (RatingBar) itemView.findViewById(R.id.item_ratingbar);
            rb.setNumStars(5);
            rb.setRating((float)(rc.getRate()));

            return itemView;
        }
    }

    public void jumpMessage(View view) {
        Intent intent = new Intent(this, Message.class);
        intent.putExtra("carpoolid", carpool.getCarpoolid());
        intent.putExtra("drivername", carpool.getDrivername());
        startActivity(intent);
    }

    public void deleteCreated(View view) {
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setMessage("You are deleting carpool: " +
                "from " + carpool.getDepart_loc() + " to " + carpool.getDesti_loc() + ". " +
                "All related info will be deleted.")
            .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    int status = Carpool.deleteCarpool(carpoolid);
                    if (status == 0) {
                        Toast.makeText(CarpoolSingle.this, "Carpool deleted", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CarpoolSingle.this, CarpoolList.class);
                        intent.putExtra("type", "Created");
                        startActivity(intent);
                    } else {
                        Toast.makeText(CarpoolSingle.this, "Network error", Toast.LENGTH_SHORT).show();
                    }
                }
            })
            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            }) ;
        AlertDialog alert = ab.create();
        alert.setTitle("Confirm Deletion");
        alert.show();
    }

    public void editCreated(View view) {
        Intent intent = new Intent(this, CarpoolNew.class);
        intent.putExtra("carpool", carpool);
        intent.putExtra("type", "Edit");
        startActivity(intent);
    }

    public void startTrip (View view) {
        int valid = Carpool.startTripCheck(carpoolid);
        if(valid == 0) {
            int status = Carpool.startTrip(carpoolid);
            if(status == 0) {
                Toast.makeText(this, "Carpool trip started", Toast.LENGTH_SHORT).show();
                created_layout.setVisibility(View.GONE);
                trip_layout.setVisibility(View.VISIBLE);
            }
            else {
                Toast.makeText(this, "Network error", Toast.LENGTH_SHORT).show();
            }
        }
        else if(valid == 1){
            Toast.makeText(this, "Can not start carpool trip. Wait till all confirmed passengers are aboard.", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "Network error", Toast.LENGTH_SHORT).show();
        }
    }

    public void declineSearch (View view) {
        int status = Carpool.declineSearch(GlobalVariables.user_name, carpoolid);
        if(status == 0) {
            Toast.makeText(this, "Carpool declined", Toast.LENGTH_SHORT).show();
            finish();
        }
        else {
            Toast.makeText(this, "Network error", Toast.LENGTH_SHORT).show();
        }
    }

    public void interestedSearch (View view) {
        int status = Carpool.interestedSearch(GlobalVariables.user_name, carpoolid);
        if(status == 0) {
            Toast.makeText(this, "Added to interested list", Toast.LENGTH_SHORT).show();
            finish();
        }
        else {
            Toast.makeText(this, "Network error", Toast.LENGTH_SHORT).show();
        }
    }

    public void confirmSearch (View view) {
        int status = Carpool.confirmSearch(GlobalVariables.user_name, carpoolid);
        if(status == 0) {
            Toast.makeText(this, "Carpool confirmed", Toast.LENGTH_SHORT).show();
            finish();
        }
        else {
            Toast.makeText(this, "Network error", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteInterested (View view) {
        int status = Carpool.deleteInterested(GlobalVariables.user_name, carpoolid);
        if(status == 0) {
            Toast.makeText(this, "Record deleted", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, CarpoolList.class);
            intent.putExtra("type", "Interested");
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Network error", Toast.LENGTH_SHORT).show();
        }
    }

    public void confirmInterested (View view) {
        int valid = Carpool.confirmInterestedCheck(carpoolid);
        if(valid == 0) {
            int status = Carpool.confirmInterested(GlobalVariables.user_name, carpoolid);
            if(status == 0) {
                Toast.makeText(this, "Carpool confirmed", Toast.LENGTH_SHORT).show();
                this.setTitle("Confirmed Carpool");
                interested_layout.setVisibility(View.GONE);
                confirmed_layout.setVisibility(View.VISIBLE);
                confirmed.setText(Integer.toString(carpool.getPassengerconfirmed() + 1));
            }
            else {
                Toast.makeText(this, "Network error", Toast.LENGTH_SHORT).show();
            }
        }
        else if(valid == 1) {
            Toast.makeText(this, "Can not confirm. This carpool has reached maximum passenger number", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "Network error", Toast.LENGTH_SHORT).show();
        }
    }

    public void aboardConfirmed (View view) {
        int status = Carpool.aboardConfirmed(GlobalVariables.user_name, carpoolid);
        if(status == 0) {
            Toast.makeText(this, "Status changed to: aboard", Toast.LENGTH_SHORT).show();
            Button btn = (Button) findViewById(R.id.btn_aboardconfirmed);
            btn.setText("\tAlready aboard\t");
            btn.setEnabled(false);
            Button btn_pay = (Button) findViewById(R.id.btn_payconfirmed);
            btn_pay.setEnabled(true);
        }
        else {
            Toast.makeText(this, "Network error", Toast.LENGTH_SHORT).show();
        }
    }

    public void payConfirmed (View view) {
        Intent intent = new Intent(this, MakePayment.class);
        intent.putExtra("carpoolid", carpoolid);
        intent.putExtra("drivername", carpool.getDrivername());
        intent.putExtra("driveravatar", carpool.getDriveravatar());
        intent.putExtra("price", carpool.getPrice());
        startActivity(intent);
    }

    public void reviewConfirmed (View view) {
        int valid = Carpool.reviewCheck(GlobalVariables.user_name, carpoolid);
        if(valid == 0) {
            Intent intent = new Intent(this, Review.class);
            intent.putExtra("drivername", carpool.getDrivername());
            intent.putExtra("driveravatar", carpool.getDriveravatar());
            startActivity(intent);
        }
        else if(valid == 1){
            Toast.makeText(this, "Can not write review. Please make your payment first.", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "Network error", Toast.LENGTH_SHORT).show();
        }
    }

    public void naviTrip (View view) {
        //carpool.getDepart_loc();
        Intent navigation = new Intent(
                Intent.ACTION_VIEW,
                Uri.parse("google.navigation:q="+String.valueOf(carpool.getDesti_lat())+","+String.valueOf(carpool.getDesti_lng())+"&mode=d"));
        navigation.setPackage("com.google.android.apps.maps");
        startActivity(navigation);
    }

    public void confirmPaymentTrip (View view) {
        Intent intent = new Intent(this, Payment.class);
        intent.putExtra("carpoolid", carpoolid);
        startActivity(intent);
    }
}