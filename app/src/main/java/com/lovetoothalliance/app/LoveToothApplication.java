package com.lovetoothalliance.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.lovetoothalliance.app.db.BaseDao;
import com.lovetoothalliance.app.db.DatabaseHelper;
import com.lovetoothalliance.app.db.IOperateType;
import com.lovetoothalliance.app.dialog.LoadingDialog;
import com.lovetoothalliance.app.ui.LoginActivity;
import com.lovetoothalliance.app.util.Utils;
import com.utils.ToastUtils;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Author:lt
 * time:2017/7/21.
 * contact：weileng143@163.com
 *
 * @description
 */

public class LoveToothApplication extends Application {

    public static LoveToothApplication applicationContext = null;
    // 存储打开的activity，用于重新启动activity时候用
    public static ArrayList<Activity> activityList = new ArrayList<Activity>();

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this;
//        //全局异常
//        CrashHandler crashHandler = CrashHandler.getInstance();
//        crashHandler.init(getApplicationContext());
        //环信初始化
        setHxInitData();
        //初始化高德地图
        initLocation();
    }

    public static LoveToothApplication getInstance() {
        return (LoveToothApplication) applicationContext;
    }

    //环信部分
    public void setHxInitData() {
        EMOptions options = new EMOptions();
        // 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);
        //SDK 中自动登录属性默认是 true 打开的，如果不需要自动登录，在初始化 SDK 初始化的时候，调用options.setAutoLogin(false);设置为 false 关闭。
        options.setAutoLogin(false);
       //初始化
        EMClient.getInstance().init(applicationContext, options);
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    //==============================================================定位部分封装================================================//

    public interface MyLLLocationListener {
        void getLoaction(AMapLocation location, AMapLocationClient locationClient);
    }

    private MyLLLocationListener lllListener;
    private LoadingDialog loadingDialog;
    /**
     * 初始化定位
     */
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;

    private void initLocation() {
        //初始化client
        locationClient = new AMapLocationClient(getApplicationContext());
        locationOption = getDefaultOption();
        //设置定位参数
        locationClient.setLocationOption(locationOption);
    }

    /**
     * 执行定位
     */
    public void getMyLocation(Context context, MyLLLocationListener listener,
                              boolean isShowDialog) {
        this.lllListener = listener;
//        if(locationClient.isStarted()){
//             return;
//        }
        if (isShowDialog) {
            loadingDialog = new LoadingDialog(context);
            loadingDialog.show();
            loadingDialog.setTitle("正在定位中");
        }
        locationClient.setLocationListener(new MyLocationListener());
        // 启动定位
        locationClient.startLocation();
    }

    /**
     * 实现实时位置回调监听
     */
    public class MyLocationListener implements AMapLocationListener {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            // location.getLatitude();//维度
            // location.getLongitude();//经度
            if (null != loadingDialog && loadingDialog.isShowing()) {
                loadingDialog.dismiss();
            }
            //停止定位
//            locationClient.stopLocation();
            lllListener.getLoaction(aMapLocation, locationClient);
        }
    }

    // 根据控件的选择，重新设置定位参数
