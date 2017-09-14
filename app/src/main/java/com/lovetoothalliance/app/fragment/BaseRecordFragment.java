package com.lovetoothalliance.app.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lovetoothalliance.app.R;
import com.lovetoothalliance.app.adapter.CommonAdapter;
import com.lovetoothalliance.app.adapter.ViewHolder;
import com.lovetoothalliance.app.constant.ServiceUrl;
import com.lovetoothalliance.app.net.BaseNetEntity;
import com.lovetoothalliance.app.net.OkHttpResponseCallback;
import com.lovetoothalliance.app.net.bean.response.AllOrderRespBean;
import com.lovetoothalliance.app.ui.EvaluateActivity;
import com.lovetoothalliance.app.ui.ImmediatelyBuyActivity;
import com.lovetoothalliance.app.widget.PullToRefreshView;
import com.utils.SharedPreferencesUtils;
import com.utils.ToastUtils;
import com.utils.view.MyListview;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.InjectView;

/**
 * Author:lt
 * time:2017/8/21.
 * contact：weileng143@163.com
 *
 * @description 诊断记录所有子类的fragment父类
 */

public abstract class BaseRecordFragment extends BaseFragment implements PullToRefreshView.OnHeaderRefreshListener,
        PullToRefreshView.OnFooterRefreshListener {
    private static String Tag = "BaseRecordFragment";

    @InjectView(R.id.diagnose_listview)
    MyListview mListview;
    @InjectView(R.id.main_pull_refresh_view)
    PullToRefreshView mPullToRefreshView;

    //    private BaseAdapter mBaseAdapter;
    private CommonAdapter<AllOrderRespBean.DataBean> mBaseAdapter;
    public List<AllOrderRespBean.DataBean> mOrderList = new ArrayList<>();

    public final int RECODERD_ALL = 1; //全部
    public final int RECODERD_PAYFOR = 2;//待付款
    public final int RECODERD_USE = 3;//待使用
    public final int RECODERD_EVALUATE = 4;//待评价
    private int mPosition = 0;

    public int mycurrentPage = 1; //当前的页数
    private int mPageSize = 3; //请求几条数据
    private int mRecordCount = 3; //默认和请求多少一样
    //传递一个状态来处理是否已经评价
    public static boolean isEvaluateOk=false;
    public static boolean isAllEvaluateOk=false;
    public static int backType=0;

    public static int getBackType() {
        return backType;
    }

    public static void setBackType(int backType) {
        BaseRecordFragment.backType = backType;
    }

    //记录一个状态来处理找专家下单提交订单的情况
    public static boolean isSpecialist=false;

    protected abstract int getRecordType();

    protected abstract int getPosition();

    protected abstract String getParamUrl();

    protected abstract void setAdapterParamValues(ViewHolder holder, int position, AllOrderRespBean.DataBean item, List<AllOrderRespBean.DataBean> mOrderList);

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recoed_array;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        //刷新
        mPullToRefreshView.setOnHeaderRefreshListener(this);
        mPullToRefreshView.setOnFooterRefreshListener(this);
        mPullToRefreshView.setLastUpdated(new Date().toLocaleString());
        //初始化方法
        setListViewData();
        getRecordData();
    }

    /**
     * 请求诊断记录网络数据
     */
    public void getRecordData() {
        String patmentBuyUrl = "";
        if (RECODERD_ALL == getRecordType()) {
            patmentBuyUrl = ServiceUrl.Get_Diagnostic_Record + "&PageNo=" + mycurrentPage + "&PageSize=" + mPageSize;
        } else {
            patmentBuyUrl = ServiceUrl.Get_Diagnostic_Record + "&IsBack=0"
                    + getParamUrl() + "&PageNo=" + mycurrentPage + "&PageSize=" + mPageSize;
        }
        BaseNetEntity baseNetEntity = new BaseNetEntity();
        baseNetEntity.get(getActivity(), patmentBuyUrl, "正在请求数据",
                true, new OkHttpResponseCallback<AllOrderRespBean>(AllOrderRespBean.class) {
                    @Override
                    public void onSuccess(AllOrderRespBean bean) {
                        //在调用一次
                        setComplete();
                        //将每次返回的条目数量记下来
                        mRecordCount = bean.data.size();
                        if (bean.data.size() > 0) {
                            setRecordData(bean);
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
     * 取消订单
     */
    public void cancelOrder(String ordernumber) {
        String cancleOrderUrl = ServiceUrl.Cancle_Order + "MemLoginID=" +
                SharedPreferencesUtils.getString(getActivity(), "memlogin", "") + "&orderNumber=" + ordernumber;
        Log.i(Tag, "取消订单" + cancleOrderUrl);
        BaseNetEntity baseNetEntity = new BaseNetEntity();
        baseNetEntity.get(getActivity(), cancleOrderUrl, "正在取消订单",
                true, new OkHttpResponseCallback<Object>(Object.class) {
                    @Override
                    public void onSuccess(Object bean) {
                        Log.i(Tag, "取消订单");
                        //取消成功就移除当前的bean
                        ToastUtils.showToastLong(getActivity(), "取消订单成功");

                        mOrderList.remove(mOrderList.get(getPosition()));
                        mBaseAdapter.notifyDataSetChanged();
                        getRecordData();
                    }

                    @Override
                    public void onFailError(Exception e) {

                    }
                });
    }

    /**
     * 确定收货，显示出未评价
     * "memberLoginID" + '&orderNumber=' + OrderNumber
     */
    public void commitOrderShowEvaluate(String ordernumber) {
        String cancleOrderUrl = ServiceUrl.Commit_Ok +
                SharedPreferencesUtils.getString(getActivity(), "memlogin", "") + "&orderNumber=" + ordernumber;
        Log.i(Tag, "确定收货 " + cancleOrderUrl);
        BaseNetEntity baseNetEntity = new BaseNetEntity();
        baseNetEntity.get(getActivity(), cancleOrderUrl, "正在收货",
                true, new OkHttpResponseCallback<Object>(Object.class) {
                    @Override
                    public void onSuccess(Object bean) {
                        //取消成功就移除当前的bean
                        ToastUtils.showToastLong(getActivity(), "确定成功");
                    }

                    @Override
                    public void onFailError(Exception e) {

                    }
                });
    }

    /**
     * 跳转评价界面
     */
    public void enterEvaluatePayment() {
        Log.i(Tag,"下标"+getPosition());
        if(getRecordType()==1){
            isAllEvaluateOk=false;
        }else{
            isEvaluateOk=false;
        }
        setBackType(getRecordType());
        //跳转评价页面
        Bundle bundle = new Bundle();
        bundle.putString("proguid", mOrderList.get(getPosition()).Goods.get(0).ProductGuid);
        bundle.putString("ordernumber", mOrderList.get(getPosition()).OrderNumber);
        bundle.putString("attributes", mOrderList.get(getPosition()).Goods.get(0).Attributes.trim());
        bundle.putString("name", mOrderList.get(getPosition()).Goods.get(0).Name);
        bundle.putString("OriginalImge", mOrderList.get(getPosition()).Goods.get(0).OriginalImge);
        bundle.putDouble("BuyPrice", mOrderList.get(getPosition()).Goods.get(0).BuyPrice);
        showActivity(getActivity(), EvaluateActivity.class, bundle);
    }

    /**
     * 去购买界面付款
     */
    public void enterBuyPayment() {
        //获取到下单的值传递给立刻购买界面
        Bundle bundle = new Bundle();
        bundle.putString("ordernumber", mOrderList.get(getPosition()).OrderNumber);
        bundle.putDouble("shouldpayprice", mOrderList.get(getPosition()).ShouldPayPrice);
        //名称和图标
        bundle.putString("ordername", mOrderList.get(getPosition()).Goods.get(0).Name);
        bundle.putString("orderpic", mOrderList.get(getPosition()).Goods.get(0).OriginalImge);
        bundle.putString("orderShopname", mOrderList.get(getPosition()).Goods.get(0).Name);
//
        showActivity(getActivity(), ImmediatelyBuyActivity.class, bundle);
    }


    /**
     * 设置值到List集合当中
     */
    public void setRecordData(AllOrderRespBean bean) {
        if (mycurrentPage == 1) {
            // 清空集合数据
            if (mOrderList.size() > 0) {
                mOrderList.clear();
            }
            mOrderList.addAll(bean.data);
        } else if (mOrderList.size() > 0) {

            for (int i = 0; i < bean.data.size(); i++) {
                int j = 0;
                for (j = 0; j < mOrderList.size(); j++) {  //当OrderNumber相同的话就不执行跳出当前循环
                    if (mOrderList.get(j).OrderNumber.equals(bean.data.get(i).OrderNumber)) {
                        break;
                    }
                }
                if (j == mOrderList.size()) {  //否则的话就添加到orderlist当中
                    mOrderList.add(bean.data.get(i));
                }
            }
            Log.i("INFO", "orderlisto------" + mOrderList.size());
        }
        Log.i("INFO", "orderlist======" + mOrderList.size());
        if (mBaseAdapter != null) {
            mBaseAdapter.setmDatas(mOrderList);
            mBaseAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 初始化适配
     */
    public void setListViewData() {
        mBaseAdapter = new CommonAdapter<AllOrderRespBean.DataBean>(getActivity(), mOrderList,
                R.layout.diagnose_item) {
            @Override
            public void convert(ViewHolder helper, final AllOrderRespBean.DataBean item, final int position) {
                setAdapterParamValues(helper, position, item, mOrderList);
            }

        };
        mListview.setAdapter(mBaseAdapter);
    }


    private boolean isFootRefresh = false;// 是否是上下拉刷新（获取数据分进入界面时自动获取数据，和下下拉获取数据两种情况）

    private void setComplete() {
        if (isFootRefresh) {
            mPullToRefreshView.onFooterRefreshComplete();
            isFootRefresh = false;
        }
        mPullToRefreshView.onHeaderRefreshComplete("更新于:"
                + new Date().toLocaleString());
    }

    //底部刷新 10 8
    @Override
    public void onFooterRefresh(PullToRefreshView view) {
        Log.i(Tag, "onFooterRefresh" + mRecordCount);
        if (mRecordCount == mPageSize) {
            mycurrentPage++;
            isFootRefresh = true;
            getRecordData();
        } else {
            Toast.makeText(getActivity(), "已经没有更多内容了", Toast.LENGTH_SHORT).show();
            isFootRefresh = true;
            setComplete();
        }
    }

    //头部刷新
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
