package com.lovetoothalliance.app.ui;

import android.view.View;
import android.widget.Button;

import com.lovetoothalliance.app.R;
import com.lovetoothalliance.app.widget.TopBarLayout;

import butterknife.InjectView;

/**
 * Author:lt
 * time:2017/7/21.
 * contact：weileng143@163.com
 *
 * @description 身份验证
 */

public class IdentityVerificationActivity extends BaseActivity {
    @InjectView(R.id.topbar)
    TopBarLayout topbar;
    @InjectView(R.id.btn_get_yz)
    Button btnGetYz;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_identityverification);
    }

    @Override
    protected void initData() {
        topbar.setTxvTitleName("身份验证");
        topbar.setRigthViewTypeMode(TopBarLayout.RightViewTypeMode.TEXT);
        topbar.setTxvRightShow();
        topbar.setTxvRightName("下一步");
        topbar.setRightTxvOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


}
