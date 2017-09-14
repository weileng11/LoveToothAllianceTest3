package com.lovetoothalliance.app.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ikkong.wximagepicker.ImagePickerAdapter;
import com.ikkong.wximagepicker.ImagePickerConstants;
import com.lovetoothalliance.app.R;
import com.lovetoothalliance.app.constant.ServiceUrl;
import com.lovetoothalliance.app.fragment.BaseRecordFragment;
import com.lovetoothalliance.app.net.BaseNetEntity;
import com.lovetoothalliance.app.net.OkHttpResponseCallback;
import com.lovetoothalliance.app.net.bean.RespnoseResultBean;
import com.lovetoothalliance.app.net.bean.response.UploadPictureRespBean;
import com.lovetoothalliance.app.widget.TopBarLayout;
import com.lovetoothalliance.app.widget.WrapHeightGridLayoutManager;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.loader.GlideImageLoader;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.utils.ImagesCompress;
import com.utils.SharedPreferencesUtils;
import com.utils.ToastUtils;
import com.utils.view.RatingBar;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Author:lt
 * time:2017/7/21.
 * contact：weileng143@163.com
 *
 * @description 诊断记录的评价
 */

public class EvaluateActivity extends BaseActivity implements ImagePickerAdapter.OnRecyclerViewItemClickListener {
    private static String Tag = "EvaluateActivity";

    @InjectView(R.id.topbar)
    TopBarLayout topbar;
    @InjectView(R.id.iv_evaluate)
    ImageView ivEvaluate;
    @InjectView(R.id.rb_describe)
    RatingBar rbDescribe;
    @InjectView(R.id.ed_evaluate)
    EditText edEvaluate;
    @InjectView(R.id.btn_add_evaluate)
    Button btnAddEvaluate; //添加晒单
    @InjectView(R.id.rb_service)
    RatingBar rbService;
    @InjectView(R.id.rb_deliver)
    RatingBar rbDeliver;
    @InjectView(R.id.btn_evaluate_commit)
    Button btnEvaluateCommit;

    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    @InjectView(R.id.tv_evaluate_name)
    TextView tvEvaluateName;
    @InjectView(R.id.tv_shopname)
    TextView tvShopname;
    @InjectView(R.id.tv_evaluate_price)
    TextView tvEvaluatePrice;
    @InjectView(R.id.textView)
    TextView textView;
    @InjectView(R.id.iv_ish)
    CheckBox ivIsh;

    private ImagePickerAdapter adapter;
    private ArrayList<ImageItem> selImageList; //当前选择的所有图片
    private int maxImgCount = 9;               //允许选择图片最大数
    private RecyclerView recyclerView;
    private ImagePicker imagePicker;  //查看图片

    private String shopName; //商品名称
    private String proguid;   //proguid
    private String orderNumber;
    private String attributes; //裤子 28
    private String OriginalImge; //图片
    private Double buyPrice; //价格
    private int level = 4; //描述相符
    private int level1 = 4; //服务态度
    private int level2 = 4; //发货的速度
    private String content; //评论的内容
    private int isH; //是否匿名
    private String evaluateImage; //将图片集合转换成为字符串

    //    private List<File> evaluateImageList = new ArrayList<>();
    private List<String> evaluateImageList = new ArrayList<>();
    private List<String> uploadSuccessPic = new ArrayList<>();
    private ArrayList<ImageItem> images;
    private Handler mHandler = new Handler();
    private MyRunnable mr = new MyRunnable();

    class MyRunnable implements Runnable {
        public void run() {
            System.out.println("上传照片成功：response666666 = ");
            //设置适配参数显示
            selImageList.addAll(images);
            adapter.setImages(selImageList);
        }
    }


    @Override
    protected void initView() {
        setContentView(R.layout.activity_evaluate);
    }

    @Override
    protected void initData() {
        //获取bundle数据
        Bundle bundle = getIntent().getExtras();
        shopName = bundle.getString("name");
        proguid = bundle.getString("proguid");
        orderNumber = bundle.getString("ordernumber");
        attributes = bundle.getString("attributes");
        OriginalImge = bundle.getString("OriginalImge");
        buyPrice = bundle.getDouble("BuyPrice");
        Log.i(Tag, "评价" + shopName + proguid + orderNumber + attributes);
        topbar.setTxvTitleName("评价");
        //设置参数显示
        setInitParams();
        //监听星星
        setStartListenter();
        //设置具体模式
        setPattern();
        //初始选择图片的数据
        initWidget();
    }

    @OnClick(R.id.btn_evaluate_commit)
    public void onClick() {
        content = edEvaluate.getText().toString();
        if (!content.equals("")) {
            getEvaluateData();
        } else {
            ToastUtils.showToastLong(this, "内容不能为空");
        }
    }

    /**
     * 设置初始参数
     */
    public void setInitParams() {
        tvEvaluateName.setText(shopName);
        tvShopname.setText(shopName);
        Glide.with(EvaluateActivity.this).load(OriginalImge).into(ivEvaluate);
        tvEvaluatePrice.setText("¥" + String.valueOf(buyPrice));
    }

    /**
     * list图片转换成字符串
     */
    public String setPicChangeToString() {
        evaluateImage = "";
        //试卷图片
        if (uploadSuccessPic.size() > 0) {
            for (int i = 0; i < uploadSuccessPic.size(); i++) {
                evaluateImage = evaluateImage + uploadSuccessPic.get(i);
                if (i < (uploadSuccessPic.size() - 1)) {
                    evaluateImage = evaluateImage + ",";
                }
            }

            Log.i(Tag,"图片string"+evaluateImage);
        }
        return evaluateImage;
    }

