package com.qianyouba.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.qianyouba.R;
import com.qianyouba.bean.GroupItemData;
import com.qianyouba.bean.ViewData;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class GroupListAdapter extends BaseAdapter {
	private DisplayImageOptions options;

	private Context mContext;
	private List<GroupItemData> mLists;
	private LayoutInflater mInflater;
	private ViewHolder viewHolder = null;

	public GroupListAdapter(Context context, List<GroupItemData> lists) {
		this.mContext = context;
		this.mLists = lists;
		this.mInflater = LayoutInflater.from(mContext);
		this.options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.qian_default_image) // 设置图片下载期间显示的图片
				.showImageForEmptyUri(R.drawable.qian_default_image) // 设置图片Uri为空或是错误的时候显示的图片
				.showImageOnFail(R.drawable.qian_default_image) // 设置图片加载或解码过程中发生错误显示的图片
				.cacheInMemory(true) // 设置下载的图片是否缓存在内存中
				.cacheOnDisk(true) // 设置下载的图片是否缓存在SD卡中
				.displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
				.build(); // 创建配置过得DisplayImageOption对象
	}

	public void setmLists(List<GroupItemData> mLists) {
		this.mLists = mLists;
	}

	@Override
	public int getCount() {
		return mLists != null ? mLists.size() : 0;
	}

	@Override
	public Object getItem(int position) {
		return mLists != null ? mLists.get(position) : null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.group_list_item, null);
			viewHolder.pictureView = (ImageView) convertView
					.findViewById(R.id.iv_picture);
			viewHolder.summary = (TextView) convertView
					.findViewById(R.id.tv_summary);
			viewHolder.price = (TextView) convertView.findViewById(R.id.tv_price);
			viewHolder.status = (TextView) convertView
					.findViewById(R.id.tv_status);
			viewHolder.type = (TextView) convertView
					.findViewById(R.id.tv_type);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		GroupItemData data = (GroupItemData) getItem(position);
		if (data != null) {
			ImageLoader.getInstance().displayImage(data.getImageUrl(),
					viewHolder.pictureView, options,
					new AnimateFirstDisplayListener());
			viewHolder.summary.setText(data.getSummary());
			viewHolder.price.setText("￥" + data.getPrice() + "/人");
			viewHolder.status.setText(data.getStatus());
			viewHolder.type.setText(data.getType());
			// viewHolder.publishPPhoto.setBackgroundResource(viewData.getPublishPPhoto());

		}
		return convertView;
	}

	private final class ViewHolder {
		private ImageView pictureView;
		private TextView summary;
		private TextView price;
		private TextView status;
		private TextView type;
	}

	private static class AnimateFirstDisplayListener extends
			SimpleImageLoadingListener {

		static final List<String> displayedImages = Collections
				.synchronizedList(new LinkedList<String>());

		@Override
		public void onLoadingComplete(String imageUri, View view,
				Bitmap loadedImage) {
			if (loadedImage != null) {
				ImageView imageView = (ImageView) view;
				imageView.setImageBitmap(loadedImage);
				// 是否第一次显示
				boolean firstDisplay = !displayedImages.contains(imageUri);
				if (firstDisplay) {
					// 图片淡入效果
					FadeInBitmapDisplayer.animate(imageView, 500);
					displayedImages.add(imageUri);
				}
			}
		}
	}

}
