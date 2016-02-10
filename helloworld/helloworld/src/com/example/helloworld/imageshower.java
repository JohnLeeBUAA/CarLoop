package com.example.helloworld;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;

import com.maker.dao.Images;
import com.maker.dao.User;
import com.maker.util.DownloadUtil;
import com.maker.util.HttpUtil;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class imageshower extends Activity{
	private User u=new User();
	private String pid;
	private ImageView image=null;
	private Button bt_good=null;
	private boolean flag=false;			//false:yet    true:already
	private Set<BitmapWorkerTask2> taskCollection;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.imageshower);
		taskCollection = new HashSet<BitmapWorkerTask2>();
		Intent intent=this.getIntent();
		u=(User)intent.getSerializableExtra("user");
		pid=intent.getStringExtra("pid");
		String result=query(pid,u.getId());
		if(result.equals("0"))
			flag=true;
		image=(ImageView)findViewById(R.id.bigimage);
		bt_good=(Button)findViewById(R.id.good_imageshower);
		bt_good.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(flag)
					Toast.makeText(imageshower.this, "你已经点过赞了", Toast.LENGTH_LONG).show();
				else{
					String result2=query2(pid,u.getId());
					if(result2.equals("0"))
						Toast.makeText(imageshower.this, "点赞成功", Toast.LENGTH_LONG).show();
					else
						Toast.makeText(imageshower.this, "网络有问题，请重试", Toast.LENGTH_LONG).show();
				}
			}
		});
		loadBitmaps();
		
	}
	private String query(String pid2, int id) {
		// TODO Auto-generated method stub
		String queryString="pid="+pid2+"&uid="+id+"&method=TrueOrFalse";
		String url=HttpUtil.BASE_URL+"servlet/Photo_good?"+queryString;
		return HttpUtil.queryStringForPost(url);
	}
	private String query2(String pid2, int id) {
		// TODO Auto-generated method stub
		String queryString="pid="+pid2+"&uid="+id+"&method=good";
		String url=HttpUtil.BASE_URL+"servlet/Photo_good?"+queryString;
		return HttpUtil.queryStringForPost(url);
	}
	private void loadBitmaps() {
				String imageUrl = pid;
				BitmapWorkerTask2 task = new BitmapWorkerTask2();
				taskCollection.add(task);
				task.execute(imageUrl);
	}
	public void cancelAllTasks() {
		if (taskCollection != null) {
			for (BitmapWorkerTask2 task : taskCollection) {
				task.cancel(false);
			}
		}
	}
	class BitmapWorkerTask2 extends AsyncTask<String, Void, Bitmap> {

		/**
		 * 图片的URL地址
		 */
		private String imageUrl;

		@Override
		protected Bitmap doInBackground(String... params) {
			imageUrl = params[0];
			// 在后台开始下载图片
			Bitmap bitmap = downloadBitmap(params[0]);
			return bitmap;
		}

		@Override
		protected void onPostExecute(Bitmap bitmap) {
			super.onPostExecute(bitmap);
			// 根据Tag找到相应的ImageView控件，将下载好的图片显示出来。
			if (image != null && bitmap != null) {
				image.setImageBitmap(bitmap);
			}
			taskCollection.remove(this);
		}

		/**
		 * 建立HTTP请求，并获取Bitmap对象。
		 * 
		 * @param imageUrl
		 *            图片的URL地址
		 * @return 解析后的Bitmap对象
		 */
		private Bitmap downloadBitmap(String imageUrl) {
			Bitmap bitmap = null;
			try {
				bitmap = DownloadUtil.getGossipImage("http://172.26.124.53:8080/MyServer/PhotoDeal2",imageUrl);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return bitmap;
		}

	}

}