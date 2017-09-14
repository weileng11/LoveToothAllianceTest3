package com.lovetoothalliance.app.ui;

import android.util.Log;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lovetoothalliance.app.R;
import com.lovetoothalliance.app.adapter.CommonAdapter;
import com.lovetoothalliance.app.adapter.ViewHolder;
import com.lovetoothalliance.app.constant.ServiceUrl;
import com.lovetoothalliance.app.dialog.SignDialog;
import com.lovetoothalliance.app.net.BaseNetEntity;
import com.lovetoothalliance.app.net.OkHttpResponseCallback;
import com.lovetoothalliance.app.net.bean.response.IntegralRespBean;
import com.lovetoothalliance.app.widget.TopBarLayout;
import com.utils.view.CircleImageView;
import com.utils.view.MyGridView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Author:lt
 * time:2017/7/27.
 * contact：weileng143@163.com
 *
 * @description 积分商城
 */

public class IntegralStoreActivity extends BaseActivity {
    private static String Tag = "IntegralStoreActivity";
    @InjectView(R.id.topbar)
    TopBarLayout topbar;
    @InjectView(R.id.img_is_head)
    CircleImageView imgIsHead;
    @InjectView(R.id.btn_sign)
    Button btnSign;
    @InjectView(R.id.img_jf_gift)
    ImageView imgJfGift;
    @InjectView(R.id.gv_integral_store)
    MyGridView gvIntegralStore;

    private BaseAdapter mBaseAdapter;
    private List<IntegralRespBean.DataBean> mIDataList = new ArrayList<>();
    @Override
    protected void initView() {
        setContentView(R.layout.activity_integral_store);
    }

    @Override
    protected void initData() {
        topbar.setTxvTitleName("积分商城");
        //获取积分商城的数据
        getIntegralData();
    }

    /**
     * 设置积分商城请求数据
     */
    public void getIntegralData() {
        BaseNetEntity baseNetEntity = new BaseNetEntity();
        baseNetEntity.get(IntegralStoreActivity.this, ServiceUrl.Get_Integral_Shop, "正在获取积分数据", true, new OkHttpResponseCallback<IntegralRespBean>(IntegralRespBean.class) {
            @Override
            public void onSuccess(IntegralRespBean bean) {
                Log.i("INFO", "bean信息" + bean.data.toString());
                setIntegralList(bean);
            }

            @Override
            public void onFailError(Exception e) {
            }
        });
    }

    /**
     * 设置数据
     */
    public void setIntegralList(IntegralRespBean bean) {
        mIDataList.addAll(bean.data);
        //设置到适配器
        setIntegralListToAdapter();
    }

    /**
     * 适配器
     *
     * @return
     */
    public void setIntegralListToAdapter() {
        mBaseAdapter = new CommonAdapter<IntegralRespBean.DataBean>(IntegralStoreActivity.this, mIDataList,
                R.layout.integral_store_item) {
            @Override
            public void convert(ViewHolder helper, IntegralRespBean.DataBean item, int position) {
                //图标  item.OriginalImge
                Glide.with(IntegralStoreActivity.this).load(item.OriginalImge).error(R.mipmap.integral_img).into((ImageView) helper.getView(R.id.iv_integral_item));
                // 名称
                helper.setText(R.id.tv_jf_name, item.Name);
                //积分
                helper.setText(R.id.tv_integral_value,String.valueOf(item.ExchangeScore));

            }
        };
        gvIntegralStore.setAdapter(mBaseAdapter);
    }

    //签到弹出对话框
    private SignDialog signDialog;

    @OnClick(R.id.btn_sign)
    public void onClick() {
        signDialog = new SignDialog(this, new SignDialog.signListenter() {
            @Override
            public void timerColseSign() {
                startTimeThree();
            }
        }, 2);
        signDialog.show();

    }

    public static Timer timer;
    private int period = 1000;
    //    private int mTime=0;
    private int i = 0;
    private int j = 2;

    public void startTimeThree() {
        Log.e("lzp", "timer excute" + i);
        // 初始化定时器
        timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                Log.e("lzp", "timer " + i);
                i++;
                if (i == j) {
                    stopTimer();
                    signDialog.dismiss();
                    i = 0;
                }

            }
        }, 1000, period);

    }

    // 停止定时器
    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
            // 一定设置为null，否则定时器不会被回收
            timer = null;
        }
    }
}
