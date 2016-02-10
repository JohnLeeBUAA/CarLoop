package com.example.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class personalif_for_publisher extends Activity{
	private Button bt_back;
	private Button bt_delete;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.personalif_friend);
		bt_back = (Button)findViewById(R.id.button_back_personalif_friend);
		bt_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
				
			}
		});
		bt_delete=(Button)findViewById(R.id.button_delete_friend);
		bt_delete.setVisibility(View.GONE);				//从服务器获取是否为好友，if ...else...
	}
	
}