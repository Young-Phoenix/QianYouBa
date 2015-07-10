package com.qianyouba.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qianyouba.R;
import com.qianyouba.application.App;

import java.util.Map;

public class MeFragment extends BaseFragment implements View.OnClickListener {
    public static final int LOGIN_OR_REGISTER = 0;
    public static final int USER_AUTH = 1;
    public static final int USER_EDIT = 2;
    //控制登录按钮的标志位
    private int user_flag;
    private TextView username_or_login, user_introduce;
    private RelativeLayout my_order, my_group, hand_in_hand, about_qianyouba,
            logout;
    //单击事件的监听
    protected OnWidgetClickListener mWidgetClick;

    public interface OnWidgetClickListener {
        void onWidgetClick(int id, int flag);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mWidgetClick = (OnWidgetClickListener) activity;
        } catch (Exception e) {
            throw new ClassCastException(activity.toString()
                    + "must implements OnFragListener");
        }
    }

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.me_fragment, container, false);
        username_or_login = (TextView) view
                .findViewById(R.id.tv_traveller_center_uname);
        user_introduce = (TextView) view
                .findViewById(R.id.tv_traveller_center_introduce);
        my_order = (RelativeLayout) view.findViewById(R.id.rl_order_manage);
        my_group = (RelativeLayout) view.findViewById(R.id.rl_my_group);
        hand_in_hand = (RelativeLayout) view.findViewById(R.id.rl_hand_in_hand);
        about_qianyouba = (RelativeLayout) view
                .findViewById(R.id.rl_about_qianyouba);
        logout = (RelativeLayout) view.findViewById(R.id.rl_logout);

        App app = (App) getActivity().getApplication();

        if (app.user != null) {
            username_or_login.setText(app.user.getUsername());
            //如果roleStatus为1，则用户已认证
            if (app.user.getUserState() == USER_AUTH) {
                user_flag = USER_EDIT;
            } else {
                user_flag = USER_AUTH;
            }
        } else {
            username_or_login.setText(getResources().getString(R.string.login));
            user_flag = LOGIN_OR_REGISTER;
        }
        username_or_login.setOnClickListener(this);
        my_order.setOnClickListener(this);
        hand_in_hand.setOnClickListener(this);
        about_qianyouba.setOnClickListener(this);
        logout.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        mWidgetClick.onWidgetClick(view.getId(), user_flag);
    }
}
