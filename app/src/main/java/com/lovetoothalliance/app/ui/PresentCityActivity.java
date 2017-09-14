package com.lovetoothalliance.app.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.lovetoothalliance.app.LoveToothApplication;
import com.lovetoothalliance.app.R;
import com.lovetoothalliance.app.adapter.CommonAdapter;
import com.lovetoothalliance.app.adapter.ItemBeanAdapter;
import com.lovetoothalliance.app.adapter.SearchCityAdapter;
import com.lovetoothalliance.app.adapter.ViewHolder;
import com.lovetoothalliance.app.bean.PublicInfo;
import com.lovetoothalliance.app.db.City;
import com.lovetoothalliance.app.db.MyCityDBHelper;
import com.lovetoothalliance.app.db.T;
import com.lovetoothalliance.app.widget.ClearEditText;
import com.lovetoothalliance.app.widget.MyLetterSortView;
import com.utils.view.MyGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Author:lt
 * time:2017/7/28.
 * contact：weileng143@163.com
 *
 * @description 当前城市
 */

public class PresentCityActivity extends BaseActivity {

    @InjectView(R.id.iv_back)
    ImageView ivBack;
    private Context context = PresentCityActivity.this;

    private ClearEditText mClearEditText;
    private TextView tv_mid_letter;
    private Button mBtnLocation;
    private ListView listView;
    private MyGridView gvCity;
    private MyLetterSortView right_letter;

    private ItemBeanAdapter mAdapter;
    private List<City> mlist = new ArrayList<City>();
    private InputMethodManager inputMethodManager;

    private View mCityContainer;
    private FrameLayout mSearchContainer;
    private ListView mSearchListView;
    private SearchCityAdapter mSearchCityAdapter;


    private String[] homeList = new String[]{"我的镶牙", "我要种牙", "其他项目", "找门诊",
            "找专家", "客服", "门诊寻求合作", "积分商城"};
    private int[] ivList = new int[]{R.mipmap.home_xtooth, R.mipmap.home_zytooth,
            R.mipmap.home_else, R.mipmap.home_recode, R.mipmap.home_specialist,
            R.mipmap.home_service, R.mipmap.home_collaborate, R.mipmap.home_store};
    private BaseAdapter mBaseAdapter;
    private ArrayList<PublicInfo> pList = new ArrayList<>();

    @Override
    protected void initView() {
        setContentView(R.layout.activity_present_city);
    }

    @Override
    protected void initData() {
        //设置初始参数
        initView2();
        setLinstener();
        initData2();
        fillData();
    }

    protected void initData2() {
        //设置gv值
        setGridviewParams();
        getDataCity();
        mAdapter = new ItemBeanAdapter(this, mlist);
        listView.setEmptyView(findViewById(R.id.citys_list_load));
        listView.setAdapter(mAdapter);

    }

    public void setGridviewParams() {
        setPicAndTvValue();
    }

    /**
     * 设置gv文字和图片的参数
     *
     * @return
     */
    public void setPicAndTvValue() {
        for (int i = 0; i < homeList.length; i++) {
            PublicInfo pi = new PublicInfo(homeList[i], ivList[i]);
            pList.add(pi);
        }
        Log.i("INFO", "总数据" + pList.size());
        //设置界面参数值
        setInterfaceParams(pList);
    }

    public void setInterfaceParams(ArrayList<PublicInfo> mPersonList) {
        mBaseAdapter = new CommonAdapter<PublicInfo>(this, mPersonList,
                R.layout.city_button_item) {
            @Override
            public void convert(ViewHolder helper, PublicInfo item, int position) {
                // 名称
                helper.setText(R.id.btn_city_item, item.getName());

            }
        };
        gvCity.setAdapter(mBaseAdapter);
    }

    protected void initView2() {

        inputMethodManager = (InputMethodManager) this
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        listView = (ListView) findViewById(R.id.list);
        gvCity = (MyGridView) findViewById(R.id.gv_city);
        mClearEditText = (ClearEditText) findViewById(R.id.et_msg_search);
        mBtnLocation = (Button) findViewById(R.id.btn_location);
        // 这里设置中间字母
        right_letter = (MyLetterSortView) findViewById(R.id.right_letter);
        tv_mid_letter = (TextView) findViewById(R.id.tv_mid_letter);
        right_letter.setTextView(tv_mid_letter);
        //搜索
        mCityContainer = findViewById(R.id.city_content_container);
        mSearchContainer = (FrameLayout) this.findViewById(R.id.search_content_container);
        mSearchListView = (ListView) findViewById(R.id.search_list);
        mSearchListView.setEmptyView(findViewById(R.id.search_empty));
        mSearchContainer.setVisibility(View.GONE);

    }

    protected void setLinstener() {
        // tv_reget_pwd.setOnClickListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                T.showShort(getApplicationContext(),
                        ((City) mAdapter.getItem(position)).toString());

            }
        });

        listView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // 隐藏软键盘
                if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
                    if (getCurrentFocus() != null)
                        inputMethodManager.hideSoftInputFromWindow(
                                getCurrentFocus().getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);
                }
                return false;
            }
        });

        mBtnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoveToothApplication.getInstance().getMyLocation(PresentCityActivity.this, new LoveToothApplication.MyLLLocationListener() {
                    @Override
                    public void getLoaction(AMapLocation location, AMapLocationClient locationClient) {
                        Log.i("INFO", "城市" + location.getCity());
                        mBtnLocation.setText(location.getCity());
                        locationClient.stopLocation();//停止定位
                    }
                }, true);
            }
        });

        // 设置右侧触摸监听
        right_letter
                .setOnTouchingLetterChangedListener(new MyLetterSortView.OnTouchingLetterChangedListener() {

                    @Override
                    public void onTouchingLetterChanged(String s) {
                        // 该字母首次出现的位置
                        int position = mAdapter.getPositionForSection(s
                                .charAt(0));
                        if (position != -1) {
                            listView.setSelection(position);
                        }

                    }
                });

        // 根据输入框输入值的改变来过滤搜索
        mClearEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // 当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
                filterData2(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mSearchListView
                .setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        // TODO Auto-generated method stub
                        // Logeer.i(mSearchCityAdapter.getItem(position).toString());
                        T.showLong(getApplicationContext(), mSearchCityAdapter
                                .getItem(position).toString());
                    }
                });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    protected void fillData() {
        // TODO Auto-generated method stub

    }

    private void getDataCity() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                MyCityDBHelper myCityDBHelper = new MyCityDBHelper(context);
                mlist = myCityDBHelper.getCityDB().getAllCity();
                mHandler.sendEmptyMessage(0);
            }
        }).start();
        // 对list进行排序
        // Collections.sort(mlist, new PinyinComparator() {
        // });

    }

    private void filterData2(String filterStr) {
        mSearchCityAdapter = new SearchCityAdapter(this,
                mlist);
        mSearchListView.setAdapter(mSearchCityAdapter);
        mSearchListView.setTextFilterEnabled(true);
        if (mlist.size() < 1 || TextUtils.isEmpty(filterStr)) {
            mCityContainer.setVisibility(View.VISIBLE);
            mSearchContainer.setVisibility(View.INVISIBLE);

        } else {

            mCityContainer.setVisibility(View.INVISIBLE);
            mSearchContainer.setVisibility(View.VISIBLE);
            mSearchCityAdapter.getFilter().filter(filterStr);
        }
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    mAdapter.updateListView(mlist);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }
}
