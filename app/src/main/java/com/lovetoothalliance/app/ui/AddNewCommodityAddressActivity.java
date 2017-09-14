package com.lovetoothalliance.app.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lovetoothalliance.app.R;
import com.lovetoothalliance.app.constant.ServiceUrl;
import com.lovetoothalliance.app.net.BaseNetEntity;
import com.lovetoothalliance.app.net.OkHttpResponseCallback;
import com.lovetoothalliance.app.widget.TopBarLayout;
import com.smarttop.library.bean.City;
import com.smarttop.library.bean.County;
import com.smarttop.library.bean.Province;
import com.smarttop.library.bean.Street;
import com.smarttop.library.db.manager.AddressDictManager;
import com.smarttop.library.utils.LogUtil;
import com.smarttop.library.widget.AddressSelector;
import com.smarttop.library.widget.BottomDialog;
import com.smarttop.library.widget.OnAddressSelectedListener;
import com.utils.SharedPreferencesUtils;
import com.utils.ToastUtils;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Author:lt
 * time:2017/8/15.
 * contact：weileng143@163.com
 *
 * @description
 */

public class AddNewCommodityAddressActivity extends BaseActivity
        implements OnAddressSelectedListener, AddressSelector.OnDialogCloseListener {
    private static String Tag = "AddNewCommodityAddressActivity";
    @InjectView(R.id.topbar)
    TopBarLayout topbar;
    @InjectView(R.id.ed_addressee_name)
    EditText edAddresseeName;
    @InjectView(R.id.ed_addressee_phone)
    EditText edAddresseePhone;
    @InjectView(R.id.ll_area)
    LinearLayout llArea;
    @InjectView(R.id.ed_detail_address)
    EditText edDetailAddress;
    @InjectView(R.id.tv_city_area)
    TextView tvCityArea;
    @InjectView(R.id.btn_address_save)
    Button btnAddressSave;

    private String mCname = "";
    private String mCaddress = "";
    private String mCmobile = "";
    private int mDefault = 0;
    private String guid = "";

    private String type = "";

    @Override
    protected void initView() {
        setContentView(R.layout.activity_add_commodity_ad);
    }

    @Override
    protected void initData() {
        topbar.setTxvTitleName("新建收货地址");
        //接收bundle
        getBundleData();
        //初始参数
        inItParameter();
    }

    /**
     * 接收bundle
     */
    public void getBundleData() {
        Bundle bundle = getIntent().getExtras();
        type = bundle.getString("type");
        if (type.equals("compileAdress")) {
            mCname = bundle.getString("name");
            mCaddress = bundle.getString("address");
            mCmobile = bundle.getString("mobile");
            mDefault = bundle.getInt("default");
            guid = bundle.getString("guid");

            //设置参数显示
            edAddresseePhone.setText(mCmobile);
            edAddresseeName.setText(mCname);
            tvCityArea.setText(mCaddress);
        }
    }

    /**
     * 初始化参数
     */
    public void inItParameter() {
        AddressSelector selector = new AddressSelector(this);
        //获取地址管理数据库
        addressDictManager = selector.getAddressDictManager();
    }

    /**
     * 添加收货的地址
     */
    public void AddCommodityAddressData() {
        mCname = edAddresseeName.getText().toString();
        mCaddress = edDetailAddress.getText().toString();
        mCmobile = edAddresseePhone.getText().toString();
        String addCommodtityUrl = ServiceUrl.Get_Add_NewAddress + SharedPreferencesUtils.getString(this, "memlogin", "")
        +"&default=0&name=" + mCname + "&mobile=" + mCmobile + "&address=" + province + " " + city + " " + county + " " + street;
        Log.i(Tag, "添加收货的地址" + addCommodtityUrl);
        BaseNetEntity baseNetEntity = new BaseNetEntity();
        baseNetEntity.get(AddNewCommodityAddressActivity.this, addCommodtityUrl, "正在添加收货地址",
                true, new OkHttpResponseCallback<Object>(Object.class) {
                    @Override
                    public void onSuccess(Object bean) {
                        SharedPreferencesUtils.saveBoolean(AddNewCommodityAddressActivity.this, "isReqCmAddress", true);
                        finish();
                    }

                    @Override
                    public void onFailError(Exception e) {

                    }
                });
    }

    /**
     * 编辑收货地址
     */
    public void UpdateCommodityAddressData() {
        mCname = edAddresseeName.getText().toString();
        mCaddress = edDetailAddress.getText().toString();
        mCmobile = edAddresseePhone.getText().toString();

        String addCommodtityUrl = ServiceUrl.Compile_Commodity_Address+guid+
                "&default="+mDefault+"&memberName="+SharedPreferencesUtils.getString(this, "memlogin", "")
                + "&name=" + mCname + "&mobile=" + mCmobile + "&address=" + tvCityArea.getText();
        Log.i(Tag, "添加收货的地址" + addCommodtityUrl);
        BaseNetEntity baseNetEntity = new BaseNetEntity();
        baseNetEntity.get(AddNewCommodityAddressActivity.this, addCommodtityUrl, "正在添加收货地址",
                true, new OkHttpResponseCallback<Object>(Object.class) {
                    @Override
                    public void onSuccess(Object bean) {
                        SharedPreferencesUtils.saveBoolean(AddNewCommodityAddressActivity.this, "isReqCmAddress", true);
                        finish();
                        ToastUtils.showToastLong(AddNewCommodityAddressActivity.this,"更新数据成功");
                    }

                    @Override
                    public void onFailError(Exception e) {

                    }
                });
    }


    @OnClick({R.id.ll_area, R.id.btn_address_save})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_address_save:
                if (type.equals("newAddress")) {
                    AddCommodityAddressData();
                } else {
                    //更新收货地址
                    UpdateCommodityAddressData();
                }
                break;
            case R.id.ll_area:
                setCitySelector();
                break;
        }

    }

    //======================================================城市选择开始====================================//
    /**
     * 开始执行城市选择
     */
    private AddressDictManager addressDictManager;
    private LinearLayout content;
    private BottomDialog dialog;
    private String provinceCode;
    private String cityCode;
    private String countyCode;
    private String streetCode;
    //选择城市单个数据
    private String province, city, county, street;

    public void setCitySelector() {
        if (dialog != null) {
            dialog.show();
        } else {
            dialog = new BottomDialog(this);
            dialog.setOnAddressSelectedListener(this);
            dialog.setDialogDismisListener(this);
            dialog.setTextSize(14);//设置字体的大小
            dialog.setIndicatorBackgroundColor(android.R.color.holo_orange_light);//设置指示器的颜色
            dialog.setTextSelectedColor(android.R.color.holo_orange_light);//设置字体获得焦点的颜色
            dialog.setTextUnSelectedColor(android.R.color.holo_blue_light);//设置字体没有获得焦点的颜色
            dialog.show();
        }

    }

    @Override
    public void dialogclose() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    public void onAddressSelected(Province province, City city, County county, Street street) {
        provinceCode = (province == null ? "" : province.code);
        cityCode = (city == null ? "" : city.code);
        countyCode = (county == null ? "" : county.code);
        streetCode = (street == null ? "" : street.code);
        LogUtil.d("数据", "省份id=" + provinceCode);
        LogUtil.d("数据", "城市id=" + cityCode);
        LogUtil.d("数据", "乡镇id=" + countyCode);
        LogUtil.d("数据", "街道id=" + streetCode);
        String s = (province == null ? "" : province.name) + (city == null ? "" : city.name) + (county == null ? "" : county.name) +
                (street == null ? "" : street.name);
        tvCityArea.setText(s);
        if (dialog != null) {
            dialog.dismiss();
        }
        //显示选择过的地区
        getSelectedArea();
    }

    /**
     * 根据code 来显示选择过的地区
     */
    private void getSelectedArea() {
        province = addressDictManager.getProvince(provinceCode);
        city = addressDictManager.getCity(cityCode);
        county = addressDictManager.getCounty(countyCode);
        street = addressDictManager.getStreet(streetCode);
        LogUtil.d("数据", "省份=" + province);
        LogUtil.d("数据", "城市=" + city);
        LogUtil.d("数据", "乡镇=" + county);
        LogUtil.d("数据", "街道=" + street);
    }

//======================================================城市选择结束====================================//
}
