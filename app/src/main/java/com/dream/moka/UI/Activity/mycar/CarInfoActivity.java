package com.dream.moka.UI.Activity.mycar;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Bean.CarDetailResultBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Component.DaggerBaseComponent;
import com.dream.moka.Contract.CarInfoContract;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Other.Const;
import com.dream.moka.Other.MyApplication;
import com.dream.moka.Presenter.CarInfoPresenter;
import com.dream.moka.R;
import com.dream.moka.UI.Activity.InCompanyRvActivity;
import com.dream.moka.Utils.DateUtils.DateFormatUtil;
import com.dream.moka.Utils.Dialog.DialogUtils;
import com.dream.moka.Utils.GlidUtils;
import com.dream.moka.Utils.IntentUtils;
import com.dream.moka.Utils.StringUtil;
import com.dream.moka.Utils.ToastUtils;
import com.dream.moka.Utils.XRegexUtils;

import java.util.Date;
import java.util.HashMap;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class CarInfoActivity extends BaseCommonActivity implements CarInfoContract {

    @Inject
    CarInfoPresenter mCarInfoPresenter;
    @Bind(R.id.carIcon)
    ImageView mCarIcon;
    @Bind(R.id.brandName)
    TextView mBrandName;
    @Bind(R.id.stylename)
    TextView mStylename;
    @Bind(R.id.status)
    CheckBox mStatus;
    @Bind(R.id.style)
    TextView mStyle;
    @Bind(R.id.lastTime)
    TextView mLastTime;
    @Bind(R.id.carnum_tv)
    TextView mCarnumTv;
    @Bind(R.id.lastmileage)
    TextView mLastmileage;
    @Bind(R.id.allmileage)
    EditText mAllmileage;
    @Bind(R.id.login_icon)
    ImageView mLoginIcon;
    @Bind(R.id.login_time)
    TextView mLoginTime;
    @Bind(R.id.buyCar_icon)
    ImageView mBuyCarIcon;
    @Bind(R.id.buyCar_time)
    TextView mBuyCarTime;
    @Bind(R.id.owner)
    EditText mOwner;
    @Bind(R.id.baoxianTime)
    TextView mBaoxianTime;
    @Bind(R.id.idCardNum)
    EditText mIdCardNum;
    @Bind(R.id.baoxianCity)
    TextView mBaoxianCity;
    @Bind(R.id.isGuohu)
    TextView mIsGuohu;
    @Bind(R.id.chejia)
    TextView mChejia;
    @Bind(R.id.baoxianName)
    TextView mBaoxianName;
    @Bind(R.id.baoxianIcon)
    ImageView mBaoxianIcon;
    private Dialog mLoadingDialog;
    private String mCarId;
    private TimePickerView dateDialog;

    public static void openAct(Context context, String carId) {
        Intent intent = new Intent(context, CarInfoActivity.class);
        intent.putExtra("carId", carId);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_carinfo;
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {

    }

    @Override
    public void initView() {
        DaggerBaseComponent.builder()
                .appComponent(MyApplication.getInstance().getAppComponent())
//                .mainActivityModule(new MainActivityModule(this))
                .build()
                .inject(this);
        mCarInfoPresenter.attachView(this);
        mLoadingDialog = DialogUtils.initLoadingDialog(mActivity);
        dateDialog = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                long time = date.getTime();
                String dateStr = DateFormatUtil.getTime(time, Const.Y_M_D);
                if (dateStr != null) {
                    switch (tempTag) {
                        case 1:
                            mLoginTime.setText(dateStr);
                            break;
                        case 2:
                            mBuyCarTime.setText(dateStr);
                            break;
                        case 3:
                            mBaoxianTime.setText(dateStr);
                            break;
                    }
                }
                Log.e("tag", "dateStr=" + dateStr);
            }
        }).setType(new boolean[]{true, true, true, false, false, false})
                .setContentSize(16)//滚轮文字大小
                .setTitleSize(13)//标题文字大小
                .setCancelText("取消")//取消按钮文字
                .setLabel(" 年", "月", "日", "时", "分", "秒")
                .isCyclic(true)//是否循环滚动
                .build();
        setEditTextReadOnly(mOwner);
        setEditTextReadOnly(mIdCardNum);
        setEditTextReadOnly(mAllmileage);
    }

    @Override
    public String initTitleText() {
        return "我的爱车";
    }

    @Override
    public String initRightTv() {
        return "编辑";
    }

    @Override
    public boolean isRighttv() {
        return true;
    }

    @Override
    public void loadResum() {
    }

    @Override
    public void initDatas() {
        mCarId = getIntent().getStringExtra("carId");
        mLoadingDialog.show();
        mCarInfoPresenter.getCarInfo(mCarId);
    }

    @Override
    public void eventListener() {
        mStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    mStatus.setText("已设为默认");
                } else {
                    mStatus.setText(" 设为默认 ");
                }
            }
        });
    }

    private String mBaoxianID = "";//保险公司的id
    private int tempTag;

    @OnClick({R.id.back_relay, R.id.right_tv, R.id.login_time, R.id.buyCar_time, R.id.baoxianTime, R.id.baoxianName})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_relay:
                finish();
                break;
            case R.id.right_tv:
                if (right_tv.getText().toString().equals("编辑")) {

                    setEditTextCanSet(mOwner);
                    setEditTextCanSet(mIdCardNum);
                    setEditTextCanSet(mAllmileage);

                    mStatus.setClickable(true);
                    mStatus.setFocusable(true);
                    mLoginIcon.setVisibility(View.VISIBLE);
                    mBuyCarIcon.setVisibility(View.VISIBLE);
                    mBaoxianIcon.setVisibility(View.VISIBLE);

                    right_tv.setText("保存");
                } else if (right_tv.getText().toString().equals("保存")) {
                    if (checkEmpty()) {
                        String status;
                        if (mStatus.isChecked()) {
                            status = "1";
                        } else {
                            status = "2";
                        }

                        String token = CommonAction.getToken();
                        HashMap<String,String> map=new HashMap<>();
                        map.put("token",token);
                        map.put("carId",mCarId);
                        map.put("insuranceId",insuranceId);
                        map.put("buyDate",mBuyCarTime.getText().toString());
                        map.put("insuranceDate",mBaoxianTime.getText().toString());
                        map.put("registerDate",mLoginTime.getText().toString());
                        map.put("owner",mOwner.getText().toString());
                        map.put("idNo",mIdCardNum.getText().toString());
                        map.put("status",status);
                        map.put("mileage",mAllmileage.getText().toString());
                        mCarInfoPresenter.editUserCar(map);
                    }
                }
                break;
            case R.id.login_time:
                if (right_tv.getText().toString().equals("保存")) {
                    tempTag = 1;
//                    hideImm();
                    dateDialog.show();
                }
                break;
            case R.id.buyCar_time:
                if (right_tv.getText().toString().equals("保存")) {
                    tempTag = 2;
//                    hideImm();
                    dateDialog.show();
                }

                break;
            case R.id.baoxianTime:
                if (right_tv.getText().toString().equals("保存")) {
                    tempTag = 3;
//                    hideImm();
                    dateDialog.show();
                }

                break;
            case R.id.baoxianName:
                if (right_tv.getText().toString().equals("保存")) {
                    IntentUtils.toActivity_result(InCompanyRvActivity.class,mActivity,111);
                }
                break;
        }
    }

    private boolean checkEmpty() {
        if (TextUtils.isEmpty(mLoginTime.getText())) {
            ToastUtils.Toast_short("请选择上牌时间");
            return false;
        }
        if (TextUtils.isEmpty(mAllmileage.getText())) {
            ToastUtils.Toast_short("请填写行驶总里程");
            return false;
        }
        if (TextUtils.isEmpty(mBuyCarTime.getText())) {
            ToastUtils.Toast_short("请选择购买日期");
            return false;
        }
        if (TextUtils.isEmpty(mBaoxianName.getText())) {
            ToastUtils.Toast_short("请选择保险公司");
            return false;
        }
        if (TextUtils.isEmpty(mBaoxianTime.getText())) {
            ToastUtils.Toast_short("请选择保险到期时间");
            return false;
        }
        if (TextUtils.isEmpty(mOwner.getText())) {
            ToastUtils.Toast_short("请填写车主姓名");
            return false;
        }
        if (TextUtils.isEmpty(mIdCardNum.getText())) {
            ToastUtils.Toast_short("请填写身份证号");
            return false;
        }
        if (!XRegexUtils.checkIdCard(mIdCardNum.getText().toString())) {
            ToastUtils.Toast_short("身份证号格式错误");
            return false;
        }
        return true;
    }

    @Override
    public void showError() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }

    @Override
    public void complete() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }

    @Override
    public void onDataSuccess(CarDetailResultBean carDetailResultBean) {

        mBrandName.setText(carDetailResultBean.getBrandName() + "");
        mStylename.setText(carDetailResultBean.getCateName() + "" + carDetailResultBean.getConfigName());
        mStyle.setText(carDetailResultBean.getCateName() + "" + carDetailResultBean.getConfigName());
        GlidUtils.LoadImgForUrl(mContext, carDetailResultBean.getBranLogo() == null ? "" : carDetailResultBean.getBranLogo(), mCarIcon);
        CarDetailResultBean.UserCarBean userCar = carDetailResultBean.getUserCar();
        CarDetailResultBean.CarInsuranceBean carInsurance = carDetailResultBean.getCarInsurance();
        if (carInsurance!=null){
            mBaoxianName.setText(StringUtil.checkNull(carInsurance.getName()));
        }
        if (userCar != null) {
            String status = userCar.getStatus();
            if (status.equals("1")) {
                mStatus.setText("已设为默认");
                mStatus.setChecked(true);
            } else {
                mStatus.setText(" 设为默认 ");
                mStatus.setChecked(false);
            }
            String lastMainDate = userCar.getLastMainDate();
            mLastTime.setText(StringUtil.NoNullOrEmpty(lastMainDate) ?
                    (lastMainDate.length() > 10 ? lastMainDate.substring(0, 10) : lastMainDate) : "");
            mCarnumTv.setText(userCar.getCardNo() != null ? userCar.getCardNo() : "");
            mLastmileage.setText(String.valueOf(userCar.getRecomMaintMile()));
            mAllmileage.setText(String.valueOf(userCar.getMileage()));
            String registerDate = userCar.getRegisterDate();
            mLoginTime.setText(StringUtil.NoNullOrEmpty(registerDate) ?
                    (registerDate.length() > 10 ? registerDate.substring(0, 10) : registerDate) : "");
            String buyDate = userCar.getBuyDate();
            mBuyCarTime.setText(StringUtil.NoNullOrEmpty(buyDate) ?
                    (buyDate.length() > 10 ? buyDate.substring(0, 10) : buyDate) : "");
            mChejia.setText(userCar.getVinNo() != null ? userCar.getVinNo() : "");
            mOwner.setText(userCar.getOwner() != null ? userCar.getOwner() : "");
            String insuranceDate = userCar.getInsuranceDate();
            mBaoxianTime.setText(StringUtil.NoNullOrEmpty(insuranceDate) ?
                    (insuranceDate.length() > 10 ? insuranceDate.substring(0, 10) : insuranceDate) : "");
            mIdCardNum.setText(userCar.getIdNo() != null ? userCar.getIdNo() : "");
        }
//        mBaoxianName.setText(carDetailResultBean.getCarInsurance().getName() != null ? carDetailResultBean.getCarInsurance().getName() : "");

    }

    @Override
    public void onEditSuccess() {
        setEditTextReadOnly(mOwner);
        setEditTextReadOnly(mIdCardNum);
        setEditTextReadOnly(mAllmileage);
        mStatus.setClickable(false);
        mStatus.setFocusable(false);


        mLoginIcon.setVisibility(View.INVISIBLE);
        mBuyCarIcon.setVisibility(View.INVISIBLE);
        mBaoxianIcon.setVisibility(View.INVISIBLE);
        right_tv.setText("编辑");
    }


    public static void setEditTextReadOnly(EditText view) {
        view.setCursorVisible(false);      //设置输入框中的光标不可见
        view.setFocusable(false);           //无焦点
        view.setFocusableInTouchMode(false);     //触摸时也得不到焦点
    }

    public static void setEditTextCanSet(EditText view) {
        view.setCursorVisible(true);
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
    }

    private String insuranceId = "";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 111 && resultCode == 111 && data != null) {
            insuranceId = data.getStringExtra("incomId");
            String incomName = data.getStringExtra("incomName");
            mBaoxianName.setText(StringUtil.checkNull(incomName));

        }
    }
}
