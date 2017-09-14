package com.lovetoothalliance.app.ui;

import android.text.TextUtils;
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
import com.utils.ToastUtils;
import com.utils.view.EncodeUtil;

import butterknife.InjectView;
import butterknife.OnClick;

import static com.lovetoothalliance.app.R.id.ed_user_phone;

/**
 * Author:lt
 * time:2017/7/25.
 * contact：weileng143@163.com
 *
 * @description 修改登录密码
 */

public class UpdatePwdActivity extends BaseActivity {
    @InjectView(R.id.topbar)
    TopBarLayout topbar;
    @InjectView(ed_user_phone)
    EditText edUserPhone;
    @InjectView(R.id.ed_yz)
    EditText edYz;
    @InjectView(R.id.btn_get_yz)
    Button btnGetYz;
    @InjectView(R.id.ed_new_pwd)
    EditText edNewPwd;
    @InjectView(R.id.btn_ok_update)
    Button btnOkUpdate;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_update_pwd);
    }

    @Override
    protected void initData() {
        topbar.setTxvTitleName("修改登录密码");
        topbar.setRigthViewTypeMode(TopBarLayout.RightViewTypeMode.TEXT);
//        topbar.setTxvRightShow();
//        topbar.setTxvRightName("保存");
//        topbar.setTxvRightOnClickListenter(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

        if(SharedPreferencesUtils.getString(this,"memlogin","")!=null||!SharedPreferencesUtils.getString(this,"memlogin","").equals("")){
            edUserPhone.setText(SharedPreferencesUtils.getString(this,"memlogin",""));
        }
    }


    @OnClick({R.id.btn_get_yz, R.id.btn_ok_update})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_get_yz:
                break;
            case R.id.btn_ok_update:
                checkUserPhone();
                break;
        }
    }

    /**
     *
     * @param pwd
     */
    public void UpdatePersonPwd(String pwd) {
        //开始提交订单
        BaseNetEntity baseNetEntity = new BaseNetEntity();
        //这里将对象转换成为字符串
        String mPersonInfoCommitUrl = ServiceUrl.Commit_PersonInfo +
                SharedPreferencesUtils.getString(UpdatePwdActivity.this, "memlogin", "") +
                "&pwd=" + EncodeUtil.encode_MD5(pwd);
        baseNetEntity.get(UpdatePwdActivity.this, mPersonInfoCommitUrl, "正在加载数据", true,
                new OkHttpResponseCallback<RespnoseResultBean>(RespnoseResultBean.class) {
                    @Override
                    public void onSuccess(RespnoseResultBean bean) {
                        Log.i("INFO", "修改密码成功");
                        finish();

                    }

                    @Override
                    public void onFailError(Exception e) {

                    }
                });
    }

    /**
     * 检查手机号码是否是自己的
     * @return
     */
    public void checkUserPhone(){
        final String phone = edUserPhone.getText().toString().trim();
        if(!SharedPreferencesUtils.getString(this,"memlogin","").equals(phone)){
            ToastUtils.showToastLong(UpdatePwdActivity.this,"请输入你自己的手机号码");
            return ;
        }

        if (TextUtils.isEmpty(phone)) {
            ToastUtils.showToastLong(this, "手机号码不能为空");
            return;
        }

        final String pwd = edNewPwd.getText().toString().trim();
        if ("".equals(pwd)) {
            ToastUtils.showToastLong(this, "密码不能为空");
            return;
        }
        //开始执行更新密码
        UpdatePersonPwd(pwd);
    }


}
