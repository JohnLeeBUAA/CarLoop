package com.example.helloworld;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.SimpleAdapter;

public class score extends ListActivity{
	private Button bt_submit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_score);
		ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		HashMap<String,String> map1=new HashMap<String,String>();
		HashMap<String,String> map2=new HashMap<String,String>();
		map1.put("name_score", "A");
		map2.put("name_score","B");
		list.add(map1);
		list.add(map2);
		SimpleAdapter01 listAdapter =new SimpleAdapter01(this,list,R.layout.score,new String[]{"name_score"},new int[]{R.id.name_score});
		setListAdapter(listAdapter);
		bt_submit = (Button)findViewById(R.id.submit);
		bt_submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(score.this, partyparticipated.class);
				score.this.startActivity(intent);
			}
		});
	}
	
}
class SimpleAdapter01 extends SimpleAdapter{
	 public SimpleAdapter01(Context context, ArrayList<HashMap<String, String>> list, int resource, String[] from, int[] to){
		 super(context, list, resource, from, to);
	 }
	 
	 @Override
	 public View getView(int position, View convertView, ViewGroup parent)
	 {
	  convertView = super.getView(position, convertView, parent); 
	  RatingBar ratingbar=(RatingBar)convertView.findViewById(R.id.ratingBar_score);
	  ratingbar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
		public void onRatingChanged(RatingBar ratingbar, float rating, boolean ischanged) {
			// TODO Auto-generated method stub
			
		}
	});

	  return convertView;
	 }
	}