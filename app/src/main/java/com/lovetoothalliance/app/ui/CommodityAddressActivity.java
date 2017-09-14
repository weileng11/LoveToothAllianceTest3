package com.lovetoothalliance.app.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.lovetoothalliance.app.R;
import com.lovetoothalliance.app.adapter.CommonAdapter;
import com.lovetoothalliance.app.adapter.ViewHolder;
import com.lovetoothalliance.app.constant.ServiceUrl;
import com.lovetoothalliance.app.net.BaseNetEntity;
import com.lovetoothalliance.app.net.OkHttpResponseCallback;
import com.lovetoothalliance.app.net.bean.RespnoseResultBean;
import com.lovetoothalliance.app.net.bean.response.CommodityAddressRespBean;
import com.lovetoothalliance.app.widget.TopBarLayout;
import com.utils.SharedPreferencesUtils;
import com.utils.view.MyListview;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Author:lt
 * time:2017/8/15.
 * contact：weileng143@163.com
 *
 * @description 收货地址
 */

public class CommodityAddressActivity extends BaseActivity {
    private static String Tag = "CommodityAddressActivity";

    @InjectView(R.id.topbar)
    TopBarLayout topbar;
    @InjectView(R.id.lv_commodity_address)
    MyListview lvCommodityAddress;
    @InjectView(R.id.btn_new_address)
    Button btnNewAddress;

    private BaseAdapter mBaseAdapter;
    private List<CommodityAddressRespBean.DataBean> mCommodtityList = new ArrayList<>();
    private CommodityAddressRespBean.DataBean mCommodityBean;
    //设置一个状态来解决新增收货地址不管是否保存每次都要调用onresume方法
    public boolean isReqCmAddress = true;

    private String type = "";  //newAddress 表示新建  compileAdress 表示编辑

    @Override
    protected void initView() {
        setContentView(R.layout.activity_commodity_address);
    }

    @Override
    protected void initData() {
        topbar.setTxvTitleName("收货地址管理");
        //初始适配
        setListViewData();
        //默认为true
        isReqCmAddress = true;
        SharedPreferencesUtils.saveBoolean(this, "isReqCmAddress", isReqCmAddress);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (SharedPreferencesUtils.getBoolean(this, "isReqCmAddress", true)) {
            //获取收货地址数据
            getCommodityAddressData();
        }
    }