//    private void resetOption() {
//        // 设置是否需要显示地址信息
//        locationOption.setNeedAddress(cbAddress.isChecked());
//        /**
//         * 设置是否优先返回GPS定位结果，如果30秒内GPS没有返回定位结果则进行网络定位
//         * 注意：只有在高精度模式下的单次定位有效，其他方式无效
//         */
//        locationOption.setGpsFirst(cbGpsFirst.isChecked());
//        // 设置是否开启缓存
//        locationOption.setLocationCacheEnable(cbCacheAble.isChecked());
//        // 设置是否单次定位
//        locationOption.setOnceLocation(cbOnceLocation.isChecked());
//        //设置是否等待设备wifi刷新，如果设置为true,会自动变为单次定位，持续定位时不要使用
//        locationOption.setOnceLocationLatest(cbOnceLastest.isChecked());
//        //设置是否使用传感器
//        locationOption.setSensorEnable(cbSensorAble.isChecked());
//        //设置是否开启wifi扫描，如果设置为false时同时会停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
//        String strInterval = etInterval.getText().toString();
//        if (!TextUtils.isEmpty(strInterval)) {
//            try{
//                // 设置发送定位请求的时间间隔,最小值为1000，如果小于1000，按照1000算
//                locationOption.setInterval(Long.valueOf(strInterval));
//            }catch(Throwable e){
//                e.printStackTrace();
//            }
//        }
//
//        String strTimeout = etHttpTimeout.getText().toString();
//        if(!TextUtils.isEmpty(strTimeout)){
//            try{
//                // 设置网络请求超时时间
//                locationOption.setHttpTimeOut(Long.valueOf(strTimeout));
//            }catch(Throwable e){
//                e.printStackTrace();
//            }
//        }
//    }

    /**
     * 默认的定位参数
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        return mOption;
    }

    /**
     * 定位监听
     */
    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation location) {
            if (null != location) {

                StringBuffer sb = new StringBuffer();
                //errCode等于0代表定位成功，其他的为定位失败，具体的可以参照官网定位错误码说明
                if (location.getErrorCode() == 0) {
                    sb.append("定位成功" + "\n");
                    sb.append("定位类型: " + location.getLocationType() + "\n");
                    sb.append("经    度    : " + location.getLongitude() + "\n");
                    sb.append("纬    度    : " + location.getLatitude() + "\n");
                    sb.append("精    度    : " + location.getAccuracy() + "米" + "\n");
                    sb.append("提供者    : " + location.getProvider() + "\n");

                    sb.append("速    度    : " + location.getSpeed() + "米/秒" + "\n");
                    sb.append("角    度    : " + location.getBearing() + "\n");
                    // 获取当前提供定位服务的卫星个数
                    sb.append("星    数    : " + location.getSatellites() + "\n");
                    sb.append("国    家    : " + location.getCountry() + "\n");
                    sb.append("省            : " + location.getProvince() + "\n");
                    sb.append("市            : " + location.getCity() + "\n");
                    sb.append("城市编码 : " + location.getCityCode() + "\n");
                    sb.append("区            : " + location.getDistrict() + "\n");
                    sb.append("区域 码   : " + location.getAdCode() + "\n");
                    sb.append("地    址    : " + location.getAddress() + "\n");
                    sb.append("兴趣点    : " + location.getPoiName() + "\n");
                    //定位完成的时间
                    sb.append("定位时间: " + Utils.formatUTC(location.getTime(), "yyyy-MM-dd HH:mm:ss") + "\n");
                } else {
                    //定位失败
                    sb.append("定位失败" + "\n");
                    sb.append("错误码:" + location.getErrorCode() + "\n");
                    sb.append("错误信息:" + location.getErrorInfo() + "\n");
                    sb.append("错误描述:" + location.getLocationDetail() + "\n");
                }
                //定位之后的回调时间
                sb.append("回调时间: " + Utils.formatUTC(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss") + "\n");

                //解析定位结果，
                String result = sb.toString();
                Log.i("Info", "城市的结果" + result);
                //使用sp保存数据
//                SharedPreferencesUtils.saveString(getApplicationContext(),"city",location.getCity());
                ToastUtils.showToastLong(getApplicationContext(), "定位成功");
            } else {
                Log.i("Info", "城市的结果" + "定位失败");
                ToastUtils.showToastLong(getApplicationContext(), "定位失败");
            }
        }
    };

    /**
     * 销毁定位
     */
    public void destroyLocation() {
        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }
    }
//==============================================================定位部分封装================================================//

    /**
     * 销毁全部界面
     */
    public void finishAllActivity() {
        if (activityList != null) {
            for (Activity activity : activityList) {
                if (!activity.isFinishing() && !(activity instanceof LoginActivity)) {
                    activity.finish();
                }
            }
            activityList.clear();
        }
    }

    /**
     * 得到操作数据库的dao
     *
     * @param clazz
     * @return
     */
    public/*protected*/ <T extends IOperateType> BaseDao<T> getDao(final Class<T> clazz) {
        BaseDao<T> baseDao = new BaseDao<T>() {

            @Override
            public Dao<T, Integer> getDao() throws SQLException {
                // TODO Auto-generated method stub
                return getHelper().getDao(clazz);
            }
        };
        return baseDao;
    }

    private DatabaseHelper dataHelper = null;

    private DatabaseHelper getHelper() {
        if (dataHelper == null) {
            dataHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return dataHelper;
    }

    private void replaceHelper() {
        if (dataHelper != null) {
            OpenHelperManager.releaseHelper();
            dataHelper = null;
        }
    }
}
