package com.lovetoothalliance.app.ui;

import android.widget.BaseAdapter;

import com.lovetoothalliance.app.R;
import com.lovetoothalliance.app.adapter.CommonAdapter;
import com.lovetoothalliance.app.adapter.ViewHolder;
import com.lovetoothalliance.app.widget.TopBarLayout;
import com.utils.view.MyListview;

import java.util.ArrayList;

import butterknife.InjectView;

/**
 * Author:lt
 * time:2017/7/25.
 * contact：weileng143@163.com
 *
 * @description 我的资产
 */

public class MyPropertyActivity extends BaseActivity {

    @InjectView(R.id.topbar)
    TopBarLayout topbar;
    @InjectView(R.id.lv_my_property)
    MyListview lvMyProperty;
    private BaseAdapter mBaseAdapter;

    private ArrayList<String> mTestDataList=new ArrayList<>();

    @Override
    protected void initView() {
        setContentView(R.layout.activity_my_property);
    }

    @Override
    protected void initData() {
        topbar.setTxvTitleName("我的资产");
        for (int i=0;i<5;i++){
            mTestDataList.add("测试"+i);
        }
        //显示数据
        setProperData();
    }

    public void setProperData(){
        mBaseAdapter = new CommonAdapter<String>(this, mTestDataList,
                R.layout.my_property_item) {
            @Override
            public void convert(ViewHolder helper, String msg,int position) {
                // 名称
                helper.setText(R.id.tv_mcontent, msg);

            }
        };
        lvMyProperty.setAdapter(mBaseAdapter);
    }


}
