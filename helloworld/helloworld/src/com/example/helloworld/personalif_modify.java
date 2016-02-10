package com.example.helloworld;

import java.util.ArrayList;

import com.example.helloworld.R.id;
import com.maker.dao.User;
import com.maker.util.HttpUtil;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;

public class personalif_modify extends Activity{
	private Button button_confirm;
	private TextView tv;
	private EditText et1;
	private EditText et2;
	private Spinner sp_grade;
	private ArrayList<String> list = new ArrayList<String>();
	private ArrayAdapter<String> adapter;
	private EditText et4;
	private EditText et5;
	private Button back;
	private User u=new User();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
    	StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());
		super.onCreate(savedInstanceState);
		setContentView(R.layout.personalif_modify);
		Intent intent=this.getIntent();
		u=(User)intent.getSerializableExtra("user");
		button_confirm = (Button) findViewById(id.button_confirmmodify);
		button_confirm.setOnClickListener(new confirmOnclicklistener());
		tv = (TextView)findViewById(id.textView_username);
		et1 = (EditText)findViewById(id.editText_school);
		et2 = (EditText)findViewById(id.editText_college);
		sp_grade = (Spinner)findViewById(id.sp_grade);
		et4 = (EditText)findViewById(id.editText_hobby);
		et5 = (EditText)findViewById(id.editText_qianming);
		String[] ls=getResources().getStringArray(R.array.sp_grade);
		for(int i=0;i<ls.length;i++)
			list.add(ls[i]);
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_grade.setAdapter(adapter);
		sp_grade.setOnItemSelectedListener(new SpinnerSelectedListener());
		sp_grade.setVisibility(View.VISIBLE);
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		back = (Button)findViewById(id.button_back_personalif_modify);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				builder.setMessage("确定放弃修改返回么？").setPositiveButton("是", new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog,int which){
					finish();}}).setNegativeButton("否", new DialogInterface.OnClickListener(){
						public void onClick(DialogInterface dialog,int which){
							//null
						}});
				AlertDialog ad = builder.create();
				ad.show();
			}
		});
		initial();
	}
	
	class confirmOnclicklistener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			u.setSchool(et1.getText().toString());
			u.setCollege(et2.getText().toString());
			u.setGrade(sp_grade.getSelectedItem().toString());
			u.setHobby(et4.getText().toString());
			u.setQianming(et5.getText().toString());
			String result=query(u.getId(),u.getSchool(),u.getCollege(),u.getGrade(),u.getHobby(),u.getQianming());
			if(result.equals("1"))
			{
				Bundle bundle=new Bundle();
				bundle.putSerializable("user",u);
				Intent intent=new Intent();
				intent.putExtras(bundle);
				intent.setClass(personalif_modify.this, personalif_user.class);
				personalif_modify.this.startActivity(intent);
				finish();
			}
		}
		
	}
	class SpinnerSelectedListener implements OnItemSelectedListener{

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
	}
	private String query(int id,String school,String college,String grade,String hobby,String qianming){
		String queryString="id="+id+"&school="+school+"&college="+college+"&grade="+grade+"&hobby="+hobby+"&qianming="+qianming;
    	String url=HttpUtil.BASE_URL+"servlet/PersonalifModifyServlet?"+queryString;
    	return HttpUtil.queryStringForPost(url);
	}
	private void initial(){
		tv.setText(u.getUsername());
		et1.setText(u.getSchool());
		et2.setText(u.getCollege());
		et4.setText(u.getHobby());
		et5.setText(u.getQianming());
		int position=0;
		if(u.getGrade().equals("大一"))
			position=0;
		else if(u.getGrade().equals("大二"))
			position=1;
		else if(u.getGrade().equals("大三"))
			position=2;
		else if(u.getGrade().equals("大四"))
			position=3;
		else if(u.getGrade().equals("研一"))
			position=4;
		else if(u.getGrade().equals("研二"))
			position=5;
		else
			position=6;
		sp_grade.setSelection(position);
	}
}