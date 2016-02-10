package com.example.helloworld;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.helloworld.R.id;
import com.google.zxing.client.android.CaptureActivity;
import com.maker.dao.Event;
import com.maker.dao.User;
import com.maker.util.HttpUtil;

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
import android.widget.Toast;

public class Event_for_publisher extends Activity {
	private TextView text1 = null;
	private TextView text2 = null;
	private TextView text3 = null;
	private TextView text4 = null;
	private TextView text5 = null;
	private TextView text6 = null;
	private Button bt1=null;
	private Button bt2=null;
	private Button bt3=null;
	private Button bt4=null;
	private final static int dialog_id=1;
	//int[] menu_image_array = { R.drawable.ic_launcher,R.drawable.ic_launcher};
	ArrayList image=new ArrayList();
	ArrayList name_array=new ArrayList();
	ArrayList<Integer> id_array=new ArrayList<Integer>();
	//String[] name_array={"A","B"};
	private User u=new User();
	private Event ev=new Event();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event);
		text1=(TextView)findViewById(R.id.event_textview1);
		text2=(TextView)findViewById(R.id.event_textview2);
		text3=(TextView)findViewById(R.id.event_textview3);
		text4=(TextView)findViewById(R.id.event_textview4);
		text5=(TextView)findViewById(R.id.event_textview5);
		text6=(TextView)findViewById(R.id.event_textview6);
		Intent intent=this.getIntent();
		u=(User)intent.getSerializableExtra("user");
		ev=(Event)intent.getSerializableExtra("event");
		String result=query(ev.getId());
		int p1=0;int count=0;
		for(int i=0;i<result.length();i++){
			if(result.charAt(i)==','){
				switch(count){
				case 1:{ev.setPlace(result.substring(p1,i));break;}
				case 2:{ev.setNumberofpeople(result.substring(p1, i));break;}
				case 3:{ev.setKeyword(result.substring(p1, i));break;}
				case 4:{ev.setPs(result.substring(p1, i));break;}
				default:break;
				}			
				p1=i+1;count++;
			}
		}
		text1.setText(ev.getEventname());
		text2.setText("时间："+ev.getTime());
		text3.setText("地点："+ev.getPlace());
		text4.setText("参与人数："+ev.getNumberofpeople());
		text5.setText("关键字："+ev.getKeyword());
		text6.setText("备注："+ev.getPs());
		bt1=(Button)findViewById(R.id.event_button1);
		bt1.setVisibility(View.GONE);
		bt2=(Button)findViewById(R.id.event_button2);
		bt2.setText("已经加入的同学");
		bt2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String result=query2(ev.getId());
				int p1=0;
				for(int i=0;i<result.length();i++){
					if(result.charAt(i)==','){
						image.add(R.drawable.ic_launcher);
						name_array.add(result.substring(p1, i));
						p1=i+1;
					}
					else if(result.charAt(i)==';'){
						id_array.add(Integer.parseInt(result.substring(p1,i)));
						p1=i+1;
					}
				}
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
		bt4=(Button)findViewById(R.id.event_button3);
		bt4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent openCameraIntent = new Intent(Event_for_publisher.this,CaptureActivity.class);
				startActivityForResult(openCameraIntent, 0);
			}
		});
	}
	

	private SimpleAdapter getMenuAdapter(ArrayList name_array2,
            ArrayList image2) {
        ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < name_array2.size(); i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("itemImage", image2.get(i));
            map.put("itemText", name_array2.get(i));
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
			builder.setAdapter(getMenuAdapter(name_array, image), listener);
			dialog=builder.create();
			break;
		}
		return dialog;
	}
	DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			Bundle bundle=new Bundle();
			bundle.putSerializable("user",u);
			bundle.putInt("id", id_array.get(which));
			Intent intent=new Intent();
			intent.putExtras(bundle);
			intent.setClass(Event_for_publisher.this, personalif_for_joiner.class);
			Event_for_publisher.this.startActivity(intent);
		}
	};
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		//处理扫描结果（在界面上显示）
		if (resultCode == RESULT_OK) {
			Bundle bundle = data.getExtras();
			String scanResult = bundle.getString("result");
			try{
				int sR=Integer.parseInt(scanResult);
				String rs=query3(sR);
				if(rs.equals("1"))
					Toast.makeText(Event_for_publisher.this, "签到成功", Toast.LENGTH_LONG).show();
				else
					Toast.makeText(Event_for_publisher.this, "签到失败，该同学可能没有参加你的聚", Toast.LENGTH_LONG).show();
			}
			catch(NumberFormatException e){
				Toast.makeText(Event_for_publisher.this, "二维码错误", Toast.LENGTH_LONG).show();
			}
			
		}
	}
	private String query(int id){
		String queryString="id="+id;
    	String url=HttpUtil.BASE_URL+"servlet/EventInDetailServlet?"+queryString;
    	return HttpUtil.queryStringForPost(url);
	}
	private String query2(int eid){
		String queryString="eid="+eid;
		String url=HttpUtil.BASE_URL+"servlet/QueryforpeoplejoinedServlet?"+queryString;
		return HttpUtil.queryStringForPost(url);
	}
	private String query3(int rs){
		String queryString="scanResult="+rs+"&method=sign&eid="+ev.getId();
		String url=HttpUtil.BASE_URL+"servlet/PointsServlet?"+queryString;
		return HttpUtil.queryStringForPost(url);
	}
}