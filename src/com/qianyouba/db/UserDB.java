package com.qianyouba.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.qianyouba.entity.Area;
import com.qianyouba.entity.City;
import com.qianyouba.entity.Province;
import com.qianyouba.entity.User;

public class UserDB {
	public static final String DB_NAME = "qianyouba.db";
	private static final String TABLE_USER = "user";
	private static final String TABLE_AREA = "area";
	private static final String TABLE_CITY = "city";
	private static final String TABLE_PROVINCE = "province";
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
		Cursor c = db.rawQuery("SELECT * from " + TABLE_USER, null);
		if (c.moveToFirst()) {
			int id = c.getInt(c.getColumnIndex("Id"));
			String username = c.getString(c.getColumnIndex("UserName"));
			String userpass = c.getString(c.getColumnIndex("UserPass"));
			String usertel = c.getString(c.getColumnIndex("UserTel"));
			String userCom = c.getString(c.getColumnIndex("UserCom"));
			String userAddress = c.getString(c.getColumnIndex("UserAddress"));
			String userQQ = c.getString(c.getColumnIndex("UserQQ"));
			String userAddtime = c.getString(c.getColumnIndex("UserAddtime"));
			int userMoney = c.getInt(c.getColumnIndex("UsrMoney"));
			int userState = c.getInt(c.getColumnIndex("UsrState"));
			String userIP = c.getString(c.getColumnIndex("UsrIP"));
			String userEmail = c.getString(c.getColumnIndex("UserEmail"));
			String idCard = c.getString(c.getColumnIndex("idcard"));
			String sex = c.getString(c.getColumnIndex("sex"));
			int age = c.getInt(c.getColumnIndex("UsrState"));
			String touxiang = c.getString(c.getColumnIndex("touxiang"));

			int areaId = c.getInt(c.getColumnIndex("areaid"));
			int areaID = c.getInt(c.getColumnIndex("areaID"));
			String area = c.getString(c.getColumnIndex("area"));

			int cityId = c.getInt(c.getColumnIndex("cityid"));
			int cityID = c.getInt(c.getColumnIndex("cityID"));
			String city = c.getString(c.getColumnIndex("city"));

			int provinceId = c.getInt(c.getColumnIndex("provinceid"));
			int provinceID = c.getInt(c.getColumnIndex("provinceID"));
			String province = c.getString(c.getColumnIndex("province"));
			String type = c.getString(c.getColumnIndex("type"));

			Province provinceObj = new Province(provinceId, provinceID,
					province, type);
			City cityObj = new City(cityId, cityID, city, provinceObj);
			Area areaObj = new Area(areaId, areaID, area, cityObj);

			String realName = c.getString(c.getColumnIndex("user_id"));
			String info = c.getString(c.getColumnIndex("user_id"));
			user = new User(id, username, userpass, usertel, userCom,
					userAddress, userQQ, userAddtime, userMoney, userState,
					userIP, userEmail, idCard, sex, age, touxiang, areaObj,
					cityObj, provinceObj, realName, info);
		}
		return user;
	}

	public void saveuser(User user) {
		db.beginTransaction();
		try {
			ContentValues provinceVal = new ContentValues();
			provinceVal.put("id", user.getProvince().getId());
			provinceVal.put("provinceID", user.getProvince().getProvinceID());
			provinceVal.put("type", user.getProvince().getType());
			provinceVal.put("province", user.getProvince().getType());
			ContentValues cityVal = new ContentValues();
			cityVal.put("id", user.getCity().getId());
			cityVal.put("cityID", user.getCity().getCityID());
			cityVal.put("city", user.getCity().getCity());
			cityVal.put("father", user.getProvince().getId());
			ContentValues areaVal = new ContentValues();
			areaVal.put("id", user.getArea().getId());
			areaVal.put("areaID", user.getArea().getAreaID());
			areaVal.put("area", user.getArea().getArea());
			areaVal.put("father", user.getCity().getCityID());
			ContentValues userVal = new ContentValues();
			userVal.put("Id", user.getId());
			userVal.put("UserName", user.getUsername());
			userVal.put("UserPass", user.getUserpass());
			userVal.put("UserTel", user.getUsertel());
			userVal.put("UserCom", user.getUserCom());
			userVal.put("UserAddress", user.getUserAddress());
			userVal.put("UserQQ", user.getUserQQ());
			userVal.put("UserAddtime", user.getUserAddtime());
			userVal.put("UserMoney", user.getUserMoney());
			userVal.put("UserState", user.getUserState());
			userVal.put("UserIP", user.getUserIP());
			userVal.put("UserEmail", user.getUserEmail());
			userVal.put("idCard", user.getIdCard());
			userVal.put("sex", user.getSex());
			userVal.put("age", user.getAge());
			userVal.put("touxiang", user.getTouxiang());
			userVal.put("area", user.getArea().getAreaID());
			userVal.put("city", user.getCity().getCityID());
			userVal.put("province", user.getProvince().getProvinceID());
			userVal.put("realName", user.getRealName());
			userVal.put("info", user.getInfo());
			db.insert(TABLE_PROVINCE, "id", provinceVal);
			db.insert(TABLE_CITY, "id", cityVal);
			db.insert(TABLE_AREA, "id", areaVal);
			db.insert(TABLE_USER, "id", userVal);
			db.setTransactionSuccessful();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.endTransaction();
		}
	}
}
