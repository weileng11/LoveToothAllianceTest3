package com.lovetoothalliance.app.fragment;


import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lovetoothalliance.app.R;
import com.lovetoothalliance.app.adapter.ViewHolder;
import com.lovetoothalliance.app.net.bean.response.AllOrderRespBean;
import com.utils.ToastUtils;

import java.util.List;

/**
 * Author:lt
 * time:2017/7/21.
 * contact：weileng143@163.com
 *
 * @description 全部  说明：1.待付款 PaymentStatus=0 IsBack=0   IsBack=1表示退款退货订单
 * 2.待使用 oderstatus=1  PaymentStatus=2 表示已完成
 * 3.待评价 orderstatus=5 PaymentStatus=2 表示已完成    IsComment=1 表示已评论
 */

public class AllArrayFragment extends BaseRecordFragment {

    private int mPosition = 0;
    @Override
    protected int getRecordType() {
        return RECODERD_ALL;
    }

    @Override
    protected int getPosition() {
        return mPosition;
    }

    @Override
    protected String getParamUrl() {
        return null;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("OnResume","isEvaluate all"+isEvaluateOk);
        //表示已经评价
        if(isAllEvaluateOk){
            mycurrentPage=1;
            //先移除，在重新拉取数据
            getRecordData();
            isAllEvaluateOk=false;
        }
    }

    @Override
    protected void setAdapterParamValues(ViewHolder helper, final int position, final AllOrderRespBean.DataBean item, final List<AllOrderRespBean.DataBean> mOrderList) {
        if (item.PaymentStatus == 0) {
            helper.setText(R.id.tv_diagnose_state, "待付款");
            helper.getView(R.id.btn_diagnose_evaluate).setVisibility(View.VISIBLE);
            helper.getView(R.id.btn_diagnose_buy).setVisibility(View.VISIBLE);
            helper.setText(R.id.btn_diagnose_evaluate, "取消订单");
            helper.setText(R.id.btn_diagnose_buy, "去付款");
        } else if (item.PaymentStatus == 2) {
            //IsComment=0表示没有评价
            if (item.OderStatus == 5) {
                //已经评价了，不显示，移除数据，再次请求服务端一次(暂时设置为已评价，不显示按钮)
                if (mOrderList.get(position).Goods.get(0).IsComment == 1) {
//                            mOrderList.remove(mOrderList.get(position));
                    helper.setText(R.id.tv_diagnose_state, "已评价");
                    helper.getView(R.id.tv_diagnose_state).setVisibility(View.VISIBLE);
                    helper.getView(R.id.btn_diagnose_evaluate).setVisibility(View.GONE);
                    helper.getView(R.id.btn_diagnose_buy).setVisibility(View.GONE);
                } else {
                    helper.setText(R.id.tv_diagnose_state, "待评价");
                    helper.setText(R.id.btn_diagnose_buy, "再次购买");
                    helper.getView(R.id.btn_diagnose_buy).setVisibility(View.VISIBLE);
                    helper.setText(R.id.btn_diagnose_evaluate, "去评价");
                    helper.getView(R.id.btn_diagnose_evaluate).setVisibility(View.VISIBLE);
                }

            } else if (item.OderStatus == 1) {
                helper.setText(R.id.tv_diagnose_state, "待使用");
                helper.setText(R.id.btn_diagnose_buy, "导航");
                helper.getView(R.id.btn_diagnose_buy).setVisibility(View.VISIBLE);
                //待使用只显示导航
                helper.getView(R.id.btn_diagnose_evaluate).setVisibility(View.GONE);
            }
        }

        helper.setText(R.id.tv_diagnose_time, item.CreateTime);
        helper.setText(R.id.tv_diagnose_name, item.Goods.get(0).Name);
        helper.setText(R.id.tv_diagnose_price, String.valueOf(item.Goods.get(0).BuyPrice));
        Glide.with(getActivity()).load(item.Goods.get(0).OriginalImge).into((ImageView) helper.getView(R.id.iv_diagonse));

        //最左边
        helper.getView(R.id.btn_diagnose_evaluate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPosition = position;
                if (item.PaymentStatus == 0) {
                    //点击开始取消订单
                    if (mOrderList.get(position).OrderNumber.equals("") ||
                            mOrderList.get(position).OrderNumber.equals(null)) {
                        ToastUtils.showToastLong(getActivity(), "订单号不能为null");
                    } else {
                        cancelOrder(mOrderList.get(position).OrderNumber);
                    }
                } else if (item.PaymentStatus == 2) {
                    if (item.OderStatus == 5) {
                        //去评价
                        enterEvaluatePayment();
                    }
                }

            }
        });

        //最右边
        helper.getView(R.id.btn_diagnose_buy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPosition = position;
                if (item.PaymentStatus == 0) {
                    //去付款
                    enterBuyPayment();
                } else if (item.PaymentStatus == 2) {
                    if (item.OderStatus == 5) {
                        //再次购买
                        enterBuyPayment();
                    } else if (item.OderStatus == 1) {
                        //待使用（导航,暂时还没处理）
                    }
                }

            }
        });
    }

}
