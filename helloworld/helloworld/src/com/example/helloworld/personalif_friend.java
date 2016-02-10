package com.example.helloworld;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class personalif_friend extends Activity{
	private Button bt_back;
	private Button bt_delete;
	private Button bt_useless;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.personalif_friend);
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		bt_useless=(Button)findViewById(R.id.button_for_publisher_personalif_friend);
		bt_useless.setVisibility(View.GONE);
		bt_back = (Button)findViewById(R.id.button_back_personalif_friend);
		bt_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
				/**Intent intent=new Intent();
				intent.setClass(personalif_friend.this,friendlist.class );
				personalif_friend.this.startActivity(intent);**/
			}
		});
		bt_delete=(Button)findViewById(R.id.button_delete_friend);
		bt_delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				builder.setMessage("确定删除该好友么？").setPositiveButton("是", new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog,int which){
					Intent intent=new Intent();
					intent.setClass(personalif_friend.this, friendlist.class);
					personalif_friend.this.startActivity(intent);}}).setNegativeButton("否", new DialogInterface.OnClickListener(){
						public void onClick(DialogInterface dialog,int which){
							//null
						}});
				AlertDialog ad = builder.create();
				ad.show();
			}
		});
	}
	
}