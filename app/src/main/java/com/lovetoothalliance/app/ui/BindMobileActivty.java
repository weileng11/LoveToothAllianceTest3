package com.lovetoothalliance.app.ui;

import android.view.View;
import android.widget.TextView;

import com.lovetoothalliance.app.R;
import com.lovetoothalliance.app.widget.TopBarLayout;
import com.utils.SharedPreferencesUtils;

import butterknife.InjectView;

/**
 * Author:lt
 * time:2017/7/21.
 * contact：weileng143@163.com
 *
 * @description 绑定手机 (只是做一个绑定显示提示)
 */

public class BindMobileActivty extends BaseActivity {
    private static  String TAG="BindMobileActivity";
    @InjectView(R.id.topbar)
    TopBarLayout topbar;
    @InjectView(R.id.tv_bind_phone)
    TextView tvBindPhone;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_bindmobile);
    }

    @Override
    protected void initData() {
        topbar.setTxvTitleName("绑定手机");
        topbar.setRigthViewTypeMode(TopBarLayout.RightViewTypeMode.TEXT);
        topbar.setTxvRightShow();
        topbar.setTxvRightName("更换");
        topbar.setRightTxvOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              showActivity(BindMobileActivty.this,BindMobileOkActivty.class);
            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        if(SharedPreferencesUtils.getString(this,"mobile","")!=null){
            tvBindPhone.setText(SharedPreferencesUtils.getString(this,"mobile",""));
        }
    }
}
