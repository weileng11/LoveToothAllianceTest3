package com.lovetoothalliance.app.fragment;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lovetoothalliance.app.R;
import com.lovetoothalliance.app.adapter.CommonAdapter;
import com.lovetoothalliance.app.adapter.ViewHolder;
import com.lovetoothalliance.app.bean.PublicInfo;
import com.lovetoothalliance.app.constant.ServiceUrl;
import com.lovetoothalliance.app.net.BaseNetEntity;
import com.lovetoothalliance.app.net.OkHttpResponseCallback;
import com.lovetoothalliance.app.net.bean.RespnoseResultBean;
import com.lovetoothalliance.app.net.bean.response.UploadPictureRespBean;
import com.lovetoothalliance.app.ui.AccountSafetyActivity;
import com.lovetoothalliance.app.ui.ClipImageActivity;
import com.lovetoothalliance.app.ui.CounselActivity;
import com.lovetoothalliance.app.ui.MyTeamActivity;
import com.lovetoothalliance.app.ui.MyWalletActivity;
import com.lovetoothalliance.app.ui.PersonalInformationActivity;
import com.lovetoothalliance.app.ui.SettingActivity;
import com.lovetoothalliance.app.ui.UpdateUserNameActivity;
import com.lovetoothalliance.app.widget.TopBarLayout;
import com.utils.SharedPreferencesUtils;
import com.utils.view.MyGridView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static android.app.Activity.RESULT_OK;

/**
 * Author:lt
 * time:2017/7/17.
 * contact：weileng143@163.com
 *
 * @description 个人中心
 */

public class MyFragment extends BaseFragment {

    private String Tag="MyFragment";
    @InjectView(R.id.topbar)
    TopBarLayout topbar;
    @InjectView(R.id.img_head)
    ImageView imgHead;
    @InjectView(R.id.mygridview)
    MyGridView mygridview;
    @InjectView(R.id.tv_user_name)
    TextView tvUserName;

    private String[] personalList = new String[]{"我的团队", "我的钱包", "法律顾问", "账号安全", "个人信息"};
    private int[] ivList = new int[]{R.mipmap.iv_personal, R.mipmap.iv_wallet,
            R.mipmap.iv_fl, R.mipmap.iv_safe, R.mipmap.iv_information};

    private BaseAdapter mBaseAdapter;
    private ArrayList<PublicInfo> pList = new ArrayList<>();
    private UploadPictureRespBean u;
    private Handler mHandler = new Handler();
    private MyRunnable mr = new MyRunnable();

