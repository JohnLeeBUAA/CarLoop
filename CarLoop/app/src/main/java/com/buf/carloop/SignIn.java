package com.buf.carloop;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedOutputStream;

public class SignIn extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private String username_val;
    private String password_val;
    private Button btn;
    private ProgressBar bar;
    private TextView signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        username = (EditText)findViewById(R.id.username_signin);
        password = (EditText)findViewById(R.id.password_signin);
        btn = (Button) findViewById(R.id.btn_signin);
        bar = (ProgressBar) findViewById(R.id.bar_signin);
        bar.setVisibility(View.GONE);
        signup = (TextView) findViewById(R.id.signup);
        signup.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
    }

    public void signIn(View view) {
        btn.setVisibility(View.GONE);
        bar.setVisibility(View.VISIBLE);
        if(validate()) {
            int status = User.signIn(username_val, password_val);
            if(status == 0) {
                SharedPreferences sharedPref = getSharedPreferences("CarLoopPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("user_name", GlobalVariables.user_name);
                editor.putInt("user_identity", GlobalVariables.user_identity);
                editor.commit();
                if(GlobalVariables.user_identity == 1) {
                    Intent intent = new Intent(this, CarpoolNew.class);
                    intent.putExtra("type", "Create");
                    startActivity(intent);
                    finish();
                }
                else {
                    Intent intent = new Intent(this, CarpoolNew.class);
                    intent.putExtra("type", "Search");
                    startActivity(intent);
                    finish();
                }

            }
            else if(status == 1) {
                Toast.makeText(this, "Username does not exist", Toast.LENGTH_SHORT).show();
                btn.setVisibility(View.VISIBLE);
                bar.setVisibility(View.GONE);
            }
            else if(status == 2) {
                Toast.makeText(this, "Wrong password", Toast.LENGTH_SHORT).show();
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

    public void signUp(View view) {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }

    public void forgotPassword(View view) {
        Intent intent = new Intent(this, ForgotPassword.class);
        startActivity(intent);
    }

    private boolean validate() {
        username_val = username.getText().toString();
        password_val = password.getText().toString();
        if(username_val.equals("") || password_val.equals("")) {
            Toast.makeText(this, "Input cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            return true;
        }
    }

}
