package com.qianyouba.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class Database {
	Context context;
	DatabaseHelper dbhelper;
	public SQLiteDatabase sqlitedatabase;

	public Database(Context context) {
		super();
		this.context = context;
		dbhelper = DatabaseHelper.getInstance(context);
	}

	// 打开数据库连接
	public void opendb() {
		if (sqlitedatabase == null || !sqlitedatabase.isOpen())
			sqlitedatabase = dbhelper.getWritableDatabase();
	}

	// 关闭数据库连接
	public void closedb() {
		if (sqlitedatabase.isOpen()) {
			sqlitedatabase.close();
		}
	}

	// 插入表数据
	public void insert(String table_name, ContentValues values) {
		opendb();
		sqlitedatabase.insert(table_name, null, values);
		closedb();
	}

	// 更新数据
	public int updatatable(String table_name, ContentValues values,
			String whereClause, int ID) {
		opendb();
		return sqlitedatabase.update(table_name, values, whereClause,
				new String[] { String.valueOf(ID) });
	}

	// 删除表数据
	public void delete(String table_name, String whereClause, String[] whereArgs) {
		opendb();
		try {

			sqlitedatabase.delete(table_name, whereClause, whereArgs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closedb();
		}
	}
}
