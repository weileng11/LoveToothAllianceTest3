package com.lovetoothalliance.app.wxapi;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.lovetoothalliance.app.constant.Constants;
import com.lovetoothalliance.app.constant.ServiceUrl;
import com.lovetoothalliance.app.net.BaseNetEntity;
import com.lovetoothalliance.app.net.OkHttpResponseCallback;
import com.lovetoothalliance.app.net.bean.response.BuyRespBean;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.utils.SharedPreferencesUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Author:Bruce
 * Package:com.lovetoothalliance.app.wxapi
 * time:2017/9/8.
 * contact：weileng143@163.com
 *
 * @description
 */

public class WxPayUtils {
    private static String TAG="WxPayUtils";
    /**
     * 应用ID	appid	String(32)	是	wx8888888888888888	微信开放平台审核通过的应用APPID
     * 商户号	partnerid	String(32)	是	1900000109	微信支付分配的商户号
     * 预支付交易会话ID	prepayid	String(32)	是	WX1217752501201407033233368018	微信返回的支付交易会话ID
     * 扩展字段	package	String(128)	是	Sign=WXPay	暂填写固定值Sign=WXPay
     * 随机字符串	noncestr	String(32)	是	5K8264ILTKCH16CQ2502SI8ZNMTM67VS	随机字符串，不长于32位。推荐随机数生成算法
     * 时间戳	timestamp	String(10)	是	1412000000	时间戳，请见接口规则-参数规定
     * 签名	sign	String(32)	是	C380BEC2BFD727A4B6845133519F3AD6	签名，详见签名生成算法
     */
    static Context context;
    static String mOrderNumber;
    public  WxPayUtils(Context contex,String orderNumber){
       this.context=contex;
        this.mOrderNumber=orderNumber;
    }

    //    private IWXAPI api;
    public static void weChatPay(IWXAPI api) {
        api = WXAPIFactory.createWXAPI(context, Constants.WEIXIN_PAY_APPID);
        PayReq req = new PayReq();
        //req.appId = "wxf8b4f85f3a794e77";  // 测试用appId
        req.appId = Constants.WEIXIN_PAY_APPID;
        req.partnerId = Constants.WX_PARTNERID;
        req.prepayId = "WX121775250120140703323336801811";
        req.nonceStr = getRandomStringByLength(33);
        req.timeStamp = getStringDate();
        req.packageValue = "Sign=WXPay";
        req.sign = Constants.WX_SING;
        req.extData = "app data"; // optional
        Toast.makeText(context, "正常调起支付", Toast.LENGTH_SHORT).show();
        // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
        api.sendReq(req);
    }

    /**
     * 随机字符串
     *
     * @param length
     * @return
     */
    public static String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 获取现在时间
     *
     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }
    /**
     * 确定支付
     */
    public static void confirmPay() {
        String confirmUrl = ServiceUrl.Get_Ok_Pay + mOrderNumber + "&m=" +
                SharedPreferencesUtils.getString(context, "memlogin", null);
        Log.i("info", "comiturl" + confirmUrl);
        BaseNetEntity baseNetEntity = new BaseNetEntity();
        baseNetEntity.get((Activity) context, confirmUrl, "正在购买中", true, new OkHttpResponseCallback<BuyRespBean>(BuyRespBean.class) {
            @Override
            public void onSuccess(BuyRespBean bean) {
                Log.i(TAG, "支付成功" + bean.toString());
            }

            @Override
            public void onFailError(Exception e) {

            }
        });
    }
}
