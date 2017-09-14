package com.lovetoothalliance.app.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
import com.lovetoothalliance.app.bean.PublicInfo;
import com.lovetoothalliance.app.constant.IntentParameter;
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
 * time:2017/7/25.
 * contact：weileng143@163.com
 *
 * @description 搜索界面(找门诊)
 */

public class OutpatientActivity extends BaseActivity {
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

    private String[] homeList = new String[]{"我的镶牙", "我要种牙", "其他项目", "找门诊",
            "找专家", "客服", "门诊寻求合作", "积分商城"};
    private int[] ivList = new int[]{R.mipmap.home_xtooth, R.mipmap.home_zytooth,
            R.mipmap.home_else, R.mipmap.home_recode, R.mipmap.home_specialist,
            R.mipmap.home_service, R.mipmap.home_collaborate, R.mipmap.home_store};
    private ArrayList<PublicInfo> pList = new ArrayList<>();

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
        //加入定位（还是直接在主页传递过来？）
        getLocationAddress();

        //测试数据
        getNearByData();
    }

    /**
     * 定位当前的城市
     */
    public void getLocationAddress() {
        Log.i("INFO", "开始定位");
        //定位服务
        LoveToothApplication.getInstance().getMyLocation(OutpatientActivity.this, new LoveToothApplication.MyLLLocationListener() {
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
     * 附近的店的请求数据
     */
    //附近的店的bean
    private NearbyRespBean mNearbyBean;
    //附近的店的集合
    private List<NearbyRespBean.NearbyBeanList> mList = new ArrayList<NearbyRespBean.NearbyBeanList>();

    public void getNearByData() {
        BaseNetEntity baseNetEntity = new BaseNetEntity();
        baseNetEntity.get(OutpatientActivity.this, ServiceUrl.Get_Nearby_Store, "正在加载数据", true, new OkHttpResponseCallback<NearbyRespBean>(NearbyRespBean.class) {
            @Override
            public void onSuccess(NearbyRespBean bean) {
                Log.i("INFO", "bean信息" + bean.getData().get(0).getGuid());
                mNearbyBean = bean;
                setNearbyValues();

                ToastUtils.showToastLong(OutpatientActivity.this, "请求网络数据成功");
            }

            @Override
            public void onFailError(Exception e) {
                ToastUtils.showToastLong(OutpatientActivity.this, "失败" + e);
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

    /**
     * 设置附近店的lv
     *
     * @param mNearbyList
     */
    private NearbyRespBean.NearbyBeanList mNearbyBeanList;

    public void setListViewData(final List<NearbyRespBean.NearbyBeanList> mNearbyList) {
        mListviewAdapter = new CommonAdapter<NearbyRespBean.NearbyBeanList>(this, mNearbyList,
                R.layout.home_nearby_item) {
            @Override
            public void convert(ViewHolder helper, NearbyRespBean.NearbyBeanList item, int position) {
                mNearbyBeanList = item;
                Glide.with(OutpatientActivity.this).load(item.getContent()).error(R.mipmap.home_seach).into((ImageView) helper.getView(R.id.iv_nearby));
                // 名称
                helper.setText(R.id.tv_nearby_name, item.getTitle());
//                //地址
                helper.setText(R.id.tv_nearby_address, item.getArea() + item.getAddress());
                helper.setText(R.id.tv_nearby_grade, item.getStars());

//                RatingBar ratingBar = helper.getView(R.id.rb_nearby);
//                ratingBar.setClickable(false);
//                ratingBar.setStar(JavaUtils.convertToFloat(item.getStars()));
            }
        };
        searchLv.setAdapter(mListviewAdapter);
        searchLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //进入门诊详情
                Bundle bundle = new Bundle();
                bundle.putString(IntentParameter.ClinicName, mNearbyBeanList.getTitle());
                bundle.putString(IntentParameter.ClinicPic, mNearbyBeanList.getContent());
                bundle.putString(IntentParameter.ClinicLocation, mNearbyBeanList.getArea() + mNearbyBeanList.getAddress());
                bundle.putString(IntentParameter.Clinicjl, mNearbyBeanList.getStars());
                showActivity(OutpatientActivity.this, ClinicContentActivity.class, bundle);
            }
        });
    }

    @OnClick({R.id.img_back, R.id.iv_address,R.id.ed_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.iv_address:
                getLocationAddress();
                break;
            case R.id.ed_search:
                skipActivity(OutpatientActivity.this,SearchActivity.class);
                break;
        }
    }
}
