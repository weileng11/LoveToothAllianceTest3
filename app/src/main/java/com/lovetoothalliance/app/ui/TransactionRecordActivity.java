package com.lovetoothalliance.app.ui;

import android.util.Log;
import android.widget.BaseAdapter;

import com.lovetoothalliance.app.R;
import com.lovetoothalliance.app.adapter.CommonAdapter;
import com.lovetoothalliance.app.adapter.ViewHolder;
import com.lovetoothalliance.app.bean.PublicInfo;
import com.lovetoothalliance.app.widget.TopBarLayout;
import com.utils.view.MyListview;

import java.util.ArrayList;

import butterknife.InjectView;

/**
 * Author:lt
 * time:2017/7/21.
 * contact：weileng143@163.com
 *
 * @description 交易记录
 */

public class TransactionRecordActivity extends BaseActivity {
    @InjectView(R.id.topbar)
    TopBarLayout topbar;
    @InjectView(R.id.lv_transaction)
    MyListview lvTransaction;

    private String[] homeList=new String[]{"我的镶牙","我要种牙","其他项目","找门诊",
            "找专家","客服","门诊寻求合作","积分商城"};
    private int[] ivList=new int[]{R.mipmap.home_xtooth,R.mipmap.home_zytooth,
            R.mipmap.home_else,R.mipmap.home_recode,R.mipmap.home_specialist,
            R.mipmap.home_service,R.mipmap.home_collaborate, R.mipmap.home_store};
    private BaseAdapter mBaseAdapter;
    private ArrayList<PublicInfo> pList=new ArrayList<>();
    @Override
    protected void initView() {
        setContentView(R.layout.activity_transaction_record);
    }

    @Override
    protected void initData() {
        topbar.setTxvTitleName("交易记录");
        //测试显示
        setPicAndTvValue();
    }


    /**
     * 设置gv文字和图片的参数
     *
     * @return
     */
    public void setPicAndTvValue() {
        for (int i=0;i<homeList.length;i++){
            PublicInfo pi=new PublicInfo(homeList[i],ivList[i]);
            pList.add(pi);
        }
        Log.i("INFO","总数据"+pList.size());
        //设置界面参数值
        setInterfaceParams(pList);
    }

    public void setInterfaceParams(ArrayList<PublicInfo> mPersonList){
        mBaseAdapter = new CommonAdapter<PublicInfo>(TransactionRecordActivity.this, mPersonList,
                R.layout.transaction_record_item) {
            @Override
            public void convert(ViewHolder helper, PublicInfo item,int position) {
                // 名称
                helper.setText(R.id.tv_price, item.getName());

            }
        };
        lvTransaction.setAdapter(mBaseAdapter);
    }

}
