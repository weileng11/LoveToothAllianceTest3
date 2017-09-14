package com.lovetoothalliance.app.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.lovetoothalliance.app.R;

/**
 * Author:lt
 * time:2017/7/27.
 * contact：weileng143@163.com
 *
 * @description 分享
 */

public class ShareDialog extends Dialog {
    private Context context;

    public LinearLayout llShareCopy, llShareWxFriend, llSharWxPyCircle, llShareQQFriend;
    public Button mBtnCancel;

    public shareListenter mListenter;

    public interface shareListenter {
        void shareCopy();

        void shareWxFriend();

        void shareWxPyCircle();

        void sharQQFriend();
    }


    public ShareDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public ShareDialog(@NonNull Context context, @Nullable shareListenter mListenterr) {
        super(context, R.style.MyDialog_three);
        this.context = context;
        this.mListenter = mListenterr;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog_share);
        llShareCopy = (LinearLayout) findViewById(R.id.ll_share_copy);
        llShareWxFriend = (LinearLayout) findViewById(R.id.ll_share_wxfriend);
        llSharWxPyCircle = (LinearLayout) findViewById(R.id.ll_share_wxpy);
        llShareQQFriend = (LinearLayout) findViewById(R.id.ll_share_qq);
        mBtnCancel=(Button)findViewById(R.id.share_cancel);
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 dismiss();
            }
        });

        llShareCopy.setOnClickListener(new shareOnClick());
        llShareWxFriend.setOnClickListener(new shareOnClick());
        llSharWxPyCircle.setOnClickListener(new shareOnClick());
        llShareQQFriend.setOnClickListener(new shareOnClick());

    }

    public class shareOnClick implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.ll_share_copy:
                    mListenter.shareCopy();
                    break;
                case R.id.ll_share_wxfriend:
                    mListenter.shareWxFriend();
                    break;
                case R.id.ll_share_wxpy:
                    mListenter.shareWxPyCircle();
                    break;
                case R.id.ll_share_qq:
                    mListenter.sharQQFriend();
                    break;

            }
        }
    }
}
