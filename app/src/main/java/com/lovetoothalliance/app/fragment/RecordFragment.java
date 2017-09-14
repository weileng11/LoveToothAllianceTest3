package com.lovetoothalliance.app.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.WindowManager;

import com.lovetoothalliance.app.R;
import com.lovetoothalliance.app.adapter.AbFragmentPagerAdapter;
import com.lovetoothalliance.app.widget.PagerSlidingTabStrip;
import com.lovetoothalliance.app.widget.TopBarLayout;
import com.utils.L;

import java.util.ArrayList;

import butterknife.InjectView;


/**
 * Author:lt
 * time:2017/7/17.
 * contact：weileng143@163.com
 *
 * @description 诊断记录
 */

public class RecordFragment extends BaseFragment implements OnPageChangeListener {
    @InjectView(R.id.topbar)
    TopBarLayout topbar;
    @InjectView(R.id.tabs)
    PagerSlidingTabStrip tabs;
    //    @InjectView(R.id.pager)
    public static ViewPager pager;


    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        pager = (ViewPager) view.findViewById(R.id.pager);
        tabs.setWidth(getscrren()[0]);
        initData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_record;
    }


    /**
     * 初始化数据
     */
    private void initData() {
        topbar.setTxvTitleName(getActivity().getResources().getString(R.string.record));
        topbar.setTxvLeftShow();
        ArrayList<Fragment> pages = new ArrayList<Fragment>();
        String[] titls = {"全部", "待付款", "待使用", "待评价"};
        pages.add(new AllArrayFragment());
        pages.add(new PaymentFragment());
        pages.add(new StayPaymentFragment());
        pages.add(new StayEvaluateFragment());
        //页面适配器
        AbFragmentPagerAdapter fp = new AbFragmentPagerAdapter(getActivity().getSupportFragmentManager(), pages, titls);
        pager.setAdapter(fp);

        pager.setOffscreenPageLimit(4);
        tabs.setOnPageChangeListener(this);
        tabs.setViewPager(pager);
        tabs.setBackgroundColor(Color.GRAY);
        //指示器颜色
        tabs.setIndicatorColor(Color.rgb(249, 93, 74));
        //指示器的高度
        tabs.setIndicatorHeight(6);
        //字体大小
        tabs.setTextSize(40);
        //设置底线的高度
        tabs.setUnderlineHeight(0);
        //设置底线的颜色
        tabs.setUnderlineColor(Color.rgb(249, 93, 74));
        //适配时指定的高度
        tabs.getLayoutParams().height = 120;

    }

    //获取屏幕宽高
    private int[] getscrren() {
        int[] w_h = new int[2];
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        w_h[0] = width;
        w_h[1] = height;
        return w_h;
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPageSelected(int position) {
        // TODO Auto-generated method stub
        L.i("onPageSelected " + position);
//        pager.setCurrentItem(position);
//        refresh(position);
    }

//    List<AllArrayFragment> fragments = new ArrayList<AllArrayFragment>();
//    Handler refreshHandler  = new RefreshHandler();
//    class RefreshHandler extends Handler{
//
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            int position = msg.arg1;
//            AllArrayFragment fragment = fragments.get(position);
////            fragment.refresh();
//        }
//    }
//
//    /**
//     * 远程刷新界面
//     * @param position
//     */
//    private void refresh(int position) {
//        int id = 100001;
//        refreshHandler.removeMessages(id);
//        Message msg = new Message();
//        msg.what = id;
//        msg.arg1 = position;
//        refreshHandler.sendMessageDelayed(msg, 1000);
//    }
}

