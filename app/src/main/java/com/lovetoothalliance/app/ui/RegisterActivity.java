package com.lovetoothalliance.app.ui;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lovetoothalliance.app.R;
import com.lovetoothalliance.app.constant.ServiceUrl;
import com.lovetoothalliance.app.net.BaseNetEntity;
import com.lovetoothalliance.app.net.OkHttpResponseCallback;
import com.lovetoothalliance.app.widget.TopBarLayout;
import com.utils.ToastUtils;
import com.utils.view.EncodeUtil;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Author:lt
 * time:2017/7/21.
 * contact：weileng143@163.com
 *
 * @description 注册页
 */

public class RegisterActivity extends BaseActivity {

    @InjectView(R.id.topbar)
    TopBarLayout topbar;
    @InjectView(R.id.ed_phone)
    EditText edPhone;
    @InjectView(R.id.ed_yz)
    EditText edYz;
    @InjectView(R.id.btn_get_yz)
    Button btnGetYz;
    @InjectView(R.id.ed_pwd)
    EditText edPwd;
    @InjectView(R.id.ed_tz_phone)
    EditText edTzPhone;
    @InjectView(R.id.btn_finish_rsg)
    Button btnFinishRsg;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_register);
    }

    @Override
    protected void initData() {
        //设置参数
        edPhone.setText("13828821554");
        edPwd.setText("123456");
        edTzPhone.setText("13828821555");
    }


    /**
     * 注册
     */
    public void RegisterStart() {

        String registerUrl = ServiceUrl.Register_Tooth + edPhone.getText() + "&Mobile=" + edPhone.getText() + "&" +
                "Pwd=" + EncodeUtil.encode_MD5(edPwd.getText().toString()) + "" +
                "&password=" + EncodeUtil.encode_MD5(edPwd.getText().toString()) + "&CommendPeople=" + edTzPhone.getText().toString();
        Log.i("INFO", "url路径" + registerUrl);

        BaseNetEntity baseNetEntity = new BaseNetEntity();
        baseNetEntity.get2(this, "正在注册", true, registerUrl, new OkHttpResponseCallback(Object.class) {
            @Override
            public void onSuccess(Object bean) {
//                skipActivity(RegisterActivity.this, LoginActivity.class);
                finish();
                ToastUtils.showToastLong(RegisterActivity.this, "注册成功");
                //"data":{"Stater":"1","result":"添加成功"}}
            }

            @Override
            public void onFailError(Exception e) {
                ToastUtils.showToastLong(RegisterActivity.this, "注册失败");
            }

        });
    }

    @OnClick({R.id.btn_get_yz, R.id.btn_finish_rsg})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_get_yz:
                break;
            case R.id.btn_finish_rsg:
                RegisterStart();
                break;
        }
    }
}
