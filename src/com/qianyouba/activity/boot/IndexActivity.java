package com.qianyouba.activity.boot;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;

import com.qianyouba.R;
import com.qianyouba.activity.MainActivity;
import com.qinyouba.uitls.Constants;
import com.qinyouba.uitls.SPUtils;

public class IndexActivity extends Activity {
	private SharedPreferences preferences;
	private Editor editor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.index_ac);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				if ((Boolean)SPUtils.get(IndexActivity.this, Constants.FIRST_START, true)) {
					// 将登录标志位设置为false，下次登录时不在显示首次登录界面
					SPUtils.put(IndexActivity.this, Constants.FIRST_START, false);
					Intent intent = new Intent();
					intent.setClass(IndexActivity.this,
							GuideActivity.class);
					IndexActivity.this.startActivity(intent);
					IndexActivity.this.finish();
				} else {
					Intent intent = new Intent();
					intent.setClass(IndexActivity.this, MainActivity.class);
					IndexActivity.this.startActivity(intent);
					IndexActivity.this.finish();

				}

			}
		}, Constants.SPLASH_DISPLAY_LENGHT);
	}
}
