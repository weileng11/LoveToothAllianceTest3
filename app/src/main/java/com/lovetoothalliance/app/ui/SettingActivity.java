package com.lovetoothalliance.app.ui;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hyphenate.chat.EMClient;
import com.lovetoothalliance.app.LoveToothApplication;
import com.lovetoothalliance.app.R;
import com.lovetoothalliance.app.dialog.ExitDialog;
import com.lovetoothalliance.app.widget.TopBarLayout;
import com.utils.DataCleanManager;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Author:lt
 * time:2017/7/25.
 * contact：weileng143@163.com
 *
 * @description 设置界面
 */

public class SettingActivity extends BaseActivity {
    @InjectView(R.id.topbar)
    TopBarLayout topbar;
    @InjectView(R.id.iv_set)
    ImageView ivSet;
    @InjectView(R.id.iv_update)
    ImageView ivUpdate;
    @InjectView(R.id.rl_clean_cache)
    RelativeLayout rlCleanCache;
    @InjectView(R.id.rl_update)
    RelativeLayout rlUpdate;
    @InjectView(R.id.btn_edit_login)
    Button btnEditLogin;
    @InjectView(R.id.tv_cache_size)
    TextView tvCacheSize;
    @InjectView(R.id.tv_version)
    TextView tvVersion;

    private Context mContext;
    private String cacheSize;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_setting);
    }

    @Override
    protected void initData() {
        mContext = this;
        topbar.setTxvTitleName("设置");
        //设置缓存的大小

        try {
            cacheSize = DataCleanManager.getTotalCacheSize(mContext);
        } catch (Exception e) {
                 e.printStackTrace();
        }

        tvCacheSize.setText(cacheSize);
        Log.i("INFO", "cacheSize大小" + cacheSize);
    }

    @OnClick({R.id.rl_clean_cache, R.id.rl_update})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_clean_cache:
                //清除缓存
                DataCleanManager.clearAllCache(mContext);
                //设置缓存的大小
                try {
                    cacheSize = DataCleanManager.getTotalCacheSize(mContext);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                tvCacheSize.setText(cacheSize);
                break;
            case R.id.rl_update:
                break;
        }
    }


    @OnClick(R.id.btn_edit_login)
    public void onClick() {
        final ExitDialog dialogExit = new ExitDialog(this, new ExitDialog.OnExitListenter() {
            @Override
            public void onExitOk() {
                //注销环信登录
                EMClient.getInstance().logout(true);
                //结束整个act
                LoveToothApplication.getInstance().finishAllActivity();
                finish();
                //跳转到登录界面
                showActivity(SettingActivity.this,LoginActivity.class);
            }

            @Override
            public void onExitCancel() {

            }
        });
        dialogExit.show();
    }

}
