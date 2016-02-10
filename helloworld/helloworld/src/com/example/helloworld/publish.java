package com.example.helloworld;

import java.util.ArrayList;
import java.util.Calendar;

import com.example.helloworld.R.id;
import com.maker.dao.User;
import com.maker.util.HttpUtil;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

public class publish extends Activity{
	private Spinner sp;
	private ArrayList<String> list = new ArrayList<String>();
	private ArrayAdapter<String> adapter;
	private EditText et_time;
	private EditText et_date;
	private Calendar c;
	private int m_year,m_month,m_day;
	private int m_hour,m_minute;
	private Button back;
	private Button publish;
	private EditText name_0;
	private EditText place_0;
	private EditText ps_0;
	private User u=new User();
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.publish);
		Intent intent=this.getIntent();
		u=(User)intent.getSerializableExtra("user");
		back=(Button)findViewById(id.button_back);
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		back.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// TODO Auto-generated method stub
				builder.setMessage("确定放弃发布返回么？").setPositiveButton("是", new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog,int which){
						onBackPressed();}}).setNegativeButton("否", new DialogInterface.OnClickListener(){
						public void onClick(DialogInterface dialog,int which){
							//null
						}});
				AlertDialog ad = builder.create();
				ad.show();
			}
		});
		sp=(Spinner)findViewById(id.sp);
		String[] ls=getResources().getStringArray(R.array.Size);
		for(int i=0;i<ls.length;i++)
			list.add(ls[i]);
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp.setAdapter(adapter);
		sp.setOnItemSelectedListener(new SpinnerSelectedListener());
		sp.setVisibility(View.VISIBLE);
		et_time = (EditText)findViewById(id.editText2);
		et_date = (EditText)findViewById(id.editText3);
		c = Calendar.getInstance();
		m_year = c.get(Calendar.YEAR);
		m_month = c.get(Calendar.MONTH);
		m_day = c.get(Calendar.DAY_OF_MONTH);
		m_hour = c.get(Calendar.HOUR_OF_DAY);
		m_minute = c.get(Calendar.MINUTE);
		if(m_hour<10 && m_minute < 10)
			et_time.setText("0"+m_hour+":0"+m_minute);
		else if(m_minute < 10 && m_minute > 10)
			et_time.setText("0"+m_hour+":"+m_minute);
		else if(m_minute > 10 && m_minute < 10)
			et_time.setText(m_hour+":0"+m_minute);
		else
			et_time.setText(m_hour+":"+m_minute);
		et_date.setText(m_year+"-"+(m_month+1)+"-"+m_day);
		et_time.setOnClickListener(new OnClickListener1());
		et_date.setOnClickListener(new OnClickListener2());
		publish = (Button)findViewById(id.button_publish);
		publish.setOnClickListener(new publishOnclicklistener());
		name_0=(EditText)findViewById(R.id.editText1);
		place_0=(EditText)findViewById(R.id.editText4);
		ps_0=(EditText)findViewById(R.id.editText6);
		
	}
	class OnClickListener1 implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Toast.makeText(publish.this, "OK", Toast.LENGTH_LONG).show();
			showDialog(0);
		}
		
	}
	class OnClickListener2 implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			showDialog(1);
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
	
	protected Dialog onCreateDialog(int id){
		if(id==0)
			return new TimePickerDialog(this, OnTimeSetListener, m_hour, m_minute, true);
		else
			return new DatePickerDialog(this, OnDateSetListener, m_year, m_month, m_day);
	}
	TimePickerDialog.OnTimeSetListener OnTimeSetListener= new TimePickerDialog.OnTimeSetListener(){
		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			// TODO Auto-generated method stub
			m_hour = hourOfDay;
			m_minute = minute;
			if(m_hour<10 && m_minute < 10)
				et_time.setText("0"+m_hour+":0"+m_minute);
			else if(m_minute < 10 && m_minute > 10)
				et_time.setText("0"+m_hour+":"+m_minute);
			else if(m_minute > 10 && m_minute < 10)
				et_time.setText(m_hour+":0"+m_minute);
			else
				et_time.setText(m_hour+":"+m_minute);
		}
	};
	DatePickerDialog.OnDateSetListener OnDateSetListener = new DatePickerDialog.OnDateSetListener(){
		public void onDateSet(DatePicker view,int year , int monthOfYear,int dayOfMonth){
			m_year = year;
			m_month = monthOfYear;
			m_day = dayOfMonth;
			et_date.setText(m_year+"-"+(m_month+1)+"-"+m_day);
		}
	};
	class publishOnclicklistener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(u.getJudou()<100)
				Toast.makeText(publish.this, "聚币余额不足，无法发布", Toast.LENGTH_LONG).show();
			else{
			String name=name_0.getText().toString();
			String place=place_0.getText().toString();
			String time=et_date.getText().toString().concat("-").concat(et_time.getText().toString());
			String numofpeople=sp.getSelectedItem().toString();
			String keyword="temp";
			String ps=ps_0.getText().toString();
			String id=String.valueOf(u.getId());
			String result=query(name,place,time,numofpeople,keyword,ps,id);
			if(result.equals("1")){
				Bundle bundle=new Bundle();
				bundle.putSerializable("user", u);
				Toast.makeText(publish.this, "发布成功", Toast.LENGTH_LONG).show();
				Intent intent = new Intent();
				intent.setClass(publish.this, Main.class);
				intent.putExtras(bundle);
				publish.this.startActivity(intent);
				finish();
			}}
		}	
	}
    private String query(String name,String place,String time,String numofpeople,String keyword,String ps,String id){
    	String queryString="name="+name+"&place="+place+"&time="+time+"&numofpeople="+numofpeople+"&keyword="+keyword+"&ps="+ps+"&id="+id;
    	String url=HttpUtil.BASE_URL+"servlet/PublishServlet?"+queryString;
    	return HttpUtil.queryStringForPost(url);
    }
}