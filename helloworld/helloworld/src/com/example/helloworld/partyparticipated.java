package com.example.helloworld;

import java.util.ArrayList;
import java.util.HashMap;



















import java.util.Map;

import com.maker.dao.Event;
import com.maker.dao.User;
import com.maker.util.HttpUtil;
import com.maker.util.UploadUtil;
import com.maker.util.UploadUtil.OnUploadProcessListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.InputFilter.LengthFilter;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class partyparticipated extends ListActivity implements OnUploadProcessListener{
	//ListView list;
	public static partyparticipated temp_activity;
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
	private Uri photoUri;  
	private final static int dialog_id=1;
	public static final int SELECT_PIC_BY_TACK_PHOTO = 1;
	public static final int SELECT_PIC_BY_PICK_PHOTO = 2; 
	public static final int TO_SELECT_PHOTO = 3;
	protected static final int TO_UPLOAD_FILE = 1;  
	 protected static final int UPLOAD_FILE_DONE = 2;
	 private static final int UPLOAD_INIT_PROCESS = 4;  
	 private static final int UPLOAD_IN_PROCESS = 5; 
	 private String picPath = null; 
	 private static final String TAG = "partyparticipated";
	 private Intent lastIntent ;
	 public static final String KEY_PHOTO_PATH = "photo_path";
	 private Bitmap bm;
	 private ProgressDialog progressDialog; 
	 private static String requestURL = "http://172.25.147.190:8080/MyServer/PhotoDeal";
	 private User u=new User();
	 private ArrayList<Integer> array_eid=new ArrayList<Integer>();
	 private ArrayList<String> array_ename=new ArrayList<String>();
	 private int eid_get=0;
	 private ArrayList<HashMap> array_map=new ArrayList<HashMap>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.partyparticipated);
		Intent intent=this.getIntent();
		u=(User)intent.getSerializableExtra("user");
		temp_activity=this;
		slidingLayout = (SlidingLayout)findViewById(R.id.slidingLayout_participated);
		contentListView = (ListView) findViewById(android.R.id.list); 
		slidingLayout.setScrollEvent(contentListView); 
		 progressDialog = new ProgressDialog(this); 
		menu = (Button)findViewById(R.id.button_menu_partyParticipated);
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
		String result=query(u.getId());
		if(!result.equals("-1")){
			int p1=0;
			int p2=0;
			int p3=0;
			int count=0;
			while(p3!=result.length()-1){
				for(int i=p1;i<result.length();i++)
				{
					if(result.charAt(i)==',')
						p2=i;
					if(result.charAt(i)==';')
					{	p3=i;break;}	
				}				
				int eid=Integer.parseInt(result.substring(p1, p2));
				String name=result.substring(p2+1,p3);
				array_eid.add(eid);
				array_ename.add(name);
				HashMap<String,String> map1=new HashMap<String,String>();
				map1.put("event_participated", name);
				array_map.add(map1);
				p1=p3+1;
				count++;
			}
		}
		//list=(ListView)findViewById(id.list2);
		ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		/*HashMap<String,String> map1=new HashMap<String,String>();
		HashMap<String,String> map2=new HashMap<String,String>();
		map1.put("event_participated", "篮球");
		map2.put("event_participated","公益活动");*/
		//map1.put("event_score", "评分");
		for(int i=0;i<array_map.size();i++)
			list.add(array_map.get(i));
		MySimpleAdapter listAdapter =new MySimpleAdapter(this,list,R.layout.participated,new String[]{"event_participated"},new int[]{R.id.event_participated});
		setListAdapter(listAdapter);
		
		menubutton0 = (Button)findViewById(R.id.menuButton0_partyParticipated);
		menubutton1 = (Button)findViewById(R.id.menuButton1_partyParticipated);
		menubutton2 = (Button)findViewById(R.id.menuButton2_partyParticipated);
		menubutton3 = (Button)findViewById(R.id.menuButton3_partyParticipated);
		menubutton4 = (Button)findViewById(R.id.menuButton4_partyParticipated);
		menubutton5 = (Button)findViewById(R.id.menuButton5_partyParticipated);
		menubutton6 = (Button)findViewById(R.id.menuButton6_partyParticipated);
		menubutton7 = (Button)findViewById(R.id.menuButton7_partyParticipated);
		menubutton8 = (Button)findViewById(R.id.menuButton8_partyParticipated);
		
		menubutton0.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Bundle bundle=new Bundle();
				bundle.putSerializable("user",u);
				Intent intent=new Intent();
				intent.putExtras(bundle);
				intent.setClass(partyparticipated.this,personalif_user.class);
				partyparticipated.this.startActivity(intent);
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
				intent.setClass(partyparticipated.this,Main.class);
				partyparticipated.this.startActivity(intent);
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
				intent.setClass(partyparticipated.this, friendlist.class);
				partyparticipated.this.startActivity(intent);
			}
		});
		
		menubutton3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(partyparticipated.this, "消息", Toast.LENGTH_LONG).show();
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
				intent.setClass(partyparticipated.this,partypublished.class);
				partyparticipated.this.startActivity(intent);
			}
		});
		
		menubutton5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Toast.makeText(Main.this, "参加过的聚", Toast.LENGTH_LONG).show();
				slidingLayout.scrollToRightLayout();  
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
				intent.setClass(partyparticipated.this, partytoparticipate.class);
				partyparticipated.this.startActivity(intent);
			}
		});
		
		menubutton7.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(partyparticipated.this, "选项", Toast.LENGTH_LONG).show();
			}
		});
		
		menubutton8.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(partyparticipated.this,MainActivity.class);
				partyparticipated.this.startActivity(intent);
				finish();
			}
		});
	}

	
