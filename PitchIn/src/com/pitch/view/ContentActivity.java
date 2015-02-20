package com.pitch.view;

import java.util.ArrayList;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ContentActivity extends Activity {
	private String mTitle, mMembers;
	private ArrayList<String> transactions;
	private TextView mBalance;
	private TextView mTransactions;
	private Button mPay, mRequest, mDistribute;
	private EditText mAmount;
	private ListView listview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_content);
//		getActionBar().show();
//		getActionBar().setTitle("temp");
		
		mBalance = (TextView)findViewById(R.id.balance_value);
		mBalance.setText("40.00");
		mPay = (Button) findViewById(R.id.pay);
		mRequest = (Button) findViewById(R.id.request);
		mDistribute = (Button) findViewById(R.id.distribute);
		
		mAmount  = (EditText)findViewById(R.id.amount);
		mAmount.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
		mPay.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
			    DialogFragment newFragment = new PayFragment();
			    newFragment.show(ContentActivity.this.getFragmentManager(), null);
			}
		});
		
		mRequest.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
			    RequestFragment newFragment = new RequestFragment();
			    newFragment.setValue(Double.parseDouble(mAmount.getText().toString()));
			    newFragment.show(ContentActivity.this.getFragmentManager(), null);
				
			}
		});
		
		mDistribute.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
			    DistributeFragment newFragment = new DistributeFragment();
			    newFragment.setValue(Double.parseDouble(mBalance.getText().toString()));
			    newFragment.show(ContentActivity.this.getFragmentManager(), null);
				
			}
		});
		listview = (ListView)findViewById(R.id.transaction_list);
		ArrayAdapter adpater  = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
		listview.setAdapter(adpater);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.content, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	public void onUserSelectPayValue(String selectedValue) {
		String who = selectedValue;
		
		if(mAmount.getText().equals("")) {
			Toast.makeText(getApplicationContext(), "You can't pay $0.00!", 
					   Toast.LENGTH_LONG).show();
		}
		double amount = Double.parseDouble(mAmount.getText().toString());
		double currentBalance = Double.parseDouble(mBalance.getText().toString());
		if(currentBalance-amount < 0) {
			Toast.makeText(getApplicationContext(), "Not eoungh valance. Pay less amount or Request for more money!", 
					   Toast.LENGTH_LONG).show();
		}
		else if(amount == 0) {
			Toast.makeText(getApplicationContext(), "You can't pay $0.00!", 
					   Toast.LENGTH_LONG).show();
		}
		else {
		String balanceInString = String.valueOf(String.format("%.2f", currentBalance-amount));
		mBalance.setText(balanceInString);
		((ArrayAdapter)listview.getAdapter()).insert("shawn" + " paid " + String.format("%.2f", amount) + " to " + who, 0);
		((ArrayAdapter)listview.getAdapter()).notifyDataSetChanged();
		}
		
	}
	
	public void onUserSelectDistributeValue(String input) {
		((ArrayAdapter)listview.getAdapter()).insert("The remaining total was " + mBalance.getText().toString() + ". Each member received " +input + "." , 0);

		mBalance.setText("0.00");
		((ArrayAdapter)listview.getAdapter()).notifyDataSetChanged();

	}
	
	public void onUserSelectRequestValue() {
		String who = "Shawn";
		Log.i("DEBUG: " + mAmount.getText().toString(), "TAG");
		if(mAmount.getText() == null || (mAmount.getText()).equals("")) {
			Toast.makeText(getApplicationContext(), "You can't pay $0.00!", 
					   Toast.LENGTH_LONG).show();
		}
		double amount = Double.parseDouble(mAmount.getText().toString());
		
	    if(amount == 0) {
			Toast.makeText(getApplicationContext(), "You can't pay $0.00!", 
					   Toast.LENGTH_LONG).show();
		}
		double currentBalance = Double.parseDouble(mBalance.getText().toString());
		((ArrayAdapter)listview.getAdapter()).insert("shawn" + " contributed " + String.format("%.2f", amount), 0);
		String balanceInString = String.valueOf(String.format("%.2f", (6 * amount)+ currentBalance));
		
		((ArrayAdapter)listview.getAdapter()).insert(who + " requested " + String.format("%.2f", amount) + " to John", 0);
		((ArrayAdapter)listview.getAdapter()).insert(who + " requested " + String.format("%.2f", amount) + " to James", 0);
		((ArrayAdapter)listview.getAdapter()).insert(who + " requested " + String.format("%.2f", amount) + " to Jimmy", 0);

		((ArrayAdapter)listview.getAdapter()).insert(who + " requested " + String.format("%.2f", amount) + " to Joe", 0);
		((ArrayAdapter)listview.getAdapter()).insert(who + " requested " + String.format("%.2f", amount) + " to June", 0);

		((ArrayAdapter)listview.getAdapter()).insert("John contributed " + String.format("%.2f", amount), 0);
		((ArrayAdapter)listview.getAdapter()).insert("James contributed " + String.format("%.2f", amount), 0);
		((ArrayAdapter)listview.getAdapter()).insert("Jimmy contributed " + String.format("%.2f", amount), 0);

		((ArrayAdapter)listview.getAdapter()).insert("Joe contributed " + String.format("%.2f", amount), 0);
		((ArrayAdapter)listview.getAdapter()).insert("June contributed " + String.format("%.2f", amount), 0);
		
		mBalance.setText(balanceInString);

		((ArrayAdapter)listview.getAdapter()).notifyDataSetChanged();
	}
}
