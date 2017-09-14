package com.lovetoothalliance.app.ui;

import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lovetoothalliance.app.R;
import com.lovetoothalliance.app.adapter.CommonAdapter;
import com.lovetoothalliance.app.adapter.ViewHolder;
import com.lovetoothalliance.app.constant.ServiceUrl;
import com.lovetoothalliance.app.net.BaseNetEntity;
import com.lovetoothalliance.app.net.OkHttpResponseCallback;
import com.lovetoothalliance.app.net.bean.response.MyTeamRespBean;
import com.lovetoothalliance.app.widget.TopBarLayout;
import com.utils.SharedPreferencesUtils;
import com.utils.ToastUtils;
import com.utils.view.MyListview;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Author:lt
 * time:2017/7/24.
 * contact：weileng143@163.com
 *
 * @description 我的团队
 */

public class MyTeamActivity extends BaseActivity {
    private String Tag = "MyTeamActivity ";
    @InjectView(R.id.textView4)
    TextView textView4;
    @InjectView(R.id.tv_one_npop)
    TextView tvOneNpop;
    @InjectView(R.id.tv_two_npop)
    TextView tvTwoNpop;
    @InjectView(R.id.tv_three_npop)
    TextView tvThreeNpop;
    @InjectView(R.id.tv_one_sell)
    TextView tvOneSell;
    @InjectView(R.id.tv_two_sell)
    TextView tvTwoSell;
    @InjectView(R.id.tv_three_sell)
    TextView tvThreeSell;
    @InjectView(R.id.LinearLayout1)
    LinearLayout LinearLayout1;
    @InjectView(R.id.topbar)
    TopBarLayout topbar;
    @InjectView(R.id.team_lv)
    MyListview teamLv;

    private CommonAdapter<MyTeamRespBean.DataBean> mBaseAdapter;
    private List<MyTeamRespBean.DataBean> mTeamList = new ArrayList<>();

    @Override
    protected void initView() {
        setContentView(R.layout.activity_my_team);
    }

    @Override
    protected void initData() {
        topbar.setTxvTitleName("我的团队");
        setListViewData();
        getMyTeamData();
    }

    /**
     * 我的团队
     */
    public void getMyTeamData() {
        String teamUrl = ServiceUrl.Get_My_Team + "&MemLoginID=" +
                SharedPreferencesUtils.getString(MyTeamActivity.this, "memlogin", "");
        Log.i(Tag, "团队" + teamUrl);
        BaseNetEntity baseNetEntity = new BaseNetEntity();
        baseNetEntity.get(MyTeamActivity.this, teamUrl, "正在获取数据",
                true, new OkHttpResponseCallback<MyTeamRespBean>(MyTeamRespBean.class) {
                    @Override
                    public void onSuccess(MyTeamRespBean bean) {
                        Log.i(Tag, "取消订单");
                        //取消成功就移除当前的bean
                        ToastUtils.showToastLong(MyTeamActivity.this, "获取数据成功");
                        if (bean.data != null) {
                            setTeamParams(bean);
                        }
                    }

                    @Override
                    public void onFailError(Exception e) {

                    }
                });
    }

    /**
     * 设置团队的参数
     */
    public void setTeamParams(MyTeamRespBean bean) {
        tvOneNpop.setText(String.valueOf(bean.data.table.p1));
        tvTwoNpop.setText(String.valueOf(bean.data.table.p2));
        tvThreeNpop.setText(String.valueOf(bean.data.table.p3));
        tvOneSell.setText(String.valueOf(bean.data.table.sale1));
        tvTwoSell.setText(String.valueOf(bean.data.table.sale2));
        tvThreeSell.setText(String.valueOf(bean.data.table.sale3));
        //设置数据到集合
        mTeamList.add(bean.data);
        if (mBaseAdapter != null) {
            mBaseAdapter.setmDatas(mTeamList);
            mBaseAdapter.notifyDataSetChanged();
        }

    }


    /**
     * 初始化适配
     */
    public void setListViewData() {
        mBaseAdapter = new CommonAdapter<MyTeamRespBean.DataBean>(MyTeamActivity.this, mTeamList, R.layout.my_team_item) {
            @Override
            public void convert(ViewHolder helper, final MyTeamRespBean.DataBean item, final int position) {

            }

        };
        teamLv.setAdapter(mBaseAdapter);
    }
}
