package com.buf.carloop;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Message extends Footer {

    private int carpoolid;
    private String drivername;
    private EditText content;
    private ListView listview;
    private List<MessageClass> list;
    private TextView tip;

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
        tip = (TextView) findViewById(R.id.tip_message);

        populateListView();
    }

    private void populateListView() {
        list = MessageClass.getMessageList(carpoolid);
        if(list == null || list.size() == 0) {
            tip.setVisibility(View.VISIBLE);
            tip.setText("No message yet");
        }
        else {
            tip.setVisibility(View.GONE);
            MyListAdapter adapter = new MyListAdapter(this, R.layout.item_message, list);
            listview.setAdapter(adapter);
        }
    }

    private class MyListAdapter extends ArrayAdapter<MessageClass> {
        public static final int TYPE_ME = 0;
        public static final int TYPE_NOT_ME = 1;
        private List<MessageClass> listviewitems;

        public MyListAdapter(Context context, int resource, List<MessageClass> listviewitems) {
            super(context, resource, listviewitems);
            this.listviewitems = listviewitems;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position) {
            if(listviewitems.get(position).getName().equals(GlobalVariables.user_name)) {
                return TYPE_ME;
            }
            else {
                return TYPE_NOT_ME;
            }
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            MessageClass mc = listviewitems.get(position);
            int listViewItemType = getItemViewType(position);
            if(convertView == null) {
                if(listViewItemType == TYPE_ME) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_message_self, null);
                }
                else {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_message, null);
                }
                ImageView avatar = (ImageView) convertView.findViewById(R.id.item_avatar);
                TextView name = (TextView) convertView.findViewById(R.id.item_name);
                TextView isdriver = (TextView) convertView.findViewById(R.id.item_isdriver);
                TextView datetime = (TextView) convertView.findViewById(R.id.item_datetime);
                TextView content = (TextView) convertView.findViewById(R.id.item_content);
                viewHolder = new ViewHolder(avatar, name, isdriver, datetime, content);
                convertView.setTag(viewHolder);
            }
            else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            byte[] avatarimage = mc.getAvatar();
            if (avatarimage != null) {
                Bitmap bm = BitmapFactory.decodeByteArray(avatarimage, 0, avatarimage.length);
                viewHolder.getAvatar().setImageBitmap(bm);
            }
            else {
                viewHolder.getAvatar().setImageResource(R.drawable.default_avatar);
            }
            viewHolder.getName().setText(mc.getName());
            viewHolder.getDatatime().setText(mc.getDatetime());
            viewHolder.getContent().setText(mc.getContent());
            if(mc.getName().equals(drivername)) {
                viewHolder.getIsdriver().setText("Driver");
                viewHolder.getIsdriver().setTextColor(Color.RED);
            }
            else {
                viewHolder.getIsdriver().setVisibility(View.GONE);
            }
            return convertView;
        }
    }

    private class ViewHolder {
        ImageView avatar;
        TextView name;
        TextView isdriver;
        TextView datatime;
        TextView content;

        public ViewHolder(ImageView avatar, TextView name, TextView isdriver, TextView datatime, TextView content) {
            this.avatar = avatar;
            this.name = name;
            this.isdriver = isdriver;
            this.datatime = datatime;
            this.content = content;
        }

        public ImageView getAvatar() {
            return avatar;
        }

        public void setAvatar(ImageView avatar) {
            this.avatar = avatar;
        }

        public TextView getName() {
            return name;
        }

        public void setName(TextView name) {
            this.name = name;
        }

        public TextView getIsdriver() {
            return isdriver;
        }

        public void setIsdriver(TextView isdriver) {
            this.isdriver = isdriver;
        }

        public TextView getDatatime() {
            return datatime;
        }

        public void setDatatime(TextView datatime) {
            this.datatime = datatime;
        }

        public TextView getContent() {
            return content;
        }

        public void setContent(TextView content) {
            this.content = content;
        }
    }

    public void sendMessage(View view) {
        if(!content.getText().toString().equals("")) {
            MessageClass.addMessage(GlobalVariables.user_name, content.getText().toString(), carpoolid);
        }
        content.setText("");
        populateListView();
    }

    public void finishActivity(View view) {
        finish();
    }

}
