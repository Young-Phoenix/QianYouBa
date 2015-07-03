package com.qianyouba.db;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.qianyouba.entity.User;

public class UserDB {
	public static final String DB_NAME = "qianyouba.db";
	private static final String TABLE_NAME = "user";
	private SQLiteDatabase db;

	public UserDB(Context context, String path) {
		db = context.openOrCreateDatabase(path, Context.MODE_PRIVATE, null);
	}

	public boolean isOpen() {
		return db != null && db.isOpen();
	}

	public void close() {
		if (db != null && db.isOpen())
			db.close();
	}
	
	public User getUser() {
		User user = null;
		Cursor c = db.rawQuery("SELECT * from " + TABLE_NAME, null);
		if (c.moveToFirst()) {
			String username = c.getString(c.getColumnIndex("user_name"));
			String nickname = c.getString(c.getColumnIndex("nick_name"));
			int onlineStatus = c.getInt(c.getColumnIndex("online_status"));
			int roleStatus = c.getInt(c.getColumnIndex("role_status"));
			String token = c.getString(c.getColumnIndex("token"));
			String userId = c.getString(c.getColumnIndex("user_id"));
			user = new User(null, 0, username, nickname, onlineStatus, roleStatus, token, 0, userId);
		}
		return user;
	}
}
