package com.example.helloworld;

import java.util.ArrayList;
import java.util.HashMap;

import com.maker.dao.User;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class friendlist extends ListActivity{
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
	private User u=new User();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.friendlist);
		slidingLayout = (SlidingLayout)findViewById(R.id.slidingLayout_friendlist);
		contentListView = (ListView) findViewById(android.R.id.list); 
		slidingLayout.setScrollEvent(contentListView); 
		Intent intent=this.getIntent();
		u=(User)intent.getSerializableExtra("user");
		menu = (Button)findViewById(R.id.button_menu_friendlist);
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
		ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		HashMap<String,String> map1=new HashMap<String,String>();
		HashMap<String,String> map2=new HashMap<String,String>();
		map1.put("name_friend","A");
		map2.put("name_friend","B");
		list.add(map1);
		list.add(map2);
		SimpleAdapter listAdapter =new SimpleAdapter(this,list,R.layout.friend,new String[]{"name_friend"},new int[]{R.id.name_friend});
		setListAdapter(listAdapter);
		
		menubutton0 = (Button)findViewById(R.id.menuButton0_friendlist);
		menubutton1 = (Button)findViewById(R.id.menuButton1_friendlist);
		menubutton2 = (Button)findViewById(R.id.menuButton2_friendlist);
		menubutton3 = (Button)findViewById(R.id.menuButton3_friendlist);
		menubutton4 = (Button)findViewById(R.id.menuButton4_friendlist);
		menubutton5 = (Button)findViewById(R.id.menuButton5_friendlist);
		menubutton6 = (Button)findViewById(R.id.menuButton6_friendlist);
		menubutton7 = (Button)findViewById(R.id.menuButton7_friendlist);
		menubutton8 = (Button)findViewById(R.id.menuButton8_friendlist);
		menubutton0.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Bundle bundle=new Bundle();
				bundle.putSerializable("user",u);
				Intent intent=new Intent();
				intent.putExtras(bundle);
				intent.setClass(friendlist.this,personalif_user.class);
				friendlist.this.startActivity(intent);
			}
		});
		
		menubutton1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Bundle bundle=new Bundle();
				bundle.putSerializable("user",u);
				Intent intent=new Intent();
				intent.putExtras(bundle);
				intent.setClass(friendlist.this,Main.class);
				friendlist.this.startActivity(intent);
			}
		});
		
		menubutton2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Toast.makeText(partyparticipated.this, "好友列表", Toast.LENGTH_LONG).show();
				slidingLayout.scrollToRightLayout(); 
			}
		});
		menubutton3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(friendlist.this, "消息", Toast.LENGTH_LONG).show();
			}
		});
		
		menubutton4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Bundle bundle=new Bundle();
				bundle.putSerializable("user",u);
				Intent intent=new Intent();
				intent.putExtras(bundle);
				intent.setClass(friendlist.this, partypublished.class);
				friendlist.this.startActivity(intent);
			} 
		});
		
		menubutton5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Bundle bundle=new Bundle();
				bundle.putSerializable("user",u);
				Intent intent=new Intent();
				intent.putExtras(bundle);
				intent.setClass(friendlist.this, partyparticipated.class);
				friendlist.this.startActivity(intent);
			}
		});
		
		menubutton6.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Bundle bundle=new Bundle();
				bundle.putSerializable("user",u);
				Intent intent=new Intent();
				intent.putExtras(bundle);
				intent.setClass(friendlist.this, partytoparticipate.class);
				friendlist.this.startActivity(intent);
			}
		});
		
		menubutton7.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(friendlist.this, "选项", Toast.LENGTH_LONG).show();
			}
		});
		
		menubutton8.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(friendlist.this,MainActivity.class);
				friendlist.this.startActivity(intent);
				finish();
			}
		});
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		if(id==0){
			Toast.makeText(friendlist.this, "A's personalif", Toast.LENGTH_LONG).show();
			Intent intent=new Intent();
			intent.setClass(friendlist.this,personalif_friend.class);
			friendlist.this.startActivity(intent);
		}
		if(id==1){
			Toast.makeText(friendlist.this, "B's personalif", Toast.LENGTH_LONG).show();
		}
	}
	
}