package com.buf.carloop;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mysql.jdbc.Util;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;

import java.math.BigDecimal;

public class MakePayment extends Footer {
    private ImageView passengeravatar;
    private ImageView driveravatar;
    private TextView drivername;
    private TextView account;
    private TextView price;
    private byte[] avatarimage;
    private String drivername_val;
    private String account_val;
    private int price_val;
    private static final int REQUEST_CODE_PAYMENT = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup vg = (ViewGroup) findViewById(R.id.content);
        ViewGroup.inflate(this, R.layout.activity_make_payment, vg);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        passengeravatar = (ImageView) findViewById(R.id.passengeravatar_makepayment);
        driveravatar = (ImageView) findViewById(R.id.driveravatar_makepayment);
        drivername = (TextView) findViewById(R.id.drivername_makepayment);
        account = (TextView) findViewById(R.id.driveraccount_makepayment);
        price = (TextView) findViewById(R.id.price_makepayment);

        byte[] avatarimage_p = User.selectSQLBlob(GlobalVariables.user_name);
        if(avatarimage_p != null) {
            Bitmap bm = BitmapFactory.decodeByteArray(avatarimage_p, 0, avatarimage_p.length);
            passengeravatar.setImageBitmap(bm);
        }
        else {
            passengeravatar.setImageResource(R.drawable.default_avatar);
        }

        drivername_val = getIntent().getStringExtra("drivername");
        drivername.setText(drivername_val);
        avatarimage = getIntent().getByteArrayExtra("driveravatar");
        if (avatarimage != null) {
            Bitmap bm = BitmapFactory.decodeByteArray(avatarimage, 0, avatarimage.length);
            driveravatar.setImageBitmap(bm);
        }
        else {
            driveravatar.setImageResource(R.drawable.default_avatar);
        }

        account_val = Vehicle.getDriverPaypal(drivername_val);
        if(account_val == null) {
            Toast.makeText(this, "Can not make payment. Driver did not add account info", Toast.LENGTH_SHORT).show();
        }
        else if(account_val.equals("")) {
            Toast.makeText(this, "Network error. Failed to get Driver's paypal account.", Toast.LENGTH_SHORT).show();
        }
        price_val = getIntent().getIntExtra("price", 0);
        account.setText(account_val);
        price.setText("$" + Integer.toString(price_val));

        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, paypalConfig);
        startService(intent);
    }

    private static PayPalConfiguration paypalConfig = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(GlobalVariables.paypal_sdk_id)
            .acceptCreditCards(true)
                    // The following are only used in PayPalFuturePaymentActivity.
            .merchantName("Carpool")
            .merchantPrivacyPolicyUri(
                    Uri.parse("https://www.paypal.com/webapps/mpp/ua/privacy-full"))
            .merchantUserAgreementUri(
                    Uri.parse("https://www.paypal.com/webapps/mpp/ua/useragreement-full"));

    public void cancelPayment(View view) {
        finish();
    }

    public void makePayment(View view) {
        PayPalPayment thingToBuy = new PayPalPayment(new BigDecimal(price_val),"CAD", account_val,
                PayPalPayment.PAYMENT_INTENT_SALE);

        Intent intent = new Intent(MakePayment.this, PaymentActivity.class);

        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, paypalConfig);

        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, thingToBuy);

        startActivityForResult(intent, REQUEST_CODE_PAYMENT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PAYMENT) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(getApplicationContext(), "Payment done succesfully", Toast.LENGTH_LONG).show();
                int status = PassengerCarpool.addPayment(GlobalVariables.user_name, getIntent().getIntExtra("carpoolid", -1));
                if(status == 0) {
                    Intent intent = new Intent(this, Review.class);
                    intent.putExtra("drivername", drivername_val);
                    intent.putExtra("driveravatar", avatarimage);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Network error", Toast.LENGTH_SHORT).show();
                }
            }
            else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "Payment canceled", Toast.LENGTH_LONG).show();
            }
            else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                Toast.makeText(getApplicationContext(), "Payment failed", Toast.LENGTH_LONG).show();
            }
        }
    }

}
