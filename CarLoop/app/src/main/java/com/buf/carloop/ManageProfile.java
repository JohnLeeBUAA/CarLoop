package com.buf.carloop;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
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
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ManageProfile extends Footer {

    private ImageView avatar;
    private TextView username;
    private TextView email;
    private EditText phone;
    private EditText description;
    private RadioButton male;
    private RadioButton female;
    private byte[] avatarimage;

    private Button btn;
    private ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup vg = (ViewGroup) findViewById(R.id.content);
        ViewGroup.inflate(this, R.layout.activity_manage_profile, vg);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btn = (Button) findViewById(R.id.btn_manageprofile);
        bar = (ProgressBar) findViewById(R.id.bar_manageprofile);

        bar.setVisibility(View.GONE);

        avatar = (ImageView) findViewById(R.id.avatar_manage_profile);
        username = (TextView) findViewById(R.id.username_manage_profile);
        email = (TextView) findViewById(R.id.email_manage_profile);
        phone = (EditText) findViewById(R.id.phone_manage_profile);
        description = (EditText) findViewById(R.id.description_manage_profile);
        male = (RadioButton) findViewById(R.id.male_manage_profile);
        female = (RadioButton) findViewById(R.id.female_manage_profile);

        User user = User.getUser(GlobalVariables.user_name);
        if(user != null) {
            username.setText(user.getU_name());
            email.setText(user.getU_email());
            if(user.getU_phone() != null) phone.setText(user.getU_phone());
            if(user.getU_description() != null) description.setText(user.getU_description());
            if(user.getU_gender() != null) {
                if(user.getU_gender().equals("male")) male.setChecked(true);
                else if(user.getU_gender().equals("female")) female.setChecked(true);
            }
            avatarimage = null;
            /*
            if(user.getU_avatar() == null) avatarimage = null;
            else {
                Bitmap bm = BitmapFactory.decodeByteArray(avatarimage, 0, avatarimage.length);
                if(!bm.equals(null)) avatar.setImageBitmap(bm);
            }
            */
        }
    }

    public void selectImage(View view) {
        Intent intent;
        if (Build.VERSION.SDK_INT < 19){
            intent = new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent, 123);
        } else {
            intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            startActivityForResult(intent, 123);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 123) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    Uri selectedImage = data.getData();
                    InputStream imageStream = getContentResolver().openInputStream(selectedImage);
                    Bitmap bm = BitmapFactory.decodeStream(imageStream);
                    avatar.setImageBitmap(bm);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    avatarimage = stream.toByteArray();
                } catch (FileNotFoundException e) {

                }
            }
        }
    }

    public void updateProfile(View view) {
        btn.setVisibility(View.GONE);
        bar.setVisibility(View.VISIBLE);
        String gender = null;
        if(male.isChecked()) gender = "male";
        else if(female.isChecked()) gender = "female";
        int status = User.updateUser(GlobalVariables.user_name, avatarimage, gender, phone.getText().toString(), description.getText().toString());
        if(status == 0) {
            Toast.makeText(this, "Profile updated", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ManageProfile.class);
            startActivity(intent);
        }
        else if(status == 1) {
            Toast.makeText(this, "Update profile failed", Toast.LENGTH_SHORT).show();
            btn.setVisibility(View.VISIBLE);
            bar.setVisibility(View.GONE);
        }
        else {
            Toast.makeText(this, "Network error", Toast.LENGTH_SHORT).show();
            btn.setVisibility(View.VISIBLE);
            bar.setVisibility(View.GONE);
        }
    }
}
