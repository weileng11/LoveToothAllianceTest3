package com.lovetoothalliance.app.fragment;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lovetoothalliance.app.R;
import com.lovetoothalliance.app.adapter.ViewHolder;
import com.lovetoothalliance.app.net.bean.response.AllOrderRespBean;

import java.util.List;

/**
 * Author:lt
 * time:2017/7/24.
 * contact：weileng143@163.com
 *
 * @description 待付款
 */

public class PaymentFragment extends BaseRecordFragment{

    private int mPosition;

    @Override
    protected int getRecordType() {
        return RECODERD_PAYFOR;
    }

    @Override
    protected int getPosition() {
        return mPosition;
    }

    @Override
    protected String getParamUrl() {
        return "&PaymentStatus=0";
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.i("OnResume","isEvaluate  pay"+isEvaluateOk);
        //表示已经评价
        if(isSpecialist){
            //先移除，在重新拉取数据
            getRecordData();
            isSpecialist=false;
        }
    }
    @Override
    protected void setAdapterParamValues(ViewHolder helper, final int position, final AllOrderRespBean.DataBean item, final List<AllOrderRespBean.DataBean> mOrderList) {
        if (item.PaymentStatus == 0) {
            helper.setText(R.id.tv_diagnose_state, "待付款");
        }
        helper.setText(R.id.tv_diagnose_time, item.CreateTime);
        helper.setText(R.id.tv_diagnose_name, item.Goods.get(0).Name);
        helper.setText(R.id.tv_diagnose_price, String.valueOf(item.Goods.get(0).BuyPrice));
        Glide.with(getActivity()).load(item.Goods.get(0).OriginalImge).into((ImageView) helper.getView(R.id.iv_diagonse));

        helper.setText(R.id.btn_diagnose_evaluate, "取消订单");
        helper.setText(R.id.btn_diagnose_buy, "去付款");

        //取消订单
        helper.getView(R.id.btn_diagnose_evaluate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPosition = position;
                cancelOrder(mOrderList.get(position).OrderNumber);
            }
        });
        //去付款
        helper.getView(R.id.btn_diagnose_buy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPosition = position;
                //获取到下单的值传递给立刻购买界面(去f)
                enterBuyPayment();
            }
        });
    }
};


