package com.lovetoothalliance.app.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.lovetoothalliance.app.R;

/**
 * Author:lt
 * time:2017/7/27.
 * contact：weileng143@163.com
 *
 * @description
 */

public class SignDialog extends Dialog {

    public Context context;

    public Button btnColseSign;
    private ImageView mIvSign;
    public signListenter mListenter;
    //根据type来选择签到失败还是成功图片
    private int mtype=0;

    public interface signListenter{
        void timerColseSign();
    }

    public SignDialog(@NonNull Context context) {
        super(context);
        this.context=context;
    }

    public SignDialog(@NonNull Context context, signListenter mListenter,int type) {
        super(context, R.style.MyDialog_three);
        this.context=context;
        this.mListenter=mListenter;
        this.mtype=type;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog_sign);
        btnColseSign=(Button)findViewById(R.id.btn_colse_sign);
        mIvSign=(ImageView)findViewById(R.id.iv_sign);
        if(mtype==1){
            mIvSign.setBackgroundResource(R.mipmap.jf_sgin_ok);
        }else if(mtype==2){
            mIvSign.setBackgroundResource(R.mipmap.jf_sgin_fail);
        }
        btnColseSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        mListenter.timerColseSign();
    }
}
