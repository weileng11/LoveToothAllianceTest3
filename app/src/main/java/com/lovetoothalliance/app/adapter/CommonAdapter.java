package com.lovetoothalliance.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 
 * @descrption 通用适配器
 * @author chenwenlu
 * @date 2015-5-23
 * @param <B>
 *            实体bean
 */
public abstract class CommonAdapter<B> extends BaseAdapter {
	protected LayoutInflater mInflater;
	protected Context mContext;
	/**
	 * 实体bean容器
	 */
	public List<B> mDatas;
	/**
	 * item模板布局id
	 */
	protected final int mItemLayoutId;



	public void setmDatas(List<B> mDatas) {
		this.mDatas = mDatas;
	}

	public CommonAdapter(Context context, List<B> mDatas, int itemLayoutId) {
		this.mContext = context;
		this.mInflater = LayoutInflater.from(mContext);
		this.mDatas = mDatas;
		this.mItemLayoutId = itemLayoutId;
	}

	public List<B> getDatas() {
		return mDatas;
	}

	@Override
	public int getCount() {
		return mDatas == null ? 0 : mDatas.size();
	}

	@Override
	public B getItem(int position) {
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder viewHolder = getViewHolder(position, convertView,
				parent);
		if(position<getCount()){
			convert(viewHolder, getItem(position),position);
		}
		return viewHolder.getConvertView();

	}

	/**
	 * 子类覆写此方法进行item内控件的处理
	 * 
	 * @param helper
	 * @param item
	 */
	public abstract void convert(ViewHolder helper, B item,int position);

	private ViewHolder getViewHolder(int position, View convertView,
			ViewGroup parent) {
		return ViewHolder.get(mContext, convertView, parent, mItemLayoutId,
				position);
	}

}
