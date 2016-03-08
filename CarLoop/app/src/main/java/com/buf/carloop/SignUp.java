package com.buf.carloop;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private EditText confirm_password;
    private EditText email;
    private RadioButton driver;
    private RadioButton passenger;
    private Button btn;
    private ProgressBar bar;
    private String username_val;
    private String password_val;
    private String confirm_password_val;
    private String email_val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        username = (EditText)findViewById(R.id.username_signup);
        password = (EditText)findViewById(R.id.password_signup);
        confirm_password = (EditText)findViewById(R.id.confirm_password_signup);
        email = (EditText)findViewById(R.id.email_signup);
        driver = (RadioButton)findViewById(R.id.driver_signup);
        passenger = (RadioButton)findViewById(R.id.passenger_signup);

        btn = (Button) findViewById(R.id.btn_signup);
        bar = (ProgressBar) findViewById(R.id.bar_signup);

        bar.setVisibility(View.GONE);
    }

    public void signUp(View view) {
        btn.setVisibility(View.GONE);
        bar.setVisibility(View.VISIBLE);
        if(validate()) {
            int status = User.signUp(username_val, password_val, email_val);
            if(status == 0) {
                SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("user_name", GlobalVariables.user_name);
                editor.putInt("user_identity", GlobalVariables.user_identity);
                editor.commit();
                Toast.makeText(this, "Sign up success", Toast.LENGTH_SHORT).show();

                if(driver.isChecked()) {
                    Intent intent = new Intent(this, DriverVehicleInfo.class);
                    intent.putExtra("type", "Add");
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(this, CarpoolList.class);
                    intent.putExtra("type", "Confirmed");
                    startActivity(intent);
                }
            }
            else if(status == 1) {
                Toast.makeText(this, "User: " + username_val + " already exist", Toast.LENGTH_SHORT).show();
                btn.setVisibility(View.VISIBLE);
                bar.setVisibility(View.GONE);
            }
            else if(status == 2) {
                Toast.makeText(this, "Email: " + email_val + " already exist", Toast.LENGTH_SHORT).show();
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

    public void back(View view){
        Intent intent = new Intent(this, SignIn.class);
        startActivity(intent);
    }

    private boolean validate() {
        username_val = username.getText().toString();
        password_val = password.getText().toString();
        confirm_password_val = confirm_password.getText().toString();
        email_val = email.getText().toString();
        if(username_val.equals("") || password_val.equals("") || confirm_password_val.equals("") || email_val.equals("")) {
            Toast.makeText(this, "Input cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!password_val.equals(confirm_password_val)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
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
