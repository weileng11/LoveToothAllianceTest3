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
 * @description 待评价
 */

public class StayEvaluateFragment extends BaseRecordFragment {
    private int mPosition=0;
    @Override
    protected int getRecordType() {
        return RECODERD_EVALUATE;
    }

    @Override
    protected int getPosition() {
        return mPosition;
    }

    @Override
    protected String getParamUrl() {
        return "&oderstatus=5";
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("OnResume","isEvaluate pl"+isEvaluateOk);
        //表示已经评价
        if(isEvaluateOk){
            mycurrentPage=1;
            if(mOrderList.size()>0){
                mOrderList.clear();
            }
            //先移除，在重新拉取数据
            getRecordData();
            isEvaluateOk=false;
        }
    }
    @Override
    protected void setAdapterParamValues(ViewHolder helper,final int position, final AllOrderRespBean.DataBean item, List<AllOrderRespBean.DataBean> mOrderList) {
        Log.i("INFO","评论数据" +mOrderList.size());
        for (int i=0;i<mOrderList.size();i++){
            Log.i("INFO","是否评论"+mOrderList.get(i).Goods.get(0).IsComment);
        }

        if (item.PaymentStatus == 2) {
//            helper.setText(R.id.tv_diagnose_state, "待评价");
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

            }
        }
        helper.setText(R.id.tv_diagnose_time, item.CreateTime);
        helper.setText(R.id.tv_diagnose_name, item.Goods.get(0).Name);
        helper.setText(R.id.tv_diagnose_price, String.valueOf(item.Goods.get(0).BuyPrice));
        Glide.with(getActivity()).load(item.Goods.get(0).OriginalImge).into((ImageView) helper.getView(R.id.iv_diagonse));

//        helper.setText(R.id.btn_diagnose_evaluate, "去评价");
//        helper.setText(R.id.btn_diagnose_buy, "再次购买");

        helper.getView(R.id.btn_diagnose_evaluate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPosition=position;
                //去评价
               enterEvaluatePayment();
            }
        });

    }
}

