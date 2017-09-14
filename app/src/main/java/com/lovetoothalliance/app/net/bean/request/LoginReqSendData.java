package com.lovetoothalliance.app.net.bean.request;

/**
 * Author:lt
 * time:2017/8/5.
 * contact：weileng143@163.com
 *
 * @description  登录请求bean
 */

public class LoginReqSendData {
    public String loginPhone;
    public String loginPwd;

    public String getLoginPhone() {
        return loginPhone;
    }

    public void setLoginPhone(String loginPhone) {
        this.loginPhone = loginPhone;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }
}
