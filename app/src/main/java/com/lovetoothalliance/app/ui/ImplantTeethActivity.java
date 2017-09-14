package com.lovetoothalliance.app.ui;

import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;

import com.lovetoothalliance.app.R;
import com.lovetoothalliance.app.constant.ServiceUrl;
import com.lovetoothalliance.app.net.BaseNetEntity;
import com.lovetoothalliance.app.net.OkHttpResponseCallback;
import com.lovetoothalliance.app.net.bean.response.HomeProductRespBean;
import com.lovetoothalliance.app.widget.TopBarLayout;
import com.utils.ToastUtils;
import com.utils.view.MyGridView;

import butterknife.InjectView;

/**
 * Author:lt
 * time:2017/7/27.
 * contact：weileng143@163.com
 *
 * @description 我要种牙
 */

public class ImplantTeethActivity extends BaseActivity {
    @InjectView(R.id.topbar)
    TopBarLayout topbar;
    @InjectView(R.id.gv_implantteeth)
    MyGridView gvImplantteeth;

    public HomeProductRespBean mHomeProductRespBean;


    //    private String[] homeList = new String[]{"我的镶牙", "我要种牙", "其他项目", "找门诊",
//            "找专家", "客服", "门诊寻求合作", "积分商城"};
//    private int[] ivList = new int[]{R.mipmap.home_xtooth, R.mipmap.home_zytooth,
//            R.mipmap.home_else, R.mipmap.home_recode, R.mipmap.home_specialist,
//            R.mipmap.home_service, R.mipmap.home_collaborate, R.mipmap.home_store};
    private BaseAdapter mBaseAdapter;
//    private ArrayList<PublicInfo> pList = new ArrayList<>();

    @Override
    protected void initView() {
        setContentView(R.layout.activity_implantteeth);
    }

    @Override
    protected void initData() {
        topbar.setTxvTitleName("种牙");
        topbar.setRigthViewTypeMode(TopBarLayout.RightViewTypeMode.ADD);
        topbar.setBtnRightDrawable(R.mipmap.want_tooth_fx);
        topbar.setRightTxvOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //测试显示
//        setPicAndTvValue();
        //服务器数据
        getImplantTeethData();
    }


    //测试各种地址
    public void getImplantTeethData() {
        String guidUrl = ServiceUrl.Get_ToGuid_Product + "b9f7ce10-eb24-46ba-a5bd-a7c99fe3b377";
        BaseNetEntity baseNetEntity = new BaseNetEntity();
        baseNetEntity.get(this, guidUrl, "正在获取数据", true, new OkHttpResponseCallback
                <HomeProductRespBean>(HomeProductRespBean.class) {
            @Override
            public void onSuccess(HomeProductRespBean bean) {
                mHomeProductRespBean = bean;
                Log.i("info", "数据" + bean.data.toString());
                //设置数据显示
//                setNearbyValues();

            }

            @Override
            public void onFailError(Exception e) {
                ToastUtils.showToastLong(ImplantTeethActivity.this, "失败" + e);
            }
        });
    }


//    /**
//     * 设置参数
//     *
//     * @param mToothList
//     */
//    public void setListViewData(List<HomeProductRespBean.DataBean> mToothList) {
//        mBaseAdapter = new CommonAdapter<HomeProductRespBean.DataBean>(ImplantTeethActivity.this, mToothList,
//                R.layout.implantteeth_item) {
//            @Override
//            public void convert(ViewHolder helper, HomeProductRespBean.DataBean item) {
//                // 名称
//                helper.setText(R.id.tv_it_name, item.Name);
//
//            }
//        };
//        gvImplantteeth.setAdapter(mBaseAdapter);
//    }

}
