package com.lovetoothalliance.app.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lovetoothalliance.app.R;
import com.lovetoothalliance.app.adapter.CommonAdapter;
import com.lovetoothalliance.app.adapter.ListItemAdapter;
import com.lovetoothalliance.app.adapter.ViewHolder;
import com.lovetoothalliance.app.bean.ItemEntity;
import com.lovetoothalliance.app.bean.PublicInfo;
import com.lovetoothalliance.app.constant.IntentParameter;
import com.lovetoothalliance.app.constant.ServiceUrl;
import com.lovetoothalliance.app.dialog.ShareDialog;
import com.lovetoothalliance.app.net.BaseNetEntity;
import com.lovetoothalliance.app.net.OkHttpResponseCallback;
import com.lovetoothalliance.app.net.bean.response.HomeProductRespBean;
import com.lovetoothalliance.app.widget.TopBarLayout;
import com.utils.ToastUtils;
import com.utils.view.MyListview;
import com.utils.view.RatingBar;

import java.util.ArrayList;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Author:lt
 * time:2017/7/27.
 * contact：weileng143@163.com
 *
 * @description (首页诊所点击进入内容页面, 门诊详情)
 */

public class ClinicContentActivity extends BaseActivity {

    private static String Tag = "ClinicContentActivity";

    @InjectView(R.id.topbar)
    TopBarLayout topbar;
    @InjectView(R.id.btn_service)
    Button btnService;
    @InjectView(R.id.btn_buy)
    Button btnBuy;
    @InjectView(R.id.clinic_iv)
    ImageView clinicIv;
    @InjectView(R.id.tv_clinic_name)
    TextView tvClinicName;
    @InjectView(R.id.tv_clinic_address)
    TextView tvClinicAddress;
    @InjectView(R.id.ll_clinic)
    LinearLayout llClinic;
    @InjectView(R.id.rb_clinic)
    RatingBar rbClinic;
    @InjectView(R.id.tv_clinic_price)
    TextView tvClinicPrice;
    @InjectView(R.id.iv_logo)
    ImageView ivLogo;
    @InjectView(R.id.lv_clinic_product)
    MyListview lvClinicProduct;
    @InjectView(R.id.rb_overall_clinic)
    RatingBar rbOverallClinic;
    @InjectView(R.id.tv_evaluate)
    TextView tvEvaluate;
    @InjectView(R.id.lv_evaluate)
    MyListview lvEvaluate;
    @InjectView(R.id.iv_clinic_share)
    ImageView ivClinicShare;
    @InjectView(R.id.bottombar)
    RelativeLayout bottombar;
    @InjectView(R.id.tv_clinic_location)
    TextView tvClinicLocation;
    @InjectView(R.id.tv_cname)
    TextView tvCname;

    private String[] homeList = new String[]{"我的镶牙", "我要种牙", "其他项目", "找门诊",
            "找专家", "客服", "门诊寻求合作", "积分商城"};
    private int[] ivList = new int[]{R.mipmap.home_xtooth, R.mipmap.home_zytooth,
            R.mipmap.home_else, R.mipmap.home_recode, R.mipmap.home_specialist,
            R.mipmap.home_service, R.mipmap.home_collaborate, R.mipmap.home_store};
    private BaseAdapter mBaseAdapter;
    private ArrayList<PublicInfo> pList = new ArrayList<>();

    @Override
    protected void initView() {
        setContentView(R.layout.activity_clinic_content);
    }

    @Override
    protected void initData() {
        //设置诊所详情的部分参数
        setClinicValues();
        //真实数据产品与服务接口（暂时没有数据所以先隐藏吧）
//        GetProductToServe();
        //获取真实的底部评论数据(暂时没有数据)
//        GetCommentData();

        //测试数据产品与服务
        setPicAndTvValue();
        //测试数据底部的评论
        initTestData();
        lvEvaluate.setAdapter(new ListItemAdapter(this, itemEntities));
    }

    /**
     * 获取评论数据库
     */
    public void GetCommentData() {
        String commentUrl = ServiceUrl.Get_Comment;
        BaseNetEntity baseNetEntity = new BaseNetEntity();
        baseNetEntity.get(this, commentUrl, "正在获取评论数据", true, new OkHttpResponseCallback<HomeProductRespBean>(HomeProductRespBean.class) {
            @Override
            public void onSuccess(HomeProductRespBean bean) {
                Log.i("info", "数据" + bean.data.toString());
            }

            @Override
            public void onFailError(Exception e) {
                ToastUtils.showToastLong(ClinicContentActivity.this, "失败" + e);
            }
        });
    }

