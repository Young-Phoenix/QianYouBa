package com.qianyouba.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qianyouba.R;

/**
 * Created by Administrator on 2015/6/23 0023.
 */
public class ForgetPasswordFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_forget_password,container,false);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
        	mCallback = (OnFragListener)activity;
        }catch(Exception e){
        	throw new ClassCastException(activity.toString()+"must implements OnFragListener");
        }
    }
}
