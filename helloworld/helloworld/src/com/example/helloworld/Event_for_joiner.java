package com.example.helloworld;

import java.util.ArrayList;
import java.util.HashMap;
import com.example.helloworld.R.id;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class Event_for_joiner extends Activity {
	private TextView text1 = null;
	private TextView text2 = null;
	private TextView text3 = null;
	private TextView text4 = null;
	private TextView text5 = null;
	private TextView text6 = null;
	private Button bt1=null;
	private Button bt2=null;
	private Button bt3=null;
	private final static int dialog_id=1;
	int[] menu_image_array = { R.drawable.ic_launcher,R.drawable.ic_launcher};
	String[] name_array={"A","B"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event);
		text1=(TextView)findViewById(R.id.event_textview1);
		text1.setText("篮球");
		text2=(TextView)findViewById(R.id.event_textview2);
		text2.setText("时间：3:00pm");
		text3=(TextView)findViewById(R.id.event_textview3);
		text3.setText("地点：篮球场");
		text4=(TextView)findViewById(R.id.event_textview4);
		text4.setText("参与人数：9");
		text5=(TextView)findViewById(R.id.event_textview5);
		text5.setText("关键字：体育	篮球");
		text6=(TextView)findViewById(R.id.event_textview6);
		text6.setText("备注：");
		bt1=(Button)findViewById(R.id.event_button1);
		bt1.setVisibility(View.GONE);
		bt2=(Button)findViewById(R.id.event_button2);
		bt2.setText("已经加入的同学");
		bt2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(dialog_id);
			}
		});
		bt3=(Button)findViewById(id.button_back_event);
		bt3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
				/**Intent intent = new Intent();
				intent.setClass(Event0.this, Main.class);
				Event0.this.startActivity(intent);**/
			}
		});
	}
	private SimpleAdapter getMenuAdapter(String[] menuNameArray,
            int[] imageResourceArray) {
        ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < menuNameArray.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("itemImage", imageResourceArray[i]);
            map.put("itemText", menuNameArray[i]);
            data.add(map);
        }
        SimpleAdapter simperAdapter = new SimpleAdapter(this, data,
                R.layout.joined_list, new String[] { "itemImage", "itemText" },
                new int[] { R.id.photo, R.id.name_joined_list });
        return simperAdapter;
    }
	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		Dialog dialog=null;
		switch(id){
		case dialog_id:
			android.app.AlertDialog.Builder builder=new AlertDialog.Builder(this);
			builder.setTitle("已参加的同学");
			builder.setAdapter(getMenuAdapter(name_array, menu_image_array), listener);
			dialog=builder.create();
			break;
		}
		return dialog;
	}
	DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			if(which==0)
			{
				Intent intent=new Intent();
				intent.setClass(Event_for_joiner.this, personalif_for_joiner.class);
				Event_for_joiner.this.startActivity(intent);
			}
		}
	};
}