package com.example.helloworld;

import com.maker.dao.User;
import com.maker.util.HttpUtil;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class personalif_for_joiner extends Activity{
	private Button bt_useless;
	private Button bt_back;
	private Button bt_delete;
	private User u=new User();
	private TextView tv;
	private TextView tv0;
	private TextView tv1;
	private TextView tv2;
	private TextView tv3;
	private TextView tv4;
	private TextView tv5;
	private TextView tv6;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.personalif_friend);
		Intent intent=this.getIntent();
		u=(User)intent.getSerializableExtra("user");
		final int id_aim=intent.getIntExtra("id", -1);
		tv=(TextView)findViewById(R.id.textView_username_friend);
		tv0=(TextView)findViewById(R.id.textView1_sex_friend);
		tv1=(TextView)findViewById(R.id.textView1_school_friend);
		tv2=(TextView)findViewById(R.id.textView1_college_friend);
		tv3=(TextView)findViewById(R.id.textView1_grade_friend);
		tv4=(TextView)findViewById(R.id.textView1_hobby_friend);
		tv5=(TextView)findViewById(R.id.textView1_qianming_friend);
		tv6=(TextView)findViewById(R.id.textView1_judou_friend);
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		String result=query(id_aim);
		if(result!= null){
    		int k1=0;
    		int count=0;
    		for(int i=0;i<result.length();i++){
    			if(result.charAt(i)==','){
    				switch(count){
    				case 0:{tv.setText(result.substring(k1, i));break;}
    				case 1:{if(result.substring(k1,i).equals("m"))
    							tv0.setText("男");
    						else
    							tv1.setText("女");
    						break;}
    				case 2:{if(result.substring(k1, i).equals("null"))
    							tv1.setText("");
    						else
    							tv1.setText(result.substring(k1, i));
    						break;}
    				case 3:{if(result.substring(k1, i).equals("null"))
    							tv2.setText("");
    						else
    							tv2.setText(result.substring(k1,i));
    						break;}
    				case 4:{if(result.substring(k1, i).equals("null"))
    							tv3.setText("");
    						else
    							tv3.setText(result.substring(k1, i));
    						break;}
    				case 5:{if(result.substring(k1, i).equals("null"))
    							tv4.setText("");
    						else
    							tv4.setText(result.substring(k1, i));
    						break;}
    				case 6:{if(result.substring(k1, i).equals("null"))
    							tv5.setText("Ta很懒，什么都没有留下");
    						else
    							tv5.setText(result.substring(k1, i));
    						break;}
    				case 7:{tv6.setText(result.substring(k1, i));break;}
    				}
    				k1=i+1;
    				count++;
    			}
    		}
		}
		bt_useless = (Button)findViewById(R.id.button_for_publisher_personalif_friend);
		bt_useless.setVisibility(View.GONE);
		bt_back = (Button)findViewById(R.id.button_back_personalif_friend);
		bt_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
				
			}
		});
		String result1=query1(u.getId(),id_aim);System.out.println(result1);
		bt_delete=(Button)findViewById(R.id.button_delete_friend);
		if(result1.equals("0"))
		{
			
			bt_delete.setText("加为好友");
			bt_delete.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String rs=query2(u.getId(),id_aim);
					if(rs.equals("1"))
					{
						Toast.makeText(personalif_for_joiner.this, "添加好友成功", Toast.LENGTH_LONG).show();
						bt_delete.setText("已加好友");
						bt_delete.setEnabled(false);
					}
					
				}
			});
		}
		else
		{
			bt_delete.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					builder.setMessage("确定要删除该好友么？").setPositiveButton("是", new DialogInterface.OnClickListener(){
						public void onClick(DialogInterface dialog,int which){
						String rs=query3(u.getId(),id_aim);
						if(rs.equals("1"))
						{
							Toast.makeText(personalif_for_joiner.this, "已删除", Toast.LENGTH_LONG).show();
							bt_delete.setText("已解除好友关系");
							bt_delete.setEnabled(false);
						}}}).setNegativeButton("否", new DialogInterface.OnClickListener(){
							public void onClick(DialogInterface dialog,int which){
								//null
							}});
					AlertDialog ad = builder.create();
					ad.show();
				}
			});
		}
			
	}
	private String query(int id){
    	String queryString="id="+id;
    	String url=HttpUtil.BASE_URL+"servlet/UserInDetailServlet?"+queryString;
    	return HttpUtil.queryStringForPost(url);
    }
	private String query1(int id0,int id1){
    	String queryString="id0="+id0+"&id1="+id1+"&method=isFriend";
    	String url=HttpUtil.BASE_URL+"servlet/FriendServlet?"+queryString;
    	return HttpUtil.queryStringForPost(url);
    }
	private String query2(int id0,int id1){
    	String queryString="id0="+id0+"&id1="+id1+"&method=addFriend";
    	String url=HttpUtil.BASE_URL+"servlet/FriendServlet?"+queryString;
    	return HttpUtil.queryStringForPost(url);
    }
	private String query3(int id0,int id1){
		String queryString="id0="+id0+"&id1="+id1+"&method=removeFriend";
    	String url=HttpUtil.BASE_URL+"servlet/FriendServlet?"+queryString;
    	return HttpUtil.queryStringForPost(url);
	}
}