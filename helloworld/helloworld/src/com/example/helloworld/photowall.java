package com.example.helloworld;



import com.maker.dao.Images;
import com.maker.dao.PhotoWallAdapter;
import com.maker.dao.User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

/**
 * 照片墙主活动，使用GridView展示照片墙。
 * 
 *
 */
public class photowall extends Activity {

	/**
	 * 用于展示照片墙的GridView
	 */
	private GridView mPhotoWall;
	private User u =new User();
	private int eid;
	/**
	 * GridView的适配器
	 */
	private PhotoWallAdapter adapter;
	public static photowall temp_activity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photowall);
		temp_activity=this;
		Intent intent=this.getIntent();
		u=(User)intent.getSerializableExtra("user");
		eid=intent.getIntExtra("eid", -1);System.out.println(eid);
		Images.getImages(eid);
		mPhotoWall = (GridView) findViewById(R.id.photo_wall);
		adapter = new PhotoWallAdapter(this, 0, Images.imageid, mPhotoWall,temp_activity,u);
		mPhotoWall.setAdapter(adapter);
		
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 退出程序时结束所有的下载任务
		adapter.cancelAllTasks();
		Images.imageid.clear();
	}

}
