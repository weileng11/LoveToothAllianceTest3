package com.lovetoothalliance.app.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lovetoothalliance.app.R;

/**
 * Author:lt
 * time:2017/8/7.
 * contact：weileng143@163.com
 *
 * @description
 */

public class LocationDialog extends Dialog{
    private TextView tv;

    public LocationDialog(Context context) {
        super(context, R.style.loadingDialogStyle);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loaction);
        tv = (TextView)findViewById(R.id.tv);
        tv.setText("正在定位,请稍候.....");
        LinearLayout linearLayout = (LinearLayout)this.findViewById(R.id.LinearLayout);
        linearLayout.getBackground().setAlpha(210);
    }


}
