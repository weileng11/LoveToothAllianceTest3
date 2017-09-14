package com.lovetoothalliance.app.ui;

import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.lovetoothalliance.app.R;
import com.lovetoothalliance.app.adapter.CommonAdapter;
import com.lovetoothalliance.app.adapter.ViewHolder;
import com.lovetoothalliance.app.widget.TopBarLayout;
import com.utils.ToastUtils;
import com.utils.view.MyListview;

import java.util.ArrayList;

import butterknife.InjectView;

/**
 * Author:lt
 * time:2017/7/25.
 * contact：weileng143@163.com
 *
 * @description 优惠促销
 */

public class DiscountPromotionActivity extends BaseActivity implements AdapterView.OnItemClickListener{
    @InjectView(R.id.topbar)
    TopBarLayout topbar;
    @InjectView(R.id.lv_my_property)
    MyListview lvMyProperty;
    private BaseAdapter mBaseAdapter;

    private ArrayList<String> mTestDataList=new ArrayList<>();

    @Override
    protected void initView() {
        setContentView(R.layout.activity_my_property); //布局跟我的资产一样
    }

    @Override
    protected void initData() {
         topbar.setTxvTitleName("优惠促销");
        Toast.makeText(DiscountPromotionActivity.this,"点击了我",Toast.LENGTH_LONG);
        for (int i=0;i<5;i++){
            mTestDataList.add("测试"+i);
        }
        //显示数据
        setProperData();

    }

    public void setProperData(){
        mBaseAdapter = new CommonAdapter<String>(this, mTestDataList,
                R.layout.discount_promotion_item) {
            @Override
            public void convert(ViewHolder helper, final String msg,int position) {
                // 名称
                helper.setText(R.id.tv_yh_content, msg);
                helper.getView(R.id.rl_details).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ToastUtils.showToastLong(DiscountPromotionActivity.this,"点击了"+msg);
                    }
                });

            }
        };
        lvMyProperty.setAdapter(mBaseAdapter);
        lvMyProperty.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ToastUtils.showToastLong(DiscountPromotionActivity.this,"点击了我"+lvMyProperty.getItemAtPosition(i).toString());
    }
}
