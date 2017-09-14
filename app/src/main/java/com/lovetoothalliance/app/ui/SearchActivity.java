package com.lovetoothalliance.app.ui;

import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lovetoothalliance.app.R;
import com.lovetoothalliance.app.adapter.CommonAdapter;
import com.lovetoothalliance.app.adapter.ViewHolder;
import com.lovetoothalliance.app.bean.PublicInfo;
import com.lovetoothalliance.app.constant.ServiceUrl;
import com.lovetoothalliance.app.net.BaseNetEntity;
import com.lovetoothalliance.app.net.OkHttpResponseCallback;
import com.lovetoothalliance.app.net.bean.response.SearchShopRespBean;
import com.utils.view.MyGridView;
import com.utils.view.MyListview;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Author:Bruce
 * time:2017/8/23.
 * contact：weileng143@163.com
 *
 * @description 搜索界面
 */

public class SearchActivity extends BaseActivity {
    private static String Tag = "SearchActivity";

    @InjectView(R.id.et_search)
    EditText etSearch;
    @InjectView(R.id.recently_lv)
    MyGridView recentlyLv;
    @InjectView(R.id.hot_lv)
    MyGridView hotLv;
    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.tv_start_search)
    TextView tvStartSearch;
    @InjectView(R.id.search_lv)
    MyListview searchLv;


    private String[] personalList = new String[]{"我的团队", "我的钱包", "法律顾问", "账号安全", "个人信息", "个人信息"};
    private int[] ivList = new int[]{R.mipmap.iv_personal, R.mipmap.iv_wallet,
            R.mipmap.iv_fl, R.mipmap.iv_safe, R.mipmap.iv_information, R.mipmap.iv_information};
    private ArrayList<PublicInfo> pList = new ArrayList<>();
    private BaseAdapter mBaseAdapter;
    private List<SearchShopRespBean.DataBean> mTestDataList = new ArrayList<>();

    @Override
    protected void initView() {
        setContentView(R.layout.activity_search);
    }

    @Override
    protected void initData() {
        for (int i = 0; i < personalList.length; i++) {
            PublicInfo pi = new PublicInfo(personalList[i], ivList[i]);
            pList.add(pi);
        }
        setSearchAdapter(pList);
        setSearchHotAdapter(pList);
        //获取搜索的数据
        searchAllShop();
    }

    public void setSearchAdapter(ArrayList<PublicInfo> mPersonList) {
        mBaseAdapter = new CommonAdapter<PublicInfo>(SearchActivity.this, mPersonList,
                R.layout.search_item) {
            @Override
            public void convert(ViewHolder helper, PublicInfo item, int position) {
                // 名称
                helper.setText(R.id.tv_search_name, item.getName());
            }
        };
        recentlyLv.setAdapter(mBaseAdapter);
    }

    public void setSearchHotAdapter(ArrayList<PublicInfo> mPersonList) {
        mBaseAdapter = new CommonAdapter<PublicInfo>(SearchActivity.this, mPersonList,
                R.layout.search_item) {
            @Override
            public void convert(ViewHolder helper, PublicInfo item, int position) {
                // 名称
                helper.setText(R.id.tv_search_name, item.getName());
            }
        };
        hotLv.setAdapter(mBaseAdapter);
    }


    @OnClick({R.id.iv_back, R.id.tv_start_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_start_search:
                break;
        }
    }

    /**
     * 搜索全部的项目
     */
    public void searchAllShop() {
        String searchUrl = ServiceUrl.Search_All_Data;
        Log.i(Tag, "搜索" + searchUrl);
        BaseNetEntity baseNetEntity = new BaseNetEntity();
        baseNetEntity.get(SearchActivity.this, searchUrl, "正在搜索",
                true, new OkHttpResponseCallback<SearchShopRespBean>(SearchShopRespBean.class) {
                    @Override
                    public void onSuccess(SearchShopRespBean bean) {
                        Log.i(Tag, "获取搜索的数据成功");
                        if (bean.data.size() > 0) {
                            mTestDataList.addAll(bean.data);
                            setSearchToDataAdapter();
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
     * 设置适配器
     */
    public void setSearchToDataAdapter() {
        mBaseAdapter = new CommonAdapter<SearchShopRespBean.DataBean>(this, mTestDataList,
                R.layout.search_lv_item) {
            @Override
            public void convert(ViewHolder helper, SearchShopRespBean.DataBean item, int position) {
                // 名称
                helper.setText(R.id.tv_search_name, item.Name);
                helper.setText(R.id.tv_pPrice,String.valueOf(item.MarketPrice));
                helper.setText(R.id.tv_primary_price,String.valueOf(item.ShopPrice));
            }
        };
        searchLv.setAdapter(mBaseAdapter);
    }

}
