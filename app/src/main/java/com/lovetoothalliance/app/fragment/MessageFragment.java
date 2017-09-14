package com.lovetoothalliance.app.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import com.lovetoothalliance.app.R;
import com.lovetoothalliance.app.adapter.CommonAdapter;
import com.lovetoothalliance.app.adapter.ViewHolder;
import com.lovetoothalliance.app.bean.PublicInfo;
import com.lovetoothalliance.app.ui.CounselActivity;
import com.lovetoothalliance.app.ui.DiscountPromotionActivity;
import com.lovetoothalliance.app.ui.MyPropertyActivity;
import com.lovetoothalliance.app.widget.TopBarLayout;
import com.utils.view.MyListview;

import java.util.ArrayList;

import butterknife.InjectView;


/**
 * Author:lt
 * time:2017/7/17.
 * contact：weileng143@163.com
 *
 * @description
 */

public class MessageFragment extends BaseFragment {

    @InjectView(R.id.message_lv)
    MyListview messageLv;
    @InjectView(R.id.topbar)
    TopBarLayout topbar;
    private String[] homeList = new String[]{"我的资产", "客服消息", "优惠促销", "系统通知",
            "法律顾问"};
    private int[] ivList = new int[]{R.mipmap.message_my, R.mipmap.message_kf,
            R.mipmap.message_discounts, R.mipmap.message_notifaction, R.mipmap.message_law};

    private ArrayList<PublicInfo> pList = new ArrayList<>();
    private BaseAdapter mBaseAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fraggment_message;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        topbar.setTxvTitleName("消息");
        topbar.setTxvLeftShow();
        setPicAndTvValue();
    }

    /**
     * 设置gv文字和图片的参数
     *
     * @return
     */
    public void setPicAndTvValue() {
        for (int i = 0; i < homeList.length; i++) {
            PublicInfo pi = new PublicInfo(homeList[i], ivList[i]);
            pList.add(pi);
        }
        Log.i("INFO", "总数据" + pList.size());
        //设置界面参数值
        setMessageValue(pList);
    }

    /**
     * 测试显示
     */
    public void setMessageValue(ArrayList<PublicInfo> mList) {
        mBaseAdapter = new CommonAdapter<PublicInfo>(getActivity(), mList,
                R.layout.message_item) {
            @Override
            public void convert(ViewHolder helper, PublicInfo item,int position) {
                // 名称
                helper.setText(R.id.tv_message_name, item.getName());
                //图片
                helper.setImageBackground(R.id.iv_message, item.getIvContent());

                // 内容
                helper.setText(R.id.tv_message_content, item.getName());
                //时间
                helper.setText(R.id.tv_message_date, item.getName());

            }
        };
        messageLv.setAdapter(mBaseAdapter);
        messageLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PublicInfo pi = (PublicInfo) mBaseAdapter.getItem(i);
                if (pi.getName().equals("我的资产")) {
                    showActivity(getActivity(), MyPropertyActivity.class);
                } else if (pi.getName().equals("客服消息")) {

                } else if (pi.getName().equals("优惠促销")) {
                    showActivity(getActivity(), DiscountPromotionActivity.class);
                } else if (pi.getName().equals("系统通知")) {

                } else if (pi.getName().equals("法律顾问")) {
                    showActivity(getActivity(), CounselActivity.class);
                }

            }
        });
    }


}
