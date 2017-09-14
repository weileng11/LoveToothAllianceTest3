package com.lovetoothalliance.app.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lovetoothalliance.app.R;
import com.lovetoothalliance.app.net.bean.response.SpecialistRespBean;
import com.lovetoothalliance.app.widget.AddAndSubView;

/**
 * Author:lt
 * time:2017/7/26.
 * contact：weileng143@163.com
 *
 * @description  下单
 */

public class PlaceOrderDialog extends Dialog {


    private Button mBtnCommit;
    private Context context;
    private PlaceOrderListener listener;
    private static int num;
    private TextView tvProductName;
    SpecialistRespBean.DataBean mSDataBean;

    public interface PlaceOrderListener {
        void onOrderCommit();// 提交

        void onGetNums(int num);// 数字值

    }

    public PlaceOrderDialog(Context context, PlaceOrderListener listener,SpecialistRespBean.DataBean DataBean) {
        super(context, R.style.cx_ContentOverlay);
        this.context = context;
        this.listener = listener;
        this.mSDataBean=DataBean;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog_place_order);
        mBtnCommit = (Button) findViewById(R.id.btn_xd_commit);
        tvProductName=(TextView)findViewById(R.id.tv_product_name);
       tvProductName.setText(mSDataBean.name+" "+"¥"+mSDataBean.shopprice);
        LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.linearLayout2);
        final AddAndSubView addAndSubView2 = new AddAndSubView(context, 1);
        linearLayout2.addView(addAndSubView2);

        mBtnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取数字的参数
                num = addAndSubView2.getNum();
                listener.onGetNums(num);
                listener.onOrderCommit();
                dismiss();
            }
        });

    }

}

