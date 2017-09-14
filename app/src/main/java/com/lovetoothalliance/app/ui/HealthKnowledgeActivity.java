package com.lovetoothalliance.app.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.lovetoothalliance.app.R;
import com.lovetoothalliance.app.adapter.CommonAdapter;
import com.lovetoothalliance.app.adapter.ViewHolder;
import com.lovetoothalliance.app.constant.ServiceUrl;
import com.lovetoothalliance.app.net.BaseNetEntity;
import com.lovetoothalliance.app.net.OkHttpResponseCallback;
import com.lovetoothalliance.app.net.bean.response.HealthKnowledgeRespBean;
import com.lovetoothalliance.app.widget.PullToRefreshView;
import com.lovetoothalliance.app.widget.TopBarLayout;
import com.utils.view.MyListview;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.InjectView;

/**
 * Author:lt
 * time:2017/7/27.
 * contact：weileng143@163.com
 *
 * @description 健康知识
 */

public class HealthKnowledgeActivity extends BaseActivity implements PullToRefreshView.OnHeaderRefreshListener,
        PullToRefreshView.OnFooterRefreshListener{

    private static String Tag = "HealthKnowledgeActivity";
    @InjectView(R.id.topbar)
    TopBarLayout topbar;
    @InjectView(R.id.lv_health)
    MyListview lvHealth;
    @InjectView(R.id.main_pull_refresh_view)
    PullToRefreshView  mPullToRefreshView;


    private CommonAdapter<HealthKnowledgeRespBean.DataBean> mBaseAdapter;
    //附近的店的bean
    private HealthKnowledgeRespBean mHealthKnowledgeRespBean;
    //附近的店的集合
    private List<HealthKnowledgeRespBean.DataBean> mHealthList = new ArrayList<>();

    @Override
    protected void initView() {
        setContentView(R.layout.activity_health_knowledge);
    }

    @Override
    protected void initData() {
        //刷新
        mPullToRefreshView.setOnHeaderRefreshListener(HealthKnowledgeActivity.this);
        mPullToRefreshView.setOnFooterRefreshListener(HealthKnowledgeActivity.this);
        mPullToRefreshView.setLastUpdated(new Date().toLocaleString());
        topbar.setTxvTitleName("健康知识");
        //获取健康知识数据
        getHealthKnowLedgeData();
    }

    /**
     * 获取健康知识数据
     */
    public void getHealthKnowLedgeData() {
        BaseNetEntity baseNetEntity = new BaseNetEntity();
        String healthUrl=ServiceUrl.Get_Health_Knowledge+"&pageNo="+mycurrentPage+"&pageSize="+mPageSize;
        Log.i(Tag, "健康知识" + ServiceUrl.Get_Health_Knowledge);
        baseNetEntity.get(HealthKnowledgeActivity.this, healthUrl, "正在加载数据..",
                true, new OkHttpResponseCallback<HealthKnowledgeRespBean>(HealthKnowledgeRespBean.class) {
                    @Override
                    public void onSuccess(HealthKnowledgeRespBean bean) {
                        //在调用一次
                        setComplete();
                        if (bean.data.size() > 0) {
                            //设置健康知识数据
                            if(mBaseAdapter!=null){
                                mBaseAdapter.setmDatas(bean.data);
                                mBaseAdapter.notifyDataSetChanged();
                            }else{
                                setHealthList(bean);
                            }
                        } else {
                            Log.i(Tag, "没有数据");
                        }
                    }

                    @Override
                    public void onFailError(Exception e) {

                    }
                });
    }

    /**
     * 设置健康知识的list
     *
     * @param
     */
    public void setHealthList(HealthKnowledgeRespBean mHealthKnowledgeRespBean) {
        Log.i("INFO", "bean信息" + mHealthKnowledgeRespBean.data.get(0).Title);
        mHealthList.addAll(mHealthKnowledgeRespBean.data);
        //设置到适配
        setHealthKnowledgeToAdapter();
    }

    public void setHealthKnowledgeToAdapter() {
        mBaseAdapter = new CommonAdapter<HealthKnowledgeRespBean.DataBean>(HealthKnowledgeActivity.this, mHealthList,
                R.layout.health_knowledge_item) {
            @Override
            public void convert(ViewHolder helper, HealthKnowledgeRespBean.DataBean item, int position) {
                // 名称
                helper.setText(R.id.tv_health, item.Title);
                //好评
                helper.setText(R.id.tv_good, item.Content);

            }
        };
        lvHealth.setAdapter(mBaseAdapter);
        lvHealth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Bundle bundle=new Bundle();
                bundle.putString("content",mHealthList.get(position).Content);
                bundle.putString("detail_name",mHealthList.get(position).Title);
                showActivity(HealthKnowledgeActivity.this, HealthKnowLedgeDetailActivity.class,bundle);
            }
        });
    }

    private int mycurrentPage = 1; //当前的页数
    private int mPageSize = 3; //请求几条数据
    private int mRecordCount = 3; //默认和请求多少一样
    private boolean isFootRefresh = false;// 是否是上下拉刷新（获取数据分进入界面时自动获取数据，和下下拉获取数据两种情况）
//    private int mPosition;
    private void setComplete() {
        if (isFootRefresh) {
            mPullToRefreshView.onFooterRefreshComplete();
            isFootRefresh = false;
        }
        mPullToRefreshView.onHeaderRefreshComplete("更新于:"
                + new Date().toLocaleString());
    }
    @Override
    public void onFooterRefresh(PullToRefreshView view) {
        Log.i(Tag, "onFooterRefresh" + mRecordCount);
        if (mRecordCount == mPageSize) {
            mycurrentPage++;
            isFootRefresh = true;
            getHealthKnowLedgeData();
        } else {
            Toast.makeText(HealthKnowledgeActivity.this, "已经没有更多内容了", Toast.LENGTH_SHORT).show();
            isFootRefresh = true;
            setComplete();
        }
    }

    @Override
    public void onHeaderRefresh(PullToRefreshView view) {
        mPullToRefreshView.postDelayed(new Runnable() {

            @Override
            public void run() {
                mPullToRefreshView.onHeaderRefreshComplete("更新于:"
                        + new Date().toLocaleString());
            }
        }, 1500);
    }
}
