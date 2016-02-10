package com.example.helloworld;

import java.util.ArrayList;
import java.util.HashMap;







import com.maker.dao.Event;
import com.maker.dao.User;
import com.maker.util.HttpUtil;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class partypublished extends ListActivity{
	//ListView list;
	public static partypublished temp_activity;
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
	private ArrayList<Event> eve=new ArrayList<Event>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.partypublished);
		Intent intent=this.getIntent();
		u=(User)intent.getSerializableExtra("user");
		temp_activity=this;
		slidingLayout = (SlidingLayout)findViewById(R.id.slidingLayout_published);
		contentListView = (ListView) findViewById(android.R.id.list); 
		slidingLayout.setScrollEvent(contentListView); 
		menu = (Button)findViewById(R.id.button_menu_partypulished);
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
		String result=query(u.getId());System.out.println(result);
		//list=(ListView)findViewById(id.list2);
		ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		HashMap[] map=new HashMap[10];
		for(int i=0;i<10;i++){
			map[i]=new HashMap<String,String>();
		}
		int p1=0;
		int p2=0;
		int p3=0;
		int p4=0;
		int count=0;
		while(p4!=result.length()-1){
			for(int i=p1;i<result.length();i++)
			{
				if(result.charAt(i)==',')
					p2=i;
				if(result.charAt(i)==';')
					p3=i;
				if(result.charAt(i)=='!'){
					p4=i;break;
				}
			}
			String name=result.substring(p1, p2);
			String time=result.substring(p2+1,p3);
			int eid=Integer.parseInt(result.substring(p3+1, p4));
			Event temp=new Event();
			temp.setEventname(name);
			temp.setTime(time);
			temp.setId(eid);
			eve.add(temp);
			p1=p4+1;
			map[count].put("user_name", name);
			map[count].put("user_event", time);
			count++;
		}
		for(int i=0;i<10;i++)
		{
			if(map[i].isEmpty()==true)
				break;
			else{
				list.add(map[i]);
			}
		}
		SimpleAdapter listAdapter =new SimpleAdapter(this,list,R.layout.user,new String[]{"user_name","user_event"},new int[]{R.id.user_name,R.id.user_event});
		setListAdapter(listAdapter);
		
		menubutton0 = (Button)findViewById(R.id.menuButton0_partypublished);
		menubutton1 = (Button)findViewById(R.id.menuButton1_partypublished);
		menubutton2 = (Button)findViewById(R.id.menuButton2_partypublished);
		menubutton3 = (Button)findViewById(R.id.menuButton3_partypublished);
		menubutton4 = (Button)findViewById(R.id.menuButton4_partypublished);
		menubutton5 = (Button)findViewById(R.id.menuButton5_partypublished);
		menubutton6 = (Button)findViewById(R.id.menuButton6_partypublished);
		menubutton7 = (Button)findViewById(R.id.menuButton7_partypublished);
		menubutton8 = (Button)findViewById(R.id.menuButton8_partypublished);
		
		menubutton0.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Bundle bundle=new Bundle();
				bundle.putSerializable("user",u);
				Intent intent=new Intent();
				intent.putExtras(bundle);
				intent.setClass(partypublished.this,personalif_user.class);
				partypublished.this.startActivity(intent);
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
				intent.setClass(partypublished.this,Main.class);
				partypublished.this.startActivity(intent);
			}
		});
		
		menubutton2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Toast.makeText(partyparticipated.this, "好友列表", Toast.LENGTH_LONG).show();
				Bundle bundle=new Bundle();
				bundle.putSerializable("user",u);
				Intent intent=new Intent();
				intent.putExtras(bundle);
				intent.setClass(partypublished.this, friendlist.class);
				partypublished.this.startActivity(intent);
			}
		});
		
		menubutton3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(partypublished.this, "消息", Toast.LENGTH_LONG).show();
			}
		});
		
		menubutton4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				slidingLayout.scrollToRightLayout();  
			}
		});
		
		menubutton5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Toast.makeText(Main.this, "参加过的聚", Toast.LENGTH_LONG).show();
				Bundle bundle=new Bundle();
				bundle.putSerializable("user",u);
				Intent intent=new Intent();
				intent.putExtras(bundle);
				intent.setClass(partypublished.this, partyparticipated.class);
				partypublished.this.startActivity(intent);
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
				intent.setClass(partypublished.this, partytoparticipate.class);
				partypublished.this.startActivity(intent);
			}
		});
		
		menubutton7.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(partypublished.this, "选项", Toast.LENGTH_LONG).show();
			}
		});
		
		menubutton8.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(partypublished.this,MainActivity.class);
				partypublished.this.startActivity(intent);
				finish();
			}
		});
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Event temp=new Event();
		int id_0=new Long(id).intValue();
		temp=(Event)eve.get(id_0);
		Bundle bundle=new Bundle();
		bundle.putSerializable("user",u);
		bundle.putSerializable("event",temp);
		Intent intent=new Intent();
		intent.putExtras(bundle);
		intent.setClass(partypublished.this, Event_for_publisher.class);
		partypublished.this.startActivity(intent);
	}
	private String query(int id){
		String queryString="id="+id;
    	String url=HttpUtil.BASE_URL+"servlet/ListOfPublishedServlet?"+queryString;
    	return HttpUtil.queryStringForPost(url);
	}
}
	
