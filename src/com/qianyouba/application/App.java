package com.qianyouba.application;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.app.Application;
import android.os.Environment;

import com.qianyouba.db.UserDB;
import com.qianyouba.entity.User;
import com.qinyouba.uitls.L;
import com.qinyouba.uitls.SPUtils;
import com.qinyouba.uitls.T;

public class App extends Application {
	public RequestQueue mQueue;
	public User user;
	private static final String VERSION = "version";

	@Override
	public void onCreate() {
		super.onCreate();
		// volley初始化配置
		mQueue = Volley.newRequestQueue(this);
		// imageloader初始化配置
		ImageLoaderConfiguration configuration = ImageLoaderConfiguration
				.createDefault(this);
		ImageLoader.getInstance().init(configuration);
		
		user = openUserDB().getUser();
	}

	private UserDB openUserDB() {
		String path = "/data"
				+ Environment.getDataDirectory().getAbsolutePath()
				+ File.separator + "com.qianyouba" + File.separator
				+ UserDB.DB_NAME;
		File db = new File(path);
		if (!db.exists() || (Integer)SPUtils.get(this, VERSION, -1) < 0) {
			L.i("db is not exists");
			try {
				InputStream is = getAssets().open(UserDB.DB_NAME);
				FileOutputStream fos = new FileOutputStream(db);
				int len = -1;
				byte[] buffer = new byte[1024];
				while ((len = is.read(buffer)) != -1) {
					fos.write(buffer, 0, len);
					fos.flush();
				}
				fos.close();
				is.close();
				SPUtils.put(this, VERSION, 1);// 用于管理数据库版本，如果数据库有重大更新时使用
			} catch (IOException e) {
				e.printStackTrace();
				T.showLong(this, e.getMessage());
				System.exit(0);
			}
		}
		return new UserDB(this, path);
	}
}
