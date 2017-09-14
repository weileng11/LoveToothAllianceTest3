package com.lovetoothalliance.app.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lovetoothalliance.app.R;
import com.lovetoothalliance.app.adapter.ListItemAdapter;
import com.lovetoothalliance.app.bean.ItemEntity;
import com.lovetoothalliance.app.constant.ServiceUrl;
import com.lovetoothalliance.app.dialog.ShareDialog;
import com.lovetoothalliance.app.net.BaseNetEntity;
import com.lovetoothalliance.app.net.OkHttpResponseCallback;
import com.lovetoothalliance.app.net.bean.response.HomeProductRespBean;
import com.lovetoothalliance.app.util.ShareClassUtil;
import com.lovetoothalliance.app.widget.TopBarLayout;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.utils.DensityUtil;
import com.utils.ToastUtils;
import com.utils.view.MyListview;

import java.util.ArrayList;

import butterknife.InjectView;
import butterknife.OnClick;

import static android.R.attr.type;
import static com.lovetoothalliance.app.R.id.img_store_head;
import static com.lovetoothalliance.app.R.id.tv_wantTooth_name;
import static com.lovetoothalliance.app.R.id.tv_wantTooth_price;

/**
 * Author:lt
 * time:2017/7/26.
 * contact：weileng143@163.com
 *
 * @description 我要镶牙
 */

public class MyWantToothActivity extends BaseActivity {
    private static String TAG = "MyWantToothActivity";

    @InjectView(R.id.topbar)
    TopBarLayout topbar;
    @InjectView(tv_wantTooth_name)
    TextView tvWantToothName;
    @InjectView(tv_wantTooth_price)
    TextView tvWantToothPrice;
    @InjectView(R.id.tv_wantTooth_yuanprice)
    TextView tvWantToothYuanprice;
    @InjectView(R.id.iv_wanttooth_fx)
    ImageView ivWanttoothFx;
    @InjectView(R.id.lv_wanttooth)
    MyListview lvWanttooth;
    @InjectView(R.id.btn_service)
    Button btnService;
    @InjectView(R.id.btn_buy)
    Button btnBuy;
    @InjectView(R.id.bottombar)
    RelativeLayout bottombar;
    //    @InjectView(R.id.btn_product)
//    Button btnProduct;
//    @InjectView(R.id.btn_user_evaluate)
//    Button btnUserEvaluate;
    @InjectView(R.id.view_product)
    View viewProduct;
    @InjectView(R.id.btn_product)
    LinearLayout btnProduct;
    @InjectView(R.id.view_user_evaluate)
    View viewUserEvaluate;
    @InjectView(R.id.btn_user_evaluate)
    LinearLayout btnUserEvaluate;
    @InjectView(R.id.tv_product)
    TextView tvProduct;
    @InjectView(R.id.tv_user_evaluate)
    TextView tvUserEvaluate;
    @InjectView(img_store_head)
    ImageView imgStoreHead;
    @InjectView(R.id.webView)
    WebView webView;

    //1 显示商品详情状态，2显示用户评价状态
//    private int type=0;
//    是镶牙还是种牙界面跳转过来的
//    private String type = "";
    private String guid; //请求数据的id
    public HomeProductRespBean mHomeProductRespBean;

    String htmlss = "<p>\\t<img src=\"http://www.yidisha.com.cn/ImgUpload/20161121192620851.jpg\"/></p>" +
            "<p>\\t<img src=\"http://www.yidisha.com.cn/ImgUpload/20161121192621720.jpg\" /> " +
            "<img src=\"http://www.yidisha.com.cn/ImgUpload/20161122203148607.jpg\" /></p><p>\\t&nbsp;</p>";

    String htmlxx = "<html>\n" +
            " \n" +
            " <body>\n" +
            "  <img src=\"http://www.yidisha.com.cn/ImgUpload/20161121192620851.jpg \n" +
            "\" />\n" +
            "  <img src=\"http://www.yidisha.com.cn/ImgUpload/20161121192621720.jpg \n" +
            "\n" +
            "\" />\n" +
            "  <img src=\"http://www.yidisha.com.cn/ImgUpload/20161122203148607.jpg \n" +
            "\n" +
            "\" />\n" +
            " </body>\n" +
            "</html>";

