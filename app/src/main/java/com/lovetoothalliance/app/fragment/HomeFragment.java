package com.lovetoothalliance.app.fragment;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.bumptech.glide.Glide;
import com.lovetoothalliance.app.LoveToothApplication;
import com.lovetoothalliance.app.R;
import com.lovetoothalliance.app.adapter.CommonAdapter;
import com.lovetoothalliance.app.adapter.ViewHolder;
import com.lovetoothalliance.app.bean.PublicInfo;
import com.lovetoothalliance.app.constant.IntentParameter;
import com.lovetoothalliance.app.constant.ServiceUrl;
import com.lovetoothalliance.app.net.BaseNetEntity;
import com.lovetoothalliance.app.net.OkHttpResponseCallback;
import com.lovetoothalliance.app.net.bean.response.NearbyRespBean;
import com.lovetoothalliance.app.ui.ClinicContentActivity;
import com.lovetoothalliance.app.ui.ElseProjectActivity;
import com.lovetoothalliance.app.ui.HealthKnowledgeActivity;
import com.lovetoothalliance.app.ui.IntegralStoreActivity;
import com.lovetoothalliance.app.ui.MyWantToothActivity;
import com.lovetoothalliance.app.ui.NearbyShopActivity;
import com.lovetoothalliance.app.ui.OutpatientActivity;
import com.lovetoothalliance.app.ui.PresentCityActivity;
import com.lovetoothalliance.app.ui.SearchActivity;
import com.lovetoothalliance.app.ui.SeekSpecialistActivity;
import com.lovetoothalliance.app.util.Utils;
import com.utils.ToastUtils;
import com.utils.view.MyGridView;
import com.utils.view.MyListview;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;


/**
 * Author:lt
 * time:2017/7/17.
 * contact：weileng143@163.com
 *
 * @description
 */
public class HomeFragment extends BaseFragment implements OnBannerListener {

    private static String Tag = "HomeFragment";

