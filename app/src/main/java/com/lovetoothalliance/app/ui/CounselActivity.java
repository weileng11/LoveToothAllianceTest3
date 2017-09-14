package com.lovetoothalliance.app.ui;

import com.lovetoothalliance.app.R;
import com.lovetoothalliance.app.constant.ServiceUrl;
import com.lovetoothalliance.app.net.BaseNetEntity;
import com.lovetoothalliance.app.net.OkHttpResponseCallback;
import com.lovetoothalliance.app.net.bean.response.NearbyRespBean;
import com.lovetoothalliance.app.widget.TopBarLayout;
import com.utils.ToastUtils;
import com.utils.view.CircleImageView;

import butterknife.InjectView;

/**
 * Author:lt
 * time:2017/7/21.
 * contact：weileng143@163.com
 *
 * @description 法律顾问
 */

public class CounselActivity extends BaseActivity {
    @InjectView(R.id.topbar)
    TopBarLayout topbar;
    @InjectView(R.id.law_head)
    CircleImageView lawHead;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_counsel);
    }

    @Override
    protected void initData() {
        topbar.setTxvTitleName("法律顾问");
        //获取法律顾问数据
        getCounselData();
    }

    /**
     * 法律顾问
     */
    public void getCounselData() {
        BaseNetEntity baseNetEntity = new BaseNetEntity();
        baseNetEntity.get(CounselActivity.this, ServiceUrl.Counsel_Counselor, "获取法律顾问数据", true, new OkHttpResponseCallback<NearbyRespBean>(NearbyRespBean.class) {
            @Override
            public void onSuccess(NearbyRespBean bean) {
                ToastUtils.showToastLong(CounselActivity.this, "请求网络数据成功");
            }

            @Override
            public void onFailError(Exception e) {
                ToastUtils.showToastLong(CounselActivity.this, "失败" + e);
            }
        });
    }

}