    class MyRunnable implements Runnable {
        public void run() {
            System.out.println("上传照片成功：response666666 = ");
            if (type == 1) {
//                        imgHead.setImageBitmap(bitMap);
            } else {
                //更新个人信息
                UpdatePersonPhoto(u.data.data.get(0));
            }
        }
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        topbar.setTxvTitleName("个人中心");
        topbar.setTxvLeftShow();
        topbar.setRigthViewTypeMode(TopBarLayout.RightViewTypeMode.ADD);
        topbar.setBtnRightDrawable(R.mipmap.setting);
        topbar.setRightTxvOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到设置界面
                showActivity(getActivity(), SettingActivity.class);
            }
        });
        //设置登录时候的头像
        if (SharedPreferencesUtils.getString(getActivity(), "headimg", "") != null ||
                !SharedPreferencesUtils.getString(getActivity(), "headimg", "").equals("")) {
            Glide.with(getActivity()).load(SharedPreferencesUtils.getString(getActivity(), "headimg", "")).error(R.mipmap.iv_head).into(imgHead);
        }

        for (int i = 0; i < personalList.length; i++) {
            PublicInfo pi = new PublicInfo(personalList[i], ivList[i]);
            pList.add(pi);
        }
        Log.i("INFO", "总数据" + pList.size());
        //设置界面参数值
        setInterfaceParams(pList);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(Tag,"onResume");
        //设置名字
        if (SharedPreferencesUtils.getString(getActivity(), "myname", "") != null ||
                !SharedPreferencesUtils.getString(getActivity(), "myname", "").equals("")) {
            tvUserName.setText(SharedPreferencesUtils.getString(getActivity(), "myname", ""));
        }
    }

    public void setInterfaceParams(ArrayList<PublicInfo> mPersonList) {
        mBaseAdapter = new CommonAdapter<PublicInfo>(getActivity(), mPersonList,
                R.layout.personal_item) {
            @Override
            public void convert(ViewHolder helper, PublicInfo item, int position) {
                // 名称
                helper.setText(R.id.tv_content, item.getName());
                //图片
                helper.setImageBackground(R.id.iv_item, item.getIvContent());
            }
        };
        mygridview.setAdapter(mBaseAdapter);
        mygridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                PublicInfo pi = (PublicInfo) mBaseAdapter.getItem(i);
                if (pi.getName().equals("我的团队")) {
                    showActivity(getActivity(), MyTeamActivity.class);
                } else if (pi.getName().equals("我的钱包")) {
                    showActivity(getActivity(), MyWalletActivity.class);
                } else if (pi.getName().equals("法律顾问")) {
                    showActivity(getActivity(), CounselActivity.class);
                } else if (pi.getName().equals("账号安全")) {
                    showActivity(getActivity(), AccountSafetyActivity.class);
                } else if (pi.getName().equals("个人信息")) {
                    showActivity(getActivity(), PersonalInformationActivity.class);
                }
            }
        });
    }


    @OnClick({R.id.img_head, R.id.tv_user_name})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_head:
                //弹出对话框
                type = 2;
                uploadHeadImage();
                break;
            case R.id.tv_user_name:
                showActivity(getActivity(), UpdateUserNameActivity.class);
                break;
        }
    }

        //请求相机
        private static final int REQUEST_CAPTURE = 100;
        //请求相册
        private static final int REQUEST_PICK = 101;
        //请求截图
        private static final int REQUEST_CROP_PHOTO = 102;
        //请求访问外部存储
        private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 103;
        //请求写入外部存储
        private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 104;
        //调用照相机返回图片文件
        private File tempFile;
        // 1: qq, 2: weixin
        private int type;

        /**
         * 上传头像
         */
        private void uploadHeadImage() {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_popupwindow, null);
            TextView btnCarema = (TextView) view.findViewById(R.id.btn_camera);
            TextView btnPhoto = (TextView) view.findViewById(R.id.btn_photo);
            TextView btnCancel = (TextView) view.findViewById(R.id.btn_cancel);
            final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            popupWindow.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
            popupWindow.setOutsideTouchable(true);
            View parent = LayoutInflater.from(getActivity()).inflate(R.layout.activity_main, null);
            popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
            //popupWindow在弹窗的时候背景半透明
            final WindowManager.LayoutParams params = getActivity().getWindow().getAttributes();
            params.alpha = 0.5f;
            getActivity().getWindow().setAttributes(params);
            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    params.alpha = 1.0f;
                    getActivity().getWindow().setAttributes(params);
                }
            });

            btnCarema.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //权限判断
                    if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        //申请WRITE_EXTERNAL_STORAGE权限
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
                    } else {
                        //跳转到调用系统相机
                        gotoCamera();
                    }
                    popupWindow.dismiss();
                }
            });
            btnPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //权限判断
                    if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        //申请READ_EXTERNAL_STORAGE权限
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                READ_EXTERNAL_STORAGE_REQUEST_CODE);
                    } else {
                        //跳转到相册
                        gotoPhoto();
                    }
                    popupWindow.dismiss();
                }
            });
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }
            });
        }

        /**
         * 外部存储权限申请返回
         */
        @Override
        public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    gotoCamera();
                }
            } else if (requestCode == READ_EXTERNAL_STORAGE_REQUEST_CODE) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    gotoPhoto();
                }
            }
        }


        /**
         * 跳转到相册
         */
        private void gotoPhoto() {
            Log.d("evan", "*****************打开图库********************");
            //跳转到调用系统图库
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(Intent.createChooser(intent, "请选择图片"), REQUEST_PICK);
        }


        /**
         * 跳转到照相机
         */
        private void gotoCamera() {
            Log.d("evan", "*****************打开相机********************");
            //创建拍照存储的图片文件
            tempFile = new File(checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/image/"), System.currentTimeMillis() + ".jpg");

            //跳转到调用系统相机
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                //设置7.0中共享文件，分享路径定义在xml/file_paths.xml
                intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                Uri contentUri = FileProvider.getUriForFile(getActivity(), ".fileProvider", tempFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
            } else {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
            }
            startActivityForResult(intent, REQUEST_CAPTURE);
        }


        /**
         * 检查文件是否存在
         */
        private static String checkDirPath(String dirPath) {
            if (TextUtils.isEmpty(dirPath)) {
                return "";
            }
            File dir = new File(dirPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            return dirPath;
        }


        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent intent) {
            switch (requestCode) {
                case REQUEST_CAPTURE: //调用系统相机返回
                    if (resultCode == RESULT_OK) {
                        Log.d("evan", "**********camera uri*******" + Uri.fromFile(tempFile).toString());
                        Log.d("evan", "**********camera path*******" + getRealFilePathFromUri(getActivity(), Uri.fromFile(tempFile)));
                        gotoClipActivity(Uri.fromFile(tempFile));
                    }
                    break;
                case REQUEST_PICK:  //调用系统相册返回
                    if (resultCode == RESULT_OK) {
                        Uri uri = intent.getData();
                        Log.d("evan", "**********pick path*******" + getRealFilePathFromUri(getActivity(), uri));
                        gotoClipActivity(uri);
                    }
                    break;
                case REQUEST_CROP_PHOTO:  //剪切图片返回
                    if (resultCode == RESULT_OK) {
                        final Uri uri = intent.getData();
                        if (uri == null) {
                            return;
                        }
                        Log.i("INFO", "上传图片的路径" + uri);
                        Log.i("INFO", "sdk" + Environment.getExternalStorageDirectory());
                        String cropImagePath = getRealFilePathFromUri(getActivity(), uri);
                        Log.i("INFO", "上传图片的路径2222" + cropImagePath);
                        Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
                        File file = new File(cropImagePath);
                        Log.i("INFO", "上传图片的路径5555" + file);
    //                    Uri uri1=Uri.fromFile(new File(cropImagePath));

                        //此处后面可以将bitMap转为二进制上传后台网络
                        //根据路径上传图片
                        BaseNetEntity baseNetEntity = new BaseNetEntity();
                        baseNetEntity.uploadOneImg(cropImagePath, ServiceUrl.Upload_More_Pic, new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                //特别注意:response.body().string()只会被执行一次，在第一次必须要用string接收，否则
                                //会出现null,坑了一波
                                String str = response.body().string();
                                //开始转换
                                u = BaseNetEntity.JSONToObject(str, UploadPictureRespBean.class);
    //                            //设置图片参数
    //                            uploadSuccessPic.addAll(u.data.data);
                                mHandler.postDelayed(mr, 1000);
                                System.out.println("上传照片成功：response = " + u.data.data.get(0));

                            }
                        });

                    }
                    break;
            }
        }

        /**
         * 更新头像
         */
        public void UpdatePersonPhoto(String headImgUrl) {
            //开始提交订单
            BaseNetEntity baseNetEntity = new BaseNetEntity();
            //这里将对象转换成为字符串
            String mPersonInfoCommitUrl = ServiceUrl.Commit_PersonInfo +
                    SharedPreferencesUtils.getString(getActivity(), "memlogin", "") +
                    "&Photo=" + headImgUrl;
            baseNetEntity.get(getActivity(), mPersonInfoCommitUrl, "正在加载数据", true,
                    new OkHttpResponseCallback<RespnoseResultBean>(RespnoseResultBean.class) {
                        @Override
                        public void onSuccess(RespnoseResultBean bean) {
                            Log.i("INFO", "更新头像成功");
                            Glide.with(getActivity()).load(u.data.data.get(0)).error(R.mipmap.iv_head).into(imgHead);
                        }

                        @Override
                        public void onFailError(Exception e) {

                        }
                    });
        }


        /**
         * 打开截图界面
         */
        public void gotoClipActivity(Uri uri) {
            if (uri == null) {
                return;
            }
            Intent intent = new Intent();
            intent.setClass(getActivity(), ClipImageActivity.class);
            intent.putExtra("type", type);
            intent.setData(uri);
            startActivityForResult(intent, REQUEST_CROP_PHOTO);
        }


        /**
         * 根据Uri返回文件绝对路径
         * 兼容了file:///开头的 和 content://开头的情况
         */
        public static String getRealFilePathFromUri(final Context context, final Uri uri) {
            if (null == uri) return null;
            final String scheme = uri.getScheme();
            String data = null;
            if (scheme == null)
                data = uri.getPath();
            else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
                data = uri.getPath();
            } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
                Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
                if (null != cursor) {
                    if (cursor.moveToFirst()) {
                        int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                        if (index > -1) {
                            data = cursor.getString(index);
                        }
                    }
                    cursor.close();
                }
            }
            return data;
        }

}
