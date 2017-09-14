package com.lovetoothalliance.app.ui;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
 * time:2017/7/21.
 * contact：weileng143@163.com
 *
 * @description 绑定手机(完成，需要输入手机号和验证码)
 */

public class BindMobileOkActivty extends BaseActivity {
    @InjectView(R.id.topbar)
    TopBarLayout topbar;
    @InjectView(R.id.btn_get_yz)
    Button btnGetYz;
    @InjectView(R.id.ed_bind_photo)
    EditText edBindPhoto;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_bindmobile_ok);
    }

    @Override
    protected void initData() {
        topbar.setTxvTitleName("绑定手机");
        topbar.setRigthViewTypeMode(TopBarLayout.RightViewTypeMode.TEXT);
        topbar.setTxvRightShow();
        topbar.setTxvRightName("完成");
        topbar.setRightTxvOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                UpdatePhotoParams();
            }
        });
        //设置默认的手机号码
        if(SharedPreferencesUtils.getString(this,"mobile","")!=null){
            edBindPhoto.setText(SharedPreferencesUtils.getString(this,"mobile",""));
        }
    }
    /**
     * 修改登录密码
     */
    public void UpdatePhotoParams() {
        //开始提交订单
        BaseNetEntity baseNetEntity = new BaseNetEntity();
        //这里将对象转换成为字符串
        String mPersonInfoCommitUrl = ServiceUrl.Commit_PersonInfo +SharedPreferencesUtils.getString(BindMobileOkActivty.this, "memlogin", "")
                +"&Mobile="+edBindPhoto.getText().toString();
        baseNetEntity.get(BindMobileOkActivty.this, mPersonInfoCommitUrl, "正在加载数据", true,
                new OkHttpResponseCallback<RespnoseResultBean>(RespnoseResultBean.class) {
                    @Override
                    public void onSuccess(RespnoseResultBean bean) {
                        Log.i("INFO", "修改绑定手机");
                        //保存在sp当中
                        SharedPreferencesUtils.saveString(BindMobileOkActivty.this, "memlogin", edBindPhoto.getText().toString());
                        finish();
                    }

                    @Override
                    public void onFailError(Exception e) {

                    }
                });
    }
}
