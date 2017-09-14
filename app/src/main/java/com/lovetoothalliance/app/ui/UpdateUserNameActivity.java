package com.lovetoothalliance.app.ui;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lovetoothalliance.app.R;
import com.lovetoothalliance.app.constant.ServiceUrl;
import com.lovetoothalliance.app.net.BaseNetEntity;
import com.lovetoothalliance.app.net.OkHttpResponseCallback;
import com.lovetoothalliance.app.net.bean.RespnoseResultBean;
import com.lovetoothalliance.app.widget.TopBarLayout;
import com.utils.SharedPreferencesUtils;

import butterknife.InjectView;

/**
 * Author:lt
 * time:2017/7/25.
 * contact：weileng143@163.com
 *
 * @description 修改用户名
 */

public class UpdateUserNameActivity extends BaseActivity {
    @InjectView(R.id.topbar)
    TopBarLayout topbar;
    @InjectView(R.id.tv_user)
    TextView tvUser;
    @InjectView(R.id.ed_username)
    EditText edUsername;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_update_username);
    }

    @Override
    protected void initData() {
        topbar.setTxvTitleName("修改用户名");
        topbar.setRigthViewTypeMode(TopBarLayout.RightViewTypeMode.TEXT);
        topbar.setTxvRightShow();
        topbar.setTxvRightName("保存");
        topbar.setTxvRightOnClickListenter(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ToastUtils.showToastLong(UpdateUserNameActivity.this, "111" + 0);
                UpdatePersonPhoto();
            }
        });

        //设置名字
        edUsername.setText(SharedPreferencesUtils.getString(UpdateUserNameActivity.this,"myname",""));
        edUsername.setSelection(edUsername.length());
    }

    /**
     * 提交，更新个人信息
     */
    public void UpdatePersonPhoto() {
        final String userName=edUsername.getText().toString();
        //开始提交订单
        BaseNetEntity baseNetEntity = new BaseNetEntity();
        //这里将对象转换成为字符串
        String mPersonInfoCommitUrl = ServiceUrl.Commit_PersonInfo +
                SharedPreferencesUtils.getString(UpdateUserNameActivity.this, "memlogin", "") +
                "&RealName=" + userName;
        baseNetEntity.get(UpdateUserNameActivity.this, mPersonInfoCommitUrl, "正在加载数据", true,
                new OkHttpResponseCallback<RespnoseResultBean>(RespnoseResultBean.class) {
                    @Override
                    public void onSuccess(RespnoseResultBean bean) {
                        Log.i("INFO", "更新名字成功");
                        SharedPreferencesUtils.saveString(UpdateUserNameActivity.this,"myname",userName);
                        finish();
                    }

                    @Override
                    public void onFailError(Exception e) {

                    }
                });
    }
}
