package com.lovetoothalliance.app.util;

import android.app.Activity;
import android.widget.Toast;

import com.lovetoothalliance.app.constant.Constants;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Author:lt
 * time:2017/8/11.
 * contact：weileng143@163.com
 *
 * @description 分享类
 */

public class ShareClassUtil {

    /**
     * 分享到微信朋友和朋友圈
     * @param activity
     * @param scene
     */
    public static void shareToWxFriendOrCircle(Activity activity,int scene){
        /**
         * 微信注册
         */
        IWXAPI api;
        String app_id = Constants.WEIXIN_APPID;
        api = WXAPIFactory.createWXAPI(activity, app_id, true);
        api.registerApp(app_id);

        // 检测是否安装微信
        if (!(api.isWXAppInstalled() && api.isWXAppSupportAPI())) {
            Toast.makeText(activity, "未安装微信客户端", Toast.LENGTH_SHORT).show();
        }

        String text = "最新新闻标题n查看更多最新的新闻资讯，请去应用市场下载应用(小黑板)软件哦";
        // String text = "fffffffffffffffffff";

        // 初始化一个WXTextObject对象
        WXTextObject textObj = new WXTextObject();
        textObj.text = text;

        // 用WXTextObject对象初始化一个WXMediaMessage对象
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = textObj;
        // 发送文本类型的消息时，title字段不起作用
        // msg.title = "Will be ignored";
        msg.description = text;

        // 构造一个Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("text"); // transaction字段用于唯一标识一个请求
        req.message = msg;
        req.scene = scene;

        // 调用api接口发送数据到微信
        api.sendReq(req);
    }

    private static String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis())
                : type + System.currentTimeMillis();
    }
}
