package com.lovetoothalliance.app.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;

import com.lovetoothalliance.app.R;

/**
 * Author:lt
 * time:2017/7/25.
 * contact：weileng143@163.com
 *
 * @description
 */

public class ExitDialog extends Dialog{

    private Context context;

    private OnExitListenter listener;

    private Button okBtn;

    private Button cancelBtn;

    public interface  OnExitListenter{
        void onExitOk();
        void onExitCancel();
    }

    public ExitDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }


    public ExitDialog(Context context, OnExitListenter listener) {
        super(context, R.style.MyDialog_three);
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog_exit);

        okBtn = (Button) findViewById(R.id.btn_ok);
        cancelBtn = (Button) findViewById(R.id.btn_cancel);

        okBtn.setOnClickListener(new MyOnClickListener());
        cancelBtn.setOnClickListener(new MyOnClickListener());
    }

    public class MyOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_cancel:
                    listener.onExitCancel();
                    dismiss();
                    break;
                case R.id.btn_ok:
                    listener.onExitOk();
                    dismiss();
                    break;

                default:
                    break;
            }
        }

    }
}
