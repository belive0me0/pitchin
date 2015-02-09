package com.pitch.view.login;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import com.pitch.view.MainActivity;
import com.pitch.view.MyParcelable;
import com.pitch.view.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	private AutoCompleteTextView mEmailView;
	private EditText mPasswordView;
	private Button mLoginButton, mRegisterButton, mForgotCredentialButton;
	private String mUsername, mPassword;
	
	private final String TEMP_USERNAME = "username";
	private final String TEMP_PASSWORD = "1234";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		mEmailView = (AutoCompleteTextView) findViewById(R.id.email_view);
		
		mPasswordView = (EditText) findViewById(R.id.password);
		mLoginButton = (Button) findViewById(R.id.email_sign_in_button);
		mRegisterButton = (Button) findViewById(R.id.register_button);
		mForgotCredentialButton = (Button) findViewById(R.id.forgot_password_button);
		
		mEmailView = (AutoCompleteTextView) findViewById(R.id.email_view);
		mLoginButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				NetAsync(view);
			}
		});
		
		mRegisterButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				Intent intent = new Intent(view.getContext(), RegisterActivity.class);
				startActivity(intent);
			}			
		});
		
		mForgotCredentialButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				Intent intent = new Intent(view.getContext(), ForgotCredentialActivity.class);
				startActivity(intent);
			}			
		});
	}

	public void attemptLogin() {
		mUsername = mEmailView.getText().toString();
		mPassword = mPasswordView.getText().toString();
		
		if(mPassword.equals(TEMP_PASSWORD) && mUsername.equals(TEMP_USERNAME)) {
			Intent intent = new Intent(this, MainActivity.class);
			HashMap<String, Object> userInfo = new HashMap<String, Object>();
			MyParcelable userinfo = new MyParcelable();
			userInfo.put("username", mUsername);
			userInfo.put("password", mPassword);
			intent.putExtra("userinfo", userInfo);
			startActivity(intent);
		}
		else {
			Toast.makeText(getApplicationContext(), "Invalid username or password. Try again.", 
					   Toast.LENGTH_LONG).show();
		}

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
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
	
	private class NetCheck extends AsyncTask<Void, Void, Boolean> {
		private ProgressDialog mDialog;
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			mDialog = new ProgressDialog(LoginActivity.this);
			mDialog.setTitle("Checking Network");
			mDialog.setIndeterminate(false);
			mDialog.setCancelable(true);
			mDialog.show();
		}
		@Override
		protected Boolean doInBackground(Void... params) {
			ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
			if(netInfo != null && netInfo.isConnected()) {
				try {
					URL url = new URL("http://Www.google.com");
					HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
					urlConnection.setConnectTimeout(3000);
					urlConnection.connect();
					if(urlConnection.getResponseCode() == 200) {
						return true;
					}
				} catch (MalformedURLException mue) {
					mue.printStackTrace();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
			return false;
		}
		
		protected void onPostExecute(Boolean result) {
			if(result == true) {
				mDialog.dismiss();
				new ProcessLogin().execute();
			}
			else {
				mDialog.dismiss();
				Toast.makeText(getApplicationContext(), "Error in Network Connection.", 
						   Toast.LENGTH_LONG).show();	
			}
		}
		
	}
	
	private class ProcessLogin extends AsyncTask {
		private ProgressDialog mDialog;
		private String mEmail, mPassword;
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			mEmail = mEmailView.getText().toString();
			mPassword = mPasswordView.getText().toString();
			mDialog = new ProgressDialog(LoginActivity.this);
			mDialog.setTitle("Contactinv Servers");
			mDialog.setMessage("Logging in...");
			mDialog.setIndeterminate(false);
			mDialog.setCancelable(true);
			mDialog.show();
		}
		
		@Override
		protected Object doInBackground(Object... arg0) {
			return null;
		}
		
	}
	
	public void NetAsync(View view) {
		new NetCheck().execute();
	}
}