    @Override
    protected void initView() {
        setContentView(R.layout.activity_mywant_tooth);
    }

    @Override
    protected void initData() {
        topbar.setTxvTitleName("我要镶牙");
        //默认显示商品详情
        tvProduct.setTextColor(getResources().getColor(R.color.red));
        btnProduct.setBackgroundColor(getResources().getColor(R.color.white));

        //获取type和guid的值
        Bundle bundle = getIntent().getExtras();
//        type = bundle.getString("type");
        guid = bundle.getString("guid");
        Log.i(TAG, "type" + type + "guid" + guid);

        //开始请求商品详情的数据
        getStoreData();

        //webview
        WebSettings webSetting = webView.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());

    }

    //测试各种地址
    public void getStoreData() {
        String guidUrl = ServiceUrl.Get_ToGuid_Product + guid;
        BaseNetEntity baseNetEntity = new BaseNetEntity();
        baseNetEntity.get(this, guidUrl, "正在获取数据", true, new OkHttpResponseCallback<HomeProductRespBean>(HomeProductRespBean.class) {
            @Override
            public void onSuccess(HomeProductRespBean bean) {
                mHomeProductRespBean = bean;
                Log.i("info", "数据" + bean.data.toString());
                //设置数据显示
                setTextData();
            }

            @Override
            public void onFailError(Exception e) {
                ToastUtils.showToastLong(MyWantToothActivity.this, "失败" + e);
            }
        });
    }

    /**
     * 设置参数
     */
    String linkCss = "<style type=\"text/css\"> img {" +
            "width:100%;" +
            "height:auto;" +
            "}" +
            "body {" +
            "margin-right:15px;" +
            "margin-left:15px;" +
            "margin-top:15px;" +
            "font-size:45px;" +
            "}" +
            "</style>";

    public void setTextData() {
        tvWantToothName.setText(mHomeProductRespBean.data.Name);
        //现价
        tvWantToothPrice.setText(String.valueOf(mHomeProductRespBean.data.ShopPrice));
        //原价
        tvWantToothYuanprice.setText("原价:" + String.valueOf(mHomeProductRespBean.data.MarketPrice));
        //设置头部的图片
        Glide.with(this).load(mHomeProductRespBean.data.ActiveImage).error(R.mipmap.iv_want_tooth).into(imgStoreHead);
//        webView.loadUrl(mHomeProductRespBean.data.Detail);
//        webView.loadDataWithBaseURL("about:blank", mHomeProductRespBean.data.Detail, "text/html", "utf-8", null);
//        StringBuilder sb=new StringBuilder();
//        sb.append("<html>");
//        sb.append("<body>");
//        sb.append(mHomeProductRespBean.data.Detail);
//        sb.append("</html>");
//        sb.append("</body>");
        String html = "<html><header>" + linkCss + "</header>" + mHomeProductRespBean.data.Detail + "</body></html>";
//        webView.getSettings().setDefaultTextEncodingName("UTF-8") ;
//        Log.i(TAG,sb.toString());
//        Log.i(TAG,"人生无常"+html.toString());
//        Log.i(TAG,"正常的"+htmlxx.toString());
//        Log.i(TAG,"正常22222"+htmlss.toString());
//        webView.loadData(sb.toString(), "text/html", "UTF-8") ;
        webView.loadDataWithBaseURL("about:blank", htmlxx.toString(), "text/html", "utf-8", null);
    }


    /**
     * Item数据实体集合
     */
    private ArrayList<ItemEntity> itemEntities;
    private ListItemAdapter mListItemAdapter;

    /**
     * 测试评价初始化数据
     */
    private void initTestData() {
        itemEntities = new ArrayList<ItemEntity>();
        // 3.3张图片
        ArrayList<String> urls_2 = new ArrayList<String>();
        urls_2.add("http://img.my.csdn.net/uploads/201410/19/1413698867_8323.jpg");
        urls_2.add("http://img.my.csdn.net/uploads/201410/19/1413698883_5877.jpg");
        urls_2.add("http://img.my.csdn.net/uploads/201410/19/1413698837_5654.jpg");
        ItemEntity entity3 = new ItemEntity(//
                "http://img.my.csdn.net/uploads/201410/19/1413698837_5654.jpg", "王五", "今天好大的太阳...", urls_2);
        itemEntities.add(entity3);
        // 4.6张图片
        ArrayList<String> urls_3 = new ArrayList<String>();
        urls_3.add("http://img.my.csdn.net/uploads/201410/19/1413698837_7507.jpg");
        urls_3.add("http://img.my.csdn.net/uploads/201410/19/1413698865_3560.jpg");
        urls_3.add("http://img.my.csdn.net/uploads/201410/19/1413698867_8323.jpg");
        urls_3.add("http://img.my.csdn.net/uploads/201410/19/1413698837_5654.jpg");
        urls_3.add("http://img.my.csdn.net/uploads/201410/19/1413698883_5877.jpg");
        urls_3.add("http://img.my.csdn.net/uploads/201410/19/1413698839_2302.jpg");
        ItemEntity entity4 = new ItemEntity(//
                "http://img.my.csdn.net/uploads/201410/19/1413698883_5877.jpg", "赵六", "今天下雨了...", urls_3);
        itemEntities.add(entity4);
    }

    @OnClick({R.id.btn_product, R.id.btn_user_evaluate, R.id.btn_service, R.id.btn_buy, R.id.iv_wanttooth_fx})
    public void onClick(View view) {
        switch (view.getId())

        {
            //商品详情
            case R.id.btn_product:
                //设置为默认颜色
                tvProduct.setTextColor(getResources().getColor(R.color.red));
                btnProduct.setBackgroundColor(getResources().getColor(R.color.white));
                viewProduct.setVisibility(View.VISIBLE);
                viewUserEvaluate.setVisibility(View.GONE);
                tvUserEvaluate.setTextColor(getResources().getColor(R.color.white));
                btnUserEvaluate.setBackgroundColor(getResources().getColor(R.color.darkgray));
                //
                imgStoreHead.setFocusable(true);
                webView.setVisibility(View.VISIBLE);
                webView.setFocusable(false);
                itemEntities.clear();
                mListItemAdapter.notifyDataSetChanged();
                break;
            //用户评价
            case R.id.btn_user_evaluate:
                //设置为默认颜色
                tvProduct.setTextColor(getResources().getColor(R.color.white));
                btnProduct.setBackgroundColor(getResources().getColor(R.color.darkgray));
                viewProduct.setVisibility(View.GONE);
                viewUserEvaluate.setVisibility(View.VISIBLE);
                tvUserEvaluate.setTextColor(getResources().getColor(R.color.red));
                btnUserEvaluate.setBackgroundColor(getResources().getColor(R.color.white));

                webView.setVisibility(View.GONE);
                //点击显示评论数据
                //测试数据底部的评论
                initTestData();
                mListItemAdapter = new ListItemAdapter(this, itemEntities);
                lvWanttooth.setAdapter(mListItemAdapter);
                break;
            case R.id.btn_service:
                break;
            case R.id.btn_buy:
//                showActivity(MyWantToothActivity.this, ImmediatelyBuyActivity.class);
                break;
            case R.id.iv_wanttooth_fx:
                ShareDialog shareDialog = new ShareDialog(MyWantToothActivity.this, new ShareDialog.shareListenter() {
                    @Override
                    public void shareCopy() {

                    }

                    @Override
                    public void shareWxFriend() {
                        //SendMessageToWX.Req.WXSceneSession 分享到朋友
                        ShareClassUtil.shareToWxFriendOrCircle(MyWantToothActivity.this, SendMessageToWX.Req.WXSceneSession);
                    }

                    @Override
                    public void shareWxPyCircle() {
                       // SendMessageToWX.Req.WXSceneTimeline 分享到朋友圈
                        ShareClassUtil.shareToWxFriendOrCircle(MyWantToothActivity.this, SendMessageToWX.Req.WXSceneTimeline);
                    }

                    @Override
                    public void sharQQFriend() {

                    }

                });
                shareDialog.show();
                setPlateDialogPlace(shareDialog, 0, DensityUtil.dip2px(MyWantToothActivity.this, 91));// 设置对话框位
                break;

        }

    }


    /**
     * 设置对话框的位置
     *
     * @param dg
     */
    private void setPlateDialogPlace(Dialog dg, int x, int y) {
        Window dialogWindow = dg.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;// FULL SCREEN
        // 显示的坐标
        // lp.y = y;
        dialogWindow.setAttributes(lp);
    }

}
