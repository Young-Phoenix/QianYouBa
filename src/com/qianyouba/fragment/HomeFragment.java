package com.qianyouba.fragment;

import java.util.LinkedList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.qianyouba.R;
import com.qianyouba.adapter.HomeListAdapter;
import com.qianyouba.application.App;
import com.qianyouba.bean.ViewData;

public class HomeFragment extends BaseFragment implements View.OnClickListener {

    private RadioGroup mRadioGroup;
    private PullToRefreshListView mPullToRefreshListView;
    private HomeListAdapter mAdapter;
    private LinkedList<ViewData> mLists = new LinkedList<ViewData>();
    private int tabId;
    private TextView load_failed;
    private ImageView loading_img;
    private AnimationDrawable loading_anim;

    @Override
    public void onAttach(Activity activity) {
        try {
            mCallback = (OnFragListener) activity;
        } catch (Exception e) {
            throw new ClassCastException("The activity must implements BaseFragment.OnFragListener");
        }
        super.onAttach(activity);
    }

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        mPullToRefreshListView = (PullToRefreshListView) view
                .findViewById(R.id.p2rlv_home);
        final ListView mListView = mPullToRefreshListView.getRefreshableView();
        mRadioGroup = (RadioGroup) view.findViewById(R.id.rg_view_point);
        mRadioGroup.check(R.id.rb_our_view);
        loading_img = (ImageView) view.findViewById(R.id.loading_anim);
        loading_anim = (AnimationDrawable) loading_img.getBackground();

        load_failed = (TextView) view.findViewById(R.id.load_failed);

