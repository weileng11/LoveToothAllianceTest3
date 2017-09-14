package com.lovetoothalliance.app.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lovetoothalliance.app.R;
import com.lovetoothalliance.app.constant.Constants;
import com.lovetoothalliance.app.widget.TopBarLayout;
import com.lovetoothalliance.app.wxapi.WxPayUtils;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Author:lt
 * time:2017/7/28.
 * contact：weileng143@163.com
 *
 * @description 立刻购买
 */

public class ImmediatelyBuyActivity extends BaseActivity {
    private static String Tag = "ImmediatelyBuyActivity";
    @InjectView(R.id.topbar)
    TopBarLayout topbar;
    @InjectView(R.id.outpatient_tp_iv)
    ImageView outpatientTpIv;
    @InjectView(R.id.ck_alipay)
    CheckBox ckAlipay;
    @InjectView(R.id.ck_wx)
    CheckBox ckWx;
    @InjectView(R.id.btn_buy_ok)
    Button btnBuyOk;
    @InjectView(R.id.ck_agree)
    CheckBox ckAgree;
    @InjectView(R.id.tv_buy_name)
    TextView tvBuyName;
    @InjectView(R.id.tv_buy_price)
    TextView tvBuyPrice;
    @InjectView(R.id.tv_store_name)
    TextView tvStoreName;

    private static String orderNumber;
    private Double mPrice;
    private IWXAPI api;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_immediately_buy);
    }

    @Override
    protected void initData() {
        api = WXAPIFactory.createWXAPI(this, Constants.WEIXIN_PAY_APPID);
        topbar.setTxvTitleName("收银台");
        //获取专家下单界面传递过来的数据
        Bundle bundle = getIntent().getExtras();
        orderNumber = bundle.getString("ordernumber"); //订单号(调用接口的时候使用)
        mPrice = bundle.getDouble("shouldpayprice"); //价格
        String orderName = bundle.getString("ordername");
        String mOrderPic = bundle.getString("orderpic");
        String mOrderShopName = bundle.getString("orderShopname");

        //设置参数
        Glide.with(ImmediatelyBuyActivity.this).load(mOrderPic).into(outpatientTpIv);
        tvBuyName.setText(orderName);
        tvBuyPrice.setText("￥" + String.valueOf(mPrice));
        tvStoreName.setText(mOrderShopName);
        //支付宝支付不需要处理，后台直接调用
        //微信支付(暂时还没处理这块的需求)
//        confirmPay();
    }



    @OnClick({R.id.ck_alipay, R.id.ck_wx, R.id.btn_buy_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ck_alipay:
                break;
            case R.id.ck_wx:
                break;
            case R.id.btn_buy_ok:
                WxPayUtils wxPayUtils=new WxPayUtils(this,orderNumber);
                wxPayUtils.weChatPay(api);
                break;
        }
    }
}
