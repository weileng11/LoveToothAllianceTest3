package com.lovetoothalliance.app.ui;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.lovetoothalliance.app.R;
import com.lovetoothalliance.app.widget.TopBarLayout;

import butterknife.InjectView;

/**
 * Author:lt
 * time:2017/7/27.
 * contact：weileng143@163.com
 *
 * @description 健康知识item的详细内容(带webview)
 */

public class HealthKnowLedgeDetailActivity extends BaseActivity {
    @InjectView(R.id.topbar)
    TopBarLayout topbar;
    @InjectView(R.id.webview)
    WebView webView;
    private String title;
    private String Content;

    private String test="<html>\n" +
            " \n" +
            " <body>\n" +
            "&lt;p&gt;&lt;img src=&quot;http://aclmmanage.jshec.cn/ImgUpload/kindeditor/image/" +
            "20161230/20161230144135_7544.jpg&quot;/&gt;&lt;/p&gt;" +
            " </body>\n" +
            "</html>";
    @Override
    protected void initView() {
        setContentView(R.layout.activity_hk_detail);
    }

    @Override
    protected void initData() {
        Bundle bundle=getIntent().getExtras();
        title=bundle.getString("detail_name");
        Content=bundle.getString("content");
        topbar.setTxvTitleName(title);
        Log.i("INFO","健康详情"+Content.toString());
        //webview
        WebSettings webSetting = webView.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());

//        String call = "javascript:alertMessage(\"" + Content + "\")";
//        webView.loadUrl(call);
        webView.loadDataWithBaseURL("about:blank", test, "text/html", "utf-8", null);
//        webView.loadData(Content.toString(), "text/html", "UTF-8") ;
    }


}
