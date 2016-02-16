package com.uwaterloo.buf.carloop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class sign_up_activity extends Activity {
    private EditText account = null;
    private EditText password = null;
    private EditText passwordConfirm = null;
    private EditText name = null;
    private RadioGroup identityGroup = null;
    private RadioButton driverButton = null;
    private RadioButton passengerButton = null;
    private RadioButton bothButton = null;
    private Button signUpButton = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        account = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        passwordConfirm = (EditText) findViewById(R.id.password_confirm);
        name = (EditText) findViewById(R.id.username);
        identityGroup = (RadioGroup) findViewById(R.id.radioGroup);
        driverButton = (RadioButton) findViewById(R.id.radioButton1);
        passengerButton = (RadioButton) findViewById(R.id.radioButton2);
        bothButton = (RadioButton) findViewById(R.id.radioButton3);
        signUpButton = (Button) findViewById(R.id.sign_up_button);

        identityGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (driverButton.getId() == checkedId)
                    Toast.makeText(sign_up_activity.this, "Driver", Toast.LENGTH_SHORT).show();
                else if (passengerButton.getId() == checkedId)
                    Toast.makeText(sign_up_activity.this, "Passenger", Toast.LENGTH_SHORT).show();
                else if (bothButton.getId() == checkedId)
                    Toast.makeText(sign_up_activity.this, "Both", Toast.LENGTH_SHORT).show();
            }
        });

        signUpButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // if input is valid, then allow user to register, store data to database
                if(validate()){
                    String result = handler();
                    if(result.equals("1"))
                    {
                        Toast.makeText(sign_up_activity.this, "Successfully registered!", Toast.LENGTH_LONG).show();
                        // have signed up, then turn to user page (driver has to submit vehicle information )
                        Intent intent = new Intent();
                        intent.setClass(sign_up_activity.this, sign_up_activity.class);
                        sign_up_activity.this.startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(sign_up_activity.this, "Failed to create an account.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    // I wrote this to make sure the input is valid
    private boolean validate(){
        // email cannot be null
        String acc = account.getText().toString();
        if(acc.equals("")){
            Toast.makeText(sign_up_activity.this, "You should have a non-empty input.", Toast.LENGTH_LONG).show();
            return false;
        }
        // username cannot be null
        String nam = name.getText().toString();
        if(nam.equals("")){
            Toast.makeText(sign_up_activity.this, "You should have a valid username.", Toast.LENGTH_LONG).show();
            return false;
        }
        // password should match
        String pwd = password.getText().toString();
        String pwd_confirm = passwordConfirm.getText().toString();
        if(pwd.equals("")){
            Toast.makeText(sign_up_activity.this, "Password cannot be empty.", Toast.LENGTH_LONG).show();
            return false;
        }
        else if(!pwd.equals(pwd_confirm)){
            Toast.makeText(sign_up_activity.this, "Two passwords do not match.", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    private String handler(){
        // this is your work
        // to store data into database
        return null;
    }
}
