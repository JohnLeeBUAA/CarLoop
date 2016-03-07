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
import android.widget.EditText;
import android.widget.Toast;

public class SignIn extends AppCompatActivity {

    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        username = (EditText)findViewById(R.id.username_signin);
        password = (EditText)findViewById(R.id.password_signin);
    }

    public void signIn(View view) {
        if(validate()) {
            SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("user_name", GlobalVariables.user_name);
            editor.putInt("user_identity", GlobalVariables.user_identity);
            editor.commit();
            Toast.makeText(this, "Sign in success", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, CarpoolNew.class);
            intent.putExtra("type", "Create");
            startActivity(intent);
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
        String username_val = username.getText().toString();
        String password_val = password.getText().toString();
        if(username_val.equals("") || password_val.equals("")) {
            Toast.makeText(this, "Input cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            int validate_status = User.signIn(username_val, password_val);
            if(validate_status == 0) {
                Toast.makeText(this, "User: " + username_val + " does not exist", Toast.LENGTH_SHORT).show();
                return false;
            }
            else if(validate_status == 1) {
                Toast.makeText(this, "Wrong password", Toast.LENGTH_SHORT).show();
                return false;
            }
            else {
                return true;
            }
        }
    }

}
