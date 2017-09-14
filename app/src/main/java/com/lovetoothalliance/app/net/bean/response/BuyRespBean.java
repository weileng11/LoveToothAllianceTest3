package com.lovetoothalliance.app.net.bean.response;

import com.google.gson.annotations.SerializedName;

/**
 * Author:lt
 * time:2017/8/10.
 * contact：weileng143@163.com
 *
 * @description
 */

public class BuyRespBean {

    /**
     * data : {"appid":"wxe5dfab23d10061bb","partnerid":"1388273402","prepayid":"wx201708101031599dacf8a96e0313456227","package":"Sign=WXPay","noncestr":"844bc9182338492cb6acb115cbe1ee24","timestamp":"1502331747","sign":"77CD3BA9BB98FD6330CC2AABBA18C0BB","code":0,"msg":"成功"}
     */

    public DataBean data;

    public static class DataBean {
        /**
         * appid : wxe5dfab23d10061bb
         * partnerid : 1388273402
         * prepayid : wx201708101031599dacf8a96e0313456227
         * package : Sign=WXPay
         * noncestr : 844bc9182338492cb6acb115cbe1ee24
         * timestamp : 1502331747
         * sign : 77CD3BA9BB98FD6330CC2AABBA18C0BB
         * code : 0
         * msg : 成功
         */

        public String appid;
        public String partnerid;
        public String prepayid;
        @SerializedName("package")
        public String packageX;
        public String noncestr;
        public String timestamp;
        public String sign;
        public int code;
        public String msg;

        @Override
        public String toString() {
            return "DataBean{" +
                    "appid='" + appid + '\'' +
                    ", partnerid='" + partnerid + '\'' +
                    ", prepayid='" + prepayid + '\'' +
                    ", packageX='" + packageX + '\'' +
                    ", noncestr='" + noncestr + '\'' +
                    ", timestamp='" + timestamp + '\'' +
                    ", sign='" + sign + '\'' +
                    ", code=" + code +
                    ", msg='" + msg + '\'' +
                    '}';
        }
    }

}
