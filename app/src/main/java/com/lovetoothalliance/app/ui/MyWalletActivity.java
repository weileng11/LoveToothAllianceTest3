package com.lovetoothalliance.app.ui;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lovetoothalliance.app.R;
import com.lovetoothalliance.app.widget.TopBarLayout;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Author:lt
 * time:2017/7/21.
 * contact：weileng143@163.com
 *
 * @description 我的钱包
 */

public class MyWalletActivity extends BaseActivity {
    @InjectView(R.id.topbar)
    TopBarLayout topbar;
    @InjectView(R.id.iv_wallet)
    ImageView ivWallet;
    @InjectView(R.id.tv_price)
    TextView tvPrice;
    @InjectView(R.id.btn_tx)
    Button btnTx;
    @InjectView(R.id.btn_deal_record)
    Button btnDealRecord;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_my_wallet);
    }

    @Override
    protected void initData() {
        topbar.setTxvTitleName("我的钱包");
    }


    @OnClick({R.id.btn_tx, R.id.btn_deal_record})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_tx:
                showActivity(this,WithdrawDepositActivity.class);
                break;
            case R.id.btn_deal_record:
                showActivity(this,TransactionRecordActivity.class);
                break;
        }
    }
}
