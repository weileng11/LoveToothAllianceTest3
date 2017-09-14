package com.lovetoothalliance.app.fragment;

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
 * @description 待使用
 */

public class StayPaymentFragment extends BaseRecordFragment{
    private int mPosition;
    @Override
    protected int getRecordType() {
        return RECODERD_USE;
    }

    @Override
    protected int getPosition() {
        return mPosition;
    }

    @Override
    protected String getParamUrl() {
        return "&oderstatus=1";
    }

    @Override
    protected void setAdapterParamValues(final ViewHolder helper, int position, AllOrderRespBean.DataBean
            item, final List<AllOrderRespBean.DataBean> mOrderList) {

        mPosition=position;
        if (item.PaymentStatus == 2) {
            helper.setText(R.id.tv_diagnose_state, "待使用");
        }
        helper.setText(R.id.tv_diagnose_time, item.CreateTime);
        helper.setText(R.id.tv_diagnose_name, item.Goods.get(0).Name);
        helper.setText(R.id.tv_diagnose_price, String.valueOf(item.Goods.get(0).BuyPrice));
        Glide.with(getActivity()).load(item.Goods.get(0).OriginalImge).into((ImageView) helper.getView(R.id.iv_diagonse));

        helper.getView(R.id.btn_diagnose_evaluate).setVisibility(View.VISIBLE);
        helper.setText(R.id.btn_diagnose_evaluate,"确定");
        helper.setText(R.id.btn_diagnose_buy, "导航");
        helper.getView(R.id.btn_diagnose_evaluate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commitOrderShowEvaluate(mOrderList.get(mPosition).OrderNumber);
            }
        });

    }


}