    /**
     * 提交评价
     */
    public void getEvaluateData() {
        //转换图片
        evaluateImage=setPicChangeToString();
        String evaluateUrl = ServiceUrl.Get_Evaluate_Commit + "level=" + level + "&level2="
                + level1 + "&leve3=" + level2 + "&attributes=" + attributes + "&img=" + evaluateImage +
                "&isAnonymous=" + isH + "&content=" + content + "&Guid=" + proguid
                + "&u=" + SharedPreferencesUtils.getString(this, "memlogin", "") + "&orderNumber=" + orderNumber + "&name=" + shopName;
        Log.i(Tag, "路径" + evaluateUrl);
        BaseNetEntity baseNetEntity = new BaseNetEntity();
        baseNetEntity.get(EvaluateActivity.this, evaluateUrl, "正在提交评论",
                true, new OkHttpResponseCallback<RespnoseResultBean>(RespnoseResultBean.class) {
                    @Override
                    public void onSuccess(RespnoseResultBean bean) {
                        if (bean.data.Stater == 1) {
                            ToastUtils.showToastLong(EvaluateActivity.this, "提交评论成功");
                            if(BaseRecordFragment.getBackType()==1){
                                BaseRecordFragment.isAllEvaluateOk=true;
                            }else{
                                BaseRecordFragment.isEvaluateOk=true;
                            }
                            finish();
                        }
                    }

                    @Override
                    public void onFailError(Exception e) {

                    }
                });
    }

    /**
     * 监听星星
     */
    public void setStartListenter() {

        rbDescribe.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float ratingCount) {
                Log.i(Tag, "星星1" + ratingCount);
                level = (int) ratingCount;
            }
        });
        rbService.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float ratingCount) {
                Log.i(Tag, "星星2" + ratingCount);
                level1 = (int) ratingCount;
            }
        });
        rbDeliver.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float ratingCount) {
                Log.i(Tag, "星星3" + ratingCount);
                level2 = (int) ratingCount;
            }
        });
        ivIsh.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isH = 1; //匿名是为1，否为0传参
                } else {
                    isH = 0;
                }
            }
        });
    }

    /**
     * 设置具体的模式
     */

    public void setPattern() {
        imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(GlideImageLoader.getInstance());
        imagePicker.setMultiMode(true);

        //是否显示相机
        imagePicker.setShowCamera(true);
        imagePicker.setCrop(true);
        imagePicker.setSaveRectangle(true);
    }

    /**
     * 设置选择图片的参数
     */
    private void initWidget() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter(this, selImageList, maxImgCount);
        adapter.setOnItemClickListener(this);

        recyclerView.setLayoutManager(new WrapHeightGridLayoutManager(this, 4));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position, int adapterTag) {
        switch (position) {
            case ImagePickerConstants.IMAGE_ITEM_ADD:
                //打开选择,本次允许选择的数量
                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                Intent intent = new Intent(this, ImageGridActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SELECT);
                break;
            default:
                //打开预览(查看图片)
                Intent intentPreview = new Intent(this, ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                for (int i = 0; i < images.size(); i++) {
                    Log.i("info", "图片路径" + images.get(i).path);
//                    getimage(images.get(i).path);
//                    compressImage(Uri.parse(images.get(i).path));
                    setImagesCompress(images.get(i).path);
//                    File f=compress(images.get(i).path);
//                    System.out.println("图片路径222222:"+"\n"+f.getPath());
                }
                  //压缩完毕开始上传图片
                  uploadEvaluatePic();

//                selImageList.addAll(images);
//                adapter.setImages(selImageList);
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                selImageList.clear();
                selImageList.addAll(images);
                adapter.setImages(selImageList);
            }
        }
    }

    private Uri uri;  //图片保存uri
    private File scaledFile;
    public void setImagesCompress(String path) {
        File imageFile = new File(path);
        uri = Uri.fromFile(imageFile);
        scaledFile = ImagesCompress.scal(uri);
//      Bitmap bitmap = BitmapFactory.decodeFile(scaledFile.getAbsolutePath());
//      mImageView.setImageBitmap(bitmap);
        float oldSize = (float) new File(uri.getPath()).length() / 1024 / 1024;   //以文件的形式
        float newSize = (float) scaledFile.length() / 1024;
        String evaluateUrl = scaledFile.getPath();
        String mCurrentPhotoPath = uri.getPath();
//      Log.e("图片路径:"+"\n"+mCurrentPhotoPath+"\n"+"文件大小:"+oldSize+"M\n"+"压缩后的大小:"+newSize+"KB");
        System.out.println("图片路径:" + "\n" + mCurrentPhotoPath + "\n" + "文件大小:" + oldSize + "M\n" + "压缩后的大小:" + newSize + "KB\n");
        Log.i("INFO", "要上传的地址" + evaluateUrl);
        if (evaluateUrl != null && !evaluateUrl.equals("")) {
            evaluateImageList.add(evaluateUrl);
        }
    }

    /**
     * 开始图片上传
     */
    public void uploadEvaluatePic() {
        BaseNetEntity baseNetEntity = new BaseNetEntity();
        baseNetEntity.uploadImg(evaluateImageList, ServiceUrl.Upload_More_Pic, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                System.out.println("上传失败:e.getLocalizedMessage() = " + e.getLocalizedMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //特别注意:response.body().string()只会被执行一次，在第一次必须要用string接收，否则
                //会出现null,坑了一波
                String str = response.body().string();
                //开始转换
                UploadPictureRespBean u = BaseNetEntity.JSONToObject(str, UploadPictureRespBean.class);
                //设置图片参数
                uploadSuccessPic.addAll(u.data.data);
                mHandler.postDelayed(mr, 1000);
                System.out.println("上传照片成功：response = " + u.data.Message);
            }
        });
        Log.i(Tag, "地址" + ServiceUrl.Upload_More_Pic);
    }
}

