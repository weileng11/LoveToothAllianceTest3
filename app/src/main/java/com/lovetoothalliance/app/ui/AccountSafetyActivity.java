package com.lovetoothalliance.app.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lovetoothalliance.app.R;
import com.lovetoothalliance.app.widget.TopBarLayout;
import com.utils.SharedPreferencesUtils;

import butterknife.InjectView;
import butterknife.OnClick;

import static com.lovetoothalliance.app.R.id.tv_bind_phone;

/**
 * Author:lt
 * time:2017/7/25.
 * contact：weileng143@163.com
 *
 * @description 账号安全
 */

public class AccountSafetyActivity extends BaseActivity {
    @InjectView(R.id.topbar)
    TopBarLayout topbar;
    @InjectView(R.id.iv_safe)
    ImageView ivSafe;
    @InjectView(R.id.iv_phone)
    ImageView ivPhone;
    @InjectView(R.id.tv_binding)
    TextView tvBinding;
    @InjectView(tv_bind_phone)
    TextView tvBindPhone;
    @InjectView(R.id.rl_update_pwd)
    RelativeLayout rlUpdatePwd;
    @InjectView(R.id.rl_bind_phone)
    RelativeLayout rlBindPhone;
    @InjectView(R.id.rl_collect_address)
    RelativeLayout rlCollectAddress;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_account_safe);
    }

    @Override
    protected void initData() {
        topbar.setTxvTitleName("账号安全");


    }

    @Override
    protected void onResume() {
        super.onResume();
        if(SharedPreferencesUtils.getString(this,"mobile","")!=null){
            tvBindPhone.setText(SharedPreferencesUtils.getString(this,"mobile",""));
        }
    }

    @OnClick({R.id.rl_update_pwd, R.id.rl_bind_phone,R.id.rl_collect_address})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_update_pwd:
                showActivity(this, UpdatePwdActivity.class);
                break;
            case R.id.rl_bind_phone:
                showActivity(this, BindMobileActivty.class);
                break;
            case R.id.rl_collect_address:
                showActivity(this,CommodityAddressActivity.class);
                break;
        }
    }

}
