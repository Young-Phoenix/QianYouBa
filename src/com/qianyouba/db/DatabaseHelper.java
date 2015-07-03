package com.qianyouba.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1; // 数据库版本号

	private static final String DATABASE_NAME = "qianyouba"; // 数据库名称

	private DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	private static DatabaseHelper instance;
	public static DatabaseHelper getInstance(Context context){
		if(instance == null){
			instance = new DatabaseHelper(context);
		}
		return instance;
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sqlUser = "create table [user](" +
				"[id] INTEGER PRIMARY KEY ," +
				"[userid] VARCHAR(10)," +
				"[username] VARCHAR(20)," +
				"[nickname] VARCHAR(30)," +
				"[onlinestatus] INTEGER," +
				"[rolestatus] INTEGER," +
				"[token] VARCHAR(20))";
		db.execSQL(sqlUser);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {

	}

}
