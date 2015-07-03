package com.qianyouba.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.qianyouba.R;
import com.qianyouba.bean.GroupItemData;
import com.qianyouba.bean.ViewData;
import com.qianyouba.http.ImageLoaderUtil;

public class SceneryDetailAc extends BaseActivity implements OnClickListener{
	private Button back;
	private ImageView item_image;
	@Override
	protected void onCreate(@Nullable Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.scenery_detail_layout);
		initView();
		
		initData();
		
	}

	private void initView() {
		back = (Button)findViewById(R.id.btn_back);
		back.setOnClickListener(this);
		item_image = (ImageView)findViewById(R.id.iv_item);
		other_text_title = (TextView)findViewById(R.id.tv_title);
	}
	
	private void initData(){
		int type = getIntent().getIntExtra("type", 0);
		switch(type){
		case 1:
			other_text_title.setText("行程详情");
			ViewData data = (ViewData)getIntent().getSerializableExtra("obj");
			item_image.setImageResource(data.getImageUrl());
			break;
		case 2:
			other_text_title.setText("拼团详情");
			GroupItemData itemData = (GroupItemData)getIntent().getSerializableExtra("obj");
			ImageLoaderUtil.load(itemData.getImageUrl(), item_image);
			break;
		}
		
	}

	@Override
	public void onClick(View view) {
		switch(view.getId()){
		case R.id.btn_back:
			this.finish();
			break;
		}
	}
}
