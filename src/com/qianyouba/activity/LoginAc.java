package com.qianyouba.activity;

import java.util.Map;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.fasterxml.jackson.core.type.TypeReference;
import com.qianyouba.R;
import com.qianyouba.application.App;
import com.qianyouba.entity.Message;
import com.qianyouba.entity.User;
import com.qianyouba.fragment.BaseFragment.OnClickInnerListener;
import com.qianyouba.fragment.ForgetPasswordFragment;
import com.qianyouba.fragment.LoginFragment;
import com.qianyouba.fragment.RegisterFragment;
import com.qianyouba.http.HttpRequestUtil;
import com.qianyouba.http.HttpRequestUtil.HttpRequestListener;
import com.qianyouba.http.RequestUri;
import com.qianyouba.json.MessageJson;
import com.qinyouba.uitls.Constants;
import com.qinyouba.uitls.DialogUtil;
import com.qinyouba.uitls.L;

public class LoginAc extends BaseActivity implements OnClickInnerListener,
		HttpRequestListener {
	private static final String TAG = "LoginAc";
	private LoginFragment login;
	private RegisterFragment register;
	private ForgetPasswordFragment forgetPassword;
	private FragmentTransaction transaction;
	private Dialog dialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_layout);

		initView();
	}

	private void initView() {
		transaction = getSupportFragmentManager().beginTransaction();
		login = new LoginFragment();
		transaction.replace(R.id.fl_content, login);
		transaction.commit();
	}

	@Override
	public void onClick(int id, Map<String, String> params) {
		switch (id) {
		// 登录
		case R.id.btn_login:
			dialog = DialogUtil.createLoadingDialog(this,"登录ing……");
			dialog.show();
			login(params);
			break;
		// 关闭
		case R.id.iv_close_button:
			getSupportFragmentManager().popBackStack();
			break;
		// 注册
		case R.id.tv_register:
			register = new RegisterFragment();
			transaction.replace(R.id.fl_content, register);
			transaction.addToBackStack(null);
			transaction.commit();
			break;
		// 忘记密码
		case R.id.tv_forget_pwd:
			forgetPassword = new ForgetPasswordFragment();
			transaction.replace(R.id.fl_content, register);
			transaction.addToBackStack(null);
			transaction.commit();
			break;
		// 注册
		case R.id.btn_register:
			register(params);
			break;
		case R.id.tv_reget_code:
			getVerifyCode(params);
			break;
		}
	}

	private void getVerifyCode(Map<String, String> params) {
		new HttpRequestUtil(this).jsonRequest(Constants.GETVERIFYCODE_REQUEST,
				Constants.SERVER_IP, params);
	}

	private void register(Map<String, String> params) {
		new HttpRequestUtil(this).jsonRequest(Constants.REGISTER_REQUEST,
				Constants.SERVER_IP, params);
	}

	private void login(Map<String, String> params) {
		new HttpRequestUtil(this).stringRequest(Constants.LOGIN_REQUEST,
				Constants.QIANYOUBA_SERVER_IP, RequestUri.LOGIN,params);
	}

	@Override
	public void onResponse(int requestCode, String data) {
		if(dialog!=null && dialog.isShowing()){
			dialog.dismiss();
		}
		switch (requestCode) {
		case Constants.LOGIN_REQUEST:
			L.e(TAG,data);
			//在此处解析返回的data
			
			MessageJson<User> jsonUtil = new MessageJson<User>();
			Message<User> msg = jsonUtil.json2Obj(data,new TypeReference<Message<User>>(){});
			switch(msg.getResultCode()){
			case 200:
				((App)getApplication()).user = msg.getData();
				break;
			case 400:
				break;
			}
			break;
		case Constants.REGISTER_REQUEST:
			break;
		case Constants.GETVERIFYCODE_REQUEST:
			break;
		default:
			break;
		}
	}

	@Override
	public void onErrorResponse(int requestCode, String error) {
		if(dialog!=null && dialog.isShowing()){
			dialog.dismiss();
		}
		switch (requestCode) {
		case Constants.LOGIN_REQUEST:

			break;
		default:
			break;
		}
	}
}
