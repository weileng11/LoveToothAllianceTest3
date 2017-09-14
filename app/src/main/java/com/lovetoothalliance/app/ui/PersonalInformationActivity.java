package com.lovetoothalliance.app.ui;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.lovetoothalliance.app.R;
import com.lovetoothalliance.app.constant.ServiceUrl;
import com.lovetoothalliance.app.net.BaseNetEntity;
import com.lovetoothalliance.app.net.OkHttpResponseCallback;
import com.lovetoothalliance.app.net.bean.RespnoseResultBean;
import com.lovetoothalliance.app.net.bean.request.PersonInfoReqSendData;
import com.lovetoothalliance.app.net.bean.response.PresonInformationRespBean;
import com.lovetoothalliance.app.widget.TopBarLayout;
import com.utils.SharedPreferencesUtils;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Author:lt
 * time:2017/7/21.
 * contact：weileng143@163.com
 *
 * @description 个人信息
 */

public class PersonalInformationActivity extends BaseActivity {
    private static String Tag = "PersonalInformationActivity";

    @InjectView(R.id.topbar)
    TopBarLayout topbar;
    @InjectView(R.id.btn_person_info)
    Button btnPersonInfo;
    @InjectView(R.id.et_my_account)
    EditText etMyAccount;
    @InjectView(R.id.et_my_name)
    EditText etMyName;
    @InjectView(R.id.et_my_sex)
    EditText etMySex;
    @InjectView(R.id.et_my_birthday)
    EditText etMyBirthday;
    @InjectView(R.id.et_my_nationality)
    EditText etMyNationality;
    @InjectView(R.id.et_my_phone)
    EditText etMyPhone;
    @InjectView(R.id.et_my_address)
    EditText etMyAddress;
    @InjectView(R.id.et_my_body)
    EditText etMyBody;
    @InjectView(R.id.et_my_medicine)
    EditText etMyMedicine;
    @InjectView(R.id.et_my_cqyaowu)
    EditText etMyCqyaowu;
    @InjectView(R.id.et_my_illness)
    EditText etMyIllness;
    @InjectView(R.id.et_my_pregnancy)
    EditText etMyPregnancy;
    @InjectView(R.id.et_my_smoke)
    EditText etMySmoke;
    @InjectView(R.id.et_my_special)
    EditText etMySpecial;
    @InjectView(R.id.et_my_time)
    EditText etMyTime;
    @InjectView(R.id.et_my_toothwei)
    EditText etMyToothwei;
    @InjectView(R.id.et_my_cure)
    EditText etMyCure;
    @InjectView(R.id.et_my_outpatient)
    EditText etMyOutpatient;
    @InjectView(R.id.et_my_slice)
    EditText etMySlice;

    //个人信息
    String myAccount = "";
    String myName = "";
    int mySex = 0;
    String myBirthday = "";
    String myNationality = "";
    String myPhone = "";
    String myAddress = "";
    String myBody = "";
    String myMedicine = "";
    String myCqyaowu = "";
    String myIllness = "";
    String myPregnancy = "";
    String mySmoke = "";
    String mySpecial = "";
    String myTime = "";
    String myToothwei = "";
    String myCure = "";
    String myOutpatient = "";
    String mySlice = "";



    @Override
    protected void initView() {
        setContentView(R.layout.activity_personal_information);
    }

    @Override
    protected void initData() {
        topbar.setTxvTitleName("个人信息");
        getPersonInformationData();
    }

    /**
     * 获取个人信息数据
     */
    public void getPersonInformationData() {
        String mPersonInfoUrl = ServiceUrl.Get_PersonInformation +
                SharedPreferencesUtils.getString(this, "memlogin", "") + "&f=*";
        Log.i(Tag, "个人信息数据" + mPersonInfoUrl);
        BaseNetEntity baseNetEntity = new BaseNetEntity();
        baseNetEntity.get(PersonalInformationActivity.this, mPersonInfoUrl, "正在加载数据", true,
                new OkHttpResponseCallback<PresonInformationRespBean>(PresonInformationRespBean.class) {
                    @Override
                    public void onSuccess(PresonInformationRespBean bean) {
                        Log.i(Tag, "个人信息数据" + bean.data.get(0).toString());
                        if (bean.data.size() > 0) {
                            //开始设置参数
                            setPersonInfoParameter(bean);
                        }
                    }

                    @Override
                    public void onFailError(Exception e) {

                    }
                });
    }

