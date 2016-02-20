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
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class ForgotPassword extends AppCompatActivity {

    private EditText username;
    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        username = (EditText) findViewById(R.id.username_forgotpassword);
        email = (EditText) findViewById(R.id.email_forgotpassword);
    }

    public void retrievePassword(View view) {
//        if(validate()) {
//            final Random rand = new Random();
//            int rand_int = rand.nextInt(900000) + 100000; //generate a random 6-digits number as temp password;
//            String temp_password = Integer.toString(rand_int);
//            if(User.retrievePassword(username.getText().toString(), temp_password) &&
//                    sendEmail(email.getText().toString(), username.getText().toString(), temp_password)) {
//                Toast.makeText(this, "An email to retrieve password has been sent to: " + email.getText().toString() + ", please check your mailbox", Toast.LENGTH_SHORT).show();
//            }
//        }

        sendEmail("lizijinbuaa@gmail.com", "John Lee", "123456");
        Toast.makeText(this, "An email to retrieve password has been sent to: " + email.getText().toString() + ", please check your mailbox", Toast.LENGTH_SHORT).show();
    }

    /*
    inform the user with the temp password with email
     */
    private void sendEmail(String email, String username, String temp_password) {
        final String email_content = "Dear " + username + ",\nThe temporary password to your CarLoop account is: " + temp_password
                + ". Please change your password as soon as possible.\n\nBest,\nCarLoop Team\n";
        final String to_email = email;

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
        return true;
    }

}
