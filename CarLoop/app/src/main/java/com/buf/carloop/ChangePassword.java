package com.buf.carloop;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class ChangePassword extends Footer {

    private EditText oldpassword;
    private EditText newpassword;
    private EditText confirm_newpassword;
    private String old_val;
    private String new_val;
    private String confirm_new_val;

    private Button btn;
    private ProgressBar bar;

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

        btn = (Button) findViewById(R.id.btn_changepassword);
        bar = (ProgressBar) findViewById(R.id.bar_changepassword);

        bar.setVisibility(View.GONE);
    }

    public void updatePassword(View view) {
        btn.setVisibility(View.GONE);
        bar.setVisibility(View.VISIBLE);
        if(validate()) {
            int status = User.updatePassword(GlobalVariables.user_name, new_val, old_val);
            if(status == 0) {
                Toast.makeText(this, "Password updated", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, Settings.class);
                startActivity(intent);
            }
            else if(status == 4) {
                Toast.makeText(this, "Old password is not correct", Toast.LENGTH_SHORT).show();
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

    private boolean validate() {
        old_val = oldpassword.getText().toString();
        new_val = newpassword.getText().toString();
        confirm_new_val = confirm_newpassword.getText().toString();
        if(old_val.equals("") || new_val.equals("") || confirm_new_val.equals("")) {
            Toast.makeText(this, "Input cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!new_val.equals(confirm_new_val)) {
            Toast.makeText(this, "New passwords do not match", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            return true;
        }
    }
}