    /**
     * 获取产品和服务
     */
    public void GetProductToServe() {
        String productUrl = ServiceUrl.Get_Search_Product;
        BaseNetEntity baseNetEntity = new BaseNetEntity();
        baseNetEntity.get(this, productUrl, "正在获取产品数据", true, new OkHttpResponseCallback<HomeProductRespBean>(HomeProductRespBean.class) {
            @Override
            public void onSuccess(HomeProductRespBean bean) {
                Log.i("info", "数据" + bean.data.toString());
            }

            @Override
            public void onFailError(Exception e) {
                ToastUtils.showToastLong(ClinicContentActivity.this, "失败" + e);
            }
        });
    }

    /**
     * 设置诊所详情的部分参数
     *
     * @param
     * @return
     */
    public void setClinicValues() {
        //接收传递过来的数据
        Bundle bundle = getIntent().getExtras();   //得到传过来的bundle
        String mClinicName = bundle.getString(IntentParameter.ClinicName);//读出数据
        String mClinicPic = bundle.getString(IntentParameter.ClinicPic);
        String mClinicLoaction = bundle.getString(IntentParameter.ClinicLocation);
        String mClinicjf = bundle.getString(IntentParameter.Clinicjl);
        Log.i(Tag, "参数" + mClinicName + mClinicLoaction + mClinicPic + mClinicjf);
        //设置图片
        Glide.with(this).load(mClinicPic).error(R.mipmap.home_seach).into(clinicIv);
        //设置位置
        tvClinicLocation.setText(mClinicLoaction);
        //设置rb星
        rbClinic.setClickable(false);
//        if (!mClinicjf.equals("")) {
//            rbClinic.setStar(JavaUtils.convertToFloat(mClinicjf));
//        }
//        tvClinicPrice.setText(mClinicjf + "分");

        tvClinicName.setText(mClinicName);
        tvCname.setText(mClinicName);
        topbar.setTxvTitleName(mClinicName);
    }

    /**
     * Item数据实体集合
     */
    private ArrayList<ItemEntity> itemEntities;

    /**
     * 初始化数据
     */
    private void initTestData() {
        itemEntities = new ArrayList<ItemEntity>();
        // 3.3张图片
        ArrayList<String> urls_2 = new ArrayList<String>();
        urls_2.add("http://img.my.csdn.net/uploads/201410/19/1413698867_8323.jpg");
        urls_2.add("http://img.my.csdn.net/uploads/201410/19/1413698883_5877.jpg");
        urls_2.add("http://img.my.csdn.net/uploads/201410/19/1413698837_5654.jpg");
        ItemEntity entity3 = new ItemEntity(//
                "http://img.my.csdn.net/uploads/201410/19/1413698837_5654.jpg", "王五", "今天好大的太阳...", urls_2);
        itemEntities.add(entity3);
        // 4.6张图片
        ArrayList<String> urls_3 = new ArrayList<String>();
        urls_3.add("http://img.my.csdn.net/uploads/201410/19/1413698837_7507.jpg");
        urls_3.add("http://img.my.csdn.net/uploads/201410/19/1413698865_3560.jpg");
        urls_3.add("http://img.my.csdn.net/uploads/201410/19/1413698867_8323.jpg");
        urls_3.add("http://img.my.csdn.net/uploads/201410/19/1413698837_5654.jpg");
        urls_3.add("http://img.my.csdn.net/uploads/201410/19/1413698883_5877.jpg");
        urls_3.add("http://img.my.csdn.net/uploads/201410/19/1413698839_2302.jpg");
        ItemEntity entity4 = new ItemEntity(//
                "http://img.my.csdn.net/uploads/201410/19/1413698883_5877.jpg", "赵六", "今天下雨了...", urls_3);
        itemEntities.add(entity4);
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
        setInterfaceParams(pList);
    }

    public void setInterfaceParams(ArrayList<PublicInfo> mPersonList) {
        mBaseAdapter = new CommonAdapter<PublicInfo>(ClinicContentActivity.this, mPersonList,
                R.layout.clinic_proudct_item) {
            @Override
            public void convert(ViewHolder helper, PublicInfo item, int position) {
                // 名称
                helper.setText(R.id.tv_product_name, item.getName());

            }
        };
        lvClinicProduct.setAdapter(mBaseAdapter);
    }

    //分享点击事件
    @OnClick(R.id.iv_clinic_share)
    public void onClick() {
        ShareDialog shareDialog = new ShareDialog(this, new ShareDialog.shareListenter() {
            @Override
            public void shareCopy() {
                ToastUtils.showToastLong(ClinicContentActivity.this, "复制");
            }

            @Override
            public void shareWxFriend() {
                ToastUtils.showToastLong(ClinicContentActivity.this, "微信朋友");
            }

            @Override
            public void shareWxPyCircle() {
                ToastUtils.showToastLong(ClinicContentActivity.this, "微信朋友圈");
            }

            @Override
            public void sharQQFriend() {
                ToastUtils.showToastLong(ClinicContentActivity.this, "qq好友");
            }
        });
        shareDialog.show();
    }

}
