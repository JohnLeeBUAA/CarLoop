package com.buf.carloop;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class Message extends Footer {

    private int carpoolid;
    private String drivername;
    private EditText content;
    private ListView listview;
    private List<MessageClass> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup vg = (ViewGroup) findViewById(R.id.content);
        ViewGroup.inflate(this, R.layout.activity_message, vg);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        carpoolid = getIntent().getIntExtra("carpoolid", -1);
        drivername = getIntent().getStringExtra("drivername");
        content = (EditText) findViewById(R.id.content_message);
        listview = (ListView) findViewById(R.id.list_message);
        list = MessageClass.getMessageList(carpoolid);

        populateListView();
    }

    private void populateListView() {
        ArrayAdapter<MessageClass> adapter = new MyListAdapter();
        listview.setAdapter(adapter);
    }

    private class MyListAdapter extends ArrayAdapter<MessageClass> {
        public MyListAdapter() {
            super(Message.this, R.layout.item_message, list);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.item_message, parent, false);
            }

            MessageClass mc = list.get(position);

            ImageView avatar = (ImageView)itemView.findViewById(R.id.item_avatar);
            byte[] avatarimage = mc.getAvatar();
            if (avatarimage != null) {
                Bitmap bm = BitmapFactory.decodeByteArray(avatarimage, 0, avatarimage.length);
                avatar.setImageBitmap(bm);
            }
            else {
                avatar.setImageResource(R.drawable.default_avatar);
            }

            TextView name = (TextView) itemView.findViewById(R.id.item_name);
            name.setText(mc.getName());

            TextView content = (TextView) itemView.findViewById(R.id.item_content);
            content.setText(mc.getContent());

            TextView datetime = (TextView) itemView.findViewById(R.id.item_datetime);
            datetime.setText(mc.getDatetime());

            TextView tip = (TextView) itemView.findViewById(R.id.item_isdriver);
            if(mc.getName().equals(drivername)) {
                tip.setText("Driver");
                tip.setTextColor(Color.RED);
            }
            else {
                tip.setVisibility(View.GONE);
            }

            return itemView;
        }
    }

    public void sendMessage(View view) {
        if(!content.getText().toString().equals("")) {
            MessageClass.addMessage(GlobalVariables.user_name, content.getText().toString(), carpoolid);
        }
        list = MessageClass.getMessageList(carpoolid);
        populateListView();
    }

}