    /**
     * 设置个人信息参数
     */
    public void setPersonInfoParameter(PresonInformationRespBean bean) {
        PresonInformationRespBean.DataBean mDataBean = bean.data.get(0);
        etMyAccount.setText(mDataBean.Email);
        etMyName.setText(mDataBean.RealName);
        if (mDataBean.Sex == 0) {
            etMySex.setText("女");
        } else if (mDataBean.Sex == 1) {
            etMySex.setText("男");
        }
        etMyBirthday.setText(mDataBean.Birthday);
        etMyPhone.setText(mDataBean.Mobile);
        etMyAddress.setText(mDataBean.Address);


        if (null == mDataBean.Fax) {
            Log.i(Tag, "mDataBean.Fax" + mDataBean.Fax);
        } else {
            Log.i(Tag, "mDataBean.Fax有数据" + mDataBean.Fax);
            BaseNetEntity baseNetEntity = new BaseNetEntity();
            PersonInfoReqSendData pRespBean = baseNetEntity.JSONToObject(mDataBean.Fax, PersonInfoReqSendData.class);
            etMyBody.setText(pRespBean.Body);   //身体状况
            etMyMedicine.setText(pRespBean.Medicine); //有无药物过敏
            etMyCqyaowu.setText(pRespBean.Cqyaowu);  //有无长期药物
            etMyIllness.setText(pRespBean.Illness); //有无基础疾病
            etMyPregnancy.setText(pRespBean.Pregnancy); //是否怀孕
            etMySmoke.setText(pRespBean.Smoke); //是否吸烟
            etMySpecial.setText(pRespBean.Special); //有无特殊要求
            etMyTime.setText(pRespBean.Time); //时间
            etMyToothwei.setText(pRespBean.Toothwei); //牙位
            etMyCure.setText(pRespBean.Cure); //治疗
            etMyOutpatient.setText(pRespBean.Outpatient); //门诊及医生
            etMySlice.setText(pRespBean.Slice); //片子
            etMyNationality.setText(pRespBean.Nationality);
        }
    }


    @OnClick(R.id.btn_person_info)
    public void onClick() {
        //提交
        commitPersonInfo();
    }

    /**
     * 提交，更新个人信息
     */
    public void commitPersonInfo() {
        //开始提交订单
        myAccount = etMyAccount.getText().toString();
        myName = etMyName.getText().toString();
        if (etMySex.getText().equals("男")) {
            mySex = 1;
        } else {
            mySex = 0;
        }
        myBirthday = etMyBirthday.getText().toString();
        myNationality = etMyNationality.getText().toString();
        myPhone = etMyPhone.getText().toString();
        myAddress = etMyAddress.getText().toString();
        myBody = etMyBody.getText().toString();
        myMedicine = etMyMedicine.getText().toString();
        myCqyaowu = etMyCqyaowu.getText().toString();
        myIllness = etMyIllness.getText().toString();
        myPregnancy = etMyPregnancy.getText().toString();
        mySmoke = etMySmoke.getText().toString();
        mySpecial = etMySpecial.getText().toString();
        myTime = etMyTime.getText().toString();
        myToothwei = etMyToothwei.getText().toString();
        myCure = etMyCure.getText().toString();
        myOutpatient = etMyOutpatient.getText().toString();
        mySlice = etMySlice.getText().toString();

        PersonInfoReqSendData personInfoReqSendData = new PersonInfoReqSendData();
        personInfoReqSendData.Body = myBody;
        personInfoReqSendData.Medicine = myMedicine;
        personInfoReqSendData.Cqyaowu = myCqyaowu;
        personInfoReqSendData.Illness = myIllness;
        personInfoReqSendData.Pregnancy = myPregnancy;
        personInfoReqSendData.Smoke = mySmoke;
        personInfoReqSendData.Special = mySpecial;
        personInfoReqSendData.Time = myTime;
        personInfoReqSendData.Toothwei = myToothwei;
        personInfoReqSendData.Cure = myCure;
        personInfoReqSendData.Outpatient = myOutpatient;
        personInfoReqSendData.Slice = mySlice;
        personInfoReqSendData.Nationality = myNationality;


        BaseNetEntity baseNetEntity = new BaseNetEntity();
        String fax = baseNetEntity.getSendData(personInfoReqSendData);
        Log.i(Tag, "打印格式" + fax);
        //这里将对象转换成为字符串
        String mPersonInfoCommitUrl = ServiceUrl.Commit_PersonInfo +
                SharedPreferencesUtils.getString(this, "memlogin", "") +
                "&Email=" + myAccount + "&RealName=" + myName + "&Sex=" + mySex + "&Birthday=" + myBirthday
                + "&Mobile=" + myPhone + "&Address=" + myAddress + "&Fax=" + fax;
        Log.i(Tag, "个人信息提交路径" + mPersonInfoCommitUrl);
        baseNetEntity.get(PersonalInformationActivity.this, mPersonInfoCommitUrl, "正在加载数据", true,
                new OkHttpResponseCallback<RespnoseResultBean>(RespnoseResultBean.class) {
                    @Override
                    public void onSuccess(RespnoseResultBean bean) {
                        Log.i(Tag, "获取个人信息成功");
                        SharedPreferencesUtils.saveString(PersonalInformationActivity.this, "myname", myName);
                        finish();
                    }

                    @Override
                    public void onFailError(Exception e) {

                    }
                });
    }


}