    @InjectView(R.id.tv_city)
    TextView tvCity;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.iv_search)
    ImageView ivSearch;
    @InjectView(R.id.rl_title)
    RelativeLayout rlTitle;
    @InjectView(R.id.banner)
    Banner banner;
    @InjectView(R.id.home_gv)
    MyGridView homeGv;
    @InjectView(R.id.iv_nearby)
    ImageView ivNearby;
    @InjectView(R.id.ll_nearby)
    LinearLayout llNearby;
    @InjectView(R.id.home_listview)
    MyListview homeListview;
    @InjectView(R.id.ll_all_recode)
    LinearLayout llAllRecode;

    public static List<?> images = new ArrayList<>();
    public static List<String> titles = new ArrayList<>();
    @InjectView(R.id.tv_jk)
    TextView tvJk;
    @InjectView(R.id.view)
    View view;
    @InjectView(R.id.tv_y)
    TextView tvY;
    @InjectView(R.id.rl_health_knowledge)
    RelativeLayout rlHealthKnowledge;
    private View mView;

    private String[] homeList = new String[]{"我要镶牙", "我要种牙", "其他项目", "找门诊",
            "找专家", "客服", "门诊寻求合作", "积分商城"};
    private int[] ivList = new int[]{R.mipmap.home_xtooth, R.mipmap.home_zytooth,
            R.mipmap.home_else, R.mipmap.home_recode, R.mipmap.home_specialist,
            R.mipmap.home_service, R.mipmap.home_collaborate, R.mipmap.home_store};
    private BaseAdapter mBaseAdapter;
    private ArrayList<PublicInfo> pList =new ArrayList<>();

    //listview
    private BaseAdapter mListviewAdapter;
    //附近的店的bean
    private NearbyRespBean mNearbyBean;
    //附近的店的集合
    private List<NearbyRespBean.NearbyBeanList> mList = new ArrayList<NearbyRespBean.NearbyBeanList>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mView = view;
        //获取附近的店的数据显示
        getNearByData();
        //设置图片轮播
        setBannerShow();
        //设置gv的图片和文字参数
        setPicAndTvValue();
        //定位当前城市地点
        setLocationCity();
    }


    /**
     * 定位当前的城市
     */
    public void setLocationCity() {
        //定位服务
        LoveToothApplication.getInstance().getMyLocation(getActivity(), new LoveToothApplication.MyLLLocationListener() {
            @Override
            public void getLoaction(AMapLocation location, AMapLocationClient locationClient) {
                Log.i(Tag, "城市" + location.getCity());
//                tvCity.setText(location.getCity());
                if (location.getErrorCode() == 0) {
                    //设置城市显示
                    setCityIsNull(location.getCity(),locationClient);
                }else{
//                    ToastUtils.showToastLong(getActivity(),"定位失败");
                    //在一次定位，还是怎么处理...
                }

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
//
//
////                tvCity.setText(location.getCity());
            }
        }, true);
    }

    //判断城市是否为null
    public void setCityIsNull(String city,AMapLocationClient mapLocationClient){
        if(null==city||city.equals("")){
            setLocationCity();
        }else{
            tvCity.setText(city);
            mapLocationClient.stopLocation();
        }
    }


    /**
     * 图片轮播
     *
     * @return
     */
    public void setBannerShow() {
        //该数据只进行测试
        String[] urls = getActivity().getResources().getStringArray(R.array.url);
        List list = Arrays.asList(urls);
        images = new ArrayList(list);
        String[] title = getActivity().getResources().getStringArray(R.array.title);
        List titlelist = Arrays.asList(title);
        titles = new ArrayList(titlelist);

        Banner banner = (Banner) mView.findViewById(R.id.banner);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(titles);
        //设置图片点击监听
        banner.setOnBannerListener(this);
        //设置标题的颜色

        //banner设置方法全部调用完毕时最后调用
        banner.start();
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
        Log.i(Tag, "总数据" + pList.size());
        //设置界面参数值
        setInterfaceParams(pList);
    }

    public void setInterfaceParams(ArrayList<PublicInfo> mPersonList) {
        mBaseAdapter = new CommonAdapter<PublicInfo>(getActivity(), mPersonList,
                R.layout.home_gv_item) {
            @Override
            public void convert(ViewHolder helper, PublicInfo item,int position) {
                // 名称
                helper.setText(R.id.home_tv_content, item.getName());
                //图片
                helper.setImageBackground(R.id.home_iv_item, item.getIvContent());

            }
        };
        homeGv.setAdapter(mBaseAdapter);
        homeGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PublicInfo pi = (PublicInfo) mBaseAdapter.getItem(i);
                if (pi.getName().equals("我要镶牙")) {
                    Bundle bundle=new Bundle();
                    bundle.putString("guid","856dc4d0-08bd-4cd9-b85d-3f391f203554");
//                    bundle.putString("type","1");
                    showActivity(getActivity(), MyWantToothActivity.class,bundle);
                } else if (pi.getName().equals("我要种牙")) { //跟镶牙界面一样
                    Bundle bundle=new Bundle();
                    bundle.putString("guid","b9f7ce10-eb24-46ba-a5bd-a7c99fe3b377");
//                    bundle.putString("type","2");
                    showActivity(getActivity(), MyWantToothActivity.class,bundle);
                } else if (pi.getName().equals("其他项目")) {
                    showActivity(getActivity(), ElseProjectActivity.class);
                } else if (pi.getName().equals("找门诊")) {
                    showActivity(getActivity(), OutpatientActivity.class);
                } else if (pi.getName().equals("找专家")) {
                    showActivity(getActivity(), SeekSpecialistActivity.class);
                } else if (pi.getName().equals("客服")) {
                    showActivity(getActivity(), ElseProjectActivity.class);
                } else if (pi.getName().equals("门诊寻求合作")) {
                    Bundle bundle=new Bundle();
                    bundle.putString("guid","b9f7ce10-eb24-46ba-a5bd-a7c99fe3b377");
                    showActivity(getActivity(), MyWantToothActivity.class,bundle);
                } else if (pi.getName().equals("积分商城")) {
                    showActivity(getActivity(), IntegralStoreActivity.class);
                }
                
//                ToastUtils.showToastLong(getActivity(), "值" + pi.getName() + i);
            }
        });
    }

    /**
     * 附近的店的数据显示
     *
     * @param
     */
    private NearbyRespBean.NearbyBeanList mNearbyBeanList;

    public void setListViewData(final List<NearbyRespBean.NearbyBeanList> mNearbyList) {
        mListviewAdapter = new CommonAdapter<NearbyRespBean.NearbyBeanList>(getActivity(), mNearbyList,
                R.layout.home_nearby_item) {
            @Override
            public void convert(ViewHolder helper, NearbyRespBean.NearbyBeanList item,int position) {
                mNearbyBeanList = item;
                Glide.with(getActivity()).load(item.getContent()).error(R.mipmap.home_seach).into((ImageView) helper.getView(R.id.iv_nearby));
                // 名称
                helper.setText(R.id.tv_nearby_name, item.getTitle());
//                //地址
                helper.setText(R.id.tv_nearby_address, item.getArea() + item.getAddress());
                helper.setText(R.id.tv_nearby_grade, item.getStars());

//                RatingBar ratingBar = helper.getView(R.id.rb_nearby);
//                ratingBar.setClickable(false);
//                Log.i(Tag,"星星"+item.getStars());
//                if(item.getStars()!=""){
//                    ratingBar.setStar(JavaUtils.convertToFloat(item.getStars()));
//                }

            }
        };
        homeListview.setAdapter(mListviewAdapter);
        homeListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                Toast.makeText(getActivity(), "你点击了：" + position, Toast.LENGTH_SHORT).show();
                //进入门诊详情
                Bundle bundle = new Bundle();
                bundle.putString(IntentParameter.ClinicName, mNearbyBeanList.getTitle());
                bundle.putString(IntentParameter.ClinicPic, mNearbyBeanList.getContent());
                bundle.putString(IntentParameter.ClinicLocation, mNearbyBeanList.getArea() + mNearbyBeanList.getAddress());
                bundle.putString(IntentParameter.Clinicjl, mNearbyBeanList.getStars());
                showActivity(getActivity(), ClinicContentActivity.class, bundle);
            }
        });

    }

    /**
     * 附近的店的请求数据
     */
    public void getNearByData() {
        BaseNetEntity baseNetEntity = new BaseNetEntity();
        baseNetEntity.get(getActivity(), ServiceUrl.Get_Nearby_Store, "正在加载数据", true, new OkHttpResponseCallback<NearbyRespBean>(NearbyRespBean.class) {
            @Override
            public void onSuccess(NearbyRespBean bean) {
                Log.i("INFO", "bean信息" + bean.getData().get(0).toString());
                mNearbyBean = bean;
                setNearbyValues();

                ToastUtils.showToastLong(getActivity(), "请求网络数据成功");
            }

            @Override
            public void onFailError(Exception e) {
                ToastUtils.showToastLong(getActivity(), "失败" + e);
            }
        });
    }

    /**
     * 设置gv文字和图片的参数
     *
     * @return
     */
    public void setNearbyValues() {
        mList.addAll(mNearbyBean.getData());
        //设置界面参数值
        setListViewData(mList);
    }

    @Override
    public void OnBannerClick(int position) {
        Toast.makeText(getActivity(), "你点击了：" + position, Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.tv_city, R.id.rl_health_knowledge, R.id.ll_nearby,R.id.ll_all_recode,R.id.iv_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_city:
                showActivity(getActivity(), PresentCityActivity.class);
                break;
            case R.id.rl_health_knowledge:
                showActivity(getActivity(), HealthKnowledgeActivity.class);
                break;
            case R.id.ll_all_recode:
                showActivity(getActivity(), NearbyShopActivity.class);
                break;
            case R.id.iv_search:
                showActivity(getActivity(), SearchActivity.class);
                break;
        }
    }


    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            /**
             注意：
             1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
             2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
             传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
             切记不要胡乱强转！
             */

            //Glide 加载图片简单用法
            Glide.with(context).load(path).into(imageView);

            //Picasso 加载图片简单用法
            Glide.with(context).load(path).into(imageView);

            //用fresco加载图片简单用法，记得要写下面的createImageView方法
            Uri uri = Uri.parse((String) path);
            imageView.setImageURI(uri);
        }

//        //提供createImageView 方法，如果不用可以不重写这个方法，主要是方便自定义ImageView的创建
//        @Override
//        public ImageView createImageView(Context context) {
//            //使用fresco，需要创建它提供的ImageView，当然你也可以用自己自定义的具有图片加载功能的ImageView
//            SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context);
//            return simpleDraweeView;
//        }
    }
}
