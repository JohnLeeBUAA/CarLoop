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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class Review extends Footer {

    private ImageView driveravatar;
    private TextView drivername;
    private RatingBar rate;
    private EditText review;
    private double rate_val;
    private String review_val;
    private Button btn;
    private ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup vg = (ViewGroup) findViewById(R.id.content);
        ViewGroup.inflate(this, R.layout.activity_review, vg);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.setTitle("Rate And Review");

        driveravatar = (ImageView) findViewById(R.id.driveravatar_review);
        drivername = (TextView) findViewById(R.id.drivername_review);
        rate = (RatingBar) findViewById(R.id.rate_review);
        review = (EditText) findViewById(R.id.review_review);
        btn = (Button) findViewById(R.id.btn_review);
        bar = (ProgressBar) findViewById(R.id.bar_review);
        bar.setVisibility(View.GONE);

        drivername.setText(getIntent().getStringExtra("drivername"));
        byte[] avatarimage = getIntent().getByteArrayExtra("driveravatar");
        if (avatarimage != null) {
            Bitmap bm = BitmapFactory.decodeByteArray(avatarimage, 0, avatarimage.length);
            driveravatar.setImageBitmap(bm);
        }
        else {
            driveravatar.setImageResource(R.drawable.default_avatar);
        }
    }

    public void submitReview(View view) {
        bar.setVisibility(View.VISIBLE);
        btn.setVisibility(View.GONE);
        if(validate()) {
            int status = ReviewClass.addReview(GlobalVariables.user_name, getIntent().getStringExtra("drivername"), rate_val, review_val);
            if(status == 0) {
                Toast.makeText(this, "Your review has been submitted. Carpool trip completed.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, CarpoolList.class);
                intent.putExtra("type", "Confirmed");
                startActivity(intent);
                finish();
            }
            else {
                Toast.makeText(this, "Network error", Toast.LENGTH_SHORT).show();
                btn.setVisibility(View.VISIBLE);
                bar.setVisibility(View.GONE);
            }
        }
        else {
            btn.setVisibility(View.VISIBLE);
            bar.setVisibility(View.GONE);
        }
    }

    private boolean validate() {
        rate_val = rate.getRating();
        review_val = review.getText().toString();
        if(rate_val == 0 || review_val.equals("")) {
            Toast.makeText(this, "Input can not be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            return true;
        }
    }

}
