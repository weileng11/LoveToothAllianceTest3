package com.lovetoothalliance.app.ui;

import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.bumptech.glide.Glide;
import com.lovetoothalliance.app.LoveToothApplication;
import com.lovetoothalliance.app.R;
import com.lovetoothalliance.app.adapter.CommonAdapter;
import com.lovetoothalliance.app.adapter.ViewHolder;
import com.lovetoothalliance.app.constant.ServiceUrl;
import com.lovetoothalliance.app.net.BaseNetEntity;
import com.lovetoothalliance.app.net.OkHttpResponseCallback;
import com.lovetoothalliance.app.net.bean.response.NearbyRespBean;
import com.readystatesoftware.viewbadger.BadgeView;
import com.utils.ToastUtils;
import com.utils.view.MyListview;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Author:lt
 * time:2017/7/29.
 * contact：weileng143@163.com
 *
 * @description 附近的店
 */

public class NearbyShopActivity extends BaseActivity {
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

    private NearbyRespBean mNearbyBean;
    //listview
    private BaseAdapter mListviewAdapter;


    private List<NearbyRespBean.NearbyBeanList> mList = new ArrayList<NearbyRespBean.NearbyBeanList>();

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

        //获取网络数据
        getNearByData();
        //获取定位
        getLocationAddress();
    }

    /**
     * 定位当前的城市
     */
    public void getLocationAddress() {
        Log.i("INFO", "开始定位");
        //定位服务
        LoveToothApplication.getInstance().getMyLocation(NearbyShopActivity.this, new LoveToothApplication.MyLLLocationListener() {
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



    public void getNearByData() {
        BaseNetEntity baseNetEntity = new BaseNetEntity();
        baseNetEntity.get(this, ServiceUrl.Get_Nearby_Store, "正在加载数据", true, new OkHttpResponseCallback<NearbyRespBean>(NearbyRespBean.class) {
            @Override
            public void onSuccess(NearbyRespBean bean) {
                Log.i("INFO", "bean信息" + bean.getData().get(0).getGuid());
                mNearbyBean = bean;
                setNearbyValues();

                ToastUtils.showToastLong(NearbyShopActivity.this, "请求网络数据成功");
            }

            @Override
            public void onFailError(Exception e) {
                ToastUtils.showToastLong(NearbyShopActivity.this, "失败" + e);
            }
        });
    }


    /**
     * 设置gv文字和图片的参数
     *
     * @return
     */
    public void setNearbyValues() {
        mList.addAll(mNearbyBean.getData());
        //设置界面参数值
        setListViewData(mList);
    }

    public void setListViewData(List<NearbyRespBean.NearbyBeanList> mNearbyList) {

        mListviewAdapter = new CommonAdapter<NearbyRespBean.NearbyBeanList>(this, mNearbyList,
                R.layout.home_nearby_item) {
            @Override
            public void convert(ViewHolder helper, NearbyRespBean.NearbyBeanList item, int position) {
                Glide.with(NearbyShopActivity.this).load(item.getContent()).error(R.mipmap.home_seach).into((ImageView) helper.getView(R.id.iv_nearby));
                // 名称
                helper.setText(R.id.tv_nearby_name, item.getTitle());
//                //地址
                helper.setText(R.id.tv_nearby_address, item.getArea() + item.getAddress());
                helper.setText(R.id.tv_nearby_grade, item.getStars());

//                helper.setRb(R.id.rb_nearby, changeToInt(item.getStars()));
            }
        };
        searchLv.setAdapter(mListviewAdapter);
    }

    //将string 转换成double *10,转换成int
    public int changeToInt(String rbValue) {
        double rbDouble = Double.parseDouble(rbValue);
        double rbNearby = rbDouble * 20;

        int i = (int) rbNearby;
        return i;
    }


    @OnClick({R.id.img_back, R.id.iv_address})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.iv_address:
                getLocationAddress();
                break;
        }
    }
}
