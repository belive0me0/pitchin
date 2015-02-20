package com.pitch.model.utils;

import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "pitchin";
	private static final String TABLE_LOGIN = "login";
	private static final String KEY_ID = "id";
	private static final String KEY_FIRSTNAME = "fname";
	private static final String KEY_LASTNAME = "lname";
	private static final String KEY_USERNAME = "uname";
	private static final String KEY_UID = "uid";
	private static final String KEY_CREATED_AT = "created_at";
	
	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_LOGIN_TABLE = "CREATE TABLE" + TABLE_LOGIN + "("
				+ KEY_ID + " INTEGER PRIMARY KEY,"
				+ KEY_FIRSTNAME + " TEXT,"
				+ KEY_LASTNAME + " TEXT,"
				+ KEY_USERNAME + " TEXT UNIQUE,"
				+ KEY_UID + " TEXT,"
				+ KEY_CREATED_AT + " TEXT" + ")";
		db.execSQL(CREATE_LOGIN_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN);
		onCreate(db);
	}
	
	public void addUser(String firstName, String lastName, String userName, String uId, String createdAt) {
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_FIRSTNAME, firstName);
		values.put(KEY_LASTNAME, lastName);
		values.put(KEY_USERNAME, userName);
		values.put(KEY_UID, uId);
		values.put(KEY_CREATED_AT, createdAt);
		
		database.insert(TABLE_LOGIN, null, values);
		database.close();
	}

	public HashMap<String, String> getUserDetails() {
		HashMap<String, String> user = new HashMap<String, String>();
		String selectQuery = "SELECT * FROM " + TABLE_LOGIN;
		SQLiteDatabase database = this.getReadableDatabase();
		Cursor cursor = database.rawQuery(selectQuery, null);
		cursor.moveToFirst();
		if(cursor.getCount() > 0) {
			user.put(KEY_FIRSTNAME, cursor.getString(1));
			user.put(KEY_LASTNAME, cursor.getString(1));
			user.put(KEY_USERNAME, cursor.getString(1));
			user.put(KEY_UID, cursor.getString(1));
			user.put(KEY_CREATED_AT, cursor.getString(1));
		}
		cursor.close();
		database.close();
		return user;
	}
	
	public int getRawCount() {
		String countQuery = "SELECT * FROM " + TABLE_LOGIN;
		SQLiteDatabase database = this.getReadableDatabase();
		Cursor cursor = database.rawQuery(countQuery, null);
		int rowCount = cursor.getCount();
		database.close();
		cursor.close();
		return rowCount;
	}
	
	public void resetTables() {
		SQLiteDatabase database = this.getWritableDatabase();
		database.delete(TABLE_LOGIN, null, null);
		database.close();
	}
}
