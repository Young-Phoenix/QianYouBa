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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.qianyouba.R;
import com.qianyouba.adapter.GroupListAdapter;
import com.qianyouba.application.App;
import com.qianyouba.bean.GroupItemData;

public class GroupFragment extends BaseFragment implements OnClickListener {
	private static final String TAG = "GroupFragment";
	private PullToRefreshListView mPullToRefreshListView;
	private GroupListAdapter mAdapter;
	private LinkedList<GroupItemData> mLists = new LinkedList<GroupItemData>();
	private TextView load_failed;
	private ImageView loading_img;
	private AnimationDrawable loading_anim;

	@Override
	public void onAttach(Activity activity) {
		try {
			mCallback = (OnFragListener) activity;
		} catch (Exception e) {
			throw new ClassCastException(
					"The activity must implements BaseFragment.OnFragListener");
		}
		super.onAttach(activity);
	}

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.group_fragment, container, false);
		loading_img = (ImageView) view.findViewById(R.id.loading_anim);
		loading_anim = (AnimationDrawable) loading_img.getBackground();

		load_failed = (TextView) view.findViewById(R.id.load_failed);

		load_failed.setOnClickListener(this);

		mPullToRefreshListView = (PullToRefreshListView) view
				.findViewById(R.id.p2rlv_group);
		final ListView mListView = mPullToRefreshListView.getRefreshableView();
		mAdapter = new GroupListAdapter(getActivity(), mLists);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				mCallback.sendData(2, mLists.get(position));
			}
		});
		mPullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
		mPullToRefreshListView
				.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

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

	private void initData() {
		App app = (App) getActivity().getApplication();
		startLoadingAnim();
		String url = "http://www.baidu.com";
		StringRequest request = new StringRequest(Request.Method.POST, url,
				new Response.Listener<String>() {

					@Override
					public void onResponse(String response) {
						GroupItemData data = new GroupItemData(
								"http://hiphotos.baidu.com/lvpics/pic/item/09fa513d269759ee8e21d563b2fb43166d22df21.jpg",
								"海南三亚旅游景点加井岛户外一日游团-海角之约潜水团游", "自驾游",
								"已加入3人/10人成团", 500.0f);
						if (mLists.size() > 0)
							mLists.clear();
						for (int i = 0; i < 10; i++) {
							mLists.add(data);
						}
						stopLoadingAnim();
						mAdapter.notifyDataSetChanged();
						Log.e(TAG, response);
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						stopLoadingAnim();
						showLoadFailed();
						Log.e(TAG, "errorMsg=" + error.getMessage());
					}
				});
		app.mQueue.add(request);
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
			GroupItemData data = null;
			if (isPullDown) {

				data = new GroupItemData(
						"http://hiphotos.baidu.com/lvpics/pic/item/fc1f4134970a304e4551c352d1c8a786c9175c22.jpg",
						"圣托里尼岛（Santorini）是爱琴海最璀璨的一颗明珠，柏拉图笔下的自由之地", "飞机游",
						"已加入2人/7人成团", 3000.0f);
				mLists.addFirst(data);
			} else {
				data = new GroupItemData(
						"http://hiphotos.baidu.com/lvpics/pic/item/d31b0ef41bd5ad6e480b104b81cb39dbb7fd3ca4.jpg",
						"厦门的街道很干净，慢节奏的生活很惬意。海鲜不错，当地小吃很棒", "骑行", "已加入4人/5人成团",
						500.0f);
				mLists.addLast(data);
			}
			mAdapter.notifyDataSetChanged();
			// Call onRefreshComplete when the list has been refreshed.
			mPullToRefreshListView.onRefreshComplete();
			super.

			onPostExecute(result);
		}

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
}
