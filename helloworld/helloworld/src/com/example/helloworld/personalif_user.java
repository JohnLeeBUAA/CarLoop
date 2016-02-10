package com.example.helloworld;

import com.example.helloworld.R.id;
import com.maker.dao.User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class personalif_user extends Activity{
	private Button button_modify;
	private TextView tv0;
	private TextView tv1;
	private TextView tv2;
	private TextView tv3;
	private TextView tv4;
	private TextView tv5;
	private TextView tv6;
	
	public Button menu = null;
	private SlidingLayout slidingLayout;
	private RelativeLayout contentLayout; 
	private Button menubutton0;
	private Button menubutton1;
	private Button menubutton2;
	private Button menubutton3;
	private Button menubutton4;
	private Button menubutton5;
	private Button menubutton6;
	private Button menubutton7;
	private Button menubutton8;
	private Button create_qrcode;
	private User u=new User();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.personalif_user);
		Intent intent=this.getIntent();
		u=(User)intent.getSerializableExtra("user");
		
		slidingLayout = (SlidingLayout)findViewById(R.id.slidingLayout_personalif_user);
		contentLayout = (RelativeLayout)findViewById(R.id.personalif_content); 
		slidingLayout.setScrollEvent(contentLayout);
		tv0 = (TextView)findViewById(id.textView_username);
		tv0.setText(u.getUsername());
		tv1 = (TextView)findViewById(id.textView1_school);
		tv1.setText(u.getSchool());
		tv2 = (TextView)findViewById(id.textView1_college);
		tv2.setText(u.getCollege());
		tv3 = (TextView)findViewById(id.textView1_grade);
		tv3.setText(u.getGrade());
		tv4 = (TextView)findViewById(id.textView1_hobby);
		tv4.setText(u.getHobby());
		tv5 = (TextView)findViewById(id.textView1_qianming);
		tv5.setText(u.getQianming());
		tv6 = (TextView)findViewById(id.textView1_judou);
		tv6.setText(String.valueOf(u.getJudou()));
		button_modify = (Button) findViewById(id.button_modify);
		button_modify.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Bundle bundle=new Bundle();
				bundle.putSerializable("user",u);
				Intent intent=new Intent();
				intent.putExtras(bundle);
				intent.setClass(personalif_user.this, personalif_modify.class);
				personalif_user.this.startActivity(intent);
			}
		});
		menu = (Button)findViewById(id.button_menu_personalif);
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
		create_qrcode=(Button)findViewById(R.id.button_qrcode);
		create_qrcode.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Bundle bundle=new Bundle();
				bundle.putSerializable("user", u);
				Intent intent=new Intent();
				intent.putExtras(bundle);
				intent.setClass(personalif_user.this, myqrcode.class);
				personalif_user.this.startActivity(intent);
			}
		});
		
		menubutton0 = (Button)findViewById(R.id.menuButton0_personalif);
		menubutton1 = (Button)findViewById(R.id.menuButton1_personalif);
		menubutton2 = (Button)findViewById(R.id.menuButton2_personalif);
		menubutton3 = (Button)findViewById(R.id.menuButton3_personalif);
		menubutton4 = (Button)findViewById(R.id.menuButton4_personalif);
		menubutton5 = (Button)findViewById(R.id.menuButton5_personalif);
		menubutton6 = (Button)findViewById(R.id.menuButton6_personalif);
		menubutton7 = (Button)findViewById(R.id.menuButton7_personalif);
		menubutton8 = (Button)findViewById(R.id.menuButton8_personalif);
		
		menubutton0.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				slidingLayout.scrollToRightLayout();  
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
				intent.setClass(personalif_user.this,Main.class);
				personalif_user.this.startActivity(intent);
				finish();
			}
		});
		
		menubutton2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Bundle bundle=new Bundle();
				bundle.putSerializable("user",u);
				Intent intent=new Intent();
				intent.putExtras(bundle);
				intent.setClass(personalif_user.this, friendlist.class);
				personalif_user.this.startActivity(intent);
				finish();
			}
		});
		
		menubutton3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(personalif_user.this, "消息", Toast.LENGTH_LONG).show();
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
				intent.setClass(personalif_user.this, partypublished.class);
				personalif_user.this.startActivity(intent);
				finish();
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
				intent.setClass(personalif_user.this,partyparticipated.class);
				personalif_user.this.startActivity(intent);
				finish();
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
				intent.setClass(personalif_user.this, partytoparticipate.class);
				personalif_user.this.startActivity(intent);
				finish();
			}
		});
		
		menubutton7.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(personalif_user.this, "选项", Toast.LENGTH_LONG).show();
			}
		});
		
		menubutton8.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(personalif_user.this,MainActivity.class);
				personalif_user.this.startActivity(intent);
				finish();
			}
		});
		
		
	}
	
}