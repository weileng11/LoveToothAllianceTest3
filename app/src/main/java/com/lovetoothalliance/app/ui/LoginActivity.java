package com.lovetoothalliance.app.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.lovetoothalliance.app.R;
import com.lovetoothalliance.app.constant.ServiceUrl;
import com.lovetoothalliance.app.net.BaseNetEntity;
import com.lovetoothalliance.app.net.OkHttpResponseCallback;
import com.lovetoothalliance.app.net.bean.request.LoginReqSendData;
import com.lovetoothalliance.app.net.bean.response.AllOrderRespBean;
import com.lovetoothalliance.app.net.bean.response.LoginRespBean;
import com.lovetoothalliance.app.widget.TopBarLayout;
import com.maiml.library.BaseItemLayout;
import com.utils.SharedPreferencesUtils;
import com.utils.ToastUtils;
import com.utils.view.EncodeUtil;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Author:lt
 * time:2017/7/21.
 * contact：weileng143@163.com
 *
 * @description 登录页
 */

public class LoginActivity extends BaseActivity {
    private static String TAG = "LoginActivity";
    @InjectView(R.id.topbar)
    TopBarLayout topbar;
    @InjectView(R.id.ed_phone)
    EditText edPhone;
    @InjectView(R.id.ed_pwd)
    EditText edPwd;
    @InjectView(R.id.btn_login)
    Button btnLogin;
    @InjectView(R.id.ll_content)
    LinearLayout llContent;
    @InjectView(R.id.ll_bottom)
    LinearLayout llBottom;
    @InjectView(R.id.btn_for_pwd)
    Button btnForPwd;
    @InjectView(R.id.btn_register)
    Button btnRegister;

    String u;
    @InjectView(R.id.layout)
    BaseItemLayout layout;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initData() {

        topbar.setTxvTitleName("登录");
        topbar.setTxvLeftShow();
//        initData1();
        //目前测试，设置默认的值 13828821554
        edPhone.setText("13828821554");
        edPwd.setText("123456");
//        Log.i("INFO", "url路径" + Integer.parseInt(u));
    }


    @OnClick(R.id.btn_login)
    public void onClick() {
        //开始登录
        Login();
//        getAllRecordData();
    }

    /**
     * 获取所有的记录诊断
     */
    public void getAllRecordData() {
        BaseNetEntity baseNetEntity = new BaseNetEntity();
        baseNetEntity.get(LoginActivity.this, ServiceUrl.Get_Diagnostic_Record + "&IsBack=0&oderstatus=5", "正在加载数据",
                true, new OkHttpResponseCallback<AllOrderRespBean>(AllOrderRespBean.class) {
                    @Override
                    public void onSuccess(AllOrderRespBean bean) {
                        ToastUtils.showToastLong(LoginActivity.this, "请求网络数据成功" + bean.data.size());
                    }

                    @Override
                    public void onFailError(Exception e) {

                    }
                });
    }


    /**
     * 登录
     */
    private boolean progressShow;
    ProgressDialog pd;

    public void Login() {
        LoginReqSendData sendData = new LoginReqSendData();
        sendData.setLoginPhone(edPhone.getText().toString());
        sendData.setLoginPwd(edPwd.getText().toString());

        String LoginUrl = ServiceUrl.Login_Tooth + sendData.getLoginPhone() +
                "&Pwd=" + EncodeUtil.encode_MD5(sendData.getLoginPwd()) + "&password" +
                "=" + EncodeUtil.encode_MD5(sendData.getLoginPwd());
        Log.i("INFO", "url路径" + LoginUrl);

        progressShow = true;
//        pd = new ProgressDialog(LoginActivity.this);
//        pd.setCanceledOnTouchOutside(false);
//        pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
//
//            @Override
//            public void onCancel(DialogInterface dialog) {
//                Log.d(TAG, "EMClient.getInstance().onCancel");
//                progressShow = false;
//            }
//        });
//        pd.setMessage(getString(R.string.Is_landing));
//        pd.show();

        BaseNetEntity baseNetEntity = new BaseNetEntity();
        baseNetEntity.get2(this, "正在登录", true, LoginUrl, new OkHttpResponseCallback<LoginRespBean>(LoginRespBean.class) {
            @Override
            public void onSuccess(LoginRespBean bean) {
                if (bean.data.Stater.equals("1")) {
                    Log.i(TAG, "login返回bean" + bean.data.User.Guid);
                    //登录成功保存到sp
                    SharedPreferencesUtils.saveString(LoginActivity.this, "memlogin", bean.data.User.MemLoginID);
                    //保存头像
                    SharedPreferencesUtils.saveString(LoginActivity.this, "headimg", bean.data.User.Photo);
                    //保存名字
                    SharedPreferencesUtils.saveString(LoginActivity.this, "myname", bean.data.User.RealName);
                    //保存手机号码
                    SharedPreferencesUtils.saveString(LoginActivity.this, "mobile", bean.data.User.Mobile);
                    skipActivity(LoginActivity.this, MainActivity.class);
//                    login(bean);
                } else {
                    ToastUtils.showToastLong(LoginActivity.this, "账号或者密码错误");
                }

            }

            @Override
            public void onFailError(Exception e) {
                ToastUtils.showToastLong(LoginActivity.this, "登录失败");
            }

        });
    }