private String query(int id) {
		// TODO Auto-generated method stub
	String queryString="uid="+id+"&method=eidandename";
	String url=HttpUtil.BASE_URL+"servlet/JoinedServlet?"+queryString;
	return HttpUtil.queryStringForPost(url);
	}


class MySimpleAdapter extends SimpleAdapter{
	
	 public MySimpleAdapter(Context context, ArrayList<HashMap<String, String>> list, int resource, String[] from, int[] to){
		 super(context, list, resource, from, to);
	 }
	 
	 @Override
	 public View getView(final int position, View convertView, ViewGroup parent)
	 {
	  convertView = super.getView(position, convertView, parent);
	  Button bt0=(Button)convertView.findViewById(R.id.button_share_participated);
	  Button bt1=(Button)convertView.findViewById(R.id.button_photowall);
	  bt0.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			eid_get=array_eid.get(position);
			showDialog(dialog_id);
		}

	});
	  bt1.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Bundle bundle=new Bundle();
			bundle.putSerializable("user",u);
			bundle.putInt("eid", array_eid.get(position));
			Intent intent=new Intent();
			intent.putExtras(bundle);
			intent.setClass(partyparticipated.temp_activity, photowall.class);
			partyparticipated.temp_activity.startActivity(intent);
		}
	});
	  return convertView;
	 }
}
	protected Dialog onCreateDialog(int id) {
					// TODO Auto-generated method stub
					Dialog dialog=null;
					switch(id){
					case dialog_id:
						String[] items={"拍照","从相册选择"};
						android.app.AlertDialog.Builder builder=new AlertDialog.Builder(partyparticipated.temp_activity);
						builder.setTitle("分享");
						builder.setItems(items, new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								if(which==0){
									takePhoto();
								}
								else
									pickPhoto();
							}
						});
						dialog=builder.create();
						break;
					}
					return dialog;
			}
	 private void takePhoto() {  
	        //执行拍照前，应该先判断SD卡是否存在  
	        String SDState = Environment.getExternalStorageState();  
	        if(SDState.equals(Environment.MEDIA_MOUNTED))  
	        {  
	              
	            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//"android.media.action.IMAGE_CAPTURE"  
	            /*** 
	             * 需要说明一下，以下操作使用照相机拍照，拍照后的图片会存放在相册中的 
	             * 这里使用的这种方式有一个好处就是获取的图片是拍照后的原图 
	             * 如果不实用ContentValues存放照片路径的话，拍照后获取的图片为缩略图不清晰 
	             */  
	            ContentValues values = new ContentValues();    
	            photoUri = this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);    
	            intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, photoUri);  
	            /**-----------------*/  
	            startActivityForResult(intent, SELECT_PIC_BY_TACK_PHOTO);  
	        }else{  
	            Toast.makeText(this,"内存卡不存在", Toast.LENGTH_LONG).show();  
	        }  
	    }
	 private void pickPhoto() {  
	        Intent intent = new Intent();  
	        intent.setType("image/*");  
	        intent.setAction(Intent.ACTION_GET_CONTENT);  
	        startActivityForResult(intent, SELECT_PIC_BY_PICK_PHOTO);  
	    }  
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
		 if(resultCode == Activity.RESULT_OK)  
	        {  
	            doPhoto(requestCode,data);  
	        }  
	    }  
	 private void doPhoto(int requestCode,Intent data)  
	    {  
	        if(requestCode == SELECT_PIC_BY_PICK_PHOTO )  //从相册取图片，有些手机有异常情况，请注意  
	        {  
	            if(data == null)  
	            {  
	                Toast.makeText(this, "选择图片文件出错", Toast.LENGTH_LONG).show();  
	                return;  
	            }  
	            photoUri = data.getData();  
	            if(photoUri == null )  
	            {  
	                Toast.makeText(this, "选择图片文件出错", Toast.LENGTH_LONG).show();  
	                return;  
	            }  
	        }  
	        String[] pojo = {MediaStore.Images.Media.DATA};  
	        Cursor cursor = managedQuery(photoUri, pojo, null, null,null);     
	        if(cursor != null )  
	        {  
	            int columnIndex = cursor.getColumnIndexOrThrow(pojo[0]);  
	            cursor.moveToFirst();  
	            picPath = cursor.getString(columnIndex);  
	            if(picPath!=null)  
	            {  
	                handler.sendEmptyMessage(TO_UPLOAD_FILE);  
	            }else{  
	                Toast.makeText(this, "上传的文件路径出错", Toast.LENGTH_LONG).show();  
	            }  
	            cursor.close();  
	        }  
	        Log.i(TAG, "imagePath = "+picPath); 
	    }
	 private Handler handler = new Handler(){  
	        @Override  
	        public void handleMessage(Message msg) {  
	            switch (msg.what) {  
	            case TO_UPLOAD_FILE:  
	                toUploadFile();  
	                break;  
	              
	            case UPLOAD_INIT_PROCESS:  
	                //progressBar.setMax(msg.arg1);  
	                break;  
	            case UPLOAD_IN_PROCESS:  
	                //progressBar.setProgress(msg.arg1);  
	                break;  
	            case UPLOAD_FILE_DONE:  
	                String result = "响应码："+msg.arg1+"\n响应信息："+msg.obj+"\n耗时："+UploadUtil.getRequestTime()+"秒";  
	                 
	                break; 
	            
	            default:  
	                break;  
	            }  
	            super.handleMessage(msg);  
	        }  
	          
	    };  
	    private void toUploadFile()  
	    {  
	        
	        progressDialog.setMessage("正在上传文件...");  
	        progressDialog.show();  
	        String fileKey = "pic";  
	        UploadUtil uploadUtil = UploadUtil.getInstance();;  
	        uploadUtil.setOnUploadProcessListener(this);  //设置监听器监听上传状态  
	        
	        Map<String, String> params = new HashMap<String, String>();  
	        params.put("orderId", "11111");  
	        uploadUtil.uploadFile( picPath,fileKey, requestURL,params,eid_get,u.getId());  
	    }  
	    @Override  
	    public void onUploadProcess(int uploadSize) {  
	        Message msg = Message.obtain();  
	        msg.what = UPLOAD_IN_PROCESS;  
	        msg.arg1 = uploadSize;  
	        handler.sendMessage(msg );  
	    }

		@Override
		public void initUpload(int fileSize) {
			// TODO Auto-generated method stub
			Message msg = Message.obtain();  
	        msg.what = UPLOAD_INIT_PROCESS;  
	        msg.arg1 = fileSize;  
	        handler.sendMessage(msg ); 
		}
		@Override
		public void onUploadDone(int responseCode, String message) {
			// TODO Auto-generated method stub
			 progressDialog.dismiss();  
		        Message msg = Message.obtain();  
		        msg.what = UPLOAD_FILE_DONE;  
		        msg.arg1 = responseCode;  
		        msg.obj = message;  
		        handler.sendMessage(msg); 
		        handler.post(runnable);
		}  
		Runnable   runnable=new  Runnable(){  
	        @Override  
	        public void run() {  
	            //更新界面  
	            Toast.makeText(partyparticipated.this, "上传成功", Toast.LENGTH_LONG).show(); 
	        }           
	    };  
	}