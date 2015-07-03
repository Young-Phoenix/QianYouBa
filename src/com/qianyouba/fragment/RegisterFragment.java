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
public class RegisterFragment extends BaseFragment implements OnClickListener{
	private EditText username,verifyCode,password,confirmPwd;
	private TextView regetCode;
	private Button register;
	private ImageView close;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_register,container,false);
        username = (EditText) view.findViewById(R.id.et_username);
        verifyCode = (EditText) view.findViewById(R.id.et_verify_code);
		password = (EditText) view.findViewById(R.id.et_password);
		confirmPwd = (EditText) view.findViewById(R.id.et_confirm_password);
		register = (Button) view.findViewById(R.id.btn_register);
		regetCode = (TextView) view.findViewById(R.id.tv_reget_code);
		close = (ImageView) view.findViewById(R.id.iv_close_button);
		
		register.setOnClickListener(this);
		regetCode.setOnClickListener(this);
		close.setOnClickListener(this);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
        	mClick = (OnClickInnerListener)activity;
        }catch(Exception e){
        	throw new ClassCastException(activity.toString()+"must implements OnFragListener");
        }
    }

	@Override
	public void onClick(View view) {
		Map<String, String> params = new HashMap<String, String>();
		switch(view.getId()){
		//注册
		case R.id.btn_register:
			
			params.put("username", username.getText().toString());
			params.put("password", password.getText().toString());
			params.put("verifycode", verifyCode.getText().toString());
			
			break;
		//获取验证码
		case R.id.tv_reget_code:
			Map<String, String> params2 = new HashMap<String, String>();
			params2.put("username", username.getText().toString());
			break;
		//关闭
		case R.id.iv_close_button:
			params=null;
			break;
		}
		mClick.onClick(view.getId(), params);
	}
}
