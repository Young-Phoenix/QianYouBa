package com.qianyouba.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.widget.Button;
import android.widget.TextView;

public class BaseActivity extends FragmentActivity {
	protected TextView other_text_title;
	protected Button btn_back;
	@Override
	protected void onCreate(@Nullable Bundle bundle) {
		super.onCreate(bundle);
	}

}
