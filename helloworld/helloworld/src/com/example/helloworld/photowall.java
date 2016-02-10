package com.example.helloworld;



import com.maker.dao.Images;
import com.maker.dao.PhotoWallAdapter;
import com.maker.dao.User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

/**
 * ��Ƭǽ�����ʹ��GridViewչʾ��Ƭǽ��
 * 
 *
 */
public class photowall extends Activity {

	/**
	 * ����չʾ��Ƭǽ��GridView
	 */
	private GridView mPhotoWall;
	private User u =new User();
	private int eid;
	/**
	 * GridView��������
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
		// �˳�����ʱ�������е���������
		adapter.cancelAllTasks();
		Images.imageid.clear();
	}

}
