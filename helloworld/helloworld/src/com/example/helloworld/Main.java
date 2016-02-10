package com.example.helloworld;

import java.util.ArrayList;
import java.util.HashMap;







import com.maker.dao.Event;
import com.maker.dao.User;
import com.maker.util.HttpUtil;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class Main extends ListActivity {
	private Button button;
	private Button button_publish_flag;
	private Button menubutton0;
	private Button menubutton1;
	private Button menubutton2;
	private Button menubutton3;
	private Button menubutton4;
	private Button menubutton5;
	private Button menubutton6;
	private Button menubutton7;
	private Button menubutton8;
	
	private SlidingLayout slidingLayout;
	private ListView contentListView; 
	private User u=new User();
	private ArrayList<Event> eve=new ArrayList<Event>();
	private ArrayList joined=new ArrayList();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
    	StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Intent intent=this.getIntent();
		u=(User)intent.getSerializableExtra("user");
		String result=query(u.getId());					//获取活动列表
		String result_1=query_1(u.getId());				//获取已参加了的活动
		int temp_count=0;
		for(int i=0;i<result_1.length();i++){
			if(result_1.charAt(i)==','){
				joined.add(Integer.parseInt(result_1.substring(temp_count, i)));
				temp_count=i+1;
			}
		}
		slidingLayout = (SlidingLayout)findViewById(R.id.slidingLayout_main); 
		contentListView = (ListView) findViewById(android.R.id.list); 
		slidingLayout.setScrollEvent(contentListView);
		button = (Button)findViewById(R.id.button_menu);
		button.setOnClickListener(new buttonListener());
		button_publish_flag= (Button)findViewById(R.id.button_publishflag);
		button_publish_flag.setOnClickListener(new button_publishflagListener());
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
			String ev=result.substring(p2+1,p3);
			int eid=Integer.parseInt(result.substring(p3+1, p4));
			Event temp=new Event();
			temp.setEventname(ev);
			temp.setId(eid);
			eve.add(temp);
			p1=p4+1;
			map[count].put("user_name", name);
			map[count].put("user_event", ev);
			count++;
		}
		//HashMap<String,String> map1=new HashMap<String,String>();
		/**HashMap<String,String> map2=new HashMap<String,String>();
		HashMap<String,String> map3=new HashMap<String,String>();
		HashMap<String,String> map4=new HashMap<String,String>();**/
		//map1.put("user_name", "王二");
		//map1.put("user_event", en);
		/**map2.put("user_name","张三");
		map2.put("user_event", "找人踢足球");
		map3.put("user_name", "李四");
		map3.put("user_event", "食堂门口义卖");**/
		for(int i=0;i<10;i++)
		{
			if(map[i].isEmpty()==true)
				break;
			else{
				list.add(map[i]);
			}
		}
		//list.add(map2);
		//list.add(map3);
		//list.add(map4);	
		SimpleAdapter listAdapter =new SimpleAdapter(this,list,R.layout.user,new String[]{"user_name","user_event"},new int[]{R.id.user_name,R.id.user_event});
		setListAdapter(listAdapter);
		
		menubutton0 = (Button)findViewById(R.id.menuButton0_main);
		menubutton1 = (Button)findViewById(R.id.menuButton1_main);
		menubutton2 = (Button)findViewById(R.id.menuButton2_main);
		menubutton3 = (Button)findViewById(R.id.menuButton3_main);
		menubutton4 = (Button)findViewById(R.id.menuButton4_main);
		menubutton5 = (Button)findViewById(R.id.menuButton5_main);
		menubutton6 = (Button)findViewById(R.id.menuButton6_main);
		menubutton7 = (Button)findViewById(R.id.menuButton7_main);
		menubutton8 = (Button)findViewById(R.id.menuButton8_main);
		
		menubutton0.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Bundle bundle=new Bundle();
				bundle.putSerializable("user",u);
				Intent intent=new Intent();
				intent.putExtras(bundle);
				intent.setClass(Main.this,personalif_user.class);
				Main.this.startActivity(intent);
			}
		});
		menubutton1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				slidingLayout.scrollToRightLayout();
			}
		});
		menubutton2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Toast.makeText(Main.this, "好友列表", Toast.LENGTH_LONG).show();
				Bundle bundle=new Bundle();
				bundle.putSerializable("user",u);
				Intent intent=new Intent();
				intent.putExtras(bundle);
				intent.setClass(Main.this, friendlist.class);
				Main.this.startActivity(intent);
			}
		});
		
		menubutton3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(Main.this, "消息", Toast.LENGTH_LONG).show();
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
				intent.setClass(Main.this, partypublished.class);
				Main.this.startActivity(intent);
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
				intent.setClass(Main.this, partyparticipated.class);
				Main.this.startActivity(intent);
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
				intent.setClass(Main.this, partytoparticipate.class);
				Main.this.startActivity(intent);
			}
		});
		
		menubutton7.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(Main.this, "选项", Toast.LENGTH_LONG).show();
			}
		});
		
		menubutton8.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(Main.this,MainActivity.class);
				Main.this.startActivity(intent);
				finish();
			}
		});
	}
	
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		//id从上到下依次为0，1，2……
		Event temp=new Event();
		int id_0=new Long(id).intValue();
		temp=(Event)eve.get(id_0);
		Bundle bundle=new Bundle();
		bundle.putSerializable("user",u);
		bundle.putSerializable("event",temp);
		bundle.putParcelableArrayList("joined", joined);
		Intent intent=new Intent();
		intent.setClass(Main.this, Event0.class);
		intent.putExtras(bundle);
		Main.this.startActivity(intent);
	}
	class buttonListener implements OnClickListener{

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
		
	}
	class button_publishflagListener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Bundle bundle=new Bundle();
			bundle.putSerializable("user", u);
			Intent intent=new Intent();
			intent.setClass(Main.this, publish.class);
			intent.putExtras(bundle);
			Main.this.startActivity(intent);
		}
		
	}
    private String query(int id){
    	String queryString="id="+id;
    	String url=HttpUtil.BASE_URL+"servlet/ListOfJuServlet?"+queryString;
    	return HttpUtil.queryStringForPost(url);
    }
	private String query_1(int id){
		String queryString="uid="+id+"&method=onlyeid";
    	String url=HttpUtil.BASE_URL+"servlet/JoinedServlet?"+queryString;
    	return HttpUtil.queryStringForPost(url);
	}
}
