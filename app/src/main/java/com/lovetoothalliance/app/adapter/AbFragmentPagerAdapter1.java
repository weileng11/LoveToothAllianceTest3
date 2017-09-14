package com.lovetoothalliance.app.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

public class AbFragmentPagerAdapter1 extends FragmentPagerAdapter {
	/** The m fragment list. */
//	private ArrayList<Fragment> mFragmentList = null;
//    private int currentPageIndex = 0;
	private String[] titles=null;

	public AbFragmentPagerAdapter1(FragmentManager mFragmentManager, String[] titles) {
		super(mFragmentManager);
		this.titles=titles;
	}
	@Override
	public CharSequence getPageTitle(int position) {
		// TODO Auto-generated method stub
		if(titles!=null&&titles.length>0){
			return titles[position];
		}else{return super.getPageTitle(position);}

	}

	@Override
	public int getCount() {
		return titles.length;
	}


	@Override
	public Fragment getItem(int position) {
		String id = null;
		if(position!=0){
			id = titles[position];
		}
		return null;

	}

	// 初始化每个页卡选项
	@Override
	public Object instantiateItem(ViewGroup arg0, int arg1) {
		return super.instantiateItem(arg0, arg1);
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		super.destroyItem(container, position, object);
	}
}
