package com.pitch.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends ListActivity {

    public void onCreate(Bundle icicle) {
	    super.onCreate(icicle);
	    String[] title = new String[] { "SuperBowl Party", "Ski Trip", "App Deveoplment",
	        "Vegas", "Taho",  "BirthDay", "Toilet Paper", "Vodka",
	        "Event", "Marrage", "Something", "Kick Back", "BFF",
	        "Divorce Work", "Group"};
	    String[] member = new String[] { "Eric, Jem...", "John, Jim...", "Jess, Rachel...",
		        "Shawn, Tony...", "name...",
		        "Eric, Jem...", "John, Jim...", "Jess, Rachel...",
		        "Shawn, Tony...", "name...",
		        "Eric, Jem...", "John, Jim...", "Jess, Rachel...",
		        "Shawn, Tony...", "name..."};
	    String[] photo = new String[] { "photo1", "photo2", "photo3",
		        "photo4", "photo5","photo1", "photo2", "photo3",
		        "photo4", "photo5","photo1", "photo2", "photo3",
		        "photo4", "photo5"};
	    String[] money = new String[] { "$40.00", "$40.00", "$40.00",
		        "$40.00", "$40.00", "$40.00", "$40.00", "$40.00",
		        "$40.00", "$40.00","$40.00", "$40.00", "$40.00",
		        "$4.00", "$40.00"};
	    String[] from = {"title", "member", "money" };
	    int[] to = {R.id.title, R.id.member, R.id.money};
	    ArrayList<Map<String, String>> list = buildData(title, member, photo, money);
	    
	    // use your custom layout
	    SimpleAdapter adapter = new SimpleAdapter(this, list,
	        R.layout.main_raw_layout, from, to);
	    setListAdapter(adapter);
	  }

	  private ArrayList<Map<String, String>> buildData(String[] title, String[] member, String[] photo, String[] money) {
		    ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();
		    for(int i = 0; i < title.length; i++) {
		    	list.add(putData(title[i], member[i], photo[i], money[i]));
		    }
		    return list;
		  }

		  private HashMap<String, String> putData(String name, String member, String photo, String money) {
		    HashMap<String, String> item = new HashMap<String, String>();
		    item.put("title", name);
		    item.put("member", member);
//		    item.put("photo", photo);
		    item.put("money", money);
		    return item;
		  }
		  
		  
	  @Override
	  protected void onListItemClick(ListView l, View v, int position, long id) {
//	    String item = (String) getListAdapter().getItem(position);
//	    Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
		Intent intent = new Intent((Activity)this, ContentActivity.class);
		startActivity(intent);
	  }
	} 