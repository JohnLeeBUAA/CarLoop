package com.example.helloworld;

import com.maker.dao.User;
import com.maker.util.HttpUtil;

import android.os.Bundle;
import android.os.StrictMode;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;;
public class MainActivity extends Activity {
	private Button Mybutton = null;
	private Button Mybutton1 = null;
	private EditText account=null;
	private EditText password=null;
	private TextView tv=null;
	User u=new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
    	StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_main);	
		//getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);
        tv=(TextView)findViewById(R.id.myTextView);
        tv.setVisibility(View.INVISIBLE);
        Mybutton=(Button)findViewById(R.id.button1);
        Mybutton.setOnClickListener(new myButtonListener());
        Mybutton1=(Button)findViewById(R.id.Button2);
        Mybutton1.setOnClickListener(new myButtonListener1());
        account=(EditText)findViewById(R.id.editText2);
        password=(EditText)findViewById(R.id.editText1);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    class myButtonListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//生成一个intent对象
			Intent intent=new Intent();
			intent.setClass(MainActivity.this, Register.class);
			MainActivity.this.startActivity(intent);
		}
    }
    private boolean validate(){
    	String acc=account.getText().toString();
    	if(acc.equals("")){
    		Toast.makeText(MainActivity.this, "用户名不能为空", Toast.LENGTH_LONG).show();
    		return false;
    	}
    	String pwd=password.getText().toString();
    	if(pwd.equals("")){
    		Toast.makeText(MainActivity.this, "密码不能为空", Toast.LENGTH_LONG).show();
    		return false;
    	}
    	return true;
    }
    private String query(String acc,String pwd){
    	String queryString="account="+acc+"&password="+pwd;
    	String url=HttpUtil.BASE_URL+"servlet/LoginServlet?"+queryString;
    	return HttpUtil.queryStringForPost(url);
    }
    private boolean login(){
    	String acc=account.getText().toString();
    	String pwd=password.getText().toString();
    	String result=query(acc,pwd);
    	System.out.println(result);
    	if(result!= null){
    		int k1=0;
    		int count=0;
    		for(int i=0;i<result.length();i++){
    			if(result.charAt(i)==','){
    				switch(count){
    				case 0:{u.setId(Integer.parseInt(result.substring(k1, i)));break;}
    				case 1:{u.setUsername(result.substring(k1,i));break;}
    				case 2:{u.setSex(result.substring(k1,i));break;}
    				case 3:{if(result.substring(k1, i).equals("null"))
    							u.setSchool("");
    						else
    							u.setSchool(result.substring(k1, i));
    						break;}
    				case 4:{if(result.substring(k1, i).equals("null"))
    							u.setCollege("");
    						else
    							u.setCollege(result.substring(k1,i));
    						break;}
    				case 5:{if(result.substring(k1, i).equals("null"))
    							u.setGrade("");
    						else
    							u.setGrade(result.substring(k1, i));
    						break;}
    				case 6:{if(result.substring(k1, i).equals("null"))
    							u.setHobby("");
    						else
    							u.setHobby(result.substring(k1, i));
    						break;}
    				case 7:{if(result.substring(k1, i).equals("null"))
    							u.setQianming("Ta很懒，什么都没有留下");
    						else
    							u.setQianming(result.substring(k1, i));
    						break;}
    				case 8:{u.setJudou(Integer.parseInt(result.substring(k1, i)));break;}
    				}
    				k1=i+1;
    				count++;
    			}
    		}
    		u.setAccount(account.getText().toString());
    		u.setPassword(password.getText().toString());
    		return true;
    	}
    	else
    		return false;
    }
    class myButtonListener1 implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(validate()){
			if(login()){
				Bundle bundle=new Bundle();
				bundle.putSerializable("user", u);
				Intent intent=new Intent();
				intent.setClass(MainActivity.this,Main.class);
				intent.putExtras(bundle);
				MainActivity.this.startActivity(intent);
				finish();		
			}
			else{
				Toast.makeText(MainActivity.this, "用户名或密码错误，请重试", Toast.LENGTH_LONG).show();
			}}
		}
    }    
}

