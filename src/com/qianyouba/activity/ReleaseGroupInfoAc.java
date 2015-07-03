package com.qianyouba.activity;

import java.util.Calendar;

import com.qianyouba.R;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2015/6/24 0024.
 */
public class ReleaseGroupInfoAc extends BaseActivity implements
		View.OnClickListener {
	private EditText tv_time;

	@Override
	protected void onCreate(@Nullable Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.release_group_info_layout);
		initView();

		initData();
	}

	private void initView() {
		other_text_title = (TextView) findViewById(R.id.tv_title);
		btn_back = (Button) findViewById(R.id.btn_back);
		tv_time = (EditText) findViewById(R.id.et_time);
	}

	private void initData() {
		other_text_title.setText("发布拼团");
		btn_back.setOnClickListener(this);
		tv_time.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btn_back:
			this.finish();
			break;
		case R.id.tv_time:
			Calendar c = Calendar.getInstance();
			DatePickerDialog datePicker = new DatePickerDialog(
					ReleaseGroupInfoAc.this, new OnDateSetListener() {
						@Override
						public void onDateSet(DatePicker view, int year,
								int monthOfYear, int dayOfMonth) {
							Toast.makeText(
									ReleaseGroupInfoAc.this,
									year + "年 " + (monthOfYear + 1)
											+ "月 " + dayOfMonth + "日",
									Toast.LENGTH_SHORT).show();
							tv_time.setText(year + "-" + (monthOfYear + 1)
											+ "- " + dayOfMonth + "-");
						}
					}, c.get(Calendar.YEAR), c.get(Calendar.MONTH),
					c.get(Calendar.DAY_OF_MONTH)){
				@Override
				protected void onStop() {
					//super.onStop();
				}
			};
			datePicker.show();
			break;
		}
	}
}