    /**
     * 获取收货地址的数据
     */
    public void getCommodityAddressData() {
        BaseNetEntity baseNetEntity = new BaseNetEntity();
        baseNetEntity.get(CommodityAddressActivity.this, ServiceUrl.Get_Commdity_Address
                        + SharedPreferencesUtils.getString(this, "memlogin", ""), "正在加载数据",
                true, new OkHttpResponseCallback<CommodityAddressRespBean>(CommodityAddressRespBean.class) {
                    @Override
                    public void onSuccess(CommodityAddressRespBean bean) {
                        if (mCommodtityList.size() > 0) {
                            mCommodtityList.clear();
                        }
                        if (bean.data.size() > 0) {
                            mCommodtityList.addAll(bean.data);
                            //直接刷新适配
                            if (mBaseAdapter != null) {
                                mBaseAdapter.notifyDataSetChanged();
                            } else {
                                setListViewData();
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
     * 设置listview适配
     */
    public void setListViewData() {
        mBaseAdapter = new CommonAdapter<CommodityAddressRespBean.DataBean>(CommodityAddressActivity.this, mCommodtityList,
                R.layout.commodity_address_item) {
            @Override
            public void convert(ViewHolder helper, CommodityAddressRespBean.DataBean item, final int position) {
                mCommodityBean = item;
                helper.setText(R.id.tv_address_name, item.Name);
                helper.setText(R.id.tv_address_phone, item.Mobile);
                helper.setText(R.id.tv_address_detail, item.Address);
                if (item.IsDefault == 1) { //说明是默认的
                    helper.getView(R.id.iv_default_address).setBackgroundResource(R.mipmap.cm_default);
                } else {
                    helper.getView(R.id.iv_default_address).setBackgroundResource(R.mipmap.c_address);
                }

                //编辑
                helper.getView(R.id.commdity_compile).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        type = "compileAdress";
                        setCommodtityTypeToActivity(type,mCommodtityList.get(position));
                    }
                });
                //删除
                helper.getView(R.id.commdity_delete).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteCommodity(mCommodtityList.get(position));
                    }
                });

                //设置默认地址
                helper.getView(R.id.iv_default_address).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //点击设置为默认地址
                        setCommodityDefaultAddress(mCommodtityList.get(position));
                    }
                });
//                Glide.with(getActivity()).load(item.Goods.get(0).OriginalImge).into((ImageView) helper.getView(R.id.iv_diagonse));
//
//                helper.setText(R.id.btn_diagnose_evaluate,"取消订单");
//                helper.setText(R.id.btn_diagnose_buy,"去付款");

            }
        };
        lvCommodityAddress.setAdapter(mBaseAdapter);
    }

    /**
     * 删除收货地址
     */
    private void deleteCommodity(final CommodityAddressRespBean.DataBean mBean) {
        String deleteCommodity = ServiceUrl.Delete_Address + mBean.Guid + "&memberName=" + SharedPreferencesUtils.getString(this, "memlogin", "");
        Log.i(Tag, "删除地址" + ServiceUrl.Delete_Address + mBean.Guid);
        BaseNetEntity baseNetEntity = new BaseNetEntity();
        baseNetEntity.get(CommodityAddressActivity.this, deleteCommodity, "正在加载数据", true, new OkHttpResponseCallback<RespnoseResultBean>(RespnoseResultBean.class) {
            @Override
            public void onSuccess(RespnoseResultBean bean) {
                mCommodtityList.remove(mBean);
                mBaseAdapter.notifyDataSetChanged();
                Log.i(Tag, "删除成功");
            }

            @Override
            public void onFailError(Exception e) {

            }
        });
    }


    /**
     * 设置为默认的地址
     */
    public void setCommodityDefaultAddress(final CommodityAddressRespBean.DataBean mBean) {
        String defaultAddressUrl = ServiceUrl.Update_Default_Address
                + mBean.Guid + "&default=1" + "&memberName=" + SharedPreferencesUtils.getString(this, "memlogin", "");
        Log.i(Tag, "默认地址" + defaultAddressUrl);
        BaseNetEntity baseNetEntity = new BaseNetEntity();
        baseNetEntity.get(CommodityAddressActivity.this, defaultAddressUrl, "正在加载数据",
                true, new OkHttpResponseCallback<RespnoseResultBean>(RespnoseResultBean.class) {
                    @Override
                    public void onSuccess(RespnoseResultBean bean) {
                        //在请求一次网络
//                        mBean.IsDefault=1;
//                        mCommodtityList.add(mBean);
//                        mBaseAdapter.notifyDataSetChanged();
                        getCommodityAddressData();
                        Log.i(Tag, "设置默认地址ok");
                    }

                    @Override
                    public void onFailError(Exception e) {

                    }
                });
    }


    @OnClick(R.id.btn_new_address)
    public void onClick() {
        isReqCmAddress = false;
        SharedPreferencesUtils.saveBoolean(this, "isReqCmAddress", isReqCmAddress);
        type = "newAddress";
        setCommodtityTypeToActivity(type,null);
    }

    /**
     * 跟进传递的类型决定是否传递值
     */
    public void setCommodtityTypeToActivity(String type,final CommodityAddressRespBean.DataBean mBean) {
        Bundle bundle = new Bundle();
        if (type.equals("newAddress")) {
            bundle.putString("type", type);
        } else if (type.equals("compileAdress")) {
            bundle.putString("type", type);
            bundle.putString("name", mBean.Name);
            bundle.putString("mobile", mBean.Mobile);
            bundle.putString("address", mBean.Address);
            bundle.putInt("default",mBean.IsDefault);
            bundle.putString("guid",mBean.Guid);

        }

        //每次跳转
        showActivity(CommodityAddressActivity.this, AddNewCommodityAddressActivity.class, bundle);
    }

}
