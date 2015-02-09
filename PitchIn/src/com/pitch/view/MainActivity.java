package com.pitch.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.os.Build;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.CursorAdapter;
import android.widget.Toast;
import android.support.v4.widget.DrawerLayout;

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