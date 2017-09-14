package com.lovetoothalliance.app.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lovetoothalliance.app.R;


/**
 * Author:lt
 * time:2017/7/18.
 * contact：weileng143@163.com
 *
 * @description
 */
public class TopBarLayout extends LinearLayout {


//    @InjectView(R.id.back_img)
//    ImageView backImg;
//    @InjectView(R.id.ll_back)
//    LinearLayout llBack;
//    @InjectView(R.id.txv_title)
//    TextView txvTitle;
//    @InjectView(R.id.txv_right)
//    TextView txvRight;
    private View view;
    //标题
    private TextView txvTitle;
    //图片
    private ImageView backImg;
    //返回
    private LinearLayout  llBack;
    //右边的图片
    private TextView txvRight;
    //右边的btn
    private Button mBtnRight;

    public TopBarLayout(Context context) {
        super(context);
    }

    public TopBarLayout(final Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(R.layout.topbar_layout, this);

        txvTitle=(TextView) view.findViewById(R.id.txv_title);
        txvRight=(TextView) view.findViewById(R.id.txv_right);
        backImg=(ImageView) view.findViewById(R.id.back_img);
        llBack=(LinearLayout) view.findViewById(R.id.ll_back);
        mBtnRight=(Button)view.findViewById(R.id.btn_right);
        setLeftTxvOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if(context instanceof Activity){
                    ((Activity)context).finish();
                }
            }
        });

    }

    /**
     * 设置右边是否显示
     */
    public void setTxvRightShow(){
       txvRight.setVisibility(View.VISIBLE);
    }

    /**
     * 设置左边是否显示
     */
    public void setTxvLeftShow(){
        llBack.setVisibility(View.GONE);
    }


    /**
     * 设置标题的内容
     */
    public void setTxvTitleName(String txvTitleName){
        txvTitle.setText(txvTitleName);
    }

    /**
     * 设置左边键按钮的监听
     * @param onClickListener
     */
    public void setLeftTxvOnClickListener(OnClickListener onClickListener){
        llBack.setOnClickListener(onClickListener);
    }

    /**
     * 设置右边文字的显示内容
     */
    public void setTxvRightName(String txvRightName){
        txvRight.setText(txvRightName);
    }

    /**
     * 设置右边按钮的图片
     */
    public void setBtnRightDrawable(int mRightDrawable){
        mBtnRight.setBackgroundResource(mRightDrawable);
    }

    /**
     * 设置右边文字的监听事件
     */
    public void setTxvRightOnClickListenter(OnClickListener onClickListener){
        txvRight.setOnClickListener(onClickListener);
    }

    private RightViewTypeMode nowMode;

    /**
     * 设置右边按钮的 模式
     * TEXT 文本
     * SCAN 扫描图标
     * ADD  添加图标
     * @param mode
     */
    public void setRigthViewTypeMode(RightViewTypeMode mode){
        nowMode=mode;
        if(RightViewTypeMode.TEXT==mode){//文本
            txvRight.setVisibility(View.VISIBLE);
            txvRight.setBackgroundColor(Color.TRANSPARENT);
        }else if(RightViewTypeMode.SCAN==mode){//扫描
            mBtnRight.setVisibility(View.VISIBLE);
        }else if(RightViewTypeMode.ADD==mode){//添加
            mBtnRight.setVisibility(View.VISIBLE);
//			rightIm.setImageResource(R.drawable.mk_topbar_add);
        }
    }

    /**
     * 设置右边按钮的监听
     * @param onClickListener
     */
    public void setRightTxvOnClickListener(View.OnClickListener onClickListener){
        if(nowMode==RightViewTypeMode.TEXT){
            txvRight.setOnClickListener(onClickListener);
        }else{
            mBtnRight.setOnClickListener(onClickListener);
        }
    }

    /**
     * @author lt
     * @date
     * @ClassName: RightViewTypeMode
     * @Description: 右边按钮的模式
     */
    public enum RightViewTypeMode{
        /**
         * 扫描
         */
        SCAN,
        /**
         * 文本
         */
        TEXT,
        /**
         * 添加
         */
        ADD
    }
}
