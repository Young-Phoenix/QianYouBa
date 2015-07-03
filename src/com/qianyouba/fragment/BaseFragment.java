package com.qianyouba.fragment;

import java.util.Map;

import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment {
	//发送数据的监听
	protected OnFragListener mCallback;
	public interface OnFragListener{
		void sendData(int type,Object data);
	}
	//单击事件的监听
	protected OnClickInnerListener mClick;
	public interface OnClickInnerListener{
		void onClick(int id,Map<String,String> params);
	}
}
