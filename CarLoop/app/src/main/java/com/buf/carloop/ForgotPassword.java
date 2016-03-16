package com.buf.carloop;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Random;

public class ForgotPassword extends AppCompatActivity {

    private EditText username;
    private EditText email;
    private String username_val;
    private String email_val;

    private Button btn;
    private ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        username = (EditText) findViewById(R.id.username_forgotpassword);
        email = (EditText) findViewById(R.id.email_forgotpassword);

        btn = (Button) findViewById(R.id.btn_forgotpassword);
        bar = (ProgressBar) findViewById(R.id.bar_forgotpassword);

        bar.setVisibility(View.GONE);
    }

    public void retrievePassword(View view) {
        btn.setVisibility(View.GONE);
        bar.setVisibility(View.VISIBLE);
        if(validate()) {
            final Random rand = new Random();
            int rand_int = rand.nextInt(900000) + 100000; //generate a random 6-digits number as temp password;
            String temp_password = Integer.toString(rand_int);
            int status = User.retrievePassword(username_val, email_val, temp_password);
            if(status == 0) {
                sendEmail(email.getText().toString(), username.getText().toString(), temp_password);
                Toast.makeText(this, "An email to retrieve password has been sent to: "
                        + email.getText().toString() + ", please check your mailbox", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, SignIn.class);
                startActivity(intent);
            }
            else if(status == 4) {
                Toast.makeText(this, "Invalid username and/or email", Toast.LENGTH_SHORT).show();
                btn.setVisibility(View.VISIBLE);
                bar.setVisibility(View.GONE);
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

    private void sendEmail(String email, String username, String temp_password) {

        final String to_email = email;
        final String email_content = "Dear " + username + ",\nThe temporary password to your CarLoop account is: " + temp_password
                + ". Please change your password as soon as possible.\n\nBest,\nCarLoop Team\n";

        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    Mail m = new Mail("carloop.service@gmail.com", "carloopservice");

                    String[] toArr = {to_email};
                    m.setTo(toArr);
                    m.setFrom("carloop.service@gmail.com");
                    m.setSubject("Retrieve password to your CarLoop account");
                    m.setBody(email_content);
                    m.send();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    private boolean validate() {
        username_val = username.getText().toString();
        email_val = email.getText().toString();

        if(username_val.equals("") || email_val.equals("")) {
            Toast.makeText(this, "Input cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email_val).matches()) {
            Toast.makeText(this, "Incorrect email format", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            return true;
        }
    }

}