    @OnClick({R.id.btn_for_pwd, R.id.btn_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_for_pwd:
                showActivity(LoginActivity.this, UpdatePwdActivity.class);
                break;
            case R.id.btn_register:
                showActivity(LoginActivity.this, RegisterActivity.class);
                break;
        }
    }

    /**
     * 环信登录login
     *
     * @param
     */
    public void login(LoginRespBean bean) {

        if (!EaseCommonUtils.isNetWorkConnected(this)) {
            Toast.makeText(this, R.string.network_isnot_available, Toast.LENGTH_SHORT).show();
            return;
        }
        //环信密码 如果登陆环信，如读的返回会员信息的Pwd属性直接md5( Pwd+"JinShang")作为密码登陆
//        String currentUsername = edPhone.getText().toString().trim();
        //用户名是member的guid属性去除“-”转成小写
        String currentUsername = bean.data.User.Guid.replace("-", "").toLowerCase();
        String currentPassword = EncodeUtil.encode_MD5(bean.data.User.Pwd+"JinShang");
        Log.i(TAG,"账户"+currentUsername);
        Log.i(TAG,"密码"+bean.data.User.Pwd);
        Log.i(TAG,"环信密码"+currentPassword);

        if (TextUtils.isEmpty(currentUsername)) {
            Toast.makeText(this, R.string.User_name_cannot_be_empty, Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(currentPassword)) {
            Toast.makeText(this, R.string.Password_cannot_be_empty, Toast.LENGTH_SHORT).show();
            return;
        }

        // After logout，the DemoDB may still be accessed due to async callback, so the DemoDB will be re-opened again.
        // close it before login to make sure DemoDB not overlap
//        DemoDBManager.getInstance().closeDB();

        // reset current user name before login
//        DemoHelper.getInstance().setCurrentUserName(currentUsername);

        final long start = System.currentTimeMillis();
        // call login method
        Log.d(TAG, "EMClient.getInstance().login");
        EMClient.getInstance().login(currentUsername, currentPassword, new EMCallBack() {

            @Override
            public void onSuccess() {
                Log.d(TAG, "login: onSuccess");


                // ** manually load all local groups and conversation
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();

                //更新当前用户的APNs的显示名称
//                boolean updatenick = EMClient.getInstance().pushManager().updatePushNickname(
//                        DemoApplication.currentUserNick.trim());
//                if (!updatenick) {
//                    Log.e("LoginActivity", "update current user nick fail");
//                }

                if (!LoginActivity.this.isFinishing() && pd.isShowing()) {
                    pd.dismiss();
                }
                // get user's info (this should be get from App's server or 3rd party service)
//                DemoHelper.getInstance().getUserProfileManager().asyncGetCurrentUserInfo();

                Intent intent = new Intent(LoginActivity.this,
                        MainActivity.class);
                startActivity(intent);

                finish();
            }

            @Override
            public void onProgress(int progress, String status) {
                Log.d(TAG, "login: onProgress");
            }

            @Override
            public void onError(final int code, final String message) {
                Log.d(TAG, "login: onError: " + code);
                if (!progressShow) {
                    return;
                }
                runOnUiThread(new Runnable() {
                    public void run() {
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(), getString(R.string.Login_failed) + message,
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }


//    private void initData1() {
//        List<String> valueList = new ArrayList<>();
//
//        valueList.add("相册");
//        valueList.add("收藏");
//        valueList.add("钱包");
//        valueList.add("卡包");
//        valueList.add("设置");
//        SurfaceView s = new SurfaceView(this);
//
//        List<Integer> resIdList = new ArrayList<>();
//
//        resIdList.add(R.mipmap.ic_launcher);
//        resIdList.add(R.mipmap.ic_launcher_round);
//        resIdList.add(R.mipmap.ip_ic_back);
//        resIdList.add(R.mipmap.index_home);
//        resIdList.add(R.mipmap.index_home);
//
//        List<String> rightTextList = new ArrayList<>();
//
//        rightTextList.add("test1");
//        rightTextList.add("test2");
//        rightTextList.add("test3");
//        rightTextList.add("test4");
//        rightTextList.add("test5");
//
//
//        ConfigAttrs attrs = new ConfigAttrs(); // 把全部参数的配置，委托给ConfigAttrs类处理。
//
//        //参数 使用链式方式配置
//        attrs.setValueList(valueList)  // 文字 list
//                .setResIdList(resIdList) // icon list
//                .setIconWidth(24)  //设置icon 的大小
//                .setIconHeight(24)
////                .setItemBgSelector(R.drawable.btn_list_item_black_bg2)
//                .setItemMode(Mode.TEXT)
//                .setRightText(rightTextList);
//
//
//        layout.setConfigAttrs(attrs)
//                .create(); //
//
//        layout.setOnBaseItemClick(new BaseItemLayout.OnBaseItemClick() {
//            @Override
//            public void onItemClick(int position) {
//
//            }
//        });
//
//        layout.setOnSwitchClickListener(new BaseItemLayout.OnSwitchClickListener() {
//            @Override
//            public void onClick(int position, boolean isCheck) {
//
//            }
//        });
//
//
//    }
}
