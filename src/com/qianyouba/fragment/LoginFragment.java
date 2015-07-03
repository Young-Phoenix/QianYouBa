package com.qianyouba.fragment;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.qianyouba.R;

/**
 * Created by Administrator on 2015/6/23 0023.
 */
public class LoginFragment extends BaseFragment {
	private EditText username, password;
	private Button btn_login;
	private TextView register, forgetPwd;
	private ImageView close;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.qian_login_layout, container,
				false);
		username = (EditText) view.findViewById(R.id.et_username);
		password = (EditText) view.findViewById(R.id.et_password);
		register = (TextView) view.findViewById(R.id.tv_register);
		forgetPwd = (TextView) view.findViewById(R.id.tv_forget_pwd);
		btn_login = (Button) view.findViewById(R.id.btn_login);
		close = (ImageView) view.findViewById(R.id.iv_close_button);
		register.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				mClick.onClick(view.getId(), null);
			}
		});
		forgetPwd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				mClick.onClick(view.getId(), null);
			}
		});
		btn_login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Map<String, String> params = new HashMap<String, String>();
				params.put("username", username.getText().toString());
				params.put("password", password.getText().toString());
				mClick.onClick(view.getId(), params);
			}
		});
		close.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				mClick.onClick(view.getId(), null);
			}
		});
		return view;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mClick = (OnClickInnerListener) activity;
		} catch (Exception e) {
			throw new ClassCastException(activity.toString()
					+ "must implements OnFragListener");
		}
	}
}
