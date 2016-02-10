package com.example.helloworld;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.helloworld.R.id;
import com.maker.dao.Event;
import com.maker.dao.User;
import com.maker.util.HttpUtil;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class Event0 extends Activity {
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
	private User u=new User();
	private Event ev=new Event();
	private final static int dialog_id=1;
	//int[] menu_image_array = { R.drawable.ic_launcher,R.drawable.ic_launcher};
	ArrayList image=new ArrayList();
	//String[] name_array={"A","B"};
	ArrayList name_array=new ArrayList();
	ArrayList joined=new ArrayList();
	ArrayList<Integer> id_array=new ArrayList<Integer>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
    	StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event);
		Intent intent=this.getIntent();
		u=(User)intent.getSerializableExtra("user");
		ev=(Event)intent.getSerializableExtra("event");
		joined=(ArrayList)intent.getParcelableArrayListExtra("joined");
		String result=query(ev.getId());
		int p1=0;int count=0;
		for(int i=0;i<result.length();i++){
			if(result.charAt(i)==','){
				switch(count){
				case 0:{ev.setTime(result.substring(p1, i));break;}
				case 1:{ev.setPlace(result.substring(p1,i));break;}
				case 2:{ev.setNumberofpeople(result.substring(p1, i));break;}
				case 3:{ev.setKeyword(result.substring(p1, i));break;}
				case 4:{ev.setPs(result.substring(p1, i));break;}
				case 5:{ev.setUid(Integer.parseInt(result.substring(p1, i)));break;}
				}			
				p1=i+1;count++;
			}
		}
		text1=(TextView)findViewById(R.id.event_textview1);
		text1.setText(ev.getEventname());
		text2=(TextView)findViewById(R.id.event_textview2);
		text2.setText("时间："+ev.getTime());
		text3=(TextView)findViewById(R.id.event_textview3);
		text3.setText("地点："+ev.getPlace());
		text4=(TextView)findViewById(R.id.event_textview4);
		text4.setText("参与人数："+ev.getNumberofpeople());
		text5=(TextView)findViewById(R.id.event_textview5);
		text5.setText("关键字："+ev.getKeyword());
		text6=(TextView)findViewById(R.id.event_textview6);
		text6.setText("备注："+ev.getPs());
		bt1=(Button)findViewById(R.id.event_button1);
		if(joined.contains(ev.getId()) ){
			bt1.setText("已加入");
			bt1.setEnabled(false);
		}
		else if(ev.getUid()==u.getId()){
			bt1.setVisibility(View.INVISIBLE);
		}
		else{
			bt1.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(u.getJudou()<50)
						Toast.makeText(Event0.this, "聚币不足", Toast.LENGTH_LONG).show();
					else{
					String result=query1(ev.getId(),u.getId());
					if(result.equals("1"))
					{
						Toast.makeText(Event0.this, "加入成功", Toast.LENGTH_LONG).show();
						bt1.setText("已加入");
						bt1.setEnabled(false);
					}
					else
						Toast.makeText(Event0.this, "加入失败", Toast.LENGTH_LONG).show();
				}}
			});
		}
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
		bt4.setVisibility(View.GONE);
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
			intent.setClass(Event0.this, personalif_for_joiner.class);
			Event0.this.startActivity(intent);
		}
	};
	private String query(int id){
		String queryString="id="+id;
    	String url=HttpUtil.BASE_URL+"servlet/EventInDetailServlet?"+queryString;
    	return HttpUtil.queryStringForPost(url);
	}
	private String query1(int eid,int uid){
		String queryString="eid="+eid+"&uid="+uid;
    	String url=HttpUtil.BASE_URL+"servlet/JoinServlet?"+queryString;			//有待改善
    	return HttpUtil.queryStringForPost(url);
	}
	private String query2(int eid){
		String queryString="eid="+eid;
		String url=HttpUtil.BASE_URL+"servlet/QueryforpeoplejoinedServlet?"+queryString;
		return HttpUtil.queryStringForPost(url);
	}
}