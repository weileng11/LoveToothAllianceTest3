package com.lovetoothalliance.app.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.lovetoothalliance.app.LoveToothApplication;
import com.lovetoothalliance.app.R;
import com.lovetoothalliance.app.adapter.SpecialistAdapter;
import com.lovetoothalliance.app.constant.ServiceUrl;
import com.lovetoothalliance.app.dialog.PlaceOrderDialog;
import com.lovetoothalliance.app.fragment.BaseRecordFragment;
import com.lovetoothalliance.app.net.BaseNetEntity;
import com.lovetoothalliance.app.net.OkHttpResponseCallback;
import com.lovetoothalliance.app.net.bean.response.SpecialistOrderRespBean;
import com.lovetoothalliance.app.net.bean.response.SpecialistRespBean;
import com.readystatesoftware.viewbadger.BadgeView;
import com.utils.DensityUtil;
import com.utils.ToastUtils;
import com.utils.view.MyListview;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Author:lt
 * time:2017/7/26.
 * contact：weileng143@163.com
 *
 * @description 找专家
 */

public class SeekSpecialistActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @InjectView(R.id.ed_search)
    EditText edSearch;
    @InjectView(R.id.iv_message_search)
    ImageView ivMessageSearch;
    @InjectView(R.id.search_lv)
    MyListview searchLv;
    @InjectView(R.id.img_back)
    ImageView imgBack;
    @InjectView(R.id.tv_address)
    TextView tvAddress;
    @InjectView(R.id.iv_address)
    ImageView ivAddress;

    //listview
    private BaseAdapter mListviewAdapter;
    public List<SpecialistRespBean.DataBean> mSpecialist = new ArrayList<>();
    private SpecialistAdapter mSpecialistAdapter;
    //设置图片和文字显示
    public int mNum = 0;

    //自定义的弹出框类
    PlaceOrderDialog menuWindow;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_outpatient);
    }

    @Override
    protected void initData() {
        BadgeView mBadgeView = new BadgeView(this, ivMessageSearch);
        mBadgeView.setText("1");
        mBadgeView.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
        mBadgeView.show();
        setAdapterToListenter();
        //定位地址
        getLocationAddress();
        //测试数据
        getSpecialistData();
    }

    /**
     * 定位当前的城市
     */
    public void getLocationAddress() {
        Log.i("INFO", "开始定位");
        //定位服务
        LoveToothApplication.getInstance().getMyLocation(SeekSpecialistActivity.this, new LoveToothApplication.MyLLLocationListener() {
            @Override
            public void getLoaction(AMapLocation location, AMapLocationClient locationClient) {
                if (location.getErrorCode() == 0) {
                    //设置城市显示
                    setCityIsNull(location.getAddress(), locationClient);
                    Log.i("INFO", "地址" + location.getAddress());
                } else {
//                    ToastUtils.showToastLong(getActivity(),"定位失败");
                    //在一次定位，还是怎么处理...
                }

            }
        }, true);
    }

    //判断城市是否为null
    public void setCityIsNull(String address, AMapLocationClient mapLocationClient) {
        if (null == address || address.equals("")) {
            getLocationAddress();
        } else {
            tvAddress.setText(address);
            mapLocationClient.stopLocation();
        }
    }

    /**
     * 设置值到适配器并且下单按钮点击监听
     */
    public void setAdapterToListenter() {
        mSpecialistAdapter = new SpecialistAdapter(this, mSpecialist, new SpecialistAdapter.nextOrderListenter() {
            @Override
            public void startOrderListenter(final int position) {
                PlaceOrderDialog placeOrderDialog = new PlaceOrderDialog(SeekSpecialistActivity.this, new PlaceOrderDialog.PlaceOrderListener() {
                    @Override
                    public void onOrderCommit() {
                        //提交订单
                        commitOrder(position, mNum);
                    }

                    @Override
                    public void onGetNums(int num) {
                        mNum = num;
                        ToastUtils.showToastLong(SeekSpecialistActivity.this, "点击下单" + num);
                    }
                }, mSpecialist.get(position));
                placeOrderDialog.show();
                setPlateDialogPlace(placeOrderDialog, 0, DensityUtil.dip2px(SeekSpecialistActivity.this, 91));// 设置对话框位置
            }
        });
        searchLv.setAdapter(mSpecialistAdapter);
    }


    //测试各种地址
    public void getSpecialistData() {
        BaseNetEntity baseNetEntity = new BaseNetEntity();
        baseNetEntity.get(this, ServiceUrl.Get_Specialist, "正在获取数据", true, new OkHttpResponseCallback<SpecialistRespBean>(SpecialistRespBean.class) {
            @Override
            public void onSuccess(SpecialistRespBean bean) {
                Log.i("info", "数据" + bean.data.toString());
                //设置数据显示
                setSpecialistData(bean.data);
            }

            @Override
            public void onFailError(Exception e) {
                ToastUtils.showToastLong(SeekSpecialistActivity.this, "失败" + e);
            }
        });
    }

    /**
     * 设置图片和文字显示
     *
     * @param
     */
    public void setSpecialistData(List<SpecialistRespBean.DataBean> mlist) {
        mSpecialist.addAll(mlist);
        //设置值适配
//        setSpecialistListViewData();
        if (mSpecialistAdapter != null) {
            mSpecialistAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 提交订单
     */
    public void commitOrder(final int position, int num) {
        String commitUrl = ServiceUrl.Get_Order + mSpecialist.get(position).id
                + "&buyNumber=" + num + "&memLoginId=13725150568&DispatchModeGuid=" + mSpecialist.get(position).id;
        Log.i("info", "comiturl" + commitUrl);
        BaseNetEntity baseNetEntity = new BaseNetEntity();
        baseNetEntity.get(this, commitUrl, "正在获取数据", true, new OkHttpResponseCallback<SpecialistOrderRespBean>(SpecialistOrderRespBean.class) {
            @Override
            public void onSuccess(SpecialistOrderRespBean bean) {
                Log.i("info", "数据" + bean.data.result.toString());
                BaseRecordFragment.isSpecialist = true; //修改状态重新去拉取数据
                //获取到下单的值传递给立刻购买界面
                Bundle bundle = new Bundle();
                bundle.putString("ordernumber", bean.data.result.orderNumber);
                bundle.putDouble("shouldpayprice", bean.data.result.shouldPayPrice);
                //名称和图标
                bundle.putString("ordername", mSpecialist.get(position).name);
                bundle.putString("orderpic", mSpecialist.get(position).OriginalImge);
                bundle.putString("orderShopname", mSpecialist.get(position).ShopName);

                showActivity(SeekSpecialistActivity.this, ImmediatelyBuyActivity.class, bundle);
            }

            @Override
            public void onFailError(Exception e) {
                ToastUtils.showToastLong(SeekSpecialistActivity.this, "失败" + e);
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ToastUtils.showToastLong(this, "点击下单");
    }

    /**
     * 设置对话框的位置
     *
     * @param dg
     */
    private void setPlateDialogPlace(Dialog dg, int x, int y) {
        Window dialogWindow = dg.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        lp.width = LayoutParams.MATCH_PARENT;// FULL SCREEN
        // 显示的坐标
        // lp.y = y;
        dialogWindow.setAttributes(lp);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }

    @OnClick({R.id.img_back, R.id.ed_search,R.id.iv_address})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.iv_address:
                getLocationAddress();
                break;
            case R.id.ed_search:
                skipActivity(SeekSpecialistActivity.this, SearchActivity.class);
                break;
        }
    }
}
