package com.lovetoothalliance.app.ui;

import android.os.Bundle;
import android.widget.Button;

import com.lovetoothalliance.app.R;
import com.lovetoothalliance.app.widget.TopBarLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Author:lt
 * time:2017/7/26.
 * contact：weileng143@163.com
 *
 * @description 提现
 */

public class WithdrawDepositActivity extends BaseActivity {
    @InjectView(R.id.topbar)
    TopBarLayout topbar;
    @InjectView(R.id.btn_withdraw_deposit)
    Button btnWithdrawDeposit;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_withdraw_deposit);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }
}
