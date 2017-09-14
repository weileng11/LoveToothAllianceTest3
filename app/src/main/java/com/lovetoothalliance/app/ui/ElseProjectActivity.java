package com.lovetoothalliance.app.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.bumptech.glide.Glide;
import com.lovetoothalliance.app.LoveToothApplication;
import com.lovetoothalliance.app.R;
import com.lovetoothalliance.app.adapter.CommonAdapter;
import com.lovetoothalliance.app.adapter.ViewHolder;
import com.lovetoothalliance.app.constant.IntentParameter;
import com.lovetoothalliance.app.constant.ServiceUrl;
import com.lovetoothalliance.app.net.BaseNetEntity;
import com.lovetoothalliance.app.net.OkHttpResponseCallback;
import com.lovetoothalliance.app.net.bean.response.NearbyRespBean;
import com.utils.ToastUtils;
import com.utils.view.MyListview;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Author:lt
 * time:2017/7/28.
 * contact：weileng143@163.com
 *
 * @description 其他项目 (listview跳转到门店详情界面)
 */

public class ElseProjectActivity extends BaseActivity {
    @InjectView(R.id.ed_search)
    EditText edSearch;
    @InjectView(R.id.iv_message_search)
    ImageView ivMessageSearch;
    @InjectView(R.id.else_lv)
    MyListview elseLv;
    @InjectView(R.id.ll_inlay)
    LinearLayout llInlay;
    @InjectView(R.id.ll_veneering)
    LinearLayout llVeneering;
    @InjectView(R.id.ll_skin)
    LinearLayout llSkin;
    @InjectView(R.id.ll_zq)
    LinearLayout llZq;
    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.tv_address)
    TextView tvAddress;
    @InjectView(R.id.iv_address)
    ImageView ivAddress;
    //listview
    private BaseAdapter mListviewAdapter;
    //附近的店的集合
    private List<NearbyRespBean.NearbyBeanList> mNearbyList = new ArrayList<NearbyRespBean.NearbyBeanList>();

    @Override
    protected void initView() {
        setContentView(R.layout.activity_else_project);
    }

    @Override
    protected void initData() {
        setListViewData();
        getNearByData();
        getLocationAddress();
    }

    /**
     * 定位当前的城市
     */
    public void getLocationAddress() {
        Log.i("INFO", "开始定位");
        //定位服务
        LoveToothApplication.getInstance().getMyLocation(ElseProjectActivity.this, new LoveToothApplication.MyLLLocationListener() {
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


    @OnClick({R.id.ll_inlay, R.id.ll_veneering, R.id.ll_skin, R.id.ll_zq, R.id.iv_back, R.id.ed_search,R.id.iv_address})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_inlay:
                commitToMyTooth();
                break;
            case R.id.ll_veneering:
                commitToMyTooth();
                break;
            case R.id.ll_skin:
                //也是跳转到我要镶牙界面
                commitToMyTooth();
                break;
            case R.id.ll_zq:
                //也是跳转到我要镶牙界面
                commitToMyTooth();
                break;
            case R.id.ed_search:
                skipActivity(ElseProjectActivity.this, SearchActivity.class);
                break;
            case R.id.iv_address:
                getLocationAddress();
                break;
        }
    }

    /**
     * 附近的店的请求数据
     */
    public void getNearByData() {
        BaseNetEntity baseNetEntity = new BaseNetEntity();
        baseNetEntity.get(ElseProjectActivity.this, ServiceUrl.Get_Nearby_Store, "正在加载数据", true, new OkHttpResponseCallback<NearbyRespBean>(NearbyRespBean.class) {
            @Override
            public void onSuccess(NearbyRespBean bean) {
                if (bean.data.size() > 0) {
                    setNearbyValues(bean.data);
                }else{
                    //没有数据
                }
            }

            @Override
            public void onFailError(Exception e) {
                ToastUtils.showToastLong(ElseProjectActivity.this, "失败" + e);
            }
        });
    }

    /**
     * 设置gv文字和图片的参数
     *
     * @return
     */
    public void setNearbyValues(List<NearbyRespBean.NearbyBeanList> mNearbyValuesList) {
        if(mListviewAdapter!=null){
            mNearbyList.addAll(mNearbyValuesList);
            mListviewAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 附近的店的数据显示
     *
     * @param
     */
    private NearbyRespBean.NearbyBeanList mNearbyBeanList;
    public void setListViewData() {
        mListviewAdapter = new CommonAdapter<NearbyRespBean.NearbyBeanList>(ElseProjectActivity.this, mNearbyList,
                R.layout.home_nearby_item) {
            @Override
            public void convert(ViewHolder helper, NearbyRespBean.NearbyBeanList item, int position) {
                Glide.with(ElseProjectActivity.this).load(item.getContent()).error(R.mipmap.home_seach).into((ImageView) helper.getView(R.id.iv_nearby));
                // 名称
                helper.setText(R.id.tv_nearby_name, item.getTitle());
//                //地址
                helper.setText(R.id.tv_nearby_address, item.getArea() + item.getAddress());
                helper.setText(R.id.tv_nearby_grade, item.getStars());

//                RatingBar ratingBar = helper.getView(R.id.rb_nearby);
//                ratingBar.setClickable(false);
//                Log.i(Tag,"星星"+item.getStars());
//                if(item.getStars()!=""){
//                    ratingBar.setStar(JavaUtils.convertToFloat(item.getStars()));
//                }

            }
        };
        elseLv.setAdapter(mListviewAdapter);
        elseLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                Toast.makeText(getActivity(), "你点击了：" + position, Toast.LENGTH_SHORT).show();
                //进入门诊详情
                Bundle bundle = new Bundle();
                bundle.putString(IntentParameter.ClinicName,mNearbyList.get(position).getTitle());
                bundle.putString(IntentParameter.ClinicPic, mNearbyList.get(position).getContent());
                bundle.putString(IntentParameter.ClinicLocation, mNearbyList.get(position).getArea() +mNearbyList.get(position).getAddress());
                bundle.putString(IntentParameter.Clinicjl, mNearbyList.get(position).getStars());
                showActivity(ElseProjectActivity.this, ClinicContentActivity.class, bundle);
            }
        });

    }

    /**
     * 跳转到我的镶牙界面
     */
    public void commitToMyTooth() {
        //也是跳转到我要镶牙界面(根据id不同)
        Bundle bundle = new Bundle();
        bundle.putString("guid", "856dc4d0-08bd-4cd9-b85d-3f391f203554");
        showActivity(ElseProjectActivity.this, MyWantToothActivity.class, bundle);
    }

}
