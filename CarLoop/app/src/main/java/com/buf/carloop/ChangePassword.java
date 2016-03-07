package com.buf.carloop;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePassword extends Footer {

    private EditText oldpassword;
    private EditText newpassword;
    private EditText confirm_newpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup vg = (ViewGroup) findViewById(R.id.content);
        ViewGroup.inflate(this, R.layout.activity_change_password, vg);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        oldpassword = (EditText) findViewById(R.id.old_password);
        newpassword = (EditText) findViewById(R.id.new_password);
        confirm_newpassword = (EditText) findViewById(R.id.confirm_new_password);
    }

    public void updatePassword(View view) {
        if(validate() && User.updatePassword(GlobalVariables.user_name, newpassword.getText().toString()) == 0) {
            Toast.makeText(this, "Password updated", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validate() {
        String old_val = oldpassword.getText().toString();
        String new_val = newpassword.getText().toString();
        String confirm_new_val = confirm_newpassword.getText().toString();
        if(old_val.equals("") || new_val.equals("") || confirm_new_val.equals("")) {
            Toast.makeText(this, "Input cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!new_val.equals(confirm_new_val)) {
            Toast.makeText(this, "New passwords do not match", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!User.checkPassword(GlobalVariables.user_name, old_val)) {
            Toast.makeText(this, "Old password is not correct", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            return true;
        }
    }
}

