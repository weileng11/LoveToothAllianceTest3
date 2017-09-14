package com.lovetoothalliance.app.ui;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.lovetoothalliance.app.R;
import com.lovetoothalliance.app.fragment.HomeFragment;
import com.lovetoothalliance.app.fragment.MessageFragment;
import com.lovetoothalliance.app.fragment.MyFragment;
import com.lovetoothalliance.app.fragment.RecordFragment;
import com.lovetoothalliance.app.widget.HomeViewPager;

import java.util.ArrayList;

import butterknife.InjectView;

/**
 * 首页
 */
public class MainActivity extends BaseActivity {


    @InjectView(R.id.rd_group)
    RadioGroup mGroup;
    @InjectView(R.id.m_viewpager)
    HomeViewPager mPager;
    @InjectView(R.id.rb_index)
    RadioButton rbIndex;
    @InjectView(R.id.rb_record)
    RadioButton rbRecord;
    @InjectView(R.id.rb_message)
    RadioButton rbMessage;
    @InjectView(R.id.rb_my)
    RadioButton rbMy;
    private HomeFragment mHomeFg;
    private MessageFragment mMessageFg;
    private MyFragment mMyFg;
    private RecordFragment mRdFg;

    private ArrayList<Fragment> fragmentList;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void initData() {
        //控制底部栏图标大小
//        Drawable drawable1 = getResources().getDrawable(R.drawable.home_selector);
//        drawable1.setBounds(0, 20, 80, 80);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
//        rbIndex.setCompoundDrawables(null, drawable1, null, null);//只放左边
//        Drawable drawable2 = getResources().getDrawable(R.drawable.recoder_selector);
//        drawable2.setBounds(0, 20, 80, 80);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
//        rbRecord.setCompoundDrawables(null, drawable2, null, null);//只放左边
//        Drawable drawable3 = getResources().getDrawable(R.drawable.message_selector);
//        drawable3.setBounds(0, 20, 80, 80);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
//        rbMessage.setCompoundDrawables(null, drawable3, null, null);//只放左边
//        Drawable drawable4 = getResources().getDrawable(R.drawable.my_selector);
//        drawable4.setBounds(0, 20, 80, 80);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
//        rbMy.setCompoundDrawables(null, drawable4, null, null);//只放左边

        mHomeFg = new HomeFragment();
        mRdFg = new RecordFragment();
        mMessageFg = new MessageFragment();
        mMyFg = new MyFragment();

        fragmentList = new ArrayList<Fragment>();
        fragmentList.add(mHomeFg);
        fragmentList.add(mRdFg);
        fragmentList.add(mMessageFg);
        fragmentList.add(mMyFg);

        // RadioGroup CheckedChange
        mGroup.setOnCheckedChangeListener(new CheckedChangeListener());
        mGroup.check(R.id.rb_index);
        mPager.setAdapter(new MyViewPagerAdapter(this.getSupportFragmentManager()));
        mPager.setOnPageChangeListener(new PageChangeListener());
        mPager.setOffscreenPageLimit(4);
    }


    public class MyViewPagerAdapter extends FragmentPagerAdapter {
        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
            // TODO Auto-generated constructor stub
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

    }

    private class CheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.rb_index:
                    mPager.setCurrentItem(0);
                    break;
                case R.id.rb_record:
                    mPager.setCurrentItem(1);
                    RecordFragment.pager.setCurrentItem(0);
                    break;
                case R.id.rb_message:
                    mPager.setCurrentItem(2);
                    break;
                case R.id.rb_my:
                    mPager.setCurrentItem(3);
                    break;
            }
        }
    }

    private class PageChangeListener implements ViewPager.OnPageChangeListener {
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    mGroup.check(R.id.rb_index);
                    break;
                case 1:
                    mGroup.check(R.id.rb_record);
                    break;
                case 2:
                    mGroup.check(R.id.rb_message);
                    break;
                case 3:
                    mGroup.check(R.id.rb_my);
                    break;
            }
        }

        public void onPageScrollStateChanged(int arg0) {
        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }
    }

    private int backKeyCount; // 返回按键次数
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // moveTaskToBack(false);
            if (backKeyCount == 0) {
                String tip = "再按一次退出应用";
                Toast.makeText(this, tip, Toast.LENGTH_LONG).show();
                backKeyCount++;
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        backKeyCount = 0;
                    }
                }, 1500);
            } else {
                backKeyCount = 0;
                finish(); //销毁主界面

            }
        }
        return false;
    }


}