        load_failed.setOnClickListener(this);
        /*ViewData viewData1 = new ViewData(R.drawable.test_image2,
                getResources().getString(R.string.summary_inner),
				R.drawable.ic_verify_realname_ok, Integer
				.parseInt(getString(R.string.price_inner)));
		if (mLists.size() > 0)
			mLists.clear();
		for (int i = 0; i < 10; i++) {
			mLists.add(viewData1);
		}*/
        mAdapter = new HomeListAdapter(getActivity(), mLists);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                mCallback.sendData(1, mLists.get(position));
            }
        });
        mRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_our_view:
                        tabId = 0;
                        Toast.makeText(getActivity(), "国内景点", Toast.LENGTH_SHORT)
                                .show();
                        ViewData viewData1 = new ViewData(R.drawable.test_image2,
                                getResources().getString(R.string.summary_inner),
                                "http://d.lanrentuku.com/down/png/1503/male-avatars/cool-male-avatars-08.png", Integer
                                .parseInt(getString(R.string.price_inner)));
                        if (mLists.size() > 0)
                            mLists.clear();
                        for (int i = 0; i < 10; i++) {
                            mLists.add(viewData1);
                        }
                        mAdapter.notifyDataSetChanged();
                        break;
                    case R.id.rb_other_view:
                        tabId = 1;
                        Toast.makeText(getActivity(), "国外景点", Toast.LENGTH_SHORT)
                                .show();
                        ViewData viewData2 = new ViewData(R.drawable.test_image,
                                getResources().getString(R.string.summary_outer),
                                "http://d.lanrentuku.com/down/png/1503/male-avatars/cool-male-avatars-09.png", Integer
                                .parseInt(getString(R.string.price_inner)));
                        if (mLists.size() > 0)
                            mLists.clear();
                        for (int i = 0; i < 10; i++) {
                            mLists.add(viewData2);
                        }
                        mAdapter.notifyDataSetChanged();
                        break;
                }
            }
        });
        mPullToRefreshListView.setMode(Mode.BOTH);
        mPullToRefreshListView
                .setOnRefreshListener(new OnRefreshListener2<ListView>() {

                    @Override
                    public void onPullDownToRefresh(
                            PullToRefreshBase<ListView> refreshView) {
                        String label = DateUtils.formatDateTime(getActivity(),
                                System.currentTimeMillis(),
                                DateUtils.FORMAT_SHOW_TIME
                                        | DateUtils.FORMAT_SHOW_DATE
                                        | DateUtils.FORMAT_ABBREV_ALL);
                        // Update the LastUpdatedLabel
                        refreshView.getLoadingLayoutProxy()
                                .setLastUpdatedLabel(label);
                        // Do work to refresh the list here.
                        new GetDataTask(true).execute();
                    }

                    @Override
                    public void onPullUpToRefresh(
                            PullToRefreshBase<ListView> refreshView) {
                        String label = DateUtils.formatDateTime(getActivity(),
                                System.currentTimeMillis(),
                                DateUtils.FORMAT_SHOW_TIME
                                        | DateUtils.FORMAT_SHOW_DATE
                                        | DateUtils.FORMAT_ABBREV_ALL);
                        // Update the LastUpdatedLabel
                        mPullToRefreshListView.getLoadingLayoutProxy()
                                .setLastUpdatedLabel(label);
                        // Do work to refresh the list here.
                        new GetDataTask(false).execute();
                    }
                });
        initData();
        return view;
    }

    /**
     * 加载数据
     */
    private void initData() {
        App app = (App) getActivity().getApplication();
        startLoadingAnim();
        String url = "http://www.baidu.com";
        StringRequest request = new StringRequest(Method.POST, url,
                new Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        ViewData viewData1 = new ViewData(R.drawable.test_image2,
                                getResources().getString(R.string.summary_inner),
                                "http://d.lanrentuku.com/down/png/1503/male-avatars/cool-male-avatars-08.png", Integer
                                .parseInt(getString(R.string.price_inner)));
                        if (mLists.size() > 0)
                            mLists.clear();
                        for (int i = 0; i < 10; i++) {
                            mLists.add(viewData1);
                        }
                        stopLoadingAnim();
                        mAdapter.notifyDataSetChanged();
                        Log.e("HomeFragmnet", response);
                    }
                }, new ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                stopLoadingAnim();
                showLoadFailed();
                Log.e("HomeFragmnet", "errorMsg=" + error.getMessage());
            }
        });
        app.mQueue.add(request);
    }

    /**
     * 开始数据加载动画
     */
    private void startLoadingAnim() {
        loading_img.setVisibility(View.VISIBLE);
        loading_anim.start();
    }

    /**
     * 停止数据加载动画，并隐藏控件
     */
    private void stopLoadingAnim() {
        loading_img.setVisibility(View.GONE);
        if (loading_anim.isRunning()) {
            loading_anim.stop();
        }
    }

    /**
     * 显示数据加载失败提示
     */
    private void showLoadFailed() {
        if (load_failed.getVisibility() == View.GONE) {
            load_failed.setVisibility(View.VISIBLE);
        }
    }

    private void hiddenLoadFailed() {
        if (load_failed.getVisibility() == View.VISIBLE) {
            load_failed.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.load_failed:
                hiddenLoadFailed();
                initData();
                break;
        }
    }

    private class GetDataTask extends AsyncTask<Void, Void, String> {
        private boolean isPullDown;

        public GetDataTask(boolean isPullDown) {
            this.isPullDown = isPullDown;
        }

        @Override
        protected String doInBackground(Void... params) {
            // Simulates a background job.
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            String str = "Added after refresh...I add";
            return str;
        }

        @Override
        protected void onPostExecute(String result) {
            ViewData viewData = null;
            if (isPullDown) {
                switch (tabId) {
                    case 0:
                        viewData = new ViewData(R.drawable.test_image,
                                getResources().getString(R.string.summary_outer),
                                "http://d.lanrentuku.com/down/png/1503/male-avatars/cool-male-avatars-09.png", Integer
                                .parseInt(getString(R.string.price_inner)));
                        break;
                    case 1:
                        viewData = new ViewData(R.drawable.test_image2,
                                getResources().getString(R.string.summary_inner),
                                "http://d.lanrentuku.com/down/png/1503/male-avatars/cool-male-avatars-08.png", Integer
                                .parseInt(getString(R.string.price_inner)));
                        break;
                    default:
                        break;
                }
                mLists.addFirst(viewData);
            } else {
                switch (tabId) {
                    case 0:
                        viewData = new ViewData(R.drawable.test_image2,
                                getResources().getString(R.string.summary_inner),
                                "http://d.lanrentuku.com/down/png/1503/male-avatars/cool-male-avatars-09.png", Integer
                                .parseInt(getString(R.string.price_inner)));
                        break;
                    case 1:
                        viewData = new ViewData(R.drawable.test_image,
                                getResources().getString(R.string.summary_outer),
                                "http://d.lanrentuku.com/down/png/1503/male-avatars/cool-male-avatars-08.png", Integer
                                .parseInt(getString(R.string.price_inner)));
                        break;
                    default:
                        break;
                }
                mLists.addLast(viewData);
            }

            mAdapter.notifyDataSetChanged();
            // Call onRefreshComplete when the list has been refreshed.
            mPullToRefreshListView.onRefreshComplete();
            super.onPostExecute(result);
        }
    }
}
