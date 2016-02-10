package com.example.helloworld;

import java.util.ArrayList;
import java.util.HashMap;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class partytoparticipate extends ListActivity{
	//ListView list;
	public static partypublished temp_activity;
	public Button menu = null;
	private SlidingLayout slidingLayout;
	private ListView contentListView; 
	private Button menubutton0;
	private Button menubutton1;
	private Button menubutton2;
	private Button menubutton3;
	private Button menubutton4;
	private Button menubutton5;
	private Button menubutton6;
	private Button menubutton7;
	private Button menubutton8;
	private TextView title_text;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.partypublished);
		slidingLayout = (SlidingLayout)findViewById(R.id.slidingLayout_published);
		contentListView = (ListView) findViewById(android.R.id.list); 
		slidingLayout.setScrollEvent(contentListView); 
		menu = (Button)findViewById(R.id.button_menu_partypulished);
		menu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (slidingLayout.isLeftLayoutVisible()) {  
	                slidingLayout.scrollToRightLayout();  
	            } 
				else {  
	                slidingLayout.scrollToLeftLayout();  
	            } 
			}
		});
		//list=(ListView)findViewById(id.list2);
		ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		HashMap<String,String> map1=new HashMap<String,String>();
		HashMap<String,String> map2=new HashMap<String,String>();
		map1.put("user_name", "篮球");
		map1.put("user_event", "13/11/11 15:00");
		map2.put("user_name", "公益活动");
		map2.put("user_event", "13/11/11 12:00");
		list.add(map1);
		list.add(map2);
		SimpleAdapter listAdapter =new SimpleAdapter(this,list,R.layout.user,new String[]{"user_name","user_event"},new int[]{R.id.user_name,R.id.user_event});
		setListAdapter(listAdapter);
		
		menubutton0 = (Button)findViewById(R.id.menuButton0_partypublished);
		menubutton1 = (Button)findViewById(R.id.menuButton1_partypublished);
		menubutton2 = (Button)findViewById(R.id.menuButton2_partypublished);
		menubutton3 = (Button)findViewById(R.id.menuButton3_partypublished);
		menubutton4 = (Button)findViewById(R.id.menuButton4_partypublished);
		menubutton5 = (Button)findViewById(R.id.menuButton5_partypublished);
		menubutton6 = (Button)findViewById(R.id.menuButton6_partypublished);
		menubutton7 = (Button)findViewById(R.id.menuButton7_partypublished);
		menubutton8 = (Button)findViewById(R.id.menuButton8_partypublished);
		title_text=(TextView)findViewById(R.id.event_published);
		title_text.setText("即将参加的聚");
		menubutton0.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(partytoparticipate.this,personalif_user.class);
				partytoparticipate.this.startActivity(intent);
			}
		});
		
		menubutton1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(partytoparticipate.this,Main.class);
				partytoparticipate.this.startActivity(intent);
			}
		});
		
		menubutton2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Toast.makeText(partyparticipated.this, "好友列表", Toast.LENGTH_LONG).show();
				Intent intent=new Intent();
				intent.setClass(partytoparticipate.this, friendlist.class);
				partytoparticipate.this.startActivity(intent);
			}
		});
		
		menubutton3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(partytoparticipate.this, "消息", Toast.LENGTH_LONG).show();
			}
		});
		
		menubutton4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(partytoparticipate.this, partypublished.class);
				partytoparticipate.this.startActivity(intent);
			}
		});
		
		menubutton5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Toast.makeText(Main.this, "参加过的聚", Toast.LENGTH_LONG).show();
				Intent intent=new Intent();
				intent.setClass(partytoparticipate.this, partyparticipated.class);
				partytoparticipate.this.startActivity(intent);
			}
		});
		
		menubutton6.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				slidingLayout.scrollToRightLayout();
			}
		});
		
		menubutton7.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(partytoparticipate.this, "选项", Toast.LENGTH_LONG).show();
			}
		});
		
		menubutton8.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(partytoparticipate.this,MainActivity.class);
				partytoparticipate.this.startActivity(intent);
			}
		});
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		if(id==0)
		{
			Intent intent=new Intent();
			intent.setClass(partytoparticipate.this, Event_for_joiner.class);
			partytoparticipate.this.startActivity(intent);
		}
	}
	
}
	
